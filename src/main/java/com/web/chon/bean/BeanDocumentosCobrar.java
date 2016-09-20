/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.bean;

import com.web.chon.dominio.AbonoDocumentos;
import com.web.chon.dominio.Caja;
import com.web.chon.dominio.Cliente;
import com.web.chon.dominio.CobroCheques;
import com.web.chon.dominio.Documento;
import com.web.chon.dominio.OperacionesCaja;
import com.web.chon.dominio.Sucursal;
import com.web.chon.dominio.TipoAbono;
import com.web.chon.dominio.UsuarioDominio;
import com.web.chon.security.service.PlataformaSecurityContext;
import com.web.chon.service.IfaceAbonoCredito;
import com.web.chon.service.IfaceAbonoDocumentos;
import com.web.chon.service.IfaceCaja;
import com.web.chon.service.IfaceCatCliente;
import com.web.chon.service.IfaceCatSucursales;
import com.web.chon.service.IfaceCobroCheques;
import com.web.chon.service.IfaceDocumentos;
import com.web.chon.service.IfaceOperacionesCaja;
import com.web.chon.service.IfaceTipoAbono;
import com.web.chon.util.Constantes;
import com.web.chon.util.JasperReportUtil;
import com.web.chon.util.JsfUtil;
import com.web.chon.util.TiempoUtil;
import com.web.chon.util.UtilUpload;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author JesusAlfredo
 */
