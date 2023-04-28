import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//import uniandes.dpoo.taller2.modelo.Ingrediente;
//import uniandes.dpoo.taller2.modelo.ProductoMenu;

public class Loader {

	public static ArrayList<Habitacion> updateHabitaciones(File f) {
		ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();
		try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] atributos = line.split(";");
				int id = Integer.parseInt(atributos[0]);
				String tipo = atributos[1];
				boolean balcon = Boolean.parseBoolean(atributos[2]);
				boolean vista = Boolean.parseBoolean(atributos[3]);
				boolean cocina = Boolean.parseBoolean(atributos[4]);
				int camas = Integer.parseInt(atributos[5]);
				Double tarifa = Double.parseDouble(atributos[6]);
				boolean ocupado = Boolean.parseBoolean(atributos[7]);
				
				Habitacion room = new Habitacion(id, tipo, balcon, vista, cocina, camas, tarifa, ocupado);
				habitaciones.add(room);
			}
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
		return habitaciones;
	}
	
	public static ArrayList<Habitacion> cargarHabitaciones()
	{
		File f = new File("data/habitaciones.txt");
		return updateHabitaciones(f);
	}

	public static void salvarHabitaciones(ArrayList<Habitacion> habitaciones) {
		try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/habitaciones.txt"));
            for (Habitacion h : habitaciones) {
                writer.write(h.getId() + ";" + h.getTipo() + ";" + h.tieneBalcon() + ";" + h.tieneVista() + ";" + h.tieneCocina() + ";" + h.getCamas() + ";" + h.getTarifa() + ";" + h.isOcupado() + "\n");
            }
            writer.close();
            System.out.println("File written successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
	}



	private void addServicio(Servicio service) {

	}

	private void updateServicio(Servicio service) {

	}

	private void removeServicio(Servicio service) {

	}

	private void buscarServicio(Servicio service) {

	}

	private void addTarifa(Tarifa fee) {

	}

	private void updateTarifa(Tarifa service) {

	}

	public static Menu addMenu() {
		File file = new File("data/menu.txt");
		ArrayList<Producto> productos = new ArrayList<Producto>();
		Menu menu;

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] atributos = line.split(";");
				Producto producto = new Producto(atributos[0], Float.parseFloat(atributos[1]),
						Boolean.parseBoolean(atributos[2]));
				productos.add(producto);
			}
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
		menu = new Menu(productos);
		return menu;
	}

	public static void updateMenu(File nuevoMenu) {
		File menuActual = new File("data/menu.txt");

		try {
			// Create a FileReader and BufferedReader to read the contents of the new file
			FileReader fileReader = new FileReader(nuevoMenu);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			// Create a FileWriter and BufferedWriter to write the contents to the old file
			FileWriter fileWriter = new FileWriter(menuActual);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			String line;
			// Read each line of the new file and write it to the old file
			while ((line = bufferedReader.readLine()) != null) {
				bufferedWriter.write(line);
				bufferedWriter.newLine();
			}

			// Close the readers and writers
			bufferedReader.close();
			bufferedWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void removeMenu(Producto product, Menu menu) {
		boolean existe = false;
		for (Producto producto : menu.menu) {
			if (producto.equals(product)) {
				product = producto;
				existe = true;
				break;
			}
		}
		if (existe) {
			menu.removeProduct(product);
		} else {
			System.out.println("El producto no existe.");
		}
	}

	private void buscarMenu(Menu service) {

	}

	public void createReserva(Reserva reserva) {

	}

	public void mostrarReserva(Reserva reserva) {

	}

	/*
	 * 
	 * public static void cargarInformacionHotel(File archivoHabitaciones, File
	 * archivoMenu) throws IOException { ArrayList<Habitacion> habitaciones =
	 * cargarHabitaciones(archivoHabitaciones); ArrayList<Producto> productosMenu =
	 * cargarMenu(archivoMenu); //ArrayList<Combo> combos =
	 * cargarCombos(archivoCombos); }
	 * 
	 * 
	 * 
	 * private static ArrayList<Habitacion> cargarHabitaciones(File
	 * archivoIngredientes) throws FileNotFoundException, IOException { try
	 * (BufferedReader br = new BufferedReader(new FileReader(archivoIngredientes)))
	 * { String linea; ArrayList<Habitacion> habitacion = new
	 * ArrayList<Habitacion>(); while ((linea = br.readLine()) != null) { String[]
	 * split = linea.split(";"); Habitacion habitaciones = new
	 * Habitacion(split[0],Integer.parseInt(split[1]));
	 * habitacion.add(habitaciones); } return habitacion; } }
	 * 
	 * private static ArrayList<Producto> cargarMenu(File archivoMenu) throws
	 * IOException { try (BufferedReader br = new BufferedReader(new
	 * FileReader(archivoMenu))) { String linea; ArrayList<Producto> productos = new
	 * ArrayList<Producto>(); while ((linea = br.readLine()) != null) { String[]
	 * split = linea.split(";"); Producto productoMenu = new
	 * Producto(split[0],Integer.parseInt(split[1])); productos.add(productoMenu); }
	 * return productos; } }
	 */

}
