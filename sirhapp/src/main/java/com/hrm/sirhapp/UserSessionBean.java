package com.hrm.sirhapp;

import com.hrm.sirhapp.util.LanguagesUtil;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Named
@SessionScoped
public class UserSessionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String infoMessageModule;
    private String infoMessage;

    private String aspirante;
    private String aspiranteDatosPersonales;
    private String aspiranteIdentidad;
    private String aspiranteRequisitos;
    private Integer aspiranteDocumento;
    private Integer aspiranteBeneficiario;
    private Integer aspiranteEscolaridad;
    private Integer aspiranteFamiliar;
    private Integer aspiranteExperiencia;

    private Integer trabajador;
    private Integer trabajadorDatosPersonales;
    private Integer trabajadorIdentidad;
    private Integer trabajadorDocumento;
    private Integer trabajadorBeneficiario;
    private Integer trabajadorEscolaridad;
    private Integer trabajadorFamiliar;
    private Integer trabajadorExperiencia;

    private Integer contrato;
    private Integer contratoTrabajador;
    private Integer contratoSanci;
    private Integer contratoPrypr;
    private Integer contratoApriv;
    private String contratoTapriv;
    private String contratoTprypr;
    private String contratoTsanci;

    private Integer inven;
    private String tinven;

    private String aspiranteKardex;
    private Integer trabajadorKardex;

    private String aspiranteTrabajador;
    private Integer trabajadorActualizacionClaves;
    private String aspiranteActualizacionRfc;

    private String sq_oetra_a;
    private String sq_odtra_a;
    private String sq_octra_a;

    private Integer idcat;
    private Integer idsed;
    private Integer idraz;
    private Integer iddep;
    private Integer idemp;
    private Integer idesp;
    private Integer idciv;
    private Integer idmes;
    private Integer idcom;
    private Integer idnac;
    private Integer idest;
    private Integer idrel;
    private Integer idsex;
    private Integer idsin;
    private Integer idstc;
    private Integer idstr;
    private Integer idtap;
    private Integer idtic;
    private Integer idtid;
    private Integer idtip;
    private Integer idpyp;
    private Integer idsan;
    private Integer idtur;
    private Integer idubi;
    private String cvusu;
    private Integer idnia;
    private Integer idtin;
    private Integer idinf;
    private Integer idtab;
    private Integer idpet;
    private Integer idrep;
    private Integer idcho;
    private Integer idrut;
    private Integer iduni;
    private Integer nueve;
    private Integer idcin;
    private Integer idhab;
    private Integer idmon;
    private Integer idpsa;
    private Integer idpro;

    private Boolean m1o0o0;
    private Boolean m1o1o0;
    private Boolean m1o2o0;
    private Boolean m2o0o0;
    private Boolean m3o0o0;
    private Boolean m4o0o0;
    private Boolean m5o0o0;
    private Boolean m5o1o0;
    private Boolean m5o2o0;
    private Boolean m5o3o0;
    private Boolean m5o4o0;
    private Boolean m5o5o0;
    private Boolean m5o6o0;
    private Boolean m5o7o0;
    private Boolean m5o8o0;
    private Boolean m5o9o0;
    private Boolean m5o10o0;
    private Boolean m6o0o0;
    private Boolean m6o1o0;
    private Boolean m6o2o0;
    private Boolean m6o3o0;
    private Boolean m6o4o0;
    private Boolean m6o5o0;
    private Boolean m6o6o0;
    private Boolean m6o7o0;
    private Boolean m6o8o0;
    private Boolean m6o9o0;
    private Boolean m7o0o0;
    private Boolean m7o1o0;
    private Boolean m7o2o0;
    private Boolean m7o3o0;
    private Boolean m7o4o0;
    private Boolean m7o4o1;
    private Boolean m7o4o2;
    private Boolean m7o4o3;
    private Boolean m7o4o4;
    private Boolean m7o4o5;
    private Boolean m7o4o6;
    private Boolean m7o4o7;
    private Boolean m7o5o0;
    private Boolean m7o5o1;
    private Boolean m7o5o2;
    private Boolean m7o5o3;
    private Boolean m7o6o0;
    private Boolean m8o0o0;
    private Boolean m8o1o0;
    private Boolean m8o2o0;
    private Boolean m8o3o0;
    private Boolean m8o4o0;
    private Boolean m9o0o0;
    private Boolean m9o1o0;
    private Boolean m9o2o0;
    private Boolean m10o0o0;
    private Boolean m10o1o0;
    private Boolean m10o2o0;
    private Boolean m10o3o0;
    private Boolean m10o4o0;
    private Boolean m10o5o0;
    private Boolean m10o6o0;
    private Boolean m10o7o0;
    private Boolean m10o8o0;
    private Boolean m10o9o0;
    private Boolean m10o10o0;
    private Boolean m10o11o0;
    private Boolean m10o12o0;
    private Boolean m10o13o0;
    private Boolean m10o14o0;
    private Boolean m10o15o0;
    private Boolean m10o16o0;
    private Boolean m10o17o0;
    private Boolean m10o18o0;
    private Boolean m10o19o0;
    private Boolean m11o0o0;
    private Boolean m11o1o0;
    private Boolean m11o2o0;
    private Boolean m11o3o0;
    private Boolean m11o4o0;
    private Boolean m11o5o0;
    private Boolean m11o6o0;
    private Boolean m12o0o0;
    private Boolean m12o1o0;
    private Boolean m12o2o0;
    private Boolean m12o3o0;
    private Boolean m12o4o0;
    private Boolean m13o0o0;
    private Boolean m14o0o0;

    @PostConstruct
    private void init() {
        this.infoMessageModule = "Servicio de la aplicacion SirhApp";
        sq_oetra_a = "";
        sq_odtra_a = "";
        sq_octra_a = "";
    }

    /**
     *
     */
    public void keepSessionAlive() {
        System.out.println(">>> La sesion esta activa... ");
    }

    /**
     *
     * @return
     */
    public String getAspiranteActualizacionRfc() {
        return aspiranteActualizacionRfc;
    }

    /**
     *
     * @param aspiranteActualizacionRfc
     */
    public void setAspiranteActualizacionRfc(String aspiranteActualizacionRfc) {
        this.aspiranteActualizacionRfc = aspiranteActualizacionRfc;
    }

    /**
     *
     * @return
     */
    public Integer getTrabajadorActualizacionClaves() {
        return trabajadorActualizacionClaves;
    }

    /**
     *
     * @param trabajadorActualizacionClaves
     */
    public void setTrabajadorActualizacionClaves(Integer trabajadorActualizacionClaves) {
        this.trabajadorActualizacionClaves = trabajadorActualizacionClaves;
    }

    /**
     *
     * @return
     */
    public String getAspiranteRequisitos() {
        return aspiranteRequisitos;
    }

    /**
     *
     * @param aspiranteRequisitos
     */
    public void setAspiranteRequisitos(String aspiranteRequisitos) {
        this.aspiranteRequisitos = aspiranteRequisitos;
    }

    /**
     *
     * @return
     */
    public Integer getContratoSanci() {
        return contratoSanci;
    }

    /**
     *
     * @param contratoSanci
     */
    public void setContratoSanci(Integer contratoSanci) {
        this.contratoSanci = contratoSanci;
    }

    /**
     *
     * @return
     */
    public Integer getContratoPrypr() {
        return contratoPrypr;
    }

    /**
     *
     * @param contratoPrypr
     */
    public void setContratoPrypr(Integer contratoPrypr) {
        this.contratoPrypr = contratoPrypr;
    }

    /**
     *
     * @return
     */
    public Integer getContratoApriv() {
        return contratoApriv;
    }

    /**
     *
     * @param contratoApriv
     */
    public void setContratoApriv(Integer contratoApriv) {
        this.contratoApriv = contratoApriv;
    }

    /**
     *
     * @return
     */
    public Integer getIdpro() {
        if (idpro == null) {
            idpro = -1;
        }
        return idpro;
    }

    /**
     *
     * @param idpro
     */
    public void setIdpro(Integer idpro) {
        this.idpro = idpro;
    }

    /**
     *
     * @return
     */
    public Integer getIdpsa() {
        if (idpsa == null) {
            idpsa = -1;
        }
        return idpsa;
    }

    /**
     *
     * @param idpsa
     */
    public void setIdpsa(Integer idpsa) {
        this.idpsa = idpsa;
    }

    /**
     *
     * @return
     */
    public Integer getIdmon() {
        if (idmon == null) {
            idmon = -1;
        }
        return idmon;
    }

    /**
     *
     * @param idmon
     */
    public void setIdmon(Integer idmon) {
        this.idmon = idmon;
    }

    /**
     *
     * @return
     */
    public Integer getIdhab() {
        if (idhab == null) {
            idhab = -1;
        }
        return idhab;
    }

    /**
     *
     * @param idhab
     */
    public void setIdhab(Integer idhab) {
        this.idhab = idhab;
    }

    /**
     *
     * @return
     */
    public Integer getIdcin() {
        if (idcin == null) {
            idcin = -1;
        }
        return idcin;
    }

    /**
     *
     * @param idcin
     */
    public void setIdcin(Integer idcin) {
        this.idcin = idcin;
    }

    /**
     *
     * @return
     */
    public Integer getNueve() {
        if (nueve == null) {
            nueve = -1;
        }
        return nueve;
    }

    /**
     *
     * @param nueve
     */
    public void setNueve(Integer nueve) {
        this.nueve = nueve;
    }

    /**
     *
     * @return
     */
    public Integer getIduni() {
        if (iduni == null) {
            iduni = -1;
        }
        return iduni;
    }

    /**
     *
     * @param iduni
     */
    public void setIduni(Integer iduni) {
        this.iduni = iduni;
    }

    /**
     *
     * @return
     */
    public Integer getIdrut() {
        if (idrut == null) {
            idrut = -1;
        }
        return idrut;
    }

    /**
     *
     * @param idrut
     */
    public void setIdrut(Integer idrut) {
        this.idrut = idrut;
    }

    /**
     *
     * @return
     */
    public Integer getIdcho() {
        if (idrep == null) {
            idrep = -1;
        }
        return idcho;
    }

    /**
     *
     * @param idcho
     */
    public void setIdcho(Integer idcho) {
        this.idcho = idcho;
    }

    /**
     *
     * @return
     */
    public Integer getIdrep() {
        if (idrep == null) {
            idrep = -1;
        }

        return idrep;
    }

    /**
     *
     * @param idrep
     */
    public void setIdrep(Integer idrep) {
        this.idrep = idrep;
    }

    /**
     *
     * @return
     */
    public Integer getIdpet() {
        if (idpet == null) {
            idpet = -1;
        }
        return idpet;
    }

    /**
     *
     * @param idpet
     */
    public void setIdpet(Integer idpet) {
        this.idpet = idpet;
    }

    /**
     *
     * @return
     */
    public Integer getIdtab() {
        if (idtab == null) {
            idtab = -1;
        }
        return idtab;
    }

    /**
     *
     * @param idtab
     */
    public void setIdtab(Integer idtab) {
        this.idtab = idtab;
    }

    /**
     *
     * @return
     */
    public Integer getIdinf() {
        if (idinf == null) {
            idinf = -1;
        }
        return idinf;
    }

    /**
     *
     * @param idinf
     */
    public void setIdinf(Integer idinf) {
        this.idinf = idinf;
    }

    /**
     *
     * @return
     */
    public Integer getIdtin() {
        if (idtin == null) {
            idtin = -1;
        }
        return idtin;
    }

    /**
     *
     * @param idtin
     */
    public void setIdtin(Integer idtin) {
        this.idtin = idtin;
    }

    /**
     *
     * @return
     */
    public Integer getIdnia() {
        if (idnia == null) {
            idnia = -1;
        }
        return idnia;
    }

    /**
     *
     * @param idnia
     */
    public void setIdnia(Integer idnia) {
        this.idnia = idnia;
    }

    /**
     *
     * @return
     */
    public String getCvusu() {
        if (cvusu.length() == 0) {
            cvusu = "";
        }
        return cvusu;
    }

    /**
     *
     * @param cvusu
     */
    public void setCvusu(String cvusu) {
        this.cvusu = cvusu;
    }

    /**
     *
     * @return
     */
    public Integer getIdubi() {
        if (idubi == null) {
            idubi = -1;
        }
        return idubi;
    }

    /**
     *
     * @param idubi
     */
    public void setIdubi(Integer idubi) {
        this.idubi = idubi;
    }

    /**
     *
     * @return
     */
    public Integer getIdtur() {
        if (idtur == null) {
            idtur = -1;
        }
        return idtur;
    }

    /**
     *
     * @param idtur
     */
    public void setIdtur(Integer idtur) {
        this.idtur = idtur;
    }

    /**
     *
     * @return
     */
    public Integer getIdsan() {
        if (idsan == null) {
            idsan = -1;
        }
        return idsan;
    }

    /**
     *
     * @param idsan
     */
    public void setIdsan(Integer idsan) {
        this.idsan = idsan;
    }

    /**
     *
     * @return
     */
    public Integer getIdpyp() {
        if (idpyp == null) {
            idpyp = -1;
        }
        return idpyp;
    }

    /**
     *
     * @param idpyp
     */
    public void setIdpyp(Integer idpyp) {
        this.idpyp = idpyp;
    }

    /**
     *
     * @return
     */
    public Integer getIdtip() {
        if (idtip == null) {
            idtip = -1;
        }
        return idtip;
    }

    /**
     *
     * @param idtip
     */
    public void setIdtip(Integer idtip) {
        this.idtip = idtip;
    }

    /**
     *
     * @return
     */
    public Integer getIdtid() {
        if (idtid == null) {
            idtid = -1;
        }
        return idtid;
    }

    /**
     *
     * @param idtid
     */
    public void setIdtid(Integer idtid) {
        this.idtid = idtid;
    }

    /**
     *
     * @return
     */
    public Integer getIdtic() {
        if (idtic == null) {
            idtic = -1;
        }
        return idtic;
    }

    /**
     *
     * @param idtic
     */
    public void setIdtic(Integer idtic) {
        this.idtic = idtic;
    }

    /**
     *
     * @return
     */
    public Integer getIdtap() {
        if (idtap == null) {
            idtap = -1;
        }
        return idtap;
    }

    /**
     *
     * @param idtap
     */
    public void setIdtap(Integer idtap) {
        this.idtap = idtap;
    }

    /**
     *
     * @return
     */
    public Integer getIdstr() {
        if (idstr == null) {
            idstr = -1;
        }
        return idstr;
    }

    /**
     *
     * @param idstr
     */
    public void setIdstr(Integer idstr) {
        this.idstr = idstr;
    }

    /**
     *
     * @return
     */
    public Integer getIdstc() {
        if (idstc == null) {
            idstc = -1;
        }
        return idstc;
    }

    /**
     *
     * @param idstc
     */
    public void setIdstc(Integer idstc) {
        this.idstc = idstc;
    }

    /**
     *
     * @return
     */
    public Integer getIdsin() {
        if (idsin == null) {
            idsin = -1;
        }
        return idsin;
    }

    /**
     *
     * @param idsin
     */
    public void setIdsin(Integer idsin) {
        this.idsin = idsin;
    }

    /**
     *
     * @return
     */
    public Integer getIdsex() {
        if (idsex == null) {
            idsex = -1;
        }
        return idsex;
    }

    /**
     *
     * @param idsex
     */
    public void setIdsex(Integer idsex) {
        this.idsex = idsex;
    }

    /**
     *
     * @return
     */
    public Integer getIdrel() {
        if (idrel == null) {
            idrel = -1;
        }
        return idrel;
    }

    /**
     *
     * @param idrel
     */
    public void setIdrel(Integer idrel) {
        this.idrel = idrel;
    }

    /**
     *
     * @return
     */
    public Integer getIdest() {
        if (idest == null) {
            idest = -1;
        }
        return idest;
    }

    /**
     *
     * @param idest
     */
    public void setIdest(Integer idest) {
        this.idest = idest;
    }

    /**
     *
     * @return
     */
    public Integer getIdnac() {
        if (idnac == null) {
            idnac = -1;
        }
        return idnac;
    }

    /**
     *
     * @param idnac
     */
    public void setIdnac(Integer idnac) {
        this.idnac = idnac;
    }

    /**
     *
     * @return
     */
    public Integer getIdcom() {
        if (idcom == null) {
            idcom = -1;
        }
        return idcom;
    }

    /**
     *
     * @param idcom
     */
    public void setIdcom(Integer idcom) {
        this.idcom = idcom;
    }

    /**
     *
     * @return
     */
    public Integer getIdmes() {
        if (idmes == null) {
            idmes = -1;
        }
        return idmes;
    }

    /**
     *
     * @param idmes
     */
    public void setIdmes(Integer idmes) {
        this.idmes = idmes;
    }

    /**
     *
     * @return
     */
    public Integer getIdciv() {
        if (idciv == null) {
            idciv = -1;
        }
        return idciv;
    }

    /**
     *
     * @param idciv
     */
    public void setIdciv(Integer idciv) {
        this.idciv = idciv;
    }

    /**
     *
     * @return
     */
    public Integer getIdesp() {
        if (idesp == null) {
            idesp = -1;
        }
        return idesp;
    }

    /**
     *
     * @param idesp
     */
    public void setIdesp(Integer idesp) {
        this.idesp = idesp;
    }

    /**
     *
     * @return
     */
    public Integer getIdemp() {
        if (idemp == null) {
            idemp = -1;
        }
        return idemp;
    }

    /**
     *
     * @param idemp
     */
    public void setIdemp(Integer idemp) {
        this.idemp = idemp;
    }

    /**
     *
     * @return
     */
    public Integer getIddep() {
        if (iddep == null) {
            iddep = -1;
        }
        return iddep;
    }

    /**
     *
     * @param iddep
     */
    public void setIddep(Integer iddep) {
        this.iddep = iddep;
    }

    /**
     *
     * @return
     */
    public Integer getIdraz() {
        if (idraz == null) {
            idraz = -1;
        }
        return idraz;
    }

    /**
     *
     * @param idraz
     */
    public void setIdraz(Integer idraz) {
        this.idraz = idraz;
    }

    /**
     *
     * @return
     */
    public Integer getIdcat() {
        if (idcat == null) {
            idcat = -1;
        }
        return idcat;
    }

    /**
     *
     * @param idcat
     */
    public void setIdcat(Integer idcat) {
        this.idcat = idcat;
    }

    /**
     *
     * @return
     */
    public Integer getIdsed() {
        if (idsed == null) {
            idsed = -1;
        }
        return idsed;
    }

    /**
     *
     * @param idsed
     */
    public void setIdsed(Integer idsed) {
        this.idsed = idsed;
    }

    /**
     *
     * @return
     */
    public String getAspiranteKardex() {
        return aspiranteKardex;
    }

    /**
     *
     * @param aspiranteKardex
     */
    public void setAspiranteKardex(String aspiranteKardex) {
        this.aspiranteKardex = aspiranteKardex;
    }

    /**
     *
     * @return
     */
    public Integer getTrabajadorKardex() {
        return trabajadorKardex;
    }

    /**
     *
     * @param trabajadorKardex
     */
    public void setTrabajadorKardex(Integer trabajadorKardex) {
        this.trabajadorKardex = trabajadorKardex;
    }

    /**
     *
     * @return
     */
    public String getAspiranteTrabajador() {
        return aspiranteTrabajador;
    }

    /**
     *
     * @param aspiranteTrabajador
     */
    public void setAspiranteTrabajador(String aspiranteTrabajador) {
        this.aspiranteTrabajador = aspiranteTrabajador;
    }

    /**
     *
     * @return
     */
    public String getInfoMessageModule() {
        return infoMessageModule;
    }

    /**
     *
     * @param infoMessageModule
     */
    public void setInfoMessageModule(String infoMessageModule) {
        this.infoMessageModule = infoMessageModule;
    }

    /**
     *
     * @return
     */
    public String getInfoMessage() {
        return infoMessage;
    }

    /**
     *
     * @param infoMessage
     */
    public void setInfoMessage(String infoMessage) {
        this.infoMessage = infoMessage;
    }

    /**
     *
     * @return
     */
    public Integer getContrato() {
        if (contrato == null) {
            contrato = -1;
        }
        return contrato;
    }

    /**
     *
     * @param contrato
     */
    public void setContrato(Integer contrato) {
        this.contrato = contrato;
    }

    /**
     *
     * @return
     */
    public Integer getContratoTrabajador() {
        if (contratoTrabajador == null) {
            contratoTrabajador = -1;
        }
        return contratoTrabajador;
    }

    /**
     *
     * @param contratoTrabajador
     */
    public void setContratoTrabajador(Integer contratoTrabajador) {
        this.contratoTrabajador = contratoTrabajador;
    }

    /**
     *
     * @return
     */
    public String getContratoTapriv() {
        if (contratoTapriv == null) {
            contratoTapriv = "";
        }
        return contratoTapriv;
    }

    /**
     *
     * @param contratoTapriv
     */
    public void setContratoTapriv(String contratoTapriv) {
        this.contratoTapriv = contratoTapriv;
    }

    /**
     *
     * @return
     */
    public String getContratoTprypr() {
        if (contratoTprypr == null) {
            contratoTprypr = "";
        }
        return contratoTprypr;
    }

    /**
     *
     * @param contratoTprypr
     */
    public void setContratoTprypr(String contratoTprypr) {
        this.contratoTprypr = contratoTprypr;
    }

    /**
     *
     * @return
     */
    public String getContratoTsanci() {
        if (contratoTsanci == null) {
            contratoTsanci = "";
        }
        return contratoTsanci;
    }

    /**
     *
     * @param contratoTsanci
     */
    public void setContratoTsanci(String contratoTsanci) {
        this.contratoTsanci = contratoTsanci;
    }

    /**
     *
     * @return
     */
    public Integer getInven() {
        if (inven == null) {
            inven = -1;
        }
        return inven;
    }

    /**
     *
     * @param inven
     */
    public void setInven(Integer inven) {
        this.inven = inven;
    }

    /**
     *
     * @return
     */
    public String getTinven() {
        if (tinven == null) {
            tinven = "";
        }
        return tinven;
    }

    /**
     *
     * @param tinven
     */
    public void setTinven(String tinven) {
        this.tinven = tinven;
    }

    /**
     *
     * @return
     */
    public Integer getAspiranteExperiencia() {
        return aspiranteExperiencia;
    }

    /**
     *
     * @param aspiranteExperiencia
     */
    public void setAspiranteExperiencia(Integer aspiranteExperiencia) {
        this.aspiranteExperiencia = aspiranteExperiencia;
    }

    /**
     *
     * @return
     */
    public Integer getAspiranteFamiliar() {
        if (aspiranteFamiliar == null) {
            aspiranteFamiliar = -1;
        }
        return aspiranteFamiliar;
    }

    /**
     *
     * @param aspiranteFamiliar
     */
    public void setAspiranteFamiliar(Integer aspiranteFamiliar) {
        this.aspiranteFamiliar = aspiranteFamiliar;
    }

    /**
     *
     * @return
     */
    public Integer getAspiranteEscolaridad() {
        if (aspiranteEscolaridad == null) {
            aspiranteEscolaridad = -1;
        }
        return aspiranteEscolaridad;
    }

    /**
     *
     * @param aspiranteEscolaridad
     */
    public void setAspiranteEscolaridad(Integer aspiranteEscolaridad) {
        this.aspiranteEscolaridad = aspiranteEscolaridad;
    }

    /**
     *
     * @return
     */
    public Integer getAspiranteBeneficiario() {
        if (aspiranteBeneficiario == null) {
            aspiranteBeneficiario = -1;
        }
        return aspiranteBeneficiario;
    }

    /**
     *
     * @param aspiranteBeneficiario
     */
    public void setAspiranteBeneficiario(Integer aspiranteBeneficiario) {
        this.aspiranteBeneficiario = aspiranteBeneficiario;
    }

    /**
     *
     * @return
     */
    public Integer getAspiranteDocumento() {
        if (aspiranteDocumento == null) {
            aspiranteDocumento = -1;
        }
        return aspiranteDocumento;
    }

    /**
     *
     * @param aspiranteDocumento
     */
    public void setAspiranteDocumento(Integer aspiranteDocumento) {
        this.aspiranteDocumento = aspiranteDocumento;
    }

    /**
     *
     * @return
     */
    public String getAspirante() {
        if (aspirante == null) {
            aspirante = "";
        }
        return aspirante;
    }

    /**
     *
     * @param aspirante
     */
    public void setAspirante(String aspirante) {
        this.aspirante = aspirante;
    }

    /**
     *
     * @return
     */
    public String getAspiranteDatosPersonales() {
        if (aspiranteDatosPersonales.length() == 0) {
            aspiranteDatosPersonales = "";
        }
        return aspiranteDatosPersonales;
    }

    /**
     *
     * @param aspiranteDatosPersonales
     */
    public void setAspiranteDatosPersonales(String aspiranteDatosPersonales) {
        this.aspiranteDatosPersonales = aspiranteDatosPersonales;
    }

    /**
     *
     * @return
     */
    public Integer getTrabajadorExperiencia() {
        return trabajadorExperiencia;
    }

    /**
     *
     * @param trabajadorExperiencia
     */
    public void setTrabajadorExperiencia(Integer trabajadorExperiencia) {
        this.trabajadorExperiencia = trabajadorExperiencia;
    }

    /**
     *
     * @return
     */
    public Integer getTrabajadorFamiliar() {
        if (trabajadorFamiliar == null) {
            trabajadorFamiliar = -1;
        }
        return trabajadorFamiliar;
    }

    /**
     *
     * @param trabajadorFamiliar
     */
    public void setTrabajadorFamiliar(Integer trabajadorFamiliar) {
        this.trabajadorFamiliar = trabajadorFamiliar;
    }

    /**
     *
     * @return
     */
    public Integer getTrabajadorEscolaridad() {
        if (trabajadorEscolaridad == null) {
            trabajadorEscolaridad = -1;
        }
        return trabajadorEscolaridad;
    }

    /**
     *
     * @param trabajadorEscolaridad
     */
    public void setTrabajadorEscolaridad(Integer trabajadorEscolaridad) {
        this.trabajadorEscolaridad = trabajadorEscolaridad;
    }

    /**
     *
     * @return
     */
    public Integer getTrabajadorBeneficiario() {
        if (trabajadorBeneficiario == null) {
            trabajadorBeneficiario = -1;
        }
        return trabajadorBeneficiario;
    }

    /**
     *
     * @param trabajadorBeneficiario
     */
    public void setTrabajadorBeneficiario(Integer trabajadorBeneficiario) {
        this.trabajadorBeneficiario = trabajadorBeneficiario;
    }

    /**
     *
     * @return
     */
    public Integer getTrabajadorDocumento() {
        if (trabajadorDocumento == null) {
            trabajadorDocumento = -1;
        }
        return trabajadorDocumento;
    }

    /**
     *
     * @param trabajadorDocumento
     */
    public void setTrabajadorDocumento(Integer trabajadorDocumento) {
        this.trabajadorDocumento = trabajadorDocumento;
    }

    /**
     *
     * @return
     */
    public Integer getTrabajador() {
        if (trabajador == null) {
            trabajador = -1;
        }
        return trabajador;
    }

    /**
     *
     * @param trabajador
     */
    public void setTrabajador(Integer trabajador) {
        this.trabajador = trabajador;
    }

    /**
     *
     * @return
     */
    public Integer getTrabajadorDatosPersonales() {
        if (trabajadorDatosPersonales == null) {
            trabajadorDatosPersonales = -1;
        }
        return trabajadorDatosPersonales;
    }

    /**
     *
     * @param trabajadorDatosPersonales
     */
    public void setTrabajadorDatosPersonales(Integer trabajadorDatosPersonales) {
        this.trabajadorDatosPersonales = trabajadorDatosPersonales;
    }

    /**
     *
     * @return
     */
    public String getAspiranteIdentidad() {
        return aspiranteIdentidad;
    }

    /**
     *
     * @param aspiranteIdentidad
     */
    public void setAspiranteIdentidad(String aspiranteIdentidad) {
        this.aspiranteIdentidad = aspiranteIdentidad;
    }

    /**
     *
     * @return
     */
    public Integer getTrabajadorIdentidad() {
        return trabajadorIdentidad;
    }

    /**
     *
     * @param trabajadorIdentidad
     */
    public void setTrabajadorIdentidad(Integer trabajadorIdentidad) {
        this.trabajadorIdentidad = trabajadorIdentidad;
    }

    private void cleanVars() {

        this.aspirante = "";
        this.aspiranteDatosPersonales = "";
        this.aspiranteIdentidad = "";
        this.aspiranteRequisitos = "";
        this.aspiranteDocumento = -1;
        this.aspiranteBeneficiario = -1;
        this.aspiranteEscolaridad = -1;
        this.aspiranteFamiliar = -1;
        this.aspiranteExperiencia = -1;

        this.trabajador = -1;
        this.trabajadorDatosPersonales = -1;
        this.trabajadorIdentidad = -1;
        this.trabajadorDocumento = -1;
        this.trabajadorBeneficiario = -1;
        this.trabajadorEscolaridad = -1;
        this.trabajadorFamiliar = -1;
        this.trabajadorExperiencia = -1;

        this.inven = -1;
        this.tinven = "";

        this.aspiranteKardex = "";
        this.trabajadorKardex = -1;

        this.aspiranteTrabajador = "";
        this.trabajadorActualizacionClaves = -1;
        this.aspiranteActualizacionRfc = "";

        this.contratoTrabajador = -1;
        this.contrato = -1;
        this.contratoTprypr = "";
        this.contratoTapriv = "";
        this.contratoTsanci = "";
        this.contratoPrypr = -1;
        this.contratoApriv = -1;
        this.contratoSanci = -1;

    }

    private void cleanVarsContrato() {
        this.contratoTprypr = "";
        this.contratoTapriv = "";
        this.contratoTsanci = "";
        this.contratoPrypr = -1;
        this.contratoApriv = -1;
        this.contratoSanci = -1;
    }

    /**
     *
     * @param action
     * @return
     * @throws IOException
     */
    public String goSistema(int action) throws IOException {
        cleanVars();

        //action==1
        String outcome = new LanguagesUtil().getBundle("StartPage");

        if (action == -1) {
            outcome = "/login.xhtml";
        }

        if (action == 0) {
            outcome = "/logout";
        }
        if (action == 1) {
            outcome = "/secured/empty.xhtml";
        }
        if (action == 3) {
            outcome = "/secured/procesos/actualizacionRfc.xhtml";
        }
        if (action == 4) {
            outcome = "/secured/procesos/actualizacionCategorias.xhtml";
        }
        if (action == 5) {
            outcome = "/secured/procesos/findActualizacionClaves.xhtml";
        }
        if (action == 6) {
            outcome = "/secured/ayuda.xhtml";
        }
        if (action == 7) {
            outcome = "/secured/auditoria/auditoria.xhtml";
        }
        if (action == 8) {
            outcome = "/secured/nuevoaspirante/capturaCorta.xhtml";
        }
        if (action == 9) {
            outcome = "/secured/nuevoaspirante/capturaLarga.xhtml";
        }

        try {

            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + outcome);
        } catch (Exception e) {
        }

        return outcome;
    }

    /**
     *
     * @param action
     * @return
     * @throws IOException
     */
    public String goReportes(int action) throws IOException {
        String outcome = new LanguagesUtil().getBundle("StartPage");
        if (action == 0) {
            outcome = "/secured/reportes/find.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;
    }

    /**
     *
     * @param action
     * @return
     * @throws IOException
     */
    public String goKardex(int action) throws IOException {
        String outcome = new LanguagesUtil().getBundle("StartPage");

        if (action == 0) {
            cleanVars();
            outcome = "/secured/kardex/findAspirantes.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            cleanVars();
            outcome = "/secured/kardex/findTrabajadores.xhtml?faces-redirect=true";
        }
        if (action == 2) {
            outcome = "/secured/kardex/aspirantes.xhtml?faces-redirect=true";
        }
        if (action == 3) {
            outcome = "/secured/kardex/trabajadores.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;
    }

    /**
     *
     * @param action
     * @return
     * @throws IOException
     */
    public String goAspiranteTrabajador(int action) throws IOException {

        String outcome = new LanguagesUtil().getBundle("StartPage");

        if (action == 0) {
            outcome = "/secured/procesos/findAspiranteTrabajador.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            outcome = "/secured/procesos/aspiranteTrabajador.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;
    }

    /**
     *
     * @param action
     * @return
     * @throws IOException
     */
    public String goBusquedaAspirantes(int action) throws IOException {
        cleanVars();
        String outcome = new LanguagesUtil().getBundle("StartPage");

        if (action == 2) {
            outcome = "/secured/aspirantes/find.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;
    }

    /**
     *
     * @param action
     * @return
     * @throws IOException
     */
    public String goBusquedaTrabajadores(int action) throws IOException {
        cleanVars();
        String outcome = new LanguagesUtil().getBundle("StartPage");

        if (action == 2) {
            outcome = "/secured/trabajadores/find.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;
    }

    /**
     *
     * @param action
     * @return
     * @throws IOException
     */
    public String goBusquedaContratos(int action) throws IOException {
        cleanVarsContrato();
        String outcome = new LanguagesUtil().getBundle("StartPage");

        if (action == 0) {
            this.contrato = -1;
            outcome = "/secured/contratos/contratoCreate.xhtml?faces-redirect=true";
        }
        if (action == 1) {
            this.contratoTrabajador = -1;
            this.contrato = -1;
            outcome = "/secured/contratos/find.xhtml?faces-redirect=true";
        }
        if (action == 2) {
            this.contratoTrabajador = -1;
            this.contrato = -1;
            outcome = "/secured/contratos/find.xhtml?faces-redirect=true";
        }
        if (action == 3) {
            outcome = "/secured/contratos/contratoView.xhtml?faces-redirect=true";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;
    }

    /**
     *
     * @param action
     * @return
     * @throws IOException
     */
    public String goBusquedaApriv(String action) throws IOException {
        cleanVarsContrato();
        String outcome;
        if (action.length() == 0) {
            this.contratoTapriv = "TODOS";
        } else {
            this.contratoTapriv = action;
        }

        outcome = "/secured/contratos/aprivList.xhtml?faces-redirect=true";

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;
    }

    /**
     *
     * @param action
     * @return
     * @throws IOException
     */
    public String goBusquedaPrypr(String action) throws IOException {
        cleanVarsContrato();
        String outcome;
        if (action.length() == 0) {
            this.contratoTprypr = "TODOS";
        } else {
            this.contratoTprypr = action;
        }

        outcome = "/secured/contratos/pryprList.xhtml?faces-redirect=true";

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;
    }

    /**
     *
     * @param action
     * @return
     * @throws IOException
     */
    public String goBusquedaSanci(String action) throws IOException {
        cleanVarsContrato();
        String outcome;
        if (action.length() == 0) {
            this.contratoTsanci = "TODAS";
        } else {
            this.contratoTsanci = action;
        }

        outcome = "/secured/contratos/sanciList.xhtml?faces-redirect=true";

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;
    }

    /**
     *
     * @param action
     * @return
     * @throws IOException
     */
    public String goBusquedaInven(String action) throws IOException {
        this.tinven = action;
        String outcome;
        if (action.length() == 0) {
            this.tinven = "EXISTENCIAS";
        } else {
            this.tinven = action;
        }

        outcome = "/secured/inventarios/inventarioList.xhtml?faces-redirect=true";

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + outcome);

        return outcome;
    }

    /**
     *
     * @return
     */
    public Boolean getM1o0o0() {
        return m1o0o0;
    }

    /**
     *
     * @param m1o0o0
     */
    public void setM1o0o0(Boolean m1o0o0) {
        this.m1o0o0 = m1o0o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM1o1o0() {
        return m1o1o0;
    }

    /**
     *
     * @param m1o1o0
     */
    public void setM1o1o0(Boolean m1o1o0) {
        this.m1o1o0 = m1o1o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM1o2o0() {
        return m1o2o0;
    }

    /**
     *
     * @param m1o2o0
     */
    public void setM1o2o0(Boolean m1o2o0) {
        this.m1o2o0 = m1o2o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM2o0o0() {
        return m2o0o0;
    }

    /**
     *
     * @param m2o0o0
     */
    public void setM2o0o0(Boolean m2o0o0) {
        this.m2o0o0 = m2o0o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM3o0o0() {
        return m3o0o0;
    }

    /**
     *
     * @param m3o0o0
     */
    public void setM3o0o0(Boolean m3o0o0) {
        this.m3o0o0 = m3o0o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM4o0o0() {
        return m4o0o0;
    }

    /**
     *
     * @param m4o0o0
     */
    public void setM4o0o0(Boolean m4o0o0) {
        this.m4o0o0 = m4o0o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM5o0o0() {
        return m5o0o0;
    }

    /**
     *
     * @param m5o0o0
     */
    public void setM5o0o0(Boolean m5o0o0) {
        this.m5o0o0 = m5o0o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM5o1o0() {
        return m5o1o0;
    }

    /**
     *
     * @param m5o1o0
     */
    public void setM5o1o0(Boolean m5o1o0) {
        this.m5o1o0 = m5o1o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM5o2o0() {
        return m5o2o0;
    }

    /**
     *
     * @param m5o2o0
     */
    public void setM5o2o0(Boolean m5o2o0) {
        this.m5o2o0 = m5o2o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM5o3o0() {
        return m5o3o0;
    }

    /**
     *
     * @param m5o3o0
     */
    public void setM5o3o0(Boolean m5o3o0) {
        this.m5o3o0 = m5o3o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM5o4o0() {
        return m5o4o0;
    }

    /**
     *
     * @param m5o4o0
     */
    public void setM5o4o0(Boolean m5o4o0) {
        this.m5o4o0 = m5o4o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM5o5o0() {
        return m5o5o0;
    }

    /**
     *
     * @param m5o5o0
     */
    public void setM5o5o0(Boolean m5o5o0) {
        this.m5o5o0 = m5o5o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM5o6o0() {
        return m5o6o0;
    }

    /**
     *
     * @param m5o6o0
     */
    public void setM5o6o0(Boolean m5o6o0) {
        this.m5o6o0 = m5o6o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM5o7o0() {
        return m5o7o0;
    }

    /**
     *
     * @param m5o7o0
     */
    public void setM5o7o0(Boolean m5o7o0) {
        this.m5o7o0 = m5o7o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM5o8o0() {
        return m5o8o0;
    }

    /**
     *
     * @param m5o8o0
     */
    public void setM5o8o0(Boolean m5o8o0) {
        this.m5o8o0 = m5o8o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM6o0o0() {
        return m6o0o0;
    }

    /**
     *
     * @param m6o0o0
     */
    public void setM6o0o0(Boolean m6o0o0) {
        this.m6o0o0 = m6o0o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM6o1o0() {
        return m6o1o0;
    }

    /**
     *
     * @param m6o1o0
     */
    public void setM6o1o0(Boolean m6o1o0) {
        this.m6o1o0 = m6o1o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM6o2o0() {
        return m6o2o0;
    }

    /**
     *
     * @param m6o2o0
     */
    public void setM6o2o0(Boolean m6o2o0) {
        this.m6o2o0 = m6o2o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM6o3o0() {
        return m6o3o0;
    }

    /**
     *
     * @param m6o3o0
     */
    public void setM6o3o0(Boolean m6o3o0) {
        this.m6o3o0 = m6o3o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM6o4o0() {
        return m6o4o0;
    }

    /**
     *
     * @param m6o4o0
     */
    public void setM6o4o0(Boolean m6o4o0) {
        this.m6o4o0 = m6o4o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM6o5o0() {
        return m6o5o0;
    }

    /**
     *
     * @param m6o5o0
     */
    public void setM6o5o0(Boolean m6o5o0) {
        this.m6o5o0 = m6o5o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM6o6o0() {
        return m6o6o0;
    }

    /**
     *
     * @param m6o6o0
     */
    public void setM6o6o0(Boolean m6o6o0) {
        this.m6o6o0 = m6o6o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM6o7o0() {
        return m6o7o0;
    }

    /**
     *
     * @param m6o7o0
     */
    public void setM6o7o0(Boolean m6o7o0) {
        this.m6o7o0 = m6o7o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM6o8o0() {
        return m6o8o0;
    }

    /**
     *
     * @param m6o8o0
     */
    public void setM6o8o0(Boolean m6o8o0) {
        this.m6o8o0 = m6o8o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM7o0o0() {
        return m7o0o0;
    }

    /**
     *
     * @param m7o0o0
     */
    public void setM7o0o0(Boolean m7o0o0) {
        this.m7o0o0 = m7o0o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM7o1o0() {
        return m7o1o0;
    }

    /**
     *
     * @param m7o1o0
     */
    public void setM7o1o0(Boolean m7o1o0) {
        this.m7o1o0 = m7o1o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM7o2o0() {
        return m7o2o0;
    }

    /**
     *
     * @param m7o2o0
     */
    public void setM7o2o0(Boolean m7o2o0) {
        this.m7o2o0 = m7o2o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM7o3o0() {
        return m7o3o0;
    }

    /**
     *
     * @param m7o3o0
     */
    public void setM7o3o0(Boolean m7o3o0) {
        this.m7o3o0 = m7o3o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM7o4o0() {
        return m7o4o0;
    }

    /**
     *
     * @param m7o4o0
     */
    public void setM7o4o0(Boolean m7o4o0) {
        this.m7o4o0 = m7o4o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM7o4o1() {
        return m7o4o1;
    }

    /**
     *
     * @param m7o4o1
     */
    public void setM7o4o1(Boolean m7o4o1) {
        this.m7o4o1 = m7o4o1;
    }

    /**
     *
     * @return
     */
    public Boolean getM7o4o2() {
        return m7o4o2;
    }

    /**
     *
     * @param m7o4o2
     */
    public void setM7o4o2(Boolean m7o4o2) {
        this.m7o4o2 = m7o4o2;
    }

    /**
     *
     * @return
     */
    public Boolean getM7o4o3() {
        return m7o4o3;
    }

    /**
     *
     * @param m7o4o3
     */
    public void setM7o4o3(Boolean m7o4o3) {
        this.m7o4o3 = m7o4o3;
    }

    /**
     *
     * @return
     */
    public Boolean getM7o5o0() {
        return m7o5o0;
    }

    /**
     *
     * @param m7o5o0
     */
    public void setM7o5o0(Boolean m7o5o0) {
        this.m7o5o0 = m7o5o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM8o0o0() {
        return m8o0o0;
    }

    /**
     *
     * @param m8o0o0
     */
    public void setM8o0o0(Boolean m8o0o0) {
        this.m8o0o0 = m8o0o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM8o1o0() {
        return m8o1o0;
    }

    /**
     *
     * @param m8o1o0
     */
    public void setM8o1o0(Boolean m8o1o0) {
        this.m8o1o0 = m8o1o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM8o2o0() {
        return m8o2o0;
    }

    /**
     *
     * @param m8o2o0
     */
    public void setM8o2o0(Boolean m8o2o0) {
        this.m8o2o0 = m8o2o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM8o3o0() {
        return m8o3o0;
    }

    /**
     *
     * @param m8o3o0
     */
    public void setM8o3o0(Boolean m8o3o0) {
        this.m8o3o0 = m8o3o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM8o4o0() {
        return m8o4o0;
    }

    /**
     *
     * @param m8o4o0
     */
    public void setM8o4o0(Boolean m8o4o0) {
        this.m8o4o0 = m8o4o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM9o0o0() {
        return m9o0o0;
    }

    /**
     *
     * @param m9o0o0
     */
    public void setM9o0o0(Boolean m9o0o0) {
        this.m9o0o0 = m9o0o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM9o1o0() {
        return m9o1o0;
    }

    /**
     *
     * @param m9o1o0
     */
    public void setM9o1o0(Boolean m9o1o0) {
        this.m9o1o0 = m9o1o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM9o2o0() {
        return m9o2o0;
    }

    /**
     *
     * @param m9o2o0
     */
    public void setM9o2o0(Boolean m9o2o0) {
        this.m9o2o0 = m9o2o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM10o0o0() {
        return m10o0o0;
    }

    /**
     *
     * @param m10o0o0
     */
    public void setM10o0o0(Boolean m10o0o0) {
        this.m10o0o0 = m10o0o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM10o1o0() {
        return m10o1o0;
    }

    /**
     *
     * @param m10o1o0
     */
    public void setM10o1o0(Boolean m10o1o0) {
        this.m10o1o0 = m10o1o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM10o2o0() {
        return m10o2o0;
    }

    /**
     *
     * @param m10o2o0
     */
    public void setM10o2o0(Boolean m10o2o0) {
        this.m10o2o0 = m10o2o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM10o3o0() {
        return m10o3o0;
    }

    /**
     *
     * @param m10o3o0
     */
    public void setM10o3o0(Boolean m10o3o0) {
        this.m10o3o0 = m10o3o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM10o4o0() {
        return m10o4o0;
    }

    /**
     *
     * @param m10o4o0
     */
    public void setM10o4o0(Boolean m10o4o0) {
        this.m10o4o0 = m10o4o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM10o5o0() {
        return m10o5o0;
    }

    /**
     *
     * @param m10o5o0
     */
    public void setM10o5o0(Boolean m10o5o0) {
        this.m10o5o0 = m10o5o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM10o6o0() {
        return m10o6o0;
    }

    /**
     *
     * @param m10o6o0
     */
    public void setM10o6o0(Boolean m10o6o0) {
        this.m10o6o0 = m10o6o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM10o7o0() {
        return m10o7o0;
    }

    /**
     *
     * @param m10o7o0
     */
    public void setM10o7o0(Boolean m10o7o0) {
        this.m10o7o0 = m10o7o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM10o8o0() {
        return m10o8o0;
    }

    /**
     *
     * @param m10o8o0
     */
    public void setM10o8o0(Boolean m10o8o0) {
        this.m10o8o0 = m10o8o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM10o9o0() {
        return m10o9o0;
    }

    /**
     *
     * @param m10o9o0
     */
    public void setM10o9o0(Boolean m10o9o0) {
        this.m10o9o0 = m10o9o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM10o10o0() {
        return m10o10o0;
    }

    /**
     *
     * @param m10o10o0
     */
    public void setM10o10o0(Boolean m10o10o0) {
        this.m10o10o0 = m10o10o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM10o11o0() {
        return m10o11o0;
    }

    /**
     *
     * @param m10o11o0
     */
    public void setM10o11o0(Boolean m10o11o0) {
        this.m10o11o0 = m10o11o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM10o12o0() {
        return m10o12o0;
    }

    /**
     *
     * @param m10o12o0
     */
    public void setM10o12o0(Boolean m10o12o0) {
        this.m10o12o0 = m10o12o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM10o13o0() {
        return m10o13o0;
    }

    /**
     *
     * @param m10o13o0
     */
    public void setM10o13o0(Boolean m10o13o0) {
        this.m10o13o0 = m10o13o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM10o14o0() {
        return m10o14o0;
    }

    /**
     *
     * @param m10o14o0
     */
    public void setM10o14o0(Boolean m10o14o0) {
        this.m10o14o0 = m10o14o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM10o15o0() {
        return m10o15o0;
    }

    /**
     *
     * @param m10o15o0
     */
    public void setM10o15o0(Boolean m10o15o0) {
        this.m10o15o0 = m10o15o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM10o16o0() {
        return m10o16o0;
    }

    /**
     *
     * @param m10o16o0
     */
    public void setM10o16o0(Boolean m10o16o0) {
        this.m10o16o0 = m10o16o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM10o17o0() {
        return m10o17o0;
    }

    /**
     *
     * @param m10o17o0
     */
    public void setM10o17o0(Boolean m10o17o0) {
        this.m10o17o0 = m10o17o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM10o18o0() {
        return m10o18o0;
    }

    /**
     *
     * @param m10o18o0
     */
    public void setM10o18o0(Boolean m10o18o0) {
        this.m10o18o0 = m10o18o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM10o19o0() {
        return m10o19o0;
    }

    /**
     *
     * @param m10o19o0
     */
    public void setM10o19o0(Boolean m10o19o0) {
        this.m10o19o0 = m10o19o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM11o0o0() {
        return m11o0o0;
    }

    /**
     *
     * @param m11o0o0
     */
    public void setM11o0o0(Boolean m11o0o0) {
        this.m11o0o0 = m11o0o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM11o1o0() {
        return m11o1o0;
    }

    /**
     *
     * @param m11o1o0
     */
    public void setM11o1o0(Boolean m11o1o0) {
        this.m11o1o0 = m11o1o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM11o2o0() {
        return m11o2o0;
    }

    /**
     *
     * @param m11o2o0
     */
    public void setM11o2o0(Boolean m11o2o0) {
        this.m11o2o0 = m11o2o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM11o3o0() {
        return m11o3o0;
    }

    /**
     *
     * @param m11o3o0
     */
    public void setM11o3o0(Boolean m11o3o0) {
        this.m11o3o0 = m11o3o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM11o4o0() {
        return m11o4o0;
    }

    /**
     *
     * @param m11o4o0
     */
    public void setM11o4o0(Boolean m11o4o0) {
        this.m11o4o0 = m11o4o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM11o5o0() {
        return m11o5o0;
    }

    /**
     *
     * @param m11o5o0
     */
    public void setM11o5o0(Boolean m11o5o0) {
        this.m11o5o0 = m11o5o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM11o6o0() {
        return m11o6o0;
    }

    /**
     *
     * @param m11o6o0
     */
    public void setM11o6o0(Boolean m11o6o0) {
        this.m11o6o0 = m11o6o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM12o0o0() {
        return m12o0o0;
    }

    /**
     *
     * @param m12o0o0
     */
    public void setM12o0o0(Boolean m12o0o0) {
        this.m12o0o0 = m12o0o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM12o1o0() {
        return m12o1o0;
    }

    /**
     *
     * @param m12o1o0
     */
    public void setM12o1o0(Boolean m12o1o0) {
        this.m12o1o0 = m12o1o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM12o2o0() {
        return m12o2o0;
    }

    /**
     *
     * @param m12o2o0
     */
    public void setM12o2o0(Boolean m12o2o0) {
        this.m12o2o0 = m12o2o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM12o3o0() {
        return m12o3o0;
    }

    /**
     *
     * @param m12o3o0
     */
    public void setM12o3o0(Boolean m12o3o0) {
        this.m12o3o0 = m12o3o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM12o4o0() {
        return m12o4o0;
    }

    /**
     *
     * @param m12o4o0
     */
    public void setM12o4o0(Boolean m12o4o0) {
        this.m12o4o0 = m12o4o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM13o0o0() {
        return m13o0o0;
    }

    /**
     *
     * @param m13o0o0
     */
    public void setM13o0o0(Boolean m13o0o0) {
        this.m13o0o0 = m13o0o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM14o0o0() {
        return m14o0o0;
    }

    /**
     *
     * @param m14o0o0
     */
    public void setM14o0o0(Boolean m14o0o0) {
        this.m14o0o0 = m14o0o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM5o9o0() {
        return m5o9o0;
    }

    /**
     *
     * @param m5o9o0
     */
    public void setM5o9o0(Boolean m5o9o0) {
        this.m5o9o0 = m5o9o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM5o10o0() {
        return m5o10o0;
    }

    /**
     *
     * @param m5o10o0
     */
    public void setM5o10o0(Boolean m5o10o0) {
        this.m5o10o0 = m5o10o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM6o9o0() {
        return m6o9o0;
    }

    /**
     *
     * @param m6o9o0
     */
    public void setM6o9o0(Boolean m6o9o0) {
        this.m6o9o0 = m6o9o0;
    }

    /**
     *
     * @return
     */
    public Boolean getM7o4o4() {
        return m7o4o4;
    }

    /**
     *
     * @param m7o4o4
     */
    public void setM7o4o4(Boolean m7o4o4) {
        this.m7o4o4 = m7o4o4;
    }

    /**
     *
     * @return
     */
    public Boolean getM7o4o5() {
        return m7o4o5;
    }

    /**
     *
     * @param m7o4o5
     */
    public void setM7o4o5(Boolean m7o4o5) {
        this.m7o4o5 = m7o4o5;
    }

    /**
     *
     * @return
     */
    public Boolean getM7o4o6() {
        return m7o4o6;
    }

    /**
     *
     * @param m7o4o6
     */
    public void setM7o4o6(Boolean m7o4o6) {
        this.m7o4o6 = m7o4o6;
    }

    /**
     *
     * @return
     */
    public Boolean getM7o4o7() {
        return m7o4o7;
    }

    /**
     *
     * @param m7o4o7
     */
    public void setM7o4o7(Boolean m7o4o7) {
        this.m7o4o7 = m7o4o7;
    }

    /**
     *
     * @return
     */
    public Boolean getM7o5o1() {
        return m7o5o1;
    }

    /**
     *
     * @param m7o5o1
     */
    public void setM7o5o1(Boolean m7o5o1) {
        this.m7o5o1 = m7o5o1;
    }

    /**
     *
     * @return
     */
    public Boolean getM7o5o2() {
        return m7o5o2;
    }

    /**
     *
     * @param m7o5o2
     */
    public void setM7o5o2(Boolean m7o5o2) {
        this.m7o5o2 = m7o5o2;
    }

    /**
     *
     * @return
     */
    public Boolean getM7o5o3() {
        return m7o5o3;
    }

    /**
     *
     * @param m7o5o3
     */
    public void setM7o5o3(Boolean m7o5o3) {
        this.m7o5o3 = m7o5o3;
    }

    /**
     *
     * @return
     */
    public Boolean getM7o6o0() {
        return m7o6o0;
    }

    /**
     *
     * @param m7o6o0
     */
    public void setM7o6o0(Boolean m7o6o0) {
        this.m7o6o0 = m7o6o0;
    }

    /**
     *
     * @return
     */
    public String getSq_oetra_a() {
        return sq_oetra_a;
    }

    /**
     *
     * @param sq_oetra_a
     */
    public void setSq_oetra_a(String sq_oetra_a) {
        this.sq_oetra_a = sq_oetra_a;
    }

    /**
     *
     * @return
     */
    public String getSq_odtra_a() {
        return sq_odtra_a;
    }

    /**
     *
     * @param sq_odtra_a
     */
    public void setSq_odtra_a(String sq_odtra_a) {
        this.sq_odtra_a = sq_odtra_a;
    }

    /**
     *
     * @return
     */
    public String getSq_octra_a() {
        return sq_octra_a;
    }

    /**
     *
     * @param sq_octra_a
     */
    public void setSq_octra_a(String sq_octra_a) {
        this.sq_octra_a = sq_octra_a;
    }

}
