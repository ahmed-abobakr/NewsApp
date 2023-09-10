package com.ahmed.abobakr.newsapp.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.ahmed.abobakr.newsapp.databinding.FragmentDetailsBinding
import com.bumptech.glide.Glide

class DetailsFragment: Fragment() {

    private lateinit var  binding: FragmentDetailsBinding

    val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val article = args.article

        binding.tvNewsDetails.text = article?.content

        binding.tvAuthor.text = article?.author

        binding.tvDate.text = article?.publishedAt?.substring(0, article?.publishedAt?.indexOf("T") ?: 0)

        article?.urlToImage?.let {
            Glide.with(requireContext()).load(it).into(binding.imgNewsDetails)
        }
    }

}