package com.ramilkapev.kts_android_09_2021.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class LoginViewModel(state: SavedStateHandle): ViewModel() {

    fun validateEmail(email: String): Boolean {
        return when {
            email.isEmpty() -> {
                false
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                false
            }
            else -> true
        }
    }

    fun validatePass(password: String): Boolean {
        return when {
            password.length < 8 -> {
                false
            }
            else -> true
        }
    }

    private val savedStateHandle = state

    var email: String
        get() = savedStateHandle.get<String>(EMAIL) ?: ""
        set(value) {
            savedStateHandle.set(EMAIL, value)
        }

    var pass: String
        get() = savedStateHandle.get<String>(PASS) ?: ""
        set(value) {
            savedStateHandle.set(PASS, value)
        }

    companion object {
        private val EMAIL = "email"
        private val PASS = "pass"
    }
}