package com.sn.cleannewsapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sn.cleannewsapp.data.entities.Article
import com.sn.cleannewsapp.databinding.NewsItemViewBinding

class BreakingNewsAdapter constructor(private val onClick: (article: Article) -> Unit) :
    RecyclerView.Adapter<BreakingNewsAdapter.BreakingNewsViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
            oldItem.url === newItem.url

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
            oldItem == newItem

    }
    val differ = AsyncListDiffer(this, diffCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreakingNewsViewHolder =
        parent(parent)

    override fun onBindViewHolder(holder: BreakingNewsViewHolder, position: Int) {
        holder.bind(differ.currentList[position], onClick)
    }

    override fun getItemCount(): Int = differ.currentList.size

    private fun parent(parent: ViewGroup): BreakingNewsViewHolder = BreakingNewsViewHolder(
        NewsItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    inner class BreakingNewsViewHolder(private val newsItemViewBinding: NewsItemViewBinding) :
        RecyclerView.ViewHolder(newsItemViewBinding.root) {
        fun bind(article: Article, onClick: (article: Article) -> Unit) {
            newsItemViewBinding.apply {
                Glide
                    .with(this.root.context)
                    .load(article.urlToImage)
                    .into(ivArticleImage)

                tvSource.text = article.source?.name
                tvTitle.text = article.title
                tvDescription.text = article.description
                tvPublishedAt.text = article.publishedAt

                this.root.setOnClickListener {
                    onClick(article)
                }
            }
        }
    }

}