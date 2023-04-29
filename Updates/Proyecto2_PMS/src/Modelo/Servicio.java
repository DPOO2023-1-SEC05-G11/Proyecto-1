package Modelo;

import java.util.HashMap;

public class Servicio {
	
	protected static HashMap<String, Integer> map;
	
	
	protected Servicio(HashMap<String, Integer> map)
	{
		this.map = map;
	}
	
	public static void mostrarOpciones()
	{
		
	}
	
	public static Servicio getInstance(HashMap<String, Integer> map)// tal vez no lo necesitamos
	{
		return new Servicio(map);
	}

	
}
