package com.web.chon.service;

import com.web.chon.dominio.PreciosCompetencia;
import com.web.chon.ejb.EjbPreciosCompetidores;
import java.math.BigDecimal;
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
public class ServicePreciosCompetencias implements IfacePreciosCompetencias {

    @Autowired
    EjbPreciosCompetidores ejb;

    @Override
    public int getNextVal() {

        return ejb.getNextVal();
    }

    @Override
    public int insertPreciosCompetencias(PreciosCompetencia pc) {

        return ejb.insertPreciosCompetidores(pc);
    }

    @Override
    public ArrayList<PreciosCompetencia> getPreciosCompetencias(String fecha) {

        List<Object[]> lstObject = new ArrayList<Object[]>();
        ArrayList<PreciosCompetencia> lista = new ArrayList<PreciosCompetencia>();
        lstObject = ejb.getPreciosCompetidores(fecha);
        for (Object[] obj : lstObject) {
            PreciosCompetencia dominio = new PreciosCompetencia();
            dominio.setIdPcPk(obj[0] == null ? null : new BigDecimal(obj[0].toString()));
            dominio.setIdCompetidorFk(obj[1] == null ? null : new BigDecimal(obj[1].toString()));
            dominio.setIdSubProductoPk(obj[2] == null ? null : obj[2].toString());
            dominio.setFechaRegistro(obj[3] == null ? null : (Date) obj[3]);
            dominio.setPrecioVenta(obj[4] == null ? null : new BigDecimal(obj[4].toString()));
            dominio.setNombreProducto(obj[5] == null ? "" : obj[5].toString());
            dominio.setNombreCompetidor(obj[6] == null ? "" : obj[6].toString());
            lista.add(dominio);
        }
        return lista;
    }

    @Override
    public int deletePrecioCompetencia(PreciosCompetencia pc) {

        return ejb.deletePreciosCompetidores(pc);
    }

    @Override
    public int updateCompetencia(PreciosCompetencia pc) {

        return ejb.updatePreciosCompetidores(pc);
    }

    @Override
    public PreciosCompetencia getPreciosCompetenciasByCompetidorProducto(PreciosCompetencia pc) {
        List<Object[]> lstObject = null;
        PreciosCompetencia dominio = new PreciosCompetencia();

        lstObject = ejb.getPreciosCompetenciasByCompetidorProducto(pc);

        for (Object[] obj : lstObject) {

            dominio.setIdPcPk(obj[0] == null ? null : new BigDecimal(obj[0].toString()));
            dominio.setIdCompetidorFk(obj[1] == null ? null : new BigDecimal(obj[1].toString()));
            dominio.setIdSubProductoPk(obj[2] == null ? null : obj[2].toString());
            dominio.setFechaRegistro(obj[3] == null ? null : (Date) obj[3]);
            dominio.setPrecioVenta(obj[4] == null ? null : new BigDecimal(obj[4].toString()));
            dominio.setNombreProducto(obj[5] == null ? "" : obj[5].toString());
            dominio.setNombreCompetidor(obj[6] == null ? "" : obj[6].toString());

        }
        return dominio;

    }

}
