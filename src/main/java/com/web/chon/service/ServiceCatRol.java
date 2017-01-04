package com.web.chon.service;

import com.web.chon.dominio.Pagina;
import com.web.chon.dominio.Rol;
import com.web.chon.ejb.EjbCatRol;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Juan
 */
@Service
@Transactional
public class ServiceCatRol implements IfaceCatRol {

    @Autowired
    EjbCatRol ejb;

    

    @Override
    public int getNextVal() {
        
           
        
        return ejb.getNextVal();
    }

    @Override
    public Pagina<Rol> findAll(Pageable pageable) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pagina<Rol> findAllDominio(Rol filters, int first, int pageSize) {
       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int create(Rol rol) {

           
        return ejb.insert(rol);
    }

    @Override
    public int update(Rol rol) {
           
        return ejb.update(rol);
    }

    @Override
    public List<Rol> getAll() {

        try {
            ArrayList<Rol> lstRol = new ArrayList<Rol>();

               
            
            List<Object[]> lstObject = ejb.getAll();

            for (Object[] obj : lstObject) {
                Rol rol = new Rol();
                rol.setIdRolPk(new BigDecimal(obj[0].toString()));
                rol.setNombreRol((obj[1] == null ? "" : obj[1].toString()));
                rol.setDescripcionRol((obj[2] == null ? "" : obj[2].toString()));
                lstRol.add(rol);

            }
            return lstRol;
        } catch (Exception ex) {
            Logger.getLogger(ServiceCatRol.class.getName()).log(Level.SEVERE, null, ex);
            return null;

        }
    }

    @Override
    public int delete(BigDecimal id) {
           
        return ejb.delete(id.intValue());
    }

    @Override
    public Rol getById(BigDecimal idRol) {

        List<Object[]> lstObject = null;
        Rol rol = new Rol();
           
        lstObject = ejb.getById(idRol.intValue());

           
        for (Object[] obj : lstObject) {

            rol.setIdRolPk(obj[0] == null ? null : new BigDecimal(obj[0].toString()));
            rol.setNombreRol(obj[1] == null ? "" : obj[1].toString());
            rol.setDescripcionRol(obj[2] == null ? "" : obj[2].toString());
        }

        return rol;

    }

    @Override
    public Rol getById(String dominio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
