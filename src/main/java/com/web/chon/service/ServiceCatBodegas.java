package com.web.chon.service;

import com.web.chon.dominio.Bodega;
import com.web.chon.dominio.Pagina;
import com.web.chon.ejb.EjbCatBodegas;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Juan
 */
@Service
@Transactional
public class ServiceCatBodegas implements IfaceCatBodegas {

    @Autowired
    EjbCatBodegas ejb;

    @Override
    public ArrayList<Bodega> getBodegas() {
        try {
            ArrayList<Bodega> lista_bodegas = new ArrayList<Bodega>();

            List<Object[]> lstObject = ejb.getBodegas();

            for (Object[] obj : lstObject) {
                Bodega s = new Bodega();
                s.setIdBodegaPK(new BigDecimal(obj[0].toString()));
                s.setNombreBodega((obj[1] == null ? "" : obj[1].toString()));
                s.setDescripcionBodega((obj[2] == null ? "" : obj[2].toString()));
                lista_bodegas.add(s);

            }
            return lista_bodegas;
        } catch (Exception ex) {
            Logger.getLogger(ServiceCatBodegas.class.getName()).log(Level.SEVERE, null, ex);
            return null;

        }
    }

    @Override
    public List<Bodega[]> getBodegaById(int idBodega) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNextVal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pagina<Bodega> findAll(Pageable pageable) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pagina<Bodega> findAllDominio(Bodega filters, int first, int pageSize) {

        try {

            ArrayList<Bodega> lstBodegas = new ArrayList<Bodega>();
            long size = ejb.countBodegas();

            if (first != 0) {
                first++;
            }

            List<Object[]> lstObject = ejb.getBodepagasPagination(first, pageSize, filters.getIdSucursalFk());

            for (Object[] obj : lstObject) {
                Bodega bodega = new Bodega();
                bodega.setIdBodegaPK(new BigDecimal(obj[0].toString()));
                bodega.setNombreBodega((obj[1] == null ? "" : obj[1].toString()));
                bodega.setDescripcionBodega((obj[2] == null ? "" : obj[2].toString()));
                bodega.setIdSucursalFk(obj[3] == null ? null : new BigDecimal(obj[3].toString()));
                bodega.setNombreSucursal((obj[4] == null ? "" : obj[4].toString()));

                lstBodegas.add(bodega);

            }

            return new Pagina<Bodega>(lstBodegas, size);
        } catch (Exception ex) {
            Logger.getLogger(ServiceCatBodegas.class.getName()).log(Level.SEVERE, null, ex);
            return null;

        }

    }

    @Override
    public Bodega getById(BigDecimal dominio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int create(Bodega dominio) {

        return ejb.insertBodega(dominio);
    }

    @Override
    public int update(Bodega dominio) {

        return ejb.updateBodega(dominio);
    }

    @Override
    public List<Bodega> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(BigDecimal id) {

        return ejb.deleteBodega(id.intValue());
    }

    @Override
    public Bodega getById(String dominio) {

        List<Object[]> lstObject = null;
        Bodega bodega = new Bodega();

        lstObject = ejb.getBodegaById(Integer.parseInt(dominio));

        for (Object[] obj : lstObject) {

            bodega.setIdBodegaPK(obj[0] == null ? null : new BigDecimal(obj[0].toString()));
            bodega.setNombreBodega(obj[1] == null ? "" : obj[1].toString());
            bodega.setDescripcionBodega(obj[2] == null ? "" : obj[2].toString());
        }

        return bodega;

    }

    @Override
    public ArrayList<Bodega> getBodegaByIdSucursal(BigDecimal idSucursal) {
        try {
            ArrayList<Bodega> lista_bodegas = new ArrayList<Bodega>();

            List<Object[]> lstObject = ejb.getBodegaByIdSucursal(idSucursal);

            for (Object[] obj : lstObject) {
                Bodega s = new Bodega();
                s.setIdBodegaPK(new BigDecimal(obj[0].toString()));
                s.setNombreBodega((obj[1] == null ? "" : obj[1].toString()));
                s.setDescripcionBodega((obj[2] == null ? "" : obj[2].toString()));
                s.setIdSucursalFk(new BigDecimal(obj[3].toString()));
                lista_bodegas.add(s);

            }
            return lista_bodegas;
        } catch (Exception ex) {
            Logger.getLogger(ServiceCatBodegas.class.getName()).log(Level.SEVERE, null, ex);
            return null;

        }

    }
}
