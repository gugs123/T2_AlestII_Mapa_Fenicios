public class Temporizador {
    private static long tempoIni = 0, tempoFim = 0;

    public static void initClock() {
		tempoIni = tempoFim = System.nanoTime();
	}

    public static double getClockSec() {
		double res;

		tempoFim = System.nanoTime();

		res =  ((double)tempoFim - (double)tempoIni) / (double)1_000_000_000.0;

		return res;
	}

}
