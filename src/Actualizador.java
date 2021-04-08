import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Actualizador extends Thread
{
	public static Estructuras estruct;
	public static String ruta;
	
	public Actualizador (Estructuras comun, String pRuta) {
		estruct = comun;
		ruta = pRuta;
	}
	
	public void run() {
		
		FileReader f = null;
		BufferedReader b = null;
		String cadena = "";
		int contador = 0;
		
		try {
			f = new FileReader(ruta);
			b = new BufferedReader(f);
			cadena = b.readLine();
		} 
		catch (IOException e) { e.printStackTrace(); }
		

		while(cadena != null) 
		{	    	
			if(contador > 2) {
				estruct.cargarPaginaRam(Integer.parseInt(cadena.trim()));
				
				try { Thread.sleep(5);} 
				catch (InterruptedException e) { e.printStackTrace(); }
			}
			
			contador++;
			try { cadena = b.readLine(); } 
			catch (IOException e) { e.printStackTrace(); }
		}

		try { b.close(); } 
		catch (IOException e) { e.printStackTrace(); } 
		
		estruct.stop();
		System.out.println(estruct.darFallas());
	}
}
