package com.cesar2m.guessinggame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.cesar2m.guessinggame.databinding.FragmentGameBinding
import com.cesar2m.guessinggame.databinding.FragmentResultBinding

/**
 * A simple [Fragment] subclass.
 * Use the [ResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentResultBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.wonLost.text  = ResultFragmentArgs.fromBundle(requireArguments()).result

        binding.newGameButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_resultFragment_to_gameFragment)
        }
        return view
    }

    fun onDestroyeView(){
        super.onDestroyView()
        _binding = null
    }


}