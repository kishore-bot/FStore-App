package com.example.fstore.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.fstore.data.remote.BagApi
import com.example.fstore.domain.model.rec_sub.Bag
import kotlinx.coroutines.delay

class BagPaging(
    private val bagApi: BagApi
) : PagingSource<Int, Bag>() {
    override fun getRefreshKey(state: PagingState<Int, Bag>): Int {
        return state.anchorPosition?.let { anchorPos ->
            val anchorPage = state.closestPageToPosition(anchorPos)
            anchorPage?.nextKey?.minus(1)
        } ?: 1
    }


    private var totalProducts: Int = 0
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Bag> {
        val page = params.key ?: 1
        return try {
            val bagResponse = bagApi.getPage(page = page)
            totalProducts += bagResponse.bags.size
            val bags = bagResponse.bags.distinctBy { it.id }
            delay(500)
            LoadResult.Page(
                data = bags,
                nextKey = if (totalProducts == bagResponse.total) null else page + 1,
                prevKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }

}