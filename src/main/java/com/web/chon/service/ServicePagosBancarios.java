package com.web.chon.service;

import com.web.chon.dominio.PagosBancarios;
import com.web.chon.ejb.EjbPagoBancario;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author freddy
 */
@Service
@Transactional
public class ServicePagosBancarios implements IfacePagosBancarios {

    @Autowired
    EjbPagoBancario ejb;

    @Override
    public int insertaPagoBancario(PagosBancarios pb) {

        return ejb.insertaPagoBancario(pb);
    }

    @Override
    public int updatePagoBancario(PagosBancarios pb) {

        return ejb.updatePagoBancario(pb);

    }

    @Override
    public ArrayList<PagosBancarios> getPagosPendientes() {

        BigDecimal i = new BigDecimal(1);
        ArrayList<PagosBancarios> listaOperaciones = new ArrayList<PagosBancarios>();
        List<Object[]> lstObject = ejb.getPagosPendientes();
        for (Object[] obj : lstObject) {
            PagosBancarios op = new PagosBancarios();
            op.setIdTransBancariasPk(obj[0] == null ? null : new BigDecimal(obj[0].toString()));
            op.setIdCajaFk(obj[1] == null ? null : new BigDecimal(obj[1].toString()));
            op.setIdConceptoFk(obj[2] == null ? null : new BigDecimal(obj[2].toString()));
            op.setIdTipoFk(obj[3] == null ? null : new BigDecimal(obj[3].toString()));
            op.setComentarios(obj[4] == null ? null : obj[4].toString());
            op.setIdUserFk(obj[5] == null ? null : new BigDecimal(obj[5].toString()));
            op.setMonto(obj[6] == null ? null : new BigDecimal(obj[6].toString()));
            op.setFecha(obj[7] == null ? null : (Date) obj[7]);
            op.setIdStatusFk(obj[8] == null ? null : new BigDecimal(obj[8].toString()));
            op.setFechaTranferencia((obj[9] == null ? null : (Date) obj[9]));
            op.setFolioElectronico(obj[10] == null ? null : new BigDecimal(obj[10].toString()));
            op.setFechaDeposito((obj[11] == null ? null : (Date) obj[11]));
            op.setIdCuentaFk(obj[12] == null ? null : new BigDecimal(obj[12].toString()));
            op.setConcepto(obj[13] == null ? null : obj[13].toString());
            op.setReferencia(obj[14] == null ? null : obj[14].toString());
            op.setIdOperacionCajaFk(obj[15] == null ? null : new BigDecimal(obj[15].toString()));
            op.setNombreCaja(obj[16] == null ? null : obj[16].toString());
            op.setNombreConcepto(obj[17] == null ? "" : obj[17].toString());
            op.setNombreTipoAbono(obj[18] == null ? "" : obj[18].toString());
            op.setNombreUsuario(obj[19] == null ? null : obj[19].toString());
            op.setNombreBanco(obj[20] == null ? null : obj[20].toString());
            op.setNumero(i);
            i = i.add(new BigDecimal(1), MathContext.UNLIMITED);

            if (op.getIdStatusFk().intValue() == 1) {
                op.setNombreStatus("APLICADO");
            } else {
                op.setNombreStatus("PENDIENTE");
            }

            listaOperaciones.add(op);
        }
        return listaOperaciones;

    }

    @Override
    public int getNextVal() {

        return ejb.getNextVal();
    }

}
