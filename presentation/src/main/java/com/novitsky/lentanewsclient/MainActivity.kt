package com.novitsky.lentanewsclient

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.novitsky.data.repositories.LentaNetworkRepositoryImpl
import com.novitsky.domain.model.NewsModel
import com.novitsky.domain.repository.LentaNetworkRepository
import com.novitsky.domain.useсases.LentaRepositoryUseCase
import com.novitsky.domain.useсases.LentaRepositoryUseCaseImpl
import com.novitsky.lentanewsclient.adapters.CategoryListAdapter
import com.novitsky.lentanewsclient.fragments.CategoryListFragment
import com.novitsky.lentanewsclient.fragments.NewsDetailFragment

class MainActivity : AppCompatActivity() {
    @SuppressLint("CommitTransaction")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = NewsDetailFragment()

        val ft = supportFragmentManager.beginTransaction()


        val useCase: LentaRepositoryUseCase = LentaRepositoryUseCaseImpl(LentaNetworkRepositoryImpl())
        useCase.getCategory(LentaNetworkRepository.NewsCategory.CULTURE.value,
                object: LentaRepositoryUseCase.CallbackCategory {
                    override fun onResponse(category: MutableList<NewsModel>) {
                        fragment.setURL(category[0].guid)
                        ft.add(R.id.test_fragment, fragment)
                        ft.commit()
                    }

                    override fun onFailure(errorMessage: String) {
                        TODO("Not yet implemented")
                    }

                })
    }
}
