package com.cesar2m.guessinggame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    val palabras = createListOfWords()
    val palabraSecreta = palabras.random().uppercase()
    var palabraSecretaDisplay = ""
    var correcatAdivinacion = ""
    var incorrectaAdivinacion = ""
    var vidas = 3
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        //return inflater.inflate(R.layout.fragment_game, container, false)

        _binding = FragmentGameBinding.inflate(inflater,container,false)
        val view = binding.root

        palabraSecretaDisplay = deriveSecretWordDisplay()
        updateScreen()

        binding.adivinaButton.setOnClickListener(){

            makeGuess(binding.adivinacion.text.toString().uppercase())
            binding.adivinacion.text = null
            updateScreen()

            if(isWon() || isLost()){
                val action = GameFragmentDirections.actionGameFragmentToResultFragment(wonLostMessage())
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
        binding.palabra.text = palabraSecretaDisplay
        binding.vidas.text = "Te quedan $vidas ."
        binding.adivinacionIncorrecta.text = "Incorrecta adivinación: $incorrectaAdivinacion"
    }

    fun deriveSecretWordDisplay() : String {
        var display = ""
        palabraSecreta.forEach {
            display += checkLetter(it.toString())
        }
        return display
    }

    fun checkLetter(chr : String ) =
        when (correcatAdivinacion.contains(chr)){
            true -> chr
            false -> "_"
        }

    fun makeGuess(guess:String){

        if (guess.length == 1){
            if (palabraSecreta.contains(guess)){
                correcatAdivinacion += guess
                palabraSecretaDisplay = deriveSecretWordDisplay()
            }else{
                incorrectaAdivinacion += "$guess "
                vidas--
            }
        }
    }

    fun isWon() = palabraSecreta.equals(palabraSecretaDisplay, true)

    fun isLost() = vidas <= 0

    fun wonLostMessage(): String {
        var message = ""
        if (isWon()) message = "¡Ganaste :>) ! "
        else if (isLost()) message = "Perdiste :<("
        message += " La palabra secreta fue $palabraSecreta."
        return message
    }

    fun createListOfWords(): List<String> {
        return listOf<String>("Android", "Activity", "Fragment", "Cesar", "Isabel")
    }


}