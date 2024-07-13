package com.cesar2m.guessinggame

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel :  ViewModel() {

    private val listWords = createListOfWords()
    private val secretWord = listWords.random().uppercase()
    private var correctGuess = ""

    private var _secretWordDisplay = MutableLiveData<String>()
    val secretWordDisplay: LiveData<String> get() = _secretWordDisplay
    private var _incorrectGuesses = MutableLiveData<String>("")
    val incorrectGuesses: LiveData<String> get() = _incorrectGuesses
    private var _lives = MutableLiveData<Int>(5)
    val lives: LiveData<Int> get() = _lives

    private var _gameOver = MutableLiveData<Boolean>()
    val gameOver: LiveData<Boolean> get() = _gameOver

    init {
        _secretWordDisplay.value = deriveSecretWordDisplay()
        _gameOver.value = false
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
                _secretWordDisplay.value = deriveSecretWordDisplay()
            }else{
                _incorrectGuesses.value += "$guess "
                _lives.value = _lives.value?.minus(1) //resta 1
            }

            checkGameOver()
        }
    }

    fun checkGameOver(){

        if (isWon()){
            _gameOver.value = true
        }else if(isLost()){
            _gameOver.value = true
        }
    }
    fun isWon() = secretWord.equals(secretWordDisplay.value, true)

    fun isLost() = _lives.value ?: 0 <= 0

    fun wonLostMessage(): String {
        var message = ""
        if (isWon()) message = "¡Ganaste :>) ! "
        else if (isLost()) message = "Perdiste :<("
        message += " La palabra secreta fue $secretWord."
        return message
    }

    fun createListOfWords(): List<String> {
        return listOf<String>("Android", "Linux", "Windows", "Pan","Mango", "Cesar", "Isabel")
    }

    override fun onCleared() {
        Log.i("GameViewModel",  "ViewModel cleared")
    }

    fun finishGame(){
        _gameOver.value = true
    }

}
