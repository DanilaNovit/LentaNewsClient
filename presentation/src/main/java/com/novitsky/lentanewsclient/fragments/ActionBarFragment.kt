package com.novitsky.lentanewsclient.fragments

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class ActionBarFragment: Fragment() {
    open fun updateTitle(title: String) {
        (activity as AppCompatActivity).supportActionBar?.title = title
    }
}
