package com.github.skyfe79.helloroom.redux

import com.github.skyfe79.android.reactcomponentkit.redux.Action
import com.github.skyfe79.android.reactcomponentkit.redux.State
import com.github.skyfe79.helloroom.MainViewModel
import com.github.skyfe79.helloroom.MainViewState
import com.github.skyfe79.helloroom.actions.InsertWordAction
import com.github.skyfe79.helloroom.actions.LoadWordsAction
import com.github.skyfe79.helloroom.db.WordDB
import io.reactivex.Observable

fun MainViewModel.insertWord(state: MainViewState, action: InsertWordAction): MainViewState {

    WordDB.getInstance(getApplication())
        .wordDao()
        .insert(action.word)

    return loadWords(state, LoadWordsAction)
}