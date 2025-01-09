//Import para la conexion
import java.sql.Connection;  // Permite manejar conexiones con bases de datos
import java.sql.DriverManager;  // Permite gestionar los controladores de base de datos
import java.sql.SQLException;

//Utilizar select
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//Import la GUI
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        String URL = "jdbc:mysql://localhost:3306/concurso";
        String USER = "root";
        String PASSWORD = "1234";

        String query="SELECT * FROM usuarios";


        try(Connection cone=DriverManager.getConnection(URL,USER,PASSWORD)){
        PreparedStatement statement=cone.prepareStatement(query);
        ResultSet resultSet=statement.executeQuery();




            System.out.println("Conectado");
            while(resultSet.next()){
                int id =resultSet.getInt("id");
                String nombre=resultSet.getString("nombre");
                String correo=resultSet.getString("correo");
                String pass=resultSet.getString("pass");
                System.out.println("id "+id+" nombre "+nombre+" correo "+correo+" pass "+pass);

            }

        }
        catch (Exception e){
            Exception e1=e;
            e1.printStackTrace();
        }
        //Ventana de Login
        JFrame frame = new JFrame("Login");
        // Establecer el panel principal de la clase login como el contenido de la ventana
        frame.setContentPane(new Login().login);
        // Establecer el comportamiento de cierre de la ventana: salir al cerrar la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Establecer el tamaño de la ventana a 1024x768 píxeles
        frame.setSize(1024, 768);
        // Establecer el tamaño preferido de la ventana, útil para redimensionar automáticamente
        frame.setPreferredSize(new Dimension(1024, 768));
        // Ajustar el tamaño del frame según el tamaño preferido
        frame.pack();
        // Hacer la ventana visible
        frame.setVisible(true);
    }

}