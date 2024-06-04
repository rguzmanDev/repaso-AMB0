package sv.edu.ufg.fis.amb.practica02

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

// Clase que extiende SQLiteOpenHelper para manejar la base de datos
class BaseDeDatos(context: Context) : SQLiteOpenHelper(context, NOMBRE_BD, null, BD_VERSION) {

    companion object {
        // Constantes para el nombre de la base de datos, versión, nombre de la tabla y columnas
        private const val NOMBRE_BD = "mensaje.db"
        private const val BD_VERSION = 1
        private const val NOMBRE_TABLA = "mensaje"
        private const val ID = "id"
        private const val MENSAJE = "mensaje"
    }

    // Método que se llama cuando la base de datos se crea por primera vez
    override fun onCreate(db: SQLiteDatabase?) {
        // Creación de la tabla
        val queryCreacionTabla = "CREATE TABLE $NOMBRE_TABLA ($ID INTEGER PRIMARY KEY AUTOINCREMENT, $MENSAJE TEXT)"
        db?.execSQL(queryCreacionTabla)
    }

    // Método que se llama cuando se necesita actualizar la base de datos
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Eliminación de la tabla antigua
        val queryBorrarTabla = "DROP TABLE IF EXISTS $NOMBRE_TABLA"
        db?.execSQL(queryBorrarTabla)
        // Creación de la nueva tabla
        onCreate(db)
    }

    // Método para insertar un nuevo mensaje en la base de datos
    fun insertarMensaje(registro: Registro) {
        val base_de_datos = writableDatabase // Obtención de la base de datos en modo escritura
        val valores = ContentValues().apply {
            put(MENSAJE, registro.mensaje) // Agregando el mensaje al conjunto de valores
        }
        base_de_datos.insert(NOMBRE_TABLA, null, valores) // Insertando el conjunto de valores en la tabla
        base_de_datos.close() // Cerrando la base de datos
    }
    fun BaseDeDatos(guardar: Guardar) {
    }
}