package GUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

import Modelo.*;

import java.util.ArrayList;


public class Aplicacion
{
	ArrayList<Habitacion> habitaciones;
	ArrayList<ReservaEstadia> reservas;
	Servicio restaurante;
	Servicio spa;
	Servicio guia;
	
	
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
		
	public int acceso()
	{
		File archivo = new File("data/datalogin.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            String usuarioIngresado = input("Inserte usuario: ");
            String contrasenaIngresada = input("Inserte contraseña: ");
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(";");
                String usuario = campos[0];
                String contrasena = campos[1];
                String tipo = campos[2];
                if (usuario.equals(usuarioIngresado) && contrasena.equals(contrasenaIngresada) && tipo.equals("admin")) {
                	//mostrarOpcionesAdmin();
                	return 0;
                }
                else if (usuario.equals(usuarioIngresado) && contrasena.equals(contrasenaIngresada) && tipo.equals("empleado")) 
                {
                	//mostrarOpcionesEmpleado();
                	return 1;
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 2;
	 }
	
	public void ejecutarAplicacion()
	{
		this.habitaciones=LoaderSaver.cargarHabitaciones();
		this.restaurante = new Servicio(LoaderSaver.cargarServicio("menu"));
		this.spa = new Servicio(LoaderSaver.cargarServicio("spa"));
		this.guia = new Servicio(LoaderSaver.cargarServicio("guia"));
		
		int acceso = acceso();
		 System.out.println("¡Bienvenido a su PMS!\n");
		 boolean continuar = true;
		 while (continuar)
		 {
			 try
			 {
				 if (acceso==0) { 
					 mostrarOpcionesAdmin();
					 int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
					 if (opcion_seleccionada == 1) {
					 		System.out.println("\nOpciones para Habitaciones\n");
							System.out.println("1. Cargar habitaciones");
							System.out.println("2. Añadir habitación");
							System.out.println("3. Actualizar habitación");
							System.out.println("4. Remover habitación");
							System.out.println("5. Buscar habitación");
							int opcion_seleccionada2 = Integer.parseInt(input("Por favor seleccione una opción"));
							if (opcion_seleccionada2 == 1) {
								System.out.println("Ya se cargaron las habitaciones.");
							} else { if (opcion_seleccionada2 == 2) {

								for (Habitacion hab : this.habitaciones) 
								{
									System.out.println(hab);
								}
								Habitacion habitacion = crearHabitacion();
								addHabitacion(habitacion);
								LoaderSaver.salvarHabitaciones(habitaciones);
								System.out.println("La habitacion se añadió correctamente.");

							} else { if (opcion_seleccionada2 == 3) {

								File file = new File(input("Ingrese el path del nuevo archivo de habitaciones."));
								habitaciones = LoaderSaver.updateHabitaciones(file);
								LoaderSaver.salvarHabitaciones(habitaciones);
								System.out.println("Las nuevas habitaciones se cargaron correctamente.");

							} else { if (opcion_seleccionada2 == 4) {

								int id = Integer.parseInt(input("Ingrese el id de la habitación que desea eliminar."));
								removeHabitacion(id);
								LoaderSaver.salvarHabitaciones(habitaciones);
								System.out.println("La habitacion se eliminó correctamente.");

							} else { if (opcion_seleccionada2 == 5) {

								int id = Integer.parseInt(input("Ingrese el id de la habitación que desea buscar."));
								System.out.println(buscarHabitacion(id));
							} } } } } 
					 	} else { if (opcion_seleccionada == 2) {
						 		System.out.println("\nOpciones para Servicios\n");
								System.out.println("1. Añadir servicios");
								System.out.println("2. Actualizar servicios");
								System.out.println("3. Remover servicios");
								System.out.println("4. Buscar servicios"); 
								int opcion_seleccionada2 = Integer.parseInt(input("Por favor seleccione una opción"));
								if (opcion_seleccionada2 == 2) {
									//addServicio();	
								} else { if (opcion_seleccionada2 == 2) {
									//Loader.updateServicio();
								} else { if (opcion_seleccionada2 == 3) {
									//Loader.removeServicio():
								} else { if (opcion_seleccionada2 == 4) {
									//searchServicio();
								} } } } 
					 	} else { if (opcion_seleccionada == 3) {
					 		System.out.println("\nOpciones para Tarifas\n");
							System.out.println("1. Añadir tarifas");
							System.out.println("2. Actualizar tarifas");
							int opcion_seleccionada2 = Integer.parseInt(input("Por favor seleccione una opción"));
							if (opcion_seleccionada2 == 1) {
								//Loader.addTarifa();	
							} else { if (opcion_seleccionada2 == 2) {
								//Loader.updateTarifa();
							} }
					 	} else { if (opcion_seleccionada == 4) {
					 		System.out.println("\nOpciones para Menú\n");
							System.out.println("1. Añadir menú");
							System.out.println("2. Actualizar menú");
							System.out.println("3. Remover menú");
							System.out.println("4. Buscar menú");
							int opcion_seleccionada2 = Integer.parseInt(input("Por favor seleccione una opción"));
							if (opcion_seleccionada2 == 1) {
								//Loader.addMenu();	
							} else { if (opcion_seleccionada2 == 2) {
								File file = new File(input("Ingrese el path del nuevo archivo de menu."));
								this.restaurante.setMap(LoaderSaver.updateServicio(file));
								LoaderSaver.salvarServicio(restaurante.getMap(), "menu");
							} else { if (opcion_seleccionada2 == 3) {
								//Loader.removeMenu():
							} else { if (opcion_seleccionada2 == 4) {
								//Loader.buscarMenu();
							} } } }
					 	} else { if (opcion_seleccionada == 5) {
					 		System.out.println("Saliendo de la aplicación ...");
							continuar = false;
					 	} else { System.out.println("Por favor seleccione una opción válida."); 
					 	} } } } }
				 }
					 else if (acceso==1)
					 { 
						 mostrarOpcionesEmpleado();
						 int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
						 if (opcion_seleccionada == 1) {
						 		System.out.println("\nOpciones para Reservas\n");
								System.out.println("1. Crear reserva");
								System.out.println("2. Mostrar info de reserva vigente");
								int opcion_seleccionada2 = Integer.parseInt(input("Por favor seleccione una opción"));
								if (opcion_seleccionada2==1) {ejecutarCrearReserva();}
						 	} else { if (opcion_seleccionada == 2) {
									//checkIn();
							 		System.out.println("\nChecking in...\n"); 
						 	} else { if (opcion_seleccionada == 3) {
						 		System.out.println("\nChecking out...\n");
						 	} else { if (opcion_seleccionada == 5) {
						 		System.out.println("\nBuscando habitación...\n");
						 	} else { if (opcion_seleccionada == 5) {
						 		System.out.println("\nBuscando servicio...\n");
						 	} else { if (opcion_seleccionada == 6) {
						 		System.out.println("\nBuscando tarifa...\n");
						 	} else { if (opcion_seleccionada == 7) {
						 		System.out.println("\nBuscando en el menú...\n");
						 	} else { if (opcion_seleccionada == 8) {
						 		System.out.println("Saliendo de la aplicación ...");
								continuar = false;
						 	} else { System.out.println("Por favor seleccione una opción válida.");
						 		} } } } } } } }
						}
				 }
				 catch (NumberFormatException e)
				 {
					System.out.println("Debe seleccionar uno de los números de las opciones.");
					}
		}
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
			
			
			//salvarHabitaciones(habitaciones);
		} else {
			System.out.println("No existe ninguna habitación con ese ID");
		}
	}
	
	private void addHabitacion(Habitacion hab)
	{
		if (!habitaciones.contains(hab)) {
			habitaciones.add(hab);}
			
			//salvarHabitaciones();
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

	public Habitacion preguntarHabitaciones()
	{
		String tipo = input("Por favor ingrese el tipo de habitacion\n estandar, suite, doble");
		
		Boolean balcon = Boolean.parseBoolean(("Escriba '1', si quiere balcón, '0', si no"));
		Boolean vista = Boolean.parseBoolean(input("Escriba '1' si quiere vista, '0' si no"));
		Boolean cocina = Boolean.parseBoolean((input("Escriba '1' si quiere cocina, '0' si no")));
		
		int adultos = Integer.parseInt(input("Ingrese el numero de adultos"));
		int ninos = Integer.parseInt(input("Ingrese el numero de ninos"));
		
		for (Habitacion hab:habitaciones) {
			if (tipo==hab.getTipo() && balcon.equals(hab.tieneBalcon()) && vista.equals(hab.tieneVista()) 
					&& cocina.equals(hab.tieneCocina()) && hab.espacioAdultos() == adultos && hab.espacioNinos() == ninos
					&& !hab.isOcupado())
				{
					return hab;
				}
		}
		System.out.println("No quedan más habitaciones de esta descripcion.");
		return null;
	}
	
	public void ejecutarCrearReserva()
	{
		ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();
		
		System.out.println("Por favor ingrese las informaciones del huesped principal.");
		Huesped huespedPrincipal = crearHuesped();
		
		int numhabitaciones = Integer.parseInt(input("Por favor escriba cuantas habitaciones necesitan"));
		int e = 1;
		while (e<=numhabitaciones)
		{
			habitaciones.add(preguntarHabitaciones());
			e++;
		}
		
		
		LocalDate fechaInicio = LocalDate.parse(input("Por favor escriba fecha de inicio de reserva en formato 'YYYY-MM-DD':"));
		int duracion = Integer.parseInt(input("Por favor escriba la duración de la estadía"));
		ReservaEstadia res = new ReservaEstadia(fechaInicio, duracion, huespedPrincipal, habitaciones);
		reservas.add(res);
	}
	
	public void checkIn(ReservaEstadia reserva)
	{
		ArrayList<Huesped> huespedes = new ArrayList<Huesped>();
		int numpersonas = Integer.parseInt(input("Por favor escriba cuantas personas estarán en el grupo"));
		
		int i = 1;
		while (i<=numpersonas)
		{
			huespedes.add(crearHuesped());
			i++;
		}
		reserva.checkIn(huespedes);
		
	}
	
	public void mostrarOpcionesAdmin()
	{
		System.out.println("\nOpciones de la aplicación\n");
		System.out.println("1. Habitaciones");
		System.out.println("2. Servicios");
		System.out.println("3. Tarifas");
		System.out.println("4. Menú");
		System.out.println("5. Salir de la aplicación\n");
	}
	public void mostrarOpcionesEmpleado()
	{
		System.out.println("\nOpciones de la aplicación\n");
		System.out.println("1. Reservas");
		System.out.println("2. Check-In");
		System.out.println("3. Check-Out");
		System.out.println("4. Buscar habitación");
		System.out.println("5. Buscar servicios");
		System.out.println("6. Buscar tarifa");
		System.out.println("7. Buscar en el menú");
		System.out.println("8. Salir de la aplicación\n");
	}
	
	
	private void cargarDatos()
	{
		//File combos = new File("data/combos.txt");
		File menu = new File("data/menu.txt");
		this.habitaciones = LoaderSaver.cargarHabitaciones();
	}
	
	public static void main(String[] args)
	{
		Aplicacion aplicacion = new Aplicacion();
		aplicacion.ejecutarAplicacion();
	}
}