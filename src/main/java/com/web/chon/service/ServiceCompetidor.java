package com.web.chon.service;

import com.web.chon.dominio.Competidor;
import com.web.chon.ejb.EjbCompetidores;
import java.math.BigDecimal;
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
public class ServiceCompetidor implements IfaceCompetidores {

    @Autowired
    EjbCompetidores ejb;

    @Override
    public ArrayList<Competidor> getCometidores() {

        List<Object[]> lstObject = new ArrayList<Object[]>();
        ArrayList<Competidor> lstCompetidores = new ArrayList<Competidor>();
        lstObject = ejb.getCometidores();
        for (Object[] obj : lstObject) {
            Competidor dominio = new Competidor();
            dominio.setIdCompetidorPk(obj[0] == null ? null : new BigDecimal(obj[0].toString()));;
            dominio.setNombreCompetidor(obj[1] == null ? "" : obj[1].toString());
            dominio.setUbicacion(obj[2] == null ? "" : obj[2].toString());
            lstCompetidores.add(dominio);
        }
        return lstCompetidores;

    }

    @Override
    public int getNextVal() {

        return ejb.getNextVal();
    }

    @Override
    public int insertCompetidor(Competidor c) {

        return ejb.insertCompetidor(c);
    }

    @Override
    public int updateCompetidor(Competidor c) {

        return ejb.updateCompetidor(c);
    }

    @Override
    public Competidor getCometidoresById(BigDecimal idCompetidor) {

        try {
            Competidor c = new Competidor();
            List<Object[]> object = ejb.getCometidoresById(idCompetidor);
            for (Object[] obj : object) {
                c.setIdCompetidorPk(obj[0] == null ? null : new BigDecimal(obj[0].toString()));
                c.setNombreCompetidor(obj[1] == null ? "" : obj[1].toString());
                c.setUbicacion(obj[2] == null ? "" : obj[2].toString());
            }

            return c;
        } catch (Exception ex) {
            Logger.getLogger(ServiceCompetidor.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

}
