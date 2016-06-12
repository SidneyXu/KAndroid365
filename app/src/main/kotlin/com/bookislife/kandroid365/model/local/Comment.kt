package com.bookislife.kandroid365.model.local

import io.realm.RealmModel
import io.realm.annotations.RealmClass
import io.realm.annotations.Required

/**
 * Created by SidneyXu on 2016/05/27.
 */
@RealmClass
open class Comment : RealmModel {
    @Required
    var text: String? = null
}