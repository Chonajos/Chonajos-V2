/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.ejb;

import com.web.chon.dominio.Correos;
import java.math.BigDecimal;
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
public class EjbCatCorreos   {

    @PersistenceContext
    private EntityManager em;

       
    public int insertCorreo(Correos co) 
    {
        try 
        {
            System.out.println("EJB_INSERTA_CORREOS");

            Query querySel = em.createNativeQuery("SELECT correo FROM CORREOS WHERE CORREO = '" + co.getCorreo() + "' ");
            List<Object[]> resultList = null;
            resultList = querySel.getResultList();
            if (resultList.isEmpty()) 
            {
                
                System.out.println("insert : " + co);
                Query query = em.createNativeQuery("INSERT INTO CORREOS (ID_PK,ID_CLIENTE_FK,CORREO,TIPO)VALUES(S_CORREOS.NextVal,?,?,?)");
                query.setParameter(1, co.getId_cliente_fk());
                query.setParameter(2, co.getCorreo());
                query.setParameter(3, co.getTipo());
                return query.executeUpdate();

            } else 
            {
                System.out.println("Encontro un correo igual");

                return 0;

            }

        } catch (Exception ex) 
        {
            Logger.getLogger(EjbCatCorreos.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

       
    public int deleteCorreos(Correos co)
    {
        
   try {

            System.out.println("Correos a eliminar: " + co.getId_cliente_fk());
            Query query = em.createNativeQuery("DELETE CORREOS WHERE ID_CLIENTE_FK = ?");
            query.setParameter(1, co.getId_cliente_fk());

            return query.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(EjbCatCliente.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
      
    }

       
    public List<Object[]> SearchCorreosbyidClientPk(BigDecimal idClientepk)
    {
        System.out.println("EJB_BUSCA_CORREOS POR ID CLIENTE");
        
        try 
        {

            Query query = em.createNativeQuery("SELECT * FROM CORREOS WHERE ID_CLIENTE_FK = ?");
            query.setParameter(1, idClientepk);
            return  query.getResultList();

        } catch (Exception ex) {
            Logger.getLogger(EjbCatCliente.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }

       
    public int updateCorreos(Correos c) 
    {
        try 
        {
            System.out.println("Correo a modificar :"+c.toString());
            Query query = em.createNativeQuery("UPDATE CORREOS SET CORREO = ?, TIPO = ? WHERE ID_PK= ? ");
            query.setParameter(1, c.getCorreo());
            query.setParameter(2, c.getTipo());
            query.setParameter(3, c.getIdcorreo());
            return query.executeUpdate();
        } catch (Exception ex) {

            Logger.getLogger(EjbCatCorreos.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
}