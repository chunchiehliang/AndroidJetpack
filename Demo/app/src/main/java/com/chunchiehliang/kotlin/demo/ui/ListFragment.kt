package com.chunchiehliang.kotlin.demo.ui

import android.content.res.Configuration
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chunchiehliang.kotlin.demo.R
import com.chunchiehliang.kotlin.demo.MovieAdapter
import com.chunchiehliang.kotlin.demo.MovieViewModel
import com.chunchiehliang.kotlin.demo.databinding.FragmentListBinding


class ListFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentListBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_list, container, false)

        val viewModel: MovieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

//        binding.card.setOnClickListener {
//            findNavController().navigate(R.id.action_list_to_detail)
//        }

        binding.recyclerWordList.layoutManager = LinearLayoutManager(context)
        binding.recyclerWordList.adapter = MovieAdapter(viewModel.wordList)
        binding.recyclerWordList.addItemDecoration(MarginItemDecoration((resources.getDimension(R.dimen.margin_normal)).toInt()))

        return binding.root
    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(context, "Portrait", Toast.LENGTH_SHORT).show()
        }else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(context, "Landscape", Toast.LENGTH_SHORT).show()
        }
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
