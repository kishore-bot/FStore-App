package com.example.fstore.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.fstore.data.remote.ProductApi
import com.example.fstore.data.remote.ReviewApi
import com.example.fstore.domain.model.rec_sub.Product
import com.example.fstore.domain.model.rec_sub.ReviewModel
import com.example.fstore.domain.model.send.ProductQuery
import kotlinx.coroutines.delay

class ReviewPaging(
    private val reviewApi: ReviewApi, private val id: Int
) : PagingSource<Int, ReviewModel>() {
    override fun getRefreshKey(state: PagingState<Int, ReviewModel>): Int {
        return state.anchorPosition?.let { anchorPos ->
            val anchorPage = state.closestPageToPosition(anchorPos)
            anchorPage?.nextKey?.minus(1)
        } ?: 1
    }


    private var totalReview: Int = 0
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReviewModel> {
        val page = params.key ?: 1
        return try {
            delay(500)
            val reviewResponse = reviewApi.fetchReview(id = id, page = page)
            totalReview += reviewResponse.review.size
            val reviews = reviewResponse.review.distinctBy { it.id }
            LoadResult.Page(
                data = reviews,
                nextKey = if (totalReview == reviewResponse.total) null else page + 1,
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