package frantaieltpintegrador;

import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private String cuit;
    private String nombreautorizado;
    private String telefono;
    private String mail;
    private String nombreContacto;
    private ArrayList<String> dniAutorizados;

    public Empresa(String cuit, String nombreAutorizado, String telefono, String mail, String nombreContacto) {
        if (cuit == null || cuit.isEmpty()) {
            throw new IllegalArgumentException("El CUIT no puede ser nulo o vacío.");
        }
        if (nombreAutorizado == null || nombreAutorizado.isEmpty()) {
            throw new IllegalArgumentException("El nombre del autorizado no puede ser nulo o vacío.");
        }
        if (telefono == null || telefono.isEmpty()) {
            throw new IllegalArgumentException("El teléfono no puede ser nulo o vacío.");
        }   
        if (mail == null || mail.isEmpty()) {
            throw new IllegalArgumentException("El mail no puede ser nulo o vacío.");
        }
        if (nombreContacto == null || nombreContacto.isEmpty()) {
            throw new IllegalArgumentException("El nombre del contacto no puede ser nulo o vacío.");
        }
        this.cuit = cuit;
        this.nombreautorizado = nombreAutorizado;
        this.telefono = telefono;
        this.mail = mail;
        this.nombreContacto = nombreContacto;
        this.dniAutorizados = new ArrayList<>();
    }

    public String getCuit() {
        return cuit;
    }
    
    public String getNombreAutorizado() {
        return nombreautorizado;
    }

    public boolean agregarPersonaAutorizada(String dni) {
        return dniAutorizados.add(dni);
    }

    public boolean estaAutorizado(String dni) {
        return dniAutorizados.contains(dni);
    }

    public ArrayList<String> getPersonasAutorizadas() {
        return dniAutorizados;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Empresa: ").append(nombreautorizado).append("\n");
        sb.append("CUIT: ").append(cuit).append("\n");
        sb.append("Autorizados: ").append(dniAutorizados).append("\n");
        return sb.toString();
    }
}