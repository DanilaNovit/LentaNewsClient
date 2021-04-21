package com.novitsky.lentanewsclient.contracts

interface MainActivityContract {
    interface View {
        fun setVisibleBackButton(isVisible: Boolean)
        fun setTitle(title: String)
        fun finish()
    }

    interface Presenter {
        fun onCreateActivity()
        fun onClickBackButton()
        fun onBackStackChanged(size: Int)
    }
}