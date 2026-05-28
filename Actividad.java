package frantaieltpintegrador;

import java.time.LocalDate;

public abstract class Actividad {

    private LocalDate fecha;
    private double monto;
    private boolean aprobada;

    public Actividad(double monto, boolean aprobada) {
            if (monto <= 0) {
            throw new IllegalArgumentException("El monto no puede ser negativo");
        }
    if (fecha == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula");
        }
    if (monto<cuentaOrigen.getSaldoDisponible()) {
            throw new IllegalArgumentException("El monto no puede ser mayor al saldo disponible");
        }

        this.fecha = LocalDate.now();
        this.monto = monto;
        this.aprobada = aprobada;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public double getMonto() {
        return monto;
    }

    public boolean isAprobada() {
        return aprobada;
    }

    public boolean esAprobada() {
        return aprobada;
    }
}