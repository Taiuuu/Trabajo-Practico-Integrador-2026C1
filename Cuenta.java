package frantaieltpintegrador;

import java.util.ArrayList;
import java.util.List;

public abstract class Cuenta {
    private String cvu;
    private String alias;
    private double montoInvertido;
    private double saldoDisponible;
    private Usuario usuario;
    private List<Actividad> actividades;

    public Cuenta(String cvu, String alias, Usuario usuario) {
        this.cvu = cvu;
        this.alias = alias;
        this.usuario = usuario;
        this.montoInvertido = 0;
        this.saldoDisponible = 0;
        this.actividades = new ArrayList<>();
    }

    public String getCvu() {
        return cvu;
    }

    public String getAlias() {
        return alias;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public double getSaldoDisponible() {
        return saldoDisponible;
    }

    public double getMontoInvertido() {
        return montoInvertido;
    }

    public void invertir(double monto) {
        if (monto < 0) {
            throw new IllegalArgumentException("El monto de inversión no puede ser negativo");
        }
        this.montoInvertido += monto;
    }

    public void acreditar(double monto) {
        if (monto < 0) {
            throw new IllegalArgumentException("El monto a acreditar no puede ser negativo");
        }
        this.saldoDisponible += monto;
    }

    public void debitar(double monto) {
        if (monto < 0) {
            throw new IllegalArgumentException("El monto a debitar no puede ser negativo");
        }
        if (!puedeOperar(monto)) {
            throw new RuntimeException("Saldo insuficiente");
        }
        this.saldoDisponible -= monto;
    }

    public double getSaldo() {
        return saldoDisponible;
    }

    public void registrarActividad(Actividad actividad) {
        actividades.add(actividad);
    }

    public List<Actividad> getActividades() {
        return actividades;
    }

    public boolean puedeOperar(double monto) {
        return saldoDisponible >= monto;
    }

    public abstract String getTipo();

    @Override
    public String toString() {
        return getTipo() + ":" + alias + "(" + cvu + ")" + " Saldo: $" + saldoDisponible;
    }
}