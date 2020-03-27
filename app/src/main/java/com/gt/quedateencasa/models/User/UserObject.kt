package com.gt.quedateencasa.models.User

import java.util.*

data class UserObject(
    val id:String,
    val firstname:String?,
    val surname:String?,
    val gender:Char,
    val bornDate: Date
)