package com.web.chon.dominio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Juan
 */
public class EntradaMercancia extends ValueObject implements Serializable {

    private static final long serialVersionUID = 1L;

    private BigDecimal idEntrada;
    private Date fecha;
    private BigDecimal cantidadToneladas;
    private BigDecimal precio;
    private String descripcionFiltro;
    
    public BigDecimal getIdEntrada() {
        return idEntrada;
    }

    public void setIdEntrada(BigDecimal idEntrada) {
        this.idEntrada = idEntrada;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getCantidadToneladas() {
        return cantidadToneladas;
    }

    public void setCantidadToneladas(BigDecimal cantidadToneladas) {
        this.cantidadToneladas = cantidadToneladas;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getDescripcionFiltro() {
        return descripcionFiltro;
    }

    public void setDescripcionFiltro(String descripcionFiltro) {
        this.descripcionFiltro = descripcionFiltro;
    }

    @Override
    public String toString() {
        return "EntradaMercancia{" + "idEntrada=" + idEntrada + ", fecha=" + fecha + ", cantidadToneladas=" + cantidadToneladas + ", precio=" + precio + ", descripcionFiltro=" + descripcionFiltro + '}';
    }
   

    @Override
    public void reset() {
        idEntrada = null;
        fecha = null;
        cantidadToneladas = null;
        precio = null;

    }

}
