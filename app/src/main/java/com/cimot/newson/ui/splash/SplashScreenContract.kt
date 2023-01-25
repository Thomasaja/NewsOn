package com.cimot.newson.ui.splash

import androidx.lifecycle.LiveData
import com.cimot.newson.base.arch.BaseContract
import com.cimot.newson.base.model.Resource
import com.cimot.newson.data.model.response.auth.BaseAuthResponse
import com.cimot.newson.data.model.response.auth.User

interface SplashScreenContract {
    interface View : BaseContract.BaseView {
        fun checkLoginStatus()
        fun deleteSession()
        fun navigateToLogin()
        fun navigateToHome()
    }

    interface ViewModel : BaseContract.BaseViewModel {
        fun getSyncUserLiveData(): LiveData<Resource<User>>
        fun getSyncUser()
        fun isUserLoggedIn(): Boolean
        fun clearSession()
    }

    interface Repository : BaseContract.BaseRepository {
        suspend fun getSyncUser(): BaseAuthResponse<User, String>
        fun isUserLoggedIn(): Boolean
        fun clearSession()
    }
}