package com.github.skyfe79.helloroom

import android.app.Application
import android.util.Log
import com.github.skyfe79.android.reactcomponentkit.redux.Error
import com.github.skyfe79.android.reactcomponentkit.redux.Output
import com.github.skyfe79.android.reactcomponentkit.redux.State
import com.github.skyfe79.android.reactcomponentkit.viewmodel.RCKViewModel
import com.github.skyfe79.helloroom.actions.DeleteWordAction
import com.github.skyfe79.helloroom.actions.InsertWordAction
import com.github.skyfe79.helloroom.actions.LoadWordsAction
import com.github.skyfe79.helloroom.components.WordModel
import com.github.skyfe79.helloroom.db.Word
import com.github.skyfe79.helloroom.redux.loadWords
import com.github.skyfe79.helloroom.redux.insertWord
import com.github.skyfe79.helloroom.redux.deleteWord
import com.github.skyfe79.helloroom.redux.makeItemModels

data class MainViewState(
    val words: List<Word> = emptyList(),
    val itemModels: List<WordModel> = emptyList()
): State() {
    override fun copyState(): MainViewState {
        return copy()
    }
}

class MainViewModel(application: Application): RCKViewModel<MainViewState>(application) {

    val itemModels = Output<List<WordModel>>(emptyList())

    override fun setupStore() {

        initStore { store ->
            store.initialState(MainViewState())

            store.flow<LoadWordsAction>(
                { state, _ -> loadWords(state) },
                { state, _ -> makeItemModels(state) }
            )

            store.flow<InsertWordAction>(
                ::insertWord,
                { state, _ -> loadWords(state) },
                { state, _ -> makeItemModels(state) }
            )

            store.flow<DeleteWordAction>(
                ::deleteWord,
                { state, _ -> loadWords(state) },
                { state, _ -> makeItemModels(state) }
            )
        }
    }

    operator fun get(index: Int): Word = withState { state ->
        state.words[index]
    }

    override fun on(newState: MainViewState) {
        itemModels.accept(newState.itemModels)
    }

    override fun on(error: Error) {
        Log.e("MainViewModel", error.toString())
    }
}