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

import com.toedter.calendar.JDateChooser;
import org.pap.dtClasses.*;
import org.pap.Enums.*;
import org.pap.interfaces.Fabrica;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
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
        
         // Crear y añadir el elemento de menú "Listar Distribucion por zona"
        JMenuItem mntmListDistribucionZona = new JMenuItem("Listar distribuciones por Zona");
        mntmListDistribucionZona.addActionListener((ActionEvent arg0) -> {
            mostrarFormularioListarDistribucionZona("Listar Distribucion por Zona");
        });
        mnListar.add(mntmListDistribucionZona);
        
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
        
        // Mostrar el cuadro de diálogo de inicio de sesión
        // Hacer visible el JFrame
        ventanaP.setVisible(true);

        Fabrica.getInstancia().getIControlador().cargarBaseDatos();
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
        JComboBox<String> cbEstado = new JComboBox<String>();
        cbEstado.setBounds(5, 5, 5, 5);
        cbEstado.addItem("Activo");
        cbEstado.addItem("Suspendido");

        JComboBox<String> cbCiudad = new JComboBox<String>();
        cbCiudad.setBounds(5, 5, 5, 5);
        cbCiudad.addItem("Centro");
        cbCiudad.addItem("Ciudad vieja");
        cbCiudad.addItem("Cordon");
        cbCiudad.addItem("Palermo");
        cbCiudad.addItem("Parque Rodo");

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
                if(cantBeneficiario>=50) { // Si se alzanzo el limite de usuarios se manda mensaje de error
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
                        fabrica.getIControlador().altaBeneficiario(txtNombre.getText(), txtEmail.getText(), txtDirecc.getText(), fechaNacimiento, estado, barrio);
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
        internalFrame.setSize(300, 140);
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

                if (fabrica.getIControlador().conGetCantRepartidores() >= 5) { // Si se alzanzo el limite de usuarios se manda mensaje de error
                    JOptionPane.showMessageDialog(null, "Se ha alcanzado el limite de Repartidores", "Error", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    if (fabrica.getIControlador().existeEmail(txtEmail.getText())) {
                        JOptionPane.showMessageDialog(null, "El beneficiario ya existe.", "Érror", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        // Guardar la información
                        if (!fabrica.getIControlador().existeLicencia(txtLicencia.getText())) {
                            fabrica.getIControlador().altaRepartidor(txtNombre.getText(), txtEmail.getText(), txtLicencia.getText());

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
        // Crear un JInternalFrame para el formulario
        JInternalFrame internalFrame = new JInternalFrame(titulo, true, true, true, true);
        internalFrame.setSize(600, 400);
        internalFrame.setLayout(new BorderLayout());
        internalFrame.setLocation(100, 100);

        // Panel para el estado de distribución
        JPanel panelEstado = new JPanel();
        JLabel lblEstado = new JLabel("Estado de Distribución:");
        JComboBox<String> cbEstado = new JComboBox<>();
        String nullString = "";
        cbEstado.addItem(nullString);
        cbEstado.addItem("Todas");  // Opción para todas las distribuciones

        panelEstado.add(lblEstado);
        panelEstado.add(cbEstado);
        for (EnumEstadoDistribucion estado : EnumEstadoDistribucion.values()) {
            cbEstado.addItem(estado.name());
        }
        // Tabla para mostrar las distribuciones
        String[] columnNames = {"Fecha Preparación", "Fecha Entrega", "Tipo", "Descripción"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable tablaDistribuciones = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tablaDistribuciones);

        // Actualizar la tabla según el estado seleccionado
        cbEstado.addActionListener((ActionEvent e) -> {
            cbEstado.removeItem(nullString);
            String estadoSeleccionado = (String) cbEstado.getSelectedItem();

            List<DTDistribucion> distribuciones;
            if ("Todas".equals(estadoSeleccionado)) {
                // Obtener todas las distribuciones si se selecciona "Todas"
                distribuciones = fabrica.getIControlador().listarDistribuciones();
            } else {
                // Convertir el estado seleccionado a EnumEstadoDistribucion y obtener las distribuciones por estado
                EnumEstadoDistribucion estado = EnumEstadoDistribucion.valueOf(estadoSeleccionado);
                distribuciones = fabrica.getIControlador().listarDistribucionesPorEstado(estado);
            }

            tableModel.setRowCount(0); // Limpiar tabla existente

            if (distribuciones.isEmpty()) {
                tableModel.addRow(new Object[]{"No hay distribuciones disponibles", "", "", ""});
                return;
            }

            distribuciones.forEach((DTDistribucion distribucion) -> {
                DTDonacion donacion = fabrica.getIControlador().obtenerDonacion(distribucion.getId());

                String tipo;
                if (donacion instanceof DTAlimento) {
                    tipo = "Alimento";
                } else if (donacion instanceof DTArticulo) {
                    tipo = "Artículo";
                } else {
                    tipo = "Desconocido";
                }

                String descripcion;
                switch (donacion) {
                    case DTAlimento dTAlimento ->
                        descripcion = dTAlimento.getDescProducto();
                    case DTArticulo dTArticulo ->
                        descripcion = dTArticulo.getDescr();
                    default ->
                        descripcion = "N/A";
                }

                tableModel.addRow(new Object[]{
                    distribucion.getFechaPreparacion().toString(),
                    distribucion.getFechaEntrega().toString(),
                    tipo,
                    descripcion
                });
            });

            if (tableModel.getRowCount() == 0) {
                tableModel.addRow(new Object[]{"No hay distribuciones para este estado", "", "", ""});
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

        List<DTDistribucion> distribuciones = fabrica.getIControlador().listarDistribuciones();
        if (distribuciones.isEmpty()) {
            cbDistribuciones.addItem("No hay distribuciones disponibles");
            cbDistribuciones.setEnabled(false);
        } else {
            for (DTDistribucion distribucion : distribuciones) {
                // Obtener la donación correspondiente a la distribución
                DTDonacion donacion = fabrica.getIControlador().obtenerDonacion(distribucion.getDonacionAsc());

                // Declarar la variable descripcion
                String descripcion;

                // Determinar el tipo de donación y establecer la descripción
                switch (donacion) {
                    case DTAlimento dTAlimento ->
                        descripcion = dTAlimento.getDescProducto();
                    case DTArticulo dTArticulo ->
                        descripcion = dTArticulo.getDescr();
                    default ->
                        descripcion = "Descripción no disponible";
                }

                // Agregar la descripción al JComboBox
                cbDistribuciones.addItem(distribucion.getId() + " - " + descripcion);
            }

        }

        panelSeleccion.add(lblSeleccion);
        panelSeleccion.add(cbDistribuciones);

        // Panel para los detalles de la distribución seleccionada
        JPanel panelDetalles = new JPanel(new GridLayout(4, 2));

        // Fecha de Entrega
        JLabel lblFechaEntrega = new JLabel("Fecha de Entrega:");
        JSpinner spinnerFechaEntrega = new JSpinner(new SpinnerDateModel());

        int distIndex = cbDistribuciones.getSelectedIndex();
        LocalDateTime fechaEntregaDate = distribuciones.get(distIndex).getFechaEntrega();

        int year = fechaEntregaDate.getYear();
        int month = fechaEntregaDate.getMonthValue(); // El mes ya viene como 1-12, no hay que restar 1.
        int day = fechaEntregaDate.getDayOfMonth(); // Utiliza getDayOfMonth() en lugar de getDayOfYear().

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day); // Aquí sí restamos 1 al mes, ya que Calendar usa 0-11 para los meses.
        Date date = calendar.getTime();
        spinnerFechaEntrega.setValue(date);

        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinnerFechaEntrega, "dd-MM-yyyy");
        spinnerFechaEntrega.setEditor(dateEditor);



        // Estado
        JLabel lblEstado = new JLabel("Estado:");
        JComboBox<String> cbEstado = new JComboBox<>();
        for (EnumEstadoDistribucion estado : EnumEstadoDistribucion.values()) {
            cbEstado.addItem(estado.name());
        }


        cbEstado.setSelectedItem(distribuciones.get(distIndex).getEstado().name());

        // Añadir componentes al panel de detalles
        panelDetalles.add(lblFechaEntrega);
        panelDetalles.add(spinnerFechaEntrega);
        panelDetalles.add(lblEstado);
        panelDetalles.add(cbEstado);

        // Botón para guardar cambios
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            String seleccion = (String) cbDistribuciones.getSelectedItem();
            if (seleccion != null && !seleccion.equals("No hay distribuciones disponibles")) {
                int idDistribucion = Integer.parseInt(seleccion.split(" - ")[0]);
                LocalDateTime fechaEntrega = ((Date) spinnerFechaEntrega.getValue()).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();
                String estadoSeleccionado = (String) cbEstado.getSelectedItem();
                EnumEstadoDistribucion estado = EnumEstadoDistribucion.valueOf(estadoSeleccionado);

                // Actualizar la distribución
                fabrica.getIControlador().modificarDistribucion(idDistribucion, fechaEntrega, estado);

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
