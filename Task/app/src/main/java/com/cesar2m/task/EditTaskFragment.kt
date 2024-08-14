package com.cesar2m.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.cesar2m.task.databinding.FragmentEditTaskBinding


class EditTaskFragment : Fragment() {

    private var _binding: FragmentEditTaskBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentEditTaskBinding.inflate(inflater, container, false)
        val view = binding.root

        val taskId = EditTaskFragmentArgs.fromBundle(requireArguments()).taskId

        //Lo siguiente es necesario para poder crear el EditTaskViewModelFactory
        val application = requireNotNull(this.activity).application
        val taskDao = TaskDataBase.getInstantce(application).taskDao

        val editTaskViewModelFactory = EditTaskViewModelFactory(taskId,taskDao)
        val editTaskViewModel = ViewModelProvider(this,editTaskViewModelFactory).get(EditTaskViewModel::class.java)

        binding.etViewModel = editTaskViewModel
        binding.lifecycleOwner =viewLifecycleOwner
        editTaskViewModel.navigateToList.observe(viewLifecycleOwner, Observer{navigate ->
            if(navigate){
                view?.findNavController()
                    ?.navigate(R.id.action_editTaskFragment_to_taskFragment)
                editTaskViewModel.onNavigateToList()
            }
        })

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}