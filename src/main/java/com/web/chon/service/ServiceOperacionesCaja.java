package com.web.chon.service;

import com.web.chon.dominio.OperacionesCaja;
import com.web.chon.dominio.TipoOperacion;
import com.web.chon.dominio.Usuario;
import com.web.chon.ejb.EjbOperacionesCaja;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author JesusAlfredo
 */
@Service
@Transactional
public class ServiceOperacionesCaja implements IfaceOperacionesCaja {

    @Autowired
    EjbOperacionesCaja ejb;

    @Override
    public int getNextVal() {

        return ejb.getNextVal();

    }

    @Override
    public int insertaOperacion(OperacionesCaja es) {

        return ejb.insertaOperacion(es);
    }

    @Override
    public int updateOperacion(OperacionesCaja es) {

        return ejb.updateOperacion(es);
    }

    @Override
    public OperacionesCaja getOperacionByIdPk(BigDecimal idPk) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<OperacionesCaja> getOperacionesBy(BigDecimal idCajaFk, BigDecimal idOperacionFk, BigDecimal idConceptoFk, String fechaInicio, String fechaFin, BigDecimal idStatusFk, BigDecimal idUserFk, BigDecimal idCorte, BigDecimal inout) {

        int i = 1;
        ArrayList<OperacionesCaja> listaOperaciones = new ArrayList<OperacionesCaja>();
        List<Object[]> lstObject = ejb.getOperacionesBy(idCajaFk, idOperacionFk, idConceptoFk, fechaInicio, fechaFin, idStatusFk, idUserFk, idCorte, inout);
        for (Object[] obj : lstObject) {
            OperacionesCaja op = new OperacionesCaja();
            op.setIdOperacionesCajaPk(obj[0] == null ? null : new BigDecimal(obj[0].toString()));
            op.setIdCorteCajaFk(obj[1] == null ? null : new BigDecimal(obj[1].toString()));
            op.setIdCajaFk(obj[2] == null ? null : new BigDecimal(obj[2].toString()));
            op.setIdCajaDestinoFk(obj[3] == null ? null : new BigDecimal(obj[3].toString()));
            op.setIdConceptoFk(obj[4] == null ? null : new BigDecimal(obj[4].toString()));
            op.setFecha(obj[5] == null ? null : (Date) obj[5]);
            op.setIdStatusFk(obj[6] == null ? null : new BigDecimal(obj[6].toString()));
            op.setIdUserFk(obj[7] == null ? null : new BigDecimal(obj[7].toString()));
            op.setComentarios(obj[8] == null ? null : obj[8].toString());
            op.setMonto(obj[9] == null ? null : new BigDecimal(obj[9].toString()));
            op.setEntradaSalida(obj[10] == null ? null : new BigDecimal(obj[10].toString()));
            op.setIdCuentaDestinoFk(obj[11] == null ? null : new BigDecimal(obj[11].toString()));
            op.setIdSucursalFk(obj[12] == null ? null : new BigDecimal(obj[12].toString()));
            op.setNombreCaja(obj[13] == null ? null : obj[13].toString());
            op.setNombreConcepto(obj[14] == null ? "" : obj[14].toString());
            op.setNombreOperacion(obj[15] == null ? null : obj[15].toString());
            op.setNombreUsuario(obj[16] == null ? null : obj[16].toString());
            op.setNombreSucursal(obj[17] == null ? null : obj[17].toString());
            op.setNumero(i);
            i += 1;
            if (op.getIdStatusFk().intValue() == 1) {
                op.setNombreStatus("APLICADO");
            } else {
                op.setNombreStatus("PENDIENTE");
            }
            if (op.getEntradaSalida().intValue() == 1) {
                op.setNombreEntradaSalida("E");
            } else {
                op.setNombreEntradaSalida("S");
            }
            listaOperaciones.add(op);
        }
        return listaOperaciones;

    }

