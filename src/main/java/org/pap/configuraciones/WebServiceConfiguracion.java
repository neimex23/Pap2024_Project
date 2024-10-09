package org.pap.configuraciones;

import java.io.*;
import java.util.HashMap;

public class WebServiceConfiguracion {

    private String path = System.getProperty("user.home") + "/.Ayudemos/.properties";  // Ruta del archivo de configuración
    private HashMap<String, String> configs;  // Almacenará las configuraciones cargadas

    public WebServiceConfiguracion() throws Exception {
        configs = new HashMap<>();

        // Crear el archivo si no existe
        File file = new File(path);
        if (!file.exists()) {
            crearArchivoConfiguracion(file);
        }

        // Leer el archivo de configuración
        System.out.println("Cargando configuraciones desde: " + path);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String properties;
            while((properties = reader.readLine()) != null){
                if(!properties.startsWith("#") && properties.contains("=")) {  // Ignorar líneas comentadas o inválidas
                    String[] div = properties.split("=", 2);  // Dividir en clave y valor
                    configs.put(div[0].trim(), div[1].trim());  // Guardar clave y valor en el HashMap
                }
            }
        } catch (Exception e) {
            throw new IOException("Error al leer el archivo de configuración", e);
        }
    }

    // Método para obtener una configuración por su clave
    public String getConfigOf(String nombre) {
        return configs.get(nombre);
    }

    // Crear el archivo de configuración con valores predeterminados si no existe
    private void crearArchivoConfiguracion(File file) throws IOException {
        file.getParentFile().mkdirs();  // Crear directorios necesarios

        try (FileWriter writer = new FileWriter(file)) {
            writer.write("# Archivo de configuración de WebService\n");
            writer.write("db.url=jdbc:mysql://localhost:3306/papdb\n");
            writer.write("db.username=root\n");
            writer.write("db.password=root\n");
            writer.write("db.driver=com.mysql.cj.jdbc.Driver\n");
            writer.write("webservice.url=https://api.ejemplo.com\n");
            writer.write("webservice.timeout=5000\n");
            writer.write("webservice.retry=3\n");
        }

        System.out.println("Archivo de configuración creado exitosamente en: " + file.getPath());
    }

}
