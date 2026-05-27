package ar.edu.ungs.billetera;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;
import frantaieltpintegrador.*;

public class Billetera implements IBilletera {
    private HashMap<String, Usuario> usuario;
    private HashMap<String, Empresa> empresas;
    private HashMap<String, Cuenta> cuentas;
    private HashMap<String, Cuenta> cuentasPorAlias;
    private HashMap<Integer, Inversion> inversiones;
    private ArrayList<Actividad> actividadesglobales;
    private int contadorInversiones;
    

    public Billetera() {
        this.usuario = new HashMap<>();
        this.empresas = new HashMap<>();
        this.cuentas = new HashMap<>();
        this.cuentasPorAlias = new HashMap<>();
        this.inversiones = new HashMap<>();
        this.actividadesglobales = new ArrayList<>();
        this.contadorInversiones = 1;
    }

    @Override
    public void registrarUsuario(String dni, String nombre, String telefono, String email){
        if (usuario.containsKey(dni)){
            throw new RuntimeException("El usuario ya existe");
        }
        usuario.put(dni, new Usuario(dni, nombre, telefono, email));
    }
    
    @Override
    public void registrarEmpresa(String cuit, String nombre, String telefono, String email, String nombreContacto){
        if (empresas.containsKey(cuit)){
            throw new RuntimeException("La empresa ya existe");
        }
        empresas.put(cuit, new Empresa(cuit, nombre, telefono, email, nombreContacto));
    }
    
    @Override
    public void agregarPersonaAutorizada(String cuit, String dniAutorizado){
        if (!empresas.containsKey(cuit)){
            throw new RuntimeException("La empresa no existe");
        }
        Empresa empresa = empresas.get(cuit);
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
        String cvu = Utilitarios.generarSiguienteCvu();
        Usuario titular = usuario.get(dniUsuario);
        Cuenta cuenta = new CuentaRegular(cvu, alias, titular);
        cuentas.put(cvu, cuenta);
        cuentasPorAlias.put(alias, cuenta);
        titular.agregarCuenta(cuenta);
        return cvu;
       
    }
    @Override
    public String crearCuentaPremium(String dniUsuario, String alias, double saldoInicial) {
        if (!usuario.containsKey(dniUsuario)) {
            throw new RuntimeException("El usuario no existe");
        }
        if (cuentasPorAlias.containsKey(alias)) {
            throw new RuntimeException("El alias ya esta registrado");
        }

        if(saldoInicial < 500000){
            throw new RuntimeException("El saldo disponible inicial minimo debe ser de $500.000");
        }
        String cvu = Utilitarios.generarSiguienteCvu();
        Usuario titular = usuario.get(dniUsuario);
        Cuenta cuenta = new CuentaPremium(cvu, alias, titular, saldoInicial);

        cuentas.put(cvu, cuenta);
        cuentasPorAlias.put(alias, cuenta);
        titular.agregarCuenta(cuenta);

        return cvu;
    }

    @Override
    public String crearCuentaCorporativa(String dniUsuario, String alias, String cuitEmpresa) {
        if (!empresas.containsKey(cuitEmpresa))
            throw new IllegalArgumentException("La empresa no existe.");
        if (!usuario.containsKey(dniUsuario))
            throw new IllegalArgumentException("El usuario no existe.");
        if (cuentasPorAlias.containsKey(alias))
            throw new IllegalArgumentException("El alias ya esta registrado.");

        Empresa empresa = empresas.get(cuitEmpresa);
        if (!empresa.estaAutorizado(dniUsuario))
            throw new IllegalArgumentException("El usuario no esta autorizado.");

        String cvu = Utilitarios.generarSiguienteCvu();
        Usuario titular = usuario.get(dniUsuario);
        Cuenta cuenta = new CuentaCorporativa(cvu, alias, titular, empresa);
        cuentas.put(cvu, cuenta);
        cuentasPorAlias.put(alias, cuenta);
        titular.agregarCuenta(cuenta);
        return cvu;
    }

    @Override
    public String consultarCvu(String alias) {
        if (!cuentasPorAlias.containsKey(alias)) {
            throw new RuntimeException("El alias no existe");
        }
        return cuentasPorAlias.get(alias).getCvu();
    }
    
    private Cuenta obtenerCuentaExistente(String cvu) {
        Cuenta cuenta = cuentas.get(cvu);
        if (cuenta == null)
            throw new IllegalArgumentException("No existe cuenta con CVU: " + cvu);
        return cuenta;
    }

