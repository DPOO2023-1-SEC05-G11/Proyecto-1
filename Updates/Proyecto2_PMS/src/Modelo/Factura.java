package Modelo;

import java.util.ArrayList;

import Persistencia.Hotel;

public class Factura {
	private int id;
	private int precioTotal;
	private ArrayList<Integer> preciosHabs;
	private ArrayList<Integer> preciosHuespedes;
	private int precioTotalGrupal;
	
	
	
	public Factura(ReservaEstadia registro)
	{
		id = Hotel.contadorFacturas() + 1;
	}
	
	public Factura(Consumo consumo)
	{
		id = Hotel.contadorFacturas() + 1;
	}
	
	//Crear las informaciones necesarias para la factura
	//Imprimir Factura
	//Guardar la factura en un .txt
	
	@Override
	public String toString()
	{
		return "id";
	}
	
}
