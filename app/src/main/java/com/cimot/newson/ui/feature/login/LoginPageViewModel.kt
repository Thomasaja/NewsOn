package com.cimot.newson.ui.feature.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cimot.newson.base.arch.BaseViewModelImpl
import com.cimot.newson.base.model.Resource
import com.cimot.newson.data.model.request.AuthRequest
import com.cimot.newson.data.model.response.auth.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginPageViewModel @Inject constructor(
    private val repository: LoginPageRepository
) : BaseViewModelImpl(), LoginPageContract.ViewModel {

    private val loginResultLiveData = MutableLiveData<Resource<User>>()
    override fun loginUser(loginRequest: AuthRequest) {
        loginResultLiveData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.postLoginUser(loginRequest)
                viewModelScope.launch(Dispatchers.Main) {
                    loginResultLiveData.value = Resource.Success(response.data)
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.IO) {
                    loginResultLiveData.value = Resource.Error(e.message.orEmpty())
                }
            }
        }
    }

    override fun getLoginResultLiveData(): LiveData<Resource<User>> = loginResultLiveData

}