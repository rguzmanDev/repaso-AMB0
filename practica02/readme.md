https://inloop.github.io/sqlite-viewer/

# Practica-02

## Descripción

La aplicación "Repaso" es una aplicación de Android simple que permite al usuario ingresar texto y guardarlo en la memoria interna y externa del dispositivo. 

La aplicación consta de dos pantallas principales:

1. **MainActivity**: Esta es la pantalla de inicio de la aplicación. Contiene un botón que permite al usuario navegar a la pantalla `Guardar` para crear nuevos registros.

2. **Guardar**: En esta pantalla, el usuario puede ingresar un mensaje en un campo de texto. Al hacer clic en el botón "Guardar", el mensaje se guarda en la base de datos interna y externa del dispositivo.

## Funcionalidades

- **Ingreso de texto**: Los usuarios pueden ingresar cualquier texto que deseen guardar en la base de datos.

- **Guardado de datos**: Los datos ingresados por los usuarios se guardan tanto en la memoria interna como en la memoria externa del dispositivo.

- **Manejo de permisos**: La aplicación solicita los permisos necesarios para escribir en la memoria externa del dispositivo y maneja adecuadamente la concesión o denegación de estos permisos.

## Estructura del código

El código de la aplicación se divide en varias partes:

- **MainActivity.kt**: Esta es la actividad principal de la aplicación. Maneja la navegación a la pantalla `Guardar`.

- **Guardar.kt**: Esta actividad permite al usuario ingresar un mensaje y lo guarda en la base de datos cuando se hace clic en el botón "Guardar".

- **BaseDeDatos.kt**: Esta clase ayuda a manejar las operaciones de la base de datos, como la creación de la base de datos y la inserción de datos.

- **Registro.kt**: Esta es una clase de datos que representa un registro con un mensaje.

Además, la aplicación utiliza varios archivos XML para definir la interfaz de usuario y los recursos de la aplicación.
