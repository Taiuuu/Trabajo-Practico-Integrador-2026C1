package frantaieltpintegrador;

public class InversionDivisa extends Inversion {

    private String divisa;
    private double tasaDeInteres;
    private double cotizacionInicial;


    public InversionDivisa(Cuenta cuenta, double monto, int plazo, String divisa, double tasaDeInteres) {
        super(cuenta, monto, plazo, true);
        if (divisa == null || divisa.isEmpty()) {
            throw new IllegalArgumentException("La divisa no puede ser nula o vacía");
        }
        if (tasaDeInteres <= 0) {
            throw new IllegalArgumentException("La tasa de interes debe ser mayor a cero");
        }
        this.divisa = divisa;
        this.tasaDeInteres = tasaDeInteres;
        this.cotizacionInicial = Utilitarios.consultarCotizacion(divisa);
    }

    @Override
    public double calcularResultado() {
        double cotizacionActual = Utilitarios.consultarCotizacion(divisa);
        return getMonto() * (cotizacionActual / cotizacionInicial) * (1 + tasaDeInteres);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("fecha: ").append(getFecha()).append("\n");
        sb.append("origen: ").append(getCuenta().getUsuario().getDni());
        sb.append(" (").append(getCuenta().getCvu()).append(")\n");
        sb.append("desc: InversionDivisa(").append(divisa).append(")\n");
        sb.append("monto: ").append(getMonto()).append("\n");
        sb.append("plazo: ").append(getPlazo()).append("\n");
        sb.append(isAprobada() ? "Aprobado" : "Rechazado");
        return sb.toString();
    }
}