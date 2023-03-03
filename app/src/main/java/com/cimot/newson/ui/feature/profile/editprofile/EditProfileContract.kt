package com.cimot.newson.ui.feature.profile.editprofile

import androidx.lifecycle.MutableLiveData
import com.cimot.newson.base.arch.BaseContract
import com.cimot.newson.base.model.Resource
import com.cimot.newson.data.model.response.auth.BaseAuthResponse
import com.cimot.newson.data.model.response.auth.User
import java.io.File

interface EditProfileContract {

    interface View : BaseContract.BaseView {
        fun setOnClickListeners()
        fun checkFormValidation(): Boolean
        fun setDataToView(data: User)
        fun getData()
        fun navigateToProfileFragment()
    }

    interface ViewModel : BaseContract.BaseViewModel {
        fun getChangeProfileResultLiveData(): MutableLiveData<Resource<User>>
        fun getProfileLiveData(): MutableLiveData<Resource<User>>
        fun getProfileData()
        fun updateProfileData(email: String, username: String, photoProfile: File? = null)
    }

    interface Repository : BaseContract.BaseRepository {
        suspend fun saveCacheProfileData(response: BaseAuthResponse<User, String>)
        suspend fun getProfileData(): BaseAuthResponse<User, String>
        suspend fun updateProfileData(
            email: String,
            username: String,
            photoProfile: File? = null
        ): BaseAuthResponse<User, String>
    }
}