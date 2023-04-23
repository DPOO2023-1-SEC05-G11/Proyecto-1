import java.io.File;
import java.util.ArrayList;

public class TestLoader 
{
    

    public static void main(String[] args)
	{

        Loader loader = new Loader();

		ArrayList<Habitacion> habs = loader.cargarHabitaciones();

        for (Habitacion hab : habs)
        {
        System.out.println(hab.getId() + ", " + hab.getTipo() + ", " + hab.getTarifa() + ", " + hab.isOcupado());
        }
        System.out.println("################################################");

        loader.updateHabitaciones(new File("data/otrasHabs.txt"));
        habs = loader.cargarHabitaciones();

        for (Habitacion hab : habs)
        {
        System.out.println(hab.getId() + ", " + hab.getTipo() + ", " + hab.getTarifa() + ", " + hab.isOcupado());
        }
        System.out.println("################################################");

        ArrayList<String> caract = new ArrayList<String>();
        for (int x=0;x>4;x++)
        {
            caract.add("true");

        }

        Habitacion room = new Habitacion(5555, "duplex", caract, 0.0, false);

        loader.addHabitacion(room, habs);

        System.out.println("Cargaste la habitación");

        for (Habitacion hab : habs)
        {
        System.out.println(hab.getId() + ", " + hab.getTipo() + ", " + hab.getTarifa() + ", " + hab.isOcupado());
        }
        System.out.println("################################################");

        loader.removeHabitacion(room.getId(), habs);

        System.out.println("Eliminaste la habitación");

        for (Habitacion hab : habs)
        {
        System.out.println(hab.getId() + ", " + hab.getTipo() + ", " + hab.getTarifa() + ", " + hab.isOcupado());
        }
        System.out.println("################################################");

        Menu menu = loader.addMenu();

        for (Producto producto : menu.menu)
        {
        System.out.println(producto.getNombre() + ", " + producto.getPrecio() + ", " + producto.isAvailable());
        }
        System.out.println("################################################");

        loader.updateMenu(new File("data/nuevoMenu.txt"));
        menu = loader.addMenu();

        for (Producto producto : menu.menu)
        {
        System.out.println(producto.getNombre() + ", " + producto.getPrecio() + ", " + producto.isAvailable());
        }
        System.out.println("################################################");

        loader.removeMenu(new Producto("wrap de pollo",(float)15000,true), menu);
        loader.removeMenu(new Producto("gaseosa",(float)5000,true), menu);

        for (Producto producto : menu.menu)
        {
        System.out.println(producto.getNombre() + ", " + producto.getPrecio() + ", " + producto.isAvailable());
        }
        System.out.println("################################################");

	}

}
