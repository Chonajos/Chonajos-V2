/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.ejb;

import com.web.chon.negocio.NegocioConceptos;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author JesusAlfredo
 */
@Repository
public class EjbConceptos  {
    @PersistenceContext
    private EntityManager em;

       
    public List<Object[]> getConceptosByTipoOperacion(BigDecimal idTipoOperacionFk)
    {
        Query query = em.createNativeQuery("select * from CONCEPTOS where ID_TIPO_OPERACION_FK = ?");
        query.setParameter(1, idTipoOperacionFk);
        return query.getResultList();
    }

       
    public List<Object[]> getConceptos() {
       Query query = em.createNativeQuery("select * from CONCEPTOS");
        return query.getResultList();
    }
}