    @Override
    public ArrayList<OperacionesCaja> getTransferenciasEntrantes(BigDecimal idCajaFk) {

        int i = 1;
        ArrayList<OperacionesCaja> listaOperaciones = new ArrayList<OperacionesCaja>();
        List<Object[]> lstObject = ejb.getTransferenciasEntrantes(idCajaFk);
        for (Object[] obj : lstObject) {
            OperacionesCaja op = new OperacionesCaja();
            op.setIdOperacionesCajaPk(obj[0] == null ? null : new BigDecimal(obj[0].toString()));
            op.setIdCorteCajaFk(obj[1] == null ? null : new BigDecimal(obj[1].toString()));
            op.setIdCajaFk(obj[2] == null ? null : new BigDecimal(obj[2].toString()));
            op.setIdCajaDestinoFk(obj[3] == null ? null : new BigDecimal(obj[3].toString()));
            op.setIdConceptoFk(obj[4] == null ? null : new BigDecimal(obj[4].toString()));
            op.setFecha(obj[5] == null ? null : (Date) obj[5]);
            op.setIdStatusFk(obj[6] == null ? null : new BigDecimal(obj[6].toString()));
            op.setIdUserFk(obj[7] == null ? null : new BigDecimal(obj[7].toString()));
            op.setComentarios(obj[8] == null ? null : obj[8].toString());
            op.setMonto(obj[9] == null ? null : new BigDecimal(obj[9].toString()));
            op.setEntradaSalida(obj[10] == null ? null : new BigDecimal(obj[10].toString()));
            op.setIdCuentaDestinoFk(obj[11] == null ? null : new BigDecimal(obj[11].toString()));
            op.setIdSucursalFk(obj[12] == null ? null : new BigDecimal(obj[12].toString()));

            op.setNombreCaja(obj[13] == null ? null : obj[13].toString());
            op.setNombreConcepto(obj[14] == null ? "" : obj[14].toString());
            op.setNombreOperacion(obj[15] == null ? null : obj[15].toString());
            op.setNombreUsuario(obj[16] == null ? null : obj[16].toString());
            op.setNumero(i);
            i += 1;
            if (op.getIdStatusFk().intValue() == 1) {
                op.setNombreStatus("APLICADO");
            } else {
                op.setNombreStatus("PENDIENTE");
            }
            if (op.getEntradaSalida().intValue() == 1) {
                op.setNombreEntradaSalida("E");
            } else {
                op.setNombreEntradaSalida("S");
            }
            listaOperaciones.add(op);
        }
        return listaOperaciones;

    }

    @Override
    public int updateStatusConcepto(BigDecimal idOperacionPk, BigDecimal idStatusFk, BigDecimal idConceptoFk) {

        return ejb.updateStatusConceptoOperacion(idOperacionPk, idStatusFk, idConceptoFk);
    }

    @Override
    public ArrayList<TipoOperacion> getOperacionesCorteBy(BigDecimal idCajaFk, BigDecimal idUserFk, BigDecimal idES) {

        int i = 1;
        ArrayList<TipoOperacion> lista = new ArrayList<TipoOperacion>();
        List<Object[]> lstObject = ejb.getOperacionesCorteBy(idCajaFk, idUserFk, idES);
        for (Object[] obj : lstObject) {
            TipoOperacion op = new TipoOperacion();
            op.setIdTipoOperacionPk(obj[0] == null ? null : new BigDecimal(obj[0].toString()));
            op.setNombre(obj[1] == null ? null : obj[1].toString());
            op.setMontoTotal(obj[2] == null ? null : new BigDecimal(obj[2].toString()));
            op.setNumero(i);
            i += 1;
            lista.add(op);
        }
        return lista;

    }

