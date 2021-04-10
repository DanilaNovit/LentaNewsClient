package com.novitsky.lentanewsclient

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.novitsky.data.repositories.LentaNetworkRepositoryImpl
import com.novitsky.domain.model.NewsModel
import com.novitsky.domain.useсases.LentaRepositoryUseCase
import com.novitsky.domain.useсases.LentaRepositoryUseCaseImpl

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val useCase: LentaRepositoryUseCase = LentaRepositoryUseCaseImpl(LentaNetworkRepositoryImpl())
        useCase.getCatalog(4, object: LentaRepositoryUseCase.CallbackCatalog {
            override fun onResponse(catalog: MutableMap<String, MutableList<NewsModel>>) {
                println("++++++++++++++")
                val str = resources.getString(
                    resources.getIdentifier("science", "string", packageName))
                println(str)
            }

            override fun onFailure(errorMessage: String) {
                println("--------------")
            }
        })
    }
}
