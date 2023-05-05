package Persistencia;

import java.util.ArrayList;

import Modelo.*;

public class Hotel {
	
	private static int contadorFactura;
	
	private static Servicio restaurante;
	private static Servicio spa;
	private static Servicio guia;
	
	private static ArrayList<Habitacion> habitaciones;
	
	private static ArrayList<ReservaEstadia> reservas;
	
	

	public static int contadorFacturas() {
		// TODO Auto-generated method stub
		int contador = contadorFactura;
		contadorFactura ++;
		return contador;
	}
	
	public Hotel getInstance()
	{
		return new Hotel();
	}
	
	

}
