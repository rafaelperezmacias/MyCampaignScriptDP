package tests;

import models.*;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ScriptTest {

    private static ArrayList<State> states = new ArrayList<>();
    private static int federalDistricts = 0;
    private static int localDistricts = 0;
    private static int municipalities = 0;
    private static int sections = 0;

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
                newState.setId(Integer.parseInt(tokenizer.nextToken().trim()));
                newState.setName(tokenizer.nextToken().trim());
                // Informacion del distrito federal
                FederalDistrict newFederalDistrict = new FederalDistrict();
                newFederalDistrict.setNumber(Integer.parseInt(tokenizer.nextToken().trim()));
                newFederalDistrict.setName(tokenizer.nextToken().trim());
                // Informacion del distrito local
                LocalDistrict newLocalDistrict = new LocalDistrict();
                newLocalDistrict.setNumber(Integer.parseInt(tokenizer.nextToken().trim()));
                newLocalDistrict.setName(tokenizer.nextToken().trim());
                // Informacion del municipio
                Municipality newMunicipality = new Municipality();
                newMunicipality.setNumber(Integer.parseInt(tokenizer.nextToken().trim()));
                newMunicipality.setName(tokenizer.nextToken().trim());
                // Informacion de la seccion
                Section newSection = new Section();
                newSection.setSection(Integer.parseInt(tokenizer.nextToken().trim()));
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
                    newFederalDistrict.setId(++federalDistricts);
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
                    newLocalDistrict.setId(++localDistricts);
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
                    newMunicipality.setId(++municipalities);
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
                    newSection.setId(++sections);
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

        // Generacion de los archivos txt para las inserciones
        generateFileForStates();
        generateFileForFederalDistricts();
        generateFileForLocalDistricts();
        generateFileForMunicipalities();
        generateFileForSections();

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

    private static void generateFileForStates() {
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO `states`(`id`, `name`) VALUES ").append("\n");
        for ( State state : states ) {
            builder.append("(").append(state.getId()).append(",'").append(state.getName()).append("'), ").append("\n");
        }
        builder.replace(builder.length() - ", \n".length(), builder.length(), ";");
        writeToDisk(builder.toString(), "states.txt");
    }

    private static void generateFileForFederalDistricts() {
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO `federal_districts`(`id`, `name`, `number`) VALUES ").append("\n");
        for ( State state : states ) {
            for ( FederalDistrict federalDistrict : state.getFederalDistricts() ) {
                builder.append("(").append(federalDistrict.getId()).append(",'").append(federalDistrict.getName());
                builder.append("', ").append(federalDistrict.getNumber()).append("), ").append("\n");
            }
        }
        builder.replace(builder.length() - ", \n".length(), builder.length(), ";");
        writeToDisk(builder.toString(), "federalDistricts.txt");
    }

    private static void generateFileForLocalDistricts() {
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO `local_districts`(`id`, `name`, `number`) VALUES ").append("\n");
        for ( State state : states ) {
            for ( LocalDistrict localDistrict : state.getLocalDistricts() ) {
                builder.append("(").append(localDistrict.getId()).append(",'").append(localDistrict.getName());
                builder.append("', ").append(localDistrict.getNumber()).append("), ").append("\n");
            }
        }
        builder.replace(builder.length() - ", \n".length(), builder.length(), ";");
        writeToDisk(builder.toString(), "localDistricts.txt");
    }

    private static void generateFileForMunicipalities() {
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO `municipalities`(`id`, `name`, `number`) VALUES ").append("\n");
        for ( State state : states ) {
            for ( Municipality municipality : state.getMunicipalities() ) {
                builder.append("(").append(municipality.getId()).append(",'").append(municipality.getName());
                builder.append("', ").append(municipality.getNumber()).append("), ").append("\n");
            }
        }
        builder.replace(builder.length() - ", \n".length(), builder.length(), ";");
        writeToDisk(builder.toString(), "municipalities.txt");
    }

    private static void generateFileForSections() {
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO `sections`(`id`, `section`, `state_id`, `municipality_id`, `federal_district_id`, `local_district_id`) VALUES ").append("\n");
        int row = 0;
        for ( State state : states ) {
            for ( Section section : state.getSections() ) {
                builder.append("(").append(section.getId()).append(", '").append(section.getSection()).append("', ");
                builder.append(state.getId()).append(", ").append(section.getMunicipality().getId()).append(", ");
                builder.append(section.getFederalDistrict().getId()).append(", ").append(section.getLocalDistrict().getId());
                builder.append("), ").append("\n");
                if ( ++row == 2500 ) {
                    builder.replace(builder.length() - ", \n".length(), builder.length(), ";");
                    builder.append("\n");
                    builder.append("INSERT INTO `sections`(`id`, `section`, `state_id`, `municipality_id`, `federal_district_id`, `local_district_id`) VALUES ").append("\n");
                    row = 0;
                }
            }
        }
        builder.replace(builder.length() - ", \n".length(), builder.length(), ";");
        writeToDisk(builder.toString(), "sections.txt");
    }

    private static void writeToDisk(String text, String path) {
        File file = new File(path);
        File folder = file.getParentFile();
        if ( folder != null && !folder.exists() ) {
            folder.mkdirs();
        }
        if ( !file.exists() ) {
            try {
                file.createNewFile();
            } catch ( IOException ex ) {
                System.out.println("Error al crear el archivo");
                System.out.println(ex.getMessage());
                System.exit(1);
            }
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch ( FileNotFoundException ex ) {
            System.out.println("Error al abrir el archivo");
            System.out.println(ex.getMessage());
            System.exit(1);
        }
        try {
            fileOutputStream.write(text.getBytes());
        } catch ( IOException ex ) {
            System.out.println("Error al escribir en el archivo");
            System.out.println(ex.getMessage());
            System.exit(1);
        }
        try {
            fileOutputStream.close();
        } catch ( IOException ex ) {
            System.out.println("Error al cerrar el archivo");
            System.out.println(ex.getMessage());
            System.exit(1);
        }
    }
}
