package com.gt.quedateencasa.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.gt.quedateencasa.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    companion object {
        val FB_PERMISSIONS = arrayListOf("public_profile", "email", "user_birthday")
    }

    val callbackManager by lazy {
        CallbackManager.Factory.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupFBButton()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    //region facebook
    private fun setupFBButton() {
        login_fb_button.setPermissions(FB_PERMISSIONS)
        login_fb_button.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                onSuccessLoginFB(result)
            }

            override fun onCancel() {
                onCancelLoginFb()
            }

            override fun onError(error: FacebookException?) {
                onErrorLoginFb(error)
            }
        })
    }

    private fun onSuccessLoginFB(reuslt: LoginResult?) {

    }

    private fun onCancelLoginFb() {

    }

    private fun onErrorLoginFb(error: FacebookException?) {

    }

    private fun isLoggedInFB(): Boolean {
        val accessToken = AccessToken.getCurrentAccessToken()
        return null != accessToken && !accessToken.isExpired
    }
    //endregion
}

