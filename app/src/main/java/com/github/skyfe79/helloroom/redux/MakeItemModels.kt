package com.github.skyfe79.helloroom.redux

import com.github.skyfe79.android.reactcomponentkit.collectionmodels.DefaultSectionModel
import com.github.skyfe79.android.reactcomponentkit.redux.Action
import com.github.skyfe79.android.reactcomponentkit.redux.State
import com.github.skyfe79.helloroom.MainViewModel
import com.github.skyfe79.helloroom.MainViewState
import com.github.skyfe79.helloroom.actions.LoadWordsAction
import com.github.skyfe79.helloroom.components.WordModel
import io.reactivex.Observable

fun MainViewModel.makeItemModels(state: State, action: Action): Observable<State> {
    if (action !is LoadWordsAction) { return Observable.just(state) }
    val mainViewState = (state as? MainViewState) ?: return Observable.just(state)
    if (mainViewState.words.isEmpty()) { return Observable.just(state) }

    val itemModels = mainViewState.words.map { WordModel(it) }

    return Observable.just(mainViewState.copy(itemModels = itemModels))
}