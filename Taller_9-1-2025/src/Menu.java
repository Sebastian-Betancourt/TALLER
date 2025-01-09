import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    private JButton Cargar;
    private JButton Registrar;
    public JPanel Menu;
    private JButton Salir;
    private JButton Ingresar;

    public Menu() {
        Ingresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

                // Cerrar la ventana de login
                ((JFrame) SwingUtilities.getWindowAncestor(Menu)).dispose();
            }
        });
        Salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Cerrar la aplicación
            }
        });
        Cargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Ventana de Login
                JFrame frame = new JFrame("Login");
                // Establecer el panel principal de la clase login como el contenido de la ventana
                frame.setContentPane(new PantalladecargarDatos().RegistroPantalla);
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
                ((JFrame) SwingUtilities.getWindowAncestor(Menu)).dispose();
            }
        });
        Registrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Ventana de Login
                JFrame frame = new JFrame("Login");
                // Establecer el panel principal de la clase login como el contenido de la ventana
                frame.setContentPane(new PantalladeRegistrar().PnatallaRegistrar);
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
                ((JFrame) SwingUtilities.getWindowAncestor(Menu)).dispose();
            }
        });
    }
}
