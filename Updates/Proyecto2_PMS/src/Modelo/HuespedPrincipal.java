package Modelo;

public class HuespedPrincipal extends Huesped{
	
	private int idReserva;

	public HuespedPrincipal(String nombre, String documento, String email, String telefono) {
		super(nombre, documento, email, telefono);
		// TODO Auto-generated constructor stub
	}
	
	public int getIdReserva()
	{
		return idReserva;
	}

}
