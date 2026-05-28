package frantaieltpintegrador;

public abstract class Inversion extends Actividad {

    private Cuenta cuenta;
    private int plazo;
    private int id;
    private boolean precancelable;
    private boolean activa;

    public Inversion(Cuenta cuenta, double monto, int plazo, boolean precancelable) {
        super(monto, true);
        if (cuenta == null) {
            throw new IllegalArgumentException("La cuenta no puede ser nula");
        }
        if (plazo <= 0) {
            throw new IllegalArgumentException("El plazo debe ser mayor a cero");
        }
        this.cuenta = cuenta;
        this.plazo = plazo;
        this.precancelable = precancelable;
        this.activa = true;
    }

    public Cuenta getCuenta(){ 
        return cuenta;
    }
    
    public int getPlazo(){
        return plazo;
    }

    public int getId(){
        return id;
    }

    public boolean isPrecancelable(){ 
        eturn precancelable;
    }
    
    public boolean estaActiva(){ 
        return activa;
    }

    public void setId(int id){
        this.id = id;
    }

    public void cancelar(){
        if (!precancelable)
            throw new IllegalArgumentException("La inversion no es precancelable.");
        activa = false;
    }
    public void finalizar(){
        activa = false;
    }

    public abstract double calcularResultado();

    public double calcularGanancia() {
        double ganancia = calcularResultado() - getMonto();
        return ganancia;
    }


    @Override
    public abstract String toString();
}