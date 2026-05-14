package Logica;

public class Linea {
	private double cantidad;
	private Producto producto;
	
	public Linea(Producto producto, double cantidad) throws Exception {
		if (cantidad<=0)
			throw new Exception("Cantidad no valida.");
		if (cantidad > producto.getCantidad())
			throw new Exception("No hay suficiente producto");
		this.producto=producto;
		this.cantidad=cantidad;
	}
	public double getCantidad() {
		return cantidad;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public void setCantidad(double cantidad) throws Exception {
		try {
			if (cantidad > producto.getCantidad())
				throw new Exception("No hay suficiente producto");
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.cantidad = cantidad;
	}
	public double getCoste() {
		double coste;
		coste= cantidad*producto.getPrecio();
		return coste;
	}
}
