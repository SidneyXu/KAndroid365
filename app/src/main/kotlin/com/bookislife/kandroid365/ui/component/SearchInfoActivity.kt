package com.bookislife.kandroid365.ui.component

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import com.bookislife.kandroid365.extension.log
import com.bookislife.kandroid365.model.local.Labs
import kotlinx.android.synthetic.main.activity_searchinfo.*
import org.jetbrains.anko.appcompat.v7.onQueryTextListener

/**
 * SearchInfo Showcase
 *
 * Created by SidneyXu on 2016/05/24.
 */
class SearchInfoActivity : BaseActivity() {

    var adapter: ArrayAdapter<String>? = null
    var originalData = lazy {
        Labs.words
    }
    var data = lazy {
        Labs.words.toMutableList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchinfo)
        setUpToolbar()

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data.value)
        list.adapter = adapter

        searchView.onQueryTextListener {
            onQueryTextChange {
                log("onQueryTextChange()")
                filter(query = it!!)
                true
            }
            onQueryTextSubmit {
                log("onQueryTextSubmit()")
                searchFor(query = it!!)
                true
            }
        }
        search(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)

        log("onNewIntent()")

        if (Intent.ACTION_SEARCH == intent?.action) {
            val query = intent!!.getStringExtra(SearchManager.QUERY)
            filter(query)
        }
    }

    private fun search(intent: Intent?) {
        if (Intent.ACTION_SEARCH == intent?.action) {
            val query = intent!!.getStringExtra(SearchManager.QUERY)
            searchFor(query)
        }
    }

    private fun searchFor(query: String) {
        val intent = Intent(this, SearchInfoActivity::class.java)
        intent.action = Intent.ACTION_SEARCH
//        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.putExtra(SearchManager.QUERY, query)
        startActivity(intent)
    }

    private fun filter(query: String) {
        val newData = originalData.value.filter {
            it.contains(query, true)
        }
        data.value.clear()
        data.value.addAll(newData)
        adapter!!.notifyDataSetChanged()
    }
}