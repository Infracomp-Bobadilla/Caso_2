import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Actualizador extends Thread
{
	public Estructuras estruct; 				// .. Monitor sobre el cual va a trabajar este Thread
	public String ruta;							// .. Ruta del archivo sobre el cual trabajar
	public String nombre;						// .. Nombre del arvhivo 
	
	public Actualizador (Estructuras comun, String pRuta) {		// .. Definición por contructor del monitor y archivo
		estruct = comun;
		ruta = pRuta;
		nombre = this.ruta.split("/")[3];		// .. Nombre dado por la ruta en la organización del proyecto
	}
	
	public void run() {
		
		// .. Necesario para la lectura del archivo		
		FileReader f = null;
		BufferedReader b = null;
		String cadena = "";
		int contador = 0;
		
		try {
			f = new FileReader(this.ruta); 		// .. Leo el archivo con la seuncia
			b = new BufferedReader(f);
			cadena = b.readLine();
		} 
		catch (IOException e) { e.printStackTrace(); }
		

		while(cadena != null) 
		{	    	
			if(contador > 2) {														// .. Evito los datos de configuración
				this.estruct.cargarPaginaRam(Integer.parseInt(cadena.trim()));		// .. Cargo la pagina que me llega
				
				try { Thread.sleep(5);} 											// .. Cargo cada 5 milisegundos
				catch (InterruptedException e) { e.printStackTrace(); }
			}
			
			contador++;
			try { cadena = b.readLine(); } 											// .. Avanzo a la siguiente linea
			catch (IOException e) { e.printStackTrace(); }
		}

		try { b.close(); } 											// .. Una vez ya leí todo, cierro el archivo
		catch (IOException e) { e.printStackTrace(); } 
		
		estruct.stop();														// .. Notifico que pare al Thread 'Envejecedor'
		System.out.println(this.nombre + "," + this.estruct.darFallas());	// .. Imprimo el resultado

	}
}
