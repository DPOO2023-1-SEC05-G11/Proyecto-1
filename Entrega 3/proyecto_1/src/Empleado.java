import java.util.*;

public class Empleado extends Usuario
{
	private String cargo;
	
	public Empleado(String login, String password, String cargo) {
		super(login, password);
		this.cargo = cargo;
	}

	public String getCargo() {
		return cargo;
	}
	
	public void crearReserva(int fecha, int duracionEstadia, Grupo grupoAsociado, ArrayList<Habitacion> huespedesAsociados)
	{
		
	}
	
	public void mostrarInfoReserva(Reserva booked) 
	{
		
	}
	
	public void checkIn(Reserva booked) 
	{
		
	}
	
	public Factura checkOut() 
	{
		return null;
	}
}