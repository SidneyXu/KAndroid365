package com.bookislife.kandroid365.ui.system

import android.database.Cursor
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.app.LoaderManager
import android.support.v4.content.CursorLoader
import android.support.v4.content.Loader
import android.support.v4.widget.SimpleCursorAdapter
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import com.bookislife.kandroid365.extension.log
import kotlinx.android.synthetic.main.activity_listview.*

/**
 * Contact Showcase
 *
 * Created by SidneyXu on 2016/05/24.
 */
class ContactActivity : BaseActivity() {

    companion object {

        private val FROM_COLUMNS = arrayOf(
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
                    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
                else
                    ContactsContract.Contacts.DISPLAY_NAME
        )

        private val TO_IDS = intArrayOf(android.R.id.text1)
    }

    var apt = lazy {
        SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, null, FROM_COLUMNS, TO_IDS, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listview)
        setUpToolbar()

        list.adapter = apt.value

        supportLoaderManager.initLoader(0, null, object : LoaderManager.LoaderCallbacks<Cursor> {
            override fun onLoadFinished(loader: Loader<Cursor>?, data: Cursor?) {
                apt.value.swapCursor(data)
            }

            override fun onLoaderReset(loader: Loader<Cursor>?) {
                apt.value.swapCursor(null)
            }

            override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor>? {
                return CursorLoader(
                        this@ContactActivity,
                        ContactsContract.Contacts.CONTENT_URI,
                        null,
                        null,
                        null,
                        null
                )
            }

        })

        list.setOnItemClickListener { adapterView, view, pos, id ->
            val cursor = apt.value.cursor
            cursor.moveToPosition(pos)
            val contactId = cursor.getLong(cursor.getColumnIndex(ContactsContract.Contacts._ID))
            val contactKey = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY))
            val contactUri = ContactsContract.Contacts.getLookupUri(contactId, contactKey)

            log("contactId is $contactId, contactKey is $contactKey, contactUri is $contactUri")
        }
    }


}