    @Override
    public ArrayList<OperacionesCaja> getOperaciones(BigDecimal idCajaFk, BigDecimal idUserFk) {

        ArrayList<OperacionesCaja> listaOperaciones = new ArrayList<OperacionesCaja>();
        List<Object[]> lstObject = ejb.getOperaciones(idCajaFk, idUserFk);
        for (Object[] obj : lstObject) {
            OperacionesCaja op = new OperacionesCaja();
            op.setIdOperacionesCajaPk(obj[0] == null ? null : new BigDecimal(obj[0].toString()));
            op.setIdCorteCajaFk(obj[1] == null ? null : new BigDecimal(obj[1].toString()));
            op.setIdCajaFk(obj[2] == null ? null : new BigDecimal(obj[2].toString()));
            op.setIdCajaDestinoFk(obj[3] == null ? null : new BigDecimal(obj[3].toString()));
            op.setIdConceptoFk(obj[4] == null ? null : new BigDecimal(obj[4].toString()));
            op.setFecha(obj[5] == null ? null : (Date) obj[5]);
            op.setIdStatusFk(obj[6] == null ? null : new BigDecimal(obj[6].toString()));
            op.setIdUserFk(obj[7] == null ? null : new BigDecimal(obj[7].toString()));
            op.setComentarios(obj[8] == null ? null : obj[8].toString());
            op.setMonto(obj[9] == null ? null : new BigDecimal(obj[9].toString()));
            op.setEntradaSalida(obj[10] == null ? null : new BigDecimal(obj[10].toString()));
            op.setIdCuentaDestinoFk(obj[11] == null ? null : new BigDecimal(obj[11].toString()));
            listaOperaciones.add(op);
        }
        return listaOperaciones;

    }

    @Override
    public int updateCorte(BigDecimal idOperacionPk, BigDecimal idCorteFk) {

        return ejb.updateCorteCaja(idOperacionPk, idCorteFk);

    }

    @Override
    public ArrayList<OperacionesCaja> getCheques(BigDecimal idCajaFk, BigDecimal idUserFk, BigDecimal idINOUT) {

        int i = 1;
        ArrayList<OperacionesCaja> listaOperaciones = new ArrayList<OperacionesCaja>();
        List<Object[]> lstObject = ejb.getCheques(idCajaFk, idUserFk, idINOUT);
        for (Object[] obj : lstObject) {
            OperacionesCaja op = new OperacionesCaja();
            op.setIdOperacionesCajaPk(obj[0] == null ? null : new BigDecimal(obj[0].toString()));
            op.setIdCorteCajaFk(obj[1] == null ? null : new BigDecimal(obj[1].toString()));
            op.setIdCajaFk(obj[2] == null ? null : new BigDecimal(obj[2].toString()));
            op.setIdCajaDestinoFk(obj[3] == null ? null : new BigDecimal(obj[3].toString()));
            op.setIdConceptoFk(obj[4] == null ? null : new BigDecimal(obj[4].toString()));
            op.setFecha(obj[5] == null ? null : (Date) obj[5]);
            op.setIdStatusFk(obj[6] == null ? null : new BigDecimal(obj[6].toString()));
            op.setIdUserFk(obj[7] == null ? null : new BigDecimal(obj[7].toString()));
            op.setComentarios(obj[8] == null ? null : obj[8].toString());
            op.setMonto(obj[9] == null ? null : new BigDecimal(obj[9].toString()));
            op.setEntradaSalida(obj[10] == null ? null : new BigDecimal(obj[10].toString()));
            op.setNumero(i);
            i += 1;
            listaOperaciones.add(op);
        }
        return listaOperaciones;

    }

