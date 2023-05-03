package Modelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class ReservaEstadia {

	private ArrayList<Consumo> consumos;
	private ArrayList<Huesped> grupo;
	private ArrayList<Habitacion> habitaciones;
	
	private Huesped huespedPrincipal;
	
	private LocalDate fechaInicial;
	private LocalDate fechaFinal;
	private int duracionEstadia;
	
	public ReservaEstadia(LocalDate fecha, int duracionEstadia, Huesped huespedPrincipal, ArrayList<Habitacion> habitacionesReservadas) {
		this.fechaInicial = fecha;
		this.duracionEstadia = duracionEstadia;
		this.huespedPrincipal = huespedPrincipal;
		this.habitaciones = habitacionesReservadas;
		this.consumos = new ArrayList<>();
		fechaFinal = fechaInicial.plusDays(duracionEstadia-1);
		
		
	}
	

	public void checkIn(ArrayList<Huesped> huespedes)
	{
		this.grupo = huespedes;
		
		//for habs, hab-->ocupado
		for (Habitacion hab : habitaciones)
		{
			if (hab.isOcupado())
			{
				System.out.println("There is a room in this reservation that is already occupied.");
			}
			//Ocupa las habitaciones de la reserva
			hab.updateOcupado(true);
			hab.setReservaActual(this);
		}
		
	}
	
	public void checkOut()
	{
		//for habs, hab-->no ocupado
		for (Habitacion hab : habitaciones)
		{
			hab.updateOcupado(false);
			hab.setReservaActual(null);
		}
		//generar factura
	}
	
	public ArrayList<Consumo> getConsumos()
	{
		return this.consumos;
	}
	
	public Huesped getHuespedPrincipal()
	{
		return this.huespedPrincipal;
	}

	public ArrayList<Huesped> huespedes()
	{
		return this.grupo;
	}
	
	public LocalDate getFechaInicial()
	{
		return this.fechaInicial;
	}
	
	public LocalDate getFechaFinal()
	{
		return this.fechaFinal;
	}
	
	public int getDuracion()
	{
		return this.duracionEstadia;
	}
	
	
}
