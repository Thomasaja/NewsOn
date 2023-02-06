package com.cimot.newson.ui.feature.register

import android.content.Intent
import android.view.View
import android.widget.Toast
import com.cimot.newson.R
import com.cimot.newson.base.arch.BaseActivity
import com.cimot.newson.base.model.Resource
import com.cimot.newson.data.model.request.AuthRequest
import com.cimot.newson.databinding.ActivityRegisterBinding
import com.cimot.newson.ui.feature.login.LoginPageActivity
import com.cimot.newson.utils.StringUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity :BaseActivity<ActivityRegisterBinding, RegisterViewModel>(
    ActivityRegisterBinding::inflate
) , RegisterContract.View{
    override fun initView() {
        observeData()
        setOnClick()
    }

    override fun setOnClick() {
        getViewBinding().btnRegister.setOnClickListener {
            if (checkFormValidation()) {
                getViewModel().registerUser(
                    AuthRequest(
                        email = getViewBinding().etEmail.text.toString(),
                        username = getViewBinding().etUsername.text.toString(),
                        password = getViewBinding().etPassword.text.toString(),
                    )
                )
            }
        }
        getViewBinding().tvNavigateToLogin.setOnClickListener{
            navigateToLogin()
        }
    }

    override fun setLoadingState(isLoadingVisible: Boolean) {
        getViewBinding().pbLoading.visibility = if (isLoadingVisible) View.VISIBLE else View.GONE
    }

    override fun checkFormValidation(): Boolean {
        val email = getViewBinding().etEmail.text.toString()
        val username = getViewBinding().etUsername.text.toString()
        val password = getViewBinding().etPassword.text.toString()
        var isFormValid = true

        //for checking is email empty
        when {
            email.isEmpty() -> {
                isFormValid = false
                getViewBinding().tilEmail.isErrorEnabled = true
                getViewBinding().tilEmail.error = getString(R.string.error_email_empty)
            }
            StringUtils.isEmailValid(email).not() -> {
                isFormValid = false
                getViewBinding().tilEmail.isErrorEnabled = true
                getViewBinding().tilEmail.error = getString(R.string.error_email_invalid)
            }
            else -> {
                getViewBinding().tilEmail.isErrorEnabled = false
            }
        }
        //for checking is Password empty
        if (password.isEmpty()) {
            isFormValid = false
            getViewBinding().tilPass.isErrorEnabled = true
            getViewBinding().tilPass.error = getString(R.string.error_password_empty)
        } else {
            getViewBinding().tilPass.isErrorEnabled = false
        }
        //for checking is Password empty
        if (username.isEmpty()) {
            isFormValid = false
            getViewBinding().tilUname.isErrorEnabled = true
            getViewBinding().tilUname.error = getString(R.string.error_username_empty)
        } else {
            getViewBinding().tilUname.isErrorEnabled = false
        }
        return isFormValid
    }

    override fun navigateToLogin() {
        val intent = Intent(this, LoginPageActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
    override fun observeData() {
        getViewModel().getRegisterResponseLiveData().observe(this) { response ->
            when (response) {
                is Resource.Loading -> {
                    setLoadingState(true)
                }
                is Resource.Success -> {
                    setLoadingState(false)
                    Toast.makeText(this, R.string.text_register_success, Toast.LENGTH_SHORT).show()
                    navigateToLogin()
                }
                is Resource.Error -> {
                    setLoadingState(false)
                    Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}