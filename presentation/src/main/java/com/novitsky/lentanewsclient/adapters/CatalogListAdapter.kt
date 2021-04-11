package com.novitsky.lentanewsclient.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.novitsky.domain.model.NewsModel
import com.novitsky.domain.repository.LentaNetworkRepository
import com.novitsky.lentanewsclient.R
import com.novitsky.lentanewsclient.customview.UrlImageView

class CatalogListAdapter(private val catalogMap: Map<String, List<NewsModel>>):
                RecyclerView.Adapter<CatalogListAdapter.ViewHolder>() {
    private var catalogMapKeys = LentaNetworkRepository.NewsCategory.values()

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_catalog_list, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val context = viewHolder.itemView.context
        val mapKey = catalogMapKeys[position].value

        viewHolder.bind(catalogMap[mapKey],
            context.resources.getString(
                context.resources.getIdentifier(mapKey, "string", context.packageName)))
    }

    override fun getItemCount(): Int {
        return catalogMap.size
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        private val categoryTitle: TextView? = itemView?.findViewById(R.id.category_title)
        private val viewButton: TextView? = itemView?.findViewById(R.id.view_button)

        private val imageNewsOne: UrlImageView? = itemView?.findViewById(R.id.image_news_one)
        private val imageNewsTwo: UrlImageView? = itemView?.findViewById(R.id.image_news_two)
        private val imageNewsThree: UrlImageView? = itemView?.findViewById(R.id.image_news_three)
        private val imageNewsFour: UrlImageView? = itemView?.findViewById(R.id.image_news_four)

        private val titleNewsOne: TextView? = itemView?.findViewById(R.id.title_news_one)
        private val titleNewsTwo: TextView? = itemView?.findViewById(R.id.title_news_two)
        private val titleNewsThree: TextView? = itemView?.findViewById(R.id.title_news_three)
        private val titleNewsFour: TextView? = itemView?.findViewById(R.id.title_news_four)

        fun bind(newsList: List<NewsModel>?, title: String) {
            if (newsList == null || newsList.size < 4) { return }

            categoryTitle?.text = title

            imageNewsOne?.uploadImage(newsList[0].imageURL.toString())
            imageNewsTwo?.uploadImage(newsList[1].imageURL.toString())
            imageNewsThree?.uploadImage(newsList[2].imageURL.toString())
            imageNewsFour?.uploadImage(newsList[3].imageURL.toString())

            titleNewsOne?.text = newsList[0].title
            titleNewsTwo?.text = newsList[1].title
            titleNewsThree?.text = newsList[2].title
            titleNewsFour?.text = newsList[3].title
        }
    }
}