/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.ejb;

import com.web.chon.dominio.EntradaSalida;
import com.web.chon.negocio.NegocioEntradaSalida;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author JesusAlfredo
 */
@Stateless(mappedName = "ejbEntradaSalida")
public class EjbEntradaSalida implements NegocioEntradaSalida
{
    @PersistenceContext(unitName = "persistenceJR")
    EntityManager em;

    @Override
    public List<Object[]> getMovimientosByIdCaja(BigDecimal idCaja, String fechaInicio, String fechaFin) {
      Query query = em.createNativeQuery("select entsal.*,c.NOMBRE,concepto.NOMBRE from ENTRADA_SALIDA entsal\n" +
"inner join caja c on c.ID_CAJA_PK = entsal.ID_CAJA_FK\n" +
"inner join CONCEPTOS_ENTRADA_SALIDA concepto on concepto.ID_CONCEPTO_PK= entsal.ID_CONCEPTO_FK");
        return query.getResultList();
    }

    @Override
    public int insertaMovimiento(EntradaSalida es) {
        try {
            
            System.out.println("ejb insert"+es.toString());
            Query query = em.createNativeQuery("INSERT INTO ENTRADA_SALIDA (ID_ENTRADA_SALIDA_PK,ID_CAJA_FK,TIPOES,FECHA,ID_CONCEPTO_FK,COMENTARIOS,MONTO) values(?,?,?,sysdate,?,?,?)");
            query.setParameter(1, es.getIdEntradaSalidaPk());
            query.setParameter(2, es.getIdCajaFk());
            query.setParameter(3, es.getTipoES());
            query.setParameter(4, es.getIdConceptoFk());
            query.setParameter(5, es.getComentarios());
            query.setParameter(6, es.getMonto());

            return query.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(EjbEmpaque.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    
    
    }

    @Override
    public int getNextVal() {
        try {
            Query query = em.createNativeQuery("select S_ENTRADA_SALIDA.nextval from dual");
            return Integer.parseInt(query.getSingleResult().toString());
        } catch (Exception ex) {
            System.out.println("error >" + ex.getMessage());
            return 0;
        }
    
    }
    
}
