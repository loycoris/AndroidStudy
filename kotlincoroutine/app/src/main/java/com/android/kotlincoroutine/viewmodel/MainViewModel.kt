package com.android.kotlincoroutine.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.kotlincoroutine.api.User
import com.android.kotlincoroutine.repository.UserRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val userLiveData = MutableLiveData<User>()

    private val userRepository = UserRepository()

    fun getUser(name: String) {
        viewModelScope.launch {
            userLiveData.value = userRepository.getUser(name)
        }
    }
}