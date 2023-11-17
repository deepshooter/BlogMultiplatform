package com.deepshooter.blogmultiplatform.models

import kotlinx.serialization.Serializable

@Serializable
actual enum class Category(color: String) {
    Technology(color = Theme.Green.hex),
    Programming(color = Theme.Yellow.hex),
    Design(color = Theme.Purple.hex)
}