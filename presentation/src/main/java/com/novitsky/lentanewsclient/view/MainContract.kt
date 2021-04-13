package com.novitsky.lentanewsclient.view

import android.content.Context

interface MainContract {
    interface View {
        fun setVisibleBackButton(isVisible: Boolean)
        fun setTitle(title: String)
        fun getContext(): Context
    }

    interface Presenter {
        fun onCreateActivity()
        fun onClickBackButton()
    }
}