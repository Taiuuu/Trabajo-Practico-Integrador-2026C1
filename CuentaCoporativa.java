package ar.edu.ungs.billetera;

public class CuentaCorporativa extends Cuenta {
    private Empresa empresa;

    public CuentaCorporativa(String cvu, String alias, Usuario usuario, Empresa empresa) {
        super(cvu, alias, usuario);
        this.empresa = empresa;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    @Override
    public boolean puedeOperar(double monto) {
        return getSaldo() >= monto;
    }

    @Override
    public String getTipo() {
        return "Corporativa";
    }

    @Override
    public String toString() {
        return getTipo() + ": " + getAlias() + " (" + getCvu() + ") | Saldo: $" + getSaldo();
    }
}