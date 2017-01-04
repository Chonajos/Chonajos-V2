/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.service;

import com.web.chon.dominio.AjusteExistenciaMenudeo;
import com.web.chon.ejb.EjbAjusteExistenciaMenudeo;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan de la Cruz
 */
@Service
public class ServiceAjusteExistenciaMenudeo implements IfaceAjusteExistenciaMenudeo {

    @Autowired
    EjbAjusteExistenciaMenudeo ejb;

  

    @Override
    public int insert(AjusteExistenciaMenudeo data) {
           

        return ejb.insert(data);
    }

    @Override
    public int update(AjusteExistenciaMenudeo data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AjusteExistenciaMenudeo> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AjusteExistenciaMenudeo> getAllByIdSucursal(BigDecimal idSucursal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
