package com.web.chon.service;

import com.web.chon.dominio.OperacionesCuentas;
import com.web.chon.ejb.EjbOperacionesCuentas;
import java.math.BigDecimal;
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
public class ServiceOperacionesCuentas implements IfaceOperacionesCuentas {

    @Autowired
    EjbOperacionesCuentas ejb;

    @Override
    public int insertaOperacion(OperacionesCuentas es) {

        return ejb.insertaOperacion(es);

    }

    @Override
    public int updateOperacion(OperacionesCuentas es) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object[]> getOperacionesByIdCuenta(BigDecimal idCuentaFk) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNextVal() {

        return ejb.getNextVal();
    }

}
