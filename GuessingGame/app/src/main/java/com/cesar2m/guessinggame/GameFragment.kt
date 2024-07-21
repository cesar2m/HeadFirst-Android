package com.cesar2m.guessinggame

import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.cesar2m.guessinggame.databinding.FragmentGameBinding
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

/**
 * A simple [Fragment] subclass.
 * Use the [GameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    lateinit var gameViewModel: GameViewModel
    lateinit var gameViewModelFactory: GameViewModelFactory


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        //return inflater.inflate(R.layout.fragment_game, container, false)

        _binding = FragmentGameBinding.inflate(inflater,container,false)
        val view = binding.root
        var listWordsToPlay  = GameFragmentArgs.fromBundle(requireArguments()).palabrasParaJugar
        gameViewModelFactory = GameViewModelFactory(listWordsToPlay as Array<String>)
        gameViewModel = ViewModelProvider(this,gameViewModelFactory).get(GameViewModel::class.java)
        binding.gameViewModelLayout = gameViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.cronometro.stop()




        //updateScreen()//Se deja de usar cuando se implementó LiveData en GameViewModel
/* Se dejan de usar por la línea: binding.lifecycleOwner = viewLifecycleOwner
        gameViewModel.incorrectGuesses.observe(viewLifecycleOwner, Observer{ newValue ->
            binding.adivinacionIncorrecta.text = "Incorrecta adivinación: $newValue"
        })
        gameViewModel.lives.observe(viewLifecycleOwner, Observer { newValue ->
            binding.vidas.text = "Te quedan ${newValue} aún."
        })
        gameViewModel.secretWordDisplay.observe(viewLifecycleOwner, Observer { newValue ->
            binding.palabra.text = newValue
        })*/
        gameViewModel.gameOver.observe(viewLifecycleOwner, Observer{newValue ->
            if(newValue) {

                var duracion: Long? = gameViewModel.timeTotal.value as? Long?
                val action = GameFragmentDirections
                    .actionGameFragmentToResultFragment (gameViewModel.wonLostMessage(), duracion as Long)
                view.findNavController().navigate(action)

            }
        })


        binding.adivinaButton.setOnClickListener(){
            gameViewModel.makeGuess(binding.adivinacion.text.toString().uppercase(), binding.cronometro)
            binding.adivinacion.text = null


        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    /*
    //No es necesrio desde que se implementó LiveData en ViewModel
    fun updateScreen(){
        binding.palabra.text = gameViewModel.secretWordDisplay
        binding.vidas.text = "Te quedan ${gameViewModel.lives} ."
        binding.adivinacionIncorrecta.text = "Incorrecta adivinación: ${gameViewModel.incorrectGuesses}"
    }*/




}