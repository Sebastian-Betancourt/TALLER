import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class PantalladeRegistrar {
    private JTextField nombre;
    private JTextField apellido;
    private JButton Registrar;
    private JButton Volver;
    public JPanel PnatallaRegistrar;
    private JLabel Nombre;
    private JLabel Apellido;
    private JTextField edad;
    private JTextField correo;
    private JLabel Edad;
    private JLabel Correo;

    public PantalladeRegistrar() {
        Volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Menu");
                frame.setContentPane(new Menu().Menu); // Aquí asumo que tienes una clase Menu con su panel
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(1024, 768);
                frame.setPreferredSize(new Dimension(1024, 768));
                frame.pack();
                frame.setVisible(true);

                // Cerrar la ventana actual
                ((JFrame) SwingUtilities.getWindowAncestor(PnatallaRegistrar)).dispose();
            }
        });

        Registrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener los datos ingresados por el usuario
                String nombreUsuario = nombre.getText();
                String apellidoUsuario = apellido.getText();
                String correoUsuario = correo.getText();
                String edadUsuarioStr = edad.getText();

                // Validar la edad
                try {
                    int edadUsuario = Integer.parseInt(edadUsuarioStr);
                    if (edadUsuario >= 18 && edadUsuario <= 40) {
                        // Si la edad es válida, registrar en la base de datos
                        registrarUsuarioEnBaseDeDatos(nombreUsuario, apellidoUsuario, edadUsuario, correoUsuario);
                    } else {
                        // Mostrar mensaje de error si la edad no es válida
                        JOptionPane.showMessageDialog(PnatallaRegistrar,
                                "La edad debe estar entre 18 y 40 años.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    // Si la edad no es un número válido
                    JOptionPane.showMessageDialog(PnatallaRegistrar,
                            "Por favor, ingrese una edad válida.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Método para registrar al usuario en la base de datos
    private void registrarUsuarioEnBaseDeDatos(String nombre, String apellido, int edad, String correo) {
        String URL = "jdbc:mysql://localhost:3306/concurso";
        String USER = "root";
        String PASSWORD = "1234";

        String query = "INSERT INTO inscripciones (nombre, apellido, edad, correo) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, nombre);
            statement.setString(2, apellido);
            statement.setInt(3, edad);
            statement.setString(4, correo);

            // Ejecutar la inserción en la base de datos
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                // Si la inserción fue exitosa
                JOptionPane.showMessageDialog(PnatallaRegistrar,
                        "Registro exitoso.",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Si algo salió mal con la inserción
                JOptionPane.showMessageDialog(PnatallaRegistrar,
                        "No se pudo registrar el usuario.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(PnatallaRegistrar,
                    "Error al conectar con la base de datos.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
