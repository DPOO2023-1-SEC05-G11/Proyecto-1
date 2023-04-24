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
	
	
	
	public static ArrayList<Habitacion> cargarHabitaciones() {
		File file = new File("data/habitaciones.txt");
		ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] atributos = line.split(";");
				ArrayList<String> caracteristicas = new ArrayList<String>();
				String[] caract = (atributos[2]).split(",");
				for (String string : caract)
				{
					caracteristicas.add(string);
				}
                Habitacion room = new Habitacion(Integer.parseInt(atributos[0]), atributos[1], caracteristicas, Double.parseDouble(atributos[3]), Boolean.parseBoolean(atributos[4]));
                habitaciones.add(room);
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
		return habitaciones;
    }

	public static void updateHabitaciones(File nuevasHabitaciones)
	{
		File habsActuales = new File("data/habitaciones.txt");

		try {
            // Create a FileReader and BufferedReader to read the contents of the new file
            FileReader fileReader = new FileReader(nuevasHabitaciones);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Create a FileWriter and BufferedWriter to write the contents to the old file
            FileWriter fileWriter = new FileWriter(habsActuales);
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
		
	public static void addHabitacion(Habitacion room, ArrayList<Habitacion> lista)
	{
	    if (!lista.contains(room))
	    {
	        lista.add(room);
	        
	        try {
	            
	            FileWriter fileWriter = new FileWriter("data/habitaciones.txt", true);
	            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

	            
	            bufferedWriter.write(room.getId() + ";" + room.getTipo() + ";");

	            
	            for (int i = 0; i < room.getCaracteristicas().size() - 1; i++) {
	                bufferedWriter.write(room.getCaracteristicas().get(i) + ",");
	            }
	            bufferedWriter.write(room.getCaracteristicas().get(room.getCaracteristicas().size() - 1) + ";");
	            bufferedWriter.write(room.getTarifa() + ";" + room.isOcupado() + "\n");

	            bufferedWriter.close();

	        } catch (IOException e) {
	            System.err.format("IOException: %s%n", e);
	        }

	    } else{
	        System.out.println("La habitación ya está cargada.");
	    }
	}
	
	
	public static void removeHabitacion(int id, ArrayList<Habitacion> lista) {
	    Habitacion room = null;
	    for (Habitacion hab : lista) {
	        if (hab.getId() == id) {
	            room = hab;
	            break;
	        }
	    }

	    if (room != null) {
	        lista.remove(room);
	        try {
	            File file = new File("data/habitaciones.txt");
	            FileReader fileReader = new FileReader(file);
	            BufferedReader bufferedReader = new BufferedReader(fileReader);
	            StringBuilder stringBuilder = new StringBuilder();

	            String line;
	            while ((line = bufferedReader.readLine()) != null) {
	                if (!line.startsWith(id + ";")) {
	                    stringBuilder.append(line);
	                    stringBuilder.append(System.lineSeparator());
	                }
	            }

	            bufferedReader.close();

	            FileWriter fileWriter = new FileWriter(file);
	            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	            bufferedWriter.write(stringBuilder.toString());
	            bufferedWriter.close();
	        } catch (IOException e) {
	            System.err.format("IOException: %s%n", e);
	        }
	    } else {
	        System.out.println("No existe ninguna habitación con ese ID");
	    }
	}

	
	public static Habitacion buscarHabitacion(int id, ArrayList<Habitacion> lista)
	{
		Habitacion habitacion = null;
		for (Habitacion hab : lista)
		{
			if (hab.getId() == id)
			{
				habitacion = hab;
			}
		}
		return habitacion;
	}
	
	private void addServicio(Servicio service)
	{
		
	}
	
	private void updateServicio(Servicio service)
	{
		
	}
	
	private void removeServicio(Servicio service)
	{
		
	}
	
	private void buscarServicio(Servicio service)
	{
		
	}
	
	private void addTarifa(Tarifa fee)
	{
		
	}
	
	private void updateTarifa(Tarifa service)
	{
		
	}
	
	public static Menu addMenu() {
		File file = new File("data/menu.txt");
		ArrayList<Producto> productos = new ArrayList<Producto>();
		Menu menu;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] atributos = line.split(";");
                Producto producto = new Producto(atributos[0], Float.parseFloat(atributos[1]), Boolean.parseBoolean(atributos[2]));
                productos.add(producto);
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
		menu = new Menu(productos);
		return menu;
    }
	
	public static void updateMenu(File nuevoMenu)
	{
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
	
	public static void removeMenu(Producto product, Menu menu)
	{
		boolean existe = false;
		for (Producto producto : menu.menu)
		{
			if (producto.equals(product))
			{
				product = producto;
				existe = true;
				break;
			}
		}
		if (existe)
		{
			menu.removeProduct(product);
		} else
		{
			System.out.println("El producto no existe.");
		}
	}
	
	private void buscarMenu(Menu service)
	{
		
	}
	
	public void createReserva(Reserva reserva) 
	{
		
	}
	
	public void mostrarReserva(Reserva reserva) 
	{
		
	}

	/*
	
	public static void cargarInformacionHotel(File archivoHabitaciones, File archivoMenu) throws IOException 
	{
		ArrayList<Habitacion> habitaciones = cargarHabitaciones(archivoHabitaciones);
		ArrayList<Producto> productosMenu = cargarMenu(archivoMenu);
		//ArrayList<Combo> combos = cargarCombos(archivoCombos);
	}

	
	
	private static ArrayList<Habitacion> cargarHabitaciones(File archivoIngredientes) throws FileNotFoundException, IOException 
	{
		try (BufferedReader br = new BufferedReader(new FileReader(archivoIngredientes)))
		{
			String linea;
			ArrayList<Habitacion> habitacion = new ArrayList<Habitacion>();
			while ((linea = br.readLine()) != null) 
			{
				String[] split = linea.split(";");
				Habitacion habitaciones = new Habitacion(split[0],Integer.parseInt(split[1]));
				habitacion.add(habitaciones);
			}
			return habitacion;
		}
	}
	
	private static ArrayList<Producto> cargarMenu(File archivoMenu) throws IOException 
	{
		try (BufferedReader br = new BufferedReader(new FileReader(archivoMenu)))
		{
		String linea;
		ArrayList<Producto> productos = new ArrayList<Producto>();
		while ((linea = br.readLine()) != null) 
		{
			String[] split = linea.split(";");
			Producto productoMenu = new Producto(split[0],Integer.parseInt(split[1]));
			productos.add(productoMenu);
		}
		return productos;
		}
	}
*/

}

