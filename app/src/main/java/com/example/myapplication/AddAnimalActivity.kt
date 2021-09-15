package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.sqlite.DBOperation

class AddAnimalActivity : AppCompatActivity() {
    private lateinit var editName: TextView
    private lateinit var editAge: TextView
    private lateinit var editBreed: TextView
    private lateinit var buttonSave: Button
    private lateinit var buttonCancel: Button
    private val operation: DBOperation = DBOperation()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_animal)
        title = "Add New Animals"
        editName = findViewById(R.id.edit_name)
        editAge = findViewById(R.id.edit_age)
        editBreed = findViewById(R.id.edit_breed)
        buttonSave = findViewById(R.id.buttonSave)
        buttonSave.setOnClickListener {
            operation.saveAnimal(applicationContext, editName.text.toString(), editAge.text.toString(), editBreed.text.toString())
            finish()
        }
        buttonCancel = findViewById(R.id.buttonCancel)
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