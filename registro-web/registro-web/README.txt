Requisitos
Entorno de desarrollo:
Java SE 8 o superior
NetBeans 23 o un IDE compatible


Servidor:
Apache Tomcat 10 o superior


Dependencias:
Jakarta Servlet API (para servlets)
Biblioteca JSON para manejar los datos de usuario.




Estructura del proyecto


HTML:
Contiene la página principal como lo es  index.html


CSS:
Los estilos se encuentran en css/styles.css y aseguran que la aplicación sea responsiva.


Java:
La lógica del servidor está implementada en servlets como LoginServlet, ModificarDatosServlet y RegistrationServlet.


JSON:
Se utiliza un archivo JSON para persistir los datos de los usuarios.




Nota aparte:
Tuve un error al momento de integrar el apache tomcat y el puerto 8080 me apareció que ya estaba en uso, el programa usa el puerto 8081.