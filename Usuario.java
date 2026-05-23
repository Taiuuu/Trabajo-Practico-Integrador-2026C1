package ar.edu.ungs.billetera;
public class Usuario{

    private String id;
    private String nombre;
    private ArrayList<Cuenta> cuentas;


    public Usuario(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.cuentas = new ArrayList<>();
    }

    public String getId() {
        return id;
    } 

    public String getNombre() {
        return nombre;
    }
    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }
    
    @Override
    public String toString() {
        for (Cuenta cuenta : cuentas) {
            return "Tipo de cuenta: " + cuenta.getTipo() + ", Alias: " + cuenta.getAlias() + ", CVU: " + cuenta.getCvu() ;
        }
        return "No hay cuentas para mostrar";
    }
    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    public double totalInvertido(double monto) {
        double total = 0;
        for (Cuenta cuenta : cuentas) {
            total += cuenta.getMontoInvertido();
        }
        return total;
    }
}