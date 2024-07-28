package com.example.fstore.data.repo

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.fstore.data.paging.ProductPaging
import com.example.fstore.data.remote.ProductApi
import com.example.fstore.domain.model.BrandsModel
import com.example.fstore.domain.model.CategoryModel
import com.example.fstore.domain.model.DetailsModel
import com.example.fstore.domain.model.ProductModel
import com.example.fstore.domain.model.rec_sub.Product
import com.example.fstore.domain.model.send.ProductQuery
import com.example.fstore.domain.repo.ProductRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductRepoImp @Inject constructor(
    private val productApi: ProductApi
) : ProductRepo {
    override suspend fun getProductDetails(id: Int): DetailsModel {
        return productApi.getProductDetails(id)
    }

    override suspend fun getProducts(query: ProductQuery): Flow<PagingData<Product>> {
        Log.d(
            "Test1212", productApi.fetchProducts(
                page = 1, category = "clothes", gender = "male"
            ).total.toString()
        )
        return Pager(config = PagingConfig(pageSize = 10), pagingSourceFactory = {
            ProductPaging(productApi = productApi, query = query)
        }).flow
    }

    override suspend fun fetchBrands(mainCategory: String): Flow<BrandsModel> {
        return flow {
            emit(productApi.getBrands(mainCategory))
        }
    }

    override suspend fun fetchCategory(mainCategory: String, gender: String): Flow<CategoryModel> {
        return flow {
            emit(productApi.getCategories(mainCategory = mainCategory, gender = gender))
        }
    }

    override suspend fun homeNewProducts(): Flow<ProductModel> {
        return flow {
            emit(productApi.fetchProducts(page = 1, byNew = 1))
        }
    }
}
