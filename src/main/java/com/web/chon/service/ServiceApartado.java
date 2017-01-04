package com.web.chon.service;

import com.web.chon.dominio.Apartado;
import com.web.chon.ejb.EjbApartado;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jramirez
 */
@Service
@Transactional
public class ServiceApartado implements IfaceApartado {

    @Autowired
    EjbApartado ejb;

    @Override
    public int insert(Apartado apartado) {

        return ejb.insert(apartado);
    }

    @Override
    public int getNextVal() {

        return ejb.getNextVal();
    }

    @Override
    public BigDecimal montoApartado(BigDecimal idVentaFk, BigDecimal idTipoFk) {

        return ejb.montoApartado(idVentaFk, idTipoFk);
    }

}
