import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Escenarios {

	public static Estructuras estruct;
	public static int contador = 0;

	public static void main(String[] args) throws IOException {

		String nombreArchivo = "";

		for (int i = 1; i < 100; i++) {

			nombreArchivo = "./data/escenarios/RAM_" + i + ".txt";

			FileReader f = new FileReader(nombreArchivo);
			BufferedReader b = new BufferedReader(f);

			int numPagRam = Integer.parseInt(b.readLine().trim());
			int numPagPro = Integer.parseInt(b.readLine().trim());

			b.close(); 

			estruct = new Estructuras(numPagRam, numPagPro);
			new Envejecedor(estruct).start();
			new Actualizador(estruct, nombreArchivo).start();
		}
		
		for (int i = 10; i < 110; i++) {

			nombreArchivo = "./data/escenarios/Proceso_" + i + ".txt";

			FileReader f = new FileReader(nombreArchivo);
			BufferedReader b = new BufferedReader(f);

			int numPagRam = Integer.parseInt(b.readLine().trim());
			int numPagPro = Integer.parseInt(b.readLine().trim());

			b.close(); 

			estruct = new Estructuras(numPagRam, numPagPro);
			new Envejecedor(estruct).start();
			new Actualizador(estruct, nombreArchivo).start();
		}
		
		for (int i = 5; i < 100; i = i + 5) {

			nombreArchivo = "./data/escenarios/Localidad_" + i + ".txt";

			FileReader f = new FileReader(nombreArchivo);
			BufferedReader b = new BufferedReader(f);

			int numPagRam = Integer.parseInt(b.readLine().trim());
			int numPagPro = Integer.parseInt(b.readLine().trim());

			b.close(); 

			estruct = new Estructuras(numPagRam, numPagPro);
			new Envejecedor(estruct).start();
			new Actualizador(estruct, nombreArchivo).start();
		}


	}
}