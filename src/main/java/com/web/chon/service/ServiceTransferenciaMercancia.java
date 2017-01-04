/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.service;

import com.web.chon.dominio.TransferenciaMercancia;
import com.web.chon.ejb.EjbTransferenciaMercancia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author marcogante
 */
@Service
public class ServiceTransferenciaMercancia implements IfaceTransferenciaMercancia{
    @Autowired
    EjbTransferenciaMercancia ejb;
        
        
        
    
    @Override
    public int insertTransferenciaMercancia(TransferenciaMercancia tm) {
        
        return ejb.insertaTransferencia(tm);
    }
    
    
}
