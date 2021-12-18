package com.poema.unsplash.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.poema.unsplash.R
import com.poema.unsplash.ui.adapters.UnsplashPhotoAdapter
import com.poema.unsplash.databinding.ActivityMainBinding
import com.poema.unsplash.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private var photoAdapter: UnsplashPhotoAdapter? = null
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)

        val menuItem = menu.findItem(R.id.search)
        val searchView = menuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query!!.isNotEmpty()) {
                    viewModel.setSearchText(query)
                    binding.progressBar.visibility=View.VISIBLE
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                val searchText = newText?.lowercase(Locale.getDefault())
                searchText?.let { text ->
                   /* if (text.isNotEmpty()) {
                        viewModel.setSearchText(text)
                        binding.progressBar.visibility=View.VISIBLE
                    }*/
                }
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

   /* override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> Toast.makeText(this, "Search Clicked", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }*/
}