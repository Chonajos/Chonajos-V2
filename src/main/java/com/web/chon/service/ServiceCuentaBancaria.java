package com.web.chon.service;

import com.web.chon.dominio.CuentaBancaria;
import com.web.chon.ejb.EjbCuentaBancaria;
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
public class ServiceCuentaBancaria implements IfaceCuentasBancarias {

    @Autowired
    EjbCuentaBancaria ejb;

    @Override
    public ArrayList<CuentaBancaria> getCuentas() {

        ArrayList<CuentaBancaria> lstCuentas = new ArrayList<CuentaBancaria>();
        List<Object[]> lstObject = new ArrayList<Object[]>();
        lstObject = ejb.getCuentas();
        for (Object[] object : lstObject) {
            CuentaBancaria cuenta = new CuentaBancaria();
            cuenta.setIdCuentaBancariaPk(object[0] == null ? null : new BigDecimal(object[0].toString()));
            cuenta.setNombreBanco(object[1] == null ? null : object[1].toString());
            cuenta.setCuenta(object[2] == null ? null : new BigDecimal(object[2].toString()));
            cuenta.setIdUsuarioFk(object[3] == null ? null : new BigDecimal(object[3].toString()));
            lstCuentas.add(cuenta);
        }
        return lstCuentas;
    }
}
