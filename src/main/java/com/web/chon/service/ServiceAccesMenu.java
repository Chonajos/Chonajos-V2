package com.web.chon.service;

import com.web.chon.dominio.AccesMenu;
import com.web.chon.dominio.Pagina;
import com.web.chon.ejb.EjbAccesMenu;
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
public class ServiceAccesMenu implements IfaceAccesMenu {

    @Autowired
    EjbAccesMenu ejb;

    @Override
    public List<AccesMenu> getAccesosMenuPorIdRol(BigDecimal idRol) {
        try {
            ArrayList<AccesMenu> lstMenuAcces = new ArrayList<AccesMenu>();

               
            List<Object[]> lstObject = ejb.getAccesosMenuPorIdRol(idRol);

            for (Object[] obj : lstObject) {
                AccesMenu accesMenu = new AccesMenu();
                accesMenu.setIdMenu(new BigDecimal(obj[0].toString()));
                accesMenu.setDescripcion((obj[1] == null ? "" : obj[1].toString()));
                accesMenu.setTipo((obj[2] == null ? "" : obj[2].toString()));
                accesMenu.setNivel((obj[3] == null ? "" : obj[3].toString()));
                accesMenu.setUrlSistema((obj[4] == null ? "" : obj[4].toString()));
                int estatus = Integer.parseInt(obj[5].toString());
                accesMenu.setEstatus(estatus > 0 ? true : false);
                lstMenuAcces.add(accesMenu);

            }
            return lstMenuAcces;
        } catch (Exception ex) {
            Logger.getLogger(ServiceAccesMenu.class.getName()).log(Level.SEVERE, null, ex);
            return null;

        }
    }

    @Override
    public Pagina<AccesMenu> findAll(Pageable pageable) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pagina<AccesMenu> findAllDominio(AccesMenu filters, int first, int pageSize) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AccesMenu getById(BigDecimal dominio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AccesMenu getById(String dominio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int create(AccesMenu dominio) {
        try {

               

            List<Object[]> lstExiste = ejb.exist(dominio.getIdRol(), dominio.getIdMenu());
            if (dominio.isEstatus()) {

                if (lstExiste.isEmpty()) {
                    return ejb.create(dominio.getIdRol(), dominio.getIdMenu());
                }

            } else if (!lstExiste.isEmpty()) {
                return ejb.delete(dominio.getIdRol(), dominio.getIdMenu());
            }

            return 0;
        } catch (Exception ex) {
            Logger.getLogger(ServiceAccesMenu.class.getName()).log(Level.SEVERE, null, ex);
            return 0;

        }

    }

    @Override
    public int update(AccesMenu dominio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AccesMenu> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(BigDecimal id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
