package com.web.chon.service;

import com.web.chon.dominio.BuscaVenta;
import com.web.chon.dominio.VentaMayoreo;
import com.web.chon.dominio.VentaProductoMayoreo;
import com.web.chon.ejb.EjbBuscaVenta;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author freddy
 */
@Service
@Transactional
public class ServiceBuscaVenta implements IfaceBuscaVenta {

    @Autowired
    EjbBuscaVenta ejb;

    @Override
    public ArrayList<BuscaVenta> getVentaById(int idVenta) {
        try {
            ArrayList<BuscaVenta> lstVentas = new ArrayList<BuscaVenta>();
            List<Object[]> lstObject = ejb.getVentaById(idVenta);
            for (Object[] obj : lstObject) {

                BuscaVenta busca_venta = new BuscaVenta();
                busca_venta.setNombreCliente(obj[0] == null ? "" : obj[0].toString());
                busca_venta.setNombreVendedor(obj[1] == null ? "" : obj[1].toString());
                busca_venta.setIdVenta(obj[2] == null ? null : new BigDecimal(obj[2].toString()));
                busca_venta.setNombreSubproducto(obj[3] == null ? "" : obj[3].toString());
                busca_venta.setNombreEmpaque(obj[4] == null ? "" : obj[4].toString());
                busca_venta.setCantidadEmpaque(obj[5] == null ? null : new BigDecimal(obj[5].toString()));
                busca_venta.setPrecioProducto(obj[6] == null ? null : new BigDecimal(obj[6].toString()));
                busca_venta.setTotal(busca_venta.getCantidadEmpaque().multiply(busca_venta.getPrecioProducto(), MathContext.UNLIMITED));
                busca_venta.setTotalVenta(obj[7] == null ? null : new BigDecimal(obj[7].toString()));
                busca_venta.setFechaVenta((Date) obj[8]);
                busca_venta.setFechaPromesaPago((Date) obj[9]);
                busca_venta.setNombreStatus(obj[10] == null ? "" : obj[10].toString());
                busca_venta.setStatusFK(obj[11] == null ? 0 : Integer.parseInt(obj[11].toString())); //id status
                busca_venta.setIdSucursalFk(obj[12] == null ? null : new BigDecimal(obj[12].toString()));
                busca_venta.setNombreSucursal(obj[13] == null ? "" : obj[13].toString());
                busca_venta.setFolioSucursal(obj[14] == null ? null : new BigDecimal(obj[14].toString()));
                lstVentas.add(busca_venta);
            }

            return lstVentas;
        } catch (Exception ex) {
            Logger.getLogger(ServiceBuscaVenta.class.getName()).log(Level.SEVERE, null, ex);
            return null;

        }

    }

    @Override
    public int updateVenta(int idVenta, int idUsusario) {
        return ejb.updateVenta(idVenta, idUsusario);
    }

    @Override
    public ArrayList<BuscaVenta> getVentaMayoreoById(int idVenta, int idSucursal) {
        try {
            ArrayList<BuscaVenta> lstVentas = new ArrayList<BuscaVenta>();
            List<Object[]> lstObject = ejb.getVentaMayoreoById(idVenta, idSucursal);
            for (Object[] obj : lstObject) {

                BuscaVenta busca_venta = new BuscaVenta();
                busca_venta.setCarro(obj[0] == null ? null : new BigDecimal(obj[0].toString()));
                busca_venta.setClave(obj[1] == null ? "" : obj[1].toString());
                //busca_venta.setNombreCliente(obj[2] == null ? "" : obj[2].toString());
                //busca_venta.setNombreVendedor(obj[3] == null ? "" : obj[3].toString());
                //busca_venta.setIdVenta(obj[4] == null ? null : new BigDecimal(obj[4].toString()));
                busca_venta.setNombreSubproducto(obj[2] == null ? "" : obj[2].toString());
                busca_venta.setNombreEmpaque(obj[3] == null ? "" : obj[3].toString());
                busca_venta.setCantidadEmpaque(obj[4] == null ? null : new BigDecimal(obj[4].toString()));
                busca_venta.setKilosVendidos(obj[5] == null ? null : new BigDecimal(obj[5].toString()));
                busca_venta.setPrecioProducto(obj[6] == null ? null : new BigDecimal(obj[6].toString()));
                busca_venta.setTotal(busca_venta.getKilosVendidos().multiply(busca_venta.getPrecioProducto(), MathContext.UNLIMITED));
                busca_venta.setTotalVenta(obj[7] == null ? null : new BigDecimal(obj[7].toString()));
                //busca_venta.setFechaVenta((Date) obj[11]);
                //busca_venta.setFechaPromesaPago((Date) obj[12]);
                //busca_venta.setNombreStatus(obj[13] == null ? "" : obj[13].toString());
                //busca_venta.setStatusFK(obj[14] == null ? 0 : Integer.parseInt(obj[14].toString())); //id status
                //busca_venta.setIdSucursalFk(obj[15] == null ? null : new BigDecimal(obj[15].toString())); 
                //busca_venta.setFolioSucursal(obj[16] == null ? null : new BigDecimal(obj[16].toString()));
                //busca_venta.setIdSubProducto(obj[17] == null ? "" : obj[17].toString());
                //busca_venta.setIdTipoEmpaque(obj[18] == null ? null : new BigDecimal(obj[18].toString()));
                //busca_venta.setIdBodega(obj[19] == null ? null : new BigDecimal(obj[19].toString()));
                //busca_venta.setIdTipoConvenio(obj[20] == null ? null : new BigDecimal(obj[20].toString()));
                //busca_venta.setIdProvedor(obj[21] == null ? null : new BigDecimal(obj[21].toString()));

//                System.out.println("###################################");
//                System.out.println(busca_venta.toString());
                lstVentas.add(busca_venta);
            }
            return lstVentas;
        } catch (Exception ex) {
            Logger.getLogger(ServiceBuscaVenta.class.getName()).log(Level.SEVERE, null, ex);
            return null;

        }
    }

