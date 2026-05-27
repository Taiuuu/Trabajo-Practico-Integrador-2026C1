package ar.edu.ungs.billetera;

import java.util.ArrayList;

public class Usuario {

    private String dni;
    private String nombre;
    private String telefono;
    private String email;
    private ArrayList<Cuenta> cuentas;
    private double totalInvertido;

    public Usuario(String dni, String nombre, String telefono, String email) {
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.cuentas = new ArrayList<>();
        this.totalInvertido = 0;
    }

    public String getDni(){
        return dni;
    }
    public String getNombre(){
        return nombre;
    }
    public ArrayList<Cuenta> getCuentas(){ 
        return cuentas;
    }
    public double getTotalInvertido(){ 
        return totalInvertido; 
    }

    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    public void sumarInvertido(double monto) {
        totalInvertido += monto;
    }

    public void restarInvertido(double monto) {
        totalInvertido -= monto;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuario: ").append(nombre).append(" (DNI: ").append(dni).append(")\n");
        for (Cuenta cuenta : cuentas) {
            sb.append("  ").append(cuenta).append("\n");
        }
        return sb.toString();
    }
}