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
package org.pap.presentation;

import org.pap.dtClasses.*;
import org.pap.Enums.*;
import org.pap.interfaces.Fabrica;
import org.pap.publicadores.ControladorPublish;

import java.awt.*;
import java.awt.event.ActionEvent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class Principal {

    private static JFrame ventanaP;
    private static JDesktopPane desktopPane;
// Obtener la instancia de la fabrica
    private static Fabrica fabrica = Fabrica.getInstancia();

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

        // Crear el menú "Agregar Distribucion"
        JMenu mnDistribucion = new JMenu("Distribucion");
        menuBar.add(mnDistribucion);

        // Crear el menú "Agregar Distribucion"
        JMenu mnListar = new JMenu("Listar");
        menuBar.add(mnListar);

        // Crear el menú "Reportes"
        JMenu mnReportes = new JMenu("Reportes");
        menuBar.add(mnReportes);

        // Crear y añadir el elemento de menú "About"
        JMenuItem mntmAbout = new JMenuItem("About");
        mntmAbout.addActionListener((ActionEvent arg0) -> {
            mostrarFormularioAbout("About");  //ejecuta llamada a procedimeinto mostrarFormularioBeneficiario("Agregar Beneficiario")
        });
        menuBar.add(mntmAbout);

        // Crear y añadir el elemento de menú "Reporte zona con mayor Distribucion "
        JMenuItem mntmRepZonaMasDistribucion = new JMenuItem("Reporte de Zonas con Mayor Distribucion");
        mntmRepZonaMasDistribucion.addActionListener((ActionEvent arg0) -> {
            mostrarFormularioReporteZonas("Reporte de Zonas con Mayor Distribucion");
        });
        mnReportes.add(mntmRepZonaMasDistribucion);

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

        JMenuItem mntmModDonacion= new JMenuItem("Modificar Donacion");
        mntmModDonacion.addActionListener((ActionEvent arg0) -> {
            mostrarFormularioModDDonacion("Modificar Donacion");
        });
        mnAgregarDonacion.add(mntmModDonacion);

        // Crear y añadir el elemento de menú "Distribucion"
        JMenuItem mntmDistribucion = new JMenuItem("Alta distribucion");
        mntmDistribucion.addActionListener((ActionEvent arg0) -> {
            mostrarFormularioAltaDistribucion("Agregar Distribucion");
        });
        mnDistribucion.add(mntmDistribucion);

        // Crear y añadir el elemento de menú "Distribucion"
        JMenuItem mntmModDistribucion = new JMenuItem("Modificar distribucion");
        mntmModDistribucion.addActionListener((ActionEvent arg0) -> {
            mostrarFormularioModDistribucion("Modificar Distribucion");
        });
        mnDistribucion.add(mntmModDistribucion);

        // Crear y añadir el elemento de menú "Listar beneficiario"
        JMenuItem mntmListBeneficiarios = new JMenuItem("Listar beneficiarios");
        mntmListBeneficiarios.addActionListener((ActionEvent arg0) -> {
            mostrarFormularioListarBeneficiario("Listar beneficiarios");
        });
        mnListar.add(mntmListBeneficiarios);

        // Crear y añadir el elemento de menú "Listar Distribucion"
        JMenuItem mntmListDistribucion = new JMenuItem("Listar distribuciones");
        mntmListDistribucion.addActionListener((ActionEvent arg0) -> {
            mostrarFormularioListarDistribucion("Listar Distribucion");
        });
        mnListar.add(mntmListDistribucion);

        // Crear y añadir el elemento de menú "Listar Beneficiarios por Zona"
        JMenuItem mntmListBeneficiariosZona = new JMenuItem("Listar Beneficiarios por Zona");
        mntmListBeneficiariosZona.addActionListener((ActionEvent arg0) -> {
            mostrarFormularioListarBeneficiarioZona("Listar Beneficiarios por Zona");
        });
        mnListar.add(mntmListBeneficiariosZona);
        
        // Crear y añadir el elemento de menú "Listar Beneficiarios por Estado"
        JMenuItem mntmListBeneficiariosEstado = new JMenuItem("Listar Beneficiarios por Estado");
        mntmListBeneficiariosEstado.addActionListener((ActionEvent arg0) -> {
            mostrarFormularioListarBeneficiarioEstado("Listar Beneficiarios por Estado");
        });
        mnListar.add(mntmListBeneficiariosEstado);

        // Crear y añadir el elemento de menú "Listar Distribucion por zona"
        JMenuItem mntmListDistribucionZona = new JMenuItem("Listar distribuciones por Zona");
        mntmListDistribucionZona.addActionListener((ActionEvent arg0) -> {
            mostrarFormularioListarDistribucionZona("Listar Distribucion por Zona");
        });
        mnListar.add(mntmListDistribucionZona);

        // Crear y añadir el elemento de menú "Modificar Usuario"
        JMenu nmModificarUsuario = new JMenu("Modificar Usuario");
        mnAgregarBeneficiario.add(nmModificarUsuario);

        JMenuItem mntmModificarBeneficiario = new JMenuItem("Modificar Beneficiario");
        mntmModificarBeneficiario.addActionListener((ActionEvent arg0) -> {
            mostrarFormulariomntmModificarBeneficiario("Modificar Beneficiario");
        });
        nmModificarUsuario.add(mntmModificarBeneficiario);

        JMenuItem mntmModificarRepartidor = new JMenuItem("Modificar Repartidor");
        mntmModificarRepartidor.addActionListener((ActionEvent arg0) -> {
            mostrarFormulariomntmModificarRepartidor("Modificar Repartidor");
        });
        nmModificarUsuario.add(mntmModificarRepartidor);

        // Mostrar el cuadro de diálogo de inicio de sesión
        // Hacer visible el JFrame
        ventanaP.setVisible(true);

        Fabrica.getInstancia().getIControlador().cargarBaseDatos();
        ControladorPublish cp = new ControladorPublish();
        cp.publicar();
    }

    private static void mostrarFormularioBeneficiario(String titulo) {
        // Crear un JInternalFrame para el formulario
        JInternalFrame internalFrame = new JInternalFrame(titulo, true, true, true, true);
        internalFrame.setSize(400, 300);
        internalFrame.setLayout(new GridLayout(15, 2));
        internalFrame.setLocation(50, 50);

        // Etiquetas y campos de texto
        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();

        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();

        JLabel lblPasswd = new JLabel("Password:");
        JPasswordField txtPassword = new JPasswordField();

        // Botón para mostrar/ocultar la contraseña
        JButton btnMostrarPassword = new JButton("Mostrar");
        btnMostrarPassword.addActionListener((ActionEvent e) -> {
            if (txtPassword.getEchoChar() != '\u0000') {
                txtPassword.setEchoChar('\u0000'); // Mostrar la contraseña
                btnMostrarPassword.setText("Ocultar");
            } else {
                txtPassword.setEchoChar('*'); // Ocultar la contraseña
                btnMostrarPassword.setText("Mostrar");
            }
        });

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
        JComboBox<String> cbEstado = new JComboBox<String>();
        cbEstado.setBounds(5, 5, 5, 5);
        cbEstado.addItem("Activo");
        cbEstado.addItem("Suspendido");

        JComboBox<String> cbCiudad = new JComboBox<String>();
        cbCiudad.setBounds(5, 5, 5, 5);
        cbCiudad.addItem("Centro");
        cbCiudad.addItem("Ciudad_Vieja");
        cbCiudad.addItem("Cordon");
        cbCiudad.addItem("Palermo");
        cbCiudad.addItem("Parque_Rodo");

        JLabel lblBarrio = new JLabel("Barrio:");
        JTextField txtBarrio = new JTextField();

        // Botón para guardar
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener((ActionEvent e) -> {
            try {
                // Validar el email
                validarEmail(txtEmail.getText());

                int cantBeneficiario = 0;
                cantBeneficiario=fabrica.getIControlador().conGetCantBeneficiarios(); // Obtengo la cantidad de Beneficiaros registrados
                if(cantBeneficiario>=50) { // Si se alcanzo el limite de usuarios se manda mensaje de error
                    JOptionPane.showMessageDialog(null, "Se ha alcanzado el limite de Beneficiarios", "Error", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    //Beneficiario(String nombre,String email, String direccion, LocalDateTime fechaNacimiento, EnumEstadoBeneficiario estado, EnumBarrio barrio)
                    // Guardar la información
                    // Capturar la fecha de nacimiento desde los JSpinner
                    int dia = (int) spnDia.getValue();
                    int mes = (int) spnMes.getValue();
                    int anio = (int) spnAno.getValue();
                    LocalDateTime fechaNacimiento = LocalDateTime.of(anio, mes, dia, 0, 0, 0);

                    // Convertir el estado y barrio seleccionados a los correspondientes Enum
                    EnumEstadoBeneficiario estado = EnumEstadoBeneficiario.valueOf(cbEstado.getSelectedItem().toString().toUpperCase());
                    EnumBarrio barrio = EnumBarrio.valueOf(cbCiudad.getSelectedItem().toString().toUpperCase().replace(" ", "_"));

                    // Agregar beneficirio con los datos obtenidos
                    if (fabrica.getIControlador().existeEmail(txtEmail.getText())) {
                        JOptionPane.showMessageDialog(null, "El beneficiario ya existe.", "Érror", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        char[] passwordArray = txtPassword.getPassword();
                        String password = new String(passwordArray);
                        fabrica.getIControlador().altaBeneficiario(txtNombre.getText(), txtEmail.getText(), password, txtDirecc.getText(), fechaNacimiento, estado, barrio);
                        // Mensaje de operacion realizada satisfactoriamente
                        JOptionPane.showMessageDialog(null, "Datos guardados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                // Esperar un poco antes de cerrar el frame para dar tiempo a mostrar el mensaje de finalización
                Thread.sleep(500);

                // Cerrar el frame después de guardar
                internalFrame.dispose();
                // Captura el error de formato incorrecto de correo
            } catch (InvalidEmailException ema) {
                JOptionPane.showMessageDialog(internalFrame, "Formato de correo con errores: " + ema.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                // Captura el error generico
            } catch (InterruptedException ex) {
                JOptionPane.showMessageDialog(internalFrame, "Ocurrio un error", "Error", JOptionPane.ERROR_MESSAGE);
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
        internalFrame.add(lblPasswd);
        internalFrame.add(txtPassword);
        internalFrame.add(new JLabel());
        internalFrame.add(btnMostrarPassword);
        internalFrame.add(lblTitulo);
        internalFrame.add(lblEspaciador);
        internalFrame.add(lblFechaNa);
        internalFrame.add(spnDia);
        internalFrame.add(new JLabel("Mes:"));
        internalFrame.add(spnMes);
        internalFrame.add(new JLabel("Año:"));
        internalFrame.add(spnAno);
        internalFrame.add(lblEstado);
        internalFrame.add(cbEstado);
        internalFrame.add(lblDirecc);
        internalFrame.add(txtDirecc);
        internalFrame.add(lblBarrio);
        internalFrame.add(cbCiudad);

        internalFrame.add(btnGuardar);
        internalFrame.add(btnCancelar);

        // Añadir el JInternalFrame al JDesktopPane
        desktopPane.add(internalFrame);
        internalFrame.setVisible(true);
    }

    private static void mostrarFormularioRepartidor(String titulo) {
        // Crear un JInternalFrame para el formulario
        JInternalFrame internalFrame = new JInternalFrame(titulo, true, true, true, true);
        internalFrame.setSize(300, 160);
        internalFrame.setLayout(new GridLayout(6, 3));
        internalFrame.setLocation(100, 100);

        // Etiquetas y campos de texto
        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();

        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();

        JLabel lblPasswd = new JLabel("Password:");
        JPasswordField txtPassword = new JPasswordField();

        JLabel lblLicencia = new JLabel("Licencia:");
        JTextField txtLicencia = new JTextField();

        JButton btnMostrarPassword = new JButton("Mostrar");
        btnMostrarPassword.addActionListener((ActionEvent e) -> {
            if (txtPassword.getEchoChar() != '\u0000') {
                txtPassword.setEchoChar('\u0000'); // Mostrar la contraseña
                btnMostrarPassword.setText("Ocultar");
            } else {
                txtPassword.setEchoChar('*'); // Ocultar la contraseña
                btnMostrarPassword.setText("Mostrar");
            }
        });

        // Botón para guardar
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener((ActionEvent e) -> {
            try {
                // Validar el email
                validarEmail(txtEmail.getText());

                if (fabrica.getIControlador().conGetCantRepartidores() >= 5) { // Si se alzanzo el limite de usuarios se manda mensaje de error
                    JOptionPane.showMessageDialog(null, "Se ha alcanzado el limite de Repartidores", "Error", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    if (fabrica.getIControlador().existeEmail(txtEmail.getText())) {
                        JOptionPane.showMessageDialog(null, "El beneficiario ya existe.", "Érror", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        // Guardar la información
                        if (!fabrica.getIControlador().existeLicencia(txtLicencia.getText())) {
                            char[] passwordArray = txtPassword.getPassword();
                            String password = new String(passwordArray);
                            fabrica.getIControlador().altaRepartidor(txtNombre.getText(), txtEmail.getText(), password, txtLicencia.getText());

                            JOptionPane.showMessageDialog(null, "Datos guardados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            JOptionPane.showMessageDialog(null, "Ya existe un usario registrado con la licencia ingresada", "Error", JOptionPane.INFORMATION_MESSAGE);
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
        internalFrame.add(lblPasswd);
        internalFrame.add(txtPassword);
        internalFrame.add(new JLabel()); // For empty space
        internalFrame.add(btnMostrarPassword);
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
        internalFrame.setLocation(100, 100);

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

                // Obtner la fecha ya que se le pasa la fecha de hoy y no una ingresada por usuario
                LocalDateTime fechaHoy = obtenerFechaHora();

                // Si la conversión es exitosa, guardar
                fabrica.getIControlador().altaDonacionAlimento(fechaHoy, descripcion, cantidad);

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
        internalFrame.setSize(400, 280);
        internalFrame.setLayout(new GridLayout(8, 2));
        internalFrame.setLocation(100, 150);

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

                LocalDateTime fechaHoy = obtenerFechaHora();
                // Agregar el articulo creado
                fabrica.getIControlador().altaDonacionArticulo(fechaHoy, txtDescripcion.getText(), peso, txtDimension.getText());

                JOptionPane.showMessageDialog(null, "Datos guardados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                // Cerrar el frame después de guardar
                internalFrame.dispose();
            } catch (HeadlessException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(internalFrame, "Peso debe ser un numero: ", "Error", JOptionPane.ERROR_MESSAGE);
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

    private static void mostrarFormularioAltaDistribucion(String titulo) {
        // Crear un JInternalFrame para el formulario
        JInternalFrame internalFrame = new JInternalFrame(titulo, true, true, true, true);
        internalFrame.setSize(800, 400);
        internalFrame.setLayout(new BorderLayout());
        internalFrame.setLocation(100, 100);

        // Panel principal donde se ve la información de la distribución
        JPanel panelPrincipal = new JPanel(new GridLayout(6, 2)); // Acomoda los elementos

        // Fecha de Preparación (autoasignada como hoy)
        JLabel lblFechaPreparacion = new JLabel("Fecha de Preparación:");
        JTextField txtFechaPreparacion = new JTextField(LocalDate.now().toString());
        txtFechaPreparacion.setEditable(false);

        // Estado (PENDIENTE)
        JLabel lblEstado = new JLabel("Estado:");
        JTextField txtEstado = new JTextField("PENDIENTE");
        txtEstado.setEditable(false);

        // Beneficiarios disponibles
        JLabel lblBeneficiario = new JLabel("Beneficiario:");
        JComboBox<String> cbBeneficiarios = new JComboBox<>();

        List<DTBeneficiario> listaBeneficiarios = (List<DTBeneficiario>) (List<?>) fabrica.getIControlador().ListarBeneficiario();
        if (listaBeneficiarios.isEmpty()) {
            cbBeneficiarios.addItem("No hay beneficiarios disponibles");
            cbBeneficiarios.setEnabled(false);
        } else {
            for (DTBeneficiario beneficiario : listaBeneficiarios) {
                cbBeneficiarios.addItem(beneficiario.getNombre());
            }
        }

        // Donaciones disponibles
        JLabel lblDonacion = new JLabel("Donación:");
        JComboBox<String> cbDonaciones = new JComboBox<>();

        List<DTDonacion> listaDonaciones = fabrica.getIControlador().ListarDonaciones();
        if (listaDonaciones.isEmpty()) {
            cbDonaciones.addItem("No hay donaciones disponibles");
            cbDonaciones.setEnabled(false);
        } else {
            String descripcion = null;
            for (DTDonacion donacion : listaDonaciones) {
                if (donacion instanceof DTArticulo) {
                    descripcion = ((DTArticulo) donacion).getDescr();
                } else if (donacion instanceof DTAlimento) {
                    descripcion = ((DTAlimento) donacion).getDescProducto();
                }
                cbDonaciones.addItem(donacion.getId() + " - " + descripcion);
            }
        }

        // Fecha de entrega
        JLabel lblFechaEntrega = new JLabel("Fecha de Entrega:");
        JSpinner spinnerFechaEntrega = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinnerFechaEntrega, "dd-MM-yyyy");
        spinnerFechaEntrega.setEditor(dateEditor);

        // Hora de entrega
        JLabel lblHoraEntrega = new JLabel("Hora de Entrega:");

// Configuración del modelo para que el spinner cambie los minutos
        SpinnerDateModel modelHoraEntrega = new SpinnerDateModel();
        JSpinner spinnerHoraEntrega = new JSpinner(modelHoraEntrega);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(spinnerHoraEntrega, "HH:mm");
        spinnerHoraEntrega.setEditor(timeEditor);

// Ajustar para que el spinner cambie minutos al cambiar el valor
        spinnerHoraEntrega.addChangeListener(e -> {
            Date date = modelHoraEntrega.getDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            // Forzar al spinner a cambiar solo los minutos
            if (modelHoraEntrega.getCalendarField() != Calendar.MINUTE) {
                modelHoraEntrega.setCalendarField(Calendar.MINUTE);
            }

            int currentMinutes = calendar.get(Calendar.MINUTE);
            calendar.set(Calendar.MINUTE, currentMinutes);
            modelHoraEntrega.setValue(calendar.getTime());
        });

        // Añadir componentes al panel principal
        panelPrincipal.add(lblFechaPreparacion);
        panelPrincipal.add(txtFechaPreparacion);
        panelPrincipal.add(lblEstado);
        panelPrincipal.add(txtEstado);
        panelPrincipal.add(lblBeneficiario);
        panelPrincipal.add(cbBeneficiarios);
        panelPrincipal.add(lblDonacion);
        panelPrincipal.add(cbDonaciones);
        panelPrincipal.add(lblFechaEntrega);
        panelPrincipal.add(spinnerFechaEntrega);
        panelPrincipal.add(lblHoraEntrega);
        panelPrincipal.add(spinnerHoraEntrega);

        // Panel inferior con los botones Registrar y Cancelar
        JPanel panelInferior = new JPanel();
        JButton btnRegistrar = new JButton("Registrar");
        JButton btnCancelar = new JButton("Cancelar");

        // Acción del botón Registrar
        btnRegistrar.addActionListener((ActionEvent e) -> {
            String nombreBeneficiarioSeleccionado = (String) cbBeneficiarios.getSelectedItem();
            String donacionSeleccionada = (String) cbDonaciones.getSelectedItem();
            // Extraer el ID de la donación seleccionada
            int idDonacion = Integer.parseInt(donacionSeleccionada.split(" - ")[0]);
            if (cbBeneficiarios.isEnabled() && cbDonaciones.isEnabled()
                    && nombreBeneficiarioSeleccionado != null && donacionSeleccionada != null) {

                DTBeneficiario beneficiarioSeleccionado = listaBeneficiarios.stream()
                        .filter(b -> b.getNombre().equals(nombreBeneficiarioSeleccionado))
                        .findFirst().orElse(null);

                // Obtener la fecha y hora seleccionadas
                Date fechaEntrega = (Date) spinnerFechaEntrega.getValue();
                Date horaEntrega = (Date) spinnerHoraEntrega.getValue();

                // Combinar fecha y hora
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(fechaEntrega);
                calendar.set(Calendar.HOUR_OF_DAY, horaEntrega.getHours());
                calendar.set(Calendar.MINUTE, horaEntrega.getMinutes());

                LocalDateTime fechaHoraEntrega = LocalDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId());

                // Guardar el beneficiarioSeleccionado, donacionSeleccionadaObj y fechaHoraEntrega
                //El id de la donacion deberia ser generado por el controlador no?
                fabrica.getIControlador().agregarDistribucion(fechaHoraEntrega, fechaHoraEntrega, EnumEstadoDistribucion.PENDIENTE, idDonacion, beneficiarioSeleccionado.getEmail());
                JOptionPane.showMessageDialog(null, "Datos guardados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                // Cerrar el frame después de guardar
                internalFrame.dispose();
            }
        });

        // Acción del botón Cancelar
        btnCancelar.addActionListener((ActionEvent e) -> internalFrame.dispose());

        // Añadir botones al panel inferior
        panelInferior.add(btnRegistrar);
        panelInferior.add(btnCancelar);

        // Añadir los paneles al JInternalFrame
        internalFrame.add(panelPrincipal, BorderLayout.CENTER);
        internalFrame.add(panelInferior, BorderLayout.SOUTH);

        // Añadir el JInternalFrame al JDesktopPane
        desktopPane.add(internalFrame);
        internalFrame.setVisible(true);
    }

    private static void mostrarFormularioListarBeneficiario(String titulo) {
        // Crear un JInternalFrame para el formulario
        JInternalFrame internalFrame = new JInternalFrame(titulo, true, true, true, true);
        internalFrame.setSize(600, 400);
        internalFrame.setLayout(new BorderLayout());
        internalFrame.setLocation(100, 100);

        // Crear el modelo de la tabla
        String[] columnNames = {"Nombre", "Correo", "Dirección", "Estado", "Barrio"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable tablaBeneficiarios = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tablaBeneficiarios);

        // Obtener la lista de beneficiarios
        List<DTUsuario> listaBeneficiarios = fabrica.getIControlador().ListarBeneficiario();

        // Limpiar tabla existente
        tableModel.setRowCount(0);

        if (listaBeneficiarios.isEmpty()) {
            tableModel.addRow(new Object[]{"No hay beneficiarios registrados", "", "", "", ""});
        } else {
            for (DTUsuario usuario : listaBeneficiarios) {
                if (usuario instanceof DTBeneficiario) {
                    DTBeneficiario beneficiario = (DTBeneficiario) usuario;
                    String email = beneficiario.getEmail();
                    String nombre = beneficiario.getNombre();
                    String direccion = beneficiario.getDireccion();
                    String estado = beneficiario.getEstado().toString();
                    EnumBarrio barrio = beneficiario.getBarrio();

                    tableModel.addRow(new Object[]{nombre, email, direccion, estado, barrio});
                }
            }
        }

        // Panel inferior con el botón Cancelar
        JPanel panelInferior = new JPanel();
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> internalFrame.dispose());
        panelInferior.add(btnCancelar);

        // Añadir componentes al JInternalFrame
        internalFrame.add(scrollPane, BorderLayout.CENTER);
        internalFrame.add(panelInferior, BorderLayout.SOUTH);

        // Añadir el JInternalFrame al JDesktopPane
        desktopPane.add(internalFrame);
        internalFrame.setVisible(true);
    }

    private static void mostrarFormularioListarDistribucion(String titulo) {
        // Crear y configurar el JInternalFrame
        JInternalFrame internalFrame = crearInternalFrameDistribucion(titulo);

        // Crear y configurar el JComboBox para el estado de distribución
        JPanel panelEstado = crearPanelEstadoDistribucion(internalFrame);

        // Crear y configurar la tabla de distribuciones
        DefaultTableModel tableModel = crearTableModelDistribucion();
        JTable tablaDistribuciones = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tablaDistribuciones);

        // Evento para actualizar la tabla según el estado seleccionado
        JComboBox<String> cbEstado = (JComboBox<String>) panelEstado.getComponent(1);
        cbEstado.addActionListener(e -> actualizarTablaDistribuciones(cbEstado, tableModel));

        // Crear panel inferior con el botón Cancelar
        JPanel panelInferior = crearPanelInferiorDistribucion(internalFrame);

        // Añadir componentes al JInternalFrame
        internalFrame.add(panelEstado, BorderLayout.NORTH);
        internalFrame.add(scrollPane, BorderLayout.CENTER);
        internalFrame.add(panelInferior, BorderLayout.SOUTH);

        // Añadir el JInternalFrame al JDesktopPane
        desktopPane.add(internalFrame);
        internalFrame.setVisible(true);
    }

    // Método auxiliar para crear el JInternalFrame
    private static JInternalFrame crearInternalFrameDistribucion(String titulo) {
        JInternalFrame internalFrame = new JInternalFrame(titulo, true, true, true, true);
        internalFrame.setSize(600, 400);
        internalFrame.setLayout(new BorderLayout());
        internalFrame.setLocation(100, 100);
        return internalFrame;
    }

    // Método auxiliar para crear el panel de estado de distribución
    private static JPanel crearPanelEstadoDistribucion(JInternalFrame internalFrame) {
        JPanel panelEstado = new JPanel();
        JLabel lblEstado = new JLabel("Estado de Distribución:");
        JComboBox<String> cbEstado = new JComboBox<>();

        cbEstado.addItem("");  // Espacio inicial vacío
        cbEstado.addItem("Todas");  // Opción para todas las distribuciones

        for (EnumEstadoDistribucion estado : EnumEstadoDistribucion.values()) {
            cbEstado.addItem(estado.name());
        }

        panelEstado.add(lblEstado);
        panelEstado.add(cbEstado);
        return panelEstado;
    }

    // Método auxiliar para crear el modelo de la tabla
    private static DefaultTableModel crearTableModelDistribucion() {
        String[] columnNames = {"Fecha Preparación", "Fecha Entrega", "Tipo", "Descripción"};
        return new DefaultTableModel(columnNames, 0);
    }

    // Método auxiliar para crear el panel inferior con el botón Cancelar
    private static JPanel crearPanelInferiorDistribucion(JInternalFrame internalFrame) {
        JPanel panelInferior = new JPanel();
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> internalFrame.dispose());
        panelInferior.add(btnCancelar);
        return panelInferior;
    }

    // Método para actualizar la tabla de distribuciones según el estado seleccionado
    private static void actualizarTablaDistribuciones(JComboBox<String> cbEstado, DefaultTableModel tableModel) {
        String estadoSeleccionado = (String) cbEstado.getSelectedItem();

        SwingWorker<List<Object[]>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<Object[]> doInBackground() {
                List<Object[]> filas = new ArrayList<>();
                try {
                    List<DTDistribucion> distribuciones = "Todas".equals(estadoSeleccionado)
                            ? fabrica.getIControlador().listarDistribuciones()
                            : fabrica.getIControlador().listarDistribucionesPorEstado(EnumEstadoDistribucion.valueOf(estadoSeleccionado));

                    for (DTDistribucion distribucion : distribuciones) {
                        DTDonacion donacion = fabrica.getIControlador().obtenerDonacion(distribucion.getDonacionAsc());
                        String tipo = donacion instanceof DTAlimento ? "Alimento" : donacion instanceof DTArticulo ? "Artículo" : "Desconocido";
                        String descripcion = switch (donacion) {
                            case DTAlimento dTAlimento -> dTAlimento.getDescProducto();
                            case DTArticulo dTArticulo -> dTArticulo.getDescr();
                            default -> "N/A";
                        };

                        filas.add(new Object[]{
                                distribucion.getFechaPreparacion().toString(),
                                distribucion.getFechaEntrega().toString(),
                                tipo,
                                descripcion
                        });
                    }
                } catch (IllegalArgumentException ex) {
                    filas.add(new Object[]{"Estado no válido seleccionado", "", "", ""});
                }
                return filas;
            }

            @Override
            protected void done() {
                try {
                    // Limpiamos y actualizamos la tabla con los datos obtenidos
                    tableModel.setRowCount(0);
                    for (Object[] fila : get()) {
                        tableModel.addRow(fila);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        worker.execute();
    }





    // Método auxiliar para agregar una fila a la tabla de distribuciones
    private static void agregarFilaDistribucion(DefaultTableModel tableModel, DTDistribucion distribucion) {
        DTDonacion donacion = fabrica.getIControlador().obtenerDonacion(distribucion.getId());

        String tipo = donacion instanceof DTAlimento ? "Alimento" : donacion instanceof DTArticulo ? "Artículo" : "Desconocido";
        String descripcion = switch (donacion) {
            case DTAlimento dTAlimento -> dTAlimento.getDescProducto();
            case DTArticulo dTArticulo -> dTArticulo.getDescr();
            default -> "N/A";
        };

        tableModel.addRow(new Object[]{
                distribucion.getFechaPreparacion().toString(),
                distribucion.getFechaEntrega().toString(),
                tipo,
                descripcion
        });
    }

    private static void mostrarFormularioModDistribucion(String titulo) {
        // Crear un JInternalFrame para el formulario
        JInternalFrame internalFrame = new JInternalFrame(titulo, true, true, true, true);
        internalFrame.setSize(600, 400);
        internalFrame.setLayout(new BorderLayout());
        internalFrame.setLocation(100, 100);

        // Panel para seleccionar la distribución a modificar
        JPanel panelSeleccion = new JPanel();
        JLabel lblSeleccion = new JLabel("Seleccionar Distribución:");
        JComboBox<String> cbDistribuciones = new JComboBox<>();

        // Estado y otros componentes
        JLabel lblEstado = new JLabel("Estado:");
        JComboBox<String> cbEstado = new JComboBox<>();
        for (EnumEstadoDistribucion estado : EnumEstadoDistribucion.values()) {
            cbEstado.addItem(estado.name());
        }

        JLabel lblFechaEntrega = new JLabel("Fecha de Entrega:");
        JSpinner spinnerFechaEntrega = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinnerFechaEntrega, "dd-MM-yyyy");
        spinnerFechaEntrega.setEditor(dateEditor);

        JPanel panelDetalles = new JPanel(new GridLayout(4, 2));
        panelDetalles.add(lblFechaEntrega);
        panelDetalles.add(spinnerFechaEntrega);
        panelDetalles.add(lblEstado);
        panelDetalles.add(cbEstado);

        // SwingWorker para cargar distribuciones de forma asincrónica
        new SwingWorker<List<DTDistribucion>, Void>() {
            @Override
            protected List<DTDistribucion> doInBackground() {
                return fabrica.getIControlador().listarDistribuciones();
            }

            @Override
            protected void done() {
                try {
                    // Limpiar elementos anteriores
                    cbDistribuciones.removeAllItems();

                    List<DTDistribucion> distribuciones = get();
                    if (distribuciones.isEmpty()) {
                        cbDistribuciones.addItem("No hay distribuciones disponibles");
                        cbDistribuciones.setEnabled(false);
                        spinnerFechaEntrega.setEnabled(false);
                        cbEstado.setEnabled(false);
                    } else {
                        cbDistribuciones.addItem(""); // Item vacío para deseleccionar
                        for (DTDistribucion distribucion : distribuciones) {
                            // Cargar cada donación en otro SwingWorker
                            new SwingWorker<DTDonacion, Void>() {
                                @Override
                                protected DTDonacion doInBackground() {
                                    return fabrica.getIControlador().obtenerDonacion(distribucion.getDonacionAsc());
                                }

                                @Override
                                protected void done() {
                                    try {
                                        DTDonacion donacion = get();
                                        String descripcion = switch (donacion) {
                                            case DTAlimento dTAlimento -> dTAlimento.getDescProducto();
                                            case DTArticulo dTArticulo -> dTArticulo.getDescr();
                                            default -> "Descripción no disponible";
                                        };
                                        cbDistribuciones.addItem(distribucion.getId() + " - " + descripcion);
                                    } catch (Exception ex) {
                                        cbDistribuciones.addItem("Error al cargar donación");
                                    }
                                }
                            }.execute();
                        }
                    }

                    // Listener para actualizar detalles al seleccionar una distribución
                    cbDistribuciones.addActionListener(e -> {
                        int distIndex = cbDistribuciones.getSelectedIndex() - 1;
                        if (distIndex >= 0 && distIndex < distribuciones.size()) {
                            DTDistribucion seleccionada = distribuciones.get(distIndex);
                            LocalDateTime fechaEntregaDate = seleccionada.getFechaEntrega();
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(fechaEntregaDate.getYear(), fechaEntregaDate.getMonthValue() - 1, fechaEntregaDate.getDayOfMonth());
                            spinnerFechaEntrega.setValue(calendar.getTime());
                            cbEstado.setSelectedItem(seleccionada.getEstado().name());
                        } else {
                            spinnerFechaEntrega.setValue(new Date());
                            cbEstado.setSelectedIndex(0);
                        }
                    });
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(internalFrame, "Error al cargar distribuciones: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }.execute();

        // Botón para guardar cambios
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            String seleccion = (String) cbDistribuciones.getSelectedItem();
            if (seleccion != null && !seleccion.equals("") && !seleccion.equals("No hay distribuciones disponibles")) {
                try {
                    int idDistribucion = Integer.parseInt(seleccion.split(" - ")[0]);
                    LocalDateTime fechaEntrega = ((Date) spinnerFechaEntrega.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                    EnumEstadoDistribucion estado = EnumEstadoDistribucion.valueOf((String) cbEstado.getSelectedItem());

                    fabrica.getIControlador().modificarDistribucion(idDistribucion, fechaEntrega, estado);

                    JOptionPane.showMessageDialog(internalFrame, "Modificación realizada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    internalFrame.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(internalFrame, "Error al guardar los cambios: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Botón para cancelar
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> internalFrame.dispose());

        JPanel panelInferior = new JPanel();
        panelInferior.add(btnGuardar);
        panelInferior.add(btnCancelar);

        // Añadir componentes al JInternalFrame
        panelSeleccion.add(lblSeleccion);
        panelSeleccion.add(cbDistribuciones);
        internalFrame.add(panelSeleccion, BorderLayout.NORTH);
        internalFrame.add(panelDetalles, BorderLayout.CENTER);
        internalFrame.add(panelInferior, BorderLayout.SOUTH);

        desktopPane.add(internalFrame);
        internalFrame.setVisible(true);
    }



    private static void mostrarFormularioListarBeneficiarioZona(String titulo) {
        // Crear un JInternalFrame para el formulario
        JInternalFrame internalFrame = new JInternalFrame(titulo, true, true, true, true);
        internalFrame.setSize(600, 400);
        internalFrame.setLayout(new BorderLayout());
        internalFrame.setLocation(100, 100);
        
        // Panel para el Seleccion por Zona
        JPanel panelBarrio = new JPanel();
        JLabel lblBarrio = new JLabel("Seleccione Barrio:");
        JComboBox<String> cbBarrio = new JComboBox<>();
        String nullString = "";
        cbBarrio.addItem(nullString);// Opción para todas las Zonas

        panelBarrio.add(lblBarrio);
        panelBarrio.add(cbBarrio);
        for (EnumBarrio barrio : EnumBarrio.values()) {
            cbBarrio.addItem(barrio.name());
        }

        // Crear el modelo de la tabla
        String[] columnNames = {"Nombre", "Correo", "Dirección", "Estado", "Barrio"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable tablaBeneficiarios = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tablaBeneficiarios);

        cbBarrio.addActionListener((ActionEvent e) -> {
            cbBarrio.removeItem(nullString);
            String barrioSeleccionado = (String) cbBarrio.getSelectedItem();

            List<DTUsuario> listaBeneficiarios;
            // Convertir el estado seleccionado a EnumEstadoDistribucion y obtener las distribuciones por estado
            EnumBarrio barrioS = EnumBarrio.valueOf(barrioSeleccionado);
            listaBeneficiarios = fabrica.getIControlador().ListarBeneficiarioZona(barrioS);

            tableModel.setRowCount(0); // Limpiar tabla existente

            if (listaBeneficiarios.isEmpty()) {
                tableModel.addRow(new Object[]{"No hay Beneficiarios disponibles", "", "", ""});
                return;
            }

            for (DTUsuario usuario : listaBeneficiarios) {
                if (usuario instanceof DTBeneficiario) {
                    DTBeneficiario beneficiario = (DTBeneficiario) usuario;
                    String email = beneficiario.getEmail();
                    String nombre = beneficiario.getNombre();
                    String direccion = beneficiario.getDireccion();
                    String estado = beneficiario.getEstado().toString();
                    EnumBarrio barrio = beneficiario.getBarrio();

                    tableModel.addRow(new Object[]{nombre, email, direccion, estado, barrio});
                }
            }

            if (tableModel.getRowCount() == 0) {
                tableModel.addRow(new Object[]{"No hay Beneficiarios para este Barrio", "", "", ""});
            }
        });

        // Panel inferior con el botón Cancelar
        JPanel panelInferior = new JPanel();
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> internalFrame.dispose());
        panelInferior.add(btnCancelar);

        // Añadir componentes al JInternalFrame
        internalFrame.add(panelBarrio, BorderLayout.NORTH);
        internalFrame.add(scrollPane, BorderLayout.CENTER);
        internalFrame.add(panelInferior, BorderLayout.SOUTH);

        // Añadir el JInternalFrame al JDesktopPane
        desktopPane.add(internalFrame);
        internalFrame.setVisible(true);
    }

    private static void mostrarFormularioListarBeneficiarioEstado(String titulo) {
        // Crear un JInternalFrame para el formulario
        JInternalFrame internalFrame = new JInternalFrame(titulo, true, true, true, true);
        internalFrame.setSize(600, 400);
        internalFrame.setLayout(new BorderLayout());
        internalFrame.setLocation(100, 100);
        
        // Panel para el Seleccion por Estado
        JPanel panelEstado = new JPanel();
        JLabel lblEstado = new JLabel("Seleccione Estado:");
        JComboBox<String> cbEstado = new JComboBox<>();
        String nullString = "";
        cbEstado.addItem(nullString);// Opción para todos los estados

        panelEstado.add(lblEstado);
        panelEstado.add(cbEstado);
        for (EnumEstadoBeneficiario estado : EnumEstadoBeneficiario.values()) {
            cbEstado.addItem(estado.name());
        }

        // Crear el modelo de la tabla
        String[] columnNames = {"Nombre", "Correo", "Dirección", "Estado", "Barrio"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable tablaBeneficiarios = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tablaBeneficiarios);

        cbEstado.addActionListener((ActionEvent e) -> {
            cbEstado.removeItem(nullString);
            String estadoSeleccionado = (String) cbEstado.getSelectedItem();

            List<DTUsuario> listaBeneficiarios;
            
            EnumEstadoBeneficiario estadoS = EnumEstadoBeneficiario.valueOf(estadoSeleccionado);
            listaBeneficiarios = fabrica.getIControlador().ListarBeneficiarioEstado(estadoS);

            tableModel.setRowCount(0); // Limpiar tabla existente

            if (listaBeneficiarios.isEmpty()) {
                tableModel.addRow(new Object[]{"No hay Beneficiarios disponibles", "", "", ""});
                return;
            }

            for (DTUsuario beneficiario : listaBeneficiarios) {
                if (beneficiario instanceof DTBeneficiario) {
                    DTBeneficiario Dtbeneficiario = (DTBeneficiario) beneficiario;
                    String email = Dtbeneficiario.getEmail();
                    String nombre = Dtbeneficiario.getNombre();
                    String direccion = Dtbeneficiario.getDireccion();
                    String estado = Dtbeneficiario.getEstado().toString();
                    EnumBarrio barrio = Dtbeneficiario.getBarrio();

                    tableModel.addRow(new Object[]{nombre, email, direccion, estado, barrio});
                }
            }

            if (tableModel.getRowCount() == 0) {
                tableModel.addRow(new Object[]{"No hay Beneficiarios para este Estado", "", "", ""});
            }
        });

        // Panel inferior con el botón Cancelar
        JPanel panelInferior = new JPanel();
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> internalFrame.dispose());
        panelInferior.add(btnCancelar);

        // Añadir componentes al JInternalFrame
        internalFrame.add(panelEstado, BorderLayout.NORTH);
        internalFrame.add(scrollPane, BorderLayout.CENTER);
        internalFrame.add(panelInferior, BorderLayout.SOUTH);

        // Añadir el JInternalFrame al JDesktopPane
        desktopPane.add(internalFrame);
        internalFrame.setVisible(true);
    }

    private static void mostrarFormularioModDDonacion(String titulo) {
        // Crear un JInternalFrame para el formulario
        JInternalFrame internalFrame = new JInternalFrame(titulo, true, true, true, true);
        internalFrame.setSize(600, 400);
        internalFrame.setLayout(new BorderLayout());
        internalFrame.setLocation(100, 100);

        // Panel para seleccionar la distribución a modificar
        JPanel panelSeleccion = new JPanel();
        JLabel lblSeleccion = new JLabel("Seleccionar Donacion:");
        JComboBox<String> cbDonaciones = new JComboBox<>();
        String stringNull = "";

        List<DTDonacion> donaciones = fabrica.getIControlador().ListarDonaciones();
        if (donaciones.isEmpty()) {
            cbDonaciones.addItem("No hay Donaciones disponibles");
            cbDonaciones.setEnabled(false);
        } else {
            cbDonaciones.addItem(stringNull);
            for (DTDonacion donacion : donaciones) {
                // Declarar la variable descripcion
                String descripcion;

                // Determinar el tipo de donación y establecer la descripción
                switch (donacion) {
                    case DTAlimento dTAlimento -> descripcion = dTAlimento.getDescProducto();
                    case DTArticulo dTArticulo -> descripcion = dTArticulo.getDescr();
                    default -> descripcion = "Descripción no disponible";
                }

                // Agregar la descripción al JComboBox
                cbDonaciones.addItem(donacion.getId() + " - " + descripcion);
            }

        }

        panelSeleccion.add(lblSeleccion);
        panelSeleccion.add(cbDonaciones);

        // Panel para los detalles de la distribución seleccionada
        JPanel panelDetalles = new JPanel(new GridLayout(4, 2));
        JSpinner spinnerFechaIngreso = new JSpinner(new SpinnerDateModel());
        JSpinner spinnerCantidadElementos =  new JSpinner(new SpinnerNumberModel());
        JLabel lblDescripcion = new JLabel("Descripción:");
        JTextField txtDescripcion = new JTextField();
        JTextField txtPeso = new JTextField();
        JTextField txtDimension = new JTextField();

        cbDonaciones.addActionListener((ActionEvent e) -> {
            cbDonaciones.removeItem(stringNull);
            panelDetalles.removeAll();

            //Se utiliza para resfrescar correctamente los paneles si se cambia entre comboboxs
            internalFrame.revalidate();
            internalFrame.repaint();

            DTDonacion donacionSeleccionada = donaciones.get(cbDonaciones.getSelectedIndex());

            JLabel lblFechaEntrega = new JLabel("Fecha de Ingreso:");
            LocalDateTime fechaIngresoDate = donacionSeleccionada.getFechaIngresada();

            int year = fechaIngresoDate.getYear();
            int month = fechaIngresoDate.getMonthValue(); // El mes ya viene como 1-12, no hay que restar 1.
            int day = fechaIngresoDate.getDayOfMonth();

            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month - 1, day); // Aquí sí restamos 1 al mes, ya que Calendar usa 0-11 para los meses.
            Date date = calendar.getTime();
            spinnerFechaIngreso.setValue(date);
            JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinnerFechaIngreso, "dd-MM-yyyy");
            spinnerFechaIngreso.setEditor(dateEditor);
            spinnerFechaIngreso.enable(false); //Spiner es solo para informacion visual

            panelDetalles.add(lblFechaEntrega);
            panelDetalles.add(spinnerFechaIngreso);

            if (donacionSeleccionada instanceof DTAlimento) {
                String desc = ((DTAlimento) donacionSeleccionada).getDescProducto();
                txtDescripcion.setText(desc);
                txtDescripcion.setColumns(20);

                JLabel lblCantElementos = new JLabel("Cantidad de Elementos:");
                int cantEleDon = ((DTAlimento) donacionSeleccionada).getCantElemntos();
                spinnerCantidadElementos.setValue(cantEleDon);

                // Añadimos los componentes al panel
                panelDetalles.add(lblCantElementos);
                panelDetalles.add(spinnerCantidadElementos);
            }

            if (donacionSeleccionada instanceof DTArticulo) {
                String desc = ((DTArticulo) donacionSeleccionada).getDescr();
                txtDescripcion.setText(desc);
                txtDescripcion.setColumns(20);

                JLabel lblPeso = new JLabel("Peso:");
                String peso = String.valueOf(((DTArticulo) donacionSeleccionada).getPeso());  // Convertir de Float a Double
                txtPeso.setText(peso);

                JLabel lblDimension = new JLabel("Dimensiones:");
                String dimen =((DTArticulo) donacionSeleccionada).getDimensiones();
                txtDimension.setText(dimen);
                txtDimension.setColumns(20);


                panelDetalles.add(lblPeso);
                panelDetalles.add(txtPeso);
                panelDetalles.add(lblDimension);
                panelDetalles.add(txtDimension);

            }

            panelDetalles.add(lblDescripcion);
            panelDetalles.add(txtDescripcion);
        });

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            String donacionSeleccionada = cbDonaciones.getSelectedItem().toString();
            if (donacionSeleccionada != null && !donacionSeleccionada.equals("") && !donacionSeleccionada.equals("No hay Donaciones disponibles")) {
                DTDonacion dtDonacionSeleccionada = donaciones.get(cbDonaciones.getSelectedIndex());

                DTDonacion donacionModificar = null;
                LocalDateTime fechaIngreso = ((Date) spinnerFechaIngreso.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                String desc = txtDescripcion.getText();


                if (donaciones.get(cbDonaciones.getSelectedIndex()) instanceof DTAlimento) {
                    try {
                        int cantidad = Integer.parseInt(spinnerCantidadElementos.getValue().toString());
                        donacionModificar = new DTAlimento(dtDonacionSeleccionada.getId(), fechaIngreso,desc, cantidad);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "El valor de la Cantidad no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }else {
                    try {
                        float peso = Float.parseFloat(txtPeso.getText());
                        donacionModificar = new DTArticulo(dtDonacionSeleccionada.getId(),fechaIngreso,desc, peso,  txtDimension.getText());

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "El valor del peso no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                fabrica.getIControlador().modificarDonacion(donacionModificar);

                // Mostrar mensaje de éxito
                JOptionPane.showMessageDialog(internalFrame, "Modificación realizada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                // Cerrar el frame después de guardar
                internalFrame.dispose();
            }
        });

        // Botón para cancelar
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> internalFrame.dispose());

        // Panel inferior con los botones Guardar y Cancelar
        JPanel panelInferior = new JPanel();
        panelInferior.add(btnGuardar);
        panelInferior.add(btnCancelar);

        // Añadir componentes al JInternalFrame
        internalFrame.add(panelSeleccion, BorderLayout.NORTH);
        internalFrame.add(panelDetalles, BorderLayout.CENTER);
        internalFrame.add(panelInferior, BorderLayout.SOUTH);

        // Añadir el JInternalFrame al JDesktopPane
        desktopPane.add(internalFrame);
        internalFrame.setVisible(true);

    }

    private static void mostrarFormularioReporteZonas(String titulo) {
        JInternalFrame internalFrame = new JInternalFrame(titulo, true, true, true, true);
        internalFrame.setSize(800, 600);
        internalFrame.setLayout(new BorderLayout());
        internalFrame.setLocation(50, 50);

        // Panel para seleccionar el rango de fechas
        JPanel panelFechas = new JPanel();
        JLabel lblFechaInicio = new JLabel("Fecha Inicio:");
        JLabel lblFechaFin = new JLabel("Fecha Fin:");
        JSpinner spinnerFechaInicio = new JSpinner(new SpinnerDateModel());
        JSpinner spinnerFechaFin = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editorInicio = new JSpinner.DateEditor(spinnerFechaInicio, "dd/MM/yyyy");
        JSpinner.DateEditor editorFin = new JSpinner.DateEditor(spinnerFechaFin, "dd/MM/yyyy");
        spinnerFechaInicio.setEditor(editorInicio);
        spinnerFechaFin.setEditor(editorFin);

        panelFechas.add(lblFechaInicio);
        panelFechas.add(spinnerFechaInicio);
        panelFechas.add(lblFechaFin);
        panelFechas.add(spinnerFechaFin);

        JButton btnGenerar = new JButton("Generar Reporte");
        panelFechas.add(btnGenerar);

        // Crear el modelo de la tabla del reporte
        String[] columnNames = {"Barrio", "Cantidad de Distribuciones", "Beneficiarios Atendidos"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable tablaReporte = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tablaReporte);

        btnGenerar.addActionListener(e -> {
            // Obtener las fechas seleccionadas desde los JSpinner
            LocalDate fechaInicio = ((Date) spinnerFechaInicio.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate fechaFin = ((Date) spinnerFechaFin.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            // Verificar que ambas fechas no sean null (en caso de que la conversión falle)
            if (fechaInicio == null || fechaFin == null) {
                JOptionPane.showMessageDialog(internalFrame, "Por favor, seleccione ambas fechas.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Map<EnumBarrio, Long> distribucionesPorZona = new HashMap<>();
            Map<EnumBarrio, Set<String>> beneficiariosPorZona = new HashMap<>();

            // Iterar sobre todos los barrios
            for (EnumBarrio barrio : EnumBarrio.values()) {
                List<DTDistribucion> distribucionesBarrio = fabrica.getIControlador().ListarDistribucionesPorZona(barrio);

                // Filtrar por rango de fechas
                List<DTDistribucion> distribucionesFiltradas = distribucionesBarrio.stream()
                        .filter(d -> {
                            LocalDate fechaEntrega = d.getFechaEntrega().toLocalDate();
                            return !fechaEntrega.isBefore(fechaInicio) && !fechaEntrega.isAfter(fechaFin);
                        })
                        .collect(Collectors.toList());

                long cantidadDistribuciones = distribucionesFiltradas.size();
                Set<String> beneficiariosUnicos = distribucionesFiltradas.stream()
                        .map(DTDistribucion::getEmailBenefAsc)
                        .collect(Collectors.toSet());

                if (cantidadDistribuciones > 0) {
                    distribucionesPorZona.put(barrio, cantidadDistribuciones);
                    beneficiariosPorZona.put(barrio, beneficiariosUnicos);
                }
            }
            // Ordenar zonas por cantidad de distribuciones (descendente)
            List<Map.Entry<EnumBarrio, Long>> zonasOrdenadas = distribucionesPorZona.entrySet()
                    .stream()
                    .sorted(Map.Entry.<EnumBarrio, Long>comparingByValue().reversed())
                    .collect(Collectors.toList());

            // Actualizar la tabla
            tableModel.setRowCount(0);
            for (Map.Entry<EnumBarrio, Long> entry : zonasOrdenadas) {
                EnumBarrio zona = entry.getKey();
                Long cantidadDistribuciones = entry.getValue();
                int beneficiariosAtendidos = beneficiariosPorZona.get(zona).size();
                tableModel.addRow(new Object[]{zona, cantidadDistribuciones, beneficiariosAtendidos});
            }
        });

        // Panel inferior con el botón Cancelar
        JPanel panelInferior = new JPanel();
        JButton btnCancelar = new JButton("Cerrar");
        btnCancelar.addActionListener(e -> internalFrame.dispose());
        panelInferior.add(btnCancelar);

        // Añadir componentes al JInternalFrame
        internalFrame.add(panelFechas, BorderLayout.NORTH);
        internalFrame.add(scrollPane, BorderLayout.CENTER);
        internalFrame.add(panelInferior, BorderLayout.SOUTH);

        // Añadir el JInternalFrame al JDesktopPane
        desktopPane.add(internalFrame);
        internalFrame.setVisible(true);
    }


    private static LocalDateTime obtenerFechaHora() {
        // Obtener la fecha y hora actual usando Calendar
        Calendar calendario = Calendar.getInstance();

        // Extraer los componentes de la fecha y hora
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        int mes = calendario.get(Calendar.MONTH) + 1; // Los meses en Calendar son 0-indexados (enero es 0)
        int anio = calendario.get(Calendar.YEAR);
        int hora = calendario.get(Calendar.HOUR_OF_DAY);
        int minutos = calendario.get(Calendar.MINUTE);

        // Crear una instancia de LocalDateTime con la fecha actual
        LocalDateTime fechaActual = LocalDateTime.of(anio, mes, dia, hora, minutos);
        return fechaActual;
    }

    private static void mostrarFormularioListarDistribucionZona(String titulo) {
        // Crear un JInternalFrame para el formulario
        JInternalFrame internalFrame = new JInternalFrame(titulo, true, true, true, true);
        internalFrame.setSize(600, 400);
        internalFrame.setLayout(new BorderLayout());
        internalFrame.setLocation(100, 100);

        // Panel para seleccionar la distribución por zona
        JPanel panelBarrio = new JPanel();
        JLabel lblBarrio = new JLabel("Seleccionar Zona:");
        JComboBox<String> cbBarrio = new JComboBox<>();


        String nullString = "";
        cbBarrio.addItem(nullString); // Opción para todas las Zonas
        panelBarrio.add(lblBarrio);
        panelBarrio.add(cbBarrio);
        for (EnumBarrio barrio : EnumBarrio.values()) {
            cbBarrio.addItem(barrio.name());
        }


        // Crear el modelo de la tabla Distribuciones
        String[] columnNames = {"Nombre", "Correo", "Fecha Entrega", "Estado", "Barrio"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable tablaDistribucionesZona = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tablaDistribucionesZona);

        cbBarrio.addActionListener((ActionEvent e) -> {
            cbBarrio.removeItem(nullString);
            String barrioSeleccionado = (String) cbBarrio.getSelectedItem();
            List<DTDistribucion> listaDistribuciones;
            // Convertir el estado seleccionado a EnumEstadoDistribucion y obtener las distribuciones por estado
            EnumBarrio barrioS = EnumBarrio.valueOf(barrioSeleccionado);
            listaDistribuciones = fabrica.getIControlador().ListarDistribucionesPorZona(barrioS);
            tableModel.setRowCount(0); // Limpiar tabla existente
            if (listaDistribuciones.isEmpty()) {
                tableModel.addRow(new Object[]{"No hay Distribuciones disponibles", "", "", ""});
                return;
            }

            for (DTDistribucion distribucion : listaDistribuciones) {
                // Obtener datos de la distribución y del beneficiario asociado
                DTUsuario usuario = fabrica.getIControlador().obtenerDTBeneficiario(distribucion.getEmailBenefAsc());

                if (usuario instanceof DTBeneficiario) {  // Aquí se usa DTBeneficiario en vez de DTUsuario
                    DTBeneficiario beneficiario = (DTBeneficiario) usuario;
                    String email = beneficiario.getEmail();
                    String nombre = beneficiario.getNombre();

                    EnumEstadoDistribucion estado = distribucion.getEstado(); // Estado de la distribución
                    LocalDateTime fechaEntrega = distribucion.getFechaEntrega();
                    EnumBarrio barrio = beneficiario.getBarrio();
                    tableModel.addRow(new Object[]{nombre, email, fechaEntrega, estado, barrio});
                }
            }

            if (tableModel.getRowCount() == 0) {
                tableModel.addRow(new Object[]{"No hay Beneficiarios para este Barrio", "", "", ""});
            }
        });

        // Panel inferior con el boton Cancelar
        JPanel panelInferior = new JPanel();
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> internalFrame.dispose());
        panelInferior.add(btnCancelar);

        // Añadir componentes al JInternalFrame
        internalFrame.add(panelBarrio, BorderLayout.NORTH);
        internalFrame.add(scrollPane, BorderLayout.CENTER);
        internalFrame.add(panelInferior, BorderLayout.SOUTH);

        // Añadir el JInternalFrame al JDesktopPane
        desktopPane.add(internalFrame);
        internalFrame.setVisible(true);
    }

    private static void mostrarFormulariomntmModificarBeneficiario(String titulo) {
        // Crear un JInternalFrame para el formulario
        JInternalFrame internalFrame = new JInternalFrame(titulo, true, true, true, true);
        internalFrame.setSize(600, 400);
        internalFrame.setLayout(new BorderLayout());
        internalFrame.setLocation(100, 100);

        // Crear el modelo de la tabla con una columna para los checkbox
        String[] columnNames = {"Seleccionar", "Nombre", "Correo", "Dirección", "Estado", "Barrio"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? Boolean.class : String.class; // La primera columna será para checkbox
            }
        };
        JTable tablaBeneficiarios = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tablaBeneficiarios);

        // Obtener la lista de beneficiarios
        List<DTUsuario> listaBeneficiarios = fabrica.getIControlador().ListarBeneficiario();

        // Limpiar tabla existente
        tableModel.setRowCount(0);

        if (listaBeneficiarios.isEmpty()) {
            tableModel.addRow(new Object[]{false, "No hay beneficiarios registrados", "", "", "", ""});
        } else {
            for (DTUsuario usuario : listaBeneficiarios) {
                if (usuario instanceof DTBeneficiario) {
                    DTBeneficiario beneficiario = (DTBeneficiario) usuario;
                    String email = beneficiario.getEmail();
                    String nombre = beneficiario.getNombre();
                    String direccion = beneficiario.getDireccion();
                    String estado = beneficiario.getEstado().toString();
                    EnumBarrio barrio = beneficiario.getBarrio();

                    tableModel.addRow(new Object[]{false, nombre, email, direccion, estado, barrio});
                }
            }
        }

        // Añadir un listener para permitir solo un checkbox seleccionado a la vez
        tablaBeneficiarios.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tablaBeneficiarios.rowAtPoint(evt.getPoint());
                int column = tablaBeneficiarios.columnAtPoint(evt.getPoint());

                if (column == 0) { // Si se hace clic en la columna de checkbox
                    // Desmarcar todos los checkboxes excepto el clicado
                    for (int i = 0; i < tablaBeneficiarios.getRowCount(); i++) {
                        if (i != row) {
                            tableModel.setValueAt(false, i, 0); // Desmarcar otras filas
                        }
                    }
                }
            }
        });

        // Panel inferior con los botones "Cancelar" y "Modificar"
        JPanel panelInferior = new JPanel();
        JButton btnCancelar = new JButton("Cancelar");
        JButton btnModificar = new JButton("Modificar");

        // Acción al presionar el botón "Modificar"
        btnModificar.addActionListener(e -> {
            // Verificar si algún beneficiario ha sido seleccionado
            boolean beneficiarioSeleccionado = false;
            DTBeneficiario benefSeleccionado = null;

            for (int i = 0; i < tableModel.getRowCount(); i++) {
                Boolean isSelected = (Boolean) tableModel.getValueAt(i, 0); // Verificar el checkbox
                if (isSelected != null && isSelected) {
                    beneficiarioSeleccionado = true;
                    String mailSeleccionado = (String) tableModel.getValueAt(i, 2); // Columna 2 es el email
                    benefSeleccionado = (DTBeneficiario) fabrica.getIControlador().obtenerDTBeneficiario(mailSeleccionado);
                    break; // Dejar de iterar después de encontrar el beneficiario seleccionado
                }
            }

            // Mostrar mensaje si no se selecciona ningún beneficiario
            if (!beneficiarioSeleccionado) {
                JOptionPane.showMessageDialog(internalFrame, "No se ha seleccionado ningún Beneficiario", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Mostrar el formulario con los datos del beneficiario seleccionado
                mostrarFormularioBeneficiarioConDatos("Modificar Beneficiario", benefSeleccionado);
                internalFrame.dispose(); // Cerrar la ventana actual después de abrir el formulario de modificación
            }
        });

        btnCancelar.addActionListener(e -> internalFrame.dispose());
        panelInferior.add(btnModificar);
        panelInferior.add(btnCancelar);

        // Añadir componentes al JInternalFrame
        internalFrame.add(scrollPane, BorderLayout.CENTER);
        internalFrame.add(panelInferior, BorderLayout.SOUTH);

        // Añadir el JInternalFrame al JDesktopPane
        desktopPane.add(internalFrame);
        internalFrame.setVisible(true);
    }

    // Metodo para mostrar el formulario con los datos del beneficiario seleccionado
    private static void mostrarFormularioBeneficiarioConDatos(String titulo, DTBeneficiario beneficiario) {
        // Reutiliza el formulario de alta, pero llenando los campos con los datos del beneficiario
        JInternalFrame internalFrame = new JInternalFrame(titulo, true, true, true, true);
        internalFrame.setSize(400, 300);
        internalFrame.setLayout(new GridLayout(10, 2));
        internalFrame.setLocation(50, 50);

        // Etiquetas y campos de texto
        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField(beneficiario.getNombre());

        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField(beneficiario.getEmail());
        txtEmail.setEditable(false); // No se puede editar el email

        JLabel lblDirecc = new JLabel("Dirección:");
        JTextField txtDirecc = new JTextField(beneficiario.getDireccion());

        // Estado
        JLabel lblEstado = new JLabel("Estado:");
        JComboBox<String> cbEstado = new JComboBox<>();
        cbEstado.addItem("Activo");
        cbEstado.addItem("Suspendido");

        // Seleccionar el estado actual del beneficiario como valor predeterminado
        cbEstado.setSelectedItem(beneficiario.getEstado().toString());

        // Barrio
        JLabel lblBarrio = new JLabel("Barrio:");
        JComboBox<String> cbBarrio = new JComboBox<>();
        cbBarrio.addItem("Centro");
        cbBarrio.addItem("Ciudad_Vieja");
        cbBarrio.addItem("Cordon");
        cbBarrio.addItem("Palermo");
        cbBarrio.addItem("Parque_Rodo");

        // Seleccionar el barrio actual del beneficiario como valor predeterminado
        cbBarrio.setSelectedItem(beneficiario.getBarrio().toString());

        // Configuración del JSpinner para seleccionar el día, mes y año
        Calendar calendar = Calendar.getInstance();
        JSpinner spnDia = new JSpinner(new SpinnerNumberModel(calendar.get(Calendar.DAY_OF_MONTH), 1, 31, 1));
        JSpinner spnMes = new JSpinner(new SpinnerNumberModel(calendar.get(Calendar.MONTH) + 1, 1, 12, 1));
        JSpinner spnAno = new JSpinner(new SpinnerNumberModel(calendar.get(Calendar.YEAR), 1900, 2100, 1));

        JLabel lblPasswd = new JLabel("Password:");
        JPasswordField txtPassword = new JPasswordField(beneficiario.getPassword());

        // Botón para mostrar/ocultar la contraseña
        JButton btnMostrarPassword = new JButton("Mostrar");
        btnMostrarPassword.addActionListener((ActionEvent e) -> {
            if (txtPassword.getEchoChar() != '\u0000') {
                txtPassword.setEchoChar('\u0000'); // Mostrar la contraseña
                btnMostrarPassword.setText("Ocultar");
            } else {
                txtPassword.setEchoChar('*'); // Ocultar la contraseña
                btnMostrarPassword.setText("Mostrar");
            }
        });

        // Botón para guardar los cambios
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener((ActionEvent e) -> {
            // Lógica para guardar las modificaciones del beneficiario
            // Guardar la información
            // Capturar la fecha de nacimiento desde los JSpinner
            int dia = (int) spnDia.getValue();
            int mes = (int) spnMes.getValue();
            int anio = (int) spnAno.getValue();
            LocalDateTime fechaNacimiento = LocalDateTime.of(anio, mes, dia, 0, 0, 0);

            // Convertir el estado y barrio seleccionados a los correspondientes Enum
            EnumEstadoBeneficiario estado = EnumEstadoBeneficiario.valueOf(cbEstado.getSelectedItem().toString().toUpperCase());
            EnumBarrio barrio = EnumBarrio.valueOf(cbBarrio.getSelectedItem().toString().toUpperCase().replace(" ", "_"));

            char[] passwordArray = txtPassword.getPassword();
            String password = new String(passwordArray);
            // Modificar Beneficirio con los datos obtenidos
            fabrica.getIControlador().modificarBeneficiario(txtNombre.getText(), txtEmail.getText(), txtDirecc.getText(), password, fechaNacimiento, estado, barrio);

            // Mensaje de operacion realizada satisfactoriamente
            JOptionPane.showMessageDialog(internalFrame, "Beneficiario modificado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            internalFrame.dispose();
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> internalFrame.dispose());

        // Añadir componentes al JInternalFrame
        internalFrame.add(lblNombre);
        internalFrame.add(txtNombre);
        internalFrame.add(lblEmail);
        internalFrame.add(txtEmail);
        internalFrame.add(lblPasswd);
        internalFrame.add(txtPassword);
        internalFrame.add(new JLabel());
        internalFrame.add(btnMostrarPassword);
        internalFrame.add(lblDirecc);
        internalFrame.add(txtDirecc);
        internalFrame.add(lblEstado);
        internalFrame.add(cbEstado);
        internalFrame.add(lblBarrio);
        internalFrame.add(cbBarrio);
        internalFrame.add(btnGuardar);
        internalFrame.add(btnCancelar);

        desktopPane.add(internalFrame);
        internalFrame.setVisible(true);
    }


    private static void mostrarFormulariomntmModificarRepartidor(String titulo) {
        // Crear un JInternalFrame para el formulario
        JInternalFrame internalFrame = new JInternalFrame(titulo, true, true, true, true);
        internalFrame.setSize(600, 400);
        internalFrame.setLayout(new BorderLayout());
        internalFrame.setLocation(100, 100);

        // Crear el modelo de la tabla con una columna
        // para los checkbox
        String[] columnNames = {"Seleccionar", "Nombre", "Email", "Licencia"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? Boolean.class : String.class; // La primera columna será para checkbox
            }
        };
        JTable tablaRepartidor = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tablaRepartidor);

        // Obtener la lista de repartidores
        List<DTUsuario> listaRepartidores = fabrica.getIControlador().ListarRepartidor();

        // Limpiar tabla existente
        tableModel.setRowCount(0);

        if (listaRepartidores.isEmpty()) {
            tableModel.addRow(new Object[]{false, "No hay repartidores registrados", "", ""});
        } else {
            for (DTUsuario usuario : listaRepartidores) {
                if (usuario instanceof DTRepartidor) {
                    DTRepartidor repartidor = (DTRepartidor) usuario;
                    String email = repartidor.getEmail();
                    String nombre = repartidor.getNombre();
                    String numLicencia = repartidor.getNumeroLicencia();

                    tableModel.addRow(new Object[]{false, nombre, email, numLicencia});
                }
            }
        }

        // Añadir un listener para permitir solo un checkbox seleccionado a la vez
        tablaRepartidor.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tablaRepartidor.rowAtPoint(evt.getPoint());
                int column = tablaRepartidor.columnAtPoint(evt.getPoint());

                if (column == 0) { // Si se hace clic en la columna de checkbox
                    // Desmarcar todos los checkboxes excepto el clicado
                    for (int i = 0; i < tablaRepartidor.getRowCount(); i++) {
                        if (i != row) {
                            tableModel.setValueAt(false, i, 0); // Desmarcar otras filas
                        }
                    }
                }
            }
        });

        // Panel inferior con los botones "Cancelar" y "Modificar"
        JPanel panelInferior = new JPanel();
        JButton btnCancelar = new JButton("Cancelar");
        JButton btnModificar = new JButton("Modificar");

        // Acción al presionar el botón "Modificar"
        btnModificar.addActionListener(e -> {
            // Verificar si algún repartidor ha sido seleccionado
            boolean repartidorSeleccionado = false;
            DTRepartidor repSeleccionado = null;

            for (int i = 0; i < tableModel.getRowCount(); i++) {
                Boolean isSelected = (Boolean) tableModel.getValueAt(i, 0); // Verificar el checkbox
                if (isSelected != null && isSelected) {
                    repartidorSeleccionado = true;
                    String mailSeleccionado = (String) tableModel.getValueAt(i, 2); // Columna 2 es el email
                    repSeleccionado = (DTRepartidor) fabrica.getIControlador().obtenerDTRepartidor(mailSeleccionado);
                    break; // Dejar de iterar después de encontrar el repartidor seleccionado
                }
            }

            // Mostrar mensaje si no se selecciona ningún repartidor
            if (!repartidorSeleccionado) {
                JOptionPane.showMessageDialog(internalFrame, "No se ha seleccionado ningún Repartidor", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Mostrar el formulario con los datos del repartidor seleccionado
                mostrarFormularioRepartidorConDatos("Modificar Repartidor", repSeleccionado);
                internalFrame.dispose(); // Cerrar la ventana actual después de abrir el formulario de modificación
            }
        });

        btnCancelar.addActionListener(e -> internalFrame.dispose());
        panelInferior.add(btnModificar);
        panelInferior.add(btnCancelar);

        // Añadir componentes al JInternalFrame
        internalFrame.add(scrollPane, BorderLayout.CENTER);
        internalFrame.add(panelInferior, BorderLayout.SOUTH);

        // Añadir el JInternalFrame al JDesktopPane
        desktopPane.add(internalFrame);
        internalFrame.setVisible(true);

    }

    private static void mostrarFormularioRepartidorConDatos(String titulo, DTRepartidor repartidor) {
        // Reutiliza el formulario de alta, pero llenando los campos con los datos del repartidor
        JInternalFrame internalFrame = new JInternalFrame(titulo, true, true, true, true);
        internalFrame.setSize(400, 200);
        internalFrame.setLayout(new GridLayout(6, 2));
        internalFrame.setLocation(50, 50);

        // Etiquetas y campos de texto
        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField(repartidor.getNombre());

        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField(repartidor.getEmail());
        txtEmail.setEditable(false); // No se puede editar el email

        JLabel lblLicencia = new JLabel("Licencia:");
        JTextField txtLicencia = new JTextField(repartidor.getNumeroLicencia());

        JLabel lblPasswd = new JLabel("Password:");
        JPasswordField txtPassword = new JPasswordField(repartidor.getPassword());

        // Botón para mostrar/ocultar la contraseña
        JButton btnMostrarPassword = new JButton("Mostrar");
        btnMostrarPassword.addActionListener((ActionEvent e) -> {
            if (txtPassword.getEchoChar() != '\u0000') {
                txtPassword.setEchoChar('\u0000'); // Mostrar la contraseña
                btnMostrarPassword.setText("Ocultar");
            } else {
                txtPassword.setEchoChar('*'); // Ocultar la contraseña
                btnMostrarPassword.setText("Mostrar");
            }
        });

        // Botón para guardar los cambios
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener((ActionEvent e) -> {
            String nuevaLicencia = txtLicencia.getText();

            // Verificar si la licencia ya existe
            if (fabrica.getIControlador().existeLicencia(nuevaLicencia)) {
                // Mostrar mensaje de error si la licencia ya existe
                JOptionPane.showMessageDialog(internalFrame, "El número de licencia ya existe. Por favor, elige otro.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                char[] passwordArray = txtPassword.getPassword();
                String password = new String(passwordArray);
                // Modificar repartidor con los datos obtenidos
                fabrica.getIControlador().modificarRepartidor(txtNombre.getText(), txtEmail.getText(), password, nuevaLicencia);

                // Mensaje de operación realizada satisfactoriamente
                JOptionPane.showMessageDialog(internalFrame, "Repartidor modificado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                internalFrame.dispose();
            }
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> internalFrame.dispose());

        // Añadir componentes al JInternalFrame
        internalFrame.add(lblNombre);
        internalFrame.add(txtNombre);
        internalFrame.add(lblEmail);
        internalFrame.add(txtEmail);
        internalFrame.add(lblPasswd);
        internalFrame.add(txtPassword);
        internalFrame.add(new JLabel());
        internalFrame.add(btnMostrarPassword);
        internalFrame.add(lblLicencia);
        internalFrame.add(txtLicencia);
        internalFrame.add(btnGuardar);
        internalFrame.add(btnCancelar);

        desktopPane.add(internalFrame);
        internalFrame.setVisible(true);
    }

    private static void mostrarFormularioAbout(String title) {
        // Crear JFrame
        JFrame frame;
        JTabbedPane tabbedPane;
        JPanel infoPanel;
        JPanel licensesPanel;

        frame = new JFrame(title);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null); // Centrar en pantalla
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear pestañas
        tabbedPane = new JTabbedPane();

        // Crear panel de información
        infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        JLabel appLabel = new JLabel("Aplicación: Ayudemos");
        appLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JLabel dateLabel = new JLabel("Fecha de publicación: 12/09");
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel membersLabel = new JLabel("Integrantes:");
        membersLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JTextArea membersText = new JTextArea(
                "Victoria Pilone, CI: 4.809.214-6\n" +
                        "Ezequiel Medina, CI: 5.527.291-7\n" +
                        "Damaso Tor, CI: 4.508.724-7\n" +
                        "Maikol Brion, CI: 5.050.007-2\n" +
                        "Eric Zachow, CI: 3.166.104-3"
        );
        membersText.setEditable(false);
        membersText.setFont(new Font("Arial", Font.PLAIN, 13));
        membersText.setBackground(infoPanel.getBackground());

        infoPanel.add(appLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        infoPanel.add(dateLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        infoPanel.add(membersLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        infoPanel.add(membersText);

        // Crear panel de licencias
        licensesPanel = new JPanel();
        licensesPanel.setLayout(new BoxLayout(licensesPanel, BoxLayout.Y_AXIS));

        JLabel gnuLabel = new JLabel("Licencia: GNU 3.0");
        gnuLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel mitLink = new JLabel("<html><a href=''>Licencia MIT</a></html>");
        mitLink.setFont(new Font("Arial", Font.PLAIN, 13));
        mitLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mitLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new java.net.URI("https://opensource.org/licenses/MIT"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JLabel gnuLink = new JLabel("<html><a href=''>Licencia GNU 3.0</a></html>");
        gnuLink.setFont(new Font("Arial", Font.PLAIN, 13));
        gnuLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gnuLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new java.net.URI("https://www.gnu.org/licenses/gpl-3.0.html"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JLabel mavenLabel = new JLabel("Licencias de terceros (Maven 21):");
        mavenLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JTextArea thirdPartyLicenses = new JTextArea(
                "JUnit 3.8.1\n" +
                        "MySQL Connector/J 9.0.0 - Licencia: MIT\n" +
                        "Hibernate Core 6.6.0.Final - Licencia: MIT\n" +
                        "Hibernate EntityManager 5.6.15.Final - Licencia: MIT\n" +
                        "javax.persistence 2.2.0 - Licencia: MIT\n"
        );
        thirdPartyLicenses.setEditable(false);
        thirdPartyLicenses.setFont(new Font("Arial", Font.PLAIN, 13));
        thirdPartyLicenses.setBackground(licensesPanel.getBackground());

        licensesPanel.add(gnuLabel);
        licensesPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        licensesPanel.add(gnuLink);
        licensesPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        licensesPanel.add(mavenLabel);
        licensesPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        licensesPanel.add(thirdPartyLicenses);
        licensesPanel.add(mitLink);

        // Añadir los paneles a las pestañas
        tabbedPane.addTab("Información", infoPanel);
        tabbedPane.addTab("Licencias", licensesPanel);

        // Añadir el TabbedPane al JFrame
        frame.add(tabbedPane);

        // Hacer visible la ventana
        frame.setVisible(true);
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
