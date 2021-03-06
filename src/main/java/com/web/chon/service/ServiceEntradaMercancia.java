package com.web.chon.service;

import com.web.chon.dominio.CarroDetalleGeneral;
import com.web.chon.dominio.EntradaMercancia;
import com.web.chon.dominio.MayoreoProductoEntradaProducto;
import com.web.chon.dominio.Pagina;
import com.web.chon.ejb.EjbEntradaMercancia;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marcogante
 */
@Service
@Transactional
public class ServiceEntradaMercancia implements IfaceEntradaMercancia {

    @Autowired
    IfaceEntradaMercanciaProducto ifaceEntradaMercanciaProducto;
    @Autowired
    IfaceVentaMayoreoProducto ifaceVentaMayoreoProducto;
    @Autowired
    EjbEntradaMercancia ejb;

    @Override
    public int insertEntradaMercancia(EntradaMercancia entrada) {

        return ejb.insertEntradaMercancia(entrada);

    }

    @Override
    public int buscaMaxMovimiento(EntradaMercancia entrada) {

        try {
            return ejb.buscaMaxMovimiento(entrada);
        } catch (Exception ex) {
            //Logger.getLogger(NegocioEntradaMercancia.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

    }

    @Override
    public int getNextVal() {

        try {
            return ejb.getNextVal();

        } catch (Exception ex) {
            Logger.getLogger(ServiceEntradaMercancia.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

    }

    @Override
    public ArrayList<EntradaMercancia> getEntradaProductoByIntervalDate(Date fechaInicio, Date fechaFin, BigDecimal idSucursal, BigDecimal idProvedor, BigDecimal carro) {

        List<Object[]> lstObject = new ArrayList<Object[]>();
        ArrayList<EntradaMercancia> lstEntradaMercancia2 = new ArrayList<EntradaMercancia>();

        lstObject = ejb.getEntradaProductoByIntervalDate(fechaInicio, fechaFin, idSucursal, idProvedor, carro);

        for (Object[] obj : lstObject) {
            EntradaMercancia dominio = new EntradaMercancia();
            dominio.setIdEmPK(new BigDecimal(obj[0].toString()));
            dominio.setIdProvedorFK(obj[1] == null ? null : new BigDecimal(obj[1].toString()));
            dominio.setMovimiento(obj[2] == null ? null : new BigDecimal(obj[2].toString()));
            dominio.setFecha(obj[3] == null ? null : (Date) obj[3]);
            dominio.setRemision(obj[4] == null ? null : obj[4].toString());
            dominio.setIdSucursalFK(obj[5] == null ? null : new BigDecimal(obj[5].toString()));
            dominio.setFolio(obj[6] == null ? null : obj[6].toString());
            dominio.setIdStatusFk(obj[7] == null ? null : new BigDecimal(obj[7].toString()));
            dominio.setKilosTotales(obj[8] == null ? null : new BigDecimal(obj[8].toString()));
            dominio.setKilosTotalesProvedor(obj[9] == null ? null : new BigDecimal(obj[9].toString()));
            dominio.setNombreProvedor(obj[12] == null ? " " : obj[12].toString());
            dominio.setNombreSucursal(obj[13] == null ? " " : obj[13].toString());
            dominio.setIdCarroSucursal(obj[14] == null ? null : new BigDecimal(obj[14].toString()));
            dominio.setComentariosGenerales(obj[15] == null ? " " : obj[15].toString());

            dominio.setCantidadEmpaquesReales(obj[16] == null ? null : new BigDecimal(obj[16].toString()));
            dominio.setCantidadEmpaquesProvedor(obj[17] == null ? null : new BigDecimal(obj[17].toString()));

            dominio.setFechaPago(obj[18] == null ? null : (Date) obj[18]);
            dominio.setNombreRecibidor(obj[19] == null ? " " : obj[19].toString());
            dominio.setListaProductos(ifaceEntradaMercanciaProducto.getEntradaProductoByIdEM(dominio.getIdEmPK()));
            lstEntradaMercancia2.add(dominio);
        }

        return lstEntradaMercancia2;
    }

    @Override
    public Pagina<EntradaMercancia> findAll(Pageable pageable) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pagina<EntradaMercancia> findAllDominio(EntradaMercancia filters, int first, int pageSize) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EntradaMercancia getById(BigDecimal dominio) {

        List<Object[]> object = ejb.getEntradaById(dominio);
        EntradaMercancia entra = new EntradaMercancia();
        for (Object[] obj : object) {
            entra.setIdEmPK(obj[0] == null ? null : new BigDecimal(obj[0].toString()));
            entra.setFolio(obj[1] == null ? "" : obj[1].toString());
        }

        return entra;
    }

    @Override
    public EntradaMercancia getById(String dominio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int create(EntradaMercancia dominio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(EntradaMercancia dominio) {

        try {
            return ejb.updateEntradaMercancia(dominio);

        } catch (Exception ex) {
            Logger.getLogger(ServiceEntradaMercancia.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

    }

    @Override
    public List<EntradaMercancia> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(BigDecimal id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<EntradaMercancia> getSubEntradaByNombre(String nombre) {
        try {
            ArrayList<EntradaMercancia> lstEntradas = new ArrayList<EntradaMercancia>();

            List<Object[]> object = ejb.getSubEntradaByNombre(nombre);

            for (Object[] obj : object) {

                EntradaMercancia entrada = new EntradaMercancia();
                entrada.setIdEmPK(obj[0] == null ? null : new BigDecimal(obj[0].toString()));
                entrada.setFolio(obj[1] == null ? "" : obj[1].toString());

                lstEntradas.add(entrada);
            }

            return lstEntradas;

        } catch (Exception ex) {
            Logger.getLogger(ServiceEntradaMercancia.class
                    .getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    @Override
    public int getCarroSucursal(BigDecimal idSucursal) {

        try {
            return ejb.getCarroSucursal(idSucursal);
        } catch (Exception ex) {
            Logger.getLogger(ServiceEntradaMercancia.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int deleteEntradaMercancia(EntradaMercancia entrada) {

        try {
            return ejb.deleteEntradaMercancia(entrada);
        } catch (Exception ex) {
            Logger.getLogger(ServiceEntradaMercancia.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int updateEntradaMercancia(EntradaMercancia entrada) {

        try {
            return ejb.updateEntradaMercancia(entrada);
        } catch (Exception ex) {
            Logger.getLogger(ServiceEntradaMercancia.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

    }

    @Override
    public EntradaMercancia getEntradaByIdEmPFk(BigDecimal idEmPFk) {

        List<Object[]> lstObject = new ArrayList<Object[]>();
        lstObject = ejb.getEntradaByIdEmPFk(idEmPFk);
        EntradaMercancia dominio = new EntradaMercancia();
        for (Object[] obj : lstObject) {
            dominio.setIdEmPK(new BigDecimal(obj[0].toString()));
            dominio.setIdProvedorFK(obj[1] == null ? null : new BigDecimal(obj[1].toString()));
            dominio.setMovimiento(obj[2] == null ? null : new BigDecimal(obj[2].toString()));
            dominio.setFecha(obj[3] == null ? null : (Date) obj[3]);
            dominio.setRemision(obj[4] == null ? null : obj[4].toString());
            dominio.setIdSucursalFK(obj[5] == null ? null : new BigDecimal(obj[5].toString()));
            dominio.setFolio(obj[6] == null ? null : obj[6].toString());
            dominio.setIdStatusFk(obj[7] == null ? null : new BigDecimal(obj[7].toString()));
            dominio.setKilosTotales(obj[8] == null ? null : new BigDecimal(obj[8].toString()));
            dominio.setKilosTotalesProvedor(obj[9] == null ? null : new BigDecimal(obj[9].toString()));
            dominio.setComentariosGenerales(obj[10] == null ? " " : obj[10].toString());
            dominio.setFechaRemision(obj[11] == null ? null : (Date) obj[11]);

            dominio.setIdUsuario(obj[14] == null ? null : new BigDecimal(obj[14].toString()));
            dominio.setIdCarroSucursal(obj[15] == null ? null : new BigDecimal(obj[15].toString()));

            dominio.setListaProductos(ifaceEntradaMercanciaProducto.getEntradaProductoByIdEM(dominio.getIdEmPK()));
        }
        return dominio;

    }

    @Override
    public EntradaMercancia getEntradaByIdPk(BigDecimal idPk) {

        List<Object[]> lstObject = new ArrayList<Object[]>();
        lstObject = ejb.getEntradaByIdPk(idPk);
        EntradaMercancia dominio = new EntradaMercancia();
        for (Object[] obj : lstObject) {
            dominio.setIdEmPK(new BigDecimal(obj[0].toString()));
            dominio.setIdProvedorFK(obj[1] == null ? null : new BigDecimal(obj[1].toString()));
            dominio.setMovimiento(obj[2] == null ? null : new BigDecimal(obj[2].toString()));
            dominio.setFecha(obj[3] == null ? null : (Date) obj[3]);
            dominio.setRemision(obj[4] == null ? null : obj[4].toString());
            dominio.setIdSucursalFK(obj[5] == null ? null : new BigDecimal(obj[5].toString()));
            dominio.setFolio(obj[6] == null ? null : obj[6].toString());
            dominio.setIdStatusFk(obj[7] == null ? null : new BigDecimal(obj[7].toString()));
            dominio.setKilosTotales(obj[8] == null ? null : new BigDecimal(obj[8].toString()));
            dominio.setKilosTotalesProvedor(obj[9] == null ? null : new BigDecimal(obj[9].toString()));
            dominio.setNombreProvedor(obj[12] == null ? " " : obj[12].toString());
            dominio.setNombreSucursal(obj[13] == null ? " " : obj[13].toString());
            dominio.setIdCarroSucursal(obj[14] == null ? null : new BigDecimal(obj[14].toString()));
            dominio.setComentariosGenerales(obj[15] == null ? " " : obj[15].toString());
            dominio.setListaProductos(ifaceEntradaMercanciaProducto.getEntradaProductoByIdEM(dominio.getIdEmPK()));
        }
        return dominio;
    }

    @Override
    public ArrayList<EntradaMercancia> getCarrosByIdSucursalAnsIdProvedor(BigDecimal idSucursal, BigDecimal idProvedor) {

        List<Object[]> lstObject = new ArrayList<Object[]>();
        ArrayList<EntradaMercancia> lstEntradaMercancia = new ArrayList<EntradaMercancia>();

        lstObject = ejb.getCarrosByIdSucursalAndIdProvedor(idSucursal, idProvedor, null);
        for (Object[] obj : lstObject) {

            EntradaMercancia dominio = new EntradaMercancia();

            dominio.setIdCarroSucursal(obj[0] == null ? null : new BigDecimal(obj[0].toString()));
            dominio.setRemision(obj[1] == null ? null : obj[1].toString());

            lstEntradaMercancia.add(dominio);
        }

        return lstEntradaMercancia;
    }

    @Override
    public ArrayList<CarroDetalleGeneral> getReporteGeneralCarro(BigDecimal idSucursal, BigDecimal idProvedor, BigDecimal carro, String fechaInicio, String fechaFin) {

        List<Object[]> lstObject = new ArrayList<Object[]>();
        ArrayList<CarroDetalleGeneral> lstCarroDetalleGeneral = new ArrayList<CarroDetalleGeneral>();

        lstObject = ejb.getCarrosByIdSucursalAndIdProvedor(idSucursal, idProvedor, carro);

        for (Object[] obj : lstObject) {

            CarroDetalleGeneral dominio = new CarroDetalleGeneral();

            dominio.setCarro(obj[0] == null ? null : new BigDecimal(obj[0].toString()));
            dominio.setIdentificador(obj[1] == null ? null : obj[1].toString());
            dominio.setFecha(obj[2] == null ? null : (Date) obj[2]);
            dominio.setNombreProvedor(obj[3] == null ? "" : obj[3].toString());

            ArrayList<MayoreoProductoEntradaProducto> lstMayoreoProductoEntradaProducto = ifaceVentaMayoreoProducto.getVentaByIdSucursalAndCarro(idSucursal, dominio.getCarro(), fechaInicio, fechaFin);
            //Se calculan las ventas, comisiones y el status del  carro
            String status = "Vendido";
            BigDecimal venta = new BigDecimal(0);
            BigDecimal comision = new BigDecimal(0);
            for (MayoreoProductoEntradaProducto mayoreoProducto : lstMayoreoProductoEntradaProducto) {

                venta = venta.add(mayoreoProducto.getTotalVenta());
                comision = comision.add(mayoreoProducto.getComision());
                if (mayoreoProducto.getEmpaqueEntrada().intValue() > mayoreoProducto.getEmpaquesVendidos().intValue()) {
                    status = "En Proceso";
                }
            }

            //Si no hay ventas se pone un status en proceso
            if (venta.equals(new BigDecimal(0))) {
                status = "En Proceso";
            }

            dominio.setVenta(venta);
            dominio.setStatus(status);
            dominio.setComision(comision);

            lstCarroDetalleGeneral.add(dominio);
        }

        return lstCarroDetalleGeneral;
    }
}
