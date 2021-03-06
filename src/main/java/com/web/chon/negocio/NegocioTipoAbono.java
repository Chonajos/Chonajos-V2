/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.negocio;

import com.web.chon.dominio.AbonoCredito;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author JesusAlfredo
 */
@Remote
public interface NegocioTipoAbono {
    
     public int insert(AbonoCredito tabono);
    
    public int update(AbonoCredito idtAbono);
    
    public int delete(BigDecimal idtAbono);
    
    public List<Object[]> getAll();
    
    public List<Object[]> getById(BigDecimal idtAbono) ;
}
