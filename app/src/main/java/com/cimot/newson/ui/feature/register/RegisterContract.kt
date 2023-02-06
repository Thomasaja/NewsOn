package com.cimot.newson.ui.feature.register

import androidx.lifecycle.LiveData
import com.cimot.newson.base.arch.BaseContract
import com.cimot.newson.base.model.Resource
import com.cimot.newson.data.model.request.AuthRequest
import com.cimot.newson.data.model.response.auth.BaseAuthResponse
import com.cimot.newson.data.model.response.auth.User

interface RegisterContract {
    interface View : BaseContract.BaseView {
        fun setOnClick()
        fun setLoadingState(isLoadingVisible: Boolean)
        fun checkFormValidation(): Boolean
        fun navigateToLogin()
    }

    interface ViewModel : BaseContract.BaseViewModel {
        fun getRegisterResponseLiveData(): LiveData<Resource<User>>
        fun registerUser(registerRequest: AuthRequest)
    }

    interface Repository : BaseContract.BaseRepository {
        suspend fun postRegisterUser(registerRequest: AuthRequest): BaseAuthResponse<User, String>
    }
}