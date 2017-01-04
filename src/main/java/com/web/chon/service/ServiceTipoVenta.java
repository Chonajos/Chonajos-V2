package com.web.chon.service;

import com.web.chon.dominio.TipoVenta;
import com.web.chon.ejb.EjbTipoVenta;
import java.math.BigDecimal;
import java.util.ArrayList;
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
public class ServiceTipoVenta implements IfaceTipoVenta {

    @Autowired
    EjbTipoVenta ejb;

    @Override
    public ArrayList<TipoVenta> getAll() {

        try {
            ArrayList<TipoVenta> lstTipos = new ArrayList<TipoVenta>();
            List<Object[]> lstObject = ejb.getAll();

            for (Object[] obj : lstObject) {
                TipoVenta tipo = new TipoVenta();
                tipo.setIdTipoVentaPk(obj[0] == null ? null : new BigDecimal(obj[0].toString()));
                tipo.setNombreTipoVenta(obj[1] == null ? "" : obj[1].toString());
                lstTipos.add(tipo);
            }
            return lstTipos;
        } catch (Exception ex) {
            Logger.getLogger(TipoVenta.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
}
