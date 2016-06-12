package com.bookislife.kandroid365.ui.component

import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import com.bookislife.kandroid365.model.local.Labs
import kotlinx.android.synthetic.main.activity_listview.*
import org.jetbrains.anko.appcompat.v7.onQueryTextListener
import java.util.*

/**
 * SearchView Showcase
 *
 * Created by SidneyXu on 2016/05/19.
 */
class SearchActivity : BaseActivity() {

    var adapter: ArrayAdapter<String>? = null
    var originalData = lazy {
        Labs.words
    }
    var data = lazy {
        Labs.words.toMutableList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listview)
        setUpToolbar()

        adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data.value)
        list.adapter = adapter
    }

    private fun filter(query: String) {
        val newData = originalData.value.filter {
            it.contains(query, true)
        }
        data.value.clear()
        data.value.addAll(newData)
        adapter!!.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val menuItem = menu.findItem(R.id.action_search)
        val searchView = MenuItemCompat.getActionView(menuItem) as SearchView
        searchView.onQueryTextListener {
            onQueryTextChange {
                this@SearchActivity.filter(it!!)
                true
            }
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_search) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}