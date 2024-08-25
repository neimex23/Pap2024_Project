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

import dtClasses.*;
import Enums.*;
import interfaces.*;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.swing.*;

//Damaso Testings
import handlers.*;
import classes.*;

public class Principal {

    private static JFrame ventanaP;
    private static JDesktopPane desktopPane;
    private static IControlador icontrolador = Fabrica.getInstancia().getIControlador();

    public static void main(String[] args) {

        // Inicializar y configurar ventana que es un objeto JFrame
        ventanaP = new JFrame("Ayudemos");
        ventanaP.setBounds(200, 200, 900, 600); // coordenada x, y luego ancho, alto
        ventanaP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // alternativas JFrame.DO_NOTHING_ON_CLOSE, JFrame.HIDE_ON_CLOSE, JFrame.DISPOSE_ON_CLOSE

        // Crear el JDesktopPane para manejar JInternalFrames
        desktopPane = new JDesktopPane();
        ventanaP.setContentPane(desktopPane); // Esto significa que todos los componentes que agregues al desktopPane aparecerán dentro de la ventana ventanaP.

        // Crear la barra de menú
        JMenuBar menuBar = new JMenuBar();
        ventanaP.setJMenuBar(menuBar);

        // Crear el menú "Agregar Usuario"
        JMenu mnAgregarBeneficiario = new JMenu("Agregar Usuario");
        menuBar.add(mnAgregarBeneficiario);

        // Crear el menú "Agregar Donacion"
        JMenu mnAgregarDonacion = new JMenu("Agregar Donacion");
        menuBar.add(mnAgregarDonacion);

        // Crear el menú "Agregar Donacion"
        JMenu mnDistribucion = new JMenu("Distribucion");
        menuBar.add(mnDistribucion);

        // Crear y añadir el elemento de menú "Beneficiario"
        JMenuItem mntmBeneficiario = new JMenuItem("Beneficiario");
        mntmBeneficiario.addActionListener((ActionEvent arg0) -> {  //escucha de eventos al si ocurre un evento la expresión lambda (ActionEvent arg0) -> {mostrarFormularioBeneficiario("Agregar Beneficiario");}
            mostrarFormularioBeneficiario("Agregar Beneficiario");  //ejecuta llamada a procedimeinto mostrarFormularioBeneficiario("Agregar Beneficiario")
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

        // Crear y añadir el elemento de menú "Distribucion"
        JMenuItem mntmDistribucion = new JMenuItem("Alta distribucion");
        mntmDistribucion.addActionListener((ActionEvent arg0) -> {
            mostrarFormularioCrearDistribucion("Agregar Distribucion");
        });
        mnDistribucion.add(mntmDistribucion);

        // Crear y añadir el elemento de menú "Distribucion"
        JMenuItem mntmModDistribucion = new JMenuItem("Modificar distribucion");
        mntmModDistribucion.addActionListener((ActionEvent arg0) -> {
            mostrarFormularioModDistribucion("Modificar Distribucion");
        });
        mnDistribucion.add(mntmModDistribucion);

        // Crear y añadir el elemento de menú "Distribucion"
        JMenuItem mntmModListarDistribucion = new JMenuItem("Listar distribuciones");
        mntmModDistribucion.addActionListener((ActionEvent arg0) -> {
            mostrarFormularioDistribucion("Listar Distribucion");
        });
        mnDistribucion.add(mntmModDistribucion);

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

                //Beneficiario(String nombre,String email, String direccion, DtFechaHora fechaNacimiento, EnumEstadoBeneficiario estado, EnumBarrio barrio)
                // Guardar la información
                // Capturar la fecha de nacimiento desde los JSpinner
                int dia = (int) spnDia.getValue();
                int mes = (int) spnMes.getValue();
                int anio = (int) spnAno.getValue();
                DtFechaHora fechaNacimiento = new DtFechaHora(dia, mes, anio, 0, 0);
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
                Beneficiario beneficiario = new Beneficiario(txtNombre.getText(), txtEmail.getText(), txtDirecc.getText(), fechaNacimiento, estado, barrio);

                // Pedir al controlador añadir el beneficiario a la lista el controlara la existencia de duplicados
                manejadorBeneficiario.añadirBeneficiario(beneficiario);

                JOptionPane.showMessageDialog(null, "Datos guardados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

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

                // Guardar la información
                //Crear repartidor con los datos obtenidos
                Repartidor repartidor = new Repartidor(txtNombre.getText(), txtEmail.getText(), txtLicencia.getText());

                // Pedir al controlador añadir el beneficiario a la lista el controlara la existencia de duplicados
                manejadorRepartidor.añadirRepartidor(repartidor);

                JOptionPane.showMessageDialog(null, "Datos guardados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

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
        internalFrame.setSize(400, 110);
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
                // Obtiene un id disponible para el alimento
                int id = manejadorAlimento.obtenerMayorId() + 1;
                // Obtner la fecha ya que se le pasa la fecha de hoy y no una ingresada por usuario
                DtFechaHora fechaHoy = obtenerFechaHora();
                // Si la conversión es exitosa, guardar

                Alimento alimento = new Alimento(id, fechaHoy, txtDescripcion.getText(), cantidad);

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
                float peso = Float.parseFloat(txtPeso.getText());
                double peso = Double.parseDouble(txtPeso.getText());
                double dimension = Double.parseDouble(txtDimension.getText());

                int id = manejadorArticulo.obtenerMayorId() + 1;
                DtFechaHora fechaHoy = obtenerFechaHora();
                // Crear un articulo con los datos obtenidos
                Articulo articulo = new Articulo(id, fechaHoy, txtDescripcion.getText(), peso, txtDimension.getText());
                // Agregar el articulo creado
                manejadorArticulo.añadirArticulo(articulo);

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

private static void mostrarFormularioCrearDistribucion(String titulo) {
    // Crear un JInternalFrame para el formulario
    JInternalFrame internalFrame = new JInternalFrame(titulo, true, true, true, true);
    internalFrame.setSize(800, 400);
    internalFrame.setLayout(new BorderLayout());
    internalFrame.setLocation(100, 150);

    // Panel principal donde se verá la información de la distribución
    JPanel panelPrincipal = new JPanel(new GridLayout(7, 2)); // Cambiado a 7 filas para acomodar la fecha de entrega

    // Etiquetas y campos de texto
    JLabel lblFechaDistribucion = new JLabel("Fecha de Distribución:");
    JSpinner spnFechaDistribucion = new JSpinner(new SpinnerDateModel());
    JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spnFechaDistribucion, "dd-MM-yyyy");
    spnFechaDistribucion.setEditor(dateEditor);

    JLabel lblFechaPreparacion = new JLabel("Fecha Preparación:");
    JTextField txtFechaPreparacion = new JTextField();
    txtFechaPreparacion.setEditable(false);

    JLabel lblFechaEntrega = new JLabel("Fecha Entrega:");
    JSpinner spnFechaEntrega = new JSpinner(new SpinnerDateModel());
    JSpinner.DateEditor dateEditorEntrega = new JSpinner.DateEditor(spnFechaEntrega, "dd-MM-yyyy");
    spnFechaEntrega.setEditor(dateEditorEntrega);

    JLabel lblTipo = new JLabel("Tipo de distribución:");
    JTextField txtTipo = new JTextField();
    txtTipo.setEditable(false);

    JLabel lblDescripcion = new JLabel("Descripción:");
    JTextArea txtDescripcion = new JTextArea();
    txtDescripcion.setEditable(false);
    JScrollPane descripcionScroll = new JScrollPane(txtDescripcion);

    panelPrincipal.add(lblFechaDistribucion);
    panelPrincipal.add(spnFechaDistribucion);
    panelPrincipal.add(lblFechaPreparacion);
    panelPrincipal.add(txtFechaPreparacion);
    panelPrincipal.add(lblFechaEntrega);
    panelPrincipal.add(spnFechaEntrega); // Añadido el spinner de fecha de entrega
    panelPrincipal.add(lblTipo);
    panelPrincipal.add(txtTipo);
    panelPrincipal.add(lblDescripcion);
    panelPrincipal.add(descripcionScroll);

    // Panel derecho para beneficiarios y donaciones
    JPanel panelDerecho = new JPanel(new BorderLayout());

    // Panel superior para el spinner de dirección
    JPanel panelDireccion = new JPanel(new GridLayout(1, 2));
    JLabel lblDireccion = new JLabel("Dirección:");
    JSpinner spnDireccion = new JSpinner(); // Inicializa sin datos
    panelDireccion.add(lblDireccion);
    panelDireccion.add(spnDireccion);

    // Mapa para almacenar la relación entre direcciones y beneficiarios
    Map<String, List<Beneficiario>> mapDireccionesBeneficiarios = new HashMap<>();

    // Obtenemos el manejador de beneficiarios
    ManejadorBeneficiario manejadorBeneficiario = ManejadorBeneficiario.getInstancia();
    List<Beneficiario> listaBeneficiarios = manejadorBeneficiario.getListaBeneficiario();
    Set<String> direcciones = new HashSet<>();

    // Cargar direcciones y beneficiarios en el mapa
    for (Beneficiario beneficiario : listaBeneficiarios) {
        String direccion = beneficiario.getDireccion();
        if (direccion != null && !direccion.isEmpty()) {
            direcciones.add(direccion);
            mapDireccionesBeneficiarios
                .computeIfAbsent(direccion, k -> new ArrayList<>())
                .add(beneficiario);
        }
    }

// Configurar el Spinner con las direcciones disponibles
List<String> listaDirecciones = new ArrayList<>(direcciones);

// Asegurarse de que la lista no esté vacía
if (listaDirecciones.isEmpty()) {
    listaDirecciones.add("No hay direcciones disponibles");
    spnDireccion.setEnabled(false);  // Deshabilitar el spinner si no hay direcciones
} else {
    spnDireccion.setEnabled(true);
}

spnDireccion.setModel(new SpinnerListModel(listaDirecciones));


    // Panel central para la lista de beneficiarios
    DefaultListModel<String> listModel = new DefaultListModel<>();
    JList<String> listBeneficiarios = new JList<>(listModel);
    JScrollPane scrollBeneficiarios = new JScrollPane(listBeneficiarios);

    // Panel inferior para detalles del beneficiario seleccionado
    JPanel panelDetallesBeneficiario = new JPanel(new GridLayout(2, 2));
    JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento:");
    JTextField txtFechaNacimiento = new JTextField();
    txtFechaNacimiento.setEditable(false);
    JLabel lblEstado = new JLabel("Estado:");
    JTextField txtEstado = new JTextField();
    txtEstado.setEditable(false);
    panelDetallesBeneficiario.add(lblFechaNacimiento);
    panelDetallesBeneficiario.add(txtFechaNacimiento);
    panelDetallesBeneficiario.add(lblEstado);
    panelDetallesBeneficiario.add(txtEstado);

    // Actualizar la lista de beneficiarios cuando se cambie la dirección
    spnDireccion.addChangeListener(e -> {
        String direccionSeleccionada = (String) spnDireccion.getValue();
        listModel.clear();
        if (direccionSeleccionada != null && mapDireccionesBeneficiarios.containsKey(direccionSeleccionada)) {
            for (Beneficiario beneficiario : mapDireccionesBeneficiarios.get(direccionSeleccionada)) {
                listModel.addElement(beneficiario.getNombre());
            }
        }
    });

    // Mostrar detalles del beneficiario seleccionado al hacer clic en la lista
    listBeneficiarios.addListSelectionListener(e -> {
        if (!e.getValueIsAdjusting()) {
            String beneficiarioSeleccionado = listBeneficiarios.getSelectedValue();
            if (beneficiarioSeleccionado != null) {
                Beneficiario beneficiario = mapDireccionesBeneficiarios.get((String) spnDireccion.getValue())
                                                  .stream()
                                                  .filter(b -> b.getNombre().equals(beneficiarioSeleccionado))
                                                  .findFirst().orElse(null);
                if (beneficiario != null) {
                    txtFechaNacimiento.setText(beneficiario.getFechaNacimiento().toString());
                    txtEstado.setText(beneficiario.getEstado().toString());
                }
            }
        }
    });

    panelDerecho.add(panelDireccion, BorderLayout.NORTH);
    panelDerecho.add(scrollBeneficiarios, BorderLayout.CENTER);
    panelDerecho.add(panelDetallesBeneficiario, BorderLayout.SOUTH);

    // Panel inferior para las donaciones
    JPanel panelDonacion = new JPanel(new GridLayout(2, 2));
    JLabel lblDonacion = new JLabel("Donación:");
    JSpinner spnDonacion = new JSpinner();
    try {
        Donacion[] donacionesDisponibles = getDonacionesDisponibles();
        spnDonacion.setModel(new SpinnerListModel(donacionesDisponibles));
    } catch (IllegalArgumentException ex) {
        JOptionPane.showMessageDialog(internalFrame, "No hay donaciones disponibles.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        spnDonacion.setModel(new SpinnerListModel(new Donacion[]{null}));
    }
    panelDonacion.add(lblDonacion);
    panelDonacion.add(spnDonacion);

    // Botón para registrar
    JButton btnRegistrar = new JButton("Registrar");
    btnRegistrar.addActionListener((ActionEvent e) -> {
        DtFechaHora fechaDistribucion = convertirDateADtFechaHora((Date) spnFechaDistribucion.getValue());
        DtFechaHora fechaEntrega = convertirDateADtFechaHora((Date) spnFechaEntrega.getValue());
        Donacion donacionSeleccionada = (Donacion) spnDonacion.getValue();
        String direccionSeleccionada = (String) spnDireccion.getValue();
        String nombreBeneficiarioSeleccionado = listBeneficiarios.getSelectedValue();

        if (direccionSeleccionada != null && nombreBeneficiarioSeleccionado != null) {
            Beneficiario beneficiarioSeleccionado = mapDireccionesBeneficiarios.get(direccionSeleccionada)
                                                        .stream() // Convierte esa lista de Beneficiarios en un flujo (stream), lo que permite aplicar operaciones de filtrado y búsqueda.
                                                        .filter(b -> b.getNombre().equals(nombreBeneficiarioSeleccionado)) //Filtra los elementos en el flujo, manteniendo solo aquellos Beneficiarios cuyo nombre (b.getNombre()) coincide con el nombre del beneficiario seleccionado (nombreBeneficiarioSeleccionado).
                                                        .findFirst().orElse(null);  // Si findFirst() devuelve un Optional vacío (es decir, no se encontró ningún Beneficiario que cumpla con la condición), entonces orElse(null) devuelve null. Si se encontró un Beneficiario, se devuelve ese Beneficiario.

            if (beneficiarioSeleccionado != null && donacionSeleccionada != null) {
                Distribucion nuevaDistribucion = new Distribucion(fechaDistribucion, fechaEntrega, EnumEstadoDistribucion.PENDIENTE);
                nuevaDistribucion.setDonacion(donacionSeleccionada);
                ManejadorDistribucion.getInstancia().añadirDistribucion(nuevaDistribucion);
                JOptionPane.showMessageDialog(internalFrame, "Distribución registrada exitosamente.");
                internalFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(internalFrame, "Por favor, selecciona un beneficiario y una donación válida.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(internalFrame, "Por favor, selecciona una dirección y un beneficiario válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    // Botón para cancelar
    JButton btnCancelar = new JButton("Cancelar");
    btnCancelar.addActionListener((ActionEvent e) -> internalFrame.dispose());

    // Panel inferior para los botones
    JPanel panelInferior = new JPanel();
    panelInferior.add(btnRegistrar);
    panelInferior.add(btnCancelar);

    // Añadir los paneles al JInternalFrame
    internalFrame.add(panelPrincipal, BorderLayout.CENTER);
    internalFrame.add(panelDerecho, BorderLayout.EAST);
    internalFrame.add(panelDonacion, BorderLayout.WEST);
    internalFrame.add(panelInferior, BorderLayout.SOUTH);

    // Añadir el JInternalFrame al JDesktopPane
    desktopPane.add(internalFrame);
    internalFrame.setVisible(true);

    // Simular el primer cambio para cargar los beneficiarios correspondientes a la primera dirección
    if (!direcciones.isEmpty()) {
        spnDireccion.setValue(direcciones.iterator().next());
    }
}


    //mostrarFormularioDistribucion incompleto
    private static void mostrarFormularioDistribucion(String titulo) {
        // Crear un JInternalFrame para el formulario
        JInternalFrame internalFrame = new JInternalFrame(titulo, true, true, true, true);
        internalFrame.setSize(400, 300);
        internalFrame.setLayout(new GridLayout(11, 2));
        internalFrame.setLocation(100, 150);

        // Etiquetas y campos de texto
        JLabel lblFecha = new JLabel("Fecha de Distribución:");
        JSpinner spnFechaDistribucion = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spnFechaDistribucion, "dd-MM-yyyy");
        spnFechaDistribucion.setEditor(dateEditor);

        JLabel lblFechaPreparacion = new JLabel("Fecha Preparación:");
        JTextField txtFechaPreparacion = new JTextField();
        txtFechaPreparacion.setEditable(false);

        JLabel lblFechaEntrega = new JLabel("Fecha Entrega:");
        JTextField txtFechaEntrega = new JTextField();
        txtFechaEntrega.setEditable(false);

        JLabel lblTipo = new JLabel("Tipo de distribución:");
        JTextField txtTipo = new JTextField();
        txtTipo.setEditable(false);

        JLabel lblDescripcion = new JLabel("Descripción:");
        JTextArea txtDescripcion = new JTextArea();
        txtDescripcion.setEditable(false);
        JScrollPane descripcionScroll = new JScrollPane(txtDescripcion);

// Obtener la lista de distribuciones ordenada por fecha de preparación
        List<Distribucion> distribucionesOrdenadas = ManejadorDistribucion.getInstancia().getListaDistribuciones().stream()
                .sorted((d1, d2) -> compararFechas(d1.getFechaPreparacion(), d2.getFechaPreparacion()))
                .collect(Collectors.toList());

        // Actualizar la información al cambiar la fecha en el spinner
        spnFechaDistribucion.addChangeListener(e -> {
            Date fechaSeleccionadaDate = (Date) spnFechaDistribucion.getValue();
            DtFechaHora fechaSeleccionada = convertirDateADtFechaHora(fechaSeleccionadaDate);
            Distribucion distribucionSeleccionada = obtenerDistribucionSegunFecha(fechaSeleccionada, distribucionesOrdenadas);

            if (distribucionSeleccionada != null) {
                txtFechaPreparacion.setText(distribucionSeleccionada.getFechaPreparacion().toString());
                txtFechaEntrega.setText(distribucionSeleccionada.getFechaEntrega().toString());
                Donacion donacion = distribucionSeleccionada.getDonacion();

                if (donacion != null) {
                    if (donacion instanceof Alimento) {
                        Alimento alimento = (Alimento) donacion;
                        txtTipo.setText("Alimento");
                        txtDescripcion.setText(alimento.getDescProducto());
                    } else if (donacion instanceof Articulo) {
                        Articulo articulo = (Articulo) donacion;
                        txtTipo.setText("Artículo");
                        txtDescripcion.setText(articulo.getDescr());
                    } else {
                        txtTipo.setText("Desconocido");
                        txtDescripcion.setText("Donación desconocida");
                    }
                } else {
                    txtTipo.setText("N/A");
                    txtDescripcion.setText("No hay donación asociada");
                }
            } else {
                txtFechaPreparacion.setText("");
                txtFechaEntrega.setText("");
                txtTipo.setText("");
                txtDescripcion.setText("No hay distribuciones para esta fecha");
            }
        });

        // Establecer la primera fecha como la fecha inicial en el spinner
        if (!distribucionesOrdenadas.isEmpty()) {
            DtFechaHora fechaInicial = distribucionesOrdenadas.get(0).getFechaPreparacion();
            Date fechaInicialDate = convertirDtFechaHoraADate(fechaInicial);
            ((SpinnerDateModel) spnFechaDistribucion.getModel()).setValue(fechaInicialDate);
        }

        // Botón para cancelar
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener((ActionEvent e) -> internalFrame.dispose());

        // Añadir los componentes al JInternalFrame
        internalFrame.add(lblFecha);
        internalFrame.add(spnFechaDistribucion);
        internalFrame.add(lblFechaPreparacion);
        internalFrame.add(txtFechaPreparacion);
        internalFrame.add(lblFechaEntrega);
        internalFrame.add(txtFechaEntrega);
        internalFrame.add(lblTipo);
        internalFrame.add(txtTipo);
        internalFrame.add(lblDescripcion);
        internalFrame.add(descripcionScroll);
        internalFrame.add(btnCancelar);

        // Añadir el JInternalFrame al JDesktopPane
        desktopPane.add(internalFrame);
        internalFrame.setVisible(true);
    }

    //mostrarFormularioModDistribucion incompleto
    private static void mostrarFormularioModDistribucion(String titulo) {
        // Crear un JInternalFrame para el formulario
        JInternalFrame internalFrame = new JInternalFrame(titulo, true, true, true, true);
        internalFrame.setSize(400, 300);
        internalFrame.setLayout(new GridLayout(11, 2));
        internalFrame.setLocation(100, 150);

        // Etiquetas y campos de texto
        JLabel lblIdDistribucion = new JLabel("ID Donacion:");
        JSpinner spnIdDistribucion = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1)); // Ajusta el rango según tu necesidad

        JLabel lblFechaPreparacion = new JLabel("Fecha Preparación:");
        JTextField txtFechaPreparacion = new JTextField();

        JLabel lblFechaEntrega = new JLabel("Fecha Entrega:");
        JTextField txtFechaEntrega = new JTextField();

        JLabel lblTipo = new JLabel("Tipo de distribucion:");
        JTextField txtTipo = new JTextField();

        JLabel lblDescripcion = new JLabel("Descripción:");
        JTextArea txtDescripcion = new JTextArea();
        txtDescripcion.setEditable(true);

        // Configurar la lista de distribuciones
        List<Distribucion> distribuciones = manejadorDistribucion.getListaDistribuciones();

        // Actualizar la información al cambiar el ID de la distribución
        spnIdDistribucion.addChangeListener(e -> {
            int idDistribucion = (Integer) spnIdDistribucion.getValue();
            Distribucion distribucionSeleccionada = obtenerDistribucionConDonacion(idDistribucion);

            if (distribucionSeleccionada != null) {
                // Actualizar los campos de fecha
                txtFechaPreparacion.setText(formatearFecha(distribucionSeleccionada.getFechaPreparacion()));
                txtFechaEntrega.setText(formatearFecha(distribucionSeleccionada.getFechaEntrega()));

                // Obtener la donación asociada
                Donacion donacion = distribucionSeleccionada.getDonacion();
                if (donacion != null) {
                    if (donacion instanceof Alimento) {
                        Alimento alimento = (Alimento) donacion;
                        txtTipo.setText("Alimento");
                        txtDescripcion.setText(alimento.getDescProducto());
                    } else if (donacion instanceof Articulo) {
                        Articulo articulo = (Articulo) donacion;
                        txtTipo.setText("Articulo");
                        txtDescripcion.setText("Artículo: " + articulo.getDescr());
                    } else {
                        txtDescripcion.setText("Donación desconocida");
                    }
                } else {
                    txtDescripcion.setText("No hay donación asociada");
                }
            }
        });

        // Botón para guardar
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener((ActionEvent e) -> {
            try {

                // Convertir y guardar la información
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
        internalFrame.add(lblIdDistribucion);
        internalFrame.add(spnIdDistribucion);
        internalFrame.add(lblFechaPreparacion);
        internalFrame.add(txtFechaPreparacion);
        internalFrame.add(lblFechaEntrega);
        internalFrame.add(txtFechaEntrega);
        internalFrame.add(lblTipo);
        internalFrame.add(txtTipo);
        internalFrame.add(lblDescripcion);
        internalFrame.add(new JScrollPane(txtDescripcion)); // Agregar JScrollPane para el JTextArea
        internalFrame.add(btnGuardar);
        internalFrame.add(btnCancelar);

        // Añadir el JInternalFrame al JDesktopPane
        desktopPane.add(internalFrame);
        internalFrame.setVisible(true);
    }

    private static Distribucion obtenerDistribucionSegunFecha(DtFechaHora fecha, List<Distribucion> distribuciones) {
        for (Distribucion distribucion : distribuciones) {
            if (distribucion.getFechaPreparacion().equals(fecha)) {
                return distribucion;
            }
        }
        return null;
    }

    private static Distribucion obtenerDistribucionConDonacion(int id) {
        for (Distribucion distribucion : manejadorDistribucion.getListaDistribuciones()) {
            Donacion donacion = distribucion.getDonacion();
            if (donacion != null && donacion.getId() == id) {
                return distribucion;
            }
        }
        return null;
    }

    private static DtFechaHora obtenerFechaHora() {
        // Obtener la fecha y hora actual usando Calendar
        Calendar calendario = Calendar.getInstance();

        // Extraer los componentes de la fecha y hora
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        int mes = calendario.get(Calendar.MONTH) + 1; // Los meses en Calendar son 0-indexados (enero es 0)
        int anio = calendario.get(Calendar.YEAR);
        int hora = calendario.get(Calendar.HOUR_OF_DAY);
        int minutos = calendario.get(Calendar.MINUTE);

        // Crear una instancia de DtFechaHora con la fecha actual
        DtFechaHora fechaActual = new DtFechaHora(dia, mes, anio, hora, minutos);
        return fechaActual;
    }

    private static DtFechaHora convertirDateADtFechaHora(Date fecha) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);

        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        int mes = calendar.get(Calendar.MONTH) + 1;  // Enero es 0 en Calendar, por eso se suma 1
        int anio = calendar.get(Calendar.YEAR);
        int hora = calendar.get(Calendar.HOUR_OF_DAY);
        int minutos = calendar.get(Calendar.MINUTE);

        return new DtFechaHora(dia, mes, anio, hora, minutos);
    }

    private static Date convertirDtFechaHoraADate(DtFechaHora fechaHora) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, fechaHora.getAnio());
        calendar.set(Calendar.MONTH, fechaHora.getMes() - 1);  // Los meses empiezan en 0 en Calendar
        calendar.set(Calendar.DAY_OF_MONTH, fechaHora.getDia());
        calendar.set(Calendar.HOUR_OF_DAY, fechaHora.getHora());
        calendar.set(Calendar.MINUTE, fechaHora.getMinutos());
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    private static int compararFechas(DtFechaHora fecha1, DtFechaHora fecha2) {
        if (fecha1.getAnio() != fecha2.getAnio()) {
            return fecha1.getAnio() - fecha2.getAnio();
        } else if (fecha1.getMes() != fecha2.getMes()) {
            return fecha1.getMes() - fecha2.getMes();
        } else if (fecha1.getDia() != fecha2.getDia()) {
            return fecha1.getDia() - fecha2.getDia();
        } else if (fecha1.getHora() != fecha2.getHora()) {
            return fecha1.getHora() - fecha2.getHora();
        } else {
            return fecha1.getMinutos() - fecha2.getMinutos();
        }
    }

    // Método para formatear la fecha a una cadena
    private static String formatearFecha(DtFechaHora fecha) {
        if (fecha == null) {
            return "";
        }
        return String.format("%02d/%02d/%04d %02d:%02d",
                fecha.getDia(), fecha.getMes(), fecha.getAnio(),
                fecha.getHora(), fecha.getMinutos());
    }

    private static String[] getBeneficiariosDisponibles() {
        // Lista de beneficiarios disponibles
        return ManejadorBeneficiario.getInstancia().getListaBeneficiario().stream()
                .map(Beneficiario::getNombre)
                .toArray(String[]::new);
    }

    private static Donacion[] getDonacionesDisponibles() {
        // Lista de donaciones disponibles
        return ManejadorDonacion.getInstancia().getListaDonacion().toArray(new Donacion[0]);
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
