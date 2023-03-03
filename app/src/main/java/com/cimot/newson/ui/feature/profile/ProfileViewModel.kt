package com.cimot.newson.ui.feature.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cimot.newson.base.arch.BaseViewModelImpl
import com.cimot.newson.base.model.Resource
import com.cimot.newson.data.model.response.auth.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel
@Inject constructor(private val repository: ProfileRepository) : BaseViewModelImpl(),
    ProfileContract.ViewModel {

    private val getProfileResponseLiveData = MutableLiveData<Resource<User?>>()

    override fun getProfileData() {
        getProfileResponseLiveData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getProfileData()
                viewModelScope.launch(Dispatchers.Main) {
                    getProfileResponseLiveData.value = Resource.Success(response)
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    getProfileResponseLiveData.value = Resource.Error(e.message.orEmpty())
                }
            }
        }
    }

    override fun getProfileLiveData(): MutableLiveData<Resource<User?>> = getProfileResponseLiveData

    override fun logout() {
        repository.clearSession()
    }
}