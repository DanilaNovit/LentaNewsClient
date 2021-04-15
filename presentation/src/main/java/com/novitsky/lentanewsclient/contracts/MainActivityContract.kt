package com.novitsky.lentanewsclient.contracts

import android.content.Context

interface MainActivityContract {
    interface View {
        fun setVisibleBackButton(isVisible: Boolean)
        fun setTitle(title: String)
        fun getContext(): Context
        fun finish()
    }

    interface Presenter {
        fun onCreateActivity()
        fun onClickBackButton()
    }
}