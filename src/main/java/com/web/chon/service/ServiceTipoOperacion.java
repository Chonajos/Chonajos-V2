package com.web.chon.service;

import com.web.chon.dominio.TipoOperacion;
import com.web.chon.ejb.EjbTipoOperacion;
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
public class ServiceTipoOperacion implements IfaceTiposOperacion {

    @Autowired
    EjbTipoOperacion ejb;

    @Override
    public ArrayList<TipoOperacion> getOperaciones() {

        ArrayList<TipoOperacion> lstOperaciones = new ArrayList<TipoOperacion>();
        List<Object[]> lstObject = new ArrayList<Object[]>();
        lstObject = ejb.getOperaciones();
        for (Object[] object : lstObject) {
            TipoOperacion c = new TipoOperacion();
            c.setIdTipoOperacionPk(object[0] == null ? null : new BigDecimal(object[0].toString()));
            c.setNombre(object[1] == null ? null : object[1].toString());
            c.setDescripcion(object[2] == null ? null : object[2].toString());
            lstOperaciones.add(c);
        }
        return lstOperaciones;
    }

}
