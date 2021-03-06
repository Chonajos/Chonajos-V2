/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.service;

import com.web.chon.dominio.CarroDetalle;
import com.web.chon.dominio.OperacionesVentasMayoreo;
import com.web.chon.dominio.VentaMayoreo;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author freddy
 */
public interface IfaceVentaMayoreo {

    public int insertarVenta(VentaMayoreo venta);

    public int getNextVal();

    public ArrayList<VentaMayoreo> getVentasByIntervalDate(Date fechaInicio, Date fechaFin, BigDecimal idSucursal, BigDecimal idStatusVenta, BigDecimal idTipoVenta,String idSubproductoFk,BigDecimal idCliente);
    
    public VentaMayoreo getVentaMayoreoByFolioidSucursalFk(BigDecimal idFolio,BigDecimal idSucursal);

    public int getVentaSucursal(BigDecimal idSucursal);

    /**
     * Modifica el estatus de la venta por medio del folio de la sucursal y el id de sucursal
     * @param idSucursal
     * @return 
     */
    public int updateEstatusVentaByFolioSucursalAndIdSucursal(BigDecimal folioSucursal, BigDecimal idSucursal, BigDecimal estatusVenta);

    public int cancelarVentaMayoreo(BigDecimal idVenta,BigDecimal idUsuario,String comentarios);
    
    /**
     * No se ocupa por el momento
     * @param carro
     * @param idSucursal
     * @param idTipoVenta
     * @return 
     */
    public ArrayList<OperacionesVentasMayoreo> getReporteVentasByCarroAndIdSucursalAndTipoVenta(BigDecimal carro, BigDecimal idSucursal,BigDecimal idTipoVenta);
    
    /**
     * Obtiene un detalle de las ventas realizadas por carro y id de sucursal
     * @param idSucursal
     * @param carro
     * @return 
     */
    public ArrayList<CarroDetalle> getDetalleVentasCarro(BigDecimal idSucursal, BigDecimal carro);
    

}
