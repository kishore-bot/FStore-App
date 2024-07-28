package com.example.fstore.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.fstore.data.remote.ProductApi
import com.example.fstore.domain.model.rec_sub.Product
import com.example.fstore.domain.model.send.ProductQuery
import kotlinx.coroutines.delay

class ProductPaging(
    private val productApi: ProductApi, private val query: ProductQuery
) : PagingSource<Int, Product>() {
    override fun getRefreshKey(state: PagingState<Int, Product>): Int {
        return state.anchorPosition?.let { anchorPos ->
            val anchorPage = state.closestPageToPosition(anchorPos)
            anchorPage?.nextKey?.minus(1)
        } ?: 1
    }


    private var totalProducts: Int = 0
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        val page = params.key ?: 1
        return try {
            delay(500)
            val productResponse = productApi.fetchProducts(
                page = page,
                category = query.category,
                byNew = query.byNew,
                byPriceSort = query.byPriceSort,
                byPopular = query.byPopular,
                byRating = query.byRating,
                color = query.color,
                mainCategory = query.mainCategory,
                available = query.available,
                gender = query.gender,
                size = query.size,
                name = query.name,
                lowPrice = query.lowPrice,
                highPrice = query.highPrice
            )
            totalProducts += productResponse.products.size
            val products = productResponse.products.distinctBy { it.id }
            LoadResult.Page(
                data = products,
                nextKey = if (totalProducts == productResponse.total) null else page + 1,
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