package com.novitsky.lentanewsclient

import android.annotation.SuppressLint
//import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.novitsky.data.repositories.LentaNetworkRepositoryImpl
import com.novitsky.domain.model.NewsModel
import com.novitsky.domain.useсases.LentaRepositoryUseCase
import com.novitsky.domain.useсases.LentaRepositoryUseCaseImpl
import com.novitsky.lentanewsclient.adapters.CatalogListAdapter
import com.novitsky.lentanewsclient.fragments.CatalogListFragment

class MainActivity : AppCompatActivity() {
    @SuppressLint("CommitTransaction")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = CatalogListFragment()

        val ft = supportFragmentManager.beginTransaction()


        val useCase: LentaRepositoryUseCase = LentaRepositoryUseCaseImpl(LentaNetworkRepositoryImpl())
        useCase.getCatalog(4, object: LentaRepositoryUseCase.CallbackCatalog {
            override fun onResponse(catalog: MutableMap<String, MutableList<NewsModel>>) {
                fragment.setAdapter(CatalogListAdapter(catalog))
                ft.add(R.id.test_fragment, fragment)
                ft.commit()
            }

            override fun onFailure(errorMessage: String) {
                println(":(")
            }
        })
    }
}
