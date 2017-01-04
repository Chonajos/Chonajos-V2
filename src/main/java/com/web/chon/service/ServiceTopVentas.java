package com.web.chon.service;

import com.web.chon.dominio.topVentas;
import com.web.chon.ejb.EjbTopVentas;
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
 * @author freddy
 */
@Service
@Transactional
public class ServiceTopVentas implements IfaceTopVentas {

    @Autowired
    EjbTopVentas ejb;

    @Override
    public ArrayList<topVentas> getMayoreo(Date fechaInicio, Date fechaFin, String orden, BigDecimal rows) {

        try {
            ArrayList<topVentas> lstTop = new ArrayList<topVentas>();
            List<Object[]> lstObject = ejb.getMayoreo(TiempoUtil.getFechaDDMMYYYY(fechaInicio), TiempoUtil.getFechaDDMMYYYY(fechaFin), orden, rows);

            for (Object[] obj : lstObject) {
                topVentas tv = new topVentas();
                tv.setNombreSucursal(obj[0] == null ? "" : obj[0].toString());
                tv.setNombreVendedor(obj[1] == null ? "" : obj[1].toString());
                tv.setApPaternoVendedor(obj[2] == null ? "" : obj[2].toString());
                tv.setApMaternoVendedor(obj[3] == null ? "" : obj[3].toString());
                tv.setIdVendedor(obj[4] == null ? null : new BigDecimal(obj[4].toString()));
                tv.setEmpaques(obj[5] == null ? null : new BigDecimal(obj[5].toString()));
                tv.setKilos(obj[6] == null ? null : new BigDecimal(obj[6].toString()));
                tv.setDinero(obj[7] == null ? null : new BigDecimal(obj[7].toString()));
                tv.setNumVentas(obj[8] == null ? null : new BigDecimal(obj[8].toString()));
                lstTop.add(tv);
            }
            return lstTop;
        } catch (Exception ex) {
            Logger.getLogger(ServiceTopVentas.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    @Override
    public ArrayList<topVentas> getMenudeo(Date fechaInicio, Date fechaFin, String orden, BigDecimal rows) {

        try {
            ArrayList<topVentas> lstTop = new ArrayList<topVentas>();
            List<Object[]> lstObject = ejb.getMenudeo(TiempoUtil.getFechaDDMMYYYY(fechaInicio), TiempoUtil.getFechaDDMMYYYY(fechaFin), orden, rows);
            for (Object[] obj : lstObject) {
                topVentas tv = new topVentas();
                tv.setNombreSucursal(obj[0] == null ? "" : obj[0].toString());
                tv.setNombreVendedor(obj[1] == null ? "" : obj[1].toString());
                tv.setApPaternoVendedor(obj[2] == null ? "" : obj[2].toString());
                tv.setApMaternoVendedor(obj[3] == null ? "" : obj[3].toString());
                tv.setIdVendedor(obj[4] == null ? null : new BigDecimal(obj[4].toString()));
                tv.setEmpaques(obj[5] == null ? null : new BigDecimal(obj[5].toString()));
                tv.setKilos(obj[6] == null ? null : new BigDecimal(obj[6].toString()));
                tv.setDinero(obj[7] == null ? null : new BigDecimal(obj[7].toString()));
                tv.setNumVentas(obj[8] == null ? null : new BigDecimal(obj[8].toString()));
                lstTop.add(tv);
            }
            return lstTop;
        } catch (Exception ex) {
            Logger.getLogger(ServiceTopVentas.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

}