@Component
@Scope("view")
public class BeanDocumentosCobrar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Autowired
    private IfaceCatCliente ifaceCatCliente;
    @Autowired
    private IfaceCatSucursales ifaceCatSucursales;
    @Autowired
    private PlataformaSecurityContext context;
    @Autowired
    private IfaceDocumentos ifaceDocumentos;
    @Autowired
    private IfaceCobroCheques ifaceCobroCheques;
    @Autowired
    private IfaceTipoAbono ifaceTipoAbono;
    @Autowired
    private IfaceAbonoDocumentos ifaceAbonoDocumentos;
    @Autowired
    IfaceCaja ifaceCaja;
    @Autowired
    private IfaceOperacionesCaja ifaceOperacionesCaja;

    
    private ArrayList<Sucursal> listaSucursales;
    private ArrayList<Documento> listaDocumentos;
    private ArrayList<AbonoDocumentos> listaAbonosCheques;
    private ArrayList<Cliente> lstCliente;
    private ArrayList<TipoAbono> lstTipoAbonos;
    private UsuarioDominio usuario;
    private StringBuffer query;

    private Documento dc;
    private CobroCheques cobroCheque;

    //---Variables de la vista---///
    private String title;
    private String viewEstate;
    private String leyenda;
    Date fechaInicio;
    Date fechaFin;
    private BigDecimal idSucursalFk;
    private BigDecimal saldoParaLiquidar;
    private Cliente cliente;

    private AbonoDocumentos dataAbonar;

    private BigDecimal filtroFecha;
    private Documento documentoData;

    private Map paramReport = new HashMap();
    private String rutaPDF;
    private StreamedContent media;
    private ByteArrayOutputStream outputStream;
    private String number;
    private int idSucu;
    private String pathFileJasper = "C:/Users/Juan/Documents/NetBeansProjects/Chonajos-V2/ticket.jasper";
    private boolean camposDeposito;
    private boolean camposEfectivo;
    private BigDecimal filtroStatus;

    private static final BigDecimal DOCUMENTOACTIVO = new BigDecimal(1);
    private static final BigDecimal DOCUMENTOFINALIZADO = new BigDecimal(2);
    private static final BigDecimal DOCUMENTODEVUELTO = new BigDecimal(3);
    private static final BigDecimal DOCUMENTOCONVENIO = new BigDecimal(4);

    private Caja caja;
    private OperacionesCaja opcaja;
    
    
    
    //Variables para el pago de Documentos
    private AbonoDocumentos ad;
    private String viewCheque;
    private BigDecimal filtroFormaPago;

    @PostConstruct
    public void init() {
        fechaInicio = new Date();
        fechaFin = new Date();
        dataAbonar = new AbonoDocumentos();
        listaSucursales = new ArrayList<Sucursal>();
        listaSucursales = ifaceCatSucursales.getSucursales();
        usuario = context.getUsuarioAutenticado();
        listaDocumentos = new ArrayList<Documento>();

        documentoData = new Documento();
        idSucursalFk = new BigDecimal(usuario.getSucId());
        System.out.println("IdSucursal Logueado: " + idSucursalFk);
        filtroFecha = new BigDecimal(1);

        filtroStatus = DOCUMENTOACTIVO;
        listaDocumentos = ifaceDocumentos.getDocumentos(fechaInicio, fechaFin, idSucursalFk, null, filtroFormaPago, filtroStatus,filtroFecha);
        generarQuery();
        camposDeposito = true;
        camposEfectivo = false;
        setTitle("Documentos por Cobrar");
        setViewEstate("init");
        cobroCheque = new CobroCheques();
        dc = new Documento();
        ad = new AbonoDocumentos();
        lstTipoAbonos = ifaceTipoAbono.getAll();

    }

    public void cambiarFormaPago() {
        System.out.println("DocumentoBean: " + documentoData);
        if (ifaceDocumentos.cambiarFormaPago(documentoData) == 1) {
            JsfUtil.addSuccessMessageClean("Se ha cambiado la forma de Pago correctamente");
        } else {
            JsfUtil.addErrorMessageClean("Ocurrió un problema");
        }
    }

    public void addView() {

        if (dataAbonar.getIdTipoAbonoFk().intValue() == 3) {
            setViewCheque("true");
        } else if (dataAbonar.getIdTipoAbonoFk().intValue() == 2) {
            setViewCheque("trans");

        } else {
            setViewCheque("init");
        }

    }

    public void abonar() {
        System.out.println("=============Nuevo Abono==========");
        dataAbonar.setIdAbonoDocumentoPk(new BigDecimal(ifaceAbonoDocumentos.getNextVal()));
        dataAbonar.setIdDocumentoFk(documentoData.getIdDocumentoPk());
        dataAbonar.setIdUsuarioFk(usuario.getIdUsuario());
        dataAbonar.setIdClienteFk(documentoData.getIdClienteFk());
        System.out.println("Nuevo Abono: " + dataAbonar.toString());
        if (ifaceAbonoDocumentos.insert(dataAbonar) == 1) 
        {
            //Tenemos que saber lo que llevamos abonado de un documento sumar el nuevo abono y ver si hemos terminado de
            //abonar y cambiar el estatus a finalizado.
            
            BigDecimal totalTemporal= ifaceAbonoDocumentos.getTotalAbonadoByIdDocumento(documentoData.getIdDocumentoPk());
            System.out.println("Total Abonado: "+totalTemporal);
            System.out.println("Monto Total: "+documentoData.getMonto());
            if(totalTemporal.compareTo(documentoData.getMonto())>=0)
            {
                System.out.println("Ya se liquido la deuda");
                //cambiar el estaus del Documento
                documentoData.setIdStatusFk(DOCUMENTOFINALIZADO);
                ifaceDocumentos.updateDocumentoById(documentoData);
            }
            if (dataAbonar.getIdTipoAbonoFk().intValue() == 3) 
            {
                System.out.println("Ahora insertamos documento por cobrar");
                Documento d = new Documento();
                d.setIdDocumentoPk(new BigDecimal(ifaceDocumentos.getNextVal()));
                d.setComentario("");
                d.setFechaCobro(dataAbonar.getFechaCobro());
                d.setIdAbonoDocumentoFk(dataAbonar.getIdAbonoDocumentoPk());
                d.setIdClienteFk(dataAbonar.getIdClienteFk());
                d.setIdStatusFk(new BigDecimal(1));
                d.setIdFormaCobroFk(new BigDecimal(1));
                d.setIdTipoDocumento(new BigDecimal(1));
                d.setMonto(dataAbonar.getMontoAbono());
                d.setNumeroCheque(dataAbonar.getNumeroCheque());
                d.setFactura(dataAbonar.getNumeroFactura());
                d.setBanco(dataAbonar.getBanco());
                d.setLibrador(dataAbonar.getLibrador());
                d.setFechaCobro(dataAbonar.getFechaCobro());
                d.setIdDocumentoPadreFk(dataAbonar.getIdDocumentoFk());
                
                System.out.println("El nuevo Documento por Entrar será de: " + d.toString());
                
                if (ifaceDocumentos.insertarDocumento(d) == 1) 
                {
                    JsfUtil.addSuccessMessageClean("Se ha ingresado correctamente en la tabla de documentos por cobrar");
                } else 
                {
                    JsfUtil.addErrorMessageClean("Ha ocurrido un error al ingresar el documento por cobrar");
                }
            }
            JsfUtil.addSuccessMessageClean("Se ingreso abono de documento");
        } else {
            JsfUtil.addErrorMessageClean("Ocurrió un problema");
        }
    }

    public void buscar() {
        BigDecimal idCliente = null;
        if (cliente != null) 
        {
            idCliente = cliente.getId_cliente();
        }
        listaDocumentos = ifaceDocumentos.getDocumentos(fechaInicio, fechaFin, idSucursalFk, idCliente,filtroFormaPago,filtroStatus,filtroFecha);

        }
