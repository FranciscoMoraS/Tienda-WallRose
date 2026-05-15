package Controladora;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Logica.Cliente;
import Logica.Linea;
import Logica.Orden;
import Logica.Producto;

public class ControladoraWallRose {
	private static ControladoraWallRose instance=null;
	private Map<String, Cliente> clientes;
	private Map<Integer, Producto> productos;
	private Map<Integer, Orden> ordenes;
	
	
	
	public ControladoraWallRose() {
		clientes = new TreeMap<String, Cliente>();
		productos = new TreeMap<Integer, Producto>();
		ordenes = new TreeMap<Integer, Orden>();	
	}



	public static ControladoraWallRose getInstance() {
		if (instance == null)
			instance = new ControladoraWallRose();
		return instance;
	}
	public List<Cliente> obtenerListadoClientes(){
		List<Cliente> listaClientes = new ArrayList<Cliente>();
		for (int i=0; i<clientes.size();i++) {
			Cliente c = clientes.get(i);
			listaClientes.add(c);
		}
		return listaClientes;
	}
	private void verificarClienteExistente(String idCliente) throws Exception {
		if (!clientes.containsKey(idCliente))
			throw new Exception("Cliente no encontrado.");
	}
	private boolean esEmailValido(String email) {
		Pattern p = Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
		Matcher m = p.matcher(email);
		return m.matches();
	}
	
	private void verificarEmail(String email) throws Exception {
		if (!esEmailValido(email))
			throw new Exception("Email no válido.");
	}
	private void verificarClienteNoExistente(String idCliente) throws Exception {
		if (clientes.containsKey(idCliente))
			throw new Exception("Cliente duplicado.");
	}


	public Cliente obtenerCliente(String idCliente) throws Exception {
		verificarClienteExistente(idCliente);
		Cliente c= clientes.get(idCliente);
		return c;
	}
	public List <Orden> obtenerListadoOrdenesCliente (String idCliente) throws Exception{
		verificarClienteExistente(idCliente);
		Cliente c = clientes.get(idCliente);
		return c.getOrdenes();
	}
	private List<Orden> obtenerListadoOrdenesPorEstado(String idCliente, int estado) throws Exception {
		if (estado<0 || estado>3)
			throw new Exception("Estado no valido");
		List<Orden> listadoOrdenes = new ArrayList<Orden>();
		for (Map.Entry<Integer, Orden> entry : ordenes.entrySet()) {
			Orden orden = entry.getValue();
			if (orden.getEstado() == estado)
				listadoOrdenes.add(orden);
		}
		return listadoOrdenes;		
	}
	private void verificarOrdenExistente(Integer numeroOrden) throws Exception  {
		if (!ordenes.containsKey(numeroOrden))
			throw new Exception("Orden no encontrada.");
	}
	private void verificarLineaOrdenExistente(Integer numeroOrden, Integer numeroLinea)throws Exception {
		Orden orden = ordenes.get(numeroOrden);
		if (numeroLinea < 0 || numeroLinea >= orden.getCantLineas())
			throw new Exception("Número de línea no válido.");
	}



	public List<Orden> obtenerListadoOrdenesIniciadasCliente (String idCliente) throws Exception {
		verificarClienteExistente(idCliente);
		return obtenerListadoOrdenesPorEstado(idCliente, 1);
	}
	public List<Orden>obtenerListadoOrdenesPendientesCliente (String idCliente) throws Exception {
		verificarClienteExistente(idCliente);
		return obtenerListadoOrdenesPorEstado(idCliente, 2);
	}
	public List<Orden> obtenerListadoOrdenesTerminadasCliente (String idCliente) throws Exception {
		verificarClienteExistente(idCliente);
		return obtenerListadoOrdenesPorEstado(idCliente, 3);
	}
    public void crearCliente(String idCliente, String nombre, String email) throws Exception {
    	verificarClienteNoExistente(idCliente);
		verificarEmail(email);
		Cliente c = new Cliente(idCliente, nombre, email);
		clientes.put(idCliente, c);
    }
    public void actualizarCliente (String idCliente, String nombre, String email) throws Exception {
    	verificarClienteExistente(idCliente);
		verificarEmail(email);
		Cliente c = clientes.get(idCliente);
		c.setNombre(nombre);
		c.setEmail(email);

    }
    public void borrarCliente(String idCliente) throws Exception {
    	verificarClienteExistente(idCliente);
		Cliente c = clientes.get(idCliente);
		Map<Integer, Orden> ordenesCliente = c.getDiccionarioOrdenes();
		for (Integer numeroOrden : ordenesCliente.keySet()) {
			ordenes.remove(numeroOrden);
		}
		clientes.remove(idCliente);
    }
    public List<Producto> obtenerListaProductos() {
    	List<Producto> listaProductos = new ArrayList<Producto>();
    	for (Map.Entry<Integer, Producto> entry : productos.entrySet()) {
    		listaProductos.add(entry.getValue());
    	}
    	return listaProductos;
    }
    public void crearProducto(String nombre, String medida, Double cantidad, double Precio) throws Exception {
    	if (nombre.equals(""))
    		throw new Exception("Nombre no puede ser vacío.");
    	Producto p = new Producto(nombre, medida, cantidad, Precio);
    	productos.put(p.getID(), p);
    }

