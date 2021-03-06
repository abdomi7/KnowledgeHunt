package com.abdelHalimMahmoud.knowledgehunt.presentation.components

import android.net.Uri
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.abdelHalimMahmoud.knowledgehunt.R
import com.abdelHalimMahmoud.knowledgehunt.domain.models.Screens
import com.abdelHalimMahmoud.knowledgehunt.presentation.utils.calculateIncrement1
import com.abdelHalimMahmoud.knowledgehunt.presentation.utils.calculateIncrement10
import com.abdelHalimMahmoud.knowledgehunt.presentation.utils.calculateIncrement5
import com.google.firebase.firestore.DocumentSnapshot
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage
import compose.icons.FontAwesomeIcons
import compose.icons.TablerIcons
import compose.icons.fontawesomeicons.Brands
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.brands.StackOverflow
import compose.icons.fontawesomeicons.solid.FileCode
import compose.icons.fontawesomeicons.solid.HouseUser
import compose.icons.tablericons.Binary
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

private val screens = listOf(
    Screens.Home,
    Screens.Articles,
    Screens.Questions,
    Screens.MCQ,
//    Screens.About
)
private val icon = listOf(
    FontAwesomeIcons.Solid.HouseUser,
    FontAwesomeIcons.Solid.FileCode,
    FontAwesomeIcons.Brands.StackOverflow,
    TablerIcons.Binary,
//    FontAwesomeIcons.Solid.Lightbulb,
)

@Composable
fun AppDrawer(
    currentRoute: String,
    modifier: Modifier = Modifier,
    navController: NavController,
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    userData: State<DocumentSnapshot?>,
    profileImageUrl: State<Uri>,
    email: State<String?>,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(ScrollState(0))
    ) {
        Row(Modifier.padding(12.dp)) {

            GlideImage(
                // CoilImage, FrescoImage
                imageModel = profileImageUrl.value,
                modifier = Modifier
                    .size(55.dp)
                    .padding(2.dp)
                    .clip(CircleShape),
                // shows a shimmering effect when loading an image.
                shimmerParams = ShimmerParams(
                    baseColor = MaterialTheme.colors.background,
                    highlightColor = MaterialTheme.colors.secondary,
                    durationMillis = 350,
                    dropOff = 0.65f,
                    tilt = 20f
                ),
                circularReveal = CircularReveal(800),
            )
            Column(Modifier.padding(4.dp)) {
                Text(
                    text = userData.value?.get("f_name")
                        .toString() + " " + userData.value?.get("l_name")
                        .toString(),
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 14.sp,
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier.padding(start = 8.dp, bottom = 3.dp)
                )
                Text(
                    text = email.value!!,
                    fontFamily = FontFamily.Default,
                    fontSize = 12.sp,
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier.padding(start = 8.dp, top = 3.dp)
                )
            }
        }
//        Divider(color = MaterialTheme.colors.onSurface.copy(alpha = .2f))

        Row(Modifier.padding(horizontal = 12.dp)) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp, vertical = 4.dp)
                    .background(
                        Color(calculateIncrement5(userData.value?.get("level") as Long?)).copy(alpha = 0.35f),
                        RoundedCornerShape(4.dp)
                    )
                    .border(0.5.dp, Color.LightGray, RoundedCornerShape(4.dp))
            ) {
                Text(
                    text = userData.value?.get("level")
                        .toString() + "\nLevel",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 14.sp,
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp)

                )
            }
            Spacer(Modifier.height(4.dp))

            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp, vertical = 4.dp)
                    .background(
                        Color(calculateIncrement10(userData.value?.get("score") as Long?)).copy(
                            alpha = 0.35f
                        ),
                        RoundedCornerShape(4.dp)
                    )
                    .border(0.5.dp, Color.LightGray, RoundedCornerShape(4.dp))
            ) {
                Text(
                    text = userData.value?.get("score")
                        .toString() + "\nScore",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 14.sp,
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp)
                )
            }
            Spacer(Modifier.height(4.dp))

            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp, vertical = 4.dp)
                    .background(
                        Color(calculateIncrement1(userData.value?.get("num_mcq") as Long?)).copy(
                            alpha = 0.35f
                        ),
                        RoundedCornerShape(4.dp)
                    )
                    .border(0.5.dp, Color.LightGray, RoundedCornerShape(4.dp))
            ) {
                Text(
                    text = userData.value?.get("num_mcq")
                        .toString() + "\nMCQ Tests",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 14.sp,
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp)
                )
            }
        }
        Row(Modifier.padding(horizontal = 12.dp)) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp, vertical = 4.dp)
                    .background(
                        Color(calculateIncrement1(userData.value?.get("num_articles") as Long?)).copy(
                            alpha = 0.35f
                        ),
                        RoundedCornerShape(4.dp)
                    )
                    .border(0.5.dp, Color.LightGray, RoundedCornerShape(4.dp))
            ) {
                Text(
                    text = userData.value?.get("num_articles")
                        .toString() + "\nArticles",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 14.sp,
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp)
                )
            }
            Spacer(Modifier.height(4.dp))

            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp, vertical = 4.dp)
                    .background(
                        Color(calculateIncrement1(userData.value?.get("num_contests") as Long?)).copy(
                            alpha = 0.35f
                        ),
                        RoundedCornerShape(4.dp)
                    )
                    .border(0.5.dp, Color.LightGray, RoundedCornerShape(4.dp))
            ) {
                Text(
                    text = userData.value?.get("num_contests")
                        .toString() + "\nContests",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 14.sp,
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp)
                )
            }
            Spacer(Modifier.height(4.dp))

            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp, vertical = 4.dp)
                    .background(
                        Color(calculateIncrement10(userData.value?.get("num_answers") as Long?)).copy(
                            alpha = 0.35f
                        ),
                        RoundedCornerShape(4.dp)
                    )
                    .border(0.5.dp, Color.LightGray, RoundedCornerShape(4.dp))
            ) {
                Text(
                    text = userData.value?.get("num_answers")
                        .toString() + "\nAnswers",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 14.sp,
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp)
                )
            }
        }
        Row(Modifier.padding(horizontal = 12.dp)) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp, vertical = 4.dp)
                    .background(
                        Color(calculateIncrement5(userData.value?.get("num_ask") as Long?)).copy(
                            alpha = 0.35f
                        ),
                        RoundedCornerShape(4.dp)
                    )
                    .border(0.5.dp, Color.LightGray, RoundedCornerShape(4.dp))
            ) {
                Text(
                    text = userData.value?.get("num_ask")
                        .toString() + "\nQuestions",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 14.sp,
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp)
                )
            }
            Spacer(Modifier.height(4.dp))

            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp, vertical = 4.dp)
                    .background(
                        Color(calculateIncrement10(userData.value?.get("num_upvote") as Long?)).copy(
                            alpha = 0.35f
                        ),
                        RoundedCornerShape(4.dp)
                    )
                    .border(0.5.dp, Color.LightGray, RoundedCornerShape(4.dp))
            ) {
                Text(
                    text = userData.value?.get("num_upvote")
                        .toString() + "\nUp Votes",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 14.sp,
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp)
                )
            }
            Spacer(Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp, vertical = 4.dp)
                    .background(
                        Color(calculateIncrement10(userData.value?.get("num_downvote") as Long?)).copy(
                            alpha = 0.35f
                        ),
                        RoundedCornerShape(4.dp)
                    )
                    .border(0.5.dp, Color.LightGray, RoundedCornerShape(4.dp))
            ) {
                Text(
                    text = userData.value?.get("num_downvote")
                        .toString() + "\nDown Votes",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 14.sp,
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp)
                )
            }
        }

        Spacer(Modifier.height(12.dp))
        Divider(color = MaterialTheme.colors.onSurface.copy(alpha = .2f))
        screens.forEachIndexed { index, screen ->

            DrawerButton(
                icon = icon[index],
                label = screen.title,
                isSelected = currentRoute == screen.route,
                action = {
                    navController.navigate(screen.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            inclusive = true
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
//                     Close drawer
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                })
        }
    }
}


