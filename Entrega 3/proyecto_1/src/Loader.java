import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface Loader {
	
	private void cargarHabitaciones()
	{
		try
		{
			File habitaciones = new File("data/x.txt");
		}
		catch (IOException e)
		{
			System.out.println("ERROR: hubo un problema leyendo el archivo.");
			System.out.println(e.getMessage());
		}
	}
	
	private void addHabitacion(Habitacion room)
	{
		
	}
	
	private void updateHabitaciones()
	{
		try
		{
			File habitaciones = new File("data/x.txt");
		}
		catch (IOException e)
		{
			System.out.println("ERROR: hubo un problema leyendo el archivo.");
			System.out.println(e.getMessage());
		}
	}
	
	private void removeHabitacion(Habitacion room)
	{
		
	}
	
	private void buscarHabitacion(Habitacion room)
	{
		
	}
	
	private void addServicio(Servicio service)
	{
		
	}
	
	private void updateServicio(Servicio service)
	{
		
	}
	
	private void removeServicio(Servicio service)
	{
		
	}
	
	private void buscarServicio(Servicio service)
	{
		
	}
	
	private void addTarifa(Tarifa fee)
	{
		
	}
	
	private void updateTarifa(Tarifa service)
	{
		
	}
	
	private void addMenu(Menu menu)
	{
		
	}
	
	private void updateMenu(Menu service)
	{
		
	}
	
	private void removeMenu(Menu service)
	{
		
	}
	
	private void buscarMenu(Menu service)
	{
		
	}
	
	public default void createReserva(Reserva reserva) 
	{
		
	}
	
	public default void mostrarReserva(Reserva reserva) 
	{
		
	}
}
