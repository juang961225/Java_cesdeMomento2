package services;

import models.Producto;
import models.Usuario;
import models.Orden;
import java.util.ArrayList;
import java.util.List;

public class Tienda {
    private List<Usuario> usuarios;
    private List<Producto> productos;
    private List<Orden> ordenes;

    public Tienda() {
        usuarios = new ArrayList<>();
        productos = new ArrayList<>();
        ordenes = new ArrayList<>();
        cargarDatosIniciales();
    }

    private void cargarDatosIniciales() {
        // Añadir algunos productos iniciales
        productos.add(new Producto("Laptop", 1000.0, 10));
        productos.add(new Producto("Smartphone", 500.0, 20));
        productos.add(new Producto("Tablet", 300.0, 15));
    }

    public boolean registrarUsuario(String username, String password) {
        for (Usuario u : usuarios) {
            if (u.getUsername().equals(username)) {
                return false;
            }
        }
        usuarios.add(new Usuario(username, password));
        return true;
    }

    public Usuario login(String username, String password) {
        for (Usuario u : usuarios) {
            if (u.getUsername().equals(username) && u.verificarPassword(password)) {
                return u;
            }
        }
        return null;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public boolean crearOrden(Usuario usuario, List<Producto> productos) {
        for (Producto p : productos) {
            if (p.getStock() <= 0) {
                return false;
            }
        }
        ordenes.add(new Orden(usuario, productos));
        for (Producto p : productos) {
            p.reducirStock(1);  // Asumiendo que cada orden reduce 1 del stock
        }
        return true;
    }

    public List<Orden> getOrdenes() {
        return ordenes;
    }
}
