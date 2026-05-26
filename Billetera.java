package ;

import java.util.HashMap;
import java.util.ArrayList;

public class Billetera implements IBilletera {
    private HashMap<String, Usuario> usuario;
    private HashMap<String, Empresa> empresas;
    private HashMap<String, Cuenta> cuentas;
    private HashMap<String, Cuenta> cuentasPorAlias;
    private HashMap<Integer, Inversion> inversiones;
    private ArrayList<Actividad> actividades;
    private int contadorInversiones;
    

    public Billetera() {
        this.usuario = new HashMap<>();
        this.empresas = new HashMap<>();
        this.cuentas = new HashMap<>();
        this.cuentasPorAlias = new HashMap<>();
        this.inversiones = new HashMap<>();
        this.actividades = new ArrayList<>();
        this.contadorInversiones = 1;
    }

    @Override
    void registrarUsuario(String dni, String nombre, String telefono, String email){
        if (usuario.containsKey(dni)){
            throw new RuntimeException("El usuario ya existe");
        }
        usuario.put(dni, new Usuario(dni, nombre, telefono, email));
    }
    
    @Override
    void registrarEmpresa(String cuit, String nombre, String telefono, String email){
        if (empresas.containsKey(cuit)){
            throw new RuntimeException("La empresa ya existe");
        }
        empresas.put(cuit, new Empresa(cuit, nombre, telefono, email));
    }
    
    @Override
    void agregarPersonaAutorizada(String cuitEmpresa, String dniAutorizado){
        if (!empresas.containsKey(cuitEmpresa)){
            throw new RuntimeException("La empresa no existe");
        }
        Empresa empresa = empresas.get(cuitEmpresa);
        if (empresa.getPersonasAutorizadas().contains(dniAutorizado)){
            throw new RuntimeException("La persona ya esta autorizada");
        }
        empresa.agregarPersonaAutorizada(dniAutorizado);
    }
    @Override
    public String crearCuentaRegular(String dniUsuario, String alias) {
        if (!usuario.containsKey(dniUsuario)) {
            throw new RuntimeException("El usuario no existe");
        }
        if (cuentasPorAlias.containsKey(alias)) {
            throw new RuntimeException("El alias ya esta registrado");
        }
        String cvu = utilitarios.generarCvu(cuentas.size() + 1);
        Cuenta cuenta = new CuentaRegular(cvu, alias, dniUsuario);
        cuentas.put(cvu, cuenta);
        cuentasPorAlias.put(alias, cuenta);
        usuario.get(dniUsuario).agregarCuenta(cuenta);
        return cvu;


       
    }
    // string crearCuentaPremium()
    // string crearCuentaCorporativa()

    @Override
    public String consultarCvu(String alias) {
        if (!cuentasPorAlias.containsKey(alias)) {
            throw new RuntimeException("El alias no existe");
        }
        return cuentasPorAlias.get(alias).getCvu();
    }
    
    @Override
    public ArrayList<Cuenta> obtenerCuentas(String dn)
    // ArrayList<Cuenta> obtenerCuentas()
    // double obtenerSaldoDisponible()
    
    // realizarTransferencia()
    // realizarInversionRentaFija()
    // realizarInversionDivisa()
    // realizarInversionLiquidez()
    // precancelarInversion()
    // dpuble obtenerTotalInvertido()
    
    // consultarHistorialGlobal()
    // consultarHistorialCuenta()
    // consultarHistorialUsuario()
    // ArrayList<Cuenta> cuentasConMayorVolumen()
    
    
    // toString()
}