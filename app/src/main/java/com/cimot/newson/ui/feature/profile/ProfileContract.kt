package com.cimot.newson.ui.feature.profile

import androidx.lifecycle.MutableLiveData
import com.cimot.newson.base.arch.BaseContract
import com.cimot.newson.base.model.Resource
import com.cimot.newson.data.model.response.auth.User

interface ProfileContract {

    interface View : BaseContract.BaseView {
        fun getData()
        fun setOnClickListener()
        fun setProfileData(data: User)
        fun logout()
        fun navigateToLoginActivity()
        fun showLogoutConfirmation()
    }

    interface ViewModel : BaseContract.BaseViewModel {
        fun getProfileData()
        fun getProfileLiveData(): MutableLiveData<Resource<User?>>
        fun logout()
    }

    interface Repository : BaseContract.BaseRepository {
        suspend fun getProfileData(): User?
        fun clearSession()
    }
}