package models;

public class Usuario {
    private String username;
    private String password;

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    // Método para verificar la contraseña
    public boolean verificarPassword(String password) {
        return this.password.equals(password);
    }
}
