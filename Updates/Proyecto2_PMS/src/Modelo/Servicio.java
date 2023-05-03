package Modelo;

import java.util.HashMap;
import java.util.Map.Entry;

public class Servicio {
	
	private HashMap<String, Integer> map;
	
	
	public Servicio(HashMap<String, Integer> map)
	{
		this.map = map;
	}
	
	public void mostrarOpciones() {
	    for (Entry<String, Integer> entry : map.entrySet()) {
	        System.out.println("Nombre: " + entry.getKey() + "| Precio: $COP " + entry.getValue());
	    }
	}
	
	public Servicio getInstance(HashMap<String, Integer> map)// tal vez no lo necesitamos
	{
		return new Servicio(map);
	}
	
	public HashMap<String, Integer> getMap()
	{
		return map;
	}
	
	public void setMap(HashMap<String, Integer> map)
	{
		this.map = map;
	}
	
	public void addOpcion(String key, int value)
	{
		map.put(key, value);
	}

	public void removeOpcion(String key)
	{
		map.remove(key);
	}
	
	public void salvarMap()
	{
		
	}
	

	
}
