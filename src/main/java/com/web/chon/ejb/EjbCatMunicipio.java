/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.ejb;

import com.web.chon.dominio.Municipios;
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
public class EjbCatMunicipio   {
    
    @PersistenceContext
    private EntityManager em;

       
    public List<Object[]> getMunicipios(int idEntidad) 
    {
    try {

            System.out.println("EJB_GET_MUNICIPIOS");
            Query query = em.createNativeQuery("SELECT * FROM MUNICIPIOS WHERE ID_ENTIDAD_FK=? order by nombre_municipio");
            query.setParameter(1, idEntidad);
            List<Object[]> resultList = null;
            resultList = query.getResultList();
            return resultList;
        } catch (Exception ex) 
        {
            Logger.getLogger(EjbCatEntidad.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
            

       
    public Object[] getMunicipioById(int idMunicipio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

       
    public int deleteMunicipio(int idMunicipio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

       
    public int updateMunicipio(Municipios muni) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

       
    public int insertMunicipio(Municipios muni) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
