package com.github.skyfe79.helloroom

import android.app.Application
import android.util.Log
import com.github.skyfe79.android.reactcomponentkit.redux.Error
import com.github.skyfe79.android.reactcomponentkit.redux.Output
import com.github.skyfe79.android.reactcomponentkit.redux.State
import com.github.skyfe79.android.reactcomponentkit.viewmodel.RootAndroidViewModelType
import com.github.skyfe79.helloroom.components.WordModel
import com.github.skyfe79.helloroom.db.Word
import com.github.skyfe79.helloroom.redux.loadWords
import com.github.skyfe79.helloroom.redux.insertWord
import com.github.skyfe79.helloroom.redux.deleteWord
import com.github.skyfe79.helloroom.redux.makeItemModels

data class MainViewState(
    val words: List<Word> = emptyList(),
    val itemModels: List<WordModel> = emptyList()
): State()

class MainViewModel(application: Application): RootAndroidViewModelType<MainViewState>(application) {

    val itemModels = Output<List<WordModel>>(emptyList())

    override fun setupStore() {
        store.set(
            initialState = MainViewState(),
            middlewares = emptyArray(),
            reducers = arrayOf(::loadWords, ::insertWord, ::deleteWord),
            postwares = arrayOf(::makeItemModels)
        )
    }

    operator fun get(index: Int): Word {
        return store.state.words[index]
    }

    override fun on(newState: MainViewState) {
        itemModels.accept(newState.itemModels)
    }

    override fun on(error: Error) {
        Log.e("MainViewModel", error.toString())
    }
}