//    public void comboFiltro()
//    {
//        switch(filtro.intValue())
//        {
//            //vencidos
//            //por vencer
//            //TODOS
//            case 1:
//                fechaInicio = new Date();
//                break;
//            case 2:
//                fechaInicio = new Date();
//                break;
//            case 3:
//                break;
//            
//        }
//    }

    public ArrayList<Cliente> autoCompleteCliente(String nombreCliente) {
        lstCliente = ifaceCatCliente.getClienteByNombreCompleto(nombreCliente.toUpperCase());
        return lstCliente;

    }

    public void agregarCampos() {
        switch (cobroCheque.getIdTipoCobro().intValue()) {
            case 1:
                camposDeposito = true;
                camposEfectivo = false;
                break;
            case 2:
                camposDeposito = false;
                camposEfectivo = true;
                break;
            case 3:
                camposDeposito = false;
                camposEfectivo = false;
                break;

        }
    }

    public void cobrarCheque() {
        cobroCheque.setIdCobroChequePk(new BigDecimal(ifaceCobroCheques.nextVal()));
        cobroCheque.setImporteDeposito(documentoData.getMonto());
        cobroCheque.setIdDocumentoFk(documentoData.getIdDocumentoPk());
        System.out.println("BEAN: CobroCheque: " + cobroCheque);
        if (ifaceCobroCheques.insertarDocumento(cobroCheque) == 1) {
            Documento temp = new Documento();
            temp.setIdDocumentoPk(cobroCheque.getIdDocumentoFk());
            temp.setIdStatusFk(DOCUMENTOFINALIZADO);
            //si se inserta el cobro del cheque cambiamos el status del documento
            if (ifaceDocumentos.updateDocumentoById(temp) == 1) {
                System.out.println("Se cambio status del documento");
                JsfUtil.addSuccessMessageClean("Se realizó el cobro de cheque exitosamente");

            }
        } else {
            JsfUtil.addErrorMessageClean("Ocurrió un problema al cobrar el cheque");
        }

    }

    public void buscaCheques() {
        System.out.println("idSucursal: " + idSucursalFk);
        //listaAbonosAtrasdos = ifaceAbonoCredito.getChequesPendientes(fechaInicio, fechaFin, idSucursalFk, cliente.getId_cliente(), filtro);
        generarQuery();
        System.out.println("QueryBEAN: " + query);
    }

    public void generarQuery() {

        StringBuffer cadena = new StringBuffer("select ab.* from ABONO_CREDITO ab "
                + "inner join USUARIO u "
                + "on u.ID_USUARIO_PK = ab.ID_USUARIO_FK "
                + "WHERE TO_DATE(TO_CHAR(ab.FECHA_COBRO,'dd/mm/yyyy'),'dd/mm/yyyy')< '" + TiempoUtil.getFechaDDMMYYYY(fechaInicio) + "' "
                + "and ab.ESTATUS=2 and ab.TIPO_ABONO_FK=3 ");

        if (idSucursalFk == null || idSucursalFk.equals("")) {
            cadena.append(" order by ab.FECHA_COBRO asc");

        } else {
            cadena.append(" and u.ID_SUCURSAL_FK='" + idSucursalFk + "' order by ab.FECHA_COBRO asc");
        }
        query = cadena;
    }

    public void descargar() {
//
//        if (listaAbonosAtrasdos.isEmpty()) {
//            JsfUtil.addErrorMessageClean("No se tienen registro reporte no generado");
//        } else {
//            JsfUtil.addSuccessMessage("Reporte Generado");
//            setParameterTicket();
//            generateReport(4);
//            downloadFile();
//
//        }

    }

    private void setParameterTicket() {

//        paramReport.put("nombreSucursal", usuario.getNombreSucursal());
//        paramReport.put("nombreProvedor", getNombreProvedor());
//        paramReport.put("kilosProvedor", em.getKilosProvedor().toString());
//        paramReport.put("kilosReales", em.getKilosTotales().toString());
        System.out.println("Query: " + query);
        paramReport.put("labelSucursal", usuario.getNombreSucursal());
        paramReport.put("query", query.toString());
//        paramReport.put("nombreRecibidor", usuario.getNombreCompleto());
//        paramReport.put("folio", em.getFolio().toString());
//        paramReport.put("ID_EMM_FK", em.getIdEmmPk().toString());
//        System.out.println("telefonos =======" +usuario.getTelefonoSucursal());
//        paramReport.put("leyenda", "Para cualquier duda o comentario estamos a sus órdenes al teléfono:" + usuario.getTelefonoSucursal());
//        

    }

    public void generateReport(int folio) {
        JRExporter exporter = null;

        try {
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String temporal = "";
            if (servletContext.getRealPath("") == null) {
                temporal = Constantes.PATHSERVER;
            } else {
                temporal = servletContext.getRealPath("");
            }

            pathFileJasper = temporal + File.separatorChar + "resources" + File.separatorChar + "report" + File.separatorChar + "Cheques" + File.separatorChar + "RChequesNoCobrados.jasper";
//            Connection conn=null;
//            try 
//            {
//                Class.forName("oracle.jdbc.OracleDriver");
//                conn = DriverManager.getConnection("jdbc:oracle:thin:@//192.168.1.77:1521/xe", "choniTest", "choniTest");
//                System.out.println("Se conecto PERRO !");
//            } catch (SQLException ex) 
//            {
//                ex.getStackTrace();
//            } catch (ClassNotFoundException ex) {
//                ex.getStackTrace();
//            }
            Context initContext;
            Connection con = null;
            try {

                javax.sql.DataSource datasource = null;

                Context initialContext = new InitialContext();

                // "jdbc/MyDBname" >> is a JNDI Name of DataSource on weblogic
                datasource = (DataSource) initialContext.lookup("DataChon");

                try {
                    con = datasource.getConnection();
                    System.out.println("datsource" + con.toString());
                } catch (SQLException ex) {
                    Logger.getLogger(BeanVenta.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (NamingException ex) {
                Logger.getLogger(BeanVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
            JasperPrint jp = JasperFillManager.fillReport(getPathFileJasper(), paramReport, con);
            outputStream = JasperReportUtil.getOutputStreamFromReport(paramReport, getPathFileJasper());
            exporter = new JRPdfExporter();

            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
//            exporter.setParameter(JRPdfExporterParameter.PDF_JAVASCRIPT, "this.print();");
            byte[] bytes = outputStream.toByteArray();

            rutaPDF = UtilUpload.saveFileTemp(bytes, "ticketPdf", folio, idSucu);
            con.close();
        } catch (Exception exception) {
            System.out.println("Error >" + exception.getMessage());
            exception.getStackTrace();
        }

    }

    public void downloadFile() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();

            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            response.reset();
            Date hoy = new Date();
            TiempoUtil.getFechaDDMMYYYY(hoy);
            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "attachment; filename=" + "reporteCheques" + TiempoUtil.getFechaDDMMYYYY(hoy) + ".pdf");
            OutputStream output = response.getOutputStream();
            output.write(outputStream.toByteArray());
            output.close();

            facesContext.responseComplete();
        } catch (Exception e) {
            System.out.println("Error >" + e.getMessage());
        }
    }

    public BigDecimal getIdSucursalFk() {
        return idSucursalFk;
    }

    public void setIdSucursalFk(BigDecimal idSucursalFk) {
        this.idSucursalFk = idSucursalFk;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getViewEstate() {
        return viewEstate;
    }

    public void setViewEstate(String viewEstate) {
        this.viewEstate = viewEstate;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public ArrayList<Sucursal> getListaSucursales() {
        return listaSucursales;
    }

    public void setListaSucursales(ArrayList<Sucursal> listaSucursales) {
        this.listaSucursales = listaSucursales;
    }

    public UsuarioDominio getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDominio usuario) {
        this.usuario = usuario;
    }

    public ArrayList<Documento> getListaDocumentos() {
        return listaDocumentos;
    }

    public void setListaDocumentos(ArrayList<Documento> listaDocumentos) {
        this.listaDocumentos = listaDocumentos;
    }

    public StringBuffer getQuery() {
        return query;
    }

    public void setQuery(StringBuffer query) {
        this.query = query;
    }

    public Map getParamReport() {
        return paramReport;
    }

    public void setParamReport(Map paramReport) {
        this.paramReport = paramReport;
    }

    public String getRutaPDF() {
        return rutaPDF;
    }

    public void setRutaPDF(String rutaPDF) {
        this.rutaPDF = rutaPDF;
    }

    public StreamedContent getMedia() {
        return media;
    }

    public void setMedia(StreamedContent media) {
        this.media = media;
    }

    public ByteArrayOutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(ByteArrayOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getIdSucu() {
        return idSucu;
    }

    public void setIdSucu(int idSucu) {
        this.idSucu = idSucu;
    }

    public String getPathFileJasper() {
        return pathFileJasper;
    }

    public void setPathFileJasper(String pathFileJasper) {
        this.pathFileJasper = pathFileJasper;
    }

    public BigDecimal getSaldoParaLiquidar() {
        return saldoParaLiquidar;
    }

    public void setSaldoParaLiquidar(BigDecimal saldoParaLiquidar) {
        this.saldoParaLiquidar = saldoParaLiquidar;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Documento getDc() {
        return dc;
    }

    public void setDc(Documento dc) {
        this.dc = dc;
    }

    public CobroCheques getCobroCheque() {
        return cobroCheque;
    }

    public void setCobroCheque(CobroCheques cobroCheque) {
        this.cobroCheque = cobroCheque;
    }

    public boolean isCamposDeposito() {
        return camposDeposito;
    }

    public void setCamposDeposito(boolean camposDeposito) {
        this.camposDeposito = camposDeposito;
    }

    public boolean isCamposEfectivo() {
        return camposEfectivo;
    }

    public void setCamposEfectivo(boolean camposEfectivo) {
        this.camposEfectivo = camposEfectivo;
    }

    public String getLeyenda() {
        return leyenda;
    }

    public void setLeyenda(String leyenda) {
        this.leyenda = leyenda;
    }

    public BigDecimal getFiltroFecha() {
        return filtroFecha;
    }

    public void setFiltroFecha(BigDecimal filtroFecha) {
        this.filtroFecha = filtroFecha;
    }

    
    public ArrayList<Cliente> getLstCliente() {
        return lstCliente;
    }

    public void setLstCliente(ArrayList<Cliente> lstCliente) {
        this.lstCliente = lstCliente;
    }

    public BigDecimal getFiltroStatus() {
        return filtroStatus;
    }

    public void setFiltroStatus(BigDecimal filtroStatus) {
        this.filtroStatus = filtroStatus;
    }

    public AbonoDocumentos getAd() {
        return ad;
    }

    public void setAd(AbonoDocumentos ad) {
        this.ad = ad;
    }

    public ArrayList<TipoAbono> getLstTipoAbonos() {
        return lstTipoAbonos;
    }

    public void setLstTipoAbonos(ArrayList<TipoAbono> lstTipoAbonos) {
        this.lstTipoAbonos = lstTipoAbonos;
    }

    public String getViewCheque() {
        return viewCheque;
    }

    public void setViewCheque(String viewCheque) {
        this.viewCheque = viewCheque;
    }

    public ArrayList<AbonoDocumentos> getListaAbonosCheques() {
        return listaAbonosCheques;
    }

    public void setListaAbonosCheques(ArrayList<AbonoDocumentos> listaAbonosCheques) {
        this.listaAbonosCheques = listaAbonosCheques;
    }

    public Documento getDocumentoData() {
        return documentoData;
    }

    public void setDocumentoData(Documento documentoData) {
        this.documentoData = documentoData;
    }

    public AbonoDocumentos getDataAbonar() {
        return dataAbonar;
    }

    public void setDataAbonar(AbonoDocumentos dataAbonar) {
        this.dataAbonar = dataAbonar;
    }

    public BigDecimal getFiltroFormaPago() {
        return filtroFormaPago;
    }

    public void setFiltroFormaPago(BigDecimal filtroFormaPago) {
        this.filtroFormaPago = filtroFormaPago;
    }

    
}
