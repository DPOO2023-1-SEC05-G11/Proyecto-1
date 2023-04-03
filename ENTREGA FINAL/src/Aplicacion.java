

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import java.util.ArrayList;


public class Aplicacion
{
	Usuario user = new Usuario();
	
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
		
	public boolean acceso()
	{
		File archivo = new File("data/datalogin.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;
            String usuarioIngresado = input("Inserte usuario: ");
            String contrasenaIngresada = input("Inserte contraseña: ");
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(";");
                String usuario = campos[0];
                String contrasena = campos[1];
                String tipo = campos[2];
                if (usuario.equals(usuarioIngresado) && contrasena.equals(contrasenaIngresada) && tipo.equals("admin")) {
                	mostrarOpcionesAdmin();
                	return true;
                }
                else if (usuario.equals(usuarioIngresado) && contrasena.equals(contrasenaIngresada) && tipo.equals("empleado")) 
                {
                	mostrarOpcionesEmpleado();
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
	 }
	
	public void ejecutarAplicacion()
	{
		 System.out.println("¡Bienvenido a su PMS!\n");
		 boolean continuar = true;
		 while (continuar)
		 {
			 try
			 {
				 if (acceso()) { 
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
	
	
	private void cargarDatos()
	{
		try
		{
			//File combos = new File("data/combos.txt");
			File habitaciones = new File("data/habitaciones.txt");
			File menu = new File("data/menu.txt");
			Loader.cargarInformacionHotel(habitaciones, menu);
		}
		catch (IOException e)
		{
			System.out.println("ERROR: hubo un problema leyendo el archivo.");
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args)
	{
		Aplicacion aplicacion = new Aplicacion();
		aplicacion.ejecutarAplicacion();
	}
}