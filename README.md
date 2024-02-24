ZenTunes - Reproductor de Audio MP3 Local

La aplicación ZenTunes es un reproductor de audio MP3 diseñado para brindar a los usuarios una experiencia relajante al reproducir su música almacenada en el dispositivo. Con un enfoque en la usabilidad y el diseño intuitivo, ZenTunes ofrece una experiencia de reproducción de música sin complicaciones.

Características Principales
Reproducción de MP3 Local: Reproduce fácilmente archivos de audio MP3 almacenados en el dispositivo.

Patrón Single Activity: Utiliza el patrón de diseño Single Activity, proporcionando una navegación más eficiente y un flujo de la aplicación más coherente.

Integración de API Externa: Se conecta a la API de Openwhyd (https://openwhyd.org/) para acceder y reproducir listas de reproducción de música desde Internet.

Uso de Ktor para Llamadas API: Ktor se emplea para realizar llamadas a la API de manera eficiente y gestionar las respuestas.

Presentación de Listas de Música: Utiliza RecyclerView para presentar las listas de música de manera ordenada e intuitiva.

Persistencia de Datos: Implementa Room, una biblioteca de persistencia, para almacenar y recuperar información importante de la aplicación.

Sistema de Logos KSP: Incorpora un sistema de logotipos elaborado utilizando la biblioteca KSP (Kotlin Symbol Processing).

Carga de Imágenes con Coil: Presenta imágenes utilizando Coil, una biblioteca de carga de imágenes para Android.

Capturas de Pantalla



![image](https://github.com/Henry17C/DM_T_Final/assets/117785873/2f4cf4cf-8d4d-4183-b322-d5819fc376cf)

![image](https://github.com/Henry17C/DM_T_Final/assets/117785873/4b0d3fec-459d-42de-8b4a-9374acf8e893)

![image](https://github.com/Henry17C/DM_T_Final/assets/117785873/e7662877-ffa7-4787-a292-514079042b2c)

![image](https://github.com/Henry17C/DM_T_Final/assets/117785873/644c5ced-ae92-42e4-ab4e-c182a1b0636d)

![image](https://github.com/Henry17C/DM_T_Final/assets/117785873/d869891d-5e68-479f-a901-2a155f24a66f)


Configuración del Proyecto
Clona el repositorio desde GitHub.
Abre el proyecto en Android Studio.
Configura las claves de la API de Openwhyd en el archivo local.properties.
properties
Copy code
openwhyd.api.key=TU_CLAVE_API_OPENWHYD
Ejecuta la aplicación en un emulador o dispositivo Android.
¡Disfruta de tus momentos zen con ZenTunes!
