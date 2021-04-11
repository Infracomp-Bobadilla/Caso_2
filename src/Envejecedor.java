
public class Envejecedor extends Thread
{
	public Estructuras estruct;					// .. Monitor sobre el cual va a trabajar este Thread
	
	public Envejecedor (Estructuras comun) {	// .. Definici�n por contructor del monitor
		estruct = comun;					
	}
	
	public void run() {
		
		while(!estruct.darStop()) {				// .. Ejecuta hasta que el 'Actualizador' le diga que ya se acabaron las p�ginas
			estruct.algoEnvejecer();			// .. Actualiza los contadore
			
			try { Thread.sleep(1); } 			// .. Se duerme 1 milisegundo, as� se logra envejecer cada milisegundo
			catch (InterruptedException e) { e.printStackTrace(); }
		}
	}
}
