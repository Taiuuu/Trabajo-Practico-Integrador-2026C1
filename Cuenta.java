import java.util.ArrayList;
import java.util.List;

public abstract class Cuenta {
    private String cvu;
    private String alias;
    private double montoInvertido;
    private double saldoDisponible;
    private Usuario usuario;
    private ArrayList<Actividad> actividades;

    public Cuenta(String cvu, String alias, String usuario) {
        this.cvu = cvu;
        this.alias = alias;
        this.usuario = usuario;
        this.montoInvertido = 0.0;
        this.saldoDisponible = 0.0;
        this.actividades = new ArrayList<>();
    }

    public String getCvu() {
        return cvu;
    }
    public double getSaldoDisponible() {
        return saldoDisponible;
    }

    public String getAlias() {
        return alias;
    }

    public double getMontoInvertido() {
        return montoInvertido;
    }

    public void invertir(double monto) {
        this.montoInvertido += monto;
    }
    public double getSaldo() {
        return saldoDisponible;
    }
    public void registrarActividad(Actividad actividad) {
        actividades.add(Actividad);
    }
    public boolean puedeOperar(double monto) {
        return saldoDisponible >= monto;
    }

    @Override
    public String toString() {
        return getTipo() + ":" + alias + "(" + cvu + ")" + "Saldo: $" + saldoDisponible;
    }