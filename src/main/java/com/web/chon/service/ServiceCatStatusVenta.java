package com.web.chon.service;

import com.web.chon.dominio.StatusVenta;
import com.web.chon.ejb.EjbCatStatusVenta;
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
public class ServiceCatStatusVenta implements IfaceCatStatusVenta {

    @Autowired
    EjbCatStatusVenta ejb;

    @Override
    public ArrayList<StatusVenta> getStatusVentas() {
        try {
            ArrayList<StatusVenta> lista_sucursales = new ArrayList<StatusVenta>();
            List<Object[]> lstObject = ejb.getStatusVentas();
            for (Object[] obj : lstObject) {
                StatusVenta st = new StatusVenta();
                st.setId_status_pk(Integer.parseInt(obj[0].toString()));
                st.setStatus(obj[1] == null ? "" : obj[1].toString());
                lista_sucursales.add(st);
            }
            return lista_sucursales;
        } catch (Exception ex) {
            Logger.getLogger(ServiceCatStatusVenta.class.getName()).log(Level.SEVERE, null, ex);
            return null;

        }
    }

    @Override
    public List<Object[]> getStatusVentaId(int idSucursal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteStatusVenta(int idSucursal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateStatusVenta(StatusVenta st) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insertStatusVenta(StatusVenta st) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNextVal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
