package com.cesar2m.guessinggame

import android.opengl.GLES30
import android.os.SystemClock
import android.util.Log
import android.widget.Chronometer
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class GameViewModel(val ggDao: TopicWordDao, val listTopicIds: ArrayList<Long>) :  ViewModel() {

    val allWords = ggDao.findAllWords()

    private val _secretWord = MutableLiveData<String>()
    val secretWord: LiveData<String>
        get() = _secretWord

    private var correctGuess = ""


    private var _secretWordDisplay = MutableLiveData<String>()
    val secretWordDisplay: LiveData<String> get() = _secretWordDisplay

    private var _incorrectGuesses = MutableLiveData<String>("")
    val incorrectGuesses: LiveData<String> get() = _incorrectGuesses

    private var _lives = MutableLiveData<Int>(5)
    val lives: LiveData<Int> get() = _lives

    private var _gameOver = MutableLiveData<Boolean>()
    val gameOver: LiveData<Boolean> get() = _gameOver

    private var _firstAttemp = MutableLiveData<Boolean>()
    val firstAttemp: LiveData<Boolean> get() = _firstAttemp

    private var  _timeTotal = MutableLiveData<Long>()
    val timeTotal:  LiveData<Long> get() = _timeTotal

    init {
        _secretWordDisplay.value = deriveSecretWordDisplay()
        _gameOver.value = false
        _firstAttemp.value = true
        _timeTotal.value = 0L
    }

    fun deriveSecretWordDisplay() : String {
        var display = ""
        _secretWord.value?.forEach {
            display += checkLetter(it.toString())
        }
        return display
    }

    fun checkLetter(chr : String ) =
        when (correctGuess.contains(chr)){
            true -> chr
            false -> "_"
        }


    fun makeGuess(guess:String , cronometro: Chronometer){

        if(_firstAttemp.value == true) {
            _firstAttemp.value = false
            cronometro.base = SystemClock.elapsedRealtime()
            cronometro.start()
        }

        if (guess.length == 1){

            if (_secretWord.value?.contains(guess) == true){
                correctGuess += guess
                _secretWordDisplay.value = deriveSecretWordDisplay()
            }else{
                _incorrectGuesses.value += "$guess "
                _lives.value = _lives.value?.minus(1) //resta 1
            }

            checkChronometer(cronometro)
            checkGameOver()
        }
    }

    fun checkChronometer(cronometro: Chronometer) {
        _timeTotal.value  =  (SystemClock.elapsedRealtime() - cronometro.base).toLong()
    }

    fun checkGameOver(){

        if (isWon()){
            _gameOver.value = true
        }else if(isLost()){
            _gameOver.value = true
        }
    }
    fun isWon() = _secretWord.value.equals(secretWordDisplay.value, true)

    fun isLost() = _lives.value ?: 0 <= 0

    fun wonLostMessage(): String {
        var emojiSmile:  Int = 0x1F60E
        var emojiSad:  Int = 0x1F61B
        var emojiSmollSmile:  Int = 0x1F605
        var secretW = secretWord.value
        var message = "La palabra secreta fue $secretW."
        if (isWon()){
            message = "¡Ganaste " + getEmoji(emojiSmile)  + " ! "  +message
        } else  {
            message = getEmoji(emojiSad) + " Perdiste  " + getEmoji(emojiSmollSmile)  + message

        }
        return message
    }

    fun getEmoji(unicode : Int) : String{
        return String(Character.toChars(unicode))
    }
    fun createListOfWords(listWords: List<Word>){

        var listaPalabrasFinal : ArrayList<String> =  arrayListOf()

        listTopicIds.forEach { topicId ->
            listWords.forEach{w ->
                if(w.topicId.equals(topicId)){
                    listaPalabrasFinal.add(w.word)
                }
            }
        }

        _secretWord.value =  listaPalabrasFinal.random().uppercase()
    }



    fun containsTopic(valor : String): Boolean {
        return TopicWorrdsEnum.entries.contains(TopicWorrdsEnum.valueOf(valor))
    }


    override fun onCleared() {
        Log.i("GameViewModel",  "ViewModel cleared")
    }

    fun finishGame(){
        _gameOver.value = true
    }



}
