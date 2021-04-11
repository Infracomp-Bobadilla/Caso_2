import java.util.Arrays;

public class Estructuras {
	
	// .. Parametros

	private Integer[] paginasRAM; 		// .. N�mero de marcos de p�gina en memoria RAM que el sistema le asigna al proceso
	private Integer[] paginasProceso;	// .. N�mero de p�ginas del proceso
	private String[] envejecimiento;	// .. Arreglo de contadores de cada pagina

	private int fallas;					// .. Registro de fallas
	private boolean stop = false;		// .. Parada de sincronizaci�n de los Threads
	
	// .. Constructor 
	
	public Estructuras (int pagRam, int pagProc) {
		fallas = 0;															// .. Incialmente no hay fallas

		paginasRAM = new Integer[pagRam];
		for (int i = 0; i < paginasRAM.length; i++) paginasRAM[i] = -1;		// .. Ninguna p�gina cargada en RAM historicamente

		paginasProceso = new Integer[pagProc];
		for (int i = 0; i < paginasProceso.length; i++) paginasProceso[i] = 0; 		// .. Ning�na p�gina cargada historicamente

		envejecimiento = new String[pagProc];										// .. Contador de (30 bits) en cero
		for (int i = 0; i < envejecimiento.length; i++) envejecimiento[i] = "000000000000000000000000000000";

	}
	
	// .. Dar el n�mero de fallas

	public synchronized int darFallas () {
		return fallas;
	}
	
	// .. Dar se�al de parar
	
	public synchronized boolean darStop() {
		return stop;
	}
	
	// .. Actualizar el valor para que pare
	
	public synchronized void stop() {
		stop = true;
	}
	
	// ..... Actualizar contadores de las p�ginas cargadas recientemente. 

	public synchronized void algoEnvejecer () {
		for (int i = 0; i < paginasProceso.length; i++) {						// .. Para cada una de las p�ginas
			envejecimiento[i] = "" + paginasProceso[i] + envejecimiento[i];		// .. Registro si se cargo o no de ultimas a la izquierda
			envejecimiento[i] = envejecimiento[i].substring(0, 29);				// .. Dejo unicamente los 30 bits m�s significativos
		}
		
		// .. for(int i = 0; i < envejecimiento.length; i++) System.out.println(i + ": " + envejecimiento[i]);
		// .. System.out.println("------------------------");
	}

	public synchronized void cargarPaginaRam (int pagina) {

		int index = Arrays.asList(paginasRAM).indexOf(pagina); 		// .. Se mira si la p�gina que se busca cargar ya esta cargada.

		if(index == -1) {											// .. En caso de que no lo este.
			fallas++;												// .. Se registra que ocurrio una falla
			
			// .. Primeras p�ginas cargadas (hay espacios vacios ~ -1) 
			int espacioVacio = Arrays.asList(paginasRAM).indexOf(-1);		
			if (espacioVacio != -1){ paginasRAM[espacioVacio] = pagina; }
			// .. Cuando ya no hay espacios vacios
			else {

				String valorMasViejo = "1111111111111111111111111111111";	// .. Variable aux con un n�mero mayor a todo posible
				int posPaginaMasVieja = -1;									// .. Variable aux para registrar la posici�n de la p�g a sacar

				for (int i = 0; i < paginasRAM.length; i++) {				// .. Se busca entre todas las cargadas en RAM la menos usada

					String envejActual = envejecimiento[paginasRAM[i]];		// .. Se obtiene su contador
					if(envejActual.compareTo(valorMasViejo) < 0) {			// .. Se compara. Al ser compareTo se empieza por la izquierda. 
						valorMasViejo = envejActual;						// .. Se actualiza el menor
						posPaginaMasVieja = i;								// .. Se actualiza las posici�n del menor
					}
				}
				paginasRAM[posPaginaMasVieja] = pagina;						// .. En la posici�n del menor absoluto se carga la nueva p�gina
			}
		}
		
		// .. Siempre se actualiza la p�gina cargada, sin importar que haya o no fallo. 
		for(int i = 0; i < paginasProceso.length; i++) paginasProceso[i] = 0;	// .. Se dejan todas las p�ginas en 0
		paginasProceso[pagina] = 1;												// .. Se pone 1 en la cargada. '�ltima Cargada'

	}
}
