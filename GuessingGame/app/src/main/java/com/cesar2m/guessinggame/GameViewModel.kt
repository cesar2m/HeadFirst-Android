package com.cesar2m.guessinggame

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel :  ViewModel() {

    val listWords = createListOfWords()
    val secretWord = listWords.random().uppercase()
    var secretWordDisplay = MutableLiveData<String>()
    var correctGuess = ""
    var incorrectGuesses = MutableLiveData<String>("")
    var lives = MutableLiveData<Int>(5)

    init {
        secretWordDisplay.value = deriveSecretWordDisplay()
    }


    fun deriveSecretWordDisplay() : String {
        var display = ""
        secretWord.forEach {
            display += checkLetter(it.toString())
        }
        return display
    }

    fun checkLetter(chr : String ) =
        when (correctGuess.contains(chr)){
            true -> chr
            false -> "_"
        }


    fun makeGuess(guess:String){

        if (guess.length == 1){
            if (secretWord.contains(guess)){
                correctGuess += guess
                secretWordDisplay.value = deriveSecretWordDisplay()
            }else{
                incorrectGuesses.value += "$guess "
                lives.value = lives.value?.minus(1) //resta 1
            }
        }
    }

    fun isWon() = secretWord.equals(secretWordDisplay.value, true)

    fun isLost() = lives.value ?: 0 <= 0

    fun wonLostMessage(): String {
        var message = ""
        if (isWon()) message = "¡Ganaste :>) ! "
        else if (isLost()) message = "Perdiste :<("
        message += " La palabra secreta fue $secretWord."
        return message
    }

    fun createListOfWords(): List<String> {
        return listOf<String>("Android", "Activity", "Fragment", "Cesar", "Isabel")
    }

    override fun onCleared() {
        Log.i("GameViewModel",  "ViewModel cleared")
    }



}
