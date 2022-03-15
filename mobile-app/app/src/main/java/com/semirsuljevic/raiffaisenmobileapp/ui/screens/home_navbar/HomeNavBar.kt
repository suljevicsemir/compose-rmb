package com.semirsuljevic.raiffaisenmobileapp.ui.screens.home_navbar

//
//@OptIn(ExperimentalPagerApi::class)
//@Composable
//fun HomeNavBar(navController: NavController) {
//    val pagerState = rememberPagerState(pageCount = 5)
//    Scaffold (
//        backgroundColor = Black,
//        bottomBar = {
//            HomeNavBar(navController = navController)
//        }
//    ) {
//        HorizontalPager(state = pagerState, dragEnabled = false) { page ->
//            when(page) {
//                0 -> Home(navController = navController)
//                1 -> PaymentsScreen(navController = navController)
//                2 -> MyProfileScreen(navController = navController)
//                3 -> MoreScreen(navController = navController)
//            }
//        }
//    }
//
//}
//
//@OptIn(ExperimentalPagerApi::class)
//@Composable
//private fun HomeNavBarItems(pagerState: PagerState) {
//    val coroutineScope = rememberCoroutineScope()
//    RMBNavBar(
//        items = listOf(
//            RMBBottomBarItem(
//                route = Screen.IntroHome.route,
//                icon = Icons.Default.Home,
//                index = 0
//            ),
//            RMBBottomBarItem(
//                route = Screen.IntroLocations.route,
//                icon = Icons.Outlined.Payments,
//                index = 1
//            ),
//            RMBBottomBarItem(
//                route = Screen.IntroProducts.route,
//                icon = Icons.Default.Person,
//                index = 2
//            ),
//            RMBBottomBarItem(
//                route = Screen.IntroMore.route,
//                icon = Icons.Outlined.More,
//                index = 3
//            )
//        ),
//        selectedIndex = pagerState.currentPage,
//        onItemClick = {
//            coroutineScope.launch {
//                pagerState.scrollToPage(it)
//            }
//        }
//    )
//}