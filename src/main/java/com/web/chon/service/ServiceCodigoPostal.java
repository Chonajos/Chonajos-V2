package com.web.chon.service;

import com.web.chon.dominio.CodigoPostal;
import com.web.chon.ejb.EjbCatCodigosPostales;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author freddy
 */
@Service
@Transactional
public class ServiceCodigoPostal implements IfaceCatCodigosPostales {

    @Autowired
    EjbCatCodigosPostales ejb;

    @Override
    public ArrayList<CodigoPostal> getCodigoPostalById(String codigo_postal) {
        try {
            ArrayList<CodigoPostal> lista_codigos = new ArrayList<CodigoPostal>();
            List<Object[]> lstObject = ejb.getCodigosByCP(codigo_postal);
            for (Object[] obj : lstObject) {
                CodigoPostal colonia = new CodigoPostal();
                colonia.setId_cp(Integer.parseInt(obj[0] == null ? "" : obj[0].toString()));
                colonia.setNumeropostal(obj[1] == null ? "" : obj[1].toString());
                colonia.setNombreColonia(obj[2] == null ? "" : obj[2].toString());
                colonia.setIdMunicipio(Integer.parseInt(obj[3] == null ? "" : obj[3].toString()));
                colonia.setNombreMunicipio(obj[4] == null ? "" : obj[4].toString());
                colonia.setIdEntidad(Integer.parseInt(obj[5] == null ? "" : obj[5].toString()));
                colonia.setNombreEntidad(obj[6] == null ? "" : obj[6].toString());
                lista_codigos.add(colonia);

            }
            return lista_codigos;
        } catch (Exception ex) {

            Logger.getLogger(ServiceCodigoPostal.class.getName()).log(Level.SEVERE, null, ex);
            return null;

        }

    }

    @Override
    public ArrayList<CodigoPostal> getCodigoPostalByIdMun(int idMunicipioPK) {
        try {
            ArrayList<CodigoPostal> lista_codigos = new ArrayList<CodigoPostal>();

            List<Object[]> lstObject = ejb.getCodigosByIdMun(idMunicipioPK);

            for (Object[] obj : lstObject) {
                CodigoPostal colonia = new CodigoPostal();
                colonia.setId_cp(Integer.parseInt(obj[0] == null ? "" : obj[0].toString()));
                colonia.setNumeropostal(obj[1] == null ? "" : obj[1].toString());
                colonia.setNombreColonia(obj[2] == null ? "" : obj[2].toString());
                colonia.setIdMunicipio(Integer.parseInt(obj[3] == null ? "" : obj[3].toString()));
                colonia.setNombreMunicipio(obj[4] == null ? "" : obj[4].toString());
                colonia.setIdEntidad(Integer.parseInt(obj[5] == null ? "" : obj[5].toString()));
                colonia.setNombreEntidad(obj[6] == null ? "" : obj[6].toString());
                lista_codigos.add(colonia);

            }
            return lista_codigos;
        } catch (Exception ex) {

            Logger.getLogger(ServiceCodigoPostal.class.getName()).log(Level.SEVERE, null, ex);
            return null;

        }

    }

}