    @Override
    public ArrayList<OperacionesCaja> getDepositosEntrantes() {

        int i = 1;
        ArrayList<OperacionesCaja> listaOperaciones = new ArrayList<OperacionesCaja>();
        List<Object[]> lstObject = ejb.getDepositosEntrantes();
        for (Object[] obj : lstObject) {
            OperacionesCaja op = new OperacionesCaja();
            op.setIdOperacionesCajaPk(obj[0] == null ? null : new BigDecimal(obj[0].toString()));
            op.setMonto(obj[1] == null ? null : new BigDecimal(obj[1].toString()));
            op.setNombreCaja(obj[2] == null ? null : obj[2].toString());
            op.setNombreConcepto(obj[3] == null ? null : obj[3].toString());
            op.setNombreOperacion(obj[4] == null ? null : obj[4].toString());
            op.setFecha(obj[5] == null ? null : (Date) obj[5]);
            op.setNombreUsuario(obj[6] == null ? null : obj[6].toString());
            op.setNombreBanco(obj[7] == null ? null : obj[7].toString());
            op.setCuentaBanco(obj[8] == null ? null : new BigDecimal(obj[8].toString()));
            op.setComentarios(obj[9] == null ? null : obj[9].toString());
            op.setIdConceptoFk(obj[10] == null ? null : new BigDecimal(obj[10].toString()));
            op.setIdCajaFk(obj[11] == null ? null : new BigDecimal(obj[11].toString()));
            op.setIdCuentaDestinoFk(obj[12] == null ? null : new BigDecimal(obj[12].toString()));
            op.setIdUserFk(obj[13] == null ? null : new BigDecimal(obj[13].toString()));
            op.setIdSucursalFk(obj[14] == null ? null : new BigDecimal(obj[14].toString()));

            op.setNumero(i);
            i += 1;
            listaOperaciones.add(op);
        }
        return listaOperaciones;

    }

    @Override
    public ArrayList<Usuario> getResponsables(BigDecimal idCajaFk) {

        ArrayList<Usuario> listaResponsables = new ArrayList<Usuario>();
        List<Object[]> lstObject = ejb.getResponsables(idCajaFk);
        for (Object[] obj : lstObject) {
            Usuario u = new Usuario();
            u.setIdUsuarioPk(obj[0] == null ? null : new BigDecimal(obj[0].toString()));
            u.setNombreUsuario(obj[1] == null ? null : obj[1].toString());
            listaResponsables.add(u);
        }
        return listaResponsables;
    }

    @Override
    public ArrayList<OperacionesCaja> getDetalles(BigDecimal idCajaFk, BigDecimal idUserFk, BigDecimal entrada_salida, BigDecimal idStatusFk) {

        ArrayList<OperacionesCaja> listaOperaciones = new ArrayList<OperacionesCaja>();
        List<Object[]> lstObject = ejb.getDetalles(idCajaFk, idUserFk, entrada_salida, idStatusFk);
        int count = 1;
        for (Object[] obj : lstObject) {
            OperacionesCaja op = new OperacionesCaja();

            op.setIdOperacionesCajaPk(obj[0] == null ? null : new BigDecimal(obj[0].toString()));
            op.setNombreConcepto(obj[1] == null ? null : obj[1].toString());
            op.setIdSucursalFk(obj[2] == null ? null : new BigDecimal(obj[2].toString()));
            op.setNombreSucursal(obj[3] == null ? null : obj[3].toString());
            op.setMonto(obj[4] == null ? null : new BigDecimal(obj[4].toString()));
            op.setIdConceptoFk(obj[5] == null ? null : new BigDecimal(obj[5].toString()));
            op.setEntradaSalida(obj[6] == null ? null : new BigDecimal(obj[6].toString()));
            op.setNumero(count);
            if (op.getEntradaSalida().intValue() == 1) {
                op.setNombreEntradaSalida("E");
            } else {
                op.setNombreEntradaSalida("S");
            }
            /*op.setIdCorteCajaFk(obj[1] == null ? null : new BigDecimal(obj[1].toString()));
            op.setIdCajaFk(obj[2] == null ? null : new BigDecimal(obj[2].toString()));
            op.setIdCajaDestinoFk(obj[3] == null ? null : new BigDecimal(obj[3].toString()));
            
            op.setFecha(obj[5] == null ? null : (Date)obj[5]);
            op.setIdStatusFk(obj[6] == null ? null : new BigDecimal(obj[6].toString()));
            op.setIdUserFk(obj[7] == null ? null : new BigDecimal(obj[7].toString()));
            op.setComentarios(obj[8] == null ? null : obj[8].toString());
            op.setMonto(obj[9] == null ? null : new BigDecimal(obj[9].toString()));
            op.setEntradaSalida(obj[10] == null ? null : new BigDecimal(obj[10].toString()));
            op.setIdCuentaDestinoFk(obj[11] == null ? null : new BigDecimal(obj[11].toString()));*/
            count += 1;
            listaOperaciones.add(op);
        }
        for (OperacionesCaja c : listaOperaciones) {
            System.out.println("c: " + c.toString());
        }

        return listaOperaciones;

    }

