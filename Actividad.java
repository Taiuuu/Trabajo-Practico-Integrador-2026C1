package frantaieltpintegrador;

import java.time.LocalDate;

public abstract class Actividad {
    private LocalDate fecha;
    private double monto;
    private boolean aprobada;

    public Actividad(double monto, boolean aprobada) {
        this.fecha = LocalDate.now();
        this.monto = monto;
        this.aprobada = aprobada;

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