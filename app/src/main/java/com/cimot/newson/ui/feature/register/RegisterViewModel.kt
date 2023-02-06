package com.cimot.newson.ui.feature.register

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
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: RegisterRepository
) : BaseViewModelImpl(), RegisterContract.ViewModel {

    private val registerUserLiveData = MutableLiveData<Resource<User>>()
    override fun getRegisterResponseLiveData(): LiveData<Resource<User>> = registerUserLiveData

    override fun registerUser(registerRequest: AuthRequest) {
        registerUserLiveData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.postRegisterUser(registerRequest)
                viewModelScope.launch(Dispatchers.Main) {
                    registerUserLiveData.value = Resource.Success(response.data)
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    registerUserLiveData.value = Resource.Error(e.message.orEmpty())
                }
            }
        }
    }
}