    private Usuario obtenerUsuarioExistente(String dniUsuario) {
        Usuario usuarioExistente = usuario.get(dniUsuario);
        if (usuarioExistente == null) {
            throw new IllegalArgumentException("No existe usuario con DNI: " + dniUsuario);
        }
        return usuarioExistente;
    }

    @Override
    public ArrayList<String> obtenerCuentas(String dniUsuario) {
        Usuario usuarioExistente = obtenerUsuarioExistente(dniUsuario);
        ArrayList<String> listaCuentas = new ArrayList<>();
        for (Cuenta cuenta : usuarioExistente.getCuentas()) {

            listaCuentas.add(cuenta.getTipo() + ": " + cuenta.getAlias() + " (" + cuenta.getCvu() + ")");
        }
        return listaCuentas;
    }

    @Override
    public double obtenerSaldoDisponible(String cvu) {
        Cuenta cuenta = obtenerCuentaExistente(cvu);
        return cuenta.getSaldo();
    }
    
    @Override
    public void realizarTransferencia(String cvuOrigen, String cvuDestino, double monto) {
        if (!cuentas.containsKey(cvuOrigen) || !cuentas.containsKey(cvuDestino)) {
            throw new RuntimeException("Una o ambas cuentas no existen");
        }
        Cuenta cuentaOrigen = obtenerCuentaExistente(cvuOrigen);
        Cuenta cuentaDestino = obtenerCuentaExistente(cvuDestino);

        cuentaOrigen.debitar(monto);
        cuentaDestino.acreditar(monto);

        Transferencia transferencia = new Transferencia(cuentaOrigen, cuentaDestino, monto, true);
        actividadesglobales.add(transferencia);
        cuentaOrigen.registrarActividad(transferencia);
        cuentaDestino.registrarActividad(transferencia);
    }
    @Override
    public int realizarInversionRentaFija(String dni, String cvu, double monto, int plazo) {
        Usuario usuarioExistente = obtenerUsuarioExistente(dni);
        Cuenta cuenta = obtenerCuentaExistente(cvu);
        if (!cuenta.puedeOperar(monto)){
            throw new RuntimeException("Saldo insuficiente");
        }
        cuenta.debitar(monto); //debitar el monto de la cuenta para luego invertirlo
        InversionRentaFija inversion = new InversionRentaFija(cvu, monto, plazo);
        int id = generarIdInversion();
        inversion.setId(id); //cargamos el id de la inversion en el objeto para luego poder identificarla
        inversiones.put(id, inversion); //guardamos la inversion en el mapa de inversiones para poder acceder a ella luego
        actividadesglobales.add(inversion); //registramos la actividad de la inversion en el historial global
        cuenta.registrarActividad(inversion); //registramos la actividad de la inversion en el historial de la cuenta
        usuarioExistente.sumarInvertido(monto);   //registramos el monto invertido en la cuenta para luego poder consultarlo
        
        return id;
    }

    @Override
    public int realizarInversionDivisa(String dni, String cvu, double monto, int plazoDias, String divisa, double tasaDeInteres) {
        cuentas cuenta = obtenerCuentaExistente(cvu);        
        if (!cuenta.puedeOperar(monto)) {
            throw new RuntimeException("Saldo insuficiente");
        }
        if (!usuario.containsKey(dni)) {
            throw new RuntimeException("El usuario no existe");
        }
        if (!cuenta.containskey(cvu)) {
            throw new RuntimeException("La cuenta no existe");
        }
        cuenta.debitar(monto);  //debitar el monto de la cuenta para luego invertirlo
        InversionDivisa inversion = new InversionDivisa(cuenta,monto, plazoDias, divisa, tasaDeInteres);
        
        int id= generarIdInversion();
        inversion.setId(id);    //cargamos el id de la inversion en el objeto para luego poder identificarla
        inversiones.put(id, inversion); //guardamos la inversion en el mapa de inversiones para poder acceder a ella luego
        actividadesglobales.add(inversion); //registramos la actividad de la inversion en el historial global
        cuenta.registrarActividad(inversion);   //registramos la actividad de la inversion en el historial de la cuenta
        usuario.sumarInvertido(monto);     //registramos el monto invertido en la cuenta para luego poder consultarlo
        
        
        return id;
    }

