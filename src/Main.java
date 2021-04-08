import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	
	public static Estructuras estruct;
																			// .. Núm Fallas 
	public final static String UNO = "./data/Uno.txt";   					// .. 778
	public final static String DOS = "./data/Dos.txt";						// .. 595
	public final static String TRES = "./data/Tres.txt";					// .. 752
	public final static String CUATRO = "./data/Cuatro.txt";				// .. 581

	public static void main(String[] args) throws IOException {
		
		FileReader f = new FileReader(CUATRO);
		BufferedReader b = new BufferedReader(f);
		
		int numPagRam = Integer.parseInt(b.readLine().trim());
		int numPagPro = Integer.parseInt(b.readLine().trim());
		// ..int principLoca = Integer.parseInt(b.readLine().trim());

		b.close(); 
		
		estruct = new Estructuras(numPagRam, numPagPro);
		new Envejecedor(estruct).start();
		new Actualizador(estruct, CUATRO).start();
		
	}

}
