package com.web.chon.service;

import com.web.chon.dominio.EntregaMercancia;
import com.web.chon.ejb.EjbEntregaMercancia;
import com.web.chon.util.TiempoUtil;
import java.math.BigDecimal;
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
 * @author Juan de la Cruz
 */
@Service
@Transactional
public class ServiceEntregaMercancia implements IfaceEntregaMercancia {

    @Autowired
    EjbEntregaMercancia ejb;

    @Override
    public ArrayList<EntregaMercancia> getByIdSucursalAndFolioSucursal(BigDecimal idSucursal, BigDecimal folioSucursal) {
        try {
            ArrayList<EntregaMercancia> lstEntregaMercancia = new ArrayList<EntregaMercancia>();
            List<Object[]> lstObject = new ArrayList<Object[]>();

            lstObject = ejb.getByIdSucursalAndIdFolioSucursal(idSucursal, folioSucursal);
            for (Object[] object : lstObject) {
                EntregaMercancia dominio = new EntregaMercancia();

                dominio.setIdVPMayoreo(object[0] == null ? null : new BigDecimal(object[0].toString()));
                dominio.setIdProducto(object[2] == null ? null : object[2].toString());
                dominio.setNombreProducto(object[3] == null ? null : object[3].toString());
                dominio.setPrecioProducto(object[4] == null ? null : new BigDecimal(object[4].toString()));
                dominio.setKilosRemanente(object[5] == null ? null : new BigDecimal(object[5].toString()));
                dominio.setEmpaquesRemanente(object[6] == null ? null : new BigDecimal(object[6].toString()));
                dominio.setTotalVenta(object[7] == null ? null : new BigDecimal(object[7].toString()));
                dominio.setIdTipoEmpaque(object[8] == null ? null : new BigDecimal(object[8].toString()));
                dominio.setNombreEmpaque(object[9] == null ? null : object[9].toString());
                dominio.setIdCliente(object[10] == null ? null : new BigDecimal(object[10].toString()));
                dominio.setNombreCliente(object[11] == null ? null : object[11].toString());
                dominio.setIdUsuario(object[12] == null ? null : new BigDecimal(object[12].toString()));
                dominio.setFechaVenta(object[13] == null ? null : (Date) object[13]);
                dominio.setIdEstatus(object[14] == null ? null : new BigDecimal(object[14].toString()));
                dominio.setEstatusVenta(object[15] == null ? null : object[15].toString());
                dominio.setIdSucursal(object[16] == null ? null : new BigDecimal(object[16].toString()));

                dominio.setEmpaquesEntregados(object[17] == null ? null : new BigDecimal(object[17].toString()));
                dominio.setKilosEntregados(object[18] == null ? null : new BigDecimal(object[18].toString()));
                dominio.setIdTipoVenta(object[19] == null ? null : new BigDecimal(object[19].toString()));
                dominio.setNombreTipoVenta(object[10] == null ? null : object[20].toString());
                dominio.setIdCarroFk(object[22] == null ? null : new BigDecimal(object[22].toString()));
                dominio.setLstEntregaMercancia(getByIdVentaMayoreoProducto(dominio.getIdVPMayoreo()));

                lstEntregaMercancia.add(dominio);

            }

            return lstEntregaMercancia;
        } catch (Exception ex) {
            System.out.println("error < " + ex.getMessage() + " > " + ex.getStackTrace());
            ex.getStackTrace();
            return null;

        }
    }

    @Override
    public int insert(EntregaMercancia entregaMercancia) {

        return ejb.insertar(entregaMercancia);

    }

    @Override
    public ArrayList<EntregaMercancia> getByIdVentaMayoreoProducto(BigDecimal idVentaMayoreoProducto) {
        try {

            ArrayList<EntregaMercancia> lstEntregaMercancia = new ArrayList<EntregaMercancia>();
            List<Object[]> lstObject = new ArrayList<Object[]>();

            lstObject = ejb.getByIdVentaMayoreoProducto(idVentaMayoreoProducto);
            for (Object[] object : lstObject) {
                EntregaMercancia dominio = new EntregaMercancia();

                dominio.setIdEntregaMercanciaPk(object[0] == null ? null : new BigDecimal(object[0].toString()));
                dominio.setIdVPMenudeo(object[1] == null ? null : new BigDecimal(object[1].toString()));
                dominio.setIdVPMayoreo(object[2] == null ? null : new BigDecimal(object[2].toString()));
                dominio.setIdUsuario(object[3] == null ? null : new BigDecimal(object[3].toString()));
                dominio.setFechaStr(object[4] == null ? null : TiempoUtil.getFechaDDMMYYYYHHMM(((Date) object[4])));
                dominio.setEmpaquesEntregados(object[5] == null ? null : new BigDecimal(object[5].toString()));
                dominio.setKilosEntregados(object[6] == null ? null : new BigDecimal(object[6].toString()));
                dominio.setObservaciones(object[7] == null ? null : object[7].toString());
                dominio.setNombreUsuarioEntrega(object[8] == null ? null : object[8].toString());

                lstEntregaMercancia.add(dominio);

            }

            return lstEntregaMercancia;
        } catch (Exception ex) {
            System.out.println("error < " + ex.getMessage() + " > " + ex.getStackTrace());
            ex.getStackTrace();
            return null;

        }
    }

}
