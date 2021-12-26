package com.poema.unsplash.ui.fragments


import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.poema.unsplash.R
import com.poema.unsplash.databinding.FragmentHomeBinding
import com.poema.unsplash.other.Constants.DEFAULT_SEARCH
import com.poema.unsplash.ui.adapters.UnsplashPhotoAdapter
import com.poema.unsplash.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private var photoAdapter: UnsplashPhotoAdapter? = null
    private val viewModel: MainViewModel by viewModels()
    private var indicatorItem: MenuItem? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        indicatorItem = menu.findItem(R.id.colorIcon)
        val searchView = menuItem?.actionView as SearchView
        searchView.queryHint = "Search.."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query!!.isNotEmpty()) {
                    viewModel.currentSearch = query
                    viewModel.setSearchText(query)
                    // viewModel.onEvent(UiEvent.SearchQuery(query = query))
                }
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.no_filter -> {
                setIcon(R.drawable.nofiltericon24, "no filter")
            }
            R.id.black_white -> {
                setIcon(R.drawable.black_and_white24, "black_and_white")
            }
            R.id.black -> {
                setIcon(R.drawable.black24, "black")
            }
            R.id.white -> {
                setIcon(R.drawable.white24, "white")
            }
            R.id.yellow -> {
                setIcon(R.drawable.yellow24, "yellow")
            }
            R.id.orange -> {
                setIcon(R.drawable.orange24, "orange")
            }
            R.id.red -> {
                setIcon(R.drawable.redcoloricon24, "red")
            }
            R.id.purple -> {
                setIcon(R.drawable.purple24, "purple")
            }
            R.id.magenta -> {
                setIcon(R.drawable.magenta24, "magenta")
            }
            R.id.green -> {
                setIcon(R.drawable.green24, "green")
            }
            R.id.teal -> {
                setIcon(R.drawable.teal24, "teal")
            }
            R.id.blue -> {
                setIcon(R.drawable.blue24, "blue")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setIcon(icon: Int, title: String) {
        indicatorItem?.apply {
            setIcon(icon)
            this.title = title
        }
        viewModel.setColor(title)
        if(viewModel.currentSearch != ""){
            viewModel.setSearchText(viewModel.currentSearch)
        }else{
            viewModel.currentSearch = DEFAULT_SEARCH
            viewModel.setSearchText(viewModel.currentSearch)
        }
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
}