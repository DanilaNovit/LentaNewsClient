package com.novitsky.lentanewsclient.view

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.novitsky.data.repositories.LentaNetworkRepositoryImpl
import com.novitsky.domain.useсases.LentaRepositoryUseCaseImpl
import com.novitsky.lentanewsclient.R
import com.novitsky.lentanewsclient.navigation.RouterImpl
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity(), MainContract.View {
    private val presenter: MainContract.Presenter =
                    MainPresenter(WeakReference(this),
                        RouterImpl(supportFragmentManager, R.id.fragment_container),
                    LentaRepositoryUseCaseImpl(LentaNetworkRepositoryImpl()))

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
