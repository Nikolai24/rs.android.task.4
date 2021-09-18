package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.preference.PreferenceManager
import com.example.myapplication.room.WordViewModel
import com.example.myapplication.room.WordViewModelFactory
import com.example.myapplication.sqlite.DBOperation

class EditAnimalActivity : AppCompatActivity() {
    private lateinit var editName: TextView
    private lateinit var editAge: TextView
    private lateinit var editBreed: TextView
    var position = 0
    var id = 0
    private lateinit var buttonSave: Button
    private lateinit var buttonDelete: Button
    private val operation: DBOperation = DBOperation()
    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_animal)
        title = "Update Animal"
        editName = findViewById(R.id.edit_name)
        editAge = findViewById(R.id.edit_age)
        editBreed = findViewById(R.id.edit_breed)
        val textView = findViewById<TextView>(R.id.text)
        if (intent.hasExtra("item") && intent.hasExtra("position")) {
            var item: Item = intent.getSerializableExtra("item") as Item
            id = item.id
            editName.text = item.name
            editAge.text = item.age.toString()
            editBreed.text = item.breed
            position = intent.getIntExtra("position", 0)
            textView.text = (position + 1).toString()
        }

        buttonSave = findViewById(R.id.buttonEdit)
        buttonDelete = findViewById(R.id.buttonDelete)
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        var sort = prefs.getString("prefSort", "name").toString()
        var database = prefs.getString("prefDatabase", "sqlite").toString()
        buttonSave.setOnClickListener {
            if (database.length == 6) {
                operation.updateAnimal(applicationContext, editName.text.toString(), editAge.text.toString().toInt(), editBreed.text.toString(), position, sort)
                finish()
            }
            else {
                val word = Item( id, editName.text.toString(), editAge.text.toString().toInt(), editBreed.text.toString())
                wordViewModel.update(word)
                finish()
            }
        }
        buttonDelete.setOnClickListener {
            if (database.length == 6) {
                operation.deleteAnimal(applicationContext, position, sort)
                finish()
            }
            else {
                val word = Item( id, editName.text.toString(), editAge.text.toString().toInt(), editBreed.text.toString())
                wordViewModel.delete(word)
                finish()
            }
        }
    }

    companion object{
        @JvmStatic
        fun newIntent(context: Context) = Intent(context, EditAnimalActivity::class.java)
    }
}