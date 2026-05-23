package ar.edu.ungs.billetera;

public class RentaFija extends Inversion {

    private int tasaDeInteres;
    private int resultado;


    public RentaFija(Cuenta cuenta, double monto, int plazo, double tasaDeInteres) {
       super(cuenta, monto, plazo, true);
        this.tasaDeInteres = tasaDeInteres;
    }

    @Override
    public double calcularResultado(){
        return getMonto() * (1 + tasaDeInteres * getPlazo());
    }

     @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("fecha: ").append(getFecha()).append("\n");
        sb.append("origen: ").append(getCuenta().getUsuario().getDni());
        sb.append(" (").append(getCuenta().getCvu()).append(")\n");
        sb.append("desc: RentaFija\n");
        sb.append("monto: ").append(getMonto()).append("\n");
        sb.append("plazo: ").append(getPlazo()).append("\n");
        sb.append(isAprobada() ? "Aprobado" : "Rechazado");
        return sb.toString();
    }
}