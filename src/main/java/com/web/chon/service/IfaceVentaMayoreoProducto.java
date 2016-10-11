/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.service;

import com.web.chon.dominio.VentaProductoMayoreo;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author marcogante
 */
public interface IfaceVentaMayoreoProducto {
    public int insertarVentaMayoreoProducto(VentaProductoMayoreo venta);
    public ArrayList<VentaProductoMayoreo> getProductosbyIdVmFk(BigDecimal idVmFk);
    public int getNextVal();
    public ArrayList<VentaProductoMayoreo> buscaVentaCancelar(BigDecimal idVenta,BigDecimal idSucursal);
    
    
    
}
