package Logica;

public class Producto {
	private static int Consecutivo;
	private int ID;
	private String Nombre;
	private String Medida;
	private double Cantidad;
	private double Precio;
	
	public Producto(String nombre, String medida, double cantidad, double precio) throws Exception {
		//ingresar precio por 1 de la medida elegida.
		if (cantidad<=0)
			throw new Exception("Cantidad no valida.");
		if (precio<=0)
			throw new Exception("precio no valido.");
		this.Nombre=nombre;
		this.Medida=medida;
		this.Cantidad=cantidad;
		this.Precio=precio;
		this.ID=Consecutivo;
		Consecutivo++;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getMedida() {
		return Medida;
	}

	public void setMedida(String medida) {
		Medida = medida;
	}

	public double getCantidad() {
		return Cantidad;
	}

	public void setCantidad(double cantidad) throws Exception {
		if (cantidad<=0)
			throw new Exception("Cantidad no valida.");
		Cantidad = cantidad;
	}

	public double getPrecio() {
		return Precio;
	}

	public void setPrecio(double precio) throws Exception {
		if (precio<=0)
			throw new Exception("precio no valido.");
		Precio = precio;
	}

	public int getID() {
		return ID;
	}
	
	
}
