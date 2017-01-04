package com.web.chon.service;

import com.web.chon.dominio.TipoAbono;
import com.web.chon.ejb.EjbTipoAbono;
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
 * @author JesusAlfredo
 */
@Service
@Transactional
public class ServiceTipoAbono implements IfaceTipoAbono {

    @Autowired
    EjbTipoAbono ejb;

    @Override
    public ArrayList<TipoAbono> getAll() {

        ArrayList<TipoAbono> lstCredito = new ArrayList<TipoAbono>();
        List<Object[]> lstObject = new ArrayList<Object[]>();
        lstObject = ejb.getAll();
        for (Object[] object : lstObject) {
            TipoAbono ta = new TipoAbono();
            ta.setIdTipoAbono(object[0] == null ? null : new BigDecimal(object[0].toString()));
            ta.setNombreTipoAbono(object[1] == null ? null : object[1].toString());
            ta.setDescripcion(object[2] == null ? null : object[2].toString());
            lstCredito.add(ta);
        }

        return lstCredito;

    }

    @Override
    public TipoAbono getById(BigDecimal idtAbono) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(BigDecimal idtAbono) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(TipoAbono tAbono) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insert(TipoAbono tAbono) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
