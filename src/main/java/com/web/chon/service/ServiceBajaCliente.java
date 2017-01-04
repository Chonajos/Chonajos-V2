/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.service;

import com.web.chon.dominio.BajaClientes;
import com.web.chon.ejb.EjbBajaClientes;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author marcogante
 */
@Service
public class ServiceBajaCliente implements IfaceBajaCliente
{
    @Autowired
    EjbBajaClientes ejb;

    @Override
    public int insertCliente(BajaClientes cliente) 
    {
        return ejb.insertCliente(cliente);

    }

    @Override
    public int deleteCliente(BigDecimal idCliente) 
    {
        
       return ejb.deleteClienteBajas(idCliente);
       
    }
    
}
