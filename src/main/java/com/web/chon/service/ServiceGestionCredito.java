package com.web.chon.service;

import com.web.chon.dominio.GestionCredito;
import com.web.chon.ejb.EjbGestionCredito;
import com.web.chon.negocio.NegocioGestionCredito;
import com.web.chon.util.Utilidades;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan de la Cruz
 */
@Service
public class ServiceGestionCredito implements IfaceGestionCredito {

    @Autowired
    EjbGestionCredito ejb;

 

    @Override
    public int update(GestionCredito gestionCredito) {
           
        return ejb.update(gestionCredito);
    }

    @Override
    public int insert(GestionCredito gestionCredito) {
           
        return ejb.insert(gestionCredito);
    }

    @Override
    public int delete(BigDecimal idGestionCredito) {
           
        return ejb.delete(idGestionCredito);
    }

    @Override
    public ArrayList<GestionCredito> getAll() {
           
        ArrayList<GestionCredito> lstGestionCredito = new ArrayList<GestionCredito>();
        List<Object[]> lstObject = new ArrayList<Object[]>();
        lstObject = ejb.getAll();
        for (Object[] object : lstObject) {
            GestionCredito gestionCredito = new GestionCredito();

            gestionCredito.setIdAcionGestion(object[0] == null ? null : new BigDecimal(object[0].toString()));
            gestionCredito.setIdCredito(object[1] == null ? null : new BigDecimal(object[1].toString()));
            gestionCredito.setIdGestionCredito(object[2] == null ? null : new BigDecimal(object[2].toString()));
            gestionCredito.setIdUsario(object[3] == null ? null : new BigDecimal(object[3].toString()));
            gestionCredito.setObservaciones(object[4] == null ? null : object[4].toString());

            lstGestionCredito.add(gestionCredito);
        }

        return lstGestionCredito;

    }

    @Override
    public GestionCredito getById(BigDecimal idAbonoCredito) {
           

        GestionCredito gestionCredito = new GestionCredito();
        List<Object[]> lstObject = new ArrayList<Object[]>();
        lstObject = ejb.getById(idAbonoCredito);
        for (Object[] object : lstObject) {

            gestionCredito.setIdAcionGestion(object[0] == null ? null : new BigDecimal(object[0].toString()));
            gestionCredito.setIdCredito(object[1] == null ? null : new BigDecimal(object[1].toString()));
            gestionCredito.setIdGestionCredito(object[2] == null ? null : new BigDecimal(object[2].toString()));
            gestionCredito.setIdUsario(object[3] == null ? null : new BigDecimal(object[3].toString()));
            gestionCredito.setObservaciones(object[4] == null ? null : object[4].toString());

        }

        return gestionCredito;
    }

    @Override
    public int getNextVal() {
           
        try {
            return ejb.getNextVal();

        } catch (Exception ex) {
            Logger.getLogger(ServiceGestionCredito.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

}
