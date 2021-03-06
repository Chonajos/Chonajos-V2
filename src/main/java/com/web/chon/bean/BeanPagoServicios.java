/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.bean;

import com.web.chon.dominio.Caja;
import com.web.chon.dominio.ConceptosES;
import com.web.chon.dominio.CorteCaja;
import com.web.chon.dominio.OperacionesCaja;
import com.web.chon.dominio.TipoOperacion;
import com.web.chon.dominio.UsuarioDominio;
import com.web.chon.security.service.PlataformaSecurityContext;
import com.web.chon.service.IfaceCaja;
import com.web.chon.service.IfaceConceptos;
import com.web.chon.service.IfaceCorteCaja;
import com.web.chon.service.IfaceOperacionesCaja;
import com.web.chon.util.JsfUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author JesusAlfredo
 */
@Component
@Scope("view")
public class BeanPagoServicios implements Serializable {

    @Autowired
    private IfaceConceptos ifaceConceptos;
    @Autowired
    private PlataformaSecurityContext context;
    @Autowired
    private IfaceCaja ifaceCaja;
    @Autowired
    private IfaceOperacionesCaja ifaceOperacionesCaja;

    private ArrayList<ConceptosES> listaConceptos;
    private String title;
    private String viewEstate;
    private BigDecimal idConceptoBean;

    private UsuarioDominio usuario;

    private Caja caja;
    private OperacionesCaja opcaja;

    private static final BigDecimal entradaSalida = new BigDecimal(2);
    private static final BigDecimal statusOperacion = new BigDecimal(1);

    private BigDecimal totalServicio;
    private String referencia;
    private String comentarios;

    //--Variables para Verificar Maximo en Caja --//
    private ArrayList<TipoOperacion> lstOperacionesEntrada;
    private ArrayList<TipoOperacion> lstOperacionesSalida;
    private ArrayList<Caja> listaSucursales;
    private CorteCaja corteAnterior;
    @Autowired
    private IfaceCorteCaja ifaceCorteCaja;
    private BigDecimal saldoAnterior;
    private BigDecimal totalEntradas;
    private BigDecimal totalSalidas;
    private static final BigDecimal entrada = new BigDecimal(1);
    private static final BigDecimal salida = new BigDecimal(2);
    private BigDecimal idSucursalFk;
    //--Variables para Verificar Maximo en Caja --//
    
     //==========codigo para verificar dinero en caja =========//
    private ArrayList<OperacionesCaja> listaDetalleEntradas;
    private ArrayList<OperacionesCaja> listaDetalleSalidas;
    private static final BigDecimal statusAplicado = new BigDecimal(1);
    private static final BigDecimal statusPendiente = new BigDecimal(2);
    private static final BigDecimal CERO = new BigDecimal(0).setScale(2, RoundingMode.CEILING);
    private BigDecimal nuevoSaldo;
    private BigDecimal nuevoSaldoCheques;
    private BigDecimal nuevoSaldoCuentas;
    private BigDecimal saldoAnteriorEfectivo;
    private BigDecimal saldoAnteriorCheques;
    private BigDecimal saldoAnteriorCuentas;
    private BigDecimal totalEfectivo;
    private BigDecimal totalCheques;
    private BigDecimal totalCuentasBancarias;
    //========codigo para verificar dinero en caja ============//

    @PostConstruct
    public void init() 
    {
        usuario = context.getUsuarioAutenticado();
        setTitle("Pago de Servicios");
        setViewEstate("init");
        
        listaConceptos = ifaceConceptos.getConceptosByTipoOperacion(new BigDecimal(1));
        caja = new Caja();
        caja = ifaceCaja.getCajaByIdUsuarioPk(usuario.getIdUsuario());
        opcaja = new OperacionesCaja();
        opcaja.setIdCajaFk(caja.getIdCajaPk());
        opcaja.setIdUserFk(usuario.getIdUsuario());
        opcaja.setEntradaSalida(entradaSalida);
        opcaja.setIdStatusFk(statusOperacion);
        
        //--Maximo Pago--//
        corteAnterior = new CorteCaja();
        corteAnterior = ifaceCorteCaja.getLastCorteByCaja(caja.getIdCajaPk());
        totalEntradas = CERO;
        totalSalidas = CERO;
        saldoAnterior = CERO;
       
        listaSucursales = new ArrayList<Caja>();
        System.out.println("=================idCaja: "+caja.getIdCajaPk());
        listaSucursales = ifaceCaja.getSucursalesByIdCaja(caja.getIdCajaPk());
         nuevoSaldo = CERO;
        nuevoSaldoCheques = CERO;
        nuevoSaldoCuentas = CERO;
        saldoAnteriorEfectivo = CERO;
        saldoAnteriorCheques = CERO;
        saldoAnteriorCuentas = CERO;
        totalEfectivo = CERO;
        totalCheques = CERO;
        totalCuentasBancarias = CERO;

        if (corteAnterior.getSaldoNuevo() == null) {
            corteAnterior.setSaldoNuevo(CERO);
        }
        saldoAnteriorEfectivo = corteAnterior.getSaldoNuevo();
        saldoAnteriorCheques = corteAnterior.getMontoChequesNuevos();
        saldoAnteriorCuentas = corteAnterior.getMontoCuentaNuevo();
        if (saldoAnteriorCheques == null) {
            saldoAnteriorCheques = CERO;
        }
        if (saldoAnteriorEfectivo == null) {
            saldoAnteriorEfectivo = CERO;
        }
        if (saldoAnteriorCuentas == null) {
            saldoAnteriorCuentas = CERO;
        }

        //---codigo para verificar dinero en caja --//
        System.out.println("Saldo Anterior: " + saldoAnteriorEfectivo.setScale(2, RoundingMode.CEILING));
        System.out.println("Saldo Anterior Cheques: " + saldoAnteriorCheques.setScale(2, RoundingMode.CEILING));
        System.out.println("Saldo Anterior Cuentas: " + saldoAnteriorCuentas.setScale(2, RoundingMode.CEILING));


        
    }

