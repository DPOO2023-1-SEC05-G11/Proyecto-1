import java.time.*;

public class Producto {
	
	private String nombre;
	private float precio;
	private String tipo;
	private int available;
	private boolean paraHabitacion;
	
	public Producto(String nombre, float precio, String tipo, int available, boolean paraHabitacion) {
		//super();
		this.nombre = nombre;
		this.precio = precio;
		this.tipo = tipo;
		this.available = available;
		this.paraHabitacion = paraHabitacion;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}
}
