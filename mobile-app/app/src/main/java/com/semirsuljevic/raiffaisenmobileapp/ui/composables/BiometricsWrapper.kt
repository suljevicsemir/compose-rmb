package com.semirsuljevic.raiffaisenmobileapp.ui.composables

import androidx.biometric.BiometricPrompt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import java.util.concurrent.Executor

@Composable
fun BiometricsWrapper(
    title: String,
    subtitle: String,
    negativeButtonText: String,
    onSuccess: () -> Unit,
    onError: () -> Unit,
    onFailed: () -> Unit,
) {
    val executor : Executor = ContextCompat.getMainExecutor(LocalContext.current)
    val prompt = BiometricPrompt(
        LocalContext.current as FragmentActivity,
        executor,
        object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                onSuccess()
                super.onAuthenticationSucceeded(result)
            }

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                onError()
                super.onAuthenticationError(errorCode, errString)
            }

            override fun onAuthenticationFailed() {
                onFailed()
                super.onAuthenticationFailed()
            }
        }
    )

    val promptInfo = BiometricPrompt.PromptInfo.Builder()
        .setTitle(title)
        .setSubtitle(subtitle)
        .setNegativeButtonText(negativeButtonText)
        .build()

    LaunchedEffect(Unit) {
        prompt.authenticate(promptInfo)
    }

}