    public void reset() {
        totalServicio = null;
        referencia = null;
        comentarios = null;
        idConceptoBean = null;
    }

    //----Funciones para Verificar Maximo de Dinero en Caja --//
    public void verificarDinero() {
        totalEfectivo = CERO;
        totalCheques = CERO;
        totalCuentasBancarias = CERO;
        nuevoSaldo=CERO;
        nuevoSaldoCheques=CERO;
        nuevoSaldoCuentas=CERO;
        
        listaDetalleEntradas= new ArrayList<OperacionesCaja>();
        listaDetalleEntradas =new ArrayList<OperacionesCaja>();
        listaDetalleSalidas=new ArrayList<OperacionesCaja>();
        
        listaDetalleEntradas = ifaceOperacionesCaja.getDetalles(caja.getIdCajaPk(), caja.getIdUsuarioFK(), entrada, statusAplicado);
        listaDetalleSalidas = ifaceOperacionesCaja.getDetalles(caja.getIdCajaPk(), caja.getIdUsuarioFK(), salida, statusAplicado);

        for (OperacionesCaja ope : listaDetalleEntradas) {
            if (ope.getIdConceptoFk().intValue() == 10 || ope.getIdConceptoFk().intValue() == 6 || ope.getIdConceptoFk().intValue() == 11 || ope.getIdConceptoFk().intValue() == 7 || ope.getIdConceptoFk().intValue() == 8 || ope.getIdConceptoFk().intValue() == 9 || ope.getIdConceptoFk().intValue() == 16 || ope.getIdConceptoFk().intValue() == 13) {

                totalEfectivo = totalEfectivo.add(ope.getMonto(), MathContext.UNLIMITED);
            } else if (ope.getIdConceptoFk().intValue() == 30 || ope.getIdConceptoFk().intValue() == 33 || ope.getIdConceptoFk().intValue() == 27 || ope.getIdConceptoFk().intValue() == 17 || ope.getIdConceptoFk().intValue() == 36) {

                totalCheques = totalCheques.add(ope.getMonto(), MathContext.UNLIMITED);
            } else if (ope.getIdConceptoFk().intValue() == 12 || ope.getIdConceptoFk().intValue() == 29 || ope.getIdConceptoFk().intValue() == 31 || ope.getIdConceptoFk().intValue() == 28 || ope.getIdConceptoFk().intValue() == 32 || ope.getIdConceptoFk().intValue() == 34 || ope.getIdConceptoFk().intValue() == 37 || ope.getIdConceptoFk().intValue() == 35) {
                totalCuentasBancarias = totalCuentasBancarias.add(ope.getMonto(), MathContext.UNLIMITED);
            }

        }
        for (OperacionesCaja ope : listaDetalleSalidas) {
            if (ope.getIdConceptoFk().intValue() == 7 || ope.getIdConceptoFk().intValue() == 1 || ope.getIdConceptoFk().intValue() == 3 || ope.getIdConceptoFk().intValue() == 4 || ope.getIdConceptoFk().intValue() == 6 || ope.getIdConceptoFk().intValue() == 11 || ope.getIdConceptoFk().intValue() == 10 || ope.getIdConceptoFk().intValue() == 8 || ope.getIdConceptoFk().intValue() == 9 || ope.getIdConceptoFk().intValue() == 16 || ope.getIdConceptoFk().intValue() == 13 || ope.getIdConceptoFk().intValue() == 2 || ope.getIdConceptoFk().intValue() == 25 || ope.getIdConceptoFk().intValue() == 24
                    || ope.getIdConceptoFk().intValue() == 21 || ope.getIdConceptoFk().intValue() == 20 || ope.getIdConceptoFk().intValue() == 23
                    || ope.getIdConceptoFk().intValue() == 22) {
                totalEfectivo = totalEfectivo.subtract(ope.getMonto(), MathContext.UNLIMITED);
            } else if (ope.getIdConceptoFk().intValue() == 30 || ope.getIdConceptoFk().intValue() == 33 || ope.getIdConceptoFk().intValue() == 27 || ope.getIdConceptoFk().intValue() == 17 || ope.getIdConceptoFk().intValue() == 36) {

                totalCheques = totalCheques.subtract(ope.getMonto(), MathContext.UNLIMITED);
            } else if (ope.getIdConceptoFk().intValue() == 12 || ope.getIdConceptoFk().intValue() == 29 || ope.getIdConceptoFk().intValue() == 31 || ope.getIdConceptoFk().intValue() == 28 || ope.getIdConceptoFk().intValue() == 32 || ope.getIdConceptoFk().intValue() == 34 || ope.getIdConceptoFk().intValue() == 37 || ope.getIdConceptoFk().intValue() == 35) {

                totalCuentasBancarias = totalCuentasBancarias.subtract(ope.getMonto(), MathContext.UNLIMITED);
            }

        }
        nuevoSaldo = totalEfectivo.add(saldoAnteriorEfectivo.setScale(2, RoundingMode.CEILING), MathContext.UNLIMITED);
        nuevoSaldoCheques = totalCheques.add(saldoAnteriorCheques.setScale(2, RoundingMode.CEILING), MathContext.UNLIMITED);
        nuevoSaldoCuentas = totalCuentasBancarias.add(saldoAnteriorCuentas.setScale(2, RoundingMode.CEILING), MathContext.UNLIMITED);
    }

  

