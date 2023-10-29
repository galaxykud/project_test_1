package com.example.testtaskjson.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.testtaskjson.data.remote.ReceiptRepository
import com.example.testtaskjson.databinding.MainOverviewLayoutBinding
import com.example.testtaskjson.presentation.recyclerview.ReceiptsAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainFragment: Fragment() {
    private var _binding: MainOverviewLayoutBinding? = null

    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        _binding = MainOverviewLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.lifecycleOwner = viewLifecycleOwner
        val adapter = ReceiptsAdapter(requireActivity())
        binding.rvReceiptList.adapter = adapter
        viewModel.receiptList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        }

    }
