package com.generic.kuri_android
import com.generic.authentication.KuriComposer

class SignInComposer {

    val signInUseCase = KuriComposer().signInUseCase()
    var viewModel = SignInViewModel()

    init {
        configure()
    }

    private fun configure() {
        signInUseCase.shouldShowLoading = {
            viewModel.isLoading.value = it
        }

        signInUseCase.didFinishLogin = {
            println("Sign In Finished")
        }

        signInUseCase.didFinishWithError = {
            viewModel.shouldShowError.value = true
        }

        viewModel.signInInputClosure = { input ->
            signInUseCase.signIn(input.userName, input.password)
        }
    }
}