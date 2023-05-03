package Modelo;

import java.util.Scanner;

public class Consumo {

	private Servicio servicio;
	private int precioTotal = 0;
	private String tipo; //individual/grupal/habitacional
	private Huesped huesped;
	private Huesped huespedPrincipal;
	private Boolean yaPagado;
	
	public Consumo(Servicio serv, Boolean yaPagado, Huesped huesped, Huesped huespedPrincipal, String tipo)
	{
		this.servicio = serv;
		this.yaPagado = yaPagado;
		this.huesped = huesped;
		this.huespedPrincipal = huespedPrincipal;
		this.tipo = tipo;
		addProductos();
		facturar(); //puede que sea mejor agregar el consumo a la lista en la aplicacion (/hotel)
		
	}

	
	
	private void addProductos() {
	    Scanner scanner = new Scanner(System.in);
	    String userInput = "";
	    
	    while (!userInput.equalsIgnoreCase("0")){
	        
	        mostrarOpciones();
	        
	        userInput = scanner.nextLine();
	        
	        precioTotal += servicio.getMap().get(userInput);
	        
	    }
	    scanner.close();
	}
	
	private void mostrarOpciones()
	{
		System.out.println("Aqui estan los productos a agregar:");
        servicio.mostrarOpciones();
        System.out.println("Ingrese el nombre del producto a agregar.");
        System.out.println("Ingrese '0' para no agregar mas productos.");
	}

	
	private void facturar()
	{
		if (yaPagado)
		{
			Factura factura = new Factura(this);
			precioTotal = 0;
			agregarAReservaEstadia();
			
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
	
	public String getTipo()
	{
		return this.tipo;
	}
	
	public Huesped getHuesped()
	{
		return this.huesped;
	}
	
	public Huesped getHuespedPrincipal()
	{
		return this.huespedPrincipal;
	}
	
	public int getPrecioTotal()
	{
		return this.precioTotal;
	}
}
