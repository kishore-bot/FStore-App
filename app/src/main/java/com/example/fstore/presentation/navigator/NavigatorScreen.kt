package com.example.fstore.presentation.navigator


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.fstore.domain.model.send.ProductQuery
import com.example.fstore.presentation.address.AddressScreen
import com.example.fstore.presentation.address.AddressViewModel
import com.example.fstore.presentation.address.add_address.AddAddressScreen
import com.example.fstore.presentation.address.add_address.AddAddressViewModel
import com.example.fstore.presentation.bag.BagEvents
import com.example.fstore.presentation.bag.BagScreen
import com.example.fstore.presentation.bag.BagViewModel
import com.example.fstore.presentation.checkout.CheckOutViewModel
import com.example.fstore.presentation.checkout.CheckoutScreen
import com.example.fstore.presentation.details.DetailsEvent
import com.example.fstore.presentation.details.DetailsScreen
import com.example.fstore.presentation.details.DetailsViewModel
import com.example.fstore.presentation.details.view_all.ViewAllScreen
import com.example.fstore.presentation.favorite.FavoriteScreen
import com.example.fstore.presentation.favorite.FavoriteViewModel
import com.example.fstore.presentation.home.HomeScreen
import com.example.fstore.presentation.home.HomeViewModel
import com.example.fstore.presentation.navigator.components.CustomBottomAppBar
import com.example.fstore.presentation.navigator.components.items
import com.example.fstore.presentation.order_details.OrderDetailsEvent
import com.example.fstore.presentation.order_details.OrderDetailsScreen
import com.example.fstore.presentation.order_details.OrderDetailsViewModel
import com.example.fstore.presentation.orders.OrdersScreen
import com.example.fstore.presentation.orders.OrdersViewModel
import com.example.fstore.presentation.profile.ProfileScreen
import com.example.fstore.presentation.reviews.ReviewEvent
import com.example.fstore.presentation.reviews.ReviewScreen
import com.example.fstore.presentation.reviews.ReviewViewModel
import com.example.fstore.presentation.routes.AddAddressScreenRoute
import com.example.fstore.presentation.routes.AddressScreenRoute
import com.example.fstore.presentation.routes.BagScreenRoute
import com.example.fstore.presentation.routes.BrandScreenRoute
import com.example.fstore.presentation.routes.CheckOutScreenRoute
import com.example.fstore.presentation.routes.DetailsOrderScreenRoute
import com.example.fstore.presentation.routes.DetailsScreenRouter
import com.example.fstore.presentation.routes.FavoriteScreenRoute
import com.example.fstore.presentation.routes.FilterScreenRoute
import com.example.fstore.presentation.routes.HomeScreenRoute
import com.example.fstore.presentation.routes.OrderScreenRoute
import com.example.fstore.presentation.routes.PostShopScreenRoute
import com.example.fstore.presentation.routes.ProfileScreenRoute
import com.example.fstore.presentation.routes.ReviewScreenRoute
import com.example.fstore.presentation.routes.SearchScreenRoute
import com.example.fstore.presentation.routes.ShopScreenRoute
import com.example.fstore.presentation.routes.SuccessScreenRoute
import com.example.fstore.presentation.routes.ViewAllScreenRoutes
import com.example.fstore.presentation.search.SearchScreen
import com.example.fstore.presentation.shop.ShopEvent
import com.example.fstore.presentation.shop.ShopScreen
import com.example.fstore.presentation.shop.ShopViewModel
import com.example.fstore.presentation.shop.post_shop_screen.PostShopScreen
import com.example.fstore.presentation.shop.post_shop_screen.filter.FilterScreen
import com.example.fstore.presentation.shop.post_shop_screen.filter.brand.BrandScreen
import com.example.fstore.presentation.success.SuccessScreen
import com.example.fstore.utils.getRandomNumber

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigatorScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by remember {
        mutableIntStateOf(0)
    }

    selectedItem = remember(key1 = backStackState) {
        when (backStackState?.destination?.route) {
            HomeScreenRoute -> 0
            ShopScreenRoute -> 1
            FavoriteScreenRoute -> 2
            BagScreenRoute -> 3
            ProfileScreenRoute -> 4
            else -> 0
        }
    }

    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == HomeScreenRoute || backStackState?.destination?.route == FavoriteScreenRoute || backStackState?.destination?.route == BagScreenRoute || backStackState?.destination?.route == ProfileScreenRoute || backStackState?.destination?.route == ShopScreenRoute
    }

    val shopViewModel: ShopViewModel = hiltViewModel()
    val detailsViewModel: DetailsViewModel = hiltViewModel()

    val tabIndex = remember { mutableIntStateOf(0) }

    Scaffold(
        modifier = modifier.fillMaxSize(),

        bottomBar = {
            if (isBottomBarVisible) {
                CustomBottomAppBar(items = items, selectedIndex = selectedItem) { index ->
                    when (index) {
                        0 -> navController.navigate(HomeScreenRoute)
                        1 -> navController.navigate(ShopScreenRoute)
                        2 -> navController.navigate(FavoriteScreenRoute)
                        3 -> navController.navigate(BagScreenRoute)
                        4 -> navController.navigate(ProfileScreenRoute)
                    }
                }
            }
        },

        ) {
        val bottomPaddingValue = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = HomeScreenRoute,
            modifier = Modifier.padding(bottom = bottomPaddingValue)
        ) {
            composable(HomeScreenRoute) {
                val random = remember {
                    mutableIntStateOf(getRandomNumber())
                }

                val viewModel: HomeViewModel = hiltViewModel()
                HomeScreen(
                    viewModel = viewModel,
                    random = random.intValue,
                )
            }

            composable(ShopScreenRoute) {
                ShopScreen(onNav = { gender, cate ->
                    val query = ProductQuery(mainCategory = cate, gender = gender)
                    shopViewModel.onEvent(ShopEvent.UploadQuery(query))
                    val route = PostShopScreenRoute(gender = gender, mainCategory = cate)
                    navController.navigate(route)
                }, tabIndex = tabIndex)
            }

            composable(FavoriteScreenRoute) {
                val viewModel = hiltViewModel<FavoriteViewModel>()
                FavoriteScreen(
                    viewModel = viewModel,
                    onNav = { id -> navController.navigate(DetailsScreenRouter(id = id)) },
                    onSearch = { navController.navigate(SearchScreenRoute) },
                )
            }

            composable(BagScreenRoute) {
                val bagViewModel: BagViewModel = hiltViewModel<BagViewModel>()
                LaunchedEffect(Unit) {
                    bagViewModel.onEvent(BagEvents.FetchBags)
                    bagViewModel.onEvent(BagEvents.FetchPrice)
                }
                BagScreen(
                    viewModel = bagViewModel,
                    navToDetailScreen = { id -> navController.navigate(DetailsScreenRouter(id = id)) },
                    onSubmit = { amount ->
                        navController.navigate(CheckOutScreenRoute(amount = amount.toString()))
                    },
                )
            }

            composable(ProfileScreenRoute) {
                ProfileScreen(
                    onOrderClick = { navController.navigate(OrderScreenRoute) },
                    onAddressClick = {
                        navController.navigate(
                            AddressScreenRoute
                        )
                    },
                )
            }

            composable<DetailsScreenRouter> { arg ->
                val args = arg.toRoute<DetailsScreenRouter>()
                LaunchedEffect(Unit) {
                    detailsViewModel.onEvent(DetailsEvent.UpdateId(args.id))
                    detailsViewModel.onEvent(DetailsEvent.FetchDetails)
                }
                val details = detailsViewModel.state.value.details

                LaunchedEffect(details) {
                    details?.let {
                        val query = ProductQuery(
                            category = details.category,
                            mainCategory = details.mainCategory,
                            gender = details.gender
                        )
                        detailsViewModel.onEvent(DetailsEvent.UploadQuery(query = query))
                        detailsViewModel.onEvent(DetailsEvent.GetSimilarProducts)
                    }
                }

                DetailsScreen(
                    details = details, viewModel = detailsViewModel,
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onSimilar = {
                        navController.navigate(ViewAllScreenRoutes)
                    },
                    onReviewClick = { id -> navController.navigate(ReviewScreenRoute(id)) },
                )
            }

            composable(ViewAllScreenRoutes) {
                LaunchedEffect(Unit) {
                    detailsViewModel.onEvent(DetailsEvent.GetSimilarProducts)
                }
                ViewAllScreen(viewModel = detailsViewModel, onNav = { id ->
                    navController.navigate(DetailsScreenRouter(id))
                }, onBack = { navController.popBackStack() })
            }

            composable<PostShopScreenRoute> { postScreen ->
                val args = postScreen.toRoute<PostShopScreenRoute>()
                val products = shopViewModel.state.value.products?.collectAsLazyPagingItems()
                val category = shopViewModel.state.value.categories?.collectAsState(initial = null)
                LaunchedEffect(Unit) {
                    shopViewModel.onEvent(ShopEvent.FetchCategories)
                    shopViewModel.onEvent(ShopEvent.FetchProducts)
                }
                PostShopScreen(
                    gender = args.gender,
                    viewModel = shopViewModel,
                    onFilterClick = {
                        navController.navigate(
                            FilterScreenRoute
                        )
                    },
                    onBackClick = { navController.popBackStack() },
                    products = products,
                    category = category,
                    onNav = { id -> navController.navigate(DetailsScreenRouter(id = id)) },
                    onSearch = {
                        navController.navigate(
                            SearchScreenRoute
                        )
                    },
                )
            }

            composable(FilterScreenRoute) {
                FilterScreen(viewModel = shopViewModel,
                    onBackClick = { navController.popBackStack() },
                    onBrandClick = {
                        navController.navigate(BrandScreenRoute)
                    })
            }

            composable(BrandScreenRoute) {
                LaunchedEffect(Unit) {
                    shopViewModel.onEvent(ShopEvent.FetchBrands)
                }
                BrandScreen(viewModel = shopViewModel,
                    onBackClick = { navController.popBackStack() })
            }

            composable<CheckOutScreenRoute> { checkoutArg ->
                val args = checkoutArg.toRoute<CheckOutScreenRoute>()
                val viewModel: CheckOutViewModel = hiltViewModel()
                CheckoutScreen(
                    viewModel = viewModel,
                    amount = args.amount,
                    onAddressClick = {
                        navController.navigate(AddressScreenRoute)
                    },
                    onBackClick = { navController.popBackStack() },
                    onSubmit = { navController.navigateAndClearBackStack(SuccessScreenRoute) },
                )
            }

            composable(AddressScreenRoute) {
                val addressViewModel: AddressViewModel = hiltViewModel()
                AddressScreen(
                    viewModel = addressViewModel,
                    onBackClick = { navController.popBackStack() },
                    onAddAddressClick = { navController.navigate(AddAddressScreenRoute) },
                )
            }

            composable(AddAddressScreenRoute) {
                val viewModel: AddAddressViewModel = hiltViewModel()
                AddAddressScreen(
                    onBackClick = { navController.popBackStack() }, viewModel = viewModel
                )
            }

            composable(SuccessScreenRoute) {
                SuccessScreen(
                    onContinueShoppingClick = {
                        navController.navigateAndClearBackStack(HomeScreenRoute)
                    },
                )
            }

            composable(OrderScreenRoute) {
                val viewModel: OrdersViewModel = hiltViewModel()
                LaunchedEffect(Unit) {
                    viewModel.fetchOrders()
                }
                OrdersScreen(
                    viewModel = viewModel,
                    onBackClick = { navController.popBackStack() },
                    onDetailsClick = { id ->
                        navController.navigate(DetailsOrderScreenRoute(id = id))
                    },
                )
            }

            composable<DetailsOrderScreenRoute> { orderDetails ->
                val id = orderDetails.toRoute<DetailsOrderScreenRoute>().id
                val viewModel: OrderDetailsViewModel = hiltViewModel()
                LaunchedEffect(Unit) {
                    viewModel.onEvent(OrderDetailsEvent.UpdateId(id))
                    viewModel.onEvent(OrderDetailsEvent.FetchData)
                }
                val orderDetailsModel = viewModel.state.value.orderDetails
                OrderDetailsScreen(
                    orderDetails = orderDetailsModel,
                    onBackClick = {
                        navController.popBackStack()
                    },
                )
            }
            composable<ReviewScreenRoute> { arg ->
                val id = arg.toRoute<ReviewScreenRoute>().id
                val reviewViewModel: ReviewViewModel = hiltViewModel()
                LaunchedEffect(Unit) {
                    reviewViewModel.onEvent(ReviewEvent.UploadId(id))
                    reviewViewModel.onEvent(ReviewEvent.IsOrderedCheck)
                    reviewViewModel.onEvent(ReviewEvent.FetchRating)
                    reviewViewModel.onEvent(ReviewEvent.FetchReview)
                }
                ReviewScreen(
                    viewModel = reviewViewModel,
                    onBackClick = { navController.popBackStack() },
                )
            }
            composable(SearchScreenRoute) {
                SearchScreen(
                    onBackClick = { navController.popBackStack() },
                    onNav = { id ->
                        navController.navigate(DetailsScreenRouter(id))
                    },
                )
            }
        }
    }
}

fun NavController.navigateAndClearBackStack(destination: String) {
    this.navigate(destination) {
        popUpTo(graph.startDestinationId) {
            inclusive = true
        }
        launchSingleTop = true
    }
}