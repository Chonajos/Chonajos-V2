/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.ejb;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author JesusAlfredo
 */
@Repository
public class EjbTipoOperacion   {
    @PersistenceContext
    private EntityManager em;

    
    public List<Object[]> getOperaciones() {
       Query query = em.createNativeQuery("select * from TIPOS_OPERACION");
        return query.getResultList();
    }
}
