package com.web.chon.bean;

import com.web.chon.dominio.CarroDetalleGeneral;
import com.web.chon.dominio.EntradaMercancia;
import com.web.chon.dominio.OperacionesVentasMayoreo;
import com.web.chon.dominio.Provedor;
import com.web.chon.dominio.Sucursal;
import com.web.chon.dominio.UsuarioDominio;
import com.web.chon.security.service.PlataformaSecurityContext;
import com.web.chon.service.IfaceCatProvedores;
import com.web.chon.service.IfaceCatSucursales;
import com.web.chon.service.IfaceEntradaMercancia;
import com.web.chon.service.IfaceVentaMayoreo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Bean para el tablero de control de ventas
 *
 * @author Juan de la Cruz
 */
@Component
@Scope("view")
public class BeanTableroControlVentaMeyoreo implements Serializable, BeanSimple {

    private static final long serialVersionUID = 1L;

    @Autowired
    private PlataformaSecurityContext context;
    @Autowired
    private IfaceVentaMayoreo ifaceVentaMayoreo;
    @Autowired
    private IfaceCatSucursales ifaceCatSucursales;
    @Autowired
    private IfaceEntradaMercancia ifaceEntradaMercancia;
    @Autowired
    private IfaceCatProvedores ifaceCatProvedores;

    private ArrayList<Sucursal> lstSucursal = new ArrayList<Sucursal>();
    private ArrayList<EntradaMercancia> lstCarros = new ArrayList<EntradaMercancia>();
    private ArrayList<Provedor> lstProvedor = new ArrayList<Provedor>();
    private ArrayList<CarroDetalleGeneral> lstCarroDetalleGeneral = new ArrayList<CarroDetalleGeneral>();

    private BigDecimal idSucursal;
    private BigDecimal idProvedor;
    private BigDecimal carroSucursal;
    private BigDecimal totalVentaGeneral;
    private BigDecimal totalComisionGeneral;

    private UsuarioDominio usuario;
    private String title;
    private String viewEstate;

    @PostConstruct
    public void init() {

        usuario = context.getUsuarioAutenticado();
        lstSucursal = ifaceCatSucursales.getSucursales();
        idProvedor = null;
        idSucursal = new BigDecimal(usuario.getSucId());
        lstProvedor = ifaceCatProvedores.getProvedoresByIdSucursal(idSucursal);
        lstCarros = ifaceEntradaMercancia.getCarrosByIdSucursalAnsIdProvedor(idSucursal, null);

        setTitle("Reportes de Ventas.");
        setViewEstate("init");

    }

    public void getProvedores() {
        lstProvedor = ifaceCatProvedores.getProvedoresByIdSucursal(idSucursal);
    }

    public void getCarros() {
        lstCarros = ifaceEntradaMercancia.getCarrosByIdSucursalAnsIdProvedor(idSucursal, idProvedor);
    }

    @Override
    public void searchById() {
        viewEstate = "searchById";

    }

    public void buscar() {
        lstCarroDetalleGeneral = ifaceEntradaMercancia.getReporteGeneralCarro(idSucursal, idProvedor, carroSucursal);
        calcularTotalesGeneral();

    }

    //Calcula el total de la venta del detalle general asi como la comision
    private void calcularTotalesGeneral() {
        totalComisionGeneral = new BigDecimal(0);
        totalVentaGeneral = new BigDecimal(0);
        for (CarroDetalleGeneral dominio : lstCarroDetalleGeneral) {
            totalComisionGeneral = totalComisionGeneral.add(dominio.getComision());
            totalVentaGeneral = totalVentaGeneral.add(dominio.getVenta());

        }

    }

    @Override
    public String delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String insert() {

        return null;
    }

    @Override
    public String update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public IfaceVentaMayoreo getIfaceVentaMayoreo() {
        return ifaceVentaMayoreo;
    }

    public void setIfaceVentaMayoreo(IfaceVentaMayoreo ifaceVentaMayoreo) {
        this.ifaceVentaMayoreo = ifaceVentaMayoreo;
    }

    public IfaceCatSucursales getIfaceCatSucursales() {
        return ifaceCatSucursales;
    }

    public void setIfaceCatSucursales(IfaceCatSucursales ifaceCatSucursales) {
        this.ifaceCatSucursales = ifaceCatSucursales;
    }

    public IfaceEntradaMercancia getIfaceEntradaMercancia() {
        return ifaceEntradaMercancia;
    }

    public void setIfaceEntradaMercancia(IfaceEntradaMercancia ifaceEntradaMercancia) {
        this.ifaceEntradaMercancia = ifaceEntradaMercancia;
    }

    public ArrayList<Sucursal> getLstSucursal() {
        return lstSucursal;
    }

    public void setLstSucursal(ArrayList<Sucursal> lstSucursal) {
        this.lstSucursal = lstSucursal;
    }

    public ArrayList<EntradaMercancia> getLstCarros() {
        return lstCarros;
    }

    public void setLstCarros(ArrayList<EntradaMercancia> lstCarros) {
        this.lstCarros = lstCarros;
    }

    public ArrayList<Provedor> getLstProvedor() {
        return lstProvedor;
    }

    public void setLstProvedor(ArrayList<Provedor> lstProvedor) {
        this.lstProvedor = lstProvedor;
    }

    public BigDecimal getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(BigDecimal idSucursal) {
        this.idSucursal = idSucursal;
    }

    public BigDecimal getIdProvedor() {
        return idProvedor;
    }

    public void setIdProvedor(BigDecimal idProvedor) {
        this.idProvedor = idProvedor;
    }

    public BigDecimal getCarroSucursal() {
        return carroSucursal;
    }

    public void setCarroSucursal(BigDecimal carroSucursal) {
        this.carroSucursal = carroSucursal;
    }

    public UsuarioDominio getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDominio usuario) {
        this.usuario = usuario;
    }

    public ArrayList<CarroDetalleGeneral> getLstCarroDetalleGeneral() {
        return lstCarroDetalleGeneral;
    }

    public void setLstCarroDetalleGeneral(ArrayList<CarroDetalleGeneral> lstCarroDetalleGeneral) {
        this.lstCarroDetalleGeneral = lstCarroDetalleGeneral;
    }

    public BigDecimal getTotalVentaGeneral() {
        return totalVentaGeneral;
    }

    public void setTotalVentaGeneral(BigDecimal totalVentaGeneral) {
        this.totalVentaGeneral = totalVentaGeneral;
    }

    public BigDecimal getTotalComisionGeneral() {
        return totalComisionGeneral;
    }

    public void setTotalComisionGeneral(BigDecimal totalComisionGeneral) {
        this.totalComisionGeneral = totalComisionGeneral;
    }

}