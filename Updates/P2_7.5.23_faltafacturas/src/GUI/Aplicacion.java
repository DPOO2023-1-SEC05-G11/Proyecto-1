package GUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.HashMap;

import Modelo.*;

import java.util.ArrayList;


public class Aplicacion
{
	ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();
	ArrayList<ReservaEstadia> reservas = new ArrayList<ReservaEstadia>();
	
	HashMap<String, Servicio> servicios = new HashMap<String, Servicio>();
	
	ArrayList<Factura> facturas = new ArrayList<Factura>();
		
	/**
	 * Este método sirve para imprimir un mensaje en la consola pidiéndole
	 * información al usuario y luego leer lo que escriba el usuario.
	 * 
	 * @param mensaje El mensaje que se le mostrará al usuario
	 * @return La cadena de caracteres que el usuario escriba como respuesta.
	 */
	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
		
	public String acceso() {
	    File archivo = new File("data/datalogin.txt");
	    BufferedReader br = null;
	    try {
	        br = new BufferedReader(new FileReader(archivo));
	        String usuarioIngresado;
	        String contrasenaIngresada;
	        boolean loginExitoso = false;
	        
	        do {
	            usuarioIngresado = input("Inserte usuario: ");
	            contrasenaIngresada = input("Inserte contraseña: ");
	            
	            String linea;
	            while ((linea = br.readLine()) != null) {
	                String[] campos = linea.split(";");
	                String usuario = campos[0];
	                String contrasena = campos[1];
	                String tipo = campos[2];
	                
	                if (usuario.equals(usuarioIngresado) && contrasena.equals(contrasenaIngresada)) {
	                	return tipo;
	                }
	            }
	            
	            System.out.println("Usuario o contraseña incorrecto. Por favor, intente otra vez.");
	            br.close();
	            br = new BufferedReader(new FileReader(archivo));
	        } while (!loginExitoso);
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if (br != null) {
	            try {
	                br.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    
	    return null;
	}
	
	public void ejecutarAplicacion()
	{
		this.habitaciones = LoaderSaver.cargarHabitaciones();
		for (String nombreServicio : LoaderSaver.serviciosACargar())
		{
			Servicio servicio = new Servicio(LoaderSaver.cargarServicio(nombreServicio));
			this.servicios.put(nombreServicio, servicio);
			servicio.setName(nombreServicio);
		}
		this.reservas = LoaderSaver.cargarReservas(habitaciones, servicios);
		LoaderSaver.salvarReservas(reservas);
		
		
		String acceso = acceso();
		System.out.println("¡Bienvenido a su PMS!\n");
		
		boolean continuar = true;
		while (continuar)
		{
			try
			{
				if (acceso.equals("admin")) { 
					int opcion_seleccionada = mostrarOpcionesAdmin();
					//Habitaciones
					if (opcion_seleccionada == 1) {
						int opcion_seleccionada2 = opcionesHabs();
						if (opcion_seleccionada2 == 1) {cargarHabs();}//Cargar habitaciones
						else if (opcion_seleccionada2 == 2) {anadirHabs();} //Añadir habitaciones
						else if (opcion_seleccionada2 == 3) {actualizarHabs();} //Actualizar habitaciones
						else if (opcion_seleccionada2 == 4) {removerHabs();} //remover habitaciones
						else if (opcion_seleccionada2 == 5) {buscarHabs();}//buscar habitaciones
					//Servicios
				 	}else if (opcion_seleccionada == 2) {
						int opcion_seleccionada2 = opcionesServs();
						if (opcion_seleccionada2 == 1) {anadirServs();}//Añadir servicios
						else if (opcion_seleccionada2 == 2) {actualizarServs();}//Actualizar servicios 
						else if (opcion_seleccionada2 == 3) {removerServs();}//Remover servicios
						else if (opcion_seleccionada2 == 4) {searchServicio();}//Buscar servicios
					//Salir
				 	}else if (opcion_seleccionada == 3) {continuar = terminarAplicacion();} 
				 	else { System.out.println("Por favor seleccione una opción válida.");} 
				 }
				 else if (acceso.equals("empleado")){ 
					 int opcion_seleccionada = mostrarOpcionesEmpleado();
					 //Reservas
					 if (opcion_seleccionada == 1) {
						 int opcion_seleccionada2 = opcionesReservas();
						 if (opcion_seleccionada2==1) {ejecutarCrearReserva();}//Crear nueva reserva
						 else if (opcion_seleccionada2==2){manejarReservas();}//Manejar reservas existentes
						 				 
					 //Consumos
					 }else if (opcion_seleccionada == 2) {
						 int opcion_seleccionada2 = opcionesConsumos();
						 if (opcion_seleccionada2==1) {crearConsumo();}//Crear nuevo consumo
						 else if (opcion_seleccionada2==2){buscarConsumo();}//Buscar consumo existente (por ahora no lo necesitamos)
				 	 }else if (opcion_seleccionada == 3) {continuar = terminarAplicacion();}	
				 	 else System.out.println("Por favor seleccione una opción válida.");
				 }
			 }
			 catch (NumberFormatException e)
			 {
				System.out.println("Debe seleccionar uno de los números de las opciones.");
			 }
		}
	}
	
	private void buscarConsumo() {
		// TODO Auto-generated method stub
		
	}

	private Consumo crearConsumo() {
	    
		
	    Servicio servicio = null;
	    while (servicio == null)
	    {
	    	System.out.println("Por favor seleccione un servicio de los siguientes:");
		    for (String serv : servicios.keySet())
		    {
		    	System.out.println(serv);
		    }
	    	servicio = servicios.get(input("Ingrese el servicio deseado"));
	    	if (servicio == null) {System.out.println("Invalid choice. Try again.");}
	    }
	    
	    Boolean yaPagado = Boolean.parseBoolean(input("Ingrese si el cliente paga de inmediato el consumo: true/false"));
	    System.out.println("Por favor ingrese los informaciones del cliente");
	    Huesped huesped = crearHuesped();
	    String huespedPrincipalDocumento = input("Entre el documento del huesped principal");
	    String tipo = input("Ingrese el tipo de consumo: individual/grupo/habitacion");
	    
	    Consumo consumo = new Consumo(servicio, yaPagado, huesped, huespedPrincipalDocumento, tipo);
	    
	    
	    consumo.addProductos();
	    
	    facturarConsumo(consumo, huespedPrincipalDocumento);
	    LoaderSaver.salvarReservas(reservas);
	    return consumo;
	}

	private void facturarConsumo(Consumo consumo, String huespedPrincipalDocumento) {
		if (consumo.yaPagado())
		{
			facturas.add(new Factura(consumo));
	    	//LoaderSaver.salvarFacturas();
			consumo.setPrecioTotal(0);
		}
			ReservaEstadia reserva = buscarReserva(huespedPrincipalDocumento);
			reserva.addConsumo(consumo);
	}

	private void manejarReservas() {
		ReservaEstadia reserva = buscarReserva(input("Por favor ingrese el documento del huesped principal de la reserva."));
		if(reserva != null)
		{
			int opcion_seleccionada3 = opcionesManejarReserva();
			if (opcion_seleccionada3==1) {checkIn(reserva);}//Check In
			else if (opcion_seleccionada3==2){checkOut(reserva);}//Chech Out
			else if (opcion_seleccionada3==3){System.out.println(reserva);}//Buscar Reserva. Podemos agregar opciones para hacer algo con la reserva
			else {System.out.println("Seleccion no reconocida.");}
		}
	}

	

	private ReservaEstadia buscarReserva(String documentoHuespedPrincipal) {
		for (ReservaEstadia res : reservas)
		{
			if ( res.getHuespedPrincipal().getDocumento().equals(documentoHuespedPrincipal) ) {
				return res;
			}
		}
		System.out.println("No existe ninguna reserva con Huesped principal con numero de documento "+ documentoHuespedPrincipal + ".");
		return null;
	}

	private int opcionesManejarReserva() {
		System.out.println("\nOpciones para Consumos\n");
		System.out.println("1. Check In");
		System.out.println("2. Check Out");
		System.out.println("3. Buscar Reserva");
		return Integer.parseInt(input("Por favor seleccione una opción"));
	}

	private int opcionesConsumos() {
		System.out.println("\nOpciones para Consumos\n");
		System.out.println("1. Crear nuevo consumo");
		System.out.println("2. Buscar consumos existentes");
		return Integer.parseInt(input("Por favor seleccione una opción"));
	}

	private int opcionesReservas() {
		System.out.println("\nOpciones para Reservas\n");
		System.out.println("1. Crear nueva reserva");
		System.out.println("2. Manejar reservas existentes");
		return Integer.parseInt(input("Por favor seleccione una opción"));
	}

	private Boolean terminarAplicacion() {
		System.out.println("Saliendo de la aplicación ...");
		System.out.println("Aplcacion terminada");
		return false;
	}

	private void removerServs() {
		String nombreServicio;
		do {
		    nombreServicio = input("Ingrese el nombre del servicio a remover");
		    if (!servicios.containsKey(nombreServicio)) {
		        System.out.println("El servicio no existe");
		    }
		} while (!servicios.containsKey(nombreServicio));
		
		servicios.remove(nombreServicio);
		LoaderSaver.removeServicio(nombreServicio);
		LoaderSaver.salvarServiciosDoc(servicios);
	}

	private void actualizarServs() {
		String nombreServicio;
		do {
		    nombreServicio = input("Ingrese el nombre del servicio a actualizar");
		    if (!servicios.containsKey(nombreServicio)) {
		        System.out.println("El servicio no existe");
		    }
		} while (!servicios.containsKey(nombreServicio));
		
		int opcion_seleccionada3 = 0;
		while (opcion_seleccionada3 != 4)
		{									
		System.out.println("1. Agregar opcion al servicio");
		System.out.println("2. Remover opcion del servicio");
		System.out.println("3. Remplazar opciones con opciones de otro archivo");
		System.out.println("4. Terminar");
		
		opcion_seleccionada3 = Integer.parseInt(input("Por favor seleccione una opción"));
		//Para agregar opcion al servicio
		if (opcion_seleccionada3 == 1)
		{
			agregarOpcionServicio(nombreServicio);
			LoaderSaver.salvarServicio(servicios.get(nombreServicio).getMap(), nombreServicio);
		//Para remover opcion del servicio
		}else if (opcion_seleccionada3 == 2)
		{
			removerOpcionServicio(nombreServicio);
			LoaderSaver.salvarServicio(servicios.get(nombreServicio).getMap(), nombreServicio);
		//Para remplazar opciones desde un documento txt
		}else if (opcion_seleccionada3 == 3)
		{
			File f = new File(input("Por favor ingrese el path al nuevo archivo"));
			servicios.get(nombreServicio).setMap(LoaderSaver.updateServicio(f));
			LoaderSaver.salvarServicio(servicios.get(nombreServicio).getMap(), nombreServicio);
		}
		}
	}

	private void anadirServs() {
		int opcion_seleccionada3 = opcionesAnadirServ();
		//Para añadir desde un documento txt.
		if (opcion_seleccionada3 == 1){cargarNuevoServicio();}
		//Para añadir desde 0.
		else if (opcion_seleccionada3 == 2){servicioDesde0();}
	}

	private void servicioDesde0() {
		String nombreServicio = addEmptyServicio();
		int opcion_seleccionada4 = 0;
		while (opcion_seleccionada4 != 3)
		{
		System.out.println("1. Agregar opcion al servicio");
		System.out.println("2. Remover opcion del servicio");
		System.out.println("3. Terminar");
		opcion_seleccionada4 = Integer.parseInt(input("Por favor seleccione una opción"));
		//Para agregar opcion al servicio
		if (opcion_seleccionada4 == 1)
		{
			agregarOpcionServicio(nombreServicio);
			LoaderSaver.salvarServicio(servicios.get(nombreServicio).getMap(), nombreServicio);
		//Para remover opcion del servicio
		}else if (opcion_seleccionada4 == 2)
		{
			removerOpcionServicio(nombreServicio);
			LoaderSaver.salvarServicio(servicios.get(nombreServicio).getMap(), nombreServicio);
		}
		}
	}

	private int opcionesAnadirServ() {
		System.out.println("1. Añadir servicio con opciones de un documento .txt"); 
		System.out.println("2. Añadir servicio y agrega opciones manualmente"); 
		return Integer.parseInt(input("Por favor seleccione una opción"));
	}

	private int opcionesServs() {
		System.out.println("\nOpciones para Servicios\n");
		System.out.println("1. Añadir servicios");
		System.out.println("2. Actualizar servicios");
		System.out.println("3. Remover servicios");
		System.out.println("4. Buscar servicios"); 
		return Integer.parseInt(input("Por favor seleccione una opción"));
	}

	private void buscarHabs() {
		int id = Integer.parseInt(input("Ingrese el id de la habitación que desea buscar."));
		if(buscarHabitacion(id) == null){
			System.out.println("La habitacion "+id+" no exeiste.");
		}else {
		System.out.println(buscarHabitacion(id));
		}
	}

	private void removerHabs() {
		int id = Integer.parseInt(input("Ingrese el id de la habitación que desea eliminar."));
		removeHabitacion(id);
		LoaderSaver.salvarHabitaciones(habitaciones);
		System.out.println("La habitacion se eliminó correctamente.");
	}

	private void actualizarHabs() {
		File file = new File(input("Ingrese el path del nuevo archivo de habitaciones."));
		try {
	        if (file.exists()) {
	            habitaciones = LoaderSaver.updateHabitaciones(file);
	            LoaderSaver.salvarHabitaciones(habitaciones);
	            System.out.println("Las nuevas habitaciones se cargaron correctamente.");
	        } else {
	            System.out.println("El archivo no existe. No se realizaron cambios en las habitaciones.");
	        }
	    } catch (Exception e) {
	        System.out.println("Error al cargar el archivo de habitaciones: " + e.getMessage());
	        e.printStackTrace();
	    }
	}

	private void anadirHabs() {
		for (Habitacion hab : this.habitaciones) 
		{
			System.out.println(hab);
		}
		Habitacion habitacion = crearHabitacion();
		addHabitacion(habitacion);
		LoaderSaver.salvarHabitaciones(habitaciones);
		System.out.println("La habitacion se añadió correctamente.");
	}

	private void cargarHabs() {
		this.habitaciones=LoaderSaver.cargarHabitaciones();
		System.out.println("Ya se cargaron las habitaciones.");
	}

	private int opcionesHabs() {
		System.out.println("\nOpciones para Habitaciones\n");
		System.out.println("1. Cargar habitaciones");
		System.out.println("2. Añadir habitación");
		System.out.println("3. Actualizar habitación");
		System.out.println("4. Remover habitación");
		System.out.println("5. Buscar habitación");
		return Integer.parseInt(input("Por favor seleccione una opción"));
	}

	private void searchServicio() {
	    String nombre = input("Entre el nombre del servicio que busca");
	    try {
	        servicios.get(nombre).mostrarOpciones();
	    } catch (NullPointerException e) {
	        System.out.println("El servicio " + nombre + " no existe");
	    }
	}


	private void removerOpcionServicio(String nombreServicio) {
	    Servicio servicio = servicios.get(nombreServicio);
	    String opcion = input("Ingrese el nombre de la opcion que quiere remover");
	    if (!servicio.removeOpcion(opcion)) {
	        System.out.println("La opcion " + opcion + " no existe en el servicio " + nombreServicio);
	    }
	}

	private void agregarOpcionServicio(String nombreServicio) {
	    Servicio servicio = servicios.get(nombreServicio);
	    String nombreOpcion = input("Ingrese el nombre de la opcion que quiere añadir");
	    Integer precio = Integer.parseInt(input("Ingrese el precio de la opcion"));
	    Integer previousPrecio = servicio.addOpcion(nombreOpcion, precio);
	    if (previousPrecio != null) {
	        System.out.println("La opcion " + nombreOpcion + " ya existe en el servicio " + nombreServicio + " y su precio ha sido actualizado de "+ previousPrecio + " a " + precio);
	    }
	}

	private void cargarNuevoServicio() {
		String nombre = input("Ingrese el nombre del nuevo servicio a agregar:");
		File f = new File(input("Por favor ingrese el path al archivo con las informaciones del nuevo servicio (con .txt):"));
		try {
	        if (f.exists()) {
	            servicios.put(nombre, new Servicio(LoaderSaver.updateServicio(f)));
	            salvarNuevoServicio(nombre);
	            System.out.println("El nuevo servicio está activado.");
	        } else {
	            System.out.println("El archivo no existe. No se realizó la carga del nuevo servicio.");
	        }
	    } catch (Exception e) {
	        System.out.println("Error al cargar el archivo del nuevo servicio: " + e.getMessage());
	        e.printStackTrace();
	    }
	}

	private String addEmptyServicio() {
		String nombre = input("Ingrese el nombre del nuevo servicio a agregar:");
		Servicio servicio = new Servicio(new HashMap<String, Integer>());
		servicios.put(nombre, servicio);
		salvarNuevoServicio(nombre);	
		return nombre;
	}
	
	public void salvarNuevoServicio(String nombre) {
	    File file = new File("data/" + nombre + ".txt");
	    if (file.exists()) {
	    } else {
	        try {
	            file.createNewFile();
	            System.out.println("Archivo creado exitosamente.");
	        } catch (IOException e) {
	            System.out.println("Error al crear el archivo.");
	            e.printStackTrace();
	        }
	    }
	    LoaderSaver.salvarServicio(servicios.get(nombre).getMap(), nombre);
	    LoaderSaver.salvarServiciosDoc(servicios);
	}

	private void removeHabitacion(int id)
	{
		Habitacion room = null;
		for (Habitacion hab : habitaciones) {
			if (hab.getId() == id) {
				room = hab;
				break;
			}
		}
		if (room != null) {
			habitaciones.remove(room);
			
		} else {
			System.out.println("No existe ninguna habitación con ese ID");
		}
	}
	
	private void addHabitacion(Habitacion hab)
	{
		if (!habitaciones.contains(hab)) {
			habitaciones.add(hab);}
	}
	
	private Habitacion buscarHabitacion(int id)
	{
		Habitacion habitacion = null;
		for (Habitacion hab : habitaciones)
		{
			if (hab.getId() == id)
			{
				habitacion = hab;
			}
		}
		return habitacion;
	}
	
	public 	Huesped crearHuesped() 
	{
			String nombre = input("Por favor ingrese el nombre del huesped");
			String documento = input("Por favor ingrese el documento del huesped");
			String email = input("Por favor ingrese el email del huesped");
			String telefono = input("Por favor ingrese el telefono del huesped");
			Huesped huesped = new Huesped(nombre, documento, email, telefono);
			
			return huesped;	
	}

	public Habitacion crearHabitacion()
	{
		int id = Integer.parseInt(input("Por favor ingrese el id de la habitacion\n"));
		String tipo = input("Por favor ingrese el tipo de habitacion\n estandar, suite, doble");

		Boolean balcon = Boolean.parseBoolean(input("Por favor ingrese si la habitación tiene balcon\n true/false"));
		Boolean vista = Boolean.parseBoolean(input("Por favor ingrese si la habitación tiene vista\n true/false"));
		Boolean cocina = Boolean.parseBoolean(input("Por favor ingrese si la habitación tiene cocina integrada\n true/false"));
		
		ArrayList<Cama> camas = new ArrayList<>();
		
		camas.add(new Cama(Integer.parseInt(input("Por favor ingrese el tipo de la cama 1: \n 1/2/3/4/5 (0 si no existe)"))));
		camas.add(new Cama(Integer.parseInt(input("Por favor ingrese el tipo de la cama 2: \n 1/2/3/4/5 (0 si no existe)"))));
		camas.add(new Cama(Integer.parseInt(input("Por favor ingrese el tipo de la cama 3: \n 1/2/3/4/5 (0 si no existe)"))));
		
		Double tarifa = Double.parseDouble(input("Por favor ingrese la tarifa de la habitacion\n"));
		Boolean ocupado = Boolean.parseBoolean(input("Por favor ingrese si la habitación está actualmente ocupada\n true/false"));

		Habitacion habitacion = new Habitacion(id, tipo, balcon, vista, cocina, camas, tarifa, ocupado);

		return habitacion;
	}

	public void preguntarHabitaciones(LocalDate fecha, int duracion)
	{
		ArrayList<Habitacion> habsList = new ArrayList<Habitacion>();
		
		String tipo = input("Por favor ingrese el tipo de habitacion\n estandar, suite, doble");
		
		Boolean balcon = input("Escriba '1', si quiere balcón, '0', si no").equals("1");
		Boolean vista = input("Escriba '1' si quiere vista, '0' si no").equals("1");
		Boolean cocina = input("Escriba '1' si quiere cocina, '0' si no").equals("1");

		
		int adultos = Integer.parseInt(input("Ingrese el numero de adultos"));
		int ninos = Integer.parseInt(input("Ingrese el numero de ninos"));
		
		for (Habitacion hab:habitaciones) {
			if (tipo.equals(hab.getTipo()) && balcon.equals(hab.tieneBalcon()) && vista.equals(hab.tieneVista()) 
					&& cocina.equals(hab.tieneCocina()) && hab.getEspacioAdultos() >= adultos && 
					hab.getEspacioNinos() + hab.getEspacioAdultos() - adultos >= ninos//Porque ninos pueden dormir en cama de otros tamaños.
					&& hab.libreEntre(fecha, duracion))
				{
					habsList.add(hab);
				}
		}
		if (habsList.size()>0){
			for(Habitacion hab : habsList)
			{
				System.out.println(hab);
			}
		}else {
			System.out.println("No quedan más habitaciones de esta descripcion.");
			System.out.println("Por favor intente otra vez.");
			preguntarHabitaciones(fecha, duracion);
		}
	}
	
	public void ejecutarCrearReserva() {
	    ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();

	    System.out.println("Por favor ingrese las informaciones del huésped principal.");
	    Huesped huespedPrincipal = crearHuesped();
	    
	    LocalDate fechaInicio = LocalDate.parse(input("Por favor escriba fecha de inicio de reserva en formato 'YYYY-MM-DD':"));
	    int duracion = Integer.parseInt(input("Por favor escriba la duración de la estadía"));

	    boolean addAnotherRoom = true;
	    while (addAnotherRoom) {
	        try {
	        	System.out.println("Por favor ingrese las informaciones de la habitacion que quiera reservar.");
	            preguntarHabitaciones(fechaInicio, duracion);
	            Habitacion habitacion = habSeleccionada();

	            if (habitacion != null) {
	                habitaciones.add(habitacion);
	            } else {
	                System.out.println("La habitación seleccionada no existe. Por favor, intente nuevamente.");
	            }
	        } catch (Exception e) {
	            System.out.println("Error al seleccionar la habitación: " + e.getMessage());
	            e.printStackTrace();
	        }

	        System.out.println("Desea agregar otra habitación?");
	        String choice = input("Escriba '1' para agregar otra habitación o '0' para finalizar:");

	        if (choice.equalsIgnoreCase("0")) {
	            addAnotherRoom = false;
	        }
	    }
	    
	    ReservaEstadia res = new ReservaEstadia(fechaInicio, duracion, huespedPrincipal, habitaciones);
	    reservas.add(res);
	    LoaderSaver.salvarReservas(reservas);
	}

	
	private Habitacion habSeleccionada() {
		int idSeleccionada = Integer.parseInt(input("Ingrese el id de la habitacion que quiera"));
		if(buscarHabitacion(idSeleccionada) == null){
			System.out.println("La habitacion "+idSeleccionada+" no exeiste.");
			return null;
		}else {
			return (buscarHabitacion(idSeleccionada));
		}
		
	}

	public void checkIn(ReservaEstadia reserva)
	{
		ArrayList<Huesped> huespedes = new ArrayList<Huesped>();
		int numpersonas = Integer.parseInt(input("Por favor escriba cuantas personas estarán en el grupo"));
		
		for(int i = 1; i <= numpersonas; i++)
		{
			huespedes.add(crearHuesped());
		}
		reserva.checkIn(huespedes);
		LoaderSaver.salvarReservas(reservas);
	}
	
	private void checkOut(ReservaEstadia reserva) {
		reserva.checkOut();	
		facturas.add(new Factura(reserva));
		//LoaderSaver.salvarFacturas();
	}
	
	public int mostrarOpcionesAdmin()
	{
		System.out.println("\nOpciones de la aplicación\n");
		System.out.println("1. Habitaciones");
		System.out.println("2. Servicios");
		System.out.println("3. Salir de la aplicación\n");
		return Integer.parseInt(input("Por favor seleccione una opción"));
	}
	public Integer mostrarOpcionesEmpleado()
	{
		System.out.println("\nOpciones de la aplicación\n");
		System.out.println("1. Reservas");
		System.out.println("2. Consumos");
		System.out.println("3. Salir de la aplicación\n");
		return Integer.parseInt(input("Por favor seleccione una opción"));
	}
	
	
	public static void main(String[] args)
	{
		Aplicacion aplicacion = new Aplicacion();
		aplicacion.ejecutarAplicacion();
	}
}