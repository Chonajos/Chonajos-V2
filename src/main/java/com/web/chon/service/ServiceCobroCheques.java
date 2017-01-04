/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.service;

import com.web.chon.dominio.CobroCheques;
import com.web.chon.ejb.EjbCobroCheques;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author JesusAlfredo
 */
@Service
public class ServiceCobroCheques implements IfaceCobroCheques{
    
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
