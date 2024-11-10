
package com.generic.kuri_android
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class SignInInput(val userName: String, val password: String) { }

class SignInViewModel: ViewModel() {

    var signInInputClosure: ((SignInInput) -> Unit)? = null

    var shouldShowError = mutableStateOf(false)
    var isLoading = mutableStateOf(false)

    fun submit(userName: String, password: String) {
        isLoading.value = !isLoading.value
        val input = SignInInput(userName, password)
        signInInputClosure?.invoke(input)
    }
}