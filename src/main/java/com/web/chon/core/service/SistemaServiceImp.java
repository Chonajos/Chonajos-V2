package com.web.chon.core.service;

import java.io.Serializable;
import java.util.Date;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SistemaServiceImp implements SistemaService, Serializable {

    private static final long serialVersionUID = 1L;
    @Autowired private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Transactional(readOnly = true)
    public Date getFechaSistema() {
        String query = "SELECT SYSDATE FROM DUAL";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Date fecha = jdbcTemplate.queryForObject(query, Date.class);
        return fecha;
    }
}
