package com.amirmousavi.divarapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.amirmousavi.onboarding_presentation.explore_cities_screen.ExploreCitiesScreen
import com.amirmousavi.onboarding_presentation.welcome_screen.WelcomeScreen
import com.amirmousavi.post_presentation.post_detail_screen.PostDetailScreen
import com.amirmousavi.post_presentation.post_list_screen.PostListScreen
import com.amirmousavi.post_presentation.select_city_screen.SelectCityScreen

@Composable
fun DivarNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    shouldShowOnboarding: Boolean
) {
    NavHost(
        navController = navController,
        startDestination = if (shouldShowOnboarding) WelcomeRoute else PostListRoute
    ) {
        composable<WelcomeRoute> {
            WelcomeScreen(
                modifier = modifier,
                onNextClick = {
                    navController.navigate(ExploreCitiesRoute)
                }
            )
        }


        composable<ExploreCitiesRoute> {
            ExploreCitiesScreen(
                modifier = modifier,
                onSelectCityClick = {
                    navController.navigate(SelectCityRoute)
                }
            )
        }


        composable<SelectCityRoute> {
            SelectCityScreen(
                modifier = modifier,
                onNavigate = {
                    navController.navigate(PostListRoute) {
                        popUpTo(SelectCityRoute) { inclusive = true }
                    }
                }
            )
        }


        composable<PostListRoute> {
            PostListScreen(
                modifier = modifier,
                onPostClick = { token ->
                    navController.navigate(PostDetailRoute(token

                    ))
                },
                onSelectCityClick = {
                    navController.navigate(SelectCityRoute)
                }
            )
        }

        composable<PostDetailRoute> { backStackEntry ->
            val token = backStackEntry.toRoute<PostDetailRoute>().token
            PostDetailScreen(
                token = token,
                modifier = modifier,
            )
        }

    }
}