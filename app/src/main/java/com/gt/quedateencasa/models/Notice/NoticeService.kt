package com.gt.quedateencasa.models.Notice

import com.gt.quedateencasa.models.Base.BaseService
import java.util.*

class NoticeService : BaseService {
    constructor() : super()

    public fun findNotices(): ArrayList<NoticeObject> {
        val items_: ArrayList<NoticeObject> = ArrayList()
        items_.add(NoticeObject(UUID.randomUUID(),null,null,URL = "http://quedateencasa.gt/"))
        return items_
    }
}