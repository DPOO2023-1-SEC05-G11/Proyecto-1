import java.util.ArrayList;

public class Habitacion {
	
	private int id;
	private String tipo;
	private ArrayList<String> caracteristicas;
	private Double tarifa;
	private boolean ocupado;
	
	/**
	 * @param id
	 * @param tipo
	 * @param tamaño
	 * @param tarifa
	 * @param ocupada
	 */
	public Habitacion(int id, String tipo, ArrayList<String> caracteristicas, Double tarifa, Boolean ocupada) {

		this.id = id;
		this.tipo = tipo;
		this.caracteristicas = caracteristicas;
		this.tarifa = tarifa;
		this.ocupado = ocupada;
		// TODO Auto-generated constructor stub
	}

		public int getId(){
			return this.id;
		}

		public String getTipo()
		{
			return this.tipo;
		}

		public ArrayList<String> getCaracteristicas()
		{
			return this.caracteristicas;
		}

		public Double getTarifa()
		{
			return this.tarifa;
		}

		public Boolean isOcupado()
		{
			return this.ocupado;
		}

	

	private void updateTarifa(Habitacion room) 
	{
		
	}
	
	private void addCaracteristicas(Habitacion room, ArrayList<String> caracteristicas)
	{
		
	}
	
	private void removeCaracteristicas(Habitacion room, ArrayList<String> caracteristicas)
	{
		
	}
	
	public boolean isOcupado(Habitacion room)
	{
		return false;
	}
}
