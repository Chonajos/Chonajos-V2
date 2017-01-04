/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.ejb;

import com.web.chon.negocio.NegocioCuentasBancarias;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author JesusAlfredo
 */
@Repository
public class EjbCuentaBancaria implements NegocioCuentasBancarias {
    
    @PersistenceContext
    private EntityManager em;

       
    public List<Object[]> getCuentas() {
         try {
            Query query = em.createNativeQuery("SELECT * FROM CUENTA_BANCARIA");
            List<Object[]> resultList = null;
            resultList = query.getResultList();
            return resultList;
        } catch (Exception ex) {
            Logger.getLogger(EjbCuentaBancaria.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    
    }

}
