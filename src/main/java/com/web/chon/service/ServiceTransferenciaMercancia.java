package com.web.chon.service;

import com.web.chon.dominio.TransferenciaMercancia;
import com.web.chon.ejb.EjbTransferenciaMercancia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marcogante
 */
@Service
@Transactional
public class ServiceTransferenciaMercancia implements IfaceTransferenciaMercancia {

    @Autowired
    EjbTransferenciaMercancia ejb;

    @Override
    public int insertTransferenciaMercancia(TransferenciaMercancia tm) {

        return ejb.insertaTransferencia(tm);
    }

}
