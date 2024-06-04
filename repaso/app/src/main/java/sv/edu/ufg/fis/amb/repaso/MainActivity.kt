package sv.edu.ufg.fis.amb.repaso

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class MainActivity : AppCompatActivity() {

    // Declaración de variables para la entrada de texto y el botón de guardar
    private lateinit var etEntrada: EditText
    private lateinit var  btnGuardar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicialización de las variables con los elementos de la interfaz de usuario
        etEntrada = findViewById(R.id.etEntrada)
        btnGuardar = findViewById(R.id.btnGuardar)

        // Llamada a la función que crea los archivos iniciales al iniciar la aplicación
        crearArchivosIniciales()

        // Configuración del botón de guardar para que guarde el texto ingresado en los archivos
        btnGuardar.setOnClickListener {
            val texto = etEntrada.text.toString()
            if(texto.isNotEmpty()){
                // Si el texto no está vacío, se guarda en la memoria interna
                guardarEnInterno(texto)
                // Si el almacenamiento externo está disponible y se tiene permiso, se guarda en la memoria externa
                if (validaAlmacenamientoExterno() && verificarPermiso()){
                    guardarEnExterno(texto)
                }

                // Verificación de si el archivo se guardó en la memoria interna
                val nombreArchivoInterno = "escritura_interna.txt"
                val archivoInterno = File(filesDir, nombreArchivoInterno)
                if(archivoInterno.exists()){
                    Log.d("Archivo interno","Existe en memoria interna")
                }else{
                    Log.d("Archivo interno","No existe en memoria interna")
                }

                // Verificación de si el archivo se guardó en la memoria externa
                val nombreArchivoExterno = "escritura_externa.txt"
                val archivoExterno = File(getExternalFilesDir(null), nombreArchivoExterno)
                if(archivoExterno.exists()){
                    Log.d("Archivo Externo","Existe en memoria externa")
                }else{
                    Log.d("Archivo Externo","No existe en memoria externa")
                }
            }else{
                // Si el texto está vacío, se muestra un mensaje al usuario
                Toast.makeText(this,"Ingrese un texto antes de guardar", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Función para crear los archivos iniciales en la memoria interna y externa
    private fun crearArchivosIniciales(){
        // Creación del archivo en la memoria interna
        val nombreArchivoInterno = "escritura_interna.txt"
        val archivoInterno = File(filesDir, nombreArchivoInterno)
        try{
            FileOutputStream(archivoInterno, true).use {fos -> fos.write("Archivo inicial en memoria interna/n".toByteArray())
            }
            Toast.makeText(this, "Archivo inicial creado en memoria interna", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        // Creación del archivo en la memoria externa
        val nombreArchivoExterno = "escritura_externa.txt"
        if (validaAlmacenamientoExterno() && verificarPermiso()){
            val archivoExterno = File(getExternalFilesDir(null), nombreArchivoExterno)
            try{
                FileOutputStream(archivoExterno, true).use { fos ->
                    fos.write("Archivo inicial en memoria externa\n".toByteArray())
                }
                Toast.makeText(this, "Archivo inicial creado en memoria externa", Toast.LENGTH_SHORT).show()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    // Función para guardar el texto ingresado en la memoria interna
    private fun guardarEnInterno(datos: String) {
        val nombreArchivo = "escritura_de_campo_interno.txt"
        val archivo = File(filesDir, nombreArchivo)
        try {
            FileOutputStream(archivo, true).use { fos ->
                fos.write((datos + "\n").toByteArray())
            }
            etEntrada.setText("")
            Toast.makeText(this, "Guardado en memoria interna", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Error al guardar en memoria interna", Toast.LENGTH_SHORT).show()
        }
    }

    // Función para guardar el texto ingresado en la memoria externa
    private fun guardarEnExterno(datos: String) {
        val nombreArchivo = "escritura_de_campo_externo.txt"
        val archivo = File(getExternalFilesDir(null), nombreArchivo)
        try {
            FileOutputStream(archivo, true).use { fos ->
                fos.write((datos + "\n").toByteArray())
            }
            Toast.makeText(this, "Guardado en memoria externa", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Error al guardar en memoria externa", Toast.LENGTH_SHORT).show()
        }
    }

    // Función para validar si el almacenamiento externo está disponible
    private fun validaAlmacenamientoExterno(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    // Función para verificar si se tiene permiso para escribir en la memoria externa
    private fun verificarPermiso(): Boolean {
        val permiso = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        return if (permiso != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
            false
        } else {
            true
        }
    }

    // Función para manejar la respuesta del usuario a la solicitud de permiso
    override fun onRequestPermissionsResult(codigoSolicitud: Int, permisos: Array<out String>, resultadosConcesion: IntArray) {
        super.onRequestPermissionsResult(codigoSolicitud, permisos, resultadosConcesion)
        if (codigoSolicitud == 1) {
            if ((resultadosConcesion.isNotEmpty() && resultadosConcesion[0] == PackageManager.PERMISSION_GRANTED)) {
                // Si el permiso es concedido, se crean los archivos y se realiza la acción de clic en el botón de guardar
                crearArchivosIniciales()
                btnGuardar.performClick()
            } else {
                // Si el permiso es denegado, se muestra un mensaje al usuario
                Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
