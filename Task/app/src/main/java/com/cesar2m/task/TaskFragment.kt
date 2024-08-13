package com.cesar2m.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
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

        //val adapter = TaskItemAdapter{taskId -> Toast.makeText(context, "Click tarea Id: ${taskId}", Toast.LENGTH_SHORT).show()}
       /* val adapter = TaskItemAdapter{ taskId ->
            val action = TaskFragmentDirections.actionTaskFragmentToEditTaskFragment(taskId)
            this.findNavController().navigate(action)
        }*/

        val adapter = TaskItemAdapter{ taskId ->
            taskViewModel.onTaskClicked(taskId)
        }

        binding.tasksList.adapter = adapter

        taskViewModel.tasks.observe(viewLifecycleOwner, Observer{ listTask ->
            listTask?.let{
                adapter.submitList(listTask)
            }
        })

        taskViewModel.navigateToTask.observe(viewLifecycleOwner, Observer{
            taskId -> taskId?.let {
                val action = TaskFragmentDirections.actionTaskFragmentToEditTaskFragment(taskId)
            this.findNavController().navigate(action)
            taskViewModel.onTaskNavigated()
        }
        })



        return view;
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}