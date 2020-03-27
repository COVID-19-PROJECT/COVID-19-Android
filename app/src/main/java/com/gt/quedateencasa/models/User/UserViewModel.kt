package com.gt.quedateencasa.models.User

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {

    private val userService by lazy {
        UserService()
    }

    fun getProfileData(context: Context?, id: String): LiveData<UserObject> {
        val mutableLiveData = MutableLiveData<UserObject>()
        context?.let {
            mutableLiveData.postValue(userService.find(id))
        }
        return mutableLiveData
    }

    fun saveUserData(context: Context?, id: String, data:UserObject):LiveData<UserObject>{
        val mutableLiveData = MutableLiveData<UserObject>()
        context?.let {
            mutableLiveData.postValue(userService.update(id, data))
        }
        return mutableLiveData
    }
}