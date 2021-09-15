package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.settings.SettingsActivity
import com.example.myapplication.sqlite.DBOperation
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private var items: MutableList<Item> = mutableListOf()
    private lateinit var adapter: DataAdapter
    private lateinit var recyclerView: RecyclerView
    private val operation: DBOperation = DBOperation()

    private val listener: DataAdapter.OnItemClickListener = object : DataAdapter.OnItemClickListener {
        override fun onItemClick(item: Item, position: Int) {
            startEditActivity(item, position)
        }
    }

    private fun startEditActivity(item: Item, position: Int) {
        val intent = Intent(this@MainActivity, EditAnimalActivity::class.java)
        intent.putExtra("name", item.name)
        intent.putExtra("age", item.age)
        intent.putExtra("breed", item.breed)
        intent.putExtra("position", position)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Animals"
        adapter = DataAdapter(items, listener)
        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        findViewById<FloatingActionButton>(R.id.add).setOnClickListener{
            startActivity(AddAnimalActivity.newIntent(this))
        }
    }

    override fun onResume() {
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        var sort = prefs.getString("prefSort", "name").toString()
        var database = prefs.getString("prefDatabase", "sqlite")
        super.onResume()
        adapter.setItems(operation.getAnimalsFromBD(applicationContext, sort))
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.settings){
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

 }