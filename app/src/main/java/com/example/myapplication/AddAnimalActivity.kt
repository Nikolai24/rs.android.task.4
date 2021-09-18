package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.observe
import androidx.preference.PreferenceManager
import com.example.myapplication.room.WordRepository
import com.example.myapplication.room.WordViewModel
import com.example.myapplication.room.WordViewModelFactory
import com.example.myapplication.sqlite.DBOperation

class AddAnimalActivity : AppCompatActivity() {
    private lateinit var editName: TextView
    private lateinit var editAge: TextView
    private lateinit var editBreed: TextView
    private lateinit var buttonSave: Button
    private lateinit var buttonCancel: Button
    private val operation: DBOperation = DBOperation()
    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_animal)
        title = "Add New Animals"
        editName = findViewById(R.id.edit_name)
        editAge = findViewById(R.id.edit_age)
        editBreed = findViewById(R.id.edit_breed)
        buttonSave = findViewById(R.id.buttonSave)
        buttonCancel = findViewById(R.id.buttonCancel)

        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        var database = prefs.getString("prefDatabase", "sqlite").toString()

        buttonSave.setOnClickListener {
            if (database.length == 6) {
                operation.saveAnimal(applicationContext, editName.text.toString(), editAge.text.toString().toInt(), editBreed.text.toString())
                finish()
            }
            else {
                val word = Item( 0, editName.text.toString(), editAge.text.toString().toInt(), editBreed.text.toString())
                wordViewModel.insert(word)
                finish()
            }
        }

        buttonCancel.setOnClickListener {
            val toast = Toast.makeText(this, "The list of animals has not changed", Toast.LENGTH_LONG)
            toast.show()
            finish()
        }
    }

    companion object{
        @JvmStatic
        fun newIntent(context: Context) = Intent(context, AddAnimalActivity::class.java)
    }
}