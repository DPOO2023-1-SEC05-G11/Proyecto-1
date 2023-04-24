import java.time.*;

public class Producto {
	
	private String nombre;
	private float precio;
	private boolean available;
	private boolean paraHabitacion = false;
	
	public Producto(String nombre, float precio, boolean available) {
		//super();
		this.nombre = nombre;
		this.precio = precio;
		this.available = available;
	}

	public boolean equals(Producto product)
	{
		Boolean equals = true;
		if (!(this.nombre.equals(product.nombre))) {equals = false;}
		if (this.precio != product.precio) {equals = false;}
		if (this.available != product.available) {equals = false;}
		if (this.paraHabitacion != product.paraHabitacion) {equals = false;}
		return equals;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
}
