import java.util.ArrayList;

public class Menu {
	private ArrayList<Producto> menu;
	
	public Menu(ArrayList<Producto> menu) {
		
		this.menu = menu;



		// TODO Auto-generated constructor stub
	}

	public void removeProduct(Producto prod)
	{
		this.menu.remove(prod);
	}
	
	public String getTipo(Producto prod)
	{
		return "";
	}
	
	public float getPrecio(Producto prod)
	{
		return (float) 0.0;
	}
}
