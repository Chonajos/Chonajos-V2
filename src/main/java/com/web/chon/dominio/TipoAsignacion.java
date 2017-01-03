package com.web.chon.dominio;


import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TIPO_ASIGNACION database table.
 * 
 */
@Entity
@Table(name="TIPO_ASIGNACION")
@NamedQuery(name="TipoAsignacion.findAll", query="SELECT t FROM TipoAsignacion t")
public class TipoAsignacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TIP_ID", unique=true, nullable=false, length=4)
	private String tipId;

	@Column(name="TIP_DESCRIPCION", nullable=false, length=30)
	private String tipDescripcion;

	@Column(name="TIP_STATUS", nullable=false, length=1)
	private String tipStatus;

	public TipoAsignacion() {
	}

	public String getTipId() {
		return this.tipId;
	}

	public void setTipId(String tipId) {
		this.tipId = tipId;
	}

	public String getTipDescripcion() {
		return this.tipDescripcion;
	}

	public void setTipDescripcion(String tipDescripcion) {
		this.tipDescripcion = tipDescripcion;
	}

	public String getTipStatus() {
		return this.tipStatus;
	}

	public void setTipStatus(String tipStatus) {
		this.tipStatus = tipStatus;
	}

}