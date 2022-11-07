package com.valentin.newsapp.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.valentin.newsapp.R
import com.valentin.newsapp.appComponent
import com.valentin.newsapp.databinding.FragmentDetailedBinding
import com.valentin.newsapp.databinding.FragmentNewsBinding
import com.valentin.newsapp.ui.fragments.interfaces.IDetailedListener
import com.valentin.newsapp.ui.webView.MyWebViewClient
import com.valentin.newsapp.viewmodel.NewsViewModel
import com.valentin.newsapp.viewmodel.NewsViewModelFactory
import javax.inject.Inject

class DetailedFragment : Fragment() {

    lateinit var listener: IDetailedListener
    private var _binding: FragmentDetailedBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: NewsViewModel
    private val args: DetailedFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireContext().appComponent.inject(this)
        viewModel = ViewModelProvider(requireActivity()).get(NewsViewModel::class.java)
        _binding = FragmentDetailedBinding.inflate(inflater, container, false)


        binding.webView.webViewClient = MyWebViewClient()
        binding.webView.loadUrl(args.url)

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as IDetailedListener
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "DetailedFragment"
    }
}