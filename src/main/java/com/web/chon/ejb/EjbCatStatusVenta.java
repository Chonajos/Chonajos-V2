/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.ejb;

import com.web.chon.dominio.StatusVenta;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author freddy
 */
@Repository
public class EjbCatStatusVenta  
{
   @PersistenceContext
    private EntityManager em;


       
    public List<Object[]> getStatusVentas()
    {
       try {

            System.out.println("EJB_GET_Status_ventas");
            Query query = em.createNativeQuery("select * from status_venta");
            List<Object[]> resultList = null;
            resultList = query.getResultList();
            return resultList;
        } catch (Exception ex) {
            Logger.getLogger(EjbCatCliente.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

       
    public List<Object[]> getStatusVentaId(int idSucursal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

       
    public int deleteStatusVenta(int idSucursal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

       
    public int updateStatusVenta(StatusVenta st) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

       
    public int insertStatusVenta(StatusVenta st) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

       
    public int getNextVal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
