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
	ArrayList<Habitacion> habitaciones;
	ArrayList<ReservaEstadia> reservas;
	
	HashMap<String, Servicio> servicios = new HashMap<String, Servicio>();
		
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
                }else {
                	System.out.println("Usuario o contraseña incorrecto. Porfavor intente otra vez.");
                	return acceso();
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
		for (String servicio : LoaderSaver.serviciosACargar())
		{
			this.servicios.put(servicio, new Servicio(LoaderSaver.cargarServicio(servicio)));
		}
		
		
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
								//Para añadir servicios
								if (opcion_seleccionada2 == 1) {
									System.out.println("1. Añadir servicio con opciones de un documento .txt"); 
									System.out.println("2. Añadir servicio y agrega opciones manualmente"); 
									int opcion_seleccionada3 = Integer.parseInt(input("Por favor seleccione una opción"));
									//Para añadir desde un documento txt.
									if (opcion_seleccionada3 == 1)
									{
										cargarNuevoServicio();
										System.out.println("El nuevo servicio está avticada");
										//Para añadir desde 0.
									}else if (opcion_seleccionada3 == 2)
									{
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
								//Para actualizar servicios 
								} else { if (opcion_seleccionada2 == 2) {
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
								//Para remover servicios
								} else { if (opcion_seleccionada2 == 3) {
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
								//Para buscar servicios
								} else { if (opcion_seleccionada2 == 4) {
									searchServicio();
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
								this.servicios.get("restaurante").setMap(LoaderSaver.updateServicio(file));
								LoaderSaver.salvarServicio(servicios.get("restaurante").getMap(), "restaurante");
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
		File f = new File(input("Por favor ingrese el path al archivo con las informaciones del nuevo servicio:"));
		servicios.put(nombre, new Servicio(LoaderSaver.updateServicio(f)));
		salvarNuevoServicio(nombre);	
		
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
	
	
	public static void main(String[] args)
	{
		Aplicacion aplicacion = new Aplicacion();
		aplicacion.ejecutarAplicacion();
	}
}