    //Ell siguiente metodo es para la vista de busca pedido
    @Override
    public ArrayList<BuscaVenta> getVentaMayoreoByIdBuscaVenta(int idVenta, int idSucursal) {
        try {
            ArrayList<BuscaVenta> lstVentas = new ArrayList<BuscaVenta>();
            List<Object[]> lstObject = ejb.getVentaMayoreoByIdBuscaVenta(idVenta, idSucursal);
            for (Object[] obj : lstObject) {

                BuscaVenta busca_venta = new BuscaVenta();
                busca_venta.setIdVenta(obj[0] == null ? null : new BigDecimal(obj[0].toString()));
                busca_venta.setFolioSucursal(obj[1] == null ? null : new BigDecimal(obj[1].toString()));
                busca_venta.setNombreVendedor(obj[2] == null ? "" : obj[2].toString());
                busca_venta.setNombreCliente(obj[3] == null ? "" : obj[3].toString());
                busca_venta.setNombreSubproducto(obj[4] == null ? "" : obj[4].toString());
                busca_venta.setNombreEmpaque(obj[5] == null ? "" : obj[5].toString());
                busca_venta.setPrecioProducto(obj[6] == null ? null : new BigDecimal(obj[6].toString()));
                busca_venta.setCantidadEmpaque(obj[7] == null ? null : new BigDecimal(obj[7].toString()));
                busca_venta.setKilosVendidos(obj[8] == null ? null : new BigDecimal(obj[8].toString()));

                //busca_venta.setTotal(busca_venta.getKilosVendidos().multiply(busca_venta.getPrecioProducto(), MathContext.UNLIMITED));
                busca_venta.setTotal(obj[9] == null ? null : new BigDecimal(obj[9].toString()));
                busca_venta.setNombreStatus(obj[10] == null ? "" : obj[10].toString());
                busca_venta.setIdStatus(obj[11] == null ? null : new BigDecimal(obj[11].toString()));

//                busca_venta.setCarro(obj[0] == null ? null : new BigDecimal(obj[0].toString()));
//                busca_venta.setClave(obj[1] == null ? "" : obj[1].toString());
//                //busca_venta.setNombreCliente(obj[2] == null ? "" : obj[2].toString());
//                //cbusca_venta.setNombreSubproducto(obj[2] == null ? "" : obj[2].toString());
//                busca_venta.setNombreEmpaque(obj[3] == null ? "" : obj[3].toString());
//                busca_venta.setCantidadEmpaque(obj[4] == null ? null : new BigDecimal(obj[4].toString()));
//                busca_venta.setKilosVendidos(obj[5] == null ? null : new BigDecimal(obj[5].toString()));
//                busca_venta.setPrecioProducto(obj[6] == null ? null : new BigDecimal(obj[6].toString()));
//                busca_venta.setTotal(busca_venta.getKilosVendidos().multiply(busca_venta.getPrecioProducto(), MathContext.UNLIMITED));
//                busca_venta.setTotalVenta(obj[7] == null ? null : new BigDecimal(obj[7].toString()));
//                //busca_venta.setFechaVenta((Date) obj[11]);
//                //busca_venta.setFechaPromesaPago((Date) obj[12]);
//                //busca_venta.setNombreStatus(obj[13] == null ? "" : obj[13].toString());
//                //busca_venta.setStatusFK(obj[14] == null ? 0 : Integer.parseInt(obj[14].toString())); //id status
//                //busca_venta.setIdSucursalFk(obj[15] == null ? null : new BigDecimal(obj[15].toString())); 
//                //busca_venta.setFolioSucursal(obj[16] == null ? null : new BigDecimal(obj[16].toString()));
//                //busca_venta.setIdSubProducto(obj[17] == null ? "" : obj[17].toString());
//                //busca_venta.setIdTipoEmpaque(obj[18] == null ? null : new BigDecimal(obj[18].toString()));
//                //busca_venta.setIdBodega(obj[19] == null ? null : new BigDecimal(obj[19].toString()));
//                //busca_venta.setIdTipoConvenio(obj[20] == null ? null : new BigDecimal(obj[20].toString()));
//                //busca_venta.setIdProvedor(obj[21] == null ? null : new BigDecimal(obj[21].toString()));
                lstVentas.add(busca_venta);
            }
            return lstVentas;
        } catch (Exception ex) {
            Logger.getLogger(ServiceBuscaVenta.class.getName()).log(Level.SEVERE, null, ex);
            return null;

        }
    }

