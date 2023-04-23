import java.util.*;

public class Grupo {
	
	private ArrayList<Huesped> huespedesAsociados;
	private Factura factura;
	
	public Grupo(ArrayList<Huesped> huespedesAsociados, Factura factura) {
		super();
		this.huespedesAsociados = huespedesAsociados;
		this.factura = factura;
	}

	public ArrayList<Huesped> getHuespedesAsociados() {
		return huespedesAsociados;
	}

	public Factura getFactura() {
		return factura;
	}
	
}
