public class Main {
    public static void main(String[] args) {

        String rutaCSV = "C:\\Users\\kokit\\Desktop\\CESUR 2º AÑO\\JAVA\\Ejercicio Practico Evaluable Tema 1\\src\\main\\java\\peliculas.csv"; // Cambia esto por la ruta de tu archivo

        FiltroPeliculas filtro = new FiltroPeliculas();

        filtro.filtrarPorGenero(rutaCSV, "Ciencia ficción");

        filtro.filtrarPorGenero(rutaCSV, "Acción"); // Este de prueba para comprobar que funciona
                                                           // con otros géneros también

        filtro.filtrarPorGenero(rutaCSV, "Napolitana"); // Este para comprobar que aunque no exista
                                                               // el género, te lo crea igual vacío.
    }
}
