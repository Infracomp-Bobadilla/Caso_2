import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	
	public static Estructuras estruct;
																			// .. Núm Fallas Correcto   --  Nuestro
	public final static String UNO = "./data/Uno.txt";   					// .. 587						588-594 (+1 ~ +7)
	public final static String DOS = "./data/Dos.txt";						// .. 577						573-577 (-4 ~ 0)
	public final static String TRES = "./data/Tres.txt";					// .. 758						755-758 (-3 ~ 0)
	public final static String CUATRO = "./data/Cuatro.txt";				// .. 340						325-347	(-15 ~ +7)	
	public final static String PRUEBA_UNO = "./data/referencias4_8.txt";	// .. 5							5
	public final static String PRUEBA_DOS = "./data/referencias4_16.txt";	// .. 12						12

	public static void main(String[] args) throws IOException {
		
		FileReader f = new FileReader(CUATRO);
		BufferedReader b = new BufferedReader(f);
		
		int numPagRam = Integer.parseInt(b.readLine().trim());
		int numPagPro = Integer.parseInt(b.readLine().trim());

		b.close(); 
		
		estruct = new Estructuras(numPagRam, numPagPro);
		new Envejecedor(estruct).start();
		new Actualizador(estruct, CUATRO).start();
		
		// ... CKECK MANUAL 
		
//		estruct = new Estructuras(4, 8);
//		
//		System.out.println("Llega 0");
//		estruct.cargarPaginaRam(0);
//		estruct.algoEnvejecer();
//		
//		System.out.println("Llega 0");
//		estruct.cargarPaginaRam(0);
//		estruct.algoEnvejecer();
//		
//		System.out.println("Llega 0");
//		estruct.cargarPaginaRam(0);
//		estruct.algoEnvejecer();
//		
//		System.out.println("Llega 0");
//		estruct.cargarPaginaRam(0);
//		estruct.algoEnvejecer();
//		
//		System.out.println("Llega 5");
//		estruct.cargarPaginaRam(5);
//		estruct.algoEnvejecer();
//		
//		System.out.println("Llega 3");
//		estruct.cargarPaginaRam(3);
//		estruct.algoEnvejecer();
//		
//		System.out.println("Llega 6");
//		estruct.cargarPaginaRam(6);
//		estruct.algoEnvejecer();
//		
//		System.out.println("Llega 6");
//		estruct.cargarPaginaRam(6);
//		estruct.algoEnvejecer();
//		
//		System.out.println("Llega 5");
//		estruct.cargarPaginaRam(5);
//		estruct.algoEnvejecer();
//		
//		System.out.println("Llega 4");
//		estruct.cargarPaginaRam(4);
//		estruct.algoEnvejecer();
//		
//		System.out.println("Llega 6");
//		estruct.cargarPaginaRam(6);
//		estruct.algoEnvejecer();
//		
//		System.out.println("Llega 5");
//		estruct.cargarPaginaRam(5);
//		estruct.algoEnvejecer();
//		
//		System.out.println("Llega 6");
//		estruct.cargarPaginaRam(6);
//		estruct.algoEnvejecer();
//		
//		System.out.println("Llega 5");
//		estruct.cargarPaginaRam(5);
//		estruct.algoEnvejecer();
//		
//		System.out.println("Llega 6");
//		estruct.cargarPaginaRam(6);
//		estruct.algoEnvejecer();
//		
//		System.out.println("Llega 5");
//		estruct.cargarPaginaRam(5);
//		estruct.algoEnvejecer();
//		
//		System.out.println(estruct.darFallas());
		
	}

}
