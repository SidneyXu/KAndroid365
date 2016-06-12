package com.bookislife.kandroid365.model.local

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.Ignore
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import java.util.*

/**
 * Created by SidneyXu on 2016/05/27.
 */
open class Blog() : RealmObject() {

    @PrimaryKey
    var id: Long = 0
    @Required
    var title: String? = null
    var text: String? = null
    var score: Int = 0
    var createdAt: Date? = null
    var updatedAt: Date? = null
    var comments: RealmList<Comment> = RealmList()
    @Ignore
    var tag: String? = null

}
