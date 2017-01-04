package com.web.chon.service;

import com.web.chon.dominio.BajaClientes;
import com.web.chon.ejb.EjbBajaClientes;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marcogante
 */
@Service
@Transactional
public class ServiceBajaCliente implements IfaceBajaCliente {

    @Autowired
    EjbBajaClientes ejb;

    @Override
    public int insertCliente(BajaClientes cliente) {
        return ejb.insertCliente(cliente);

    }

    @Override
    public int deleteCliente(BigDecimal idCliente) {

        return ejb.deleteClienteBajas(idCliente);

    }

}
