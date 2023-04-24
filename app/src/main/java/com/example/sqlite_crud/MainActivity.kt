package com.example.sqlite_crud

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.example.sqlite_crud.databinding.ActivityMainBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RestauranteAdapter
    private lateinit var listaRestaurantes: MutableList<Restaurante>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        // Inicializar la lista de restaurantes
        listaRestaurantes = mutableListOf()
        listaRestaurantes.add(Restaurante("Basiliko", "Dirección 1", "12345539"))
        listaRestaurantes.add(Restaurante("Ihop", "Dirección 2", "123455349"))
        listaRestaurantes.add(Restaurante("Sonic", "Dirección 3", "123453489"))
        listaRestaurantes.add(Restaurante("Wendys", "Dirección 4", "12357379"))
        listaRestaurantes.add(Restaurante("Carls", "Dirección 5", "1453489"))
        listaRestaurantes.add(Restaurante("Vips", "Dirección 6", "12345349"))

        // Configurar el RecyclerView
        recyclerView = findViewById(R.id.recycler_view)
        adapter = RestauranteAdapter(listaRestaurantes)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Agregar un restaurante al hacer clic en el botón
        val btnAgregar = findViewById<Button>(R.id.btn_agregar)
        btnAgregar.setOnClickListener {
            val nombre = findViewById<EditText>(R.id.et_nombre).text.toString()
            val direccion = findViewById<EditText>(R.id.et_direccion).text.toString()
            val telefono = findViewById<EditText>(R.id.et_telefono).text.toString()

            Log.d("MainActivity", "Nombre: $nombre")
            Log.d("MainActivity", "Direccion: $direccion")
            Log.d("MainActivity", "Telefono: $telefono")

            val restaurante = Restaurante(nombre, direccion, telefono)
            listaRestaurantes.add(restaurante)
            adapter.notifyDataSetChanged()

            Toast.makeText(this, "Restaurante agregado", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}
