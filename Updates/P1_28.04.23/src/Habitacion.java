import java.util.ArrayList;

public class Habitacion {
	
	private int id;
	private String tipo;
	private boolean balcon;
	private boolean vista;
	private boolean cocina;
	private int camas;
	private Double tarifa;
	private boolean ocupado;
	
	/**
	 * @param id
	 * @param tipo
	 * @param tamaño
	 * @param tarifa
	 * @param ocupada
	 */
	public Habitacion(int id, String tipo, Boolean balcon, Boolean vista, Boolean cocina, int camas, Double tarifa, Boolean ocupada) {

		this.id = id;
		this.tipo = tipo;
		this.balcon = balcon;
		this.vista = vista;
		this.cocina = cocina;
		this.camas = camas;
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

		public Boolean tieneBalcon(){
			return this.balcon;
		}
		
		public Boolean tieneVista(){
			return this.vista;
		}
		
		public Boolean tieneCocina(){
			return this.cocina;
		}
		
		public int getCamas(){
			return this.camas;
		}

		public Double getTarifa()
		{
			return this.tarifa;
		}

		public Boolean isOcupado()
		{
			return this.ocupado;
		}
		public void updateOcupado(boolean b) {
			this.ocupado = b;
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
	
	
	@Override
	public String toString() {
	    return "Habitacion [id=" + id + ", tipo=" + tipo + ", balcon=" + balcon + ", vista=" + vista + ", cocina=" + cocina + ", camas=" + camas +
	    		", tarifa=" + tarifa + ", ocupado=" + ocupado + "]";
	}

	
}
