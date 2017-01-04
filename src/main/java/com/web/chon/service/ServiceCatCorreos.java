/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.service;
import com.web.chon.dominio.Correos;
import com.web.chon.ejb.EjbCatCorreos;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author freddy
 */
@Service
public class ServiceCatCorreos implements IfaceCatCorreos {

    @Autowired
    EjbCatCorreos ejb;

    @Override
    public int insertCorreo(Correos co) {
        
        return ejb.insertCorreo(co);
    }

    @Override
    public int deleteCorreos(Correos co) {
       
        return ejb.deleteCorreos(co);
    }

    @Override
    public ArrayList<Correos> SearchCorreosbyidClientPk(BigDecimal idClientepk) {
    
            ArrayList<Correos> lista_correos = new ArrayList<Correos>();
            List<Object[]> lstObject = ejb.SearchCorreosbyidClientPk(idClientepk);
            for (Object[] obj : lstObject) {
                Correos c = new Correos();
                c.setIdcorreo(Integer.parseInt(obj[0].toString()));
                c.setId_cliente_fk(obj[1] == null ? null : new BigDecimal(obj[1].toString()));
                c.setCorreo(obj[2] == null ? "" : obj[2].toString());
                c.setTipo(obj[3] == null ? "" : obj[3].toString());
                lista_correos.add(c);
            }
            return lista_correos;
    }

    @Override
    public int updateCorreos(Correos co) 
    {
        if(co.getIdcorreo()==0)
        {
           
            return ejb.insertCorreo(co);
        }
        
        return ejb.updateCorreos(co);
    }

}
