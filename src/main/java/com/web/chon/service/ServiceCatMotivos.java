package com.web.chon.service;

import com.web.chon.dominio.Motivos;
import com.web.chon.ejb.EjbCatMotivos;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author freddy
 */
@Service
@Transactional
public class ServiceCatMotivos implements IfaceCatMotivos {

    @Autowired
    EjbCatMotivos ejb;

    @Override
    public ArrayList<Motivos> getMotivos() {
        try {
            ArrayList<Motivos> lista_motivos = new ArrayList<Motivos>();
            List<Object[]> lstObject = ejb.getMotivos();

            for (Object[] obj : lstObject) {
                Motivos mov = new Motivos();
                mov.setId_motivo(Integer.parseInt(obj[0].toString()));
                mov.setNombre_motivo(obj[1] == null ? "" : obj[1].toString());
                mov.setComentarios(obj[2] == null ? "" : obj[2].toString());
                lista_motivos.add(mov);

            }
            return lista_motivos;
        } catch (Exception ex) {
            Logger.getLogger(ServiceCatMotivos.class.getName()).log(Level.SEVERE, null, ex);
            return null;

        }
    }

}
