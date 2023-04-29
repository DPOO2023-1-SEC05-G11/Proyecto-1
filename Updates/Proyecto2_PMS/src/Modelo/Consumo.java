package Modelo;

public class Consumo {

	private Servicio servicio;
	private Double precioTotal;
	private String tipo; //individual/grupal/habitacional
	private Huesped huesped;
	private Boolean yaPagado;
	
	public Consumo(Servicio serv)
	{
		
	}

	
	
	private void agregarProducto(Double precio)
	{
		
	}
	
	private void facturar()
	{
		if (yaPagado)
		{
			Factura factura = new Factura(this);
			
		}else
		{
			agregarAReservaEstadia();
		}
	}
	
	private void agregarAReservaEstadia()
	{
		//input ID de la reserva
		//buscar reserva con ID
		//agregar este consumo a la lista de consumos de la reserva
	}
	
	//gets por cada atributo
}
