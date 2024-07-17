package com.cesar2m.guessinggame

import androidx.lifecycle.ViewModel
import kotlin.time.DurationUnit
import kotlin.time.toDuration


class ResultViewModel(finalResult: String , durationGame: Long ) :  ViewModel() {

    val result = finalResult
    val duration = formatTimeDuration (durationGame)

    fun formatTimeDuration(durationGame:  Long) : String{
        var duration = durationGame?.toDuration(DurationUnit.MILLISECONDS)
        var segundos = duration?.inWholeSeconds
        var minutos =  segundos?.div(60);
        var residuoSegundos =  segundos?.mod(60);

        var emoji:  Int =     0x1F552
        return "Tiempo total de juego  " + getEmoji(emoji) + ": " +   minutos + ":" + residuoSegundos
    }

    fun getEmoji(unicode : Int) : String{
        return String(Character.toChars(unicode))
    }
}