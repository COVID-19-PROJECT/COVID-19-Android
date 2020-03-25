package com.gt.quedateencasa.models.Base

import java.io.Serializable
import java.util.*

open class BaseModel : Serializable{
    private var UUID: UUID? = null
    private var created_at : Date? =null
    private var updated_at : Date? =null
    private var deleted_at : Date? =null

    constructor()
    constructor(UUID: UUID?) {
    }

}