package sv.edu.ufg.fis.amb.practica03

import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import java.io.File
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

// Clase MainActivity que extiende de AppCompatActivity
class MainActivity : AppCompatActivity() {

    // Declaración de las variables para el ImageView, la Uri de la imagen, el tiempo y el nombre de la imagen
    private lateinit var captura : ImageView
    private lateinit var urlImagen: Uri
    private lateinit var tiempo: String
    private lateinit var nombreImagen: String

    // Registro del contrato para tomar una foto y establecer la imagen en el ImageView
    private var contract = registerForActivityResult(ActivityResultContracts.TakePicture()) { it: Boolean ->
        captura.setImageURI(null)
        captura.setImageURI(urlImagen)
    }

    // Función para crear una Uri para la imagen
    private fun createImageUri(): Uri {
        // Si la versión de Android es Oreo o superior, se obtiene el tiempo actual
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tiempo = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss").withZone(ZoneOffset.UTC).format(
                Instant.now())
        }

        // Creación del nombre de la imagen con el tiempo
        nombreImagen = "$tiempo.png"

        // Creación del archivo de la imagen
        val imagen = File(filesDir,nombreImagen)

        // Devolución de la Uri de la imagen
        return FileProvider.getUriForFile(this, "com.example.contenProvider.FileProvider", imagen)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicialización del ImageView
        captura = findViewById(R.id.captureImageView)

        // Inicialización del botón para capturar la imagen
        val captureImgBtn = findViewById<Button>(R.id.captureImgBtn)

        // Configuración del botón para que lance la cámara al hacer clic
        captureImgBtn.setOnClickListener(){
            // Creación de la Uri de la imagen
            urlImagen = createImageUri()
            // Lanzamiento de la cámara
            contract.launch(urlImagen)
        }
    }
}
