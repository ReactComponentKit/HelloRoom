package com.github.skyfe79.helloroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.skyfe79.android.reactcomponentkit.recyclerview.RecyclerViewAdapter
import com.github.skyfe79.android.reactcomponentkit.rx.AutoDisposeBag
import com.github.skyfe79.android.reactcomponentkit.rx.disposedBy
import com.github.skyfe79.helloroom.actions.DeleteWordAction
import com.github.skyfe79.helloroom.actions.InsertWordAction
import com.github.skyfe79.helloroom.actions.LoadWordsAction
import com.github.skyfe79.helloroom.components.WordComponent
import com.github.skyfe79.helloroom.db.Word
import com.github.skyfe79.helloroom.utils.SwipeToDeleteCallback
import com.github.skyfe79.helloroom.utils.WordUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private val disposeBag: AutoDisposeBag by lazy {
        AutoDisposeBag(this)
    }

    private val layoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    private val adapter: RecyclerViewAdapter by lazy {
        RecyclerViewAdapter(token = viewModel.token, useDiff = true)
    }

    private val itemTouchHelper: ItemTouchHelper by lazy {
        ItemTouchHelper(SwipeToDeleteCallback {
            val word = viewModel[it]
            viewModel.dispatch(DeleteWordAction(word))
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        adapter.register(WordComponent::class)
        recyclerview.layoutManager = layoutManager
        recyclerview.adapter = adapter
        itemTouchHelper.attachToRecyclerView(recyclerview)

        loadWords()
        handleClickEvents()
        handleViewModelOutputs()
    }

    private fun loadWords() {
        viewModel.dispatch(LoadWordsAction)
    }

    private fun handleClickEvents() {
        fab.onClick {
            val word = Word(WordUtils.randomWord)
            viewModel.dispatch(InsertWordAction(word))
        }
    }

    private fun handleViewModelOutputs() {
        viewModel
            .itemModels
            .asObservable()
            .subscribe {
                adapter.set(it)
            }
            .disposedBy(disposeBag)
    }
}
