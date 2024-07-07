package com.cesar2m.guessinggame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.cesar2m.guessinggame.databinding.FragmentGameBinding

/**
 * A simple [Fragment] subclass.
 * Use the [GameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    lateinit var gameViewModel: GameViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        //return inflater.inflate(R.layout.fragment_game, container, false)

        _binding = FragmentGameBinding.inflate(inflater,container,false)
        val view = binding.root
        gameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        updateScreen()

        binding.adivinaButton.setOnClickListener(){

            gameViewModel.makeGuess(binding.adivinacion.text.toString().uppercase())
            binding.adivinacion.text = null
            updateScreen()

            if(gameViewModel.isWon() || gameViewModel.isLost()){
                val action = GameFragmentDirections.actionGameFragmentToResultFragment(gameViewModel.wonLostMessage())
                view.findNavController().navigate(action)
            }
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun updateScreen(){
        binding.palabra.text = gameViewModel.palabraSecretaDisplay
        binding.vidas.text = "Te quedan ${gameViewModel.vidas} ."
        binding.adivinacionIncorrecta.text = "Incorrecta adivinación: ${gameViewModel.incorrectaAdivinacion}"
    }




}