    @Override
    public int updateStatusVentaMayoreo(int idVenta, int idUsuario) {

        return ejb.updateStatusVentaMayoreo(idVenta, idUsuario);
    }

    @Override
    public int cancelarVenta(int idVenta, int idUsuario, String comentarios) {

        return ejb.cancelarVenta(idVenta, idUsuario, comentarios);
    }

    @Override
    public int cancelarVentaMayoreo(int idVenta, int idUsuario, String comentarios) {

        return ejb.cancelarVentaMayoreo(idVenta, idUsuario, comentarios);

    }

    @Override
    public ArrayList<BuscaVenta> buscaVentaCancelar(int idVenta, int idSucursal) {

        try {
            ArrayList<BuscaVenta> lstVentas = new ArrayList<BuscaVenta>();
            List<Object[]> lstObject = ejb.buscaVentaCancelar(idVenta, idSucursal);
            for (Object[] obj : lstObject) {
                BuscaVenta busca_venta = new BuscaVenta();
                busca_venta.setIdExistenciaFk(obj[0] == null ? null : new BigDecimal(obj[0].toString()));
                busca_venta.setCantidadEmpaque(obj[1] == null ? null : new BigDecimal(obj[1].toString()));
                busca_venta.setKilosVendidos(obj[2] == null ? null : new BigDecimal(obj[2].toString()));

                lstVentas.add(busca_venta);
            }
            return lstVentas;
        } catch (Exception ex) {
            Logger.getLogger(ServiceBuscaVenta.class.getName()).log(Level.SEVERE, null, ex);
            return null;

        }

    }

    @Override
    public VentaMayoreo getVentaByfolioAndIdSuc(BigDecimal folioVenta, int idSucursal) {

        try {
            ArrayList<BuscaVenta> lstVentas = new ArrayList<BuscaVenta>();
            List<Object[]> lstObject = ejb.getVentaMenudeoByfolioAndIdSuc(folioVenta, idSucursal);

            VentaMayoreo venta = new VentaMayoreo();
            ArrayList<VentaProductoMayoreo> lstVenta = new ArrayList<VentaProductoMayoreo>();
            BigDecimal totalVenta = new BigDecimal(0);
            for (Object[] obj : lstObject) {
                VentaProductoMayoreo producto = new VentaProductoMayoreo();
//                BuscaVenta busca_venta = new BuscaVenta();
                venta.setNombreCliente(obj[0] == null ? "" : obj[0].toString());
                venta.setNombreVendedor(obj[1] == null ? "" : obj[1].toString());
                venta.setIdVentaMayoreoPk(obj[2] == null ? null : new BigDecimal(obj[2].toString()));
                producto.setNombreProducto(obj[3] == null ? "" : obj[3].toString());
                producto.setNombreEmpaque(obj[4] == null ? "" : obj[4].toString());
                producto.setCantidadEmpaque(obj[5] == null ? null : new BigDecimal(obj[5].toString()));
                producto.setPrecioProducto(obj[6] == null ? null : new BigDecimal(obj[6].toString()));
                producto.setTotalVenta(producto.getCantidadEmpaque().multiply(producto.getPrecioProducto(), MathContext.UNLIMITED));
                venta.setTotalVenta(obj[7] == null ? null : new BigDecimal(obj[7].toString()));
                venta.setFechaVenta((Date) obj[8]);
                venta.setFechaPromesaPago((Date) obj[9]);
                venta.setNombreEstatus(obj[10] == null ? "" : obj[10].toString());
                venta.setIdStatusFk(obj[11] == null ? new BigDecimal(0) : new BigDecimal(obj[11].toString())); //id status
                venta.setIdSucursalFk(obj[12] == null ? null : new BigDecimal(obj[12].toString()));
                totalVenta = totalVenta.add(producto.getTotalVenta());
                venta.setTotalVenta(totalVenta);
                venta.setIdtipoVentaFk(obj[16] == null ? null : new BigDecimal(obj[16].toString()));
//                venta.setNombreSucursal(obj[13] == null ? "" : obj[13].toString());
//                venta.setFolioSucursal(new BigDecimal(obj[14] == null ? "0" : obj[14].toString()));
                venta.setIdClienteFk(obj[15] == null ? null : new BigDecimal(obj[15].toString()));
                venta.setVentaSucursal(folioVenta);

                lstVenta.add(producto);
            }

            venta.setListaProductos(lstVenta);

            return venta;
        } catch (Exception ex) {
            Logger.getLogger(ServiceBuscaVenta.class.getName()).log(Level.SEVERE, null, ex);
            return null;

        }

    }

}
