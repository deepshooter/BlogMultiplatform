package com.deepshooter.blogmultiplatform.models

import com.deepshooter.blogmultiplatform.CategoryCommon
import kotlinx.serialization.Serializable

@Serializable
enum class Category(override val color: String): CategoryCommon {
    Technology(color = Theme.Green.hex),
    Programming(color = Theme.Yellow.hex),
    Design(color = Theme.Purple.hex)
}