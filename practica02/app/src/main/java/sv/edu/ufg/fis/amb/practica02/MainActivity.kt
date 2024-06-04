package sv.edu.ufg.fis.amb.practica02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import sv.edu.ufg.fis.amb.practica02.databinding.ActivityMainBinding

// Clase MainActivity que extiende de AppCompatActivity
class MainActivity : AppCompatActivity() {

    // Declaración de la variable para el binding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflando el layout con View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurando el botón de nuevo registro para que inicie la actividad Guardar
        binding.btnNuevoRegistro.setOnClickListener {
            // Creando un Intent para iniciar la actividad Guardar
            val intent = Intent(this, Guardar::class.java)
            // Iniciando la actividad
            startActivity(intent)
        }
    }
}
