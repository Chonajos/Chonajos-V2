
package com.web.chon.util;

public enum Perfiles {
	
	ADMINISTRADOR_SISTEMA("Administrador Sistema"), 
	DIRECTOR_DE_NEGOCIO	("DIRECTOR DE NEGOCIO"),
	DIRECTOR_GENERAL	("DIRECTOR GENERAL"),
	ADMINISTRADOR_DE_PROYECTOS	("ADMINISTRADOR DE PROYECTOS"),
	ASISTENTE_DE_RECURSOS_HUMANOS	("ASISTENTE DE RECURSOS HUMANOS"),
	ADMINISTRADOR_SEGURIDAD	("ADMINISTRADOR SEGURIDAD"),
	GERENTE_DE_NEGOCIOS_EN_DALLAS	("GERENTE DE NEGOCIOS EN DALLAS"),
	EMPLEADO_PRAXIS_CON_REPORTES	("EMPLEADO PRAXIS CON REPORTES"),
	EMPLEADO_PRAXIS	("EMPLEADO PRAXIS"),
	ANALISTA_DE_REC_Y_SEL_LIDER	("ANALISTA DE REC Y SEL LIDER"),
	REGISTRO_DE_CURRICULUMS	("REGISTRO DE CURRICULUMS"),
	EMPLEADO_PRAXIS_CON_INCIDENCIA	("EMPLEADO PRAXIS CON INCIDENCIA"),
	GERENTE_DE_NEGOCIO	("GERENTE DE NEGOCIO"),
	BECARIO_DE_PROYECTOS	("BECARIO DE PROYECTOS"),
	EMPLEADO_PRAXIS_INCIDEN_REPORT	("EMPLEADO PRAXIS INCIDEN REPORT"),
	COORDINADOR_DE_SISTEMAS	("COORDINADOR DE SISTEMAS"),
	GERENTE_DE_NEGOCIO_ESPECIALIDA	("GERENTE DE NEGOCIO ESPECIALIDA"),
	COORDINADOR_DE_MERCADOTECNIA	("COORDINADOR DE MERCADOTECNIA"),
	JEFE_DE_COMPRAS_Y_OFICINA	("JEFE DE COMPRAS Y OFICINA"),
	COORDINADOR_RECURSOS_HUMANOS	("COORDINADOR RECURSOS HUMANOS"),
	DIRECTOR_DE_ADMINISTRACION_Y_F	("DIRECTOR DE ADMINISTRACION Y F"),
	DIRECCION_DE_PRACTICA_CMM	("DIRECCION DE PRACTICA CMM"),
	RESPONSABLE_DE_CC	("RESPONSABLE DE CC"),
	EMPLEADO_PRAXIS_CON_REQUI	("EMPLEADO PRAXIS CON REQUI"),
	ANALISTA_DE_RECURSOS_LIDER	("ANALISTA DE RECURSOS LIDER"),
	ANALISTA_DE_ASIGNACIONES_RH	("ANALISTA DE ASIGNACIONES - RH"),
	CONSULTA_REPORTES_PROYECTOS	("CONSULTA REPORTES-PROYECTOS"),
	CAPTURA_Y_REQUERIMIENTOS	("CAPTURA Y REQUERIMIENTOS"),
	ANALISTA_DE_PROYECTOS_SR	("ANALISTA DE PROYECTOS SR"),
	CONSULTOR_DE_NEGOCIO_SR	("CONSULTOR DE NEGOCIO SR"),
	ANALISTA_DE_NOMINA	("ANALISTA DE NOMINA"),
	SOPORTE	("SOPORTE"),
	PRAXIS_ASIGNACIONES_CONSULTA	("PRAXIS ASIGNACIONES CONSULTA"),
	ADMINISTRADOR	("ADMINISTRADOR"),
	EMPLEADO_PRAXIS_CON_CONOCIMIEN	("EMPLEADO PRAXIS CON CONOCIMIEN"),
	AUDITORIA	("AUDITORIA"),
	EMPLEADO_PRAXIS_CON_CV	("EMPLEADO PRAXIS CON CV"),
	EXTERNO	("EXTERNO"),
	PORPUESTA_DE_RECSELEC	("PORPUESTA DE RECSELEC"),
	ADMINISTRADOR_TEMPORAL_DF	("ADMINISTRADOR TEMPORAL DF");

	private final String perfil;

	Perfiles(String perfil) {
		this.perfil = perfil;
	}

	public String getPerfil() {
		return perfil;
	}

	@Override
	public String toString() {
		return "Perfil[" + perfil + "]";
	}
}	