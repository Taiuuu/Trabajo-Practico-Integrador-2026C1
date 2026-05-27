package frantaieltpintegrador;

public class Transferencia extends Actividad {

    private Cuenta cuentaOrigen;
    private Cuenta cuentaDestino;

    public Transferencia(Cuenta cuentaOrigen, Cuenta cuentaDestino, double monto, boolean aprobada) {
        super(monto, aprobada);
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
    }

    public Cuenta getCuentaOrigen(){
        return cuentaOrigen;
    }
    public Cuenta getCuentaDestino(){ 
        return cuentaDestino;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("fecha: ").append(getFecha()).append("\n");
        sb.append("origen: ").append(cuentaOrigen.getUsuario().getDni());
        sb.append(" (").append(cuentaOrigen.getCvu()).append(")\n");
        sb.append("destino: ").append(cuentaDestino.getUsuario().getDni());
        sb.append(" (").append(cuentaDestino.getCvu()).append(")\n");
        sb.append("monto: ").append(getMonto()).append("\n");
        sb.append(isAprobada() ? "Aprobado" : "Rechazado");
        return sb.toString();
    }
}