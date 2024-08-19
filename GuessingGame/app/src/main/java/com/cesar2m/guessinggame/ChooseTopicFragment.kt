package com.cesar2m.guessinggame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.cesar2m.guessinggame.databinding.FragmentChooseTopicBinding



class ChooseTopicFragment : Fragment() {

    private var _binding: FragmentChooseTopicBinding?  = null
    private val binding get() = _binding!!
    lateinit var chooseTopicViewModel: ChooseTopicViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_choose_topic, container, false)
        _binding = FragmentChooseTopicBinding.inflate(inflater,container, false)
        var view = binding.root
        chooseTopicViewModel = ViewModelProvider(this).get(ChooseTopicViewModel::class.java)

        binding.checkFrutas.setOnClickListener(){
            var isAdd = if(binding.checkFrutas.isChecked)  true else false
            chooseTopicViewModel.addFruitToList(isAdd);
        }

        binding.chkComida.setOnClickListener(){
            var isAdd = if (binding.chkComida.isChecked)  true else false
            chooseTopicViewModel.addFoodToList(isAdd)
        }

        binding.chkNombresPropios.setOnClickListener(){
            var isAdd = if (binding.chkNombresPropios.isChecked)  true else false
            chooseTopicViewModel.addNamesToList(isAdd)
        }

        binding.checkSO.setOnClickListener(){
            var isAdd = if (binding.checkSO.isChecked)  true else false
            chooseTopicViewModel.addSoToList(isAdd)
        }


        binding.btnComenzar.setOnClickListener(){
            var listWords :  ArrayList<TopicWorrdsEnum> = chooseTopicViewModel.listWordsToGame.value as ArrayList<TopicWorrdsEnum>


            var stopRed: String  = "\uD83D\uDED1"
            if(listWords.isEmpty()){
                Toast.makeText(activity,stopRed + "Elige una opción.", Toast.LENGTH_LONG).show()
            }else {
                var stringsArgs: Array<String> = arrayOf()
                listWords.forEach { w -> stringsArgs += w.toString() }
                var action = ChooseTopicFragmentDirections.actionChooseTopicFragmentToGameFragment(
                    stringsArgs as Array<String>
                )
                view.findNavController().navigate(action)
            }
        }

        return view
    }


}