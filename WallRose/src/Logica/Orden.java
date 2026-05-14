package Logica;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Orden {
	private static int Consecutivo;
	private int Estado; // 1-iniciada, 2-pendiente, 3-terminada
	private int Numero;
	private LocalDateTime fechaCreacion;
	private Cliente cliente;
	private List<Linea> lineas;
	
	public Orden(Cliente cliente) {
		this.Numero=Consecutivo;
		Consecutivo++;
		this.Estado=1;
		fechaCreacion= LocalDateTime.now();
		this.cliente=cliente;
		lineas= new ArrayList<Linea>();
	}

	public int getEstado() {
		return Estado;
	}

	public void setEstado(int estado) {
		Estado = estado;
	}

	public int getNumero() {
		return Numero;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public List<Linea> getLineas() {
		return lineas;
	}
	public double calcCoste() {
		double costeTotal=0;
		for (int i=0; i<lineas.size();i++) {
			Linea l = lineas.get(i);
			costeTotal += l.getCoste();
		}
		return costeTotal;
	}
	public double calcImpuesto() {
		double impuesto=0;
		impuesto += calcCoste()*0.13;
		return impuesto;
	}
	public double calcTotal() {
		double total=0;
		total += calcCoste();
		total+= calcImpuesto();
		return total;
	}
	public void addLinea(Producto producto, Double cantidad) throws Exception {
		Linea l= new Linea(producto, cantidad);
		lineas.addLast(l);
	}
	public void borrarLinea(int numLinea) throws Exception {
		if (numLinea >= lineas.size())
			throw new Exception("La linea indicada no existe");
		lineas.remove(numLinea);
	}
	
}
