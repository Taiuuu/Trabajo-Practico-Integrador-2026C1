package frantaieltpintegrador;

/**
 * Cuenta regular con un saldo máximo de 5 millones de pesos.
 */
public class CuentaRegular extends Cuenta {
    private static double SALDO_MAXIMO = 5000000.0;

    public CuentaRegular(String cvu, String alias, Usuario usuario) {
        super(cvu, alias, usuario);
    }
    @override
    public string getTipo() {
        return "Regular";
    }

    @Override
    public boolean puedeOperar(double monto) {
        return super.puedeOperar(monto) && (getSaldoDisponible() + monto) <= SALDO_MAXIMO;
    }


    @Override
    public String toString() {
        return super.toString() + " [Tipo: Regular]";
    }
}