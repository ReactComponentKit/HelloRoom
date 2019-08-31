package com.github.skyfe79.helloroom.redux

import com.github.skyfe79.helloroom.MainViewModel
import com.github.skyfe79.helloroom.MainViewState
import com.github.skyfe79.helloroom.db.WordDB

fun MainViewModel.loadWords(state: MainViewState): MainViewState {

    val words = WordDB.getInstance(getApplication())
        .wordDao()
        .getAlphabetizedWords()

    return state.copy(words = words)
}