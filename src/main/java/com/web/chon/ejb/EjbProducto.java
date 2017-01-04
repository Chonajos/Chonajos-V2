package com.web.chon.ejb;

import com.web.chon.dominio.Producto;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 * Ejb para el catalogo de categorias
 *
 * @author Juan de la Cruz
 */
@Repository
public class EjbProducto {

    @PersistenceContext
    private EntityManager em;

    public List<Object[]> getProductos() {
        try {

            Query query = em.createNativeQuery("SELECT * FROM PRODUCTO ORDER BY ID_PRODUCTO_PK ASC");
            List<Object[]> resultList = null;
            resultList = query.getResultList();

            return resultList;

        } catch (Exception ex) {
            Logger.getLogger(EjbProducto.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public int deleteProducto(String idProducto) {
        try {
            Query query = em.createNativeQuery("delete Producto where ID_PRODUCTO_PK = ?");
            query.setParameter(1, idProducto);

            return query.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(EjbProducto.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

    }

    public int insertarProducto(Producto producto) {
        try {
            Query query = em.createNativeQuery("insert into PRODUCTO (ID_PRODUCTO_PK,NOMBRE_PRODUCTO,DESCRIPCION_PRODUCTO) values(?,?,?)");
            query.setParameter(1, producto.getIdProductoPk());
            query.setParameter(2, producto.getNombreProducto());
            query.setParameter(3, producto.getDescripcionProducto());

            return query.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(EjbProducto.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

    }

    public int updateProducto(Producto producto) {
        try {

            Query query = em.createNativeQuery("update Producto set NOMBRE_PRODUCTO = ?,DESCRIPCION_PRODUCTO = ?  where ID_PRODUCTO_PK = ?");
            query.setParameter(1, producto.getNombreProducto());
            query.setParameter(2, producto.getDescripcionProducto());
            query.setParameter(3, producto.getIdProductoPk());

            return query.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(EjbProducto.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public int getLastIdCategoria() {
        Query query = em.createNativeQuery("SELECT MAX(ID_PRODUCTO_PK)+1 ID_PRODUCTO_PK FROM PRODUCTO");
        String lastId = query.getSingleResult().toString();
        return Integer.parseInt(lastId);
    }

}
