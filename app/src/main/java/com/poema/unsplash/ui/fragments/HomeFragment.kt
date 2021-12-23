package com.poema.unsplash.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.poema.unsplash.R
import com.poema.unsplash.databinding.FragmentHomeBinding
import com.poema.unsplash.ui.adapters.UnsplashPhotoAdapter
import com.poema.unsplash.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private var photoAdapter: UnsplashPhotoAdapter? = null
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

    return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        initializeRecycler()
        subscribeToListOfUrls()
    }

    private fun subscribeToListOfUrls() {
        viewModel.listOfPhoto.observe(this, {
            photoAdapter?.submitData(this.lifecycle, it)
        })

    }

    private fun initializeRecycler() {
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context)
            photoAdapter = UnsplashPhotoAdapter()
            adapter = photoAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.main, menu)

        val menuItem = menu.findItem(R.id.search)
        val searchView = menuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query!!.isNotEmpty()) {
                    viewModel.setSearchText(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                //val searchText = newText?.lowercase(Locale.getDefault())
                /*  searchText?.let { text ->
                      if (text.isNotEmpty()) {
                          viewModel.setSearchText(text)
                          binding.progressBar.visibility=View.VISIBLE
                      }
                  }*/
                return false
            }
        })
    }

    override fun onResume() {
        super.onResume()
        val appCompatActivity = activity as AppCompatActivity
        appCompatActivity.apply {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setDisplayShowTitleEnabled(true)
            supportActionBar?.show()
        }
    }

    /* override fun onOptionsItemSelected(item: MenuItem): Boolean {
         when (item.itemId) {
             R.id.search -> Toast.makeText(this, "Search Clicked", Toast.LENGTH_SHORT).show()
         }
         return super.onOptionsItemSelected(item)
     }*/
}

