package models;

import java.util.List;

public class Orden {
    private Usuario usuario;
    private List<Producto> productos;

    public Orden(Usuario usuario, List<Producto> productos) {
        this.usuario = usuario;
        this.productos = productos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public List<Producto> getProductos() {
        return productos;
    }
}
