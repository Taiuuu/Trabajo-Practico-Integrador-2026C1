package ar.edu.ungs.billetera;

import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private String cuit;
    private String nombreFantasia;
    private String telefono;
    private String mail;
    private nombreContacto
    private List<Cuenta> cuentas;

    public Empresa(String cuit, String nombreFantasia, String telefono, String mail, String nombreContacto) {
        this.cuit = cuit;
        this.nombreFantasia = nombreFantasia;
        this.telefono = telefono;
        this.mail = mail;
        this.nombreContacto = nombreContacto;
        this.cuentas = new ArrayList<>();
    }

    public String getCuit() {
        return cuit;
    }
    
    public String getNombreFantasia() {
        return nombreFantasia;
    }

    public boolean agregarAutorizado(String dni) {
        return dniAutorizados.add(dni);
    }

    public boolean estaAutorizado(String dni) {
        dniAutorizados.add(dni);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Empresa: ").append(nombreFantasia).append("\n");
        sb.append("CUIT: ").append(cuit).append("\n");
        sb.append("Autorizados: ").append(dniAutorizados).append("\n");
        return sb.toString();
    }
}