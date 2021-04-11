package com.novitsky.lentanewsclient.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.novitsky.lentanewsclient.R
import com.novitsky.lentanewsclient.adapters.CategoryListAdapter

class CategoryListFragment: Fragment() {
    private var recyclerView: RecyclerView? = null
    private var adapter: RecyclerView.Adapter<CategoryListAdapter.ViewHolder>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_category_list, container, false)
        recyclerView = view.findViewById(R.id.category_list)
        recyclerView?.layoutManager = LinearLayoutManager(view.context)
        recyclerView?.adapter = adapter

        return view
    }

    fun setAdapter(adapter: RecyclerView.Adapter<CategoryListAdapter.ViewHolder>) {
        this.adapter = adapter
    }
}