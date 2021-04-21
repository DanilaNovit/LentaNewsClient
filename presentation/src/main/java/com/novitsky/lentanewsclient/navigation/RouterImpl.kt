package com.novitsky.lentanewsclient.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.novitsky.lentanewsclient.fragments.FragmentFactory
import com.novitsky.lentanewsclient.fragments.FragmentFactoryImpl

class RouterImpl(
    private val manager: FragmentManager,
    private val containerViewId: Int
): Router {
    private val fragmentFactory: FragmentFactory = FragmentFactoryImpl()

    override fun showCatalog() {
        add(fragmentFactory.createCatalog(this))
    }

    override fun showCategory(categoryID: Int) {
        add(fragmentFactory.createCategory(this, categoryID))
    }

    override fun showNewsDetail(id: String) {
        add(fragmentFactory.createDetailNews(id))
    }

    override fun back() {
        manager.popBackStack()
    }

    private fun add(fragment: Fragment) {
        val transaction = manager.beginTransaction()
        transaction.add(containerViewId, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
