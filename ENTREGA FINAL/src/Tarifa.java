
public class Tarifa {
	
	private String tipo;
	private float precio;
	
	public Tarifa(String tipo, float precio) {
		super();
		this.tipo = tipo;
		this.precio = precio;
	}

	public String getTipo() {
		return tipo;
	}

	public float getPrecio() {
		return precio;
	}
	
	public void updateTarifa(Tarifa fee) {
	}
}
