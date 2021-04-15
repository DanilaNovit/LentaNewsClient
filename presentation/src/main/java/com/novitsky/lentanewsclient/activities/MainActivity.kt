package com.novitsky.lentanewsclient.activities

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.novitsky.lentanewsclient.R
import com.novitsky.lentanewsclient.contracts.MainActivityContract
import com.novitsky.lentanewsclient.navigation.RouterImpl
import com.novitsky.lentanewsclient.presenters.MainActivityPresenter
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity(), MainActivityContract.View {
    private val presenter: MainActivityContract.Presenter =
            MainActivityPresenter(this, RouterImpl(supportFragmentManager, R.id.fragment_container))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.onCreateActivity()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            presenter.onClickBackButton()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        presenter.onClickBackButton()
    }

    override fun setVisibleBackButton(isVisible: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(isVisible)
    }

    override fun setTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun getContext(): Context {
        return this
    }
}
