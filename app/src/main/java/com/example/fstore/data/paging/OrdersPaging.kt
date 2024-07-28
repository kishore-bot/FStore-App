package com.example.fstore.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.fstore.data.remote.OrderApi
import com.example.fstore.domain.model.rec_sub.Order
import kotlinx.coroutines.delay

class OrdersPaging(
    private val orderApi: OrderApi
) : PagingSource<Int, Order>() {
    override fun getRefreshKey(state: PagingState<Int, Order>): Int {
        return state.anchorPosition?.let { anchorPos ->
            val anchorPage = state.closestPageToPosition(anchorPos)
            anchorPage?.nextKey?.minus(1)
        } ?: 1
    }


    private var totalProducts: Int = 0
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Order> {
        val page = params.key ?: 1
        return try {
            val orderResponse = orderApi.fetchOrders(page = page)
            totalProducts += orderResponse.orders.size
            val orders = orderResponse.orders.distinctBy { it.id }
            delay(500)
            LoadResult.Page(
                data = orders,
                nextKey = if (totalProducts == orderResponse.total) null else page + 1,
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
