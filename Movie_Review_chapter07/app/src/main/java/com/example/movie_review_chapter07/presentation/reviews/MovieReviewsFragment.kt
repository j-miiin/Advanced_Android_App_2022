package com.example.movie_review_chapter07.presentation.reviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_review_chapter07.databinding.FragmentMovieReviewsBinding
import com.example.movie_review_chapter07.domain.model.Movie
import com.example.movie_review_chapter07.domain.model.Review
import com.example.movie_review_chapter07.extension.toGone
import com.example.movie_review_chapter07.extension.toVisible
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.ScopeFragment
import org.koin.core.parameter.parametersOf


class MovieReviewsFragment : ScopeFragment(), MovieReviewsContract.View {

    override val presenter: MovieReviewsContract.Presenter by inject { parametersOf(arguments.movie) }

    private val arguments: MovieReviewsFragmentArgs by navArgs()

    private var binding: FragmentMovieReviewsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View= FragmentMovieReviewsBinding.inflate(inflater, container, false)
        .also { binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        presenter.onViewCreated()
    }

    private fun initViews() {
        binding?.recyclerView?.apply {
            layoutManager = LinearLayoutManager(
                this.context,
                RecyclerView.VERTICAL,
                false
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun showLoadingIndicator() {
        binding?.progressBar?.toVisible()
    }

    override fun hideLoadingIndicator() {
        binding?.progressBar?.toGone()
    }

    override fun showErrorDescription(message: String) {
        binding?.recyclerView?.toGone()
        binding?.errorDescriptionTextView?.toVisible()
        binding?.errorDescriptionTextView?.text = message
    }

    override fun showMovieInformation(movie: Movie) {
        binding?.recyclerView?.adapter = MovieReviewsAdapter(movie)
    }

    override fun showReviews(reviews: List<Review>) {
        binding?.recyclerView?.toVisible()
        binding?.errorDescriptionTextView?.toGone()
        (binding?.recyclerView?.adapter as? MovieReviewsAdapter)?.apply {
            this.reviews = reviews
            notifyDataSetChanged()
        }
    }
}