package com.cimot.newson.ui.feature.login

import androidx.lifecycle.LiveData
import com.cimot.newson.base.arch.BaseContract
import com.cimot.newson.base.model.Resource
import com.cimot.newson.data.model.request.AuthRequest
import com.cimot.newson.data.model.response.auth.BaseAuthResponse
import com.cimot.newson.data.model.response.auth.User

interface LoginPageContract {

    interface View : BaseContract.BaseView {
        fun setOnClick()
        fun checkFormValidation(): Boolean
        fun navigateToHome()
        fun navigateToRegister()
        fun setToolBar()
    }

    interface ViewModel : BaseContract.BaseViewModel {
        fun loginUser(loginRequest: AuthRequest)
        fun getLoginResultLiveData(): LiveData<Resource<User>>
    }

    interface Repository : BaseContract.BaseRepository {
        suspend fun postLoginUser(loginRequest: AuthRequest): BaseAuthResponse<User, String>
    }


}