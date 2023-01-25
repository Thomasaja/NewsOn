package com.cimot.newson.ui.intro

import SessionPreference
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.cimot.newson.R
import com.cimot.newson.ui.feature.login.LoginPageActivity
import com.github.appintro.AppIntro2
import com.github.appintro.AppIntroCustomLayoutFragment
import com.google.gson.Gson


class IntroScreenActivity : AppIntro2() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        isSkipButtonEnabled = false

        setIndicatorColor(
            selectedIndicatorColor = ContextCompat.getColor(this, R.color.primary),
            unselectedIndicatorColor = ContextCompat.getColor(this, R.color.unselected_indicator)
        )

        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.layout_intro))
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        val intent = Intent(this, LoginPageActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        isFirstAppOpen()
    }

    private fun isFirstAppOpen() {
        SessionPreference(this, Gson()).isAppOpenedFirstTime = false
    }

}