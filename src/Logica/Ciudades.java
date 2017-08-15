package Logica;

public class Ciudades {
	
	private String nombre;
	private double longitud;
	private double latitud;
	
	public Ciudades(String nombre, double longitud, double latitud) {
		
		this.nombre = nombre;
		this.longitud = longitud;
		this.latitud = latitud;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	@Override
	public String toString() {
		return "Ciudades [nombre=" + nombre + ", longitud=" + longitud + ", latitud=" + latitud + "]";
	}
	
	
	
	

}
