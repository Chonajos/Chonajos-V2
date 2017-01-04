/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.service;

import com.web.chon.dominio.TipoEmpaque;
import com.web.chon.ejb.EjbEmpaque;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio para el catlogo de empaques
 *
 * @author Juan de la cruz
 */
@Service
public class ServiceEmpaque implements IfaceEmpaque {

    @Autowired
    EjbEmpaque ejb;

    @Override
    public ArrayList<TipoEmpaque> getEmpaques() {
        try {
            ArrayList<TipoEmpaque> lstEmpaque = new ArrayList<TipoEmpaque>();
     
            List<Object[]> lstObject = ejb.getEmpaques();

            for (Object[] obj : lstObject) {

                TipoEmpaque empaque = new TipoEmpaque();
                empaque.setIdTipoEmpaquePk(obj[0] == null ? null : new BigDecimal(obj[0].toString()));
                empaque.setNombreEmpaque(obj[1] == null ? "" : obj[1].toString());
                empaque.setEstatus(obj[2] == null ?  false: obj[2].toString().equals("1")? true:false);

                lstEmpaque.add(empaque);
            }

            return lstEmpaque;
        } catch (Exception ex) {
            Logger.getLogger(ServiceEmpaque.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    @Override
    public TipoEmpaque getEmpaqueById(int idEmpaque) {
        
       try {
           
            System.out.println("service Empaque get by id");
            TipoEmpaque te  = new TipoEmpaque();
            Object[] obj =  (Object[]) ejb.getEmpaqueByIdEmpaque(idEmpaque);
            te.setIdTipoEmpaquePk(obj[0] == null ? null : new BigDecimal(obj[0].toString()));
            te.setNombreEmpaque(obj[1] == null ? "" : obj[1].toString());
            te.setEstatus(obj[2] == null ?  false: obj[2].toString().equals("1")? true:false);
            te.setPesoKiloEmpaque(obj[3] == null ? null : new BigDecimal(obj[3].toString()));

            return te;
        } catch (Exception ex) {
            Logger.getLogger(ServiceEmpaque.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public int deleteEmpaque(int idEmpaque) {
        try {
            
        
            return ejb.deleteEmpaque(idEmpaque);
        } catch (Exception ex) {
            Logger.getLogger(ServiceEmpaque.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int updateEmpaque(TipoEmpaque tipoEmpaque) {
        try {
            
            System.out.println("service" + tipoEmpaque.toString());
            return ejb.updateEmpaque(tipoEmpaque);
        } catch (Exception ex) {
            Logger.getLogger(ServiceEmpaque.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int insertarEmpaque(TipoEmpaque tipoEmpaque) {
        try {
            
            System.out.println("tipo empaque" + tipoEmpaque.toString());
            return ejb.insertarEmpaque(tipoEmpaque);
        } catch (Exception ex) {
            Logger.getLogger(ServiceEmpaque.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

    }



}
