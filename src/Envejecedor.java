
public class Envejecedor extends Thread
{
	public static Estructuras estruct;
	
	public Envejecedor (Estructuras comun) {
		estruct = comun;
	}
	
	public void run() {
		
		while(!estruct.darStop()) {
			estruct.algoEnvejecer();
			
			try { Thread.sleep(1); } 
			catch (InterruptedException e) { e.printStackTrace(); }
		}
	}
}
