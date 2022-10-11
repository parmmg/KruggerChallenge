# KruggerChallenge
Kruger Challenge 

1. Crear una base de datos en postgre localmente llamada vaccine_inventory.
2. Clonar el Proyecto dentro de cualquier IDE
3. Realizar la descarga de las dependencias a través de Gradle.
4. El modelo de datos se crea por liquidbase al iniciar por primera vez el proyecto

   4.1. Se crean las estructuras de datos
   
   4.2. Se cargan datos maestros.
   
   4.3. Se carga datos del usuario clave.
        Usuario: test
        Clave: TestProd#2122
        
 5. Para el uso de los endpoints, se debe hacer primero la autenticación, para luego verificar la autorización. Se utiliza e implementa JWT para obtener el token de autenticación, esto es un POST, llamado login donde se obtiene el respectivo token.
![image](https://user-images.githubusercontent.com/115352466/195005020-9fedf580-51fc-4d08-a583-dcc48995148a.png)

 6. En el filterChain, se determina que los endpoints expuestos con el path /app/** solo tiene acceso Administrator.
 7. Los endpoints que pueden acceder el rol de empleado es /employee/**
 8. Al crear un empleado, se crea un usuario con la clave de su cedula y el rol de empleado.
 9. En caso de que no tengan acceso, se les muestra respuesta 403 Forbiden
 ![image](https://user-images.githubusercontent.com/115352466/195006219-f875839b-583a-4ed6-8c33-4c405d55ebdd.png)

 10. Para acceder a los END POINT, se expone el puerto 8084, pudiendo utilizar los siguientes:
 ![image](https://user-images.githubusercontent.com/115352466/195012022-3d41d338-870e-40d4-9d97-32890105c600.png)

   localhost:8084/employee/saveEmployee -> Guarda un empleado, tanto nuevo como existente
 
   localhost:8084/app/findAllEmployees -> Lista todos los empleados activos
   
   localhost:8084/app/getEmployeeByDni -> Obtiene un empleado por DNI
   
   localhost:8084/app/getEmployeeById -> Obtiene un empleado por Id
   
   localhost:8084/app/deleteEmployee -> Se inactiva un empleado por Id
   
   localhost:8084/app/findEmployeesByStatus -> Obtiene una lista de empleados por el estado (VACUNADO, NO_VACUNADO)
   
   localhost:8084/app/findEmployeesByVaccineDate -> Obtiene una lista de empleados por intervalo de fechas de vacunación
   
   localhost:8084/app/findEmployeesByVaccineId -> Obtiene una lista de empleados con el Id de la vacuna que tienen
   
   localhost:8084/app/deleteEmployees -> Se inactiva un empleado por Id
   
   localhost:8084/app/searchEmployees -> Se buscan empleados por todos los atributos anteriores en conjunto y se suma el nombre/apellido
   
   localhost:8084/employee/saveEmployeeVaccine -> Guarda una nueva aplicación de una vacuna para un empleado
   
   localhost:8084/employee/deleteEmployeeVaccine -> Inactiva una aplicación de una vacuna para un empleado
   
   localhost:8084/app/addRoleToUser -> Añade un rol a un usuario (Ambos Id)
   
   localhost:8084/app/deleteRoleToUser -> Quita un rol a un usuario (Ambos Id)
 
   localhost:8084//employee/getVaccines -> Lista todas las vacunas
 
 11. Se implementó la documentación a través de las librerías springfox
 
 13. Se realizaron pruebas de integración y con gradle se integró sonarq y jacocotesReport, para ver el porcentaje de cobertura. La forma de ejecutarlos es en el terminal, escribiendo gradle y luego el step que se desee.


Realizado por Parmenia Maldonado
