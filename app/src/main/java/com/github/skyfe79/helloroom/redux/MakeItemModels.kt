package com.github.skyfe79.helloroom.redux

import com.github.skyfe79.android.reactcomponentkit.collectionmodels.DefaultSectionModel
import com.github.skyfe79.android.reactcomponentkit.redux.Action
import com.github.skyfe79.android.reactcomponentkit.redux.State
import com.github.skyfe79.helloroom.MainViewModel
import com.github.skyfe79.helloroom.MainViewState
import com.github.skyfe79.helloroom.actions.LoadWordsAction
import com.github.skyfe79.helloroom.components.WordModel
import io.reactivex.Observable

fun MainViewModel.makeItemModels(state: MainViewState): MainViewState {

    val itemModels = state.words.map { WordModel(it) }

    return state.copy(itemModels = itemModels)
}