Reagistro de conferencias online


Para entrar a la documentación y poder probar la API facilmente tienes que:

1. Iniciar la aplicación:
    * Iniciando el proyecto desde cualquier IDE o editor de código.
    * Iniciar el crear la la imagen y el contenedor docker, para ello se dejo el archivo de configuración listo, tanto el arcivo Dockerfile como docker-compose.yml
2. Una vez iniciado debes ingresar la siguiente ruta en el navegador: http://localhost:8080/swagger-ui/index.html
3. Para probar la API en la carpeta superior a la raiz de este proyecto se encuentra un documento el cual contiene datos para probar los enpoints.

Para usar docker necesitas:
1. Situarte en el directorio del proyecto y realizar la accion de Maven -> clean para ello puedes abrir
la termminal y ejecutar el comando:
````
mvn clean
````
2. Posteriormente debes de crear el ejecutable del proyecto realizando Maven -> install igualente puedes abrir
una terminal y escribir:
````
mvn install -DskipTests 
````
3. Tener instalado docker
4. Abrir una terminal el el directorio C:/ruta_de_tu_equipo/entregable-conferencia
5. Construir la imágen con el comando:
`````
docker-compose build
`````
6. Levantar el contenedor con el comando:
````
docker-compose up
````
7. Finalmente se iniciara la aplicación y podrás probarla en swagger, postman o alguna otra aplicación.

> [!NOTE] <br>
> Además para las pruebas la API tiene un admin predeterminado con las credenciales 
> que se encuentran en el archivo:
> entregable-conferencia/credenciales-admin-root.txt
> 
> Así se podra probar más fácilmente la API

El entregable contiene la siguiente estructura:

> [!IMPORTANT] <br>
> entregable-conferencia <directorio que contiene todos los recursos necesarios>
> registro-conferencias <este es el directorio del proyecto>
> docker-compose.yml
> datos-prueba.pdf
> Documentación de la arquitectura de la aplicación.pdf
> Informe diagnóstico.pdf
> credenciales-admin-root.txt

Repositorio de GitHub: https://github.com/SayulRamirez/registro-conferencias
 