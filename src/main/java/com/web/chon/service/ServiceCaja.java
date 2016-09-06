/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.service;

import com.web.chon.dominio.Caja;
import com.web.chon.negocio.NegocioCaja;
import com.web.chon.util.Utilidades;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author JesusAlfredo
 */
@Service
public class ServiceCaja implements IfaceCaja {

    NegocioCaja ejb;

    private void getEjb() {
        try {
            if (ejb == null) {
                ejb = (NegocioCaja) Utilidades.getEJBRemote("ejbCaja", NegocioCaja.class.getName());
            }

        } catch (Exception ex) {
            Logger.getLogger(ServiceCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int insertCaja(Caja c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateCaja(Caja c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateMontoCaja(Caja c) {
        getEjb();
        return ejb.updateMontoCaja(c);

    }

    @Override
    public ArrayList<Caja> getCajas(BigDecimal idSucursalFk, BigDecimal tipo) {
        getEjb();
        List<Object[]> lstObject = new ArrayList<Object[]>();
        lstObject = ejb.getCajas(idSucursalFk, tipo);
        ArrayList<Caja> listaCajas = new ArrayList<Caja>();

        for (Object[] obj : lstObject) {
            Caja caja = new Caja();

            caja.setIdCajaPk(obj[0] == null ? null : new BigDecimal(obj[0].toString()));
            caja.setIdSucursalFk(obj[1] == null ? null : new BigDecimal(obj[1].toString()));
            caja.setNombre(obj[2] == null ? "" : obj[2].toString());
            caja.setTipo(obj[3] == null ? null : new BigDecimal(obj[3].toString()));
            caja.setCuenta(obj[4] == null ? null : new BigDecimal(obj[4].toString()));
            caja.setMonto(obj[5] == null ? null : new BigDecimal(obj[5].toString()));
            listaCajas.add(caja);
        }
        return listaCajas;

    }

    @Override
    public ArrayList<Caja> getCajasByIdPk(BigDecimal idCajaPk) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Caja getCajaByIdUsuarioPk(BigDecimal idUsuarioPk, BigDecimal tipo) {
        getEjb();
        List<Object[]> object = ejb.getCajaByIdUsuarioPk(idUsuarioPk, tipo);
        Caja caja = new Caja();
        for (Object[] obj : object) {
            caja.setIdCajaPk(obj[0] == null ? null : new BigDecimal(obj[0].toString()));
            caja.setIdSucursalFk(obj[1] == null ? null : new BigDecimal(obj[1].toString()));
            caja.setNombre(obj[2] == null ? "" : obj[2].toString());
            caja.setTipo(obj[3] == null ? null : new BigDecimal(obj[3].toString()));
            caja.setCuenta(obj[4] == null ? null : new BigDecimal(obj[4].toString()));
            caja.setMonto(obj[5] == null ? null : new BigDecimal(obj[5].toString()));
            caja.setIdUsuarioFK(obj[6] == null ? null : new BigDecimal(obj[6].toString()));
            caja.setMontoMenudeo(obj[7] == null ? null : new BigDecimal(obj[7].toString()));
            caja.setMontoMayoreo(obj[8] == null ? null : new BigDecimal(obj[8].toString()));
            caja.setMontoCredito(obj[9] == null ? null : new BigDecimal(obj[9].toString()));
        }
        return caja;

    }

}
