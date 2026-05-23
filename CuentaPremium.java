public class Cuenta_Premium extends Cuenta {
    public Cuenta_Premium(String cvu, String alias, String usuario) {
        super(cvu, alias, usuario);
    }
    @override
    public string getTipo() {
        return "Premium";
    }
    public boolean puedeOperar(double monto) {
        return getSaldoDisponible() >= monto * 0.9; // Permite operar con un 10% de margen adicional
    }
}