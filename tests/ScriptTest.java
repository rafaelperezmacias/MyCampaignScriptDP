package tests;

import models.*;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ScriptTest {

    private static ArrayList<State> states = new ArrayList<>();
    private static int federalDistricts = 1;
    private static int localDistricts = 1;
    private static int municipalities = 1;
    private static int sections = 1;

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
                // Tokenizamos la cadena
                StringTokenizer tokenizer = new StringTokenizer(line, ",");
                // Informacion del estado
                State newState = new State();
                newState.setId(Integer.parseInt(tokenizer.nextToken()));
                newState.setName(tokenizer.nextToken());
                // Informacion del distrito federal
                FederalDistrict newFederalDistrict = new FederalDistrict();
                newFederalDistrict.setNumber(Integer.parseInt(tokenizer.nextToken()));
                newFederalDistrict.setName(tokenizer.nextToken());
                // Informacion del distrito local
                LocalDistrict newLocalDistrict = new LocalDistrict();
                newLocalDistrict.setNumber(Integer.parseInt(tokenizer.nextToken()));
                newLocalDistrict.setName(tokenizer.nextToken());
                // Informacion del municipio
                Municipality newMunicipality = new Municipality();
                newMunicipality.setNumber(Integer.parseInt(tokenizer.nextToken()));
                newMunicipality.setName(tokenizer.nextToken());
                // Informacion de la seccion
                Section newSection = new Section();
                newSection.setSection(Integer.parseInt(tokenizer.nextToken()));
                // Tratamiento para los datos
                // Estado
                boolean insertState = true;
                for ( State state : states ) {
                    if ( state.getId() == newState.getId() ) {
                        insertState = false;
                        newState = state;
                        break;
                    }
                }
                if ( insertState ) {
                    states.add(newState);
                }
                // Distrito Federal
                boolean insertFederalDistrict = true;
                for ( FederalDistrict federalDistrict : newState.getFederalDistricts() ) {
                    if ( federalDistrict.getNumber() == newFederalDistrict.getNumber() ) {
                        newFederalDistrict = federalDistrict;
                        insertFederalDistrict = false;
                        break;
                    }
                }
                if ( insertFederalDistrict ) {
                    newFederalDistrict.setId(federalDistricts++);
                    newState.getFederalDistricts().add(newFederalDistrict);
                }
                // Distrito local
                boolean insertLocalDistrict = true;
                for ( LocalDistrict localDistrict : newState.getLocalDistricts() ) {
                    if ( localDistrict.getNumber() == newLocalDistrict.getNumber() ) {
                        newLocalDistrict = localDistrict;
                        insertLocalDistrict = false;
                        break;
                    }
                }
                if ( insertLocalDistrict ) {
                    newLocalDistrict.setId(localDistricts++);
                    newState.getLocalDistricts().add(newLocalDistrict);
                }
                // Municipio
                boolean insertMunicipality = true;
                for ( Municipality municipality : newState.getMunicipalities() ) {
                    if ( municipality.getNumber() == newMunicipality.getNumber() ) {
                        insertMunicipality = false;
                        newMunicipality = municipality;
                        break;
                    }
                }
                if ( insertMunicipality ) {
                    newMunicipality.setId(municipalities++);
                    newState.getMunicipalities().add(newMunicipality);
                }
                // Seccion
                boolean insertSecction = true;
                for ( Section section : newState.getSections() ) {
                    if ( section.getSection() == newSection.getSection() ) {
                        insertSecction = false;
                        newSection = section;
                        break;
                    }
                }
                if ( insertSecction ) {
                    newSection.setId(sections++);
                    newSection.setState(newState);
                    newSection.setMunicipality(newMunicipality);
                    newSection.setLocalDistrict(newLocalDistrict);
                    newSection.setFederalDistrict(newFederalDistrict);
                    newState.getSections().add(newSection);
                }
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
        System.out.println();
        System.out.println("Informacion obtenida");
        System.out.println();
        System.out.println("Estados: " + states.size());
        System.out.println("Distritos federales: " + federalDistricts);
        System.out.println("Distritos locales: " + localDistricts);
        System.out.println("Municipios: " + municipalities);
        System.out.println("Secciones: " + sections);
        System.out.println();
        for ( State state : states ) {
            System.out.println("*** " + state.getId() + " - " + state.getName() + " ***");
            System.out.println("Distritos federales: " + state.getFederalDistricts().size());
            System.out.println("Distritos locales: " + state.getLocalDistricts().size());
            System.out.println("Municipios: " + state.getMunicipalities().size());
            System.out.println("Secciones: " + state.getSections().size());
            System.out.println();
        }
    }

}
