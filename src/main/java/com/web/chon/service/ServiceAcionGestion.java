package com.web.chon.service;

import com.web.chon.dominio.AcionGestion;
import com.web.chon.ejb.EjbAcionGestion;
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
 * @author Juan de la Cruz
 */
@Service
@Transactional
public class ServiceAcionGestion implements IfaceAcionGestion {

    @Autowired
    EjbAcionGestion ejb;

    @Override
    public int update(AcionGestion acionGestion) {

        return ejb.update(acionGestion);
    }

    @Override
    public int insert(AcionGestion acionGestion) {

        return ejb.insert(acionGestion);
    }

    @Override
    public int delete(BigDecimal idAcionGestion) {

        return ejb.delete(idAcionGestion);
    }

    @Override
    public ArrayList<AcionGestion> getAll() {

        ArrayList<AcionGestion> lstAcionGestion = new ArrayList<AcionGestion>();
        List<Object[]> lstObject = new ArrayList<Object[]>();
        lstObject = ejb.getAll();
        for (Object[] object : lstObject) {
            AcionGestion acionGestion = new AcionGestion();

            acionGestion.setIdAcionGestion(object[0] == null ? null : new BigDecimal(object[0].toString()));
            acionGestion.setIdResultadoGestion(object[1] == null ? null : new BigDecimal(object[1].toString()));
            acionGestion.setDescripcion(object[2] == null ? null : object[2].toString());

            lstAcionGestion.add(acionGestion);
        }

        return lstAcionGestion;

    }

    @Override
    public AcionGestion getById(BigDecimal idAcionGestion) {

        List<Object[]> lstObject = new ArrayList<Object[]>();
        lstObject = ejb.getById(idAcionGestion);
        AcionGestion acionGestion = new AcionGestion();
        for (Object[] object : lstObject) {

            acionGestion.setIdAcionGestion(object[0] == null ? null : new BigDecimal(object[0].toString()));
            acionGestion.setIdResultadoGestion(object[1] == null ? null : new BigDecimal(object[1].toString()));
            acionGestion.setDescripcion(object[2] == null ? null : object[2].toString());
        }

        return acionGestion;
    }

    @Override
    public int getNextVal() {

        try {
            return ejb.getNextVal();

        } catch (Exception ex) {
            Logger.getLogger(ServiceAcionGestion.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public ArrayList<AcionGestion> getByIdResultadoGestion(BigDecimal idResultadoGestion) {

        List<Object[]> lstObject = new ArrayList<Object[]>();
        lstObject = ejb.getByIdResultadoGestion(idResultadoGestion);
        ArrayList<AcionGestion> lstAcionGestion = new ArrayList<AcionGestion>();
        for (Object[] object : lstObject) {

            AcionGestion acionGestion = new AcionGestion();
            acionGestion.setIdAcionGestion(object[0] == null ? null : new BigDecimal(object[0].toString()));
            acionGestion.setIdResultadoGestion(object[1] == null ? null : new BigDecimal(object[1].toString()));
            acionGestion.setDescripcion(object[2] == null ? null : object[2].toString());

            lstAcionGestion.add(acionGestion);
        }

        return lstAcionGestion;
    }

}
