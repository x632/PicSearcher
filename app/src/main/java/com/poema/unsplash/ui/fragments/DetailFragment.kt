package com.poema.unsplash.ui.fragments


import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
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

        setHasOptionsMenu(true)
        url = args.url
        description = args.description
        name = args.name
        Glide
            .with(binding.root)
            .load(url)
            .into(binding.ivDetail)

        if (description == "no description") binding.tvDescription.visibility = View.GONE
        binding.tvDescription.text = description
        binding.tvName.text = name

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        findNavController().popBackStack()
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()

        val theOrientationInt = activity?.resources?.configuration?.orientation

        if(theOrientationInt!! == ORIENTATION_LANDSCAPE){
            val temp = activity as AppCompatActivity
            temp.supportActionBar?.hide()
        }else {
            val temp = activity as AppCompatActivity
            temp.supportActionBar?.apply {
                setDisplayShowTitleEnabled(false)
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowTitleEnabled(true)
                show()
            }
        }
    }
}
