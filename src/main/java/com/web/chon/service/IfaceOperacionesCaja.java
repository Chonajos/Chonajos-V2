/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.service;

import com.web.chon.dominio.OperacionesCaja;
import com.web.chon.dominio.TipoOperacion;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JesusAlfredo
 */
public interface IfaceOperacionesCaja {
    public int insertaOperacion(OperacionesCaja es);
    public int updateOperacion(OperacionesCaja es);
    public int updateStatus(BigDecimal idOperacionPk,BigDecimal idStatusFk);
    public int updateCorte(BigDecimal idOperacionPk,BigDecimal idCorteFk);
    public OperacionesCaja getOperacionByIdPk(BigDecimal idPk);
    public ArrayList<OperacionesCaja> getOperacionesBy(BigDecimal idCajaFk,BigDecimal idOperacionFk,BigDecimal idConceptoFk,String fechaInicio, String fechaFin,BigDecimal idStatusFk,BigDecimal idUserFk);
    public ArrayList<OperacionesCaja> getTransferenciasEntrantes(BigDecimal idCajaFk);
    public ArrayList<TipoOperacion> getOperacionesCorteBy(BigDecimal idCajaFk, BigDecimal idUserFk, BigDecimal idES);
     public ArrayList<OperacionesCaja> getOperaciones(BigDecimal idCajaFk, BigDecimal idUserFk);
    public ArrayList<OperacionesCaja> getCheques(BigDecimal idCajaFk, BigDecimal idUserFk,BigDecimal idINOUT);
     public int getNextVal();
}
