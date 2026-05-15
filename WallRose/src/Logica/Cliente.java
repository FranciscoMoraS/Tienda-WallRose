package Logica;

import java.util.ArrayList;
import java.util.List;
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

	public List<Orden> getOrdenes() {
		List<Orden> l = new ArrayList<Orden>();
		for (int i=0; i<Ordenes.size(); i++) {
			l.add(Ordenes.get(i));
		}
		return l;
	}
	public Map<Integer, Orden> getDiccionarioOrdenes(){
		return Ordenes;
	}
	public void agregarOrden(Orden orden) {
		Ordenes.put(orden.getNumero(), orden);
	}
	
}
