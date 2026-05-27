package ar.edu.ungs.billetera;

public class CuentaRegular extends Cuenta {
    private static final double SALDO_MAXIMO = 5000000.0;

    public CuentaRegular(String cvu, String alias, Usuario usuario) {
        super(cvu, alias, usuario);
    }

    @Override
    public boolean puedeOperar(double monto) {
        return getSaldo() >= monto;
    }

    @Override
    public void acreditar(double monto) {
        if (getSaldo() + monto > SALDO_MAXIMO)
            throw new IllegalArgumentException("Supera el saldo maximo de $5.000.000.");
        super.acreditar(monto);
    }

    @Override
    public String getTipo() {
        return "Regular";
    }
}