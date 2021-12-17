package com.poema.unsplash.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.poema.unsplash.R
import com.poema.unsplash.adapters.PhotoAdapter
import com.poema.unsplash.ui.uimodel.Photo
import com.poema.unsplash.databinding.ActivityMainBinding
import com.poema.unsplash.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var theList : MutableList<Photo>
    private var photoAdapter: PhotoAdapter? = null
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
            theList = it as MutableList<Photo>
            photoAdapter?.submitList(theList)
            binding.progressBar.visibility=View.GONE
        })
    }

    private fun initializeRecycler() {
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context)
            photoAdapter = PhotoAdapter(context)
            adapter = photoAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)

        val menuItem = menu.findItem(R.id.search)
        val searchView = menuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                val searchText = newText?.lowercase(Locale.getDefault())
                searchText?.let { text ->
                    if (text.isNotEmpty()) {
                        viewModel.setSearchText(text)
                        binding.progressBar.visibility=View.VISIBLE
                    }
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