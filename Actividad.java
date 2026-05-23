package ar.edu.ungs.billetera;

import java.time.LocalDate;

public abstract class Actividad {
    private LocalDate fecha;
    private double mnonto;
    private boolean aprobada;

    public Actividad(LocalDate fecha, double mnonto) {
        this.fecha = fecha;
        this.mnonto = mnonto;
        this.aprobada = aprobada;
    }

    public localDate getFecha() {
        return fecha;
    }

    public double getMonto() {
        return mnonto;
    }

    public boolean esAprobada() {
        return aprobada;
    }
}