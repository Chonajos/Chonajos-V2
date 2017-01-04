package com.web.chon.ejb;

import com.web.chon.dominio.TipoCredito;

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
public class EjbTipoCredito   {

    @PersistenceContext
    private EntityManager em;

       
    public int insert(TipoCredito tipoCredito) {

        try {
            
            Query query = em.createNativeQuery("INSERT INTO  TIPO_CREDITO (ID_TIPO_CREDITO_PK,VALOR_CREDITO ,DESCRIPCION) VALUES(S_TIPO_EMPAQUE.nextval,?,?)");
            query.setParameter(1, tipoCredito.getValorCredito());
            query.setParameter(2, tipoCredito.getDescripcion());
            return query.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(EjbTipoCredito.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

       
    public int update(TipoCredito tipoCredito) {
         try {
            
            Query query = em.createNativeQuery("UPDATE TIPO_CREDITO SET VALOR_CREDITO =? ,DESCRIPCION =? WHERE ID_TIPO_CREDITO_PK = ?");
            return query.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(EjbTipoCredito.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

       
    public int delete(BigDecimal idTipoCredito) {
         try {
            
            Query query = em.createNativeQuery("DELETE TIPO_CREDITO WHERE ID_TIPO_CREDITO_PK = ?");

            query.setParameter(1, idTipoCredito);

            return query.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(EjbTipoCredito.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

       
    public List<Object[]> getAll() {
         try {
            
            Query query = em.createNativeQuery("SELECT ID_TIPO_CREDITO_PK,VALOR_CREDITO ,DESCRIPCION  FROM TIPO_CREDITO ORDER BY VALOR_CREDITO");
            List<Object[]> resultList = null;
            resultList = query.getResultList();

            return resultList;

        } catch (Exception ex) {
            Logger.getLogger(EjbTipoCredito.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

       
    public List<Object[]> getById(BigDecimal idTipoCredito) {
         try {
            
            Query query = em.createNativeQuery("SELECT ID_TIPO_CREDITO_PK,VALOR_CREDITO ,DESCRIPCION  FROM TIPO_CREDITO WHERE ID_TIPO_CREDITO_PK = ?");
            List<Object[]> resultList = null;
            query.setParameter(1, idTipoCredito);
            resultList = query.getResultList();

            return resultList;

        } catch (Exception ex) {
            Logger.getLogger(EjbTipoCredito.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
