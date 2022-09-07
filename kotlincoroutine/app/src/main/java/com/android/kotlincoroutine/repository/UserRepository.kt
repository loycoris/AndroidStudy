package com.android.kotlincoroutine.repository

import com.android.kotlincoroutine.api.User
import com.android.kotlincoroutine.api.UserServiceApi
import com.android.kotlincoroutine.api.userServiceApi

class UserRepository {
    suspend fun getUser(name: String): User {
        return userServiceApi.getUser(name)
    }
}