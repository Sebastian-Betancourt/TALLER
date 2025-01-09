import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PantalladecargarDatos {
    private JTextArea textArea1;
    private JLabel titulo;
    public JPanel RegistroPantalla;
    private JButton Volver;

    public PantalladecargarDatos() {
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
                ((JFrame) SwingUtilities.getWindowAncestor(RegistroPantalla)).dispose();
            }
        });

        // Aquí cargamos los datos de la base de datos al iniciar la pantalla
        cargarDatosInscripciones();

        textArea1.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });
    }

    // Método para cargar los registros de la tabla inscripciones
    private void cargarDatosInscripciones() {
        String URL = "jdbc:mysql://localhost:3306/concurso";
        String USER = "root";
        String PASSWORD = "1234";

        String query = "SELECT * FROM inscripciones";

        // Limpiar el JTextArea antes de mostrar los datos
        textArea1.setText("");

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            // Mostrar los datos en el JTextArea
            StringBuilder sb = new StringBuilder();
            while (resultSet.next()) {
                int cod = resultSet.getInt("cod");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                int edad = resultSet.getInt("edad");
                String correo = resultSet.getString("correo");

                sb.append("Código: ").append(cod).append(", Nombre: ").append(nombre)
                        .append(", Apellido: ").append(apellido).append(", Edad: ").append(edad)
                        .append(", Correo: ").append(correo).append("\n");
            }
            // Mostrar los resultados en el JTextArea
            textArea1.setText(sb.toString());

        } catch (SQLException e) {
            e.printStackTrace();
            textArea1.setText("Error al cargar los datos.");
        }
    }
}
