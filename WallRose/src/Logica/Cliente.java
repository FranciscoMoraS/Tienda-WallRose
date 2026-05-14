package Logica;

import java.util.Map;
import java.util.TreeMap;

public class Cliente {
	private String ID;
	private String Nombre;
	private String Email;
	private Map<Integer, Orden> Ordenes;
	
	public Cliente(String id, String nombre, String email) {
		this.ID=id;
		this.Nombre=nombre;
		this.Email=email;
		Ordenes = new TreeMap<Integer, Orden>();
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getID() {
		return ID;
	}

	public Map<Integer, Orden> getOrdenes() {
		return Ordenes;
	}
	
}
