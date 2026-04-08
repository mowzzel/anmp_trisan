package com.bolet.projectstudent.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bolet.projectstudent.databinding.FragmentStudentDetailBinding
import com.bolet.projectstudent.viewmodel.DetailViewModel
import com.bolet.projectstudent.viewmodel.ListViewModel

class StudentDetailFragment : Fragment() {

    private var _binding: FragmentStudentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: DetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStudentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val student = StudentDetailFragmentArgs.fromBundle(requireArguments()).student

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        observeViewModel()
        viewModel.fetch(student)
    }

    private fun observeViewModel() {
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            binding.txtNrp.setText(it.id)
            binding.txtName.setText(it.name)
            binding.txtBod.setText(it.bod)
            binding.txtPhone.setText(it.phone)
        })
    }
}