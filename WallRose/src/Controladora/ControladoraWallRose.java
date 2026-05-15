package Controladora;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import Logica.Cliente;
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
	
//	+crearCliente(String idCliente, String nombre, String email)
//	+actualizarCliente (String idCliente, String nombre, String email)
//	+borrarCliente(idCliente)
//	+obtenerListadoProductos() : List<Producto>
//	+crearProducto(String nombre, String medida, Double cantidad, double Precio)
//	+obtenerProducto (int codigoProducto) : producto
//	+actualizarProducto (int codigo, String nombre, String medida, Double cantidad, Doubole precio)
//	+borrarProducto (int codigoProducto)
//	+ obtenerListadoOrdenes() : List<Orden>
//	+obtenerMontoTotalPendiente():double
//	+crearOrdenVacia(String idCliente)
//	+obtenerOrden(int numeroOrden) : Orden
//	+ obtenerLineasOrden (int numeroOrden): List <linea>
//	+establecerOrdenPendiente(int numeroOrden)
//	+ establecerOrdenTerminada (int numeroOrden)
//	+agregarLineaOrden(int numeroOrden, int codigoProducto, Double cantidad)
//	+actualizarLineaOrden(int numOrden, int numLinea, int codigoProducto, double cantidad)
//	+borrarLineaOrden(int numOrden int numLinea)
//	+borrarOrden(int numOrden)

	
	
}
