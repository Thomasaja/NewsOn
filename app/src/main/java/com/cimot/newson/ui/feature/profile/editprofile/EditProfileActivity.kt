package com.cimot.newson.ui.feature.profile.editprofile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import coil.load
import coil.transform.CircleCropTransformation
import com.cimot.newson.R
import com.cimot.newson.base.arch.BaseActivity
import com.cimot.newson.base.model.Resource
import com.cimot.newson.data.model.response.auth.User
import com.cimot.newson.databinding.ActivityEditProfileBinding
import com.cimot.newson.ui.feature.MainActivity
import com.cimot.newson.utils.StringUtils
import com.github.dhaval2404.imagepicker.ImagePicker
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class EditProfileActivity :
    BaseActivity<ActivityEditProfileBinding, EditProfileViewModel>(ActivityEditProfileBinding::inflate),
    EditProfileContract.View {
    private var selectedPicture: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        getData()
    }

    override fun initView() {
        setOnClickListeners()
    }

    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            when (resultCode) {
                Activity.RESULT_OK -> {
                    //Image Uri will not be null for RESULT_OK
                    val fileUri = data?.data!!
                    getViewBinding().ivEditProfilePict.load(fileUri) {
                        crossfade(true)
                        transformations(CircleCropTransformation())
                        placeholder(R.drawable.ic_profile_blank)
                    }
                    fileUri.path?.let {
                        val file = File(it)
                        if (file.exists()) {
                            selectedPicture = file
                        }
                    }
                }
                ImagePicker.RESULT_ERROR -> {
                    Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
                }
            }
        }

    override fun observeData() {
        super.observeData()
        getViewModel().getProfileLiveData().observe(this) {
            when (it) {
                is Resource.Loading -> {
                    showLoading(true)
                    showContent(false)
                }
                is Resource.Success -> {
                    showLoading(false)
                    showContent(true)
                    setDataToView(it.data!!)

                }
                is Resource.Error -> {
                    showLoading(false)
                    showContent(true)
                    showError(true, it.message)
                }
            }
        }
        getViewModel().getChangeProfileResultLiveData().observe(this) {
            when (it) {
                is Resource.Loading -> {
                    showLoading(true)
                    showContent(false)
                }
                is Resource.Success -> {
                    showLoading(false)
                    showContent(true)
                    Toast.makeText(
                        this,
                        getString(R.string.text_change_profile_success),
                        Toast.LENGTH_SHORT
                    ).show()
                    setDataToView(it.data!!)
                    navigateToProfileFragment()
                }
                is Resource.Error -> {
                    showLoading(false)
                    showContent(true)
                    showError(true, it.message)
                }
            }
        }
    }

    override fun showLoading(isVisible: Boolean) {
        super.showLoading(isVisible)
        getViewBinding().pbLoading.isVisible = isVisible
    }

    override fun showContent(isVisible: Boolean) {
        super.showContent(isVisible)
        getViewBinding().groupContent.isVisible = isVisible
    }

    override fun setOnClickListeners() {
        getViewBinding().btnSaveEditProfile.setOnClickListener {
            if (checkFormValidation()) {
                getViewModel().updateProfileData(
                    email = getViewBinding().etEditProfileEmail.text.toString().trim(),
                    username = getViewBinding().etEditProfileUsername.text.toString().trim(),
                    photoProfile = selectedPicture,
                )
            }
        }
        getViewBinding().cvButtonBack.setOnClickListener {
            onBackPressed()
        }
        getViewBinding().llProfilePicture.setOnClickListener {
            ImagePicker.with(this)
                .crop()
                .saveDir(
                    File(
                        externalCacheDir, "ImagePicker"
                    )
                )
                .compress(1024)
                .maxResultSize(1080, 1080)
                .createIntent { intent ->
                    startForProfileImageResult.launch(intent)
                }
        }
    }

    override fun checkFormValidation(): Boolean {
        val email = getViewBinding().etEditProfileEmail.text.toString()
        val username = getViewBinding().etEditProfileUsername.text.toString()
        var isFormValid = true

        when{
            email.isEmpty() -> {
                isFormValid = false
                getViewBinding().etEditProfileEmail.error = getString(R.string.error_email_empty)
            }
            !StringUtils.isEmailValid(email).not() -> {
                isFormValid =false
                getViewBinding().etEditProfileEmail.error = getString(R.string.error_email_invalid)
            }
        }
        if (username.isEmpty()){
            isFormValid  = false
            getViewBinding().etEditProfileUsername.error = getString(R.string.error_username_empty)
        }
        return isFormValid
    }

    override fun setDataToView(data: User) {
        if (!data.photo.isNullOrEmpty()){
            getViewBinding().ivEditProfilePict.load(data.photo){
                crossfade(true)
                transformations(CircleCropTransformation())
                placeholder(R.drawable.ic_profile_blank)
            }
        }
        else {
            getViewBinding().ivEditProfilePict.load(R.drawable.ic_profile_blank)
        }
        getViewBinding().etEditProfileEmail.setText(data.email)
        getViewBinding().etEditProfileUsername.setText(data.username)
    }

    override fun getData() {
        getViewModel().getProfileData()
    }

    override fun navigateToProfileFragment() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        finish()
    }
}