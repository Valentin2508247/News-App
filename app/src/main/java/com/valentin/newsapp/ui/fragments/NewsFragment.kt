package com.valentin.newsapp.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.valentin.newsapp.appComponent
import com.valentin.newsapp.databinding.FragmentNewsBinding
import com.valentin.newsapp.models.Item
import com.valentin.newsapp.ui.adapter.IItemListener
import com.valentin.newsapp.ui.adapter.ItemAdapter
import com.valentin.newsapp.ui.fragments.interfaces.INewsListener
import com.valentin.newsapp.viewmodel.NewsViewModel


class NewsFragment : Fragment(), IItemListener {

    private lateinit var listener: INewsListener
    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private val adapter: ItemAdapter = ItemAdapter(this)
    private lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireContext().appComponent.inject(this)
        viewModel = ViewModelProvider(requireActivity()).get(NewsViewModel::class.java)
        _binding = FragmentNewsBinding.inflate(inflater, container, false)

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

    override fun showFull(item: Item) {
        Log.d(TAG, "Show full: $item")
        listener.openDetailed(item)
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