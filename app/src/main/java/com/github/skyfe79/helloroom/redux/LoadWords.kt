package com.github.skyfe79.helloroom.redux

import com.github.skyfe79.android.reactcomponentkit.redux.Action
import com.github.skyfe79.android.reactcomponentkit.redux.State
import com.github.skyfe79.helloroom.MainViewModel
import com.github.skyfe79.helloroom.MainViewState
import com.github.skyfe79.helloroom.actions.LoadWordsAction
import com.github.skyfe79.helloroom.db.WordDB
import io.reactivex.Observable

fun MainViewModel.loadWords(state: State, action: Action): Observable<State> {
    if (action !is LoadWordsAction) { return Observable.just(state) }
    val mainViewState = (state as? MainViewState) ?: return Observable.just(state)

    val words = WordDB.getInstance(getApplication())
        .wordDao()
        .getAlphabetizedWords()

    return Observable.just(mainViewState.copy(words = words))
}