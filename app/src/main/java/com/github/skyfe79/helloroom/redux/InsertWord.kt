package com.github.skyfe79.helloroom.redux

import com.github.skyfe79.android.reactcomponentkit.redux.Action
import com.github.skyfe79.android.reactcomponentkit.redux.State
import com.github.skyfe79.helloroom.MainViewModel
import com.github.skyfe79.helloroom.actions.InsertWordAction
import com.github.skyfe79.helloroom.actions.LoadWordsAction
import com.github.skyfe79.helloroom.db.WordDB
import io.reactivex.Observable

fun MainViewModel.insertWord(state: State, action: Action): Observable<State> {
    val act = (action as? InsertWordAction) ?: return Observable.just(state)

    WordDB.getInstance(getApplication())
        .wordDao()
        .insert(act.word)

    nextDispatch(LoadWordsAction)

    return Observable.just(state)
}