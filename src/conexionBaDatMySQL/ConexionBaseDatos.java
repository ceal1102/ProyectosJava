/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexionBaDatMySQL;

import java.sql.*;
import java.util.ArrayList;
//import org.hsqldb.persist.Logger;

/**
 *
 * @author cesar
 */
public class ConexionBaseDatos {

    private final String driver = "com.mysql.jdbc.Driver";

    public ConexionBaseDatos() {
    }

    // CREATE de Crud
    public String altas(int codigo, String nombre, int id_localizacion, int id_manager) {
        String mensaje = "";

        try {
            // Paso 1: Cargar el driver de la base de datos 
            Class.forName(driver);

            // Paso 2: Crear la conexión con objeto de la clase Connection a la base de datos especificando la URL, el usuario y la contraseña
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/76421541Q", "cesar", "123");

            // Definición de la consulta SQL que inserta un nuevo registro en la tabla Departamentos
            String sql = "INSERT INTO Departamentos VALUES (?,?,?,?)";

            // Paso 3: Preparar la declaración SQL para ejecutar la consulta
            PreparedStatement sentencia = conexion.prepareStatement(sql);

            // Establecer parámetros de la consulta 
            sentencia.setInt(1, codigo);
            sentencia.setString(2, nombre);
            sentencia.setInt(3, id_localizacion);
            sentencia.setInt(4, id_manager);

            // Paso 4: Ejecutar la sentencia SQL que realiza la inserción de datos en la base de datos
            sentencia.executeUpdate();

            // Paso 5: Cerrar la conexión a la base de datos para liberar recursos
            conexion.close();

            //cerrar el statement
            sentencia.close();

            // retornar el mensaje de éxito si la inserción es correcta
            mensaje = "Alta realizada correctamente";

        } catch (Exception e) {
            // En caso de error, capturar la excepción y devolver un mensaje con el error
            return "Ha ocurrido un error: " + e.getMessage();
        }
        return mensaje;
    }

    // Update de CRUD
    public String modificar(int codigo, String nombre, int id_localizacion, int id_manager) {
        String mensaje = "";

        try {
            // Paso 1: Cargar el driver de la base de datos
            Class.forName(driver);

            // Paso 2: Establecer la conexión con la base de datos utilizando la URL, el usuario y la contraseña 
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/76421541Q", "cesar", "123");

            // Paso 3: Preparar la consulta SQL para actualizar los datos 
            String query = "UPDATE Departamentos SET nombre = ?, id_localizacion = ?, id_manager = ? WHERE codigo = ?";

            // Configuración de los parámetros de la consulta. Estos valores son sustituidos en las '?' de la consulta SQL según el orden
            PreparedStatement sentencia = conexion.prepareStatement(query);
            sentencia.setString(1, nombre);
            sentencia.setInt(2, id_localizacion);
            sentencia.setInt(3, id_manager);
            sentencia.setInt(4, codigo);

            // Paso 4: Ejecutar la sentencia para actualizar los datos en la base de datos
            sentencia.executeUpdate();

            // Paso 5: Cerrar la conexión a la base de datos una vez que la actualización ha sido completada para liberar recursos
            conexion.close();

            // cerrar el statement
            sentencia.close();
            
            // Si todo sale bien, retorna el mensaje de éxito
            mensaje = "Datos modificados correctamente";
            
        } catch (Exception e) {
            // Captura de cualquier excepción que ocurra durante el proceso de conexión o actualización y devolver un mensaje de error
            return "Ha ocurrido un error durante la modificación: " + e.getMessage();
        }
        return mensaje;
    }

    // D de CRUD
    public String borrar(int codigo) {
        String mensaje = "";
        try {
            // Paso 1: Cargar el driver de la base de datos
            Class.forName("com.mysql.jdbc.Driver");

            // Paso 2: Establecer la conexión con la base de datos utilizando la URL, el usuario y la contraseña 
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/76421541Q", "cesar", "123");

            // Paso 3: Preparar la consulta SQL para eliminar el registro con el código especificado de la tabla Departamentos
            String query = "DELETE FROM Departamentos WHERE codigo = ?";
            PreparedStatement sentencia = conexion.prepareStatement(query);

            // Configuración del parámetro de la consulta, que es el código del departamento a eliminar
            sentencia.setInt(1, codigo);            

            // Paso 4: Ejecutar la sentencia para eliminar el registro de la base de datos
            sentencia.executeUpdate();

            // Paso 5: Cerrar la conexión a la base de datos una vez que la eliminación ha sido completada para liberar recursos
            conexion.close();

            //cerrar el statement
            sentencia.close();

            // Si la eliminación se realiza correctamente, retorna mensaje de éxito
            mensaje = "Datos borrados correctamente";
        } catch (Exception e) {
            // Captura de cualquier excepción que ocurra durante el proceso de conexión o eliminación y devolver un mensaje de error
            return "Ocurrió un error durante el borrado: " + e.getMessage();
        }
        return mensaje;
    }

    // Método que retorna un arraylist de objetos Departamento recuperados de la base de datos
    public ArrayList <Departamento> mostrar() {
        ArrayList<Departamento> respuesta = new ArrayList<>();
        try {
            // Paso 1: Cargar el driver de la base de datos 
            Class.forName(driver);

            // Paso 2: Establecer conexión con la base de datos especificando la URL, el usuario y la contraseña.
            Connection conex = DriverManager.getConnection("jdbc:mysql://localhost/76421541Q", "cesar", "123");

            // Paso 3: Crear un objeto Statement para poder ejecutar consultas SQL
            Statement sentencia = conex.createStatement();

            // Definición de la consulta SQL para seleccionar todos los registros de la tabla Departamentos
            String query = "SELECT * from Departamentos";

            // Paso 4: Ejecutar la consulta SQL y almacenar los resultados en un objeto ResultSet
            ResultSet resultado = sentencia.executeQuery(query);

            // Paso 5: Procesar cada registro del ResultSet, creando y añadiendo objetos Departamento al arraylist
            while (resultado.next()) {

                Departamento dep = new Departamento();

                dep.setCodigo(resultado.getInt(1));
                dep.setNombre(resultado.getString(2));
                dep.setIdLocalizacion(resultado.getInt(3));
                dep.setIdManager(resultado.getInt(4));

                respuesta.add(dep);

            }

            // Paso 6: Cerrar el Statement y la conexión para liberar recursos
            sentencia.close();
            conex.close();
        } catch (Exception e) {
            // Capturar y retornar cualquier error que ocurra durante el proceso de conexión o consulta
            System.out.println("ocurrio un error");
        }
        return respuesta;
    }
    
}
