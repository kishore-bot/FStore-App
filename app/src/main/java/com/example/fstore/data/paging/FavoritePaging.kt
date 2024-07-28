package com.example.fstore.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.fstore.data.remote.FavoriteApi
import com.example.fstore.domain.model.rec_sub.Favorite
import kotlinx.coroutines.delay

class FavoritePaging(
    private val favoriteApi: FavoriteApi
) : PagingSource<Int, Favorite>() {
    override fun getRefreshKey(state: PagingState<Int, Favorite>): Int {
        return state.anchorPosition?.let { anchorPos ->
            val anchorPage = state.closestPageToPosition(anchorPos)
            anchorPage?.nextKey?.minus(1)
        } ?: 1
    }


    private var totalProducts: Int = 0
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Favorite> {
        val page = params.key ?: 1
        return try {
            val favoriteResponse = favoriteApi.fetchFavorites(page = page)
            totalProducts += favoriteResponse.favorites.size
            val favorites = favoriteResponse.favorites.distinctBy { it.id }
            delay(500)
            LoadResult.Page(
                data = favorites,
                nextKey = if (totalProducts == favoriteResponse.total) null else page + 1,
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