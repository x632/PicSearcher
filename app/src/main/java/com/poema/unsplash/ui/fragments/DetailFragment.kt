package com.poema.unsplash.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.poema.unsplash.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {


    private lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var url: String
    private lateinit var description: String
    private lateinit var name: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        url = args.url
        description = args.description
        name = args.name
        Glide
            .with(binding.root)
            .load(url)
            .into(binding.ivDetail)

        binding.tvDescription.text = description
        binding.tvName.text = name

    }

    override fun onResume() {
        super.onResume()
        val temp = activity as AppCompatActivity
        temp.supportActionBar?.hide()
    }
}
