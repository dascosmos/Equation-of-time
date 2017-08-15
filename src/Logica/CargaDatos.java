package Logica;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import Presentacion.About;

public class CargaDatos {

	ArrayList<Ciudades> ciudades;

	public CargaDatos() {
		ciudades = new ArrayList<Ciudades>();
	}

	public void cargarDatos() {
		try {
			BufferedReader lector = new BufferedReader(new FileReader("ubicacion.txt"));


			String line = "";
			while ((line = lector.readLine()) != null) {
				
				StringTokenizer token = new StringTokenizer(line, ";");
				ciudades.add(new Ciudades(token.nextToken(), Double.parseDouble(token.nextToken()),Double.parseDouble(token.nextToken())));

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getTamaño() {

		return ciudades.size();
	}

	public String getCiudades(int i) {
		return ciudades.get(i).getNombre();
	}

	public double getLongitud(int i) {
		return ciudades.get(i).getLongitud();
	}

	public double getLatitud(int i) {
		return ciudades.get(i).getLatitud();
	}

	public void print() {
		for (int i = 0; i < ciudades.size(); i++) {
			System.out.println(ciudades.get(i));
		}
	}

}
