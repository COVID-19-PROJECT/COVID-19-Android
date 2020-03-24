package com.gt.quedateencasa.views.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.gt.quedateencasa.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    companion object {
        const val RC_SIGN_IN = 1
        val FB_PERMISSIONS = arrayListOf("public_profile", "email", "user_birthday")
    }

    private val callbackManager by lazy {
        CallbackManager.Factory.create()
    }

    private val gso by lazy {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
    }

    private val mGoogleSignInClient by lazy {
        GoogleSignIn.getClient(this, gso)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupGoogleButton()
        setupFBButton()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
        if (RC_SIGN_IN == requestCode) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.finish()
            System.exit(0)
        }
        doubleBackToExitPressedOnce = true
        Toast.makeText(
            this,
            resources.getText(R.string.message_doble_press_back),
            Toast.LENGTH_SHORT
        ).show()
        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }

    //region google
    private fun setupGoogleButton() {
        sign_in_google_button.setSize(SignInButton.SIZE_STANDARD)
        sign_in_google_button.setOnClickListener {
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
    }

    private fun handleSignInResult(task: Task<GoogleSignInAccount>) {
        try {
            val account = task.getResult(ApiException::class.java)
            account?.let {
                onSuccessLoginGoogle(it)
            }
        } catch (e: ApiException) {
            onErrorLoginGoogle(e)
        }
    }

    private fun onSuccessLoginGoogle(account: GoogleSignInAccount) {
        Toast.makeText(this, "Bienvenido: " + account.email, Toast.LENGTH_SHORT).show()
    }

    private fun onErrorLoginGoogle(e: ApiException) {
        e.printStackTrace()
    }

    private fun isLoggedInGoogle(): Boolean{
        return null != GoogleSignIn.getLastSignedInAccount(this)
    }
    //endregion

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

    private fun onSuccessLoginFB(result: LoginResult?) {

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

