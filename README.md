# SPSummit2016-EnsembleJavaHosts

Summit/

Contiene las clases helloBS.java y helloBO.java; BusinessService y BusinessOperation de la aplicación Hola Mundo.

Importar en Eclipse, referenciando a la librería cache-gateway-2.0.0.jar y generar el archivo .jar

Después puede utilizarse el archivo .jar para generar los componentes host (BS y BO) y desplegarlos en una producción de Ensemble

IOT/ 

Contiene clases de la demo de utilización del cliente MQTT.
Debe instalarse primero el Mosquitto  (https://mosquitto.org) 
El cliente lo implementa la librería Java Open Source incluida en javaLibraries\org.eclipse.paho.client.mqttv3_1.0.2.jar

Importar las clases y generar el archivo .jar con el que posteriormente se crearán los componentes host (BS y BO) de Ensemble.

Mosquitto
=========

Una vez instalado el broker, para probarlo, desde línea de comando puede ejecutarse:

mosquitto_sub -t "VENDING\ENS"  --> se quedará escuchando por mensajes asociados al topic VENDING\ENS

Desde otra línea de comando ejecutar:

mosquitto_pub -t "VENDING\ENS" -m "Mensaje de test"  --> enviará un mensaje del topic VENDING\ENS que debería aparecernos en la pantalla anterior


