package Modelo;

public class HuespedPrincipal extends Huesped{
	
	private int idReserva;

	public HuespedPrincipal(String nombre, String documento, String email, String telefono, int idReserva) {
		super(nombre, documento, email, telefono);
		this.idReserva = idReserva;
	}
	
	public int getIdReserva()
	{
		return idReserva;
	}

}
