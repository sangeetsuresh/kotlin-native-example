package com.sangeetsuresh.kotlinmultiplatform

import android.text.Editable
import android.text.TextWatcher

class AfterTextChangedListener(private val block: (Editable) -> Unit) : TextWatcher {
    override fun afterTextChanged(s: Editable?) {
        s?.let { block(it) }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

}