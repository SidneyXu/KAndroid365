package com.bookislife.koin.extension

import android.os.Bundle
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

/**
 * Created by SidneyXu on 2016/01/14.
 */
fun JSONObject.with(vararg pairs: Pair<String, Any>?): JSONObject {
    val json = JSONObject()
    pairs.map { pair ->
        if (pair == null)
            return json
        json.putOpt(pair.first, pair.second)
    }
    return json
}

inline fun JSONObject.forEach(handle: (String, Any) -> Unit) {
    keys().forEach {
        handle(it, get(it))
    }
}

inline fun JSONArray.forEach(handle: (Int) -> Unit) {
    val length = length()
    for (i in 0..length) {
        handle(i)
    }
}

fun JSONObject.putIfExist(key: String, value: Any?) {
    if (value != null) {
        put(key, value)
    }
}

fun JSONObject.putMap(map: Map<String, Any?>) {
    map.forEach {
        put(it.key, it.value)
    }
}

fun JSONObject.putMapIfExist(map: Map<String, Any?>) {
    map.forEach {
        if (it.value != null)
            put(it.key, it.value)
    }
}

fun JSONObject.toMap(): HashMap<String, Any?> {
    val map = hashMapOf<String, Any?>()
    keys().forEach {
        map.put(it, get(it))
    }
    return map
}

fun JSONArray.toList(): ArrayList<Any?> {
    val list = arrayListOf<Any?>()
    forEach {
        list.add(get(it))
    }
    return list
}

fun JSONObject.toBundle(): Bundle {
    val bundle = Bundle()
    forEach { k, v ->
        when (v) {
            is String -> bundle.putString(k, v)
            is Boolean -> bundle.putBoolean(k, v)
            is Int -> bundle.putInt(k, v)
            is Long -> bundle.putLong(k, v)
            is Float -> bundle.putFloat(k, v)
            is JSONObject -> bundle.putBundle(k, v.toBundle())
            is JSONArray -> bundle.putParcelableArrayList(k, v.toBundles())
        }
    }
    return bundle
}

fun JSONArray.toBundles(): ArrayList<Bundle> {
    val bundles = arrayListOf<Bundle>()
    forEach {
        bundles.add(optJSONObject(it).toBundle())
    }
    return bundles
}