    //----Funciones para Verificar Maximo de Dinero en Caja --//
    public void pagar() {
        verificarDinero();
        System.out.println("Saldo Actual: " + nuevoSaldo.setScale(2, RoundingMode.CEILING));
        System.out.println("Saldo Actual Cheques: " + nuevoSaldoCheques.setScale(2, RoundingMode.CEILING));
        System.out.println("Saldo Actual Cuentas: " + nuevoSaldoCuentas.setScale(2, RoundingMode.CEILING));
        
        if (nuevoSaldo.setScale(2, RoundingMode.CEILING).compareTo(totalServicio.setScale(2, RoundingMode.CEILING)) >= 0) 
        {
            opcaja.setIdOperacionesCajaPk(new BigDecimal(ifaceOperacionesCaja.getNextVal()));
            opcaja.setMonto(totalServicio);
            opcaja.setComentarios(comentarios);
            opcaja.setIdConceptoFk(idConceptoBean);
            opcaja.setIdSucursalFk(idSucursalFk);
            if (caja.getIdCajaPk() != null) 
            {
                if (ifaceOperacionesCaja.insertaOperacion(opcaja) == 1) {
                    JsfUtil.addSuccessMessageClean("Pago de servicio registrado correctamente");
                    reset();
                } else {
                    JsfUtil.addErrorMessageClean("Ocurrio un error al registrar el pago de servicio, contactar al administrador");
                }

            } else {
                JsfUtil.addErrorMessageClean("Su usuario no cuenta con caja registrada para realizar el pago de servicios");
            }
        } else {
            JsfUtil.addErrorMessageClean("No hay suficiente dinero en caja");
        }
       
    }

    public ArrayList<ConceptosES> getListaConceptos() {
        return listaConceptos;
    }

    public void setListaConceptos(ArrayList<ConceptosES> listaConceptos) {
        this.listaConceptos = listaConceptos;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getViewEstate() {
        return viewEstate;
    }

    public void setViewEstate(String viewEstate) {
        this.viewEstate = viewEstate;
    }

    public UsuarioDominio getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDominio usuario) {
        this.usuario = usuario;
    }

    public BigDecimal getIdConceptoBean() {
        return idConceptoBean;
    }

    public void setIdConceptoBean(BigDecimal idConceptoBean) {
        this.idConceptoBean = idConceptoBean;
    }

    public Caja getCaja() {
        return caja;
    }

    public void setCaja(Caja caja) {
        this.caja = caja;
    }

    public OperacionesCaja getOpcaja() {
        return opcaja;
    }

    public void setOpcaja(OperacionesCaja opcaja) {
        this.opcaja = opcaja;
    }

    public BigDecimal getTotalServicio() {
        return totalServicio;
    }

