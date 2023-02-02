package com.cimot.newson.ui.feature.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import com.cimot.newson.R
import com.cimot.newson.base.arch.BaseActivity
import com.cimot.newson.base.model.Resource
import com.cimot.newson.data.model.request.AuthRequest
import com.cimot.newson.databinding.ActivityLoginBinding
import com.cimot.newson.ui.feature.MainActivity
import com.cimot.newson.ui.feature.register.RegisterActivity
import com.cimot.newson.utils.StringUtils
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginPageActivity :
    BaseActivity<ActivityLoginBinding, LoginPageViewModel>(ActivityLoginBinding::inflate),
    LoginPageContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
    }

    override fun initView() {
        observeData()
        setOnClick()
    }

    override fun setOnClick() {
        getViewBinding().btnLogin.setOnClickListener {
            if (checkFormValidation()) {
                getViewModel().loginUser(
                    AuthRequest(
                        email = getViewBinding().etEmail.text.toString().trim(),
                        password = getViewBinding().etPassword.text.toString().trim()
                    )
                )
            }
        }
    }

    override fun checkFormValidation(): Boolean {
        var isFormValid = true
        val email = getViewBinding().etEmail.text.toString()
        val password = getViewBinding().etPassword.text.toString()
        when {
            email.isEmpty() -> {
                isFormValid = false
                getViewBinding().tilEmail.isErrorEnabled = true
                getViewBinding().tilEmail.error = getString(R.string.error_email)
            }
            StringUtils.isEmailValid(email).not() -> {
                isFormValid = false
                getViewBinding().tilEmail.isErrorEnabled = true
                getViewBinding().tilEmail.error = getString(R.string.invalid_email)
            }
            else -> {
                getViewBinding().tilEmail.isErrorEnabled = false
            }
        }
        if (password.isEmpty()) {
            isFormValid = false
            getViewBinding().tilPass.isErrorEnabled = true
            getViewBinding().tilPass.error = getString(R.string.error_password)
        } else {
            getViewBinding().tilPass.isErrorEnabled = false
        }
        return isFormValid
    }

    override fun navigateToHome() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

    override fun navigateToRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    override fun observeData() {
        getViewModel().getLoginResultLiveData().observe(this) { response ->
            when (response) {
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    showLoading(false)
                    Toast.makeText(this, R.string.text_succes_login, Toast.LENGTH_SHORT).show()
                }
                is Resource.Error -> {
                    showLoading(false)
                    Toast.makeText(this, R.string.text_error_login, Toast.LENGTH_SHORT).show()
                }

            }

        }
    }


    override fun showLoading(isVisible: Boolean) {
        getViewBinding().pbLoading.isVisible = isVisible
    }
    override fun setToolBar() {
        supportActionBar?.hide()
    }
}