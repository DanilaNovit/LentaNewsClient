package com.novitsky.lentanewsclient.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.novitsky.domain.model.NewsModel
import com.novitsky.domain.repository.LentaNetworkRepository
import com.novitsky.lentanewsclient.adapters.CatalogListAdapter
import com.novitsky.lentanewsclient.adapters.CategoryListAdapter
import com.novitsky.lentanewsclient.fragments.CatalogListFragment
import com.novitsky.lentanewsclient.fragments.CategoryListFragment
import com.novitsky.lentanewsclient.fragments.NewsDetailFragment
import com.novitsky.lentanewsclient.viewholders.CatalogViewHolderFactory
import com.novitsky.lentanewsclient.viewholders.CategoryViewHolder

class RouterImpl(
    private val manager: FragmentManager,
    private val containerViewId: Int
): Router {
    // LEAKS!!!
    private val catalogFragment = CatalogListFragment()
    private val categoryFragment = CategoryListFragment()
    private val detailFragment = NewsDetailFragment()

    private var depth = 0

    override fun showCatalog(newsMap: MutableMap<LentaNetworkRepository.NewsCategory, MutableList<NewsModel>>,
                             listener: CatalogViewHolderFactory.OnItemCatalogClickListener) {
        catalogFragment.updateAdapter(newsMap, listener)
        replace(catalogFragment)
        depth = 1
    }

    override fun showCategory(newsList: MutableList<NewsModel>,
                              listener: CategoryViewHolder.OnCategoryClickListener) {
        categoryFragment.updateAdapter(newsList, listener)
        replace(categoryFragment)
        ++depth
    }

    override fun showDetail(url: String) {
        detailFragment.setURL(url)
        replace(detailFragment)
        ++depth
    }

    override fun back() {
        if (depth > 1) {
            manager.popBackStack()
            --depth
        }
    }

    override fun isRoot(): Boolean {
        return depth == 1
    }

    private fun replace(fragment: Fragment) {
        val transaction = manager.beginTransaction()
        transaction.replace(containerViewId, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
