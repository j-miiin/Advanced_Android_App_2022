package com.example.movie_review_chapter07.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_review_chapter07.domain.model.FeaturedMovie
import com.example.movie_review_chapter07.domain.model.Movie

class HomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var data: List<DataItem> = emptyList()
    var onMovieClickListener: ((Movie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            ITEM_VIEW_TYPE_SECTION_HEADER -> {
                TitleItemViewHolder(parent.context)
            }
            ITEM_VIEW_TYPE_FEATURED -> {
                FeaturedMovieItemViewHolder(
                    ItemFeaturedMovieBinding
                        .inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
            ITEM_VIEW_TYPE_ITEM -> {
                MovieItemViewHolder(
                    ItemMovieBinding
                        .inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
        }
    }

    fun addData(featuredMovie: FeaturedMovie?, movies: List<Movie>) {
        val newData = mutableListOf<DataItem>()

        featuredMovie?.let {
            newData += DataItem("🔥 요즘 핫한 영화")
            newData += DataItem(it)
        }

        newData += DataItem("🍿")
    }
}