package ar.edu.ungs.billetera;

public class CuentaPremium extends Cuenta {
    private static final double SALDO_MINIMO = 500000.0;

    public CuentaPremium(String cvu, String alias, Usuario usuario, double saldoInicial) {
        super(cvu, alias, usuario);
        if (saldoInicial < SALDO_MINIMO)
            throw new IllegalArgumentException("El saldo inicial minimo para Cuenta Premium es $500.000.");
        acreditar(saldoInicial);
    }

    @Override
    public boolean puedeOperar(double monto) {
        return getSaldo() >= monto;
    }

    @Override
    public String getTipo() {
        return "Premium";
    }
}