    @Override
    public int realizarInversionLiquidez(String dni, String cvu, double monto) {
        if (!usuario.containsKey(dni)) {
            throw new RuntimeException("El usuario no existe");
        }
        if (!cuentas.containsKey(cvu)) {
            throw new RuntimeException("La cuenta no existe");
        }
        Cuenta cuenta = obtenerCuentaExistente(cvu);
        if (!cuenta.puedeOperar(monto)) {
            throw new RuntimeException("Saldo insuficiente");
        }
        cuenta.debitar(monto);  //debitar el monto de la cuenta para luego invertirlo
        InversionLiquidez inversion = new InversionLiquidez(cvu, monto);
            
        int id= generarIdInversion();
        inversion.setId(id);    //cargamos el id de la inversion en el objeto para luego poder identificarla
        inversiones.put(id, inversion); //guardamos la inversion en el mapa de inversiones para poder acceder a ella luego
        actividadesglobales.add(inversion); //registramos la actividad de la inversion en el historial global
        cuenta.registrarActividad(inversion);   //registramos la actividad de la inversion en el historial de la cuenta
        usuario.get(dni).sumarInvertido(monto);     //registramos el monto invertido en la cuenta para luego poder consultarlo
            
        return id;
    }
    
    @Override
    public void precancelarInversion(int idInversion) {
        if (!inversiones.containsKey(idInversion)) {
            throw new RuntimeException("La inversion no existe");
        }
        Inversion inversion = inversiones.get(idInversion);
        if (!inversion.estaActiva()) {
            throw new RuntimeException("La inversion ya fue precancelada o finalizada");
        }
        Cuenta cuenta = obtenerCuentaExistente(inversion.getCvu());
        inversion.precancelar();
        cuenta.acreditar(inversion.getMonto()); //acreditar el monto de la inversion a la cuenta
        actividadesglobales.add(new Actividad("Precancelacion", cuenta, inversion.getMonto())); //registramos la actividad de la precancelacion en el historial global
        cuenta.registrarActividad(new Actividad("Precancelacion", cuenta, inversion.getMonto())); //registramos la actividad de la precancelacion en el historial de la cuenta
    }

    @Override
    public double obtenerTotalInvertido(String dniUsuario) {
        if (!usuario.containsKey(dniUsuario))
            throw new IllegalArgumentException("El usuario no existe.");
        return usuario.get(dniUsuario).getTotalInvertido();
    }
    
    @Override
    public ArrayList<String> consultarHistorialGlobal(String cvu) {
        if (!cuentas.containsKey(cvu)) {
            throw new IllegalArgumentException("La cuenta no existe.");
        }
        ArrayList<String> historiaGlobal = new ArrayList<>();
        for (Actividad actividad : actividadesglobales) {
            historiaGlobal.add(actividad.toString());
        }
        return historiaGlobal;
    }
    
    @Override
    public ArrayList<String> consultarHistorialCuenta(String cvu) {
        Cuenta cuenta = obtenerCuentaExistente(cvu);
        ArrayList<String> historialCuenta = new ArrayList<>();
        for (Actividad actividad : cuenta.getActividades()) {
            historialCuenta.add(actividad.toString());
        }
        return historialCuenta;
    }
    
     @Override
    public ArrayList<String> consultarHistorialUsuario(String dniUsuario) {
        Usuario usuario = obtenerUsuarioExistente(dniUsuario);
        ArrayList<String> historialUsuario = new ArrayList<>();
        HashSet<String> visto = new HashSet<>();

        for (Cuenta cuenta : usuario.getCuentas()) {
            for (Actividad actividad : cuenta.getActividades()) {
                String actStr = actividad.toString();
                if (visto.add(actStr)) {
                    historialUsuario.add(actStr);
                }
            }
        }
        return historialUsuario;
    }
    
    @Override
    //public cuentasConMayorVolumen() {
    public ArrayList<String> cuentasConMayorVolumen() {
        ArrayList<String> resultado = new ArrayList<>();
        double maxVolumen = 0;
        for (Cuenta cuenta : cuentas.values()) {
            double volumen = cuenta.getMontoInvertido() + cuenta.getSaldo();
            if (volumen > maxVolumen) {
                maxVolumen = volumen;
                resultado.clear();
                resultado.add(cuenta.getAlias() + " (" + cuenta.getCvu() + ") - Volumen: $" + volumen);
            } else if (volumen == maxVolumen) {
                resultado.add(cuenta.getAlias() + " (" + cuenta.getCvu() + ") - Volumen: $" + volumen);
            }
        }
        return resultado;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuarios:\n");
        for (Usuario u : usuario.values()) {
            sb.append(u.toString()).append("\n");
        }
        sb.append("Empresas:\n");
        for (Empresa empresa : empresas.values()) {
            sb.append(empresa.toString()).append("\n");
        }
        sb.append("Actividades:\n");
        for (Actividad actividad : actividadesglobales) {
            sb.append(actividad.toString()).append("\n");
        }
        return sb.toString();
    }
    
    private int generarIdInversion() {
        return contadorInversiones++;
    }
}