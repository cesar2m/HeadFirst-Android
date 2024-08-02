package com.cesar2m.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cesar2m.task.databinding.FragmentTaskBinding


class TaskFragment : Fragment() {

    private var _binding: FragmentTaskBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTaskBinding.inflate(inflater, container, false)
        val view = binding?.root

        val application = requireNotNull(this.activity).application
        val taskDao = TaskDataBase.getInstantce(application).taskDao
        val taskViewModelFacory = TaskViewModelFactory(taskDao)


        val taskViewModel = ViewModelProvider(this, taskViewModelFacory).get(TaskViewModel::class.java)
        binding?.viewModel = taskViewModel
        binding?.lifecycleOwner = viewLifecycleOwner

        taskViewModel.allTasks.observe(viewLifecycleOwner, Observer {newValue ->
            binding.tasks.text = taskViewModel.formatAllTasks()
        })


        return view;
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}