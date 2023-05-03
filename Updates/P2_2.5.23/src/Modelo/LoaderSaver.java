package Modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;


public class LoaderSaver {
	
/////////////////////////////Carga de Habitaciones////////////////////////////////////////

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
				
				ArrayList<Cama> camas = new ArrayList<Cama>();
				for (int i=5;i<=7;i++)
				{
					if (Integer.parseInt(atributos[i]) != 0)
					{
						camas.add(new Cama(Integer.parseInt(atributos[i])));
					}
				}
				
				
				Double tarifa = Double.parseDouble(atributos[8]);
				boolean ocupado = Boolean.parseBoolean(atributos[9]);
				
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
                String camasString = "";
                List<Cama> camas = h.getCamas();
                
                camasString += camas.size() > 0 ? camas.get(0).stringASalvar() : "0";
                camasString += ";";
                camasString += camas.size() > 1 ? camas.get(1).stringASalvar() : "0";
                camasString += ";";
                camasString += camas.size() > 2 ? camas.get(2).stringASalvar() : "0";

                writer.write(h.getId() + ";" + h.getTipo() + ";" + h.tieneBalcon() + ";" + h.tieneVista() + ";" + h.tieneCocina() + ";" + camasString + ";" + h.getTarifa() + ";" + h.isOcupado() + "\n");
            }

            writer.close();
            System.out.println("File written successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
	}



/////////////////////////////Carga de Servicios////////////////////////////////////////

	

	public static HashMap<String, Integer> updateServicio(File f) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();

		try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] atributos = line.split(";");
				map.put(atributos[0], Integer.parseInt(atributos[1]));
			}
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
		
		return map;
	}
	
	public static HashMap<String, Integer> cargarServicio(String file)
	{
		File f = new File("data/"+file+".txt");
		return updateServicio(f);
	}

	

	public static void salvarServicio(HashMap<String, Integer> map, String file) {
		try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/"+file+".txt"));
            for (Entry<String, Integer> entry : map.entrySet()) {
                writer.write(entry.getKey() + ";"  + entry.getValue() + "\n");
            }
            writer.close();
            System.out.println("File written successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
	}
	
	
}

