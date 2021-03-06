package com.web.chon.ejb;
import com.web.chon.dominio.Documento;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author JesusAlfredo
 */
@Repository
public class EjbDocumentos  {

    @PersistenceContext
    private EntityManager em;

       
    public int insertarDocumento(Documento documento) {

        System.out.println("EJBDOCUMENTOS: " + documento.toString());
        try {
            Query query = em.createNativeQuery("INSERT INTO  "
                    + "DOCUMENTOS_COBRAR (ID_DOCUMENTO_PK,ID_TIPO_DOCUMENTO,"
                    + "ID_CLIENTE_FK, ID_STATUS_FK, COMENTARIO,MONTO,NUMERO_CHEQUE"
                    + ",NUMERO_FACTURA,BANCO,LIBRADOR,FECHA_COBRO,ID_FORMA_COBRO_FK,ID_DOCUMENTO_PADRE_FK) "
                    + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            query.setParameter(1, documento.getIdDocumentoPk());
            query.setParameter(2, documento.getIdTipoDocumento());
            query.setParameter(3, documento.getIdClienteFk());
            query.setParameter(4, documento.getIdStatusFk());
            query.setParameter(5, documento.getComentario());
            query.setParameter(6, documento.getMonto());
            query.setParameter(7, documento.getNumeroCheque());
            query.setParameter(8, documento.getFactura());
            query.setParameter(9, documento.getBanco());
            query.setParameter(10, documento.getLibrador());
            query.setParameter(11, documento.getFechaCobro());
            query.setParameter(12, documento.getIdFormaCobroFk());
            query.setParameter(13, documento.getIdDocumentoPadreFk());
            return query.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(EjbDocumentos.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

       
    public List<Object[]> getDocumentoByIdDocumentoPk(BigDecimal idDocumento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

       
    public List<Object[]> getDocumentoByIdAbonoFk(BigDecimal idAbonoFk) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

       
    public List<Object[]> getDocumentosByIdClienteFk(BigDecimal idClienteFk) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

       
    public List<Object[]> getDocumentosByIdStatusFk(BigDecimal idStatusFk) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

       
    public int nextVal() {
        try {
            Query query = em.createNativeQuery("select S_DOCUMENTOS_COBRAR.nextval from dual");
            return Integer.parseInt(query.getSingleResult().toString());
        } catch (Exception ex) {
            System.out.println("error >" + ex.getMessage());
            return 0;
        }
    }

       
    public int updateDocumento(Documento documento) {
        System.out.println("EJBDOCUMENTOS: " + documento.toString());
        try {
            Query query = em.createNativeQuery("UPDATE  "
                    + "DOCUMENTOS_COBRAR SET ID_STATUS_FK = ? "
                    + "  WHERE ID_DOCUMENTO_PK = ?");

            query.setParameter(1, documento.getIdStatusFk());
            query.setParameter(2, documento.getIdDocumentoPk());
            return query.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(EjbDocumentos.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

       
    public List<Object[]> getDocumentos(String fechaInicio, String fechaFin, BigDecimal idSucursal, BigDecimal idClienteFk, BigDecimal filtroFormaPago, BigDecimal filtroStatus, BigDecimal filtroFecha) {

        System.out.println("Fecha fin: " + fechaFin);
        System.out.println("IdSucursalEJB: " + idSucursal);
        StringBuffer cadena = new StringBuffer("select dc.*,(CLI.NOMBRE||' '||CLI.APELLIDO_PATERNO ||' '||CLI.APELLIDO_MATERNO ) AS CLIENTE,fp.DESCRIPCION,\n"
                + "sd.DESCRIPCION\n"
                + "from DOCUMENTOS_COBRAR dc\n"
                + "inner join cliente cli on cli.ID_CLIENTE = dc.ID_CLIENTE_FK\n"
                + "inner join FORMAS_PAGO fp on fp.ID_FORMAS_PAGO_PK = dc.ID_FORMA_COBRO_FK\n"
                + "inner join STATUS_DOCUMENTOS sd on sd.ID_STATUS_DOCUMENTO_PK = dc.ID_STATUS_FK");
        boolean bandera = false;
        if (filtroFecha.intValue() == 1) {
            cadena.append(" WHERE TO_DATE(TO_CHAR(dc.FECHA_COBRO,'dd/mm/yyyy'),'dd/mm/yyyy')<= '" + fechaInicio + "'");
            bandera = true;
        } else if (filtroFecha.intValue() == 2) {
            cadena.append(" WHERE TO_DATE(TO_CHAR(dc.FECHA_COBRO,'dd/mm/yyyy'),'dd/mm/yyyy')> '" + fechaInicio + "'");
            bandera = true;
        }
        if (filtroFormaPago != null && !filtroFormaPago.equals("")) {
            if (bandera == true) {
                cadena.append(" AND dc.ID_FORMA_COBRO_FK = '" + filtroFormaPago + "'");
            } else {
                cadena.append(" WHERE dc.ID_FORMA_COBRO_FK = '" + filtroFormaPago + "'");
                bandera = true;
            }
        }
        if (filtroStatus != null && !filtroStatus.equals("")) {
            if (bandera == true) {
                cadena.append(" AND dc.ID_STATUS_FK = '" + filtroStatus + "'");
            } else {
                cadena.append(" WHERE dc.ID_STATUS_FK = '" + filtroStatus + "'");
                bandera = true;
            }
        }
        if (idClienteFk != null && !idClienteFk.equals("")) {
            if (bandera == true) {
                cadena.append(" AND dc.ID_CLIENTE_FK = '" + idClienteFk + "'");
            } else {
                cadena.append(" WHERE dc.ID_CLIENTE_FK = '" + idClienteFk + "'");
                bandera = true;
            }
        }
        cadena.append(" order by DC.FECHA_COBRO asc");
        System.out.println("Query: " + cadena);
        Query query;
        query = em.createNativeQuery(cadena.toString());

        try {
            List<Object[]> lstObject = query.getResultList();
            return lstObject;
        } catch (Exception e) {
            System.out.println("Error >" + e.getMessage());
            return null;
        }

    }

       
    public int cambiarFormaPago(Documento d) {
        System.out.println("EJBDOCUMENTOS: " + d.toString());
        try {
            Query query = em.createNativeQuery("UPDATE  "
                    + "DOCUMENTOS_COBRAR SET ID_FORMA_COBRO_FK = ? "
                    + "  WHERE ID_DOCUMENTO_PK = ?");

            query.setParameter(1, d.getIdFormaCobroFk());
            query.setParameter(2, d.getIdDocumentoPk());
            return query.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(EjbDocumentos.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

    }

}
