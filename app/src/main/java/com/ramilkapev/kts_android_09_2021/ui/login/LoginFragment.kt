package com.ramilkapev.kts_android_09_2021.ui.login

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ramilkapev.kts_android_09_2021.databinding.FragmentLoginBinding
import timber.log.Timber
import androidx.core.widget.doOnTextChanged
import com.ramilkapev.kts_android_09_2021.R

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val loginViewModel: LoginViewModel by viewModels()
    private val viewLoginBinding by viewBinding(FragmentLoginBinding::bind)
    private var emailIsValid: Boolean = false
    private var passIsValid: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("onViewCreated ${hashCode()}")

        with(viewLoginBinding) {
            emailTv.doOnTextChanged { text, start, before, count ->

                if (!loginViewModel.validateEmail(emailTv.text.toString())) {
                    emailTvLayout.error = getString(R.string.incorrectEmail)
                    loginBtn.isEnabled = false
                } else {
                    emailIsValid = true
                    emailTvLayout.isErrorEnabled = false
                }
                if (emailIsValid && passIsValid) {
                    loginBtn.isEnabled = true
                }
                Timber.d("$emailIsValid")
            }

            passwordTv.doOnTextChanged { text, start, before, count ->
                if (!loginViewModel.validatePass(passwordTv.text.toString())) {
                    passwordTv.error = getString(R.string.passMinLength)
                    loginBtn.isEnabled = false
                    passIsValid = false
                } else {
                    passIsValid = true
                    emailTvLayout.isErrorEnabled = false
                }
                if (emailIsValid && passIsValid) {
                    loginBtn.isEnabled = true
                }
                Timber.d("$passIsValid")
            }

            loginBtn.setOnClickListener {
                login()
            }
        }
    }

    private fun login() {
        with(viewLoginBinding) {
            loginBtn.isEnabled = false
            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        loginViewModel.email = viewLoginBinding.emailTv.text.toString()
        loginViewModel.pass = viewLoginBinding.passwordTv.text.toString()
        Timber.d("onSaveInstanceState ${hashCode()}")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (loginViewModel.email.isNotEmpty() || loginViewModel.pass.isNotEmpty()) {
            viewLoginBinding.emailTv.setText(loginViewModel.email)
            viewLoginBinding.passwordTv.setText(loginViewModel.pass)
            Timber.d("restore: ${loginViewModel.email}")
        }
        Timber.d("onViewStateRestored ${hashCode()}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate ${hashCode()}")
    }

    override fun onResume() {
        super.onResume()
        Timber.d("onResume ${hashCode()}")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Timber.d("onActivityCreated ${hashCode()}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("onCreateView ${hashCode()}")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.d("onDestroyView ${hashCode()}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("onDestroy ${hashCode()}")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Timber.d("onAttach ${hashCode()}")
    }

    override fun onDetach() {
        super.onDetach()
        Timber.d("onDetach ${hashCode()}")
    }
}