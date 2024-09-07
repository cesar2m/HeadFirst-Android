package com.cesar2m.guessinggame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.io.Closeable

class MainActivityViewModel(val topicWordDao: TopicWordDao) : ViewModel() {

    val listAllTopics = topicWordDao.findAllTopic()
    val listAllWords = topicWordDao.findAllWords()

    fun addInitTopic(): List<Topic> {

        //Save Topcs
        val topic1 = Topic()
        topic1.topicName = "Colores"
        topic1.activo = false

        val topic2 = Topic()
        topic2.topicName = "Frutas"
        topic2.activo = false

        val topic3 = Topic()
        topic3.topicName = "Países"
        topic3.activo = false

        val topic4 = Topic()
        topic4.topicName = "Animales"
        topic4.activo = false


        var listTopics: List<Topic> = listOf(topic1, topic2, topic3, topic4)
        viewModelScope.launch {
            topicWordDao.saveAllTopics(listTopics)
        }
        return listTopics
    }

    fun addInitWords(listAllTopics: List<Topic> ){
    //Save new Words
        //Colores
        var topicColor: Topic? = listAllTopics.find { t -> t.topicName.equals("Colores") }
        var idColor = 1L
        if(null != topicColor &&  topicColor.topicId != 0L ) {
            idColor = topicColor.topicId
        }

        val wordColor01 = Word()
        wordColor01.word = "Azul"
        wordColor01.topicId = idColor

        val wordColor02 = Word()
        wordColor02.topicId = idColor
        wordColor02.word = "Morado"

        val wordColor03 = Word()
        wordColor03.topicId = idColor
        wordColor03.word = "Blanco"

        val wordColor04 = Word()
        wordColor04.topicId = idColor
        wordColor04.word = "Negro"

        var listNewWords01: List<Word> = listOf(wordColor01,wordColor02,wordColor03,wordColor04)
        viewModelScope.launch {
            topicWordDao.saveAllWords(listNewWords01)
        }

        //Frutas
        var topicFruit: Topic? = listAllTopics.find { t -> t.topicName.equals("Frutas") }
        var idPeople = 2L
        if(null != topicFruit &&  topicFruit.topicId != 0L ) {
            idPeople = topicFruit.topicId
        }

        val wordFruit01 = Word()
        wordFruit01.topicId = idPeople
        wordFruit01.word = "Mango"

        val wordFruit02 = Word()
        wordFruit02.topicId = idPeople
        wordFruit02.word = "Platano"

        val wordFruit03 = Word()
        wordFruit03.topicId = idPeople
        wordFruit03.word = "Aguacate"

        val wordFruit04 = Word()
        wordFruit04.topicId = idPeople
        wordFruit04.word = "Fresa"

        val wordFruit05 = Word()
        wordFruit05.topicId = idPeople
        wordFruit05.word = "Papaya"


        var listNewWords02: List<Word> = listOf(wordFruit01,wordFruit02,wordFruit03,wordFruit04,wordFruit05)
        viewModelScope.launch {
            topicWordDao.saveAllWords(listNewWords02)
        }

        //Países
        var topicCountries: Topic? = listAllTopics.find { t -> t.topicName.equals("Países") }
        var idCountries = 3L
        if(null != topicCountries &&  topicCountries.topicId != 0L ) {
            idCountries = topicCountries.topicId
        }

        val wordCountry01 = Word()
        wordCountry01.topicId = idCountries
        wordCountry01.word = "México"

        val wordCountry02 = Word()
        wordCountry02.topicId = idCountries
        wordCountry02.word = "Germany"

        val wordCountry03 = Word()
        wordCountry03.topicId = idCountries
        wordCountry03.word = "French"

        val wordCountry04 = Word()
        wordCountry04.topicId = idCountries
        wordCountry04.word = "España"


        var listNewWords03: List<Word> = listOf(wordCountry01,wordCountry02,wordCountry03,wordCountry04)
        viewModelScope.launch {
            topicWordDao.saveAllWords(listNewWords03)
        }


        //Animales
        var topicAnimals: Topic? = listAllTopics.find { t -> t.topicName.equals("Animales") }
        var idAnimals = 4L
        if(null != topicAnimals &&  topicAnimals.topicId != 0L ) {
            idAnimals = topicAnimals.topicId
        }

        val wordAnimal01 = Word()
        wordAnimal01.topicId = idAnimals
        wordAnimal01.word = "Perro"

        val wordAnimal02 = Word()
        wordAnimal02.topicId = idAnimals
        wordAnimal02.word = "Gato"

        val wordAnimal03 = Word()
        wordAnimal03.topicId = idAnimals
        wordAnimal03.word = "Burro"

        val wordAnimal04 = Word()
        wordAnimal04.topicId = idAnimals
        wordAnimal04.word = "Toro"

        val wordAnimal05 = Word()
        wordAnimal05.topicId = idAnimals
        wordAnimal05.word = "Loro"

        val wordAnimal06 = Word()
        wordAnimal06.topicId = idAnimals
        wordAnimal06.word = "Camello"

        val wordAnimal07 = Word()
        wordAnimal07.topicId = idAnimals
        wordAnimal07.word = "Caballo"


        var listNewWords04: List<Word> = listOf(wordAnimal01,wordAnimal02,wordAnimal03,wordAnimal04
            ,wordAnimal05,wordAnimal06,wordAnimal07)
        viewModelScope.launch {
            topicWordDao.saveAllWords(listNewWords04)
        }
    }

}