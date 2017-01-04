package com.web.chon.service;

import com.web.chon.dominio.ConceptosES;
import com.web.chon.ejb.EjbConceptos;
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

public class ServiceConceptos implements IfaceConceptos {
    @Autowired
    EjbConceptos ejb;
     
    
    @Override
    public ArrayList<ConceptosES> getConceptosByTipoOperacion(BigDecimal idTipoOperacionFk) {
        
           
        ArrayList<ConceptosES> lstConceptos= new ArrayList<ConceptosES>();
        List<Object[]> lstObject = new ArrayList<Object[]>();
        lstObject = ejb.getConceptosByTipoOperacion(idTipoOperacionFk);
        for (Object[] object : lstObject) {
            ConceptosES c = new ConceptosES();
            c.setIdConceptoPk(object[0] == null ? null : new BigDecimal(object[0].toString()));
            c.setIdTipoOperacionFk(object[1] == null ? null : new BigDecimal(object[1].toString()));
            c.setNombre(object[2] == null ? null : object[2].toString());
            c.setDescripcion(object[3] == null ? null : object[3].toString());
            lstConceptos.add(c);
        }
        return lstConceptos;
        
        
        
    }

    @Override
    public ArrayList<ConceptosES> getConceptos() {
           
        ArrayList<ConceptosES> lstConceptos= new ArrayList<ConceptosES>();
        List<Object[]> lstObject = new ArrayList<Object[]>();
        lstObject = ejb.getConceptos();
        for (Object[] object : lstObject) {
            ConceptosES c = new ConceptosES();
            c.setIdConceptoPk(object[0] == null ? null : new BigDecimal(object[0].toString()));
            c.setIdTipoOperacionFk(object[1] == null ? null : new BigDecimal(object[1].toString()));
            c.setNombre(object[2] == null ? null : object[2].toString());
            c.setDescripcion(object[3] == null ? null : object[3].toString());
            lstConceptos.add(c);
        }
        return lstConceptos;
    }
    
}
