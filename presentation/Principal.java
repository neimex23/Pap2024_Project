package ayudemonos;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Principal {

    private static JFrame ventanaP;
    private static JDesktopPane desktopPane;

    public static void main(String[] args) {
        // Inicializar y configurar el JFrame
        ventanaP = new JFrame("Mi Formulario");
        ventanaP.setBounds(200, 200, 900, 600);
        ventanaP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear el JDesktopPane para manejar JInternalFrames
        desktopPane = new JDesktopPane();
        ventanaP.setContentPane(desktopPane);

        // Crear la barra de menú
        JMenuBar menuBar = new JMenuBar();
        ventanaP.setJMenuBar(menuBar);

        // Crear el menú "Agregar Usuario"
        JMenu mnAgregarBeneficiario = new JMenu("Agregar Usuario");
        menuBar.add(mnAgregarBeneficiario);

        // Crear el menú "Agregar Donacion"
        JMenu mnAgregarDonacion = new JMenu("Agregar Donacion");
        menuBar.add(mnAgregarDonacion);

        // Crear y añadir el elemento de menú "Beneficiario"
        JMenuItem mntmBeneficiario = new JMenuItem("Beneficiario");
        mntmBeneficiario.addActionListener((ActionEvent arg0) -> {
            mostrarFormularioBeneficiario("Agregar Beneficiario");
        });
        mnAgregarBeneficiario.add(mntmBeneficiario);

        // Crear y añadir el elemento de menú "Repartidor"
        JMenuItem mntmRepartidor = new JMenuItem("Repartidor");
        mntmRepartidor.addActionListener((ActionEvent arg0) -> {
            mostrarFormularioRepartidor("Agregar Repartidor");
        });
        mnAgregarBeneficiario.add(mntmRepartidor);

        // Crear y añadir el elemento de menú "Alimento"
        JMenuItem mntmAlimento = new JMenuItem("Alimento");
        mntmAlimento.addActionListener((ActionEvent arg0) -> {
            mostrarFormularioAlimento("Agregar Alimento");
        });
        mnAgregarDonacion.add(mntmAlimento);

        // Crear y añadir el elemento de menú "Artículo"
        JMenuItem mntmArticulo = new JMenuItem("Artículo");
        mntmArticulo.addActionListener((ActionEvent arg0) -> {
            mostrarFormularioArticulo("Agregar Artículo");
        });
        mnAgregarDonacion.add(mntmArticulo);

        // Mostrar el cuadro de diálogo de inicio de sesión
        mostrarDialogoLogin();

        // Hacer visible el JFrame
        ventanaP.setVisible(true);
    }

    private static boolean mostrarDialogoLogin() {
        // Crear un JDialog para el formulario de login
        JDialog loginDialog = new JDialog(ventanaP, "Inicio de Sesión", true);
        loginDialog.setSize(300, 100);
        loginDialog.setLayout(new GridLayout(3, 2));

        // Etiquetas y campos de texto
        JLabel lblUsuario = new JLabel("Usuario:");
        JTextField txtUsuario = new JTextField();

        JLabel lblContrasena = new JLabel("Contraseña:");
        JPasswordField txtContrasena = new JPasswordField();

        // Botón para iniciar sesión
        JButton btnLogin = new JButton("Iniciar Sesión");
        btnLogin.addActionListener((ActionEvent e) -> {
            // Validar las credenciales (puedes reemplazar esto con tu lógica de validación)
            String usuario = txtUsuario.getText();
            String contrasena = new String(txtContrasena.getPassword());

            if (usuario.equals("admin") && contrasena.equals("admin")) {
                // Cerrar el cuadro de diálogo si las credenciales son correctas
                loginDialog.dispose();
            } else {
                // Mostrar un mensaje de error si las credenciales son incorrectas
                JOptionPane.showMessageDialog(loginDialog, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Añadir los componentes al JDialog
        loginDialog.add(lblUsuario);
        loginDialog.add(txtUsuario);
        loginDialog.add(lblContrasena);
        loginDialog.add(txtContrasena);
        loginDialog.add(new JLabel());  // Espacio en blanco
        loginDialog.add(btnLogin);

        // Centrar el diálogo en relación con el frame
        loginDialog.setLocationRelativeTo(ventanaP);

        // Mostrar el diálogo
        loginDialog.setVisible(true);

        return true;
    }

    private static void mostrarFormularioBeneficiario(String titulo) {
        // Crear un JInternalFrame para el formulario
        JInternalFrame internalFrame = new JInternalFrame(titulo, true, true, true, true);
        internalFrame.setSize(400, 300);
        internalFrame.setLayout(new GridLayout(8, 2));
        internalFrame.setLocation(50, 50);

        // Etiquetas y campos de texto
        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();

        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();

        JLabel lblDirecc = new JLabel("Dirección:");
        JTextField txtDirecc = new JTextField();

        JLabel lblFechaNa = new JLabel("Fecha nacimiento:");
        JTextField txtFechaNa = new JTextField();

        JLabel lblEstado = new JLabel("Estado:");
        JTextField txtEstado = new JTextField();

        JLabel lblBarrio = new JLabel("Barrio:");
        JTextField txtBarrio = new JTextField();

        JLabel lblCedula = new JLabel("Cédula:");
        JTextField txtCedula = new JTextField();

        // Botón para guardar
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener((ActionEvent e) -> {
            try {
                // Validar el email
                validarEmail(txtEmail.getText());

                // Guardar la información
                System.out.println("Nombre: " + txtNombre.getText());
                System.out.println("Email: " + txtEmail.getText());
                System.out.println("Cédula: " + txtCedula.getText());
                System.out.println("Dirección: " + txtDirecc.getText());
                System.out.println("Fecha de Nacimiento: " + txtFechaNa.getText());
                System.out.println("Estado: " + txtEstado.getText());
                System.out.println("Barrio: " + txtBarrio.getText());

                JOptionPane.showMessageDialog(null, "Datos guardados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                // Esperar un poco antes de cerrar el frame
                Thread.sleep(500);

                // Cerrar el frame después de guardar
                internalFrame.dispose();
            } catch (InvalidEmailException ema) {
                JOptionPane.showMessageDialog(internalFrame, "Ocurrió un error: " + ema.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        });

        // Botón para cancelar
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener((ActionEvent e) -> {
            internalFrame.dispose();
        });

        // Añadir los componentes al JInternalFrame
        internalFrame.add(lblNombre);
        internalFrame.add(txtNombre);
        internalFrame.add(lblCedula);
        internalFrame.add(txtCedula);
        internalFrame.add(lblEmail);
        internalFrame.add(txtEmail);
        internalFrame.add(lblFechaNa);
        internalFrame.add(txtFechaNa);
        internalFrame.add(lblEstado);
        internalFrame.add(txtEstado);
        internalFrame.add(lblDirecc);
        internalFrame.add(txtDirecc);
        internalFrame.add(lblBarrio);
        internalFrame.add(txtBarrio);
        internalFrame.add(btnGuardar);
        internalFrame.add(btnCancelar);

        // Añadir el JInternalFrame al JDesktopPane
        desktopPane.add(internalFrame);
        internalFrame.setVisible(true);
    }

    private static void mostrarFormularioRepartidor(String titulo) {
        // Crear un JInternalFrame para el formulario
        JInternalFrame internalFrame = new JInternalFrame(titulo, true, true, true, true);
        internalFrame.setSize(300, 150);
        internalFrame.setLayout(new GridLayout(4, 2));
        internalFrame.setLocation(100, 100);

        // Etiquetas y campos de texto
        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();

        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();

        JLabel lblLicencia = new JLabel("Licencia:");
        JTextField txtLicencia = new JTextField();

        // Botón para guardar
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener((ActionEvent e) -> {
            try {
                // Validar el email
                validarEmail(txtEmail.getText());

                // Guardar la información
                System.out.println("Nombre: " + txtNombre.getText());
                System.out.println("Email: " + txtEmail.getText());
                System.out.println("Licencia: " + txtLicencia.getText());

                JOptionPane.showMessageDialog(null, "Datos guardados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                // Cerrar el frame después de guardar
                internalFrame.dispose();
            } catch (InvalidEmailException ema) {
                JOptionPane.showMessageDialog(internalFrame, "Ocurrió un error: " + ema.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Botón para cancelar
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener((ActionEvent e) -> {
            internalFrame.dispose();
        });

        // Añadir los componentes al JInternalFrame
        internalFrame.add(lblNombre);
        internalFrame.add(txtNombre);
        internalFrame.add(lblEmail);
        internalFrame.add(txtEmail);
        internalFrame.add(lblLicencia);
        internalFrame.add(txtLicencia);
        internalFrame.add(btnGuardar);
        internalFrame.add(btnCancelar);

        // Añadir el JInternalFrame al JDesktopPane
        desktopPane.add(internalFrame);
        internalFrame.setVisible(true);
    }

private static void mostrarFormularioAlimento(String titulo) {
    // Crear un JInternalFrame para el formulario
    JInternalFrame internalFrame = new JInternalFrame(titulo, true, true, true, true);
    internalFrame.setSize(400, 150);
    internalFrame.setLayout(new GridLayout(3, 1));
    internalFrame.setLocation(50, 50);

    // Etiquetas y campos de texto
    JLabel lblDescripcion = new JLabel("Descripción:");
    JTextField txtDescripcion = new JTextField();

    JLabel lblCantElem = new JLabel("Cantidad de elementos:");
    JTextField txtCantElem = new JTextField();

    // Botón para guardar
    JButton btnGuardar = new JButton("Guardar");
    btnGuardar.addActionListener((ActionEvent e) -> {
        try {
            // Aquí puedes manejar el guardado de la información ingresada
            String descripcion = txtDescripcion.getText();
            String cantElem = txtCantElem.getText();

            // Intentar convertir la cantidad de elementos a un número entero
            int cantidad = Integer.parseInt(cantElem);

            // Si la conversión es exitosa, imprimir los datos en la consola
            System.out.println("Descripción: " + descripcion);
            System.out.println("Cantidad de elementos: " + cantidad);

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(internalFrame, "Datos guardados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // Esperar un poco antes de cerrar el frame para asegurarse de que el mensaje se muestre
            try {
                Thread.sleep(500); // 0.5 segundos
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            // Cerrar el frame después de guardar
            internalFrame.dispose();

        } catch (NumberFormatException nfe) {
            // Capturar la excepción si la conversión falla y mostrar un mensaje de error
            JOptionPane.showMessageDialog(internalFrame, "La cantidad de elementos debe ser un valor numérico.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    });
        // Botón para cancelar
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener((ActionEvent e) -> {
            internalFrame.dispose();
        });

        // Añadir los componentes al JInternalFrame
        internalFrame.add(lblCantElem);
        internalFrame.add(txtCantElem);
        internalFrame.add(lblDescripcion);
        internalFrame.add(txtDescripcion);
        internalFrame.add(btnGuardar);
        internalFrame.add(btnCancelar);

        // Añadir el JInternalFrame al JDesktopPane
        desktopPane.add(internalFrame);
        internalFrame.setVisible(true);
    }

    private static void mostrarFormularioArticulo(String titulo) {
        // Crear un JInternalFrame para el formulario
        JInternalFrame internalFrame = new JInternalFrame(titulo, true, true, true, true);
        internalFrame.setSize(400, 300);
        internalFrame.setLayout(new GridLayout(8, 2));
        internalFrame.setLocation(50, 50);

        // Etiquetas y campos de texto

        JLabel lblDescripcion = new JLabel("Descripción:");
        JTextField txtDescripcion = new JTextField();

        JLabel lblPeso = new JLabel("Peso:");
        JTextField txtPeso = new JTextField();

        JLabel lblDimension = new JLabel("Dimensión:");
        JTextField txtDimension = new JTextField();

        // Botón para guardar
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener((ActionEvent e) -> {
            try {

                // Convertir y guardar la información
                double peso = Double.parseDouble(txtPeso.getText());
                double dimension = Double.parseDouble(txtDimension.getText());


                System.out.println("Descripción: " + txtDescripcion.getText());
                System.out.println("Peso: " + peso);
                System.out.println("Dimensión: " + dimension);

                JOptionPane.showMessageDialog(null, "Datos guardados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                // Cerrar el frame después de guardar
                internalFrame.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(internalFrame, "Ocurrió un error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Botón para cancelar
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener((ActionEvent e) -> {
            internalFrame.dispose();
        });

        // Añadir los componentes al JInternalFrame
        internalFrame.add(lblDescripcion);
        internalFrame.add(txtDescripcion);
        internalFrame.add(lblPeso);
        internalFrame.add(txtPeso);
        internalFrame.add(lblDimension);
        internalFrame.add(txtDimension);
        internalFrame.add(btnGuardar);
        internalFrame.add(btnCancelar);

        // Añadir el JInternalFrame al JDesktopPane
        desktopPane.add(internalFrame);
        internalFrame.setVisible(true);
    }

    private static void validarEmail(String email) throws InvalidEmailException {
        // Patrón de validación de email
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new InvalidEmailException("Email inválido.");
        }
    }


    static class InvalidEmailException extends Exception {
        public InvalidEmailException(String message) {
            super(message);
        }
    }

}
