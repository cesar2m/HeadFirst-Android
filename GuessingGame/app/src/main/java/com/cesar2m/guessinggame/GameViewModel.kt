package com.cesar2m.guessinggame

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel :  ViewModel() {

    val palabras = createListOfWords()
    val palabraSecreta = palabras.random().uppercase()
    var palabraSecretaDisplay = ""
    var correcatAdivinacion = ""
    var incorrectaAdivinacion = ""
    var vidas = 5

    init {
        palabraSecretaDisplay = deriveSecretWordDisplay()
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

    override fun onCleared() {
        Log.i("GameViewModel",  "ViewModel cleared")
    }



}
