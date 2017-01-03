package com.web.chon.ejb;

import com.web.chon.dominio.TipoAsignacion;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Juan
 */
public interface UsuariosRepository extends JpaRepository<TipoAsignacion, String> {

    @Query(value = " SELECT men.id_menu, men.descripcion, men.tipo, men.nivel, men.url_sistema FROM menu men, acces_menu acces  "
            + "WHERE acces.ID_ROL_FK = :perfilId AND acces.id_menu_fk = men.id_menu "
            + "ORDER BY men.NIVEL", nativeQuery = true)
    List<Object[]> getMenuByUser(@Param("perfilId") String perfilId);

    @Query(value = "  SELECT USU.CLAVE_USUARIO, "
            + "                     USU.NOMBRE_USUARIO, "
            + "                     USU.APATERNO_USUARIO, "
            + "                     USU.AMATERNO_USUARIO, "
            + "                     USU.CONTRASENA_USUARIO, "
            + "                     USU.FECHA_ALTA_USUARIO, "
            + "                     USU.ID_ROL_FK, "
            + "                     USU.ID_SUCURSAL_FK, "
            + "                     ROL.NOMBRE_ROL, "
            + "                     USU.ID_USUARIO_PK, "
            + "                     SUC.NOMBRE_SUCURSAL, "
            + "                     SUC.TELEFONO_SUCURSAL "
            + "                     FROM USUARIO USU "
            + "                     INNER JOIN ROL ROL ON USU.ID_ROL_FK =ROL.ID_ROL_PK "
            + "                     INNER JOIN SUCURSAL SUC ON USU.ID_SUCURSAL_FK = SUC.ID_SUCURSAL_PK"
            + "                     WHERE CLAVE_USUARIO = :idUser ", nativeQuery = true)
     List<Object[]> getUser(@Param("idUser") String idUser);

}
