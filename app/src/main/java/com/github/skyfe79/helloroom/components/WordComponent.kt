package com.github.skyfe79.helloroom.components

import android.content.Context
import android.view.View
import android.widget.TextView
import com.github.skyfe79.android.reactcomponentkit.collectionmodels.ItemModel
import com.github.skyfe79.android.reactcomponentkit.component.ViewComponent
import com.github.skyfe79.android.reactcomponentkit.eventbus.Token
import com.github.skyfe79.helloroom.R
import com.github.skyfe79.helloroom.db.Word
import org.jetbrains.anko.*
import kotlin.reflect.KClass


interface WordProvider {
    val word: Word
}

data class WordModel(override val word: Word): ItemModel(), WordProvider {
    override val componentClass: KClass<*>
        get() = WordComponent::class
    override val id: Int
        get() = word.hashCode()
}

class WordComponent(override var token: Token, override var receiveState: Boolean): ViewComponent(token, receiveState) {

    private lateinit var textView: TextView

    override fun layout(ui: AnkoContext<Context>): View = with(ui) {
        val view = include<View>(R.layout.word_component)
        textView = view.findViewById(R.id.textView)
        return view
    }

    override fun on(item: ItemModel, position: Int) {
        val wordProvider = (item as? WordProvider) ?: return
        textView.text = wordProvider.word.word
    }
}