package com.gt.quedateencasa.views.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobile.client.Callback
import com.amazonaws.mobile.client.UserStateDetails
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.gt.quedateencasa.R
import kotlinx.android.synthetic.main.activity_welcome.*
import java.lang.Exception

class WelcomeActivity : AppCompatActivity() {

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

    /*private val mGoogleSignInClient by lazy {
        GoogleSignIn.getClient(this, gso)
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        setupGoogleButton()
        setupFBButton()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
        if (RC_SIGN_IN == requestCode) {
            /*val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)*/
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
        /*sign_in_google_button.setSize(SignInButton.SIZE_STANDARD)
        sign_in_google_button.setOnClickListener {
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }*/
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

    private fun isLoggedInGoogle(): Boolean {
        /*return null != GoogleSignIn.getLastSignedInAccount(this)*/
        return false
    }
    //endregion

    //region facebook
    private fun setupFBButton() {
        /*sign_in_facebook_button.setPermissions(FB_PERMISSIONS)
        sign_in_facebook_button.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                onSuccessLoginFB(result)
            }

            override fun onCancel() {
                onCancelLoginFb()
            }

            override fun onError(error: FacebookException?) {
                onErrorLoginFb(error)
            }
        })*/
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

    /*Button actions*/
    fun onClickFacebookButton(v: View) {
        AWSMobileClient.getInstance().initialize(this, object : Callback<UserStateDetails> {
            override fun onResult(result: UserStateDetails?) {
                AWSMobileClient.getInstance()
                    .showSignIn(this@WelcomeActivity, object : Callback<UserStateDetails> {
                        override fun onResult(result: UserStateDetails?) {
                            Toast.makeText(
                                this@WelcomeActivity,
                                "result -> ${result?.userState}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        override fun onError(e: Exception?) {
                            e?.printStackTrace()
                        }
                    })
            }

            override fun onError(e: Exception?) {
                e?.printStackTrace()
            }
        })
    }

    fun onClickTwitterButton(v: View) {
        sign_in_twitter_button?.performClick()
    }

    fun onClickGoogleButton(v: View) {
        sign_in_google_button?.performClick()
    }

    fun onClickTermsAndContidions(v: View) {
        Toast.makeText(this, "Show terms and contidions", Toast.LENGTH_SHORT).show()
    }

    fun onClickCreateAccount(v: View) {

    }
}
