package com.example.movie_review_chapter07.presentation.reviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie_review_chapter07.databinding.ItemMovieInformationBinding
import com.example.movie_review_chapter07.databinding.ItemReviewBinding
import com.example.movie_review_chapter07.domain.model.Movie
import com.example.movie_review_chapter07.domain.model.Review
import com.example.movie_review_chapter07.extension.toAbbreviatedString
import com.example.movie_review_chapter07.extension.toDecimalFormatString
import com.google.android.material.chip.Chip

class MovieReviewsAdapter(private val movie: Movie) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var reviews: List<Review> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> {
                MovieInformationViewHolder(
                    ItemMovieInformationBinding
                        .inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
            ITEM_VIEW_TYPE_ITEM -> {
                ReviewViewHolder(parent)
            }
            else -> throw RuntimeException("알 수 없는 ViewType 입니다.")
        }

    override fun getItemCount(): Int = reviews.size + 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int): Unit =
        when (holder) {
            is MovieInformationViewHolder -> {
                holder.bind(movie)
            }
            is ReviewViewHolder -> {    // review는 최초 position 값이 1인 상태로 오게 됨 -> 실제 데이터는 -1 해야 함
                holder.bind(reviews[position - 1])
            }
            else -> throw RuntimeException("알 수 없는 ViewHolder 입니다.")
        }

    override fun getItemViewType(position: Int): Int =
        when (position) {
            0 -> ITEM_VIEW_TYPE_HEADER
            else -> ITEM_VIEW_TYPE_ITEM
        }

    inner class MovieInformationViewHolder(private val binding: ItemMovieInformationBinding)
        : RecyclerView.ViewHolder(binding.root) {

            fun bind(item: Movie) {
                Glide.with(binding.root)
                    .load(item.posterUrl)
                    .into(binding.posterImageView)

                item.let {
                    binding.averageScoreTextView.text =
                        "평점 ${it.averageScore?.toDecimalFormatString("0.0")} (${it.numberOfScore?.toAbbreviatedString()})"
                    binding.titleTextView.text = it.title
                    binding.additionalInformationTextView.text = "${it.releaseYear}·${it.country}"
                    binding.relationsTextView.text = "감독 : ${it.director}\n출연진 : ${it.actors}"
                    binding.genreChipGroup.removeAllViews()
                    it.genre?.split(" ")?.forEach { genre ->
                        binding.genreChipGroup.addView(
                            Chip(binding.root.context).apply {
                                isClickable = false
                                text = genre
                            }
                        )
                    }
                }
            }
        }

    inner class ReviewViewHolder(
        parent: ViewGroup,
        private val binding: ItemReviewBinding = ItemReviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Review) {
            item.let {
                binding.authorIdTextView.text = "${it.userId?.take(3)}***"
                binding.scoreTextView.text = it.score?.toDecimalFormatString("0.0")
                binding.contentsTextView.text = "\"${it.content}\""
            }
        }
    }

    companion object {
        private const val ITEM_VIEW_TYPE_HEADER = 0
        private const val ITEM_VIEW_TYPE_ITEM = 1
    }
}