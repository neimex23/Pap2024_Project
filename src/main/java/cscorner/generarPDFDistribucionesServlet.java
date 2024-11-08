package cscorner;

// Imports para generar PDF
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

// Resto de los imports
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;
import org.pap.publicadores.*;

public class generarPDFDistribucionesServlet extends HttpServlet {

    private ControladorPublishService cps = new ControladorPublishServiceLocator();
    private ControladorPublish controlador;

    public generarPDFDistribucionesServlet() {
        super();
        try {
            controlador = cps.getControladorPublishPort();
        } catch (ServiceException e) {
            e.printStackTrace();
            // Manejo de error aquí
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"distribuciones.pdf\"");

        // Obtener las distribuciones pendientes
        DtDistribucion[] distribuciones = controlador.listarDistribuciones();

        List<DtDistribucion> lstDistribuciones = Arrays.asList(distribuciones);
        List<DtDistribucion> distribucionesParaPDF = lstDistribuciones;

        try {
            // Crear el documento PDF
            Document document = new Document(PageSize.A4, 36, 36, 36, 36); // Ajusta los márgenes a 36 puntos en cada lado
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            
            // Agregar fecha de generación a la parte superior derecha
            String fechaGeneracion = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
            Paragraph fechaParaPDF = new Paragraph(fechaGeneracion);
            fechaParaPDF.setAlignment(Element.ALIGN_RIGHT);  // Alinea el texto a la derecha
            document.add(fechaParaPDF);  // Agrega la fecha al documento
            
            // Espacio entre la fecha y el titulo
            document.add(new Paragraph(" "));

            // Crear una fuente personalizada para el título
            Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD, BaseColor.BLACK);
            // Crear el párrafo con el título y la fuente personalizada
            Paragraph titleParagraph = new Paragraph("Listado de Todas las Distribuciones", titleFont);
            // Alinear el título al centro
            titleParagraph.setAlignment(Element.ALIGN_CENTER);
            // Agregar el párrafo al documento
            document.add(titleParagraph);

            // Espacio entre el titulo y la tabla
            document.add(new Paragraph(" "));

            // Creacion de la tabla personalizada
            
            // Crear tabla con 6 columnas
            PdfPTable table = new PdfPTable(6); // Número de columnas
            table.setWidthPercentage(100);  // Esto hará que la tabla ocupe el 100% del ancho de la página
            table.setWidths(new float[]{1, 5, 3, 4, 4, 3});  // Establecer el ancho de cada columna

            // Estilo de las celdas de los encabezados
            Font headerFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

            // Crear las celdas del encabezado con el color de fondo
            PdfPCell ID = new PdfPCell(new Phrase("ID", headerFont));
            ID.setBackgroundColor(BaseColor.GRAY);
            table.addCell(ID);

            PdfPCell Email = new PdfPCell(new Phrase("Email Beneficiario", headerFont));
            Email.setBackgroundColor(BaseColor.GRAY);
            table.addCell(Email);

            PdfPCell Estado = new PdfPCell(new Phrase("Estado", headerFont));
            Estado.setBackgroundColor(BaseColor.GRAY);
            table.addCell(Estado);

            PdfPCell fEntrega = new PdfPCell(new Phrase("Fecha Entrega", headerFont));
            fEntrega.setBackgroundColor(BaseColor.GRAY);
            table.addCell(fEntrega);

            PdfPCell fPreparacion = new PdfPCell(new Phrase("Fecha Preparación", headerFont));
            fPreparacion.setBackgroundColor(BaseColor.GRAY);
            table.addCell(fPreparacion);

            PdfPCell Donacion = new PdfPCell(new Phrase("ID Donación", headerFont));
            Donacion.setBackgroundColor(BaseColor.GRAY);
            table.addCell(Donacion);

            // Llenar la tabla con las distribuciones
            Font contentFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);
            for (DtDistribucion distribucion : distribucionesParaPDF) {
                // Centrar el ID
                String idDistribucion = String.valueOf(distribucion.getId());
                PdfPCell IdDistr = new PdfPCell(new Phrase(idDistribucion, contentFont));
                IdDistr.setHorizontalAlignment(Element.ALIGN_CENTER);  // Centrar el texto
                table.addCell(IdDistr);                
                
                // Agregar el email
                table.addCell(new PdfPCell(new Phrase(distribucion.getEmailBenefAsc(), contentFont)));
                
                // Agregar el estado
                table.addCell(new PdfPCell(new Phrase(distribucion.getEstado().toString(), contentFont)));
                
                // Formatear Fecha de Entrega y centrarla
                String fEntregaOriginal = distribucion.getFechaEntrega();
                String fechaFormateada1 = fEntregaOriginal.replace("T", " - ");
                PdfPCell fechaEntrega = new PdfPCell(new Phrase(fechaFormateada1, contentFont));
                fechaEntrega.setHorizontalAlignment(Element.ALIGN_CENTER);  // Centrar el texto
                table.addCell(fechaEntrega);
                
                // Formatear Fecha de Preparación y centrarla
                String fPreparacionOriginal = distribucion.getFechaPreparacion();
                String fechaFormateada2 = fPreparacionOriginal.replace("T", " - ");
                PdfPCell fechaPreparacion = new PdfPCell(new Phrase(fechaFormateada2, contentFont));
                fechaPreparacion.setHorizontalAlignment(Element.ALIGN_CENTER);  // Centrar el texto
                table.addCell(fechaPreparacion);
                
                // Agregar el id de donacion 
                table.addCell(new PdfPCell(new Phrase(String.valueOf(distribucion.getDonacionAsc()), contentFont)));
            }

            // Agregar la tabla al documento
            document.add(table);

            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

}


