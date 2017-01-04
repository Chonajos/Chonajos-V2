package com.web.chon.service;

import com.web.chon.dominio.CobroCheques;
import com.web.chon.ejb.EjbCobroCheques;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author JesusAlfredo
 */
@Service
@Transactional
public class ServiceCobroCheques implements IfaceCobroCheques {

    @Autowired
    EjbCobroCheques ejb;

    @Override
    public int insertarDocumento(CobroCheques cc) {

        return ejb.insertarDocumento(cc);
    }

    @Override
    public int nextVal() {

        return ejb.nextVal();
    }
}
