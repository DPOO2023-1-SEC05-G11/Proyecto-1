

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.ArrayList;


public class Aplicacion implements Loader
{
	ArrayList<Usuario> Usuarios;
	HashMap<String, String> databaseUsuarios;
	
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
	
	public void crearCuenta()
	{
		String type = null;
		while (type == null)
		{
			type = input("Select type");
			if (type == "Admin") 
			{
				System.out.println("Your user will be an Admin");
			} else if (type == "Empleado")
			{
				System.out.println("Your user will be an Empleado");
			} else
			{
				System.out.println("Selected type not recognised.");
				type = null;	
			}
		}
		
		String login = null;
		while (login == null)
		{
			login = input("Choose a username:");
			if (databaseUsuarios.containsKey(login)) 
			{
				System.out.println("Username already exists.");
				login = null;	
			}
		}
		
		String password = input("Password");
		
		databaseUsuarios.put(login, password);
		if (type == "Admin")
		{
			
		}
		
		
		
		
		
		
	}
	
		
	public boolean acceso()
	{
		 System.out.println("Login");
		 String login = input("Username");
		 String password = input("Password");
		 
		 if (user.getLogin() == login && user.getPassword() == password) {
			 return true; // idea vaga de lo que hace
		 }
		 else { 
			 return false; }
		 
	 }
	
	public void ejecutarAplicacion()
	{
		 System.out.println("¡Bienvenido a su PMS!\n");
		 boolean continuar = true;
		 while (continuar && acceso())
		 {
			 try
			 {
				 if (user instanceof Admin) {
					 mostrarOpcionesAdmin(); 
					 int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
					 if (opcion_seleccionada == 1) {
					 		System.out.println("\nOpciones para Habitaciones\n");
							System.out.println("1. Cargar habitaciones");
							System.out.println("2. Añadir habitación");
							System.out.println("3. Actualizar habitación");
							System.out.println("4. Remover habitación");
							System.out.println("5. Buscar habitación");
					 	} else { if (opcion_seleccionada == 2) {
						 		System.out.println("\nOpciones para Servicios\n");
								System.out.println("1. Añadir servicios");
								System.out.println("2. Actualizar servicios");
								System.out.println("3. Remover servicios");
								System.out.println("4. Buscar servicios"); 
					 	} else { if (opcion_seleccionada == 3) {
					 		System.out.println("\nOpciones para Tarifas\n");
							System.out.println("1. Añadir tarifas");
							System.out.println("2. Actualizar tarifas");
					 	} else { if (opcion_seleccionada == 4) {
					 		System.out.println("\nOpciones para Menú\n");
							System.out.println("1. Añadir menú");
							System.out.println("2. Actualizar menú");
							System.out.println("3. Remover menú");
							System.out.println("4. Buscar menú");
					 	} else { if (opcion_seleccionada == 5) {
					 		System.out.println("Saliendo de la aplicación ...");
							continuar = false;
					 	} else { System.out.println("Por favor seleccione una opción válida."); 
					 	} } } } }
				 }
					 else 
					 { 
						 mostrarOpcionesEmpleado();
						 int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
						 if (opcion_seleccionada == 1) {
						 		System.out.println("\nOpciones para Reservas\n");
								System.out.println("1. Crear reserva");
								System.out.println("2. Mostrar info de reserva vigente");
						 	} else { if (opcion_seleccionada == 2) {
							 		System.out.println("\nChecking in...\n"); 
						 	} else { if (opcion_seleccionada == 3) {
						 		System.out.println("\nChecking out...\n");
						 	} else { if (opcion_seleccionada == 5) {
						 		System.out.println("\nBuscando habitación...\");\n");
						 	} else { if (opcion_seleccionada == 5) {
						 		System.out.println("\nBuscando servicio...\");\n");
						 	} else { if (opcion_seleccionada == 6) {
						 		System.out.println("\nBuscando tarifa...\");\n");
						 	} else { if (opcion_seleccionada == 7) {
						 		System.out.println("\nBuscando en el menú...\");\n");
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