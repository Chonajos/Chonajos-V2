package com.web.chon.dominio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Juan de la Cruz
 */
public class RelacionOperaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    private BigDecimal idVentaPk;
    private Date fechaVenta;
    private Date fechaPromesaPago;
    private BigDecimal idClienteFk;
    private BigDecimal idVendedorFk;
    private String nombreCliente;
    private String nombreVendedor;
    private BigDecimal totalVenta;
    private String estatus;


    public RelacionOperaciones() {
        
    }

    public BigDecimal getIdVentaPk() {
        return idVentaPk;
    }

    public void setIdVentaPk(BigDecimal idVentaPk) {
        this.idVentaPk = idVentaPk;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Date getFechaPromesaPago() {
        return fechaPromesaPago;
    }

    public void setFechaPromesaPago(Date fechaPromesaPago) {
        this.fechaPromesaPago = fechaPromesaPago;
    }

    public BigDecimal getIdClienteFk() {
        return idClienteFk;
    }

    public void setIdClienteFk(BigDecimal idClienteFk) {
        this.idClienteFk = idClienteFk;
    }

    public BigDecimal getIdVendedorFk() {
        return idVendedorFk;
    }

    public void setIdVendedorFk(BigDecimal idVendedorFk) {
        this.idVendedorFk = idVendedorFk;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }

    public BigDecimal getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(BigDecimal totalVenta) {
        this.totalVenta = totalVenta;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVentaPk != null ? idVentaPk.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "RelacionOperaciones{" + "idVentaPk=" + idVentaPk + ", fechaVenta=" + fechaVenta + ", fechaPromesaPago=" + fechaPromesaPago + ", idClienteFk=" + idClienteFk + ", idVendedorFk=" + idVendedorFk + ", nombreCliente=" + nombreCliente + ", nombreVendedor=" + nombreVendedor + ", totalVenta=" + totalVenta + '}';
    }
    
    
}