@Composable
fun HuntLogo(modifier: Modifier) {
    Image(
        modifier = modifier,
        painter = painterResource(id = R.drawable.logo_no_text),
        contentDescription = null, // decorative element
    )
}

@Composable
fun HuntIcon(modifier: Modifier) {
    Image(
        modifier = modifier,
        painter = painterResource(id = R.drawable.logo_with_title),
        contentDescription = null, // decorative element
    )
}

@Composable
private fun DrawerButton(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    action: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colors
    val textIconColor = if (isSelected) {
        colors.primary
    } else {
        colors.onSurface
    }
    val backgroundColor = if (isSelected) {
        colors.primary.copy(alpha = 0.12f)
    } else {
        Color.Transparent
    }

    val surfaceModifier = modifier
        .padding(start = 8.dp, top = 8.dp, end = 8.dp)
        .fillMaxWidth()
    Surface(
        modifier = surfaceModifier,
        color = backgroundColor,
        shape = MaterialTheme.shapes.small
    ) {
        TextButton(
            onClick = action,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                NavigationIcon(
                    icon = icon,
                    isSelected = isSelected,
                    contentDescription = null, // decorative
                    tintColor = textIconColor
                )
                Spacer(Modifier.width(16.dp))
                Text(
                    text = label,
                    style = MaterialTheme.typography.body2,
                    color = textIconColor
                )
            }
        }
    }
}

@Composable
fun NavigationIcon(
    icon: ImageVector,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    tintColor: Color? = null,
) {
    val imageAlpha = if (isSelected) {
        1f
    } else {
        0.6f
    }

    val iconTintColor = tintColor ?: if (isSelected) {
        MaterialTheme.colors.primary
    } else {
        MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
    }

    Image(
        modifier = modifier.size(25.dp),
        imageVector = icon,
        contentDescription = contentDescription,
        contentScale = ContentScale.Inside,
        colorFilter = ColorFilter.tint(iconTintColor),
        alpha = imageAlpha
    )
}
