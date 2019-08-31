package com.github.skyfe79.helloroom.redux

import com.github.skyfe79.android.reactcomponentkit.redux.Action
import com.github.skyfe79.android.reactcomponentkit.redux.State
import com.github.skyfe79.helloroom.MainViewModel
import com.github.skyfe79.helloroom.MainViewState
import com.github.skyfe79.helloroom.actions.DeleteWordAction
import com.github.skyfe79.helloroom.actions.LoadWordsAction
import com.github.skyfe79.helloroom.db.WordDB
import io.reactivex.Observable

fun MainViewModel.deleteWord(state: MainViewState, action: DeleteWordAction): MainViewState {

    WordDB.getInstance(getApplication())
        .wordDao()
        .delete(action.word)

    return loadWords(state, LoadWordsAction)
}