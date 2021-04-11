import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	
	// ... Monitor sobre el cual se va a sincronizar y contiene el algoritmo de Envejecimiento
	public static Estructuras estruct;
																					// .. Núm Fallas Correcto   --  Nuestro
	public final static String UNO = "./data/prueba/Uno.txt";   					// .. 587						586-594 (-1 ~ +7)
	public final static String DOS = "./data/prueba/Dos.txt";						// .. 577						573-577 (-4 ~ 0)
	public final static String TRES = "./data/prueba/Tres.txt";						// .. 758						755-758 (-3 ~ 0)
	public final static String CUATRO = "./data/prueba/Cuatro.txt";					// .. 340						325-347	(-15 ~ +7)	
	public final static String PRUEBA_UNO = "./data/prueba/referencias4_8.txt";		// .. 5							5
	public final static String PRUEBA_DOS = "./data/prueba/referencias4_16.txt";	// .. 12						12

	// .. Ejecusión 
	public static void main(String[] args) throws IOException {
		
		// .. Lectura de uno de los archivos y registro de configuraciones
		FileReader f = new FileReader(TRES);
		BufferedReader b = new BufferedReader(f);
		
		int numPagRam = Integer.parseInt(b.readLine().trim());
		int numPagPro = Integer.parseInt(b.readLine().trim());

		b.close(); 
		
		estruct = new Estructuras(numPagRam, numPagPro);		// .. Creación del monitor con las configuraciones
		new Envejecedor(estruct).start();						// .. Thread 'Envejecedor'
		new Actualizador(estruct, TRES).start();				// .. Thread 'Actualizador'
		
	}

}
