public class CuentaCorporativa extends Cuenta {

    private int cuit;
    private String usuarioAutorizado;

    public CuentaCorporativa(String cvu, String alias, String usuario, int cuit, String usuarioAutorizado) {
        super(cvu, alias, usuario);
        this.cuit = cuit;
        this.usuarioAutorizado = usuarioAutorizado;
    }
    @override
    public string getTipo() {
        return "Corporativa";
    }
    public boolean puedeOperar(double monto) {
        return getSaldoDisponible() >= monto;
    }
    