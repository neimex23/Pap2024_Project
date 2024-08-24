/* Leer antes de programar.
Controlador: Es responsable de la lógica de negocio, que incluye la validación
de datos, las reglas de negocio, y la comunicación entre la interfaz de usuario 
y el modelo de datos. La verificación de la unicidad de los datos es una regla 
de negocio, por lo que debe ser manejada en el controlador.

Interfaz gráfica (GUI): Es responsable de la presentación de datos y la 
interacción con el usuario. Su función principal es mostrar la información y 
recoger la entrada del usuario, no realizar verificaciones de lógica compleja.

Si en el futuro decides cambiar la interfaz gráfica, no tendrás que preocuparte 
por mover la lógica de verificación de unicidad si está bien encapsulada en el 
controlador.
Centralizar la lógica en el controlador facilita la detección de errores y la 
implementación de cambios en las reglas de negocio.
 */
package ayudemonos;

import classes.*;
import dtClasses.*;
import Enums.*;
import handlers.*;
import interfaces.*;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.swing.*;

public class Principal {

    private static JFrame ventanaP;
    private static JDesktopPane desktopPane;
    private static IControlador icontrolador = Fabrica.getInstancia().getIControlador();

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
        internalFrame.setLayout(new GridLayout(10, 2));
        internalFrame.setLocation(50, 50);

        // Etiquetas y campos de texto
        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();

        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();

        JLabel lblDirecc = new JLabel("Dirección:");
        JTextField txtDirecc = new JTextField();

        // Configuración del JSpinner para seleccionar el día, mes y año
        Calendar calendar = Calendar.getInstance();
        JSpinner spnDia = new JSpinner(new SpinnerNumberModel(calendar.get(Calendar.DAY_OF_MONTH), 1, 31, 1));
        JSpinner spnMes = new JSpinner(new SpinnerNumberModel(calendar.get(Calendar.MONTH) + 1, 1, 12, 1));
        JSpinner spnAno = new JSpinner(new SpinnerNumberModel(calendar.get(Calendar.YEAR), 1900, 2100, 1));

        JLabel lblEspaciador = new JLabel();
        JLabel lblTitulo = new JLabel("Fecha de nacimiento:");

        JLabel lblFechaNa = new JLabel("Dia:");
        JTextField txtFechaNa = new JTextField();

        JLabel lblEstado = new JLabel("Estado:");
        //    JTextField txtEstado = new JTextField();
        JComboBox<String> combo0 = new JComboBox<String>();
        combo0.setBounds(5, 5, 5, 5);
        combo0.addItem("Activo");
        combo0.addItem("Suspendido");

        JComboBox<String> combo1 = new JComboBox<String>();
        combo1.setBounds(5, 5, 5, 5);
        combo1.addItem("Centro");
        combo1.addItem("Ciudad vieja");
        combo1.addItem("Cordon");
        combo1.addItem("Palermo");
        combo1.addItem("Parque Rodo");

        JLabel lblBarrio = new JLabel("Barrio:");
        JTextField txtBarrio = new JTextField();

        // Botón para guardar
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener((ActionEvent e) -> {
            try {
                // Validar el email
                validarEmail(txtEmail.getText());
                int cantBeneficiario = 0;
                cantBeneficiario=icontrolador.conGetCantBeneficiarios(); // Obtengo la cantidad de Beneficiaros registrados
                if(cantBeneficiario>=50){ // Si se alzanzo el limite de usuarios se manda mensaje de error
                    JOptionPane.showMessageDialog(null, "Se ha alcanzado el limite de Beneficiarios", "Error", JOptionPane.INFORMATION_MESSAGE);
                }else { // Si hay menos de 50 Beneficiaros se procede a ingresar uno nuevo
                    if (icontrolador.existeEmail(txtEmail.getText())) { // Si existe un usuario con el mismo mail
                        JOptionPane.showMessageDialog(null, "Ya existe un usario registrado con este mail", "Error", JOptionPane.INFORMATION_MESSAGE);
                    } else {// Si no existe el mail, se crea el usuario
                        // Guardar la información
                        // Capturar la fecha de nacimiento desde los JSpinner
                        int dia = (int) spnDia.getValue();
                        int mes = (int) spnMes.getValue();
                        int anio = (int) spnAno.getValue();
                        DtFechaHora fechaNacimiento = new DtFechaHora(dia, mes, anio, 0, 0);

                        // Convertir el estado y barrio seleccionados a los correspondientes Enum
                        EnumEstadoBeneficiario estado = EnumEstadoBeneficiario.valueOf(combo0.getSelectedItem().toString().toUpperCase());
                        EnumBarrio barrio = EnumBarrio.valueOf(combo1.getSelectedItem().toString().toUpperCase().replace(" ", "_"));

                        //Crear beneficirio con los datos obtenidos
                        icontrolador.altaBeneficiario(txtNombre.getText(), txtEmail.getText(), txtDirecc.getText(), fechaNacimiento, estado, barrio);
                        JOptionPane.showMessageDialog(null, "Datos guardados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                // Esperar un poco antes de cerrar el frame para dar tiempo a mostrar el mensaje de finalización
                Thread.sleep(500);

                // Cerrar el frame después de guardar
                internalFrame.dispose();
                //Captura el error de formato incorrecto de correo
            } catch (InvalidEmailException ema) {
                JOptionPane.showMessageDialog(internalFrame, "Ocurrió un error: " + ema.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                //Captura el error generico
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
        internalFrame.add(lblEmail);
        internalFrame.add(txtEmail);
        internalFrame.add(lblTitulo);
        internalFrame.add(lblEspaciador);
        internalFrame.add(lblFechaNa);
        internalFrame.add(spnDia);
        internalFrame.add(new JLabel("Mes:"));
        internalFrame.add(spnMes);
        internalFrame.add(new JLabel("Año:"));
        internalFrame.add(spnAno);
        internalFrame.add(lblEstado);
        internalFrame.add(combo0);
        internalFrame.add(lblDirecc);
        internalFrame.add(txtDirecc);
        internalFrame.add(lblBarrio);
        internalFrame.add(combo1);
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

                int cantRepartidor = 0;
                cantRepartidor = icontrolador.conGetCantRepartidores(); // Obtengo la cantidad de Repartidores registrados
                if (cantRepartidor >= 5) { // Si se alzanzo el limite de usuarios se manda mensaje de error
                    JOptionPane.showMessageDialog(null, "Se ha alcanzado el limite de Repartidores", "Error", JOptionPane.INFORMATION_MESSAGE);
                } else {// Si hay menos de 5 Repartidores se procede a ingresar uno nuevo
                    if (icontrolador.existeEmail(txtEmail.getText())) { // Si existe un usuario con el mismo mail
                        JOptionPane.showMessageDialog(null, "Ya existe un usario registrado con el mail ingresado", "Error", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        if (icontrolador.existeLicencia(txtLicencia.getText())) { // Si existe un usuario con la misma Licencia
                            JOptionPane.showMessageDialog(null, "Ya existe un usario registrado con la licencia ingresada", "Error", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            //Crear beneficirio con los datos obtenidos
                            icontrolador.altaRepartidor(txtNombre.getText(), txtEmail.getText(), txtLicencia.getText());
                            JOptionPane.showMessageDialog(null, "Datos guardados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
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