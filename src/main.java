import services.Tienda;
import models.Producto;
import models.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Tienda tienda = new Tienda();
        Scanner scanner = new Scanner(System.in);
        Usuario usuarioActual = null;

        while (true) {
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Ver productos");
            System.out.println("4. Crear orden");
            System.out.println("5. Ver órdenes");
            System.out.println("6. Salir");
            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese nombre de usuario: ");
                    String username = scanner.nextLine();
                    System.out.print("Ingrese contraseña: ");
                    String password = scanner.nextLine();
                    if (tienda.registrarUsuario(username, password)) {
                        System.out.println("Usuario registrado exitosamente.");
                    } else {
                        System.out.println("El nombre de usuario ya existe.");
                    }
                    break;
                case 2:
                    System.out.print("Ingrese nombre de usuario: ");
                    username = scanner.nextLine();
                    System.out.print("Ingrese contraseña: ");
                    password = scanner.nextLine();
                    usuarioActual = tienda.login(username, password);
                    if (usuarioActual != null) {
                        System.out.println("Inicio de sesión exitoso.");
                    } else {
                        System.out.println("Nombre de usuario o contraseña incorrectos.");
                    }
                    break;
                case 3:
                    List<Producto> productos = tienda.getProductos();
                    for (Producto producto : productos) {
                        System.out.println("Nombre: " + producto.getNombre() + ", Precio: " + producto.getPrecio() + ", Stock: " + producto.getStock());
                    }
                    break;
                case 4:
                    if (usuarioActual == null) {
                        System.out.println("Debes iniciar sesión para crear una orden.");
                        break;
                    }
                    productos = tienda.getProductos();
                    List<Producto> productosOrden = new ArrayList<>();
                    for (int i = 0; i < productos.size(); i++) {
                        Producto producto = productos.get(i);
                        System.out.println((i + 1) + ". " + producto.getNombre() + " - " + producto.getPrecio() + " - Stock: " + producto.getStock());
                    }
                    System.out.println("Ingrese el número del producto para agregar a la orden (0 para finalizar): ");
                    while (true) {
                        int productoNumero = scanner.nextInt();
                        if (productoNumero == 0) {
                            break;
                        } else if (productoNumero > 0 && productoNumero <= productos.size()) {
                            productosOrden.add(productos.get(productoNumero - 1));
                            System.out.println("Producto agregado a la orden.");
                        } else {
                            System.out.println("Número de producto inválido.");
                        }
                    }
                    if (tienda.crearOrden(usuarioActual, productosOrden)) {
                        System.out.println("Orden creada exitosamente.");
                    } else {
                        System.out.println("No se pudo crear la orden. Verifica el stock de los productos.");
                    }
                    break;
                case 5:
                    if (usuarioActual == null) {
                        System.out.println("Debes iniciar sesión para ver tus órdenes.");
                        break;
                    }
                    List<models.Orden> ordenes = tienda.getOrdenes();
                    for (models.Orden orden : ordenes) {
                        if (orden.getUsuario().getUsername().equals(usuarioActual.getUsername())) {
                            System.out.println("Orden de " + orden.getUsuario().getUsername() + ": ");
                            for (Producto p : orden.getProductos()) {
                                System.out.println("- " + p.getNombre());
                            }
                        }
                    }
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción inválida. Intenta de nuevo.");
            }
        }
    }
}
