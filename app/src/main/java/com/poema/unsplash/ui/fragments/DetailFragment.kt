package com.poema.unsplash.ui.fragments


import android.content.Intent
import android.content.Intent.EXTRA_SUBJECT
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.graphics.Bitmap
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.transition.Transition
import com.davemorrissey.labs.subscaleview.ImageSource
import com.poema.unsplash.R
import com.poema.unsplash.databinding.FragmentDetailBinding
import android.graphics.drawable.Drawable
import androidx.annotation.Nullable

import com.bumptech.glide.request.target.CustomTarget





class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var url: String
    private lateinit var description: String
    private lateinit var name: String
    private lateinit var link: String

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
        link = args.downloadLink
        description = args.description
        name = args.name
        Glide.with(this)
            .asBitmap()
            .load(url)
            .into(object : CustomTarget<Bitmap?>() {
            override fun onResourceReady(resource: Bitmap, @Nullable transition: Transition<in Bitmap?>?) {
                binding.ivDetail.setImage( ImageSource.cachedBitmap(
                    resource
                ))
            }
            override fun onLoadCleared(@Nullable placeholder: Drawable?) {}
        })

        if (description == "no description") binding.tvDescription.visibility = View.GONE
        binding.tvDescription.text = description
        binding.tvName.text = name
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.menu_detail, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val entireText = "$url\n\ndownloadLink: $link"

        when (item.itemId) {
            R.id.shareButton -> {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, entireText)
                    putExtra(EXTRA_SUBJECT, "Shared image from PhotoSearcher")
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
            else -> {
                findNavController().popBackStack()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        val theOrientation = activity?.resources?.configuration?.orientation
        if (theOrientation!! == ORIENTATION_LANDSCAPE) {
            // API(R) och framåt (vill inte byta än! Aktuell: activity?.window?.decorView?.windowInsetsController?.hide(WindowInsets.Type.statusBars())
            activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
            val appCompatActivity = activity as AppCompatActivity
            appCompatActivity.supportActionBar?.hide()
        } else {
            val appCompatActivity = activity as AppCompatActivity
            appCompatActivity.supportActionBar?.apply {
                setDisplayShowTitleEnabled(true)
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowTitleEnabled(true)
                show()
            }
        }
    }
}
