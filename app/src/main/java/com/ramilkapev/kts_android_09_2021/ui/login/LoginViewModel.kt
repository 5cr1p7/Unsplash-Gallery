package com.ramilkapev.kts_android_09_2021.ui.login

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class LoginViewModel(state: SavedStateHandle): ViewModel() {

    companion object {
        private val EMAIL = "email"
        private val PASS = "pass"
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
}