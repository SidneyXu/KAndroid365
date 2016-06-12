package com.bookislife.kandroid365.ui.tool

import android.os.Bundle
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import com.bookislife.kandroid365.extension.log
import com.bookislife.kandroid365.model.local.Blog
import com.bookislife.kandroid365.model.local.Comment
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_realm.*
import org.jetbrains.anko.onClick
import java.util.*

/**
 * Realm Showcase
 *
 * Created by SidneyXu on 2016/05/27.
 */
class RealmActivity : BaseActivity() {

    var id: Long = 0L
    val realm = lazy {
        Realm.getDefaultInstance()
    }.value

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_realm)
        setUpToolbar()

        var globalBlog = Blog()

        val globalQuery = realm.where(Blog::class.java).greaterThanOrEqualTo("score", 0).findAll()
        println("query size is ${globalQuery.size}")
        globalQuery.addChangeListener {
            println("query size is ${globalQuery.size}")
        }

        btnSave.onClick {
            realm.executeTransactionAsync({
                // one
                val blog = Blog()
                blog.title = "object from instance"

                var result = it.where(Blog::class.java).max("id")
                val id = if (result != null) result.toLong() + 1 else 1
                blog.id = id
                blog.tag = "temporary data"
                blog.text = "hello world"
                blog.createdAt = Date()
                blog.updatedAt = blog.createdAt

                var ret = it.copyToRealm(blog)
                println("ret == blog is ${ret == blog}")    //false

                // two
                val anotherBlog = it.createObject(Blog::class.java)
                anotherBlog.id = id + 1
                anotherBlog.title = "object from reflection"

                ret = it.copyToRealm(anotherBlog)
                println("ret == anotherBlog is ${ret == anotherBlog}")  //true

                // three
                globalBlog.id = id + 2
                globalBlog.title = "global Object"

                (1..5).forEach {
                    val comment = Comment()
                    comment.text = "comment $it"
                    globalBlog.comments.add(comment)
                }

                ret = it.copyToRealm(globalBlog)
                println("ret == blog is ${ret == globalBlog}")  //false
            }, {
                log("transaction success")
            }, {
                log("transaction error")
                it.printStackTrace()
            })
        }

        btnGet.onClick {
            val blogs = realm.where(Blog::class.java)
                    .greaterThanOrEqualTo("score", 0)
                    .findAll()
            blogs.forEach {
                println(it)
            }
        }

        btnShow.onClick {
            text1.text = "Global object is ${globalBlog}"
        }

    }

    override fun onDestroy() {
        realm.close()
        super.onDestroy()
    }
}