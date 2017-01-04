/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.service;

import com.web.chon.dominio.TipoConvenio;
import com.web.chon.ejb.EjbConvenio;
import com.web.chon.util.Utilidades;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import com.web.chon.negocio.NegocioConvenio;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author marcogante
 */
@Service
public class ServiceTipoConvenio implements IfaceTipoCovenio {
    @Autowired
    EjbConvenio ejb;

    

    @Override
    public ArrayList<TipoConvenio> getTipos() {
        try {
            ArrayList<TipoConvenio> lstTipoOrden = new ArrayList<TipoConvenio>();
               

            List<Object[]> lstObject = ejb.getTipos();

            for (Object[] obj : lstObject) 
            {
                TipoConvenio tipoOrden = new TipoConvenio();
                tipoOrden.setIdTcPK(obj[0] == null ? null : new BigDecimal(obj[0].toString()));
                tipoOrden.setNombreTipoConvenio(obj[1] == null ? "" : obj[1].toString());
                tipoOrden.setDescripcionTipoConvenio(obj[2] == null ? "" : obj[2].toString());
                lstTipoOrden.add(tipoOrden);
            }
            return lstTipoOrden;
        } catch (Exception ex) {
            Logger.getLogger(TipoConvenio.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

}
