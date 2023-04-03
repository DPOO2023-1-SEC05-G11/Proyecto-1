import java.time.*;
import java.util.*;


public class Reserva {
 
	private int fecha;
	private int duracionEstadia;
	private Grupo grupoHuespedes;
	private ArrayList<Habitacion> habitacionesReservadas;
	
	public Reserva(int fecha, int duracionEstadia, Grupo grupoHuespedes, ArrayList<Habitacion> habitacionesReservadas) {
		//super();
		this.fecha = fecha;
		this.duracionEstadia = duracionEstadia;
		this.grupoHuespedes = grupoHuespedes;
		this.habitacionesReservadas = habitacionesReservadas;
	}

	public void setFecha(int fecha) {
		this.fecha = fecha;
	}

	public void setDuracionEstadia(int duracionEstadia) {
		this.duracionEstadia = duracionEstadia;
	}

	public void setGrupoHuespedes(Grupo grupoHuespedes) {
		this.grupoHuespedes = grupoHuespedes;
	}

	public void setHabitacionesReservadas(ArrayList<Habitacion> habitacionesReservadas) {
		this.habitacionesReservadas = habitacionesReservadas;
	}

}
