package com.gt.quedateencasa.models.Notice

import com.gt.quedateencasa.models.Base.BaseModel
import java.net.URL
import java.util.*

class NoticeObject : BaseModel{
    var Name: String? = null
        get() = field
        set(name) {field = name}
    var Content: String?=null
        get() = field
        set(name) {field = name}
    var URL: String?=null
        get() = field
        set(name) {field = name}

    constructor():super()
    constructor(UUID: UUID?, Name: String?, Content: String?, URL: String?):super(UUID) {
        this.Name = Name
        this.Content = Content
        this.URL = URL
    }
}