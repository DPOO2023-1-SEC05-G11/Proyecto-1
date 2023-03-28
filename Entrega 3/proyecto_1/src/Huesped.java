import java.util.*;

public class Huesped {
	
	private String nombre;
	private String documento;
	private String email;
	private String telefono;
	private Map datosDePago;
	
	public Huesped(String nombre, String documento, String email, String telefono, Map datosDePago) {
		//super();
		this.nombre = nombre;
		this.documento = documento;
		this.email = email;
		this.telefono = telefono;
		this.datosDePago = datosDePago;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefono() {
		return telefono;
	}

	public Map getDatosDePago() {
		return datosDePago;
	}

	public void setDatosDePago(Map datosDePago) {
		this.datosDePago = datosDePago;
	}
	
}
