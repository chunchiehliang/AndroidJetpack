package com.chunchiehliang.kotlin.demo.ui.movie

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.chunchiehliang.kotlin.demo.R
import com.chunchiehliang.kotlin.demo.databinding.FragmentMovieBinding
import com.chunchiehliang.kotlin.demo.widget.FadingSnackbar


class MovieFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentMovieBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_movie, container, false
        )

        val viewModel: MovieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        val adapter = MovieAdapter(MovieListener { movieId ->
            viewModel.onMovieClicked(movieId)
            Toast.makeText(context, "$movieId", Toast.LENGTH_SHORT).show()
        })
        binding.recyclerMovieList.adapter = adapter
        binding.recyclerMovieList.addItemDecoration(MarginItemDecoration((resources.getDimension(R.dimen.margin_normal)).toInt()))

        viewModel.movieList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

//        viewModel.navigateToMovieDetail.observe(this, Observer { movieId ->
//            movieId?.let {
//                this.findNavController().navigate(MovieFragmentDirections.
//                    actionMovieFragmentToMovieDetailFragment(movieId))
//                viewModel.onMovieDetailNavigated()
//            }
//        })

        return binding.root
    }

    private inner class MarginItemDecoration(val margin: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            super.getItemOffsets(outRect, view, parent, state)

            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = margin
            }
            outRect.bottom = margin
            outRect.left = margin
            outRect.right = margin
        }
    }
}