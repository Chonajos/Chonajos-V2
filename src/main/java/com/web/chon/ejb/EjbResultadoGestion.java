package com.web.chon.ejb;

import com.web.chon.dominio.ResultadoGestion;

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
 * @author juan
 */
@Repository
public class EjbResultadoGestion  {

    @PersistenceContext
    private EntityManager em;

       
    public int insert(ResultadoGestion resultadoGestion) {

        try {

            Query query = em.createNativeQuery("INSERT INTO RESULTADO_GESTION(ID_RESULTADO_GESTION_PK,DESCRIPCION) VALUES(S_RESULTADO_GESTION.NextVal,?)");
            query.setParameter(1, resultadoGestion.getDescripcion());

            return query.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(EjbResultadoGestion.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

       
    public int update(ResultadoGestion resultadoGestion) {
        try {

            Query query = em.createNativeQuery("UPDATE RESULTADO_GESTION SET DESCRIPCION =? WHERE ID_RESULTADO_GESTION_PK = ?");
            query.setParameter(1, resultadoGestion.getDescripcion());
            query.setParameter(2, resultadoGestion.getIdResultadoGestion());

            return query.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(EjbResultadoGestion.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

       
    public int delete(BigDecimal idResultadoGestion) {
        try {

            Query query = em.createNativeQuery("DELETE RESULTADO_GESTION WHERE ID_RESULTADO_GESTION_PK  = ?");

            query.setParameter(1, idResultadoGestion);

            return query.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(EjbResultadoGestion.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

       
    public List<Object[]> getAll() {
        try {

            Query query = em.createNativeQuery("SELECT ID_RESULTADO_GESTION_PK ,DESCRIPCION FROM RESULTADO_GESTION");
            List<Object[]> resultList = null;
            resultList = query.getResultList();

            return resultList;

        } catch (Exception ex) {
            Logger.getLogger(EjbResultadoGestion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

       
    public int getNextVal() {
        Query query = em.createNativeQuery("SELECT S_RESULTADO_GESTION.nextVal FROM DUAL");
        return Integer.parseInt(query.getSingleResult().toString());
    }

}
