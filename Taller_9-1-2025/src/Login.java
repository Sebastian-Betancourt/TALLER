import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    private JTextField textField1; // Campo de texto para el nombre del usuario
    private JPasswordField passwordField1; // Campo de texto para la contraseña
    private JButton Login; // Botón para verificar el login
    public JPanel login; // Panel principal de la interfaz
    private JLabel Usuario; // Etiqueta para "Nombre"
    private JLabel Contraseña; // Etiqueta para "Contraseña"
    private JButton Salir; // Botón para salir

    public Login() {
        // Acción al presionar el botón Salir
        Salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Cerrar la aplicación
            }
        });

        // Acción al presionar el botón Login
        Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el nombre y la contraseña ingresados
                String nombre = textField1.getText();
                String contrasena = new String(passwordField1.getPassword()); // Convertir la contraseña a String

                // Verificar las credenciales
                if (verificarCredenciales(nombre, contrasena)) {
                    // Si las credenciales son correctas, mostrar la siguiente ventana (ejemplo: menú)
                    JFrame frame = new JFrame("Menu");
                    frame.setContentPane(new Menu().Menu); // Aquí asumo que tienes una clase Menu con su panel
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(1024, 768);
                    frame.setPreferredSize(new Dimension(1024, 768));
                    frame.pack();
                    frame.setVisible(true);

                    // Cerrar la ventana de login
                    ((JFrame) SwingUtilities.getWindowAncestor(login)).dispose();
                } else {
                    // Si las credenciales son incorrectas, mostrar un mensaje de error
                    JOptionPane.showMessageDialog(login, "Nombre o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Método para verificar las credenciales en la base de datos
    private boolean verificarCredenciales(String nombre, String contrasena) {
        boolean esValido = false;

        // Datos de conexión a la base de datos
        String URL = "jdbc:mysql://localhost:3306/concurso";  // Asegúrate de que la base de datos esté configurada correctamente
        String USER = "root";  // Cambia esto según tu configuración
        String PASSWORD = "1234";  // Cambia esto según tu configuración

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Consulta SQL para verificar si el nombre y la contraseña coinciden
            String query = "SELECT * FROM usuarios WHERE nombre = ? AND pass = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, nombre);  // Usar el campo nombre
            stmt.setString(2, contrasena);  // Usar el campo pass

            // Ejecutar la consulta
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                esValido = true; // Si hay resultados, las credenciales son correctas
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Imprimir el error si ocurre
        }

        return esValido;
    }
}
