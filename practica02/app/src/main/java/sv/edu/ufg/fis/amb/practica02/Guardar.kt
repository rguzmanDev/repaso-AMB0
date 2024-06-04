package sv.edu.ufg.fis.amb.practica02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import sv.edu.ufg.fis.amb.practica02.databinding.ActivityGuardarBinding

// Clase Guardar que extiende de AppCompatActivity
class Guardar : AppCompatActivity() {

    // Declaración de las variables para el binding y la base de datos
    private lateinit var binding: ActivityGuardarBinding
    private lateinit var db: BaseDeDatos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflando el layout con View Binding
        binding = ActivityGuardarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializando la base de datos
        db = BaseDeDatos(this)

        // Configurando el botón de guardar para que inserte el mensaje en la base de datos
        binding.btnGuardar.setOnClickListener {
            // Obteniendo el mensaje del campo de texto
            val mensaje = binding.txtData.text.toString()
            // Creando un nuevo registro con el mensaje
            val registro = Registro(mensaje)
            // Insertando el registro en la base de datos
            db.insertarMensaje(registro)
            // Finalizando la actividad
            finish()
            // Mostrando un Toast para confirmar que el mensaje ha sido guardado
            Toast.makeText(this, "SE HA GUARDADO EL MENSAJE", Toast.LENGTH_LONG).show()
        }
    }
}
