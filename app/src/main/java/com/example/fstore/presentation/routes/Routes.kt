package com.example.fstore.presentation.routes

import kotlinx.serialization.Serializable

const val RegisterScreenRoute = "register_screen"
const val LogInScreenRoute = "login_screen"
const val NavigatorScreenRoute = "navigator_screen"
const val HomeScreenRoute = "home_screen"
const val FavoriteScreenRoute = "favorite_screen"
const val BagScreenRoute = "bag_screen"
const val ProfileScreenRoute = "profile_screen"
const val ShopScreenRoute = "shop_screen"
const val FilterScreenRoute = "filter_screen"
const val BrandScreenRoute = "brand_screen"
const val ViewAllScreenRoutes = "view_all_screen"
const val AddressScreenRoute = "address_screen"
const val SuccessScreenRoute = "success_screen"
const val AddAddressScreenRoute = "add_address_screen"
const val OrderScreenRoute = "order_screen"
const val SearchScreenRoute = "search_screen"

@Serializable
data class DetailsScreenRouter(val id: Int)

@Serializable
data class PostShopScreenRoute(val gender: String, val mainCategory: String)

@Serializable
data class CheckOutScreenRoute(val amount: String)

@Serializable
data class DetailsOrderScreenRoute(val id: Int)

@Serializable
data class ReviewScreenRoute(val id:Int)


