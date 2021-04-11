
public class Envejecedor extends Thread
{
	public Estructuras estruct;					// .. Monitor sobre el cual va a trabajar este Thread
	
	public Envejecedor (Estructuras comun) {	// .. Definición por contructor del monitor
		estruct = comun;					
	}
	
	public void run() {
		
		while(!estruct.darStop()) {				// .. Ejecuta hasta que el 'Actualizador' le diga que ya se acabaron las páginas
			estruct.algoEnvejecer();			// .. Actualiza los contadore
			
			try { Thread.sleep(1); } 			// .. Se duerme 1 milisegundo, así se logra envejecer cada milisegundo
			catch (InterruptedException e) { e.printStackTrace(); }
		}
	}
}
