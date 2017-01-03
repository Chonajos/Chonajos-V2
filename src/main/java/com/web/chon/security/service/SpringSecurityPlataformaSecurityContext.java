package com.web.chon.security.service;

import com.web.chon.core.eception.UsuarioNoAutenticadoException;
import com.web.chon.core.service.SistemaService;
import com.web.chon.dominio.UsuarioDominio;
import java.io.Serializable;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan
 */
/**
 * Wrapper de spring security {@link SecurityContext} para obtener el usuario
 * autenticado {@link Usuarios}.
 */
@Service
public class SpringSecurityPlataformaSecurityContext implements PlataformaSecurityContext, Serializable {

    private static final long serialVersionUID = 1L;
    @Autowired
    private SistemaService sistemaService;

    @Override
    public UsuarioDominio getUsuarioAutenticado() {

        UsuarioDominio usuarioActual = new UsuarioDominio();

        final SecurityContext context = SecurityContextHolder.getContext();
        if (context != null) {
            final Authentication auth = context.getAuthentication();
            if (auth != null) {
                usuarioActual = (UsuarioDominio) auth.getPrincipal();
            }
        }

        if (usuarioActual == null) {
            throw new UsuarioNoAutenticadoException();
        }

        usuarioActual.setUsuId(usuarioActual.getUsuId());

        return usuarioActual;
    }

    @Override
    public Date getFechaSistema() {
        return sistemaService.getFechaSistema();
    }

}
