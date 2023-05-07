package Modelo;

import java.util.ArrayList;
import java.util.HashMap;

import Persistencia.Hotel;

public class Factura {
	private int id;
	private String tipo;
	private String tipoServ;
	private Double precioTotal;
	private HashMap<String, Double> preciosHuespedes = new HashMap<String, Double>();
	private HashMap<Integer, Double> preciosHabs = new HashMap<Integer, Double>();
	private ArrayList<Integer> infosHuesped;
	private Double precioTotalGrupal = 0.0;
	
	
	
	public Factura(ReservaEstadia reserva)
	{
		id = Hotel.contadorFacturas();
		this.tipo = "reserva";
		for (Habitacion hab : reserva.getHabitaciones())
		{
			preciosHabs.put(hab.getId(), hab.getTarifa());
		}
		for (Consumo consumo : reserva.getConsumos())
		{
			if (consumo.getTipo().equals("individual"))
			{
				if (preciosHuespedes.containsKey(consumo.getHuesped().getNombre()))
				{
					preciosHuespedes.put(consumo.getHuesped().getNombre(), preciosHuespedes.get(consumo.getHuesped().getNombre()) + (double) consumo.getPrecioTotal());
				}else {
					preciosHuespedes.put(consumo.getHuesped().getNombre(), (double) consumo.getPrecioTotal());
				}
				
			}else if (consumo.getTipo().equals("grupo"))
			{
				precioTotalGrupal += (double) consumo.getPrecioTotal();
			}else if (consumo.getTipo().equals("habitacion"))
			{
				for (Habitacion hab : reserva.getHabitaciones())
				{
					
				}
			}
		}
		
	}
	
	public Factura(Consumo consumo)
	{
		this.id = Hotel.contadorFacturas();
		this.tipo = "consumo";
		this.tipoServ = consumo.getServicio().getName();
		this.precioTotal = (double) consumo.getPrecioTotal();
		//this.infosHuesped.add(consumo.getHuesped().getNombre());
		System.out.println(toStringConsumo());
	}
	
	//Crear las informaciones necesarias para la factura
	//Imprimir Factura
	//Guardar la factura en un .txt
	
	
	public String toStringConsumo()
	{
		return "id=" + this.id + ", tipo=" + this.tipoServ + ", precioTotal=" + this.precioTotal + "; huesped=" + this.infosHuesped;
	}
	
}
