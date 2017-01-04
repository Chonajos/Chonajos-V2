package com.web.chon.service;

import com.web.chon.dominio.TipoCredito;
import com.web.chon.ejb.EjbTipoCredito;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Juan de la Cruz
 */
@Service
@Transactional
public class ServiceTipoCredito implements IfaceTipoCredito {

    @Autowired
    EjbTipoCredito ejb;

    @Override
    public ArrayList<TipoCredito> getAll() {

        ArrayList<TipoCredito> lstTipoCredito = new ArrayList<TipoCredito>();
        List<Object[]> lstObject = new ArrayList<Object[]>();
        lstObject = ejb.getAll();
        for (Object[] object : lstObject) {
            TipoCredito tipoCredito = new TipoCredito();

            tipoCredito.setIdTipoCredito(object[0] == null ? null : new BigDecimal(object[0].toString()));
            tipoCredito.setValorCredito(object[1] == null ? null : new BigDecimal(object[1].toString()));
            tipoCredito.setDescripcion(object[2] == null ? null : new BigDecimal(object[2].toString()));

            lstTipoCredito.add(tipoCredito);
        }

        return lstTipoCredito;
    }

    @Override
    public TipoCredito getById(BigDecimal idTipoCredito) {

        List<Object[]> lstObject = new ArrayList<Object[]>();
        lstObject = ejb.getAll();
        TipoCredito tipoCredito = new TipoCredito();
        for (Object[] object : lstObject) {

            tipoCredito.setIdTipoCredito(object[0] == null ? null : new BigDecimal(object[0].toString()));
            tipoCredito.setValorCredito(object[1] == null ? null : new BigDecimal(object[1].toString()));
            tipoCredito.setDescripcion(object[2] == null ? null : new BigDecimal(object[2].toString()));

        }

        return tipoCredito;
    }

    @Override
    public int delete(BigDecimal idTipoCredito) {

        return ejb.delete(idTipoCredito);
    }

    @Override
    public int update(TipoCredito tipoCredito) {

        return ejb.update(tipoCredito);
    }

    @Override
    public int insert(TipoCredito tipoCredito) {

        return ejb.insert(tipoCredito);
    }

}
