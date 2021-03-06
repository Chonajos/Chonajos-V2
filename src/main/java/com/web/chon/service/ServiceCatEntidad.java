package com.web.chon.service;

import com.web.chon.dominio.Entidad;
import com.web.chon.ejb.EjbCatEntidad;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author freddy
 */
@Service
@Transactional
public class ServiceCatEntidad implements IfaceCatEntidad {

    @Autowired
    EjbCatEntidad ejb;

    @Override
    public ArrayList<Entidad> getEntidades() {
        try {
            ArrayList<Entidad> lista_entidades = new ArrayList<Entidad>();
            List<Object[]> lstObject = ejb.getEntidades();

            for (Object[] obj : lstObject) {
                Entidad enti = new Entidad();
                enti.setIdEntidadPk(obj[0] == null ? null : new BigDecimal(obj[0].toString()));
                enti.setNombreEntidad(obj[1].toString());
                lista_entidades.add(enti);

            }
            return lista_entidades;
        } catch (Exception ex) {

            Logger.getLogger(ServiceCatEntidad.class.getName()).log(Level.SEVERE, null, ex);
            return null;

        }
    }

    @Override
    public Entidad getEntidadById(int idEntidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteEntidad(int idEntidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateEntidad(Entidad enti) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insertEntidad(Entidad enti) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
