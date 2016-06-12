package com.bookislife.kandroid365.ui.text

import android.os.Bundle
import android.support.design.widget.TextInputLayout
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import kotlinx.android.synthetic.main.activity_text_input.*
import org.jetbrains.anko.onCheckedChange
import org.jetbrains.anko.onClick

/**
 * TextInputLayout & TextInputEditText Showcase
 *
 * Created by SidneyXu on 2016/03/25.
 */
class TextInputActivity : BaseActivity() {

    /**
     * EditText vs TextInputEditText
     *
     * Using TextInputEditText to display a hint in the IME when in 'extract' mode
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_input)
        setUpToolbar()

        cbAnimation.onCheckedChange { compoundButton, checked ->
            switchAnimation(checked, tilUsername)
            switchAnimation(checked, tilPassword)
        }

        btnLogin.onClick {
            if (edUsername.text.isEmpty()) {
                edUsername.error = getString(R.string.err_username)
            } else {
                edUsername.error = null
            }
            if (tiePassword.text.isEmpty()) {
                tiePassword.error = getString(R.string.err_password)
            } else {
                tiePassword.error = null
            }
        }
    }

    fun switchAnimation(checked: Boolean, input: TextInputLayout) {
        if (checked) {
            val hintUsername = input.editText!!.hint
            input.editText!!.hint = null
            input.hint = hintUsername
        } else {
            val hintUsername = input.hint
            input.hint = null
            input.editText!!.hint = hintUsername
        }
    }
}