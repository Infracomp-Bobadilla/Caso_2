import java.util.Arrays;

public class Estructuras {

	private Integer[] paginasRAM; 
	private Integer[] paginasProceso;
	private String[] envejecimiento;

	private int fallas;
	private boolean stop = false;

	public Estructuras (int pagRam, int pagProc) {
		fallas = 0;

		paginasRAM = new Integer[pagRam];
		for (int i = 0; i < paginasRAM.length; i++) paginasRAM[i] = -1;

		paginasProceso = new Integer[pagProc];
		for (int i = 0; i < paginasProceso.length; i++) paginasProceso[i] = 0;

		envejecimiento = new String[pagProc];
		for (int i = 0; i < envejecimiento.length; i++) envejecimiento[i] = "000000000000000000000000000000";

	}

	public synchronized int darFallas () {
		return fallas;
	}
	
	public synchronized boolean darStop() {
		return stop;
	}
	
	public synchronized void stop() {
		stop = true;
	}

	public synchronized void algoEnvejecer () {
		for (int i = 0; i < paginasProceso.length; i++) {
			envejecimiento[i] = "" + paginasProceso[i] + envejecimiento[i];
			envejecimiento[i] = envejecimiento[i].substring(0, 29);
		}
		
		// ... //
		
		for(int i = 0; i < envejecimiento.length; i++) System.out.println(i + ": " + envejecimiento[i]);
		System.out.println("------------------------");
	}

	public synchronized void cargarPaginaRam (int pagina) {

		int index = Arrays.asList(paginasRAM).indexOf(pagina); 

		if(index == -1) {
			fallas++;
			
			int espacioVacio = Arrays.asList(paginasRAM).indexOf(-1);
			if (espacioVacio != -1){
				paginasRAM[espacioVacio] = pagina;
			}
			else {

				String valorMasViejo = "1111111111111111111111111111111";
				int posPaginaMasVieja = -1;

				for (int i = 0; i < paginasRAM.length; i++) {

					String envejActual = envejecimiento[paginasRAM[i]];
					if(envejActual.compareTo(valorMasViejo) < 0) {
						valorMasViejo = envejActual;
						posPaginaMasVieja = i;
					}
				}

				paginasRAM[posPaginaMasVieja] = pagina;
			}
			
			// ... //
			
			System.out.println("La página: " + pagina + " ~ Genero la falla núm: " + fallas);
			System.out.println("------------------------");

		}
		
		for(int i = 0; i < paginasProceso.length; i++) paginasProceso[i] = 0;
		paginasProceso[pagina] = 1;

	}
}
