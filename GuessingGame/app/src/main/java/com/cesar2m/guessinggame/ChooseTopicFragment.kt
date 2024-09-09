package com.cesar2m.guessinggame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.cesar2m.guessinggame.databinding.FragmentChooseTopicBinding



class ChooseTopicFragment : Fragment() {

    private var _binding: FragmentChooseTopicBinding?  = null
    private val binding get() = _binding!!
    //lateinit var chooseTopicViewModel: ChooseTopicViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_choose_topic, container, false)
        _binding = FragmentChooseTopicBinding.inflate(inflater,container, false)
        var view = binding?.root

        val toolbar = binding.toolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)


        val application = requireNotNull(this.activity).application
        val topicWordDao = GuessingGameDataBase.getInstance(application).topicWordDao
        val  chooseTopicViewModelFactory  = ChooseTopicViewModelFactory(topicWordDao)

        val chooseTopicViewModel = ViewModelProvider(this, chooseTopicViewModelFactory)
            .get(ChooseTopicViewModel::class.java)
        binding?.chooseTopicViewModel = chooseTopicViewModel
        binding?.lifecycleOwner = viewLifecycleOwner

        //Inicializa los checkBoxs
        val adapter = TopicItemAdapter{
            topicId ->
                Toast.makeText(context, "Elijes $topicId ",Toast.LENGTH_SHORT).show()
                chooseTopicViewModel.addTopicForPlay(topicId)
        }
        binding.chooseTopicLista.adapter = adapter
        chooseTopicViewModel.listTopics.observe(viewLifecycleOwner, {listTopic ->
            listTopic?.let {
                adapter.submitList(listTopic)
            }
        })


        binding.btnComenzar.setOnClickListener(){
            var listToPlay = chooseTopicViewModel.listTopicsForPlay.value as ArrayList<Long>

            var stopRed: String  = "\uD83D\uDED1"
            if(listToPlay.isEmpty()){
                Toast.makeText(activity,stopRed + "Elige una opción.", Toast.LENGTH_LONG).show()
            }else {
                var longArgs: ArrayList<Long> = arrayListOf()
                listToPlay.forEach { w -> longArgs += w }
                var action = ChooseTopicFragmentDirections.actionChooseTopicFragmentToGameFragment(
                    longArgs.toLongArray()
                )
                view?.findNavController()?.navigate(action)
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

