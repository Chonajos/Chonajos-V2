package com.web.chon.service;

import com.web.chon.dominio.Pagina;
import com.web.chon.dominio.Sucursal;
import com.web.chon.ejb.EjbCatSucursales;
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
 * @author freddy
 */
@Service
@Transactional
public class ServiceCatSucursales implements IfaceCatSucursales {

    @Autowired
    EjbCatSucursales ejb;

    @Override
    public ArrayList<Sucursal> getSucursales() {
        try {
            ArrayList<Sucursal> lista_sucursales = new ArrayList<Sucursal>();
            List<Object[]> lstObject = ejb.getSucursales();

            for (Object[] obj : lstObject) {
                Sucursal s = new Sucursal();
                s.setIdSucursalPk(new BigDecimal(obj[0].toString()));
                s.setNombreSucursal((obj[1] == null ? "" : obj[1].toString()));
                lista_sucursales.add(s);

            }
            return lista_sucursales;
        } catch (Exception ex) {
            Logger.getLogger(ServiceCatSucursales.class.getName()).log(Level.SEVERE, null, ex);
            return null;

        }
    }

    @Override
    public List<Object[]> getSucursalId(BigDecimal idSucursal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteSucursal(BigDecimal idSucursal) {
        return ejb.deleteSucursal(idSucursal);
    }

    @Override
    public int updateSucursal(Sucursal sucu) {
        return ejb.updateSucursal(sucu);

    }

    @Override
    public int insertSucursal(Sucursal sucu) {
        return ejb.insertSucursal(sucu);
    }

    @Override
    public int getNextVal() {
        return ejb.getNextVal();
    }

    @Override
    public Pagina<Sucursal> findAll(Pageable pageable) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pagina<Sucursal> findAllDominio(Sucursal filters, int first, int pageSize) {

        long size = ejb.getSizeListSucursales();
        if (first != 0) {
            first++;
        }
        List<Object[]> listaSucursales = ejb.getSucursalesDetalle(first, pageSize);
        ArrayList<Sucursal> CatListaSucursales = new ArrayList();

        for (Object[] obj : listaSucursales) {
            Sucursal s = new Sucursal();
            s.setIdSucursalPk(obj[0] == null ? null : new BigDecimal(obj[0].toString()));
            s.setNombreSucursal(obj[1] == null ? "" : obj[1].toString());
            s.setCalleSucursal(obj[2] == null ? "" : obj[2].toString());
            s.setCpSucursal(obj[3] == null ? null : new BigDecimal(obj[3].toString()));
            s.setTelefonoSucursal(obj[4] == null ? null : new BigDecimal(obj[4].toString()));
            s.setNumInt(obj[5] == null ? null : new BigDecimal(obj[5].toString()));
            s.setNumExt(obj[6] == null ? null : new BigDecimal(obj[6].toString()));
            s.setIdStatusSucursalfk(obj[7] == null ? null : new BigDecimal(obj[7].toString()));

            if (s.getIdSucursalPk().intValue() == 1) {
                s.setStatusSucursal(false);
            } else {
                s.setStatusSucursal(true);
            }

            s.setNombreStatus(obj[8] == null ? "" : obj[8].toString());
            s.setIdEntidadFk(obj[9] == null ? null : new BigDecimal(obj[9].toString()));
            s.setNombreEntidad(obj[10] == null ? "" : obj[10].toString());
            s.setIdMunicipioFK(obj[11] == null ? null : new BigDecimal(obj[11].toString()));
            s.setNombreMunicipio(obj[12] == null ? "" : obj[12].toString());
            s.setNombreColonia(obj[13] == null ? "" : obj[13].toString());
            s.setCodigoPostal(obj[14] == null ? "" : obj[14].toString());
            CatListaSucursales.add(s);
        }
        return new Pagina<Sucursal>(CatListaSucursales, size);

    }

    @Override
    public Sucursal getById(BigDecimal id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int create(Sucursal dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Sucursal dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sucursal> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(BigDecimal id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Sucursal getById(String dominio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
