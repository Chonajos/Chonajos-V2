/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.ejb;

import com.web.chon.dominio.TransferenciaMercancia;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author marcogante
 */
@Repository
public class EjbTransferenciaMercancia   {

    @PersistenceContext
    private EntityManager em;

       
    public int insertaTransferencia(TransferenciaMercancia tm) {
        System.out.println("EJB_INSERTA_TRANSFERENCIA" + tm.toString());
        try {
            System.out.println("Transferencia: " + tm);
            Query query = em.createNativeQuery("INSERT INTO TRANSFERENCIA_MERCANCIA (ID_TRANSFERENCIA_PK,ID_EXISTENCIA_PRODUCTO_FK,CANTIDAD_ANTERIOR,KILOS_ANTERIOR,CANTIDAD_MOVIDA,KILOS_MOVIDOS,ID_BODEGA_NUEVA_FK,FECHA_MOVIMIENTO,ID_USUARIO_FK,COMENTARIOS)VALUES (S_TRANSFERENCIA_MERCANCIA.NextVal,?,?,?,?,?,?,sysdate,?,?)");
        
            query.setParameter(1, tm.getIdExistenciaProductoFK());
            query.setParameter(2, tm.getCantidadAnterior());
            query.setParameter(3, tm.getKilosAnterior());
            query.setParameter(4, tm.getCantidadMovida());
            query.setParameter(5, tm.getKilosMovios());
            query.setParameter(6, tm.getIdBodegaDestino());
            query.setParameter(7, tm.getIdUsuarioFK());
            query.setParameter(8, tm.getComentarios());
  

            return query.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(EjbTransferenciaMercancia.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

}
