package com.web.chon.service;

import com.web.chon.dominio.Caja;
import com.web.chon.ejb.EjbCaja;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author JesusAlfredo
 */
@Service
@Transactional
public class ServiceCaja implements IfaceCaja {

    @Autowired
    EjbCaja ejb;

    @Override
    public int insertCaja(Caja c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateCaja(Caja c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Caja getCajaByIdPk(BigDecimal idCajaPk) {

        List<Object[]> object = ejb.getCajaByIdPk(idCajaPk);
        Caja caja = new Caja();
        for (Object[] obj : object) {
            caja.setIdCajaPk(obj[0] == null ? null : new BigDecimal(obj[0].toString()));
            caja.setIdSucursalFk(obj[1] == null ? null : new BigDecimal(obj[1].toString()));
            caja.setNombre(obj[2] == null ? "" : obj[2].toString());
            caja.setMontoInicial(obj[3] == null ? null : new BigDecimal(obj[3].toString()));
            caja.setIdUsuarioFK(obj[4] == null ? null : new BigDecimal(obj[4].toString()));
        }
        return caja;
    }

    @Override
    public Caja getCajaByIdUsuarioPk(BigDecimal idUsuarioPk) {

        List<Object[]> object = ejb.getCajaByIdUsuarioPk(idUsuarioPk);
        Caja caja = new Caja();
        for (Object[] obj : object) {
            caja.setIdCajaPk(obj[0] == null ? null : new BigDecimal(obj[0].toString()));
            caja.setIdSucursalFk(obj[1] == null ? null : new BigDecimal(obj[1].toString()));
            caja.setNombre(obj[2] == null ? "" : obj[2].toString());
            caja.setMontoInicial(obj[3] == null ? null : new BigDecimal(obj[3].toString()));
            caja.setIdUsuarioFK(obj[4] == null ? null : new BigDecimal(obj[4].toString()));
        }
        return caja;

    }

    @Override
    public ArrayList<Caja> getCajas() {

        ArrayList<Caja> listaCajas = new ArrayList<Caja>();
        List<Object[]> lstObject = ejb.getCajas();
        for (Object[] obj : lstObject) {
            Caja caja = new Caja();
            caja.setIdCajaPk(obj[0] == null ? null : new BigDecimal(obj[0].toString()));
            caja.setIdSucursalFk(obj[1] == null ? null : new BigDecimal(obj[1].toString()));
            caja.setNombre(obj[2] == null ? "" : obj[2].toString());
            caja.setMontoInicial(obj[3] == null ? null : new BigDecimal(obj[3].toString()));
            caja.setIdUsuarioFK(obj[4] == null ? null : new BigDecimal(obj[4].toString()));
            listaCajas.add(caja);
        }
        return listaCajas;
    }

    @Override
    public ArrayList<Caja> getSucursalesByIdCaja(BigDecimal idCajaFk) {

        ArrayList<Caja> listaCajas = new ArrayList<Caja>();
        List<Object[]> lstObject = ejb.getSucursalesByIdCaja(idCajaFk);
        for (Object[] obj : lstObject) {
            Caja caja = new Caja();
            caja.setIdSucursalFk(obj[0] == null ? null : new BigDecimal(obj[0].toString()));
            caja.setNombre(obj[1] == null ? "" : obj[1].toString());
            listaCajas.add(caja);
        }
        return listaCajas;

    }

}
