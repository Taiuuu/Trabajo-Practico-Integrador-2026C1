package frantaieltpintegrador;

public class FondoLiquidezEmpresarial extends Inversion {
    
    private static final double MONTO_MINIMO = 20000000.0;
    private static final String ACTIVO = "FLE";
    private static final double TASA = 0.08;

    private double cotizacionInicial;

    public FondoLiquidezEmpresarial(CuentaCorporativa cuenta, double monto, int plazo) {
        super(cuenta, monto, plazo, false);
        if (monto < MONTO_MINIMO)
            throw new IllegalArgumentException("El monto minimo es $20.000.000.");
        this.cotizacionInicial = Utilitarios.consultarCotizacion(ACTIVO);
    }

    @Override
    public double calcularResultado() {
        double cotizacionActual = Utilitarios.consultarCotizacion(ACTIVO);
        return getMonto() * (cotizacionActual / cotizacionInicial) * (1 + TASA);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("fecha: ").append(getFecha()).append("\n");
        sb.append("origen: ").append(getCuenta().getUsuario().getDni());
        sb.append(" (").append(getCuenta().getCvu()).append(")\n");
        sb.append("desc: FondoLiquidezEmpresarial\n");
        sb.append("monto: ").append(getMonto()).append("\n");
        sb.append("plazo: ").append(getPlazo()).append("\n");
        sb.append(isAprobada() ? "Aprobado" : "Rechazado");
        return sb.toString();
    }
}