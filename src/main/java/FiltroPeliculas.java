import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FiltroPeliculas {

    /**
     * Lee un archivo CSV y devuelve una lista de películas.
     *
     * @param archivoCSV La ruta del archivo CSV a leer.
     * @return Una lista de arrays, donde cada array es una película.
     */
    private static List<String[]> leerCSV(String archivoCSV) {
        List<String[]> peliculas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                // Aquí separo los campos de la película (ID, nombre,
                // año, director y género) por comas y lo guardo en una variable (linea).
                peliculas.add(linea.split(","));
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }

        return peliculas;
    }

    /**
     * Escribe las películas en un archivo CSV.
     *
     * @param archivoSalida La ruta del archivo CSV donde se escribirán las películas.
     * @param peliculas     La lista de películas a escribir, donde cada película es un arreglo de cadenas.
     */
    private static void escribirCSV(String archivoSalida, List<String[]> peliculas) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoSalida))) {
            for (String[] pelicula : peliculas) {
                StringBuilder linea = new StringBuilder();

                for (int i = 0; i < pelicula.length; i++) {
                    linea.append(pelicula[i]);

                    if (i < pelicula.length - 1) {
                        linea.append(",");
                    } // Hago que cada campo de la película se separe por una coma, menos al final
                }
                bw.write(linea.toString());

                bw.newLine(); // Añado un salto de línea
            }

        } catch (IOException e) {
            System.out.println("Error al escribir el archivo CSV: " + e.getMessage());
        }
    }

    /**
     * Filtra las películas y escribe las películas filtradas en un nuevo archivo CSV.
     *
     * @param archivoCSV La ruta del archivo CSV de origen que contiene todas las películas.
     * @param genero     El género por el cual se desea filtrar las películas.
     */

    public void filtrarPorGenero(String archivoCSV, String genero) {

        // Gracias al método leerCSV hecho previamente, me llegan las películas
        // con sus campos separados por la coma, y cada una en su posición,
        // así puedo localizar el campo del género más fácilmente.

        List<String[]> peliculas = leerCSV(archivoCSV);
        List<String[]> peliculasFiltradas = new ArrayList<>();

        for (String[] pelicula : peliculas) {

            // El género está en la posición 4
            // Cada posición se delimita por una coma.
            // Tambiém comprueba que el texto de la posición 4 coincida con el género.

            if (pelicula.length > 4 && pelicula[4].trim().equalsIgnoreCase(genero)) {
                peliculasFiltradas.add(pelicula);
            }
        }

        if (peliculasFiltradas.isEmpty()) {

            escribirCSV(genero + ".csv", peliculasFiltradas);
            System.out.println("No hay películas de ese género, pero el archivo se ha creado igualmente.");

        } else {

            escribirCSV(genero + ".csv", peliculasFiltradas);
            System.out.println("Nuevo archivo filtrado creado.");
        }
    }
}
