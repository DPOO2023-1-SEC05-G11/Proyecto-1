package Persistencia;

public class Hotel {
	
	private static int contadorFactura;
	

	public static int contadorFacturas() {
		// TODO Auto-generated method stub
		int contador = contadorFactura;
		contadorFactura ++;
		return contador;
	}

}