    @Override
    public ArrayList<OperacionesCaja> getDetallesCorte(BigDecimal idCajaFk, BigDecimal idUserFk, BigDecimal entrada_salida, BigDecimal idStatusFk, BigDecimal idCorteFk) {

        ArrayList<OperacionesCaja> listaOperaciones = new ArrayList<OperacionesCaja>();
        List<Object[]> lstObject = ejb.getDetallesCorte(idCajaFk, idUserFk, entrada_salida, idStatusFk, idCorteFk);
        int count = 1;
        for (Object[] obj : lstObject) {
            OperacionesCaja op = new OperacionesCaja();

            op.setIdOperacionesCajaPk(obj[0] == null ? null : new BigDecimal(obj[0].toString()));
            op.setNombreConcepto(obj[1] == null ? null : obj[1].toString());
            op.setIdSucursalFk(obj[2] == null ? null : new BigDecimal(obj[2].toString()));
            op.setNombreSucursal(obj[3] == null ? null : obj[3].toString());
            op.setMonto(obj[4] == null ? null : new BigDecimal(obj[4].toString()));
            op.setIdConceptoFk(obj[5] == null ? null : new BigDecimal(obj[5].toString()));
            op.setEntradaSalida(obj[6] == null ? null : new BigDecimal(obj[6].toString()));
            op.setNumero(count);
            if (op.getEntradaSalida().intValue() == 1) {
                op.setNombreEntradaSalida("E");
            } else {
                op.setNombreEntradaSalida("S");
            }
            /*op.setIdCorteCajaFk(obj[1] == null ? null : new BigDecimal(obj[1].toString()));
            op.setIdCajaFk(obj[2] == null ? null : new BigDecimal(obj[2].toString()));
            op.setIdCajaDestinoFk(obj[3] == null ? null : new BigDecimal(obj[3].toString()));
            
            op.setFecha(obj[5] == null ? null : (Date)obj[5]);
            op.setIdStatusFk(obj[6] == null ? null : new BigDecimal(obj[6].toString()));
            op.setIdUserFk(obj[7] == null ? null : new BigDecimal(obj[7].toString()));
            op.setComentarios(obj[8] == null ? null : obj[8].toString());
            op.setMonto(obj[9] == null ? null : new BigDecimal(obj[9].toString()));
            op.setEntradaSalida(obj[10] == null ? null : new BigDecimal(obj[10].toString()));
            op.setIdCuentaDestinoFk(obj[11] == null ? null : new BigDecimal(obj[11].toString()));*/

            count += 1;
            listaOperaciones.add(op);
        }

        return listaOperaciones;

    }

    @Override
    public ArrayList<TipoOperacion> getOperacionesByIdCorteCajaFk(BigDecimal idCorteCajaFk, BigDecimal entrada_salida) {

        int i = 1;
        ArrayList<TipoOperacion> lista = new ArrayList<TipoOperacion>();

        List<Object[]> lstObject = ejb.getOperacionesByIdCorteCajaFk(idCorteCajaFk, entrada_salida);

        //List<Object[]> lstObject = ejb.getOperacionesCorteBy(idCajaFk, idUserFk, idES);
        for (Object[] obj : lstObject) {
            TipoOperacion op = new TipoOperacion();
            op.setIdTipoOperacionPk(obj[0] == null ? null : new BigDecimal(obj[0].toString()));
            op.setNombre(obj[1] == null ? null : obj[1].toString());
            op.setMontoTotal(obj[2] == null ? null : new BigDecimal(obj[2].toString()));
            op.setNumero(i);
            i += 1;
            lista.add(op);
        }
        return lista;

    }

}
