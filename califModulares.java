// Importing required libraries
import java.io.BufferedReader; // For reading text from a file efficiently
import java.util.ArrayList;    // To use dynamic lists (for names and grades)
import java.util.Scanner;      // For taking user input if needed later
import java.io.FileReader;     // To read files from disk
import java.io.IOException;    // To handle input/output exceptions

public class califModulares {
    // Create a Scanner object for potential console input
    static Scanner scan = new Scanner(System.in);

    // Function to manually read student names from user input (not used here but kept for modularity)
    static String[] leerNombres(int cantA) {
        String[] nombres = new String[10]; // Fixed array size of 10
        for (int i = 0; i < cantA; i++) {
            System.out.println("Ingrese el nombre del alumno " + (i + 1));
            nombres[i] = scan.nextLine(); // Read name from user
        }
        return nombres; // Return filled array
    }

    // Function to manually read grades from user input (not used here but reusable)
    static float[][] leerCalif(int cantA, int cantC, String[] nombres) {
        float[][] calif = new float[10][10]; // Fixed 10x10 array
        for (int i = 0; i < cantA; i++) {
            for (int j = 0; j < cantC; j++) {
                System.out.println("Ingrese la calificación No. " + (j + 1) + " del alumno " + nombres[i]);
                calif[i][j] = scan.nextFloat(); // Read grade
                scan.nextLine(); // Clean input buffer
            }
        }
        return calif; // Return filled matrix
    }

    // Function to calculate the overall average
    static float calcularProm(float[][] calif, int cantA, int cantC) {
        float prom = 0, sumProm = 0;
        int cantCal = cantA * cantC; // Total number of grades
        for (int i = 0; i < cantA; i++) {
            for (int j = 0; j < cantC; j++) {
                sumProm += calif[i][j]; // Accumulate sum
            }
        }
        prom = sumProm / cantCal; // Calculate average
        return prom;
    }

    // Function to find the highest grade and return its position
    static int[] masAlta(int cantA, int cantC, float[][] calif) {
        float hst = 0;
        int[] indices = new int[2]; // [student index, grade index]
        for (int i = 0; i < cantA; i++) {
            for (int j = 0; j < cantC; j++) {
                if (calif[i][j] > hst) {
                    hst = calif[i][j]; // Store new highest grade
                    indices[0] = i;
                    indices[1] = j;
                }
            }
        }
        return indices;
    }

    // Function to find the lowest grade and return its position
    static int[] masBaja(int cantA, int cantC, float[][] calif) {
        float lwst = 100;
        int[] indices = new int[2];
        for (int i = 0; i < cantA; i++) {
            for (int j = 0; j < cantC; j++) {
                if (calif[i][j] < lwst) {
                    lwst = calif[i][j]; // Store new lowest grade
                    indices[0] = i;
                    indices[1] = j;
                }
            }
        }
        return indices;
    }

    // Function to print all results in a readable format
    static void imprimirResultados(int cantA, int cantC, int[] indexAlta, int[] indexBaja, String[] nombres, float[][] calif, float promedio) {
        for (int i = 0; i < cantA; i++) {
            System.out.println(nombres[i] + ":");
            for (int j = 0; j < cantC; j++) {
                System.out.print(calif[i][j] + "\t"); // Print each grade
            }
            System.out.print("\n");
        }
        System.out.println("El promedio general de las calidifaciones ingresadas fue: " + promedio);
        System.out.println("La calificacion más alta obtenida fue por el alumno " + nombres[indexAlta[0]] + " con: " + calif[indexAlta[0]][indexAlta[1]]);
        System.out.println("La calificacion más baja obtenida fue por el alumno " + nombres[indexBaja[0]] + " con: " + calif[indexBaja[0]][indexBaja[1]]);
    }

    // Main execution starts here
    public static void main(String[] args) {
        String filename = "datos.txt"; // File containing names and grades

        // Create dynamic lists to store data read from file
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<float[]> gradeList = new ArrayList<>();

        // Try reading from the file
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            // Read each line until end of file
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); // Split line by commas
                nameList.add(parts[0]); // First element is the name

                // Create array for that student's grades
                float[] grades = new float[parts.length - 1];
                for (int i = 1; i < parts.length; i++) {
                    grades[i - 1] = Float.parseFloat(parts[i]); // Convert to float
                }
                gradeList.add(grades); // Add to list
            }
        } catch (IOException e) {
            // Handle errors (e.g., file not found)
            System.out.println("Error reading the file: " + e.getMessage());
        }

        // Prepare arrays for modular functions
        int cantC = gradeList.get(0).length; // Number of grades per student
        int cantA = nameList.size();         // Number of students
        String[] nombres = new String[cantA];
        float[][] grades = new float[cantA][cantC];

        // Convert lists to arrays
        for (int i = 0; i < cantA; i++) {
            nombres[i] = nameList.get(i);       // Copy name
            grades[i] = gradeList.get(i);       // Copy grade array
        }

        // Use modular functions to analyze data
        float promedio = calcularProm(grades, cantA, cantC);
        int[] indexAlta = masAlta(cantA, cantC, grades);
        int[] indexBaja = masBaja(cantA, cantC, grades);

        // Display results
        imprimirResultados(cantA, cantC, indexAlta, indexBaja, nombres, grades, promedio);
    }
}
