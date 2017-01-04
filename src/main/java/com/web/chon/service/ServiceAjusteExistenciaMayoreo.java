
package com.web.chon.service;

import com.web.chon.dominio.AjusteExistenciaMayoreo;
import com.web.chon.ejb.EjbAjusteExistenciaMayoreo;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan de la Cruz
 */
@Service
public class ServiceAjusteExistenciaMayoreo implements IfaceAjusteExistenciaMayoreo {

    @Autowired
    EjbAjusteExistenciaMayoreo ejb;


    @Override
    public int insert(AjusteExistenciaMayoreo data) {
           

        return ejb.insert(data);
    }

    @Override
    public int update(AjusteExistenciaMayoreo data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AjusteExistenciaMayoreo> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AjusteExistenciaMayoreo> getAllByIdSucursal(BigDecimal idSucursal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
