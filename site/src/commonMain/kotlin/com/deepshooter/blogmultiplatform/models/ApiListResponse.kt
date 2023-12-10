package com.deepshooter.blogmultiplatform.models

expect sealed class ApiListResponse {
    object Idle
    class Success
    class Error
}