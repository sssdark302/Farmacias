# Aplicación de Farmacias

Esta aplicación para Android permite a los usuarios visualizar una lista de farmacias, consultar detalles específicos de cada una y ver su ubicación en un mapa interactivo.
Está diseñada para cargar los datos de farmacias desde un archivo JSON incluido en los recursos de la aplicación y mostrarlos dinámicamente en una interfaz fácil de usar.

- Hecho por: Augusto Perrone
- Link al repo: https://github.com/sssdark302/Farmacias.git 

## Funcionalidades

### 1. **Carga de Datos**
- Los datos de las farmacias se almacenan en un archivo JSON ubicado en `res/raw/farmacias_equipamiento.json`.
- Este archivo se lee en la actividad `ListaFarmaciasActivity` y se convierte en una lista de objetos `Farmacia`.
- Cada farmacia tiene la siguiente información:
    - Nombre
    - Teléfono
    - Latitud y Longitud

### 2. **Visualización de Lista**
- La lista de farmacias se muestra en un `RecyclerView` con un adaptador personalizado (`FarmaciaAdapter`).
- Cada ítem de la lista incluye:
    - El nombre de la farmacia.
    - El número de teléfono.
- Al hacer clic en una farmacia, el usuario es llevado a una pantalla con más detalles.

### 3. **Detalles de Farmacia**
- En la actividad `DetallesFarmaciaActivity`, se muestran los siguientes datos:
    - Nombre de la farmacia.
    - Teléfono.
    - Coordenadas de ubicación (latitud y longitud).
- Se incluye un botón para regresar a la lista principal.
- Se muestra una imagen genérica de un mapa.

### 4. **Ubicación en Mapa**
- Los usuarios pueden ver la ubicación exacta de la farmacia en un mapa interactivo proporcionado por Google Maps.
- La actividad `MapaFarmaciaActivity` utiliza un `MapView` para mostrar la posición de la farmacia con un marcador.
- La vista del mapa incluye:
    - Marcador con el nombre de la farmacia.
    - Zoom configurado a un nivel adecuado para resaltar la ubicación.

### 5. **Firebase para Sincronización (Opcional)**
- Aunque los datos se cargan localmente desde el archivo JSON, la aplicación está preparada para usar Firebase Realtime Database para la sincronización de datos.
- La clase `FirebaseHandler` permite guardar y recuperar farmacias desde la base de datos de Firebase.
- Esto asegura que, en el futuro, los datos puedan actualizarse dinámicamente sin necesidad de modificar la aplicación.

## Estructura de Clases

### **1. `Farmacia`**
- Clase de datos que representa una farmacia con los siguientes campos:
    - `nombre`: Nombre de la farmacia.
    - `telefono`: Número de teléfono de la farmacia.
    - `latitud`: Coordenada de latitud.
    - `longitud`: Coordenada de longitud.

### **2. `FarmaciaAdapter`**
- Adaptador para mostrar la lista de farmacias en un `RecyclerView`.
- Enlaza los datos de cada farmacia con su ítem en la lista.
- Incluye un listener para manejar clics en los ítems y abrir la actividad de detalles.

### **3. `ListaFarmaciasActivity`**
- Muestra la lista de farmacias cargadas desde el archivo JSON.
- Usa un `RecyclerView` para organizar la lista.
- Al hacer clic en una farmacia, abre `DetallesFarmaciaActivity`.

### **4. `DetallesFarmaciaActivity`**
- Muestra los detalles de la farmacia seleccionada, incluyendo nombre, teléfono y coordenadas.
- Incluye un botón para regresar a la lista principal.

### **5. `MapaFarmaciaActivity`**
- Muestra la ubicación de la farmacia seleccionada en un mapa interactivo.
- Usa un marcador (`Marker`) para indicar la posición exacta de la farmacia.

### **6. `FirebaseHandler`**
- Clase para manejar interacciones con Firebase Realtime Database.
- Permite guardar y recuperar farmacias, así como gestionar autenticación.

### **7. `JSONUtils` (Opcional)**
- Clase de utilidad para leer el archivo JSON desde `res/raw` y convertirlo en una lista de objetos `Farmacia`.

## Flujo de Uso

1. **Inicio de la Aplicación:**
    - La aplicación comienza con `ListaFarmaciasActivity`, que carga las farmacias desde el archivo JSON.
    - Se muestra una lista de farmacias en un `RecyclerView`.

2. **Seleccionar una Farmacia:**
    - El usuario selecciona una farmacia de la lista, abriendo `DetallesFarmaciaActivity`.
    - Aquí puede ver más detalles de la farmacia.

3. **Ver Ubicación en el Mapa:**
    - Desde `DetallesFarmaciaActivity`, el usuario puede abrir `MapaFarmaciaActivity` para ver la ubicación exacta de la farmacia en un mapa.

4. **Regresar:**
    - El usuario puede regresar a la lista principal desde cualquier actividad.

## Firebase Realtime Database

- Se intentó implementar la sincronización de datos con Firebase Realtime Database, pero no se completó la integración.
- Solo pude conectar la aplicación a Firebase y configurar la base de datos, pero no puedo hacer que se guarden mediante los metodos implementados.


