package com.novitsky.lentanewsclient.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.novitsky.data.repositories.LentaNetworkRepositoryImpl
import com.novitsky.domain.useсases.GetCatalogUseCaseImpl
import com.novitsky.domain.useсases.GetCategoryUseCaseImpl
import com.novitsky.lentanewsclient.R
import com.novitsky.lentanewsclient.contracts.MainActivityContract
import com.novitsky.lentanewsclient.navigation.RouterImpl
import com.novitsky.lentanewsclient.presenters.MainActivityPresenter

class MainActivity : AppCompatActivity(), MainActivityContract.View,
        FragmentManager.OnBackStackChangedListener {
    private val presenter: MainActivityContract.Presenter

    init {
        val router = RouterImpl(supportFragmentManager, R.id.fragment_container)
        presenter = MainActivityPresenter(this, router)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.addOnBackStackChangedListener(this)

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

    override fun onBackStackChanged() {
        presenter.onBackStackChanged(supportFragmentManager.backStackEntryCount)
    }
}