    public void setTotalServicio(BigDecimal totalServicio) {
        this.totalServicio = totalServicio;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public BigDecimal getSaldoAnterior() {
        return saldoAnterior;
    }

    public void setSaldoAnterior(BigDecimal saldoAnterior) {
        this.saldoAnterior = saldoAnterior;
    }

    public BigDecimal getNuevoSaldo() {
        return nuevoSaldo;
    }

    public void setNuevoSaldo(BigDecimal nuevoSaldo) {
        this.nuevoSaldo = nuevoSaldo;
    }

    public BigDecimal getTotalEntradas() {
        return totalEntradas;
    }

    public void setTotalEntradas(BigDecimal totalEntradas) {
        this.totalEntradas = totalEntradas;
    }

    public BigDecimal getTotalSalidas() {
        return totalSalidas;
    }

    public void setTotalSalidas(BigDecimal totalSalidas) {
        this.totalSalidas = totalSalidas;
    }

    public BigDecimal getIdSucursalFk() {
        return idSucursalFk;
    }

    public void setIdSucursalFk(BigDecimal idSucursalFk) {
        this.idSucursalFk = idSucursalFk;
    }

    public ArrayList<TipoOperacion> getLstOperacionesEntrada() {
        return lstOperacionesEntrada;
    }

    public void setLstOperacionesEntrada(ArrayList<TipoOperacion> lstOperacionesEntrada) {
        this.lstOperacionesEntrada = lstOperacionesEntrada;
    }

    public ArrayList<TipoOperacion> getLstOperacionesSalida() {
        return lstOperacionesSalida;
    }

    public void setLstOperacionesSalida(ArrayList<TipoOperacion> lstOperacionesSalida) {
        this.lstOperacionesSalida = lstOperacionesSalida;
    }

    public ArrayList<Caja> getListaSucursales() {
        return listaSucursales;
    }

    public void setListaSucursales(ArrayList<Caja> listaSucursales) {
        this.listaSucursales = listaSucursales;
    }

    public CorteCaja getCorteAnterior() {
        return corteAnterior;
    }

    public void setCorteAnterior(CorteCaja corteAnterior) {
        this.corteAnterior = corteAnterior;
    }

    public ArrayList<OperacionesCaja> getListaDetalleEntradas() {
        return listaDetalleEntradas;
    }

    public void setListaDetalleEntradas(ArrayList<OperacionesCaja> listaDetalleEntradas) {
        this.listaDetalleEntradas = listaDetalleEntradas;
    }

    public ArrayList<OperacionesCaja> getListaDetalleSalidas() {
        return listaDetalleSalidas;
    }

    public void setListaDetalleSalidas(ArrayList<OperacionesCaja> listaDetalleSalidas) {
        this.listaDetalleSalidas = listaDetalleSalidas;
    }

    public BigDecimal getNuevoSaldoCheques() {
        return nuevoSaldoCheques;
    }

    public void setNuevoSaldoCheques(BigDecimal nuevoSaldoCheques) {
        this.nuevoSaldoCheques = nuevoSaldoCheques;
    }

    public BigDecimal getNuevoSaldoCuentas() {
        return nuevoSaldoCuentas;
    }

    public void setNuevoSaldoCuentas(BigDecimal nuevoSaldoCuentas) {
        this.nuevoSaldoCuentas = nuevoSaldoCuentas;
    }

    public BigDecimal getSaldoAnteriorEfectivo() {
        return saldoAnteriorEfectivo;
    }

    public void setSaldoAnteriorEfectivo(BigDecimal saldoAnteriorEfectivo) {
        this.saldoAnteriorEfectivo = saldoAnteriorEfectivo;
    }

    public BigDecimal getSaldoAnteriorCheques() {
        return saldoAnteriorCheques;
    }

    public void setSaldoAnteriorCheques(BigDecimal saldoAnteriorCheques) {
        this.saldoAnteriorCheques = saldoAnteriorCheques;
    }

    public BigDecimal getSaldoAnteriorCuentas() {
        return saldoAnteriorCuentas;
    }

    public void setSaldoAnteriorCuentas(BigDecimal saldoAnteriorCuentas) {
        this.saldoAnteriorCuentas = saldoAnteriorCuentas;
    }

    public BigDecimal getTotalEfectivo() {
        return totalEfectivo;
    }

    public void setTotalEfectivo(BigDecimal totalEfectivo) {
        this.totalEfectivo = totalEfectivo;
    }

    public BigDecimal getTotalCheques() {
        return totalCheques;
    }

    public void setTotalCheques(BigDecimal totalCheques) {
        this.totalCheques = totalCheques;
    }

    public BigDecimal getTotalCuentasBancarias() {
        return totalCuentasBancarias;
    }

    public void setTotalCuentasBancarias(BigDecimal totalCuentasBancarias) {
        this.totalCuentasBancarias = totalCuentasBancarias;
    }

    
    
    
}
