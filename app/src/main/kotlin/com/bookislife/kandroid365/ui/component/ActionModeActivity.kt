package com.bookislife.kandroid365.ui.component

import android.os.Bundle
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import kotlinx.android.synthetic.main.activity_action_mode.*
import org.jetbrains.anko.toast

/**
 * ActionMode Showcase
 *
 * Created by SidneyXu on 2016/05/21.
 */
class ActionModeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_action_mode)
        setUpToolbar()

        edit1.customSelectionActionModeCallback = object : ActionMode.Callback {
            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                return false
            }

            override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
                return when (item.itemId) {
                    R.id.action_toast -> {
                        val selectionStart = edit1.selectionStart
                        val selectionEnd = edit1.selectionEnd
                        val selectedText = edit1.text.subSequence(selectionStart, selectionEnd)
                        toast(selectedText)
                        true
                    }
                    R.id.action_close -> {
                        mode.finish()
                        true
                    }
                    else -> false
                }
            }

            override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
                // remove specified menu items
                menu.removeItem(android.R.id.copy);
                menu.removeItem(android.R.id.cut);
                menu.removeItem(android.R.id.paste);

                // add custom menu items
                mode.menuInflater.inflate(R.menu.menu_main, menu)
                return true
            }

            override fun onDestroyActionMode(mode: ActionMode?) {
            }

        }
    }
}