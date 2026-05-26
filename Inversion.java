package frantaieltpintegrador;

public abstract class Inversion extends Actividad {

    private Cuenta cuenta;
    private int plazo;
    private int id;
    private boolean precancelable;
    private boolean activa;

    public Inversion(Cuenta cuenta, double monto, int plazo, boolean precancelable) {
        super(monto, true);
        this.cuenta = cuenta;
        this.plazo = plazo;
        this.precancelable = precancelable;
        this.activa = true;
    }

    public Cuenta getCuenta() { return cuenta; }
    public int getPlazo() { return plazo; }
    public int getId() { return id; }
    public boolean isPrecancelable() { return precancelable; }
    public boolean isActiva() { return activa; }

    public void setId(int id) { this.id = id; }

    public void cancelar() {
        if (!precancelable)
            throw new IllegalArgumentException("La inversion no es precancelable.");
        activa = false;
    }

    public abstract double calcularResultado();

    @Override
    public abstract String toString();
}