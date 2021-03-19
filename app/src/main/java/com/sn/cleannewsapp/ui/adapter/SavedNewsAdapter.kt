package com.sn.cleannewsapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sn.cleannewsapp.data.entities.Article
import com.sn.cleannewsapp.databinding.NewsFavoriteItemViewBinding

class SavedNewsAdapter constructor(
    private val onClick: (article: Article) -> Unit,
    private val onClickDelete: (article: Article) -> Unit
) :
    RecyclerView.Adapter<SavedNewsAdapter.SavedNewsViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
            oldItem.url === newItem.url

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
            oldItem == newItem

    }
    val differ = AsyncListDiffer(this, diffCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedNewsViewHolder =
        parent(parent)

    override fun onBindViewHolder(holder: SavedNewsViewHolder, position: Int) {
        holder.bind(differ.currentList[position], onClick, onClickDelete)
    }

    override fun getItemCount(): Int = differ.currentList.size

    private fun parent(parent: ViewGroup): SavedNewsViewHolder =
        SavedNewsViewHolder(
            NewsFavoriteItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    inner class SavedNewsViewHolder(private val newsItemViewBinding: NewsFavoriteItemViewBinding) :
        RecyclerView.ViewHolder(newsItemViewBinding.root) {
        fun bind(article: Article, onClick: (article: Article) -> Unit, onClickDelete: (article: Article) -> Unit) {
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

                imgDelete.setOnClickListener {
                    onClickDelete(article)
                }
            }
        }
    }

}