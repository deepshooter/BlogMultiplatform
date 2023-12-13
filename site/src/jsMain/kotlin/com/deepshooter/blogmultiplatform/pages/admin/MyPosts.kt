package com.deepshooter.blogmultiplatform.pages.admin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.deepshooter.blogmultiplatform.components.AdminPageLayout
import com.deepshooter.blogmultiplatform.components.Posts
import com.deepshooter.blogmultiplatform.components.SearchBar
import com.deepshooter.blogmultiplatform.models.ApiListResponse
import com.deepshooter.blogmultiplatform.models.PostWithoutDetails
import com.deepshooter.blogmultiplatform.models.Theme
import com.deepshooter.blogmultiplatform.util.Constants.FONT_FAMILY
import com.deepshooter.blogmultiplatform.util.Constants.SIDE_PANEL_WIDTH
import com.deepshooter.blogmultiplatform.util.fetchMyPosts
import com.deepshooter.blogmultiplatform.util.isUserLoggedIn
import com.deepshooter.blogmultiplatform.util.noBorder
import com.varabyte.kobweb.compose.css.CSSTransition
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TransitionProperty
import com.varabyte.kobweb.compose.css.Visibility
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.compose.ui.modifiers.visibility
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.forms.Switch
import com.varabyte.kobweb.silk.components.forms.SwitchSize
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Button

@Page
@Composable
fun MyPostsPage() {
    isUserLoggedIn {
        MyPostsScreen()
    }
}


@Composable
fun MyPostsScreen() {

    val breakpoint = rememberBreakpoint()

    var selectableMode by remember { mutableStateOf(false) }
    var switchText by remember { mutableStateOf("Select") }
    val myPosts = remember { mutableStateListOf<PostWithoutDetails>() }

    LaunchedEffect(Unit) {
        fetchMyPosts(
            skip = 0,
            onSuccess = {
                if (it is ApiListResponse.Success) {
                    myPosts.addAll(it.data)
                }
            },
            onError = {
                println(it)
            }
        )
    }


    AdminPageLayout {

        Column(
            modifier = Modifier
                .margin(topBottom = 50.px)
                .fillMaxSize()
                .padding(left = if (breakpoint > Breakpoint.MD) SIDE_PANEL_WIDTH.px else 0.px),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth(
                        if (breakpoint > Breakpoint.MD) 30.percent
                        else 50.percent
                    )
                    .margin(bottom = 24.px),
                contentAlignment = Alignment.Center
            ) {

                SearchBar(
                    breakpoint = breakpoint,
                    modifier = Modifier
                        .transition(
                            CSSTransition(
                                property = TransitionProperty.All,
                                duration = 200.ms
                            )
                        ),
                    onEnterClick = {
                    },
                    onSearchIconClick = {}
                )

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(
                        if (breakpoint > Breakpoint.MD) 80.percent
                        else 90.percent
                    )
                    .margin(bottom = 24.px),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Switch(
                        modifier = Modifier.margin(right = 8.px),
                        size = SwitchSize.LG,
                        checked = selectableMode,
                        onCheckedChange = {
                            selectableMode = it
                        }
                    )
                    SpanText(
                        modifier = Modifier.color(if (selectableMode) Colors.Black else Theme.HalfBlack.rgb),
                        text = switchText
                    )
                }
                Button(
                    attrs = Modifier
                        .margin(right = 20.px)
                        .height(54.px)
                        .padding(leftRight = 24.px)
                        .backgroundColor(Theme.Red.rgb)
                        .color(Colors.White)
                        .noBorder()
                        .borderRadius(r = 4.px)
                        .fontFamily(FONT_FAMILY)
                        .fontSize(14.px)
                        .fontWeight(FontWeight.Medium)
                        .onClick {}
                        .toAttrs()
                ) {
                    SpanText(text = "Delete")
                }
            }

            Posts(
                breakpoint = breakpoint,
                posts = myPosts
            )


        }

    }

}