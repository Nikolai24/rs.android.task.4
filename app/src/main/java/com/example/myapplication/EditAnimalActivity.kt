package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.example.myapplication.sqlite.DBOperation

class EditAnimalActivity : AppCompatActivity() {
    private lateinit var editName: TextView
    private lateinit var editAge: TextView
    private lateinit var editBreed: TextView
    var position = 0
    private lateinit var buttonSave: Button
    private lateinit var buttonDelete: Button
    private val operation: DBOperation = DBOperation()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_animal)
        title = "Update Animal"
        editName = findViewById(R.id.edit_name)
        editAge = findViewById(R.id.edit_age)
        editBreed = findViewById(R.id.edit_breed)
        val textView = findViewById<TextView>(R.id.text)
        if (intent.hasExtra("name") && intent.hasExtra("age") && intent.hasExtra("breed") && intent.hasExtra("position")) {
            position = intent.getIntExtra("position", 0)
            editName.text = intent.getStringExtra("name").toString()
            editAge.text = intent.getStringExtra("age").toString()
            editBreed.text = intent.getStringExtra("breed").toString()
            textView.text = (position + 1).toString()
        }
        buttonSave = findViewById(R.id.buttonEdit)
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        var sort = prefs.getString("prefSort", "name").toString()
        var database = prefs.getString("prefDatabase", "sqlite")
        buttonSave.setOnClickListener {
            operation.updateAnimal(applicationContext, editName.text.toString(), editAge.text.toString(), editBreed.text.toString(), position, sort)
            finish()
        }
        buttonDelete = findViewById(R.id.buttonDelete)
        buttonDelete.setOnClickListener {
            operation.deleteAnimal(applicationContext, position, sort)
            finish()
        }
    }

    companion object{
        @JvmStatic
        fun newIntent(context: Context) = Intent(context, EditAnimalActivity::class.java)
    }
}