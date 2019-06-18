package com.github.skyfe79.helloroom.actions

import com.github.skyfe79.android.reactcomponentkit.redux.Action
import com.github.skyfe79.helloroom.db.Word

data class DeleteWordAction(val word: Word): Action