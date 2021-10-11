package com.ramilkapev.kts_android_09_2021.ui.login

import android.app.Application
import android.content.Intent
import android.util.Patterns
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.lifecycle.*
import com.ramilkapev.kts_android_09_2021.AuthRepository
import com.ramilkapev.kts_android_09_2021.R
import com.ramilkapev.kts_android_09_2021.utils.SingleLiveEvent
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationService
import net.openid.appauth.TokenRequest

class LoginViewModel(application: Application, state: SavedStateHandle) :
    AndroidViewModel(application) {

    private val authRepository = AuthRepository()
    private val authService: AuthorizationService = AuthorizationService(getApplication())
    private val openAuthPageLiveEvent = SingleLiveEvent<Intent>()
    private val toastLiveEvent = SingleLiveEvent<Int>()
    private val loadingMutableLiveData = MutableLiveData(false)
    private val authSuccessLiveEvent = SingleLiveEvent<Unit>()
    private val errorLiveData = SingleLiveEvent<Unit>()

    val error: LiveData<Unit>
        get() = errorLiveData

    val openAuthPageLiveData: LiveData<Intent>
        get() = openAuthPageLiveEvent

    val loadingLiveData: LiveData<Boolean>
        get() = loadingMutableLiveData

    val toastLiveData: LiveData<Int>
        get() = toastLiveEvent

    val authSuccessLiveData: LiveData<Unit>
        get() = authSuccessLiveEvent

    fun onAuthCodeFailed(exception: AuthorizationException) {
        toastLiveEvent.postValue(R.string.token_error)
    }

    fun onAuthCodeReceived(tokenRequest: TokenRequest) {
        loadingMutableLiveData.postValue(true)
        authRepository.performTokenRequest(
            authService = authService,
            tokenRequest = tokenRequest,
            onComplete = {
                loadingMutableLiveData.postValue(false)
                authSuccessLiveEvent.postValue(Unit)
            },
            onError = {
                loadingMutableLiveData.postValue(false)
                toastLiveEvent.postValue(R.string.token_error)
            }
        )
    }

    fun openLoginPage() {
        val customTabsIntent = CustomTabsIntent.Builder()
            .setToolbarColor(ContextCompat.getColor(getApplication(), R.color.background_color))
            .build()

        val openAuthPageIntent = authService.getAuthorizationRequestIntent(
            authRepository.getAuthRequest(),
            customTabsIntent
        )

        openAuthPageLiveEvent.postValue(openAuthPageIntent)
    }

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

    override fun onCleared() {
        super.onCleared()
        authService.dispose()
    }

    private fun onLoadingError() {
        loadingMutableLiveData.postValue(false)
        errorLiveData.postValue(Unit)
    }

    companion object {
        private val EMAIL = "email"
        private val PASS = "pass"
    }
}
