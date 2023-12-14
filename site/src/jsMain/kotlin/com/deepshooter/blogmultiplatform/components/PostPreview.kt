package com.deepshooter.blogmultiplatform.components

import androidx.compose.runtime.Composable
import com.deepshooter.blogmultiplatform.models.PostWithoutDetails
import com.deepshooter.blogmultiplatform.models.Theme
import com.deepshooter.blogmultiplatform.util.Constants.FONT_FAMILY
import com.deepshooter.blogmultiplatform.util.parseDateString
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.TextOverflow
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.objectFit
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.textOverflow
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px


@Composable
fun PostPreview(
    post: PostWithoutDetails,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(95.percent)
            .margin(bottom = 24.px)
            .cursor(Cursor.Pointer)
    ) {

        Image(
            modifier = Modifier
                .margin(bottom = 16.px)
                .fillMaxWidth()
                .objectFit(ObjectFit.Cover),
            src = post.thumbnail
        )

        SpanText(
            modifier = Modifier
                .fontFamily(FONT_FAMILY)
                .fontSize(12.px)
                .color(Theme.HalfBlack.rgb),
            text = post.date.parseDateString()
        )

        SpanText(
            modifier = Modifier
                .margin(bottom = 12.px)
                .fontFamily(FONT_FAMILY)
                .fontSize(20.px)
                .fontWeight(FontWeight.Bold)
                .color(Colors.Black)
                .textOverflow(TextOverflow.Ellipsis)
                .overflow(Overflow.Hidden)
                .styleModifier {
                    property("display", "-webkit-box")
                    property("-webkit-line-clamp", "2")
                    property("line-clamp", "2")
                    property("-webkit-box-orient", "vertical")
                },
            text = post.title
        )

        SpanText(
            modifier = Modifier
                .margin(bottom = 8.px)
                .fontFamily(FONT_FAMILY)
                .fontSize(16.px)
                .color(Colors.Black)
                .textOverflow(TextOverflow.Ellipsis)
                .overflow(Overflow.Hidden)
                .styleModifier {
                    property("display", "-webkit-box")
                    property("-webkit-line-clamp", "3")
                    property("line-clamp", "3")
                    property("-webkit-box-orient", "vertical")
                },
            text = post.subtitle
        )

        CategoryChip(post.category)

    }
}


@Composable
fun Posts(
    breakpoint: Breakpoint,
    posts: List<PostWithoutDetails>,
) {

    Column(
        modifier = Modifier.fillMaxWidth(if (breakpoint > Breakpoint.MD) 80.percent else 90.percent),
        verticalArrangement = Arrangement.Center
    ) {

        SimpleGrid(
            modifier = Modifier.fillMaxWidth(),
            numColumns = numColumns(base = 1, sm = 2, md = 3, lg = 4)
        ) {
            posts.forEach {
                PostPreview(post = it)
            }
        }

    }
}