    private void verificarProductoExistente(Integer codigoProducto) throws Exception {
		if (!productos.containsKey(codigoProducto))
			throw new Exception("Producto no encontrado.");
	}

    public Producto obtenerProducto (int codigoProducto) throws Exception {
    	verificarProductoExistente(codigoProducto);
		Producto p = productos.get(codigoProducto);
		return p;
    }
    public void actualizarProducto (int codigo, String nombre, String medida, double cantidad, double precio) throws Exception {
    	verificarProductoExistente(codigo);
		Producto p = productos.get(codigo);
		p.setNombre(nombre);
		p.setCantidad(cantidad);
		p.setMedida(medida);
		p.setPrecio(precio);	
    }
	public void borrarProducto (int codigoProducto) throws Exception {
		verificarProductoExistente(codigoProducto);
		for (Orden orden : ordenes.values()) {
			for (Linea linea : orden.getLineas()) {
				Producto producto = linea.getProducto();
				if (producto.getID() == codigoProducto) {
					throw new Exception("El producto está siendo utilizado en la orden " + orden.getNumero() + ".");
				}				
			}
		}
		productos.remove(codigoProducto);
	}
	public List<Orden> obtenerListaOrdenes() {
		List<Orden> listaOrdenes = new ArrayList<Orden>();
		for (Orden orden : ordenes.values()) {
			listaOrdenes.add(orden);
		}
		return listaOrdenes;
	}
	public double obtenerMontoTotalPendiente() {
		double totalPendiente = 0;
		for (Orden orden : ordenes.values()) {
			if (orden.getEstado() == 2) {
				totalPendiente += orden.calcTotal();
			}			
		}
		return totalPendiente;
	}
	public void crearOrdenVacia(String idCliente) throws Exception {
		verificarClienteExistente(idCliente);
		Cliente cliente = clientes.get(idCliente);
		Orden orden = new Orden(cliente);
		ordenes.put(orden.getNumero(), orden);
		cliente.agregarOrden(orden);

	}
	
	public Orden obtenerOrden(int numeroOrden) throws Exception {
		verificarOrdenExistente(numeroOrden);
		return ordenes.get(numeroOrden);
	}
	public List <Linea> obtenerLineasOrden (int numeroOrden) throws Exception{
		verificarOrdenExistente(numeroOrden);
		Orden orden = ordenes.get(numeroOrden);
		return orden.getLineas();
	}
	public void establecerOrdenPendiente(int numeroOrden) throws Exception {
		verificarOrdenExistente(numeroOrden);
		Orden orden = ordenes.get(numeroOrden);
		orden.setEstado(2);
	}
	public void establecerOrdenTerminada (int numeroOrden) throws Exception {
		verificarOrdenExistente(numeroOrden);
		Orden orden = ordenes.get(numeroOrden);
		orden.setEstado(3);
	}
	public void agregarLineaOrden(int numeroOrden, int codigoProducto, Double cantidad) throws Exception {
		verificarOrdenExistente(numeroOrden);
		verificarProductoExistente(codigoProducto);
		if (cantidad < 0)
			throw new Exception("La cantidad no debe ser negativa.");
		Orden orden = ordenes.get(numeroOrden);
		Producto producto = productos.get(codigoProducto);
		orden.addLinea(producto, cantidad);
	}
	public void actualizarLineaOrden(int numOrden, int numLinea, int codigoProducto, double cantidad) throws Exception {
		verificarOrdenExistente(numOrden);
		verificarLineaOrdenExistente(numOrden, numLinea);
		verificarProductoExistente(codigoProducto);
		Orden orden = ordenes.get(numOrden);
		Producto producto = productos.get(codigoProducto);
		Linea linea = orden.getLinea(numLinea);
		linea.setProducto(producto);
		linea.setCantidad(cantidad);
	}
	public void borrarLineaOrden(int numOrden, int numLinea) throws Exception {
		verificarOrdenExistente(numOrden);
		verificarLineaOrdenExistente(numOrden, numLinea);
		Orden orden = ordenes.get(numOrden);
		orden.borrarLinea(numLinea);		
	}
	public void borrarOrden(int numOrden) throws Exception {
		verificarOrdenExistente(numOrden);
		ordenes.remove(numOrden);

	}

	
	
}
