package com.valentin.newsapp.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.valentin.newsapp.appComponent
import com.valentin.newsapp.databinding.FragmentNewsBinding
import com.valentin.newsapp.models.Item
import com.valentin.newsapp.ui.adapter.IItemListener
import com.valentin.newsapp.ui.adapter.ItemAdapter
import com.valentin.newsapp.ui.fragments.interfaces.INewsListener
import com.valentin.newsapp.viewmodel.NewsViewModel
import com.valentin.newsapp.viewmodel.NewsViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import android.content.Intent
import android.net.Uri
import android.util.Log


class NewsFragment : Fragment(), IItemListener {

    private lateinit var listener: INewsListener
    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private val adapter: ItemAdapter = ItemAdapter(this)
//    @Inject
//    lateinit var factory: NewsViewModelFactory
    private lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireContext().appComponent.inject(this)
        viewModel = ViewModelProvider(requireActivity()).get(NewsViewModel::class.java)
        _binding = FragmentNewsBinding.inflate(inflater, container, false)

        //loadData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvNews.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNews.adapter = adapter

        viewModel.news.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

//    private fun loadData() {
//        lifecycleScope.launch(Dispatchers.IO) {
//            val news = viewModel.loadNews()
//
//            withContext(Dispatchers.Main) {
//                binding.rvNews.layoutManager = LinearLayoutManager(requireContext())
//                binding.rvNews.adapter = adapter
//                adapter.submitList(news)
//            }
//        }
//    }

    override fun showFull(item: Item) {
        Log.d(TAG, "Show full: $item")
        listener.openDetailed(item)
//        val intent = Intent(Intent.ACTION_VIEW)
//        intent.data = Uri.parse(item.link)
//        startActivity(intent)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as INewsListener
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private companion object {
        const val TAG = "NewsFragment"
    }
}