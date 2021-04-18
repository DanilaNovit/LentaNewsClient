package com.novitsky.lentanewsclient.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.novitsky.domain.model.News
import com.novitsky.lentanewsclient.R
import com.novitsky.lentanewsclient.adapters.CategoryListAdapter
import com.novitsky.lentanewsclient.contracts.CategoryListContract
import com.novitsky.lentanewsclient.viewholders.CategoryViewHolder

class CategoryListFragment: ActionBarFragment,
        CategoryListContract.View, FragmentManager.OnBackStackChangedListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: CategoryListContract.Presenter
    private lateinit var adapter: CategoryListAdapter
    private var title: String? = null

    constructor(): super()

    constructor(presenter: CategoryListContract.Presenter, adapter: CategoryListAdapter) {
        this.presenter = presenter
        this.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.supportFragmentManager?.addOnBackStackChangedListener(this)

        val view = inflater.inflate(R.layout.fragment_category_list, container, false)

        recyclerView = view.findViewById(R.id.category_list)
        progressBar  = view.findViewById(R.id.category_progress_bar)

        adapter.setListener(categoryListener)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter.self()
        progressBar.visibility = ProgressBar.VISIBLE

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewCreated()
    }

    override fun updateData(newsList: MutableList<News>?) {
        adapter.updateData(newsList)
        progressBar.visibility = ProgressBar.INVISIBLE

        title = newsList?.get(0)?.category
        updateTitle(title.toString())
    }

    override fun showError(message: String) {
        val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        toast.show()
    }

    private val categoryListener = object: CategoryViewHolder.OnCategoryClickListener {
        override fun onClick(item: News) {
            presenter.onClickItemNews(item)
        }
    }

    override fun onBackStackChanged() {
        val manager = activity?.supportFragmentManager

        if (manager?.fragments?.last() == this) {
            if (title != null) {
                updateTitle(title!!)
            }
        }
    }
}