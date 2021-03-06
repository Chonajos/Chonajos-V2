package com.web.chon.service;

import com.web.chon.dominio.AjusteExistenciaMenudeo;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Juan de la Cruz
 */
public interface IfaceAjusteExistenciaMenudeo {

    /**
     * Inserta los ajustes realizados en existencia de productos menudeo
     *
     * @param data
     * @return
     */
    public int insert(AjusteExistenciaMenudeo data);

    public int update(AjusteExistenciaMenudeo data);

    /**
     * Obtiene todos los ajustes que se an realizado en existencia de productos
     * menudeo
     *
     * @return
     */
    public List<AjusteExistenciaMenudeo> getAll();

    /**
     * Obtiene todos los ajustes que se an realizado en existencia de productos
     * menudeo de una sucursal
     *
     * @param idSucursal
     * @return
     */
    public List<AjusteExistenciaMenudeo> getAllByIdSucursal(BigDecimal idSucursal);

}
