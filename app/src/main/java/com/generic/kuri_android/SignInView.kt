package com.generic.kuri_android
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.generic.kuri_android.ui.theme.Kuri_AndroidTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SignInView(modifier: Modifier = Modifier, viewModel: SignInViewModel) {

    val isLoading = viewModel.isLoading.value

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { SignInAppBar() }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            SignInStack(viewModel, isLoading)
            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize()) {
                    SignInLoadingView(Modifier.align(Alignment.Center))
                }
            }
        }
    }
}

@Composable
private fun SignInLoadingView(modifier: Modifier) {
    CircularProgressIndicator(
        modifier = modifier,
        color = Color.Blue,
        strokeWidth = 4.dp
    )
}

@Composable
private fun SignInStack(viewModel: SignInViewModel, isLoading: Boolean) {

    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val shouldEnableButton: Boolean = if (isLoading) false else (userName.isNotBlank() && password.isNotBlank())

    Column(
        modifier = Modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = userName,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            visualTransformation = PasswordVisualTransformation(),
            label = {
                Text("Username")
            },
            onValueChange = {
                userName = it
            })

        TextField(
            value = password,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            label = {
                Text("Password")
            },
            onValueChange = {
                password = it
            })

        Button(onClick = {
            if (shouldEnableButton) {
                viewModel.submit(userName, password)
            }

        }, enabled = shouldEnableButton) {
            Text("Sign In")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SignInAppBar() {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.onSecondaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onSecondary
        ),
        title = {
            Text("Sign In")
        })
}

@Preview(showBackground = true)
@Composable
fun SignInPreview() {
    Kuri_AndroidTheme {
        SignInView(viewModel = SignInViewModel())
    }
}