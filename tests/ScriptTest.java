package tests;

import models.State;

import java.io.*;
import java.util.ArrayList;

public class ScriptTest {

    private static ArrayList<State> states = new ArrayList<>();

    public static void main(String[] args) {
        // Apertura del archivo
        BufferedReader buffered = null;
        FileReader reader = null;
        try {
            reader = new FileReader("D:\\RafaelPM\\Java\\MyCampaignScriptDP\\src\\files\\elecciones-2021.csv");
            buffered = new BufferedReader(reader);
        } catch ( FileNotFoundException ex ) {
            System.out.println("Ruta del archivo no encontrada");
            System.out.println(ex.getMessage());
            System.exit(1);
        }
        // Omitimos la primera línea (encabezados del archivo csv)
        try {
            buffered.readLine();
        } catch ( IOException ex ) {
            System.out.println("Error al leer la primera linea del archivo");
            System.out.println(ex.getMessage());
            System.exit(1);
        }
        // Leeemos cada una de las líneas del csv
        String line;
        int lines = 1;
        try {
            while ( (line = buffered.readLine()) != null ) {
                System.out.println(line);
                lines++;
            }
        } catch ( IOException ex ) {
            System.out.println("Error al leer la primera linea del archivo");
            System.out.println(ex.getMessage());
            System.exit(1);
        }
        // Cerramos el buffer
        try {
            buffered.close();
            reader.close();
        } catch ( IOException ex ) {
            System.out.println("Error al cerrar el archivo");
            System.out.println(ex.getMessage());
            System.exit(1);
        }

        System.out.println("Lineas leidas: " + lines);
    }

}
