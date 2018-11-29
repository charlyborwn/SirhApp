package com.hrm.sirhapp.backing;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.hrm.sirhapp.MediaBean;
import com.hrm.sirhapp.UserSessionBean;
import com.hrm.sirhapp.model.Traba36a;
import com.hrm.sirhapp.service.Empre04ManagerLocal;
import com.hrm.sirhapp.service.Traba36aManagerLocal;
import com.hrm.sirhapp.service.exception.Empre04NotFound;
import com.hrm.sirhapp.service.exception.Traba36aNotFound;
import com.hrm.sirhapp.util.FacesUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.ValueHolder;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.DefaultStreamedContent;
import com.hrm.sirhapp.service.Seqen55ManagerLocal;
import javax.faces.context.FacesContext;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Named
@SessionScoped
public class Traba36aGeneradorOficiosBacking extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Traba36aManagerLocal traba36aManager;

    @EJB
    private Empre04ManagerLocal empre04Manager;

    @EJB
    private Seqen55ManagerLocal sequen55Manager;

    private List<Traba36a> traba36aList;
    private List<Traba36a> traba36aListGen;
    private List<Traba36a> selectedTraba36aList;

    private DefaultStreamedContent output;
    private String textoHtml;
    private String folios;
    private String ap;
    private String am;
    private String n;
    private String rfc;
    private String period;
    private String reporte;
    private Boolean imprimir;

    private String oetraA;
    private String ubicacion;
    private Date entraA;

    private Integer folio = null;
    private Date startDate;
    private Date endDate;
    private Integer start;
    private Integer end;
    private String infoMessageModule;
    private String infoMessage;

    private String enterprise;
    private String enterpriseName;

    @PostConstruct
    private void init() {
        imprimir = true;
        enterprise = "";
        period = "year";
        infoMessageModule = "Generador de oficios";
        reporte = "0";

        UserSessionBean userSessionBean = FacesUtil.getManagedBean("userSessionBean", UserSessionBean.class);
        userSessionBean.setSq_oetra_a("");
        userSessionBean.setSq_odtra_a("");
        userSessionBean.setSq_octra_a("");
    }

    /**
     *
     */
    public void clearState() {
        enterprise = "";
        enterpriseName = "";
        period = "year";

        traba36aList = null;
        traba36aListGen = null;
        selectedTraba36aList = null;
        output = null;
        textoHtml = null;
        folios = null;
        ap = null;
        am = null;
        n = null;
        rfc = null;
        startDate = null;
        endDate = null;
        folio = null;
        start = null;
        end = null;
        infoMessage = null;
        imprimir = true;
        ubicacion = null;
        entraA = null;
        oetraA = null;

        reporte = "0";

        UserSessionBean userSessionBean = FacesUtil.getManagedBean("userSessionBean", UserSessionBean.class);
        userSessionBean.setSq_oetra_a("");
        userSessionBean.setSq_odtra_a("");
        userSessionBean.setSq_octra_a("");
    }

    /**
     *
     * @param vce
     */
    public void handleChange(AjaxBehaviorEvent vce) {

        String id = ((UIComponent) vce.getSource()).getId();
        String value = "";
        imprimir = true;
        switch (id) {
            case "ent_go":
                value = (String) ((ValueHolder) vce.getSource()).getValue();
                System.out.println("id:" + id + " ent_go:" + value);
                enterprise = value;
                retrieveTraba36aListPeriod();
                break;
            case "per_go":
                value = (String) ((ValueHolder) vce.getSource()).getValue();
                System.out.println("id:" + id + " per_go:" + value);
                period = value;
                retrieveTraba36aListPeriod();
                break;
            case "rep_go":
                if (reporte.equals("0") && (!selectedTraba36aList.isEmpty() && oetraA != null && oetraA.length() > 0 && ubicacion != null && ubicacion.length() > 0 && entraA != null)) {
                    imprimir = false;
                } else if (reporte.equals("1") && (!selectedTraba36aList.isEmpty() && oetraA != null && oetraA.length() > 0 && ubicacion != null && ubicacion.length() > 0)) {
                    imprimir = false;
                } else if (reporte.equals("2") && (!selectedTraba36aList.isEmpty() && oetraA != null && oetraA.length() > 0 && ubicacion != null && ubicacion.length() > 0)) {
                    imprimir = false;
                } else if (reporte.equals("3")) {
                    imprimir = false;
                } else if (reporte.equals("4")) {
                    imprimir = false;
                }
                if (selectedTraba36aList != null) {
                    System.out.println(imprimir);

                    System.out.println("uno" + (reporte == "0" && !selectedTraba36aList.isEmpty() && oetraA != null && ubicacion != null && entraA != null));
                    System.out.println("dos" + (reporte == "1" || reporte == "2" && (!selectedTraba36aList.isEmpty() && oetraA != null && ubicacion != null)));
                    System.out.println("reporte1: " + reporte + " case " + (reporte.equals("0")));
                    System.out.println("reporte2: " + reporte + " case " + (reporte.equals("1")));
                    System.out.println("reporte3: " + reporte + " case " + (reporte.equals("2")));
                    System.out.println("selectedTraba36aList: " + selectedTraba36aList.size() + "case " + !selectedTraba36aList.isEmpty());
                    System.out.println("oetraA: " + oetraA + "case " + (oetraA != null));
                    System.out.println("ubicacion: " + ubicacion + "case " + (ubicacion != null));
                    System.out.println("entraA: " + entraA + "case " + (entraA != null));
                }
                retrieveTraba36aListPeriod();
                break;
            case "processCommandButton":
            case "dataTableResults":
            case "ind_go":
            case "ndo_go":
            case "loc_go":
                if (reporte.equals("0") && (!selectedTraba36aList.isEmpty() && oetraA != null && oetraA.length() > 0 && ubicacion != null && ubicacion.length() > 0 && entraA != null)) {
                    imprimir = false;
                } else if (reporte.equals("1") && (!selectedTraba36aList.isEmpty() && oetraA != null && oetraA.length() > 0 && ubicacion != null && ubicacion.length() > 0)) {
                    imprimir = false;
                } else if (reporte.equals("2") && (!selectedTraba36aList.isEmpty() && oetraA != null && oetraA.length() > 0 && ubicacion != null && ubicacion.length() > 0)) {
                    imprimir = false;
                } else if (reporte.equals("3")) {
                    imprimir = false;
                } else if (reporte.equals("4")) {
                    imprimir = false;
                }
                if (selectedTraba36aList != null) {
                    System.out.println(imprimir);

                    System.out.println("uno" + (reporte == "0" && !selectedTraba36aList.isEmpty() && oetraA != null && ubicacion != null && entraA != null));
                    System.out.println("dos" + (reporte == "1" || reporte == "2" && (!selectedTraba36aList.isEmpty() && oetraA != null && ubicacion != null)));
                    System.out.println("reporte1: " + reporte + " case " + (reporte.equals("0")));
                    System.out.println("reporte2: " + reporte + " case " + (reporte.equals("1")));
                    System.out.println("reporte3: " + reporte + " case " + (reporte.equals("2")));
                    System.out.println("selectedTraba36aList: " + selectedTraba36aList.size() + "case " + !selectedTraba36aList.isEmpty());
                    System.out.println("oetraA: " + oetraA + "case " + (oetraA != null));
                    System.out.println("ubicacion: " + ubicacion + "case " + (ubicacion != null));
                    System.out.println("entraA: " + entraA + "case " + (entraA != null));
                }
                break;
            default:
                break;
        }

    }

    /**
     *
     */
    public void processPdf() {
        output = null;
        getEnterpriseName();
        switch (reporte) {
            case "0":
                afiliacionPdf();
                updateOetraA();
                System.out.println("oficios entrevista" + reporte);
                break;
            case "1":
                documentacionPdf();
                updateOetraA();
                System.out.println("oficios documentacion" + reporte);
                break;
            case "2":
                contratacionPdf();
                updateOetraA();
                System.out.println("oficios Contratos" + reporte);
                break;
            case "3":
                requisitosPdf();
                break;
            case "4":
                adicionalesPdf();
                break;
            default:
                break;
        }

        if (output == null) {
            blancoPdf();
        }
        imprimir = true;
        retrieveTraba36aListPeriod();
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("panelForm:generadorOficiosForm");
    }

    /**
     *
     */
    public void afiliacionPdf() {

        Date newDate = new Date();

        if (entraA == null) {
            this.entraA = newDate;
        }

        List<InputStream> pdfs = new ArrayList<InputStream>();

        InputStream pdf1;
        InputStream pdf2;

        if (!selectedTraba36aList.isEmpty()) {

            pdf1 = FacesUtil.runManagedBeanToPdf("mediaBean", "getPdf", getReporte2(String.valueOf(selectedTraba36aList.size())), "OFICIO PERSONAL ASPIRANTE A ENTREVISTA", true);
            pdfs.add(pdf1);

            pdf2 = FacesUtil.runManagedBeanToPdf("mediaBean", "getPdf", getReporte3(selectedTraba36aList, String.valueOf(selectedTraba36aList.size())), "OFICIO PERSONAL ASPIRANTE A ENTREVISTA", true);
            pdfs.add(pdf2);

        }

        try {
            mergePdf(pdfs);
        } catch (IOException | DocumentException ex) {
            Logger.getLogger(Traba36aGeneradorOficiosBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     */
    public void documentacionPdf() {

        Date newDate = new Date();

        if (entraA == null) {
            this.entraA = newDate;
        }

        List<InputStream> pdfs = new ArrayList<InputStream>();

        InputStream pdf1;
        InputStream pdf2;

        if (!selectedTraba36aList.isEmpty()) {

            pdf1 = FacesUtil.runManagedBeanToPdf("mediaBean", "getPdf", getReporte5(String.valueOf(selectedTraba36aList.size())), "OFICIO ENVIO DOCUMENTOS TRAMITE CONTRATACION", true);
            pdfs.add(pdf1);

            pdf2 = FacesUtil.runManagedBeanToPdf("mediaBean", "getPdf", getReporte6(selectedTraba36aList, String.valueOf(selectedTraba36aList.size())), "OFICIO ENVIO DOCUMENTOS TRAMITE CONTRATACION", true);
            pdfs.add(pdf2);

        }

        try {
            mergePdf(pdfs);
        } catch (IOException | DocumentException ex) {
            Logger.getLogger(Traba36aGeneradorOficiosBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     */
    public void contratacionPdf() {

        if (entraA == null) {
            this.entraA = new Date();
        }

        List<InputStream> pdfs = new ArrayList<InputStream>();

        InputStream pdf1;
        InputStream pdf2;

        if (!selectedTraba36aList.isEmpty()) {

            pdf1 = FacesUtil.runManagedBeanToPdf("mediaBean", "getPdf", getReporte32(String.valueOf(selectedTraba36aList.size())), "OFICIO DE DEVOLUCION DE CONTRATOS FIRMADOS PERSONAL DE NUEVO INGRESO", true);
            pdfs.add(pdf1);

            pdf2 = FacesUtil.runManagedBeanToPdf("mediaBean", "getPdf", getReporte33(selectedTraba36aList, String.valueOf(selectedTraba36aList.size())), "OFICIO DE DEVOLUCION DE CONTRATOS FIRMADOS PERSONAL DE NUEVO INGRESO", true);
            pdfs.add(pdf2);

        }

        try {
            mergePdf(pdfs);
        } catch (IOException | DocumentException ex) {
            Logger.getLogger(Traba36aGeneradorOficiosBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     */
    public void requisitosPdf() {

        List<InputStream> pdfs = new ArrayList<InputStream>();

        InputStream pdf1;

        pdf1 = FacesUtil.runManagedBeanToPdf("mediaBean", "getPdf", getReporte4(), "REQUISITOS INDISPENSABLES PARA CONTRATACION", true);
        pdfs.add(pdf1);

        try {
            mergePdf(pdfs);
        } catch (IOException | DocumentException ex) {
            Logger.getLogger(Traba36aGeneradorOficiosBacking.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     */
    public void adicionalesPdf() {

        try {

            URL url = this.getClass().getProtectionDomain().getCodeSource().getLocation();
            //System.out.println(url);

            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("reports/Formatos.pdf").getFile());

            byte[] data;

            data = FileUtils.readFileToByteArray(file);

            try (InputStream stream = new ByteArrayInputStream(data)) {
                output = new DefaultStreamedContent(stream, "application/pdf");
                stream.close();
            }

        } catch (IOException ex) {
            Logger.getLogger(Traba36aGeneradorOficiosBacking.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     */
    public void blancoPdf() {
        List<InputStream> pdfs = new ArrayList<InputStream>();

        InputStream pdf1;

        pdf1 = FacesUtil.runManagedBeanToPdf("mediaBean", "getPdf", "", "", false);
        pdfs.add(pdf1);

        try {
            mergePdf(pdfs);
        } catch (IOException | DocumentException ex) {
            Logger.getLogger(Traba36aGeneradorOficiosBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param pdfs
     * @throws IOException
     * @throws DocumentException
     */
    public void mergePdf(List<InputStream> pdfs) throws IOException, DocumentException {

        byte[] bytes;
        //Unir todos los pdf's en un solo archivo
        try (OutputStream out = new ByteArrayOutputStream()) {
            Document document = new Document();

            List<PdfReader> readers = new ArrayList<PdfReader>();

            Iterator<InputStream> iteratorPDFs = pdfs.iterator();

            while (iteratorPDFs.hasNext()) {
                InputStream pdf = iteratorPDFs.next();
                PdfReader pdfReader = new PdfReader(pdf);
                readers.add(pdfReader);
            }

            PdfWriter writer = PdfWriter.getInstance(document, out);

            document.open();

            PdfContentByte cb = writer.getDirectContent();

            PdfImportedPage page;
            int pageOfCurrentReaderPDF = 0;
            Iterator<PdfReader> iteratorPDFReader = readers.iterator();

            while (iteratorPDFReader.hasNext()) {
                PdfReader pdfReader = iteratorPDFReader.next();
                while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {
                    Rectangle rectangle = pdfReader.getPageSizeWithRotation(1);
                    document.setPageSize(rectangle);
                    document.newPage();

                    pageOfCurrentReaderPDF++;

                    page = writer.getImportedPage(pdfReader,
                            pageOfCurrentReaderPDF);
                    switch (rectangle.getRotation()) {
                        case 0:
                            cb.addTemplate(page, 1f, 0, 0, 1f, 0, 0);
                            break;
                        case 90:
                            cb.addTemplate(page, 0, -1f, 1f, 0, 0, pdfReader
                                    .getPageSizeWithRotation(1).getHeight());
                            break;
                        case 180:
                            cb.addTemplate(page, -1f, 0, 0, -1f, 0, 0);
                            break;
                        case 270:
                            cb.addTemplate(page, 0, 1.0F, -1.0F, 0, pdfReader
                                    .getPageSizeWithRotation(1).getWidth(), 0);
                            break;
                        default:
                            break;
                    }
                }
                pageOfCurrentReaderPDF = 0;
            }

            document.close();

            writer.flush();
            writer.close();

            bytes = IOUtils.toByteArray(new ByteArrayInputStream(((ByteArrayOutputStream) out).toByteArray()));
            out.flush();
            out.close();
        }

        //Usa la siguiente sentencia para mostrar el pdf sin proteger
        //output = new DefaultStreamedContent(new ByteArrayInputStream(bytes), "application/pdf");
        //Proteger archivo para que no sea editable
        InputStream in = new ByteArrayInputStream(bytes);
        in = new ByteArrayInputStream(new MediaBean().preventCopyingPdf(in, null, null));
        output = new DefaultStreamedContent(in, "application/pdf");
    }

    /**
     *
     */
    public void updateOetraA() {
        Date newDate = new Date();
        String userName = FacesUtil.getUserName();

        if (!selectedTraba36aList.isEmpty()) {
            for (Traba36a t : selectedTraba36aList) {

                switch (reporte) {
                    case "0":
                        t.setOetraA(oetraA);
                        t.setEntraA(entraA);
                        break;
                    case "1":
                        t.setOdtraA(oetraA);
                        break;
                    case "2":
                        t.setOctraA(oetraA);
                        break;
                }

                t.setFetraA(newDate);
                t.setUstraA(userName);
                try {
                    traba36aManager.updateTraba36a(t);
                } catch (Traba36aNotFound ex) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     *
     * @param folio
     */
    public void retrieveTraba36aListFolio(Integer folio) {
        try {

            this.folio = folio;

            HashMap<String, Traba36a> map = new HashMap<String, Traba36a>();
            HashMap<String, Traba36a> map2 = new HashMap<String, Traba36a>();

            selectedTraba36aList = null;

            traba36aList = traba36aManager.getListTraba36aGen(folio, reporte, true);
            traba36aListGen = traba36aManager.getListTraba36aGen(folio, reporte, false);

            if (!traba36aList.isEmpty()) {
                for (Traba36a e : traba36aList) {
                    map.put(e.getRftraA(), e);
                }
            }

            if (!traba36aListGen.isEmpty()) {
                for (Traba36a gen : traba36aListGen) {
                    map2.put(gen.getRftraA(), gen);
                }
            }

            traba36aList = new ArrayList(map.values());
            traba36aListGen = new ArrayList(map2.values());

            if (!traba36aList.isEmpty()) {
                infoMessage = +traba36aList.size() + " Aspirante";
            } else {
                infoMessage = "";
            }

        } catch (SecurityException | IllegalArgumentException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     */
    public void retrieveTraba36aListNombre() {
        try {
            Traba36a traba36a = new Traba36a();

            traba36a.setAptraA(ap);
            traba36a.setAmtraA(am);
            traba36a.setNotraA(n);

            HashMap<String, Traba36a> map = new HashMap<String, Traba36a>();
            HashMap<String, Traba36a> map2 = new HashMap<String, Traba36a>();

            selectedTraba36aList = null;

            traba36aList = traba36aManager.getListTraba36aGen(traba36a, reporte, true);
            traba36aListGen = traba36aManager.getListTraba36aGen(traba36a, reporte, false);

            if (!traba36aList.isEmpty()) {
                for (Traba36a e : traba36aList) {
                    map.put(e.getRftraA(), e);
                }
            }

            if (!traba36aListGen.isEmpty()) {
                for (Traba36a gen : traba36aListGen) {
                    map2.put(gen.getRftraA(), gen);
                }
            }

            traba36aList = new ArrayList(map.values());
            traba36aListGen = new ArrayList(map2.values());

            if (!traba36aList.isEmpty()) {
                infoMessage = +traba36aList.size() + " Aspirantes";
            } else {
                infoMessage = "";
            }

        } catch (SecurityException | IllegalArgumentException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     */
    public void retrieveTraba36aListRfc() {
        try {
            Traba36a traba36a = new Traba36a();

            traba36a.setRftraA(rfc);

            HashMap<String, Traba36a> map = new HashMap<String, Traba36a>();
            HashMap<String, Traba36a> map2 = new HashMap<String, Traba36a>();

            selectedTraba36aList = null;

            traba36aList = traba36aManager.getListTraba36aGen(rfc, reporte, true);
            traba36aListGen = traba36aManager.getListTraba36aGen(rfc, reporte, false);

            if (!traba36aList.isEmpty()) {
                for (Traba36a e : traba36aList) {
                    map.put(e.getRftraA(), e);
                }
            }

            if (!traba36aListGen.isEmpty()) {
                for (Traba36a gen : traba36aListGen) {
                    map2.put(gen.getRftraA(), gen);
                }
            }

            traba36aList = new ArrayList(map.values());
            traba36aListGen = new ArrayList(map2.values());

            if (!traba36aList.isEmpty()) {
                infoMessage = +traba36aList.size() + " Aspirantes";
            } else {
                infoMessage = "";
            }

        } catch (SecurityException | IllegalArgumentException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     */
    public void retrieveTraba36aStringFolios() {
        try {

            int[] numbers = Arrays.asList(folios.split(","))
                    .stream()
                    .map(String::trim)
                    .mapToInt(Integer::parseInt).toArray();

            HashMap<String, Traba36a> map = new HashMap<String, Traba36a>();
            HashMap<String, Traba36a> map2 = new HashMap<String, Traba36a>();

            selectedTraba36aList = null;

            traba36aList = traba36aManager.getListTraba36aGen(numbers, reporte, true);
            traba36aListGen = traba36aManager.getListTraba36aGen(numbers, reporte, false);

            if (!traba36aList.isEmpty()) {
                for (Traba36a traba36a : traba36aList) {
                    map.put(traba36a.getRftraA(), traba36a);
                }
            }

            if (!traba36aListGen.isEmpty()) {
                for (Traba36a gen : traba36aListGen) {
                    map2.put(gen.getRftraA(), gen);
                }
            }

            traba36aList = new ArrayList(map.values());
            traba36aListGen = new ArrayList(map2.values());

            if (!traba36aList.isEmpty()) {
                infoMessage = +traba36aList.size() + " Aspirantes";
            } else {
                infoMessage = "";
            }

        } catch (SecurityException | IllegalArgumentException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     */
    public void retrieveTraba36aListFolios() {
        try {
            HashMap<String, Traba36a> map = new HashMap<String, Traba36a>();
            HashMap<String, Traba36a> map2 = new HashMap<String, Traba36a>();

            selectedTraba36aList = null;

            traba36aList = traba36aManager.getListTraba36aGen(enterprise, start, end, reporte, true);
            traba36aListGen = traba36aManager.getListTraba36aGen(enterprise, start, end, reporte, false);

            if (!traba36aList.isEmpty()) {
                for (Traba36a traba36a : traba36aList) {
                    map.put(traba36a.getRftraA(), traba36a);
                }
            }

            if (!traba36aListGen.isEmpty()) {
                for (Traba36a gen : traba36aListGen) {
                    map2.put(gen.getRftraA(), gen);
                }
            }

            traba36aList = new ArrayList(map.values());
            traba36aListGen = new ArrayList(map2.values());

            if (!traba36aList.isEmpty()) {
                infoMessage = +traba36aList.size() + " Aspirantes";
            } else {
                infoMessage = "";
            }

        } catch (SecurityException | IllegalArgumentException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     */
    public void retrieveTraba36aListPeriod() {

        try {

            HashMap<String, Traba36a> map = new HashMap<String, Traba36a>();
            HashMap<String, Traba36a> map2 = new HashMap<String, Traba36a>();

            selectedTraba36aList = null;

            traba36aList = traba36aManager.getListTraba36aGen(enterprise, period, reporte, true);
            traba36aListGen = traba36aManager.getListTraba36aGen(enterprise, period, reporte, false);

            if (!traba36aList.isEmpty()) {
                for (Traba36a traba36a : traba36aList) {
                    map.put(traba36a.getRftraA(), traba36a);
                }
            }

            if (!traba36aListGen.isEmpty()) {
                for (Traba36a gen : traba36aListGen) {
                    map2.put(gen.getRftraA(), gen);
                }
            }

            traba36aList = new ArrayList(map.values());
            traba36aListGen = new ArrayList(map2.values());

            if (!traba36aList.isEmpty()) {
                infoMessage = +traba36aList.size() + " Aspirantes";
            } else {
                infoMessage = "";
            }

        } catch (SecurityException | IllegalArgumentException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     */
    public void retrieveTraba36aListFechas() {

        try {

            HashMap<String, Traba36a> map = new HashMap<String, Traba36a>();
            HashMap<String, Traba36a> map2 = new HashMap<String, Traba36a>();

            selectedTraba36aList = null;

            traba36aList = traba36aManager.getListTraba36aGen(enterprise, startDate, endDate, reporte, true);
            traba36aListGen = traba36aManager.getListTraba36aGen(enterprise, startDate, endDate, reporte, false);

            if (!traba36aList.isEmpty()) {
                for (Traba36a traba36a : traba36aList) {
                    map.put(traba36a.getRftraA(), traba36a);
                }
            }

            if (!traba36aListGen.isEmpty()) {
                for (Traba36a gen : traba36aListGen) {
                    map2.put(gen.getRftraA(), gen);
                }
            }

            traba36aList = new ArrayList(map.values());
            traba36aListGen = new ArrayList(map2.values());

            if (!traba36aList.isEmpty()) {
                infoMessage = +traba36aList.size() + " Aspirantes";
            } else {
                infoMessage = "";
            }

        } catch (SecurityException | IllegalArgumentException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @return
     */
    public String getReporte() {
        return reporte;
    }

    /**
     *
     * @param reporte
     */
    public void setReporte(String reporte) {
        this.reporte = reporte;
    }

    /**
     *
     * @return
     */
    public Boolean getImprimir() {
        return imprimir;
    }

    /**
     *
     * @param imprimir
     */
    public void setImprimir(Boolean imprimir) {
        this.imprimir = imprimir;
    }

    /**
     *
     * @return
     */
    public String getOetraA() {
        UserSessionBean userSessionBean = FacesUtil.getManagedBean("userSessionBean", UserSessionBean.class);
        String sn = "";
        String folio = "";
        switch (reporte) {
            case "0":
                sn = "sq_oetra_a";
                if (userSessionBean.getSq_oetra_a().length() == 0) {
                    folio = sequen55Manager.getSeqen55(sn).toString();
                    userSessionBean.setSq_oetra_a(folio);
                } else {
                    folio = userSessionBean.getSq_oetra_a();
                }
                break;
            case "1":
                sn = "sq_odtra_a";
                if (userSessionBean.getSq_odtra_a().length() == 0) {
                    folio = sequen55Manager.getSeqen55(sn).toString();
                    userSessionBean.setSq_odtra_a(folio);
                } else {
                    folio = userSessionBean.getSq_odtra_a();
                }
                break;
            case "2":
                sn = "sq_octra_a";
                if (userSessionBean.getSq_octra_a().length() == 0) {
                    folio = sequen55Manager.getSeqen55(sn).toString();
                    userSessionBean.setSq_octra_a(folio);
                } else {
                    folio = userSessionBean.getSq_octra_a();
                }
                break;
            default:
                break;
        }
        if (sn != "") {
            this.oetraA = folio;
        }
        return oetraA;
    }

    /**
     *
     * @param oetraA
     */
    public void setOetraA(String oetraA) {
        this.oetraA = oetraA;
    }

    /**
     *
     * @return
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     *
     * @param ubicacion
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     *
     * @return
     */
    public Date getEntraA() {
        if (entraA == null) {
            this.entraA = new Date();
        }
        return entraA;
    }

    /**
     *
     * @param entraA
     */
    public void setEntraA(Date entraA) {
        this.entraA = entraA;
    }

    /**
     *
     * @return
     */
    public Traba36aManagerLocal getTraba36aManager() {
        return traba36aManager;
    }

    /**
     *
     * @param traba36aManager
     */
    public void setTraba36aManager(Traba36aManagerLocal traba36aManager) {
        this.traba36aManager = traba36aManager;
    }

    /**
     *
     * @return
     */
    public List<Traba36a> getTraba36aList() {
        if (traba36aList != null) {
            traba36aList.sort(Comparator.comparing(Traba36a::getFxtraA));
        }
        return traba36aList;
    }

    /**
     *
     * @param traba36aList
     */
    public void setTraba36aList(List<Traba36a> traba36aList) {
        this.traba36aList = traba36aList;
    }

    /**
     *
     * @return
     */
    public List<Traba36a> getTraba36aListGen() {
        if (traba36aListGen != null) {
            traba36aListGen.sort(Comparator.comparing(Traba36a::getFxtraA));
        }
        return traba36aListGen;
    }

    /**
     *
     * @param traba36aListGen
     */
    public void setTraba36aListGen(List<Traba36a> traba36aListGen) {
        this.traba36aListGen = traba36aListGen;
    }

    /**
     *
     * @return
     */
    public List<Traba36a> getSelectedTraba36aList() {
        return selectedTraba36aList;
    }

    /**
     *
     * @param selectedTraba36aList
     */
    public void setSelectedTraba36aList(List<Traba36a> selectedTraba36aList) {
        this.selectedTraba36aList = selectedTraba36aList;
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
    public String getPeriod() {
        return period;
    }

    /**
     *
     * @param period
     */
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     *
     * @return
     */
    public String getEnterprise() {
        try {
            this.enterpriseName = empre04Manager.getEmpre04(enterprise).getNoemp();
        } catch (Empre04NotFound ex) {
            Logger.getLogger(Traba36aGeneradorOficiosBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
        return enterprise;
    }

    /**
     *
     * @param enterprise
     */
    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }

    /**
     *
     * @return
     */
    public Integer getStart() {
        return start;
    }

    /**
     *
     * @param start
     */
    public void setStart(Integer start) {
        this.start = start;
    }

    /**
     *
     * @return
     */
    public Integer getEnd() {
        return end;
    }

    /**
     *
     * @param end
     */
    public void setEnd(Integer end) {
        this.end = end;
    }

    /**
     *
     * @return
     */
    public String getAp() {
        return ap;
    }

    /**
     *
     * @param ap
     */
    public void setAp(String ap) {
        this.ap = ap;
    }

    /**
     *
     * @return
     */
    public String getAm() {
        return am;
    }

    /**
     *
     * @param am
     */
    public void setAm(String am) {
        this.am = am;
    }

    /**
     *
     * @return
     */
    public String getN() {
        return n;
    }

    /**
     *
     * @param n
     */
    public void setN(String n) {
        this.n = n;
    }

    /**
     *
     * @return
     */
    public String getFolios() {
        return folios;
    }

    /**
     *
     * @param folios
     */
    public void setFolios(String folios) {
        this.folios = folios;
    }

    /**
     *
     * @return
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     *
     * @param startDate
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     *
     * @return
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     *
     * @param endDate
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     *
     * @return
     */
    public String getRfc() {
        return rfc;
    }

    /**
     *
     * @param rfc
     */
    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    /**
     *
     * @return
     */
    public Integer getFolio() {
        return folio;
    }

    /**
     *
     * @param folio
     */
    public void setFolio(Integer folio) {
        this.folio = folio;
    }

    /**
     *
     * @return
     */
    public String getTextoHtml() {
        textoHtml = "<html><head></head><body><label>Vacio</label></body></html>";
        return textoHtml;
    }

    /**
     *
     * @param textoHtml
     */
    public void setTextoHtml(String textoHtml) {
        this.textoHtml = textoHtml;
    }

    /**
     *
     * @return
     */
    public DefaultStreamedContent getOutput() {
        return output;
    }

    /**
     *
     * @param output
     */
    public void setOutput(DefaultStreamedContent output) {
        this.output = output;
    }

    /**
     *
     * @return
     */
    public String getEnterpriseName() {
        try {
            this.enterpriseName = empre04Manager.getEmpre04(enterprise).getNoemp();
        } catch (Empre04NotFound ex) {
            Logger.getLogger(Traba36aGeneradorOficiosBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
        return enterpriseName;
    }

    /**
     *
     * @param enterpriseName
     */
    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    /**
     *
     * @param candidatos
     * @return
     */
    public String getReporte2(String candidatos) {

        String str;

        str = "<html>"
                + "<head>"
                + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />"
                + "<title>Entrevista</title>"
                + "</head>"
                + "<body>"
                + "<table style=\" width:100%\">"
                + "  <tr>"
                + "    <td  style=\" width:30%\">"
                + "    <table  style=\" width:100%\">"
                + "        <tr>"
                + "          <td style=\"text-align: center;\"><div>COMITE EJECUTIVO</div></td>"
                + "        </tr>"
                + "        <tr>"
                + "          <td style=\"text-align: center;\"><span style=\"font-size: x-small;\">HUGO GUILLERMO DIAZ COVARRUBIAS</span>"
                + "            <div><span style=\"font-size: x-small;\">Secretario General</span></div></td>"
                + "        </tr>"
                + "        <tr>"
                + "          <td style=\"text-align: center;\"><span style=\"font-size: x-small;\">GUILLERMO DIAZ CASTA&Ntilde;EDA</span>"
                + "            <div><span style=\"font-size: x-small;\">Secretario del Interior</span></div></td>"
                + "        </tr>"
                + "        <tr>"
                + "          <td style=\"text-align: center;\"><span style=\"font-size: x-small;\">CARLOS ALBERTO VAZQUEZ RODRIGUEZ</span>"
                + "            <div><span style=\"font-size: x-small;\">Secretario del Exterior</span></div></td>"
                + "        </tr>"
                + "        <tr>"
                + "          <td style=\"text-align: center;\"><span style=\"font-size: x-small;\">ALBERTO RABAGO CAMACHO</span>"
                + "            <div><span style=\"font-size: x-small;\">Secretario Tesorero</span></div></td>"
                + "        </tr>"
                + "        <tr>"
                + "          <td style=\"text-align: center;\"><span style=\"font-size: x-small;\">DANTE ENRIQUE INTRIAGO SIERRA</span>"
                + "            <div><span style=\"font-size: x-small;\">Secretario de Actas</span></div></td>"
                + "        </tr>"
                + "      </table>"
                + "    </td>"
                + "    <td  style=\" width:70%\">"
                + "    <table style=\" width:100%\">"
                + "        <tr>"
                + "          <td style=\"text-align: right;\">" + ubicacion.toUpperCase() + "., a " + FacesUtil.getDatewFormat() + "</td>"
                + "        </tr>"
                + "        <tr>"
                + "          <td style=\"text-align: right;\">HDC/" + oetraA + "</td>"
                + "        </tr>"
                + "        <tr>"
                + "          <td>LIC. FRANCISCO JAVIER ARAGON NAVARRO"
                + "            <div>Director de Recursos Humanos</div>"
                + "            <div><span style=\"\">y Relaciones Laborales</span></div>"
                + "            <div><span style=\"\">FCA México S.A. de C.V.</span></div>"
                + "            <div><span style=\"\">Presente</span></div></td>"
                + "        </tr>"
                + "        <tr><td style=\"text-align: right;\"><div><span style= \"\">Asunto: Envío de Personal a Entrevista</span></div></td></tr>"
                + "        <tr>"
                + "          <td style=\"width: 100%; text-align: left;\"><div>&nbsp;</div>"
                + "            <div>&nbsp;</div>"
                + "            <div><span style= \"\">Por medio de la presente le informamos a usted, que se le estan enviando un grupo de candidato(s) para su respectiva entrevista, como a continuación se detalla:</span></div>"
                + "            <div>&nbsp;</div>"
                + "            <div><span style= \"\">Planta: " + enterpriseName + "</span></div>"
                + "            <div><span style= \"\">Fecha: " + FacesUtil.getDate(entraA) + "</span></div>"
                + "            <div><span style= \"\">Candidatos: " + candidatos + "</span></div>"
                + "            <div><span style= \"\">Nota: Se anexa relación</span></div>"
                + "            <p>&nbsp;</p></td>"
                + "        </tr>"
                + "        <tr>"
                + "          <td >Agradeciendo la atención que se sirva brindar a la presente, quedo de usted.</td>"
                + "        </tr>"
                + "        <tr>"
                + "          <td ><p>&nbsp;</p>"
                + "            <div><span style=\"\">A T E N T A M E N T E</span></div>"
                + "            <div><span style=\"\">POR UN MEXICO MEJOR</span></div>"
                + "            <div><span style=\"\">P/EL COMITE EJECUTIVO</span></div></td>"
                + "        </tr>"
                + "        <tr>"
                + "          <td ><p>&nbsp;</p></td>"
                + "        </tr>"
                + "        <tr>"
                + "          <td ><p>&nbsp;</p></td>"
                + "        </tr>"
                + "        <tr>"
                + "          <td ><div><span style=\"\">C. HUGO GUILLERMO DIAZ COVARRUBIAS</span></div>"
                + "            <div><span style=\"\">Secretario General</span></div>"
                + "            <p>&nbsp;</p></td>"
                + "        </tr>"
                + "        <tr>"
                + "          <td ><div><span style=\"font-size: x-small;\">ccp</span></div>"
                + "            <span style=\"font-size: x-small;\">/LIC. JAVIER CASTA—EDA CORDOBA</span>"
                + "            <div><span style=\"font-size: x-small;\">/LIC. GUSTAVO GALINDO VALDES</span></div>"
                + "            <div><span style=\"font-size: x-small;\">/H. COMIT… EJECUTIVO</span></div>"
                + "            <div><span style=\"font-size: x-small;\">/Archivo</span></div></td>"
                + "        </tr>"
                + "      </table>"
                + "    </td>"
                + "  </tr>"
                + "</table>"
                + "</body>"
                + "</html>";

        System.out.println(str);

        return str;

    }

    /**
     *
     * @param selectedTraba36aList
     * @param candidatos
     * @return
     */
    public String getReporte3(List<Traba36a> selectedTraba36aList, String candidatos) {

        String str;
        String strTraba36aConEsp = "";
        String strTraba36aSinEsp = "";

        List<Traba36a> selectedTraba36aListConEspecialidad = selectedTraba36aList.stream()
                .filter(traba36a -> traba36a.getEstraA() != null && !traba36a.getEstraA().trim().isEmpty()).collect(Collectors.toList());
        List<Traba36a> selectedTraba36aListSinEspecialidad = selectedTraba36aList.stream()
                .filter(traba36a -> traba36a.getEstraA() == null || traba36a.getEstraA().trim().isEmpty()).collect(Collectors.toList());
        int i;
        System.out.println("selectedTraba36aListConEspecialidad" + selectedTraba36aListConEspecialidad.size());
        System.out.println("selectedTraba36aListSinEspecialidad" + selectedTraba36aListSinEspecialidad.size());

        str = "<html>"
                + "<head>"
                + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />"
                + "<title>Entrevista</title>"
                + "</head>"
                + "<body>";

        str = str + "<table width=\"100%\">"
                + "  <tr>"
                + "    <td style=\"text-align:right;\">" + ubicacion.toUpperCase() + "., a " + FacesUtil.getDatewFormat() + "</td>"
                + "  </tr>"
                + "  <tr>"
                + "    <td style=\"text-align: right;\">HDC/" + oetraA + "</td>"
                + "  </tr>"
                + "  <tr>"
                + "    <td ><div><span style=\"text-align:center;\">Relación de Aspirantes que entregaron su Documentación para el tramite de Contratación, en la Planta</span> <span >en la Planta: " + enterpriseName + "</span></div></td>"
                + "  </tr>";
        if (!selectedTraba36aListConEspecialidad.isEmpty()) {
            str = str + "  <tr>"
                    + "    <td ><br/><br/>"
                    + "<span ><strong>CON ESPECIALIDAD</strong></span></td>"
                    + "  </tr>"
                    + "        <tr><td><table width=\"100%\" border=\"1\">"
                    + "          <tr>"
                    + "            <td>No</td>"
                    + "            <td>Nombre:</td>"
                    + "            <td>Especialidad</td>"
                    + "          </tr>";
            i = 1;
            for (Traba36a eConEsp : selectedTraba36aListConEspecialidad) {
                strTraba36aConEsp = strTraba36aConEsp + "<tr>"
                        + "<td>" + i++ + "</td>"
                        + "<td>" + eConEsp.getNctraA() + "</td>"
                        + "<td>" + eConEsp.getEstraA() + "</td>"
                        + "</tr>";
            }
            str += strTraba36aConEsp;
            str = str + " </table></td></tr>"
                    + " <tr>"
                    + "    <td><span ><strong>Total de Registros:  " + selectedTraba36aListConEspecialidad.size() + "</strong></span></td>"
                    + " </tr>";
        }
        if (!selectedTraba36aListSinEspecialidad.isEmpty()) {
            str = str + "  <tr>"
                    + "    <td ><br/><br/><span ><strong>SIN ESPECIALIDAD</strong></span></td>"
                    + "  </tr>"
                    + "        <tr><td><table width=\"100%\" border=\"1\">"
                    + "          <tr>"
                    + "            <td>No</td>"
                    + "            <td>Nombre:</td>"
                    + "          </tr>";
            i = 1;
            for (Traba36a eSinEsp : selectedTraba36aListSinEspecialidad) {
                strTraba36aSinEsp = strTraba36aSinEsp + "<tr>"
                        + "<td>" + i++ + "</td>"
                        + "<td>" + eSinEsp.getNctraA() + "</td>"
                        + "</tr>";
            }
            str += strTraba36aSinEsp;
            str = str + " </table></td></tr>"
                    + " <tr>"
                    + "    <td><span ><strong>Total de Registros:  " + selectedTraba36aListSinEspecialidad.size() + "</strong></span></td>"
                    + " </tr>";
        }

        str = str + " <tr>"
                + "    <td ><span ><strong>Gran Total de Registros: " + candidatos + "</strong></span></td>"
                + "  </tr>"
                + "</table>";

        System.out.println(str);
        return str;

    }

    /**
     *
     * @return
     */
    public String getReporte4() {

        String str;
        str = "<html><head></head><body><table style=\" text-align:justify; \" border=\"1\">"
                + "  <tr>"
                + "    <td style=\"border-color: black; text-align: center; background-color: black;color: #ffffff; font-weight:bold;\">Requisitos Indispensables<br/>para Contratación</td>"
                + "  </tr>"
                + "  <tr>"
                + "    <td>Edad de 18 a 45 años y con Disponibildad para Trabajar en Cualquier Turno y Horario </td>"
                + "  </tr>"
                + "  <tr>"
                + "    <td style=\"border-color: black; text-align: center; background-color: black;color: #ffffff; font-weight:bold;\">Documentación Original y 2 Copias<br/>Fotostáticas en Tamaño Carta de: </td>"
                + "  </tr>"
                + "  <tr>"
                + "    <td>Acta de Nacimiento – Reciente (con Fecha de Certificación, no Mayor a 01 Año) </td>"
                + "  </tr>"
                + "  <tr>"
                + "    <td>Constancia de C.U.R.P. (Clave única de Registro de Población) – Reciente (con Fecha de Emisión, No Mayor a 01 Año)</td>"
                + "  </tr>"
                + "  <tr>"
                + "    <td>Cartilla Nacional de Salud y/o Esquema de Vacunación </td>"
                + "  </tr>"
                + "  <tr>"
                + "    <td>Comprobante de Domicilio, Cualquiera de los Siguientes Servicios: Predial, Cable, Gas Natural, Luz, Agua y Teléfono (con Vigencia no Mayor a 02 Meses), Verifica que Este Correcta y Actualizada la Dirección </td>"
                + "  </tr>"
                + "  <tr>"
                + "    <td>Certificados de Estudios: Primaria, Secundaria, Preparatoria y/o Carta de Pasante, Carrera Técnica, Titulo y/o Cedula Profesional (Todos) </td>"
                + "  </tr>"
                + "  <tr>"
                + "    <td>Credencial para Votar – IFE/INE (Vigente Y Actualizada) </td>"
                + "  </tr>"
                + "  <tr>"
                + "    <td>Cartilla del Servicio Militar Nacional (en 01 Hoja, Pre cartilla y/o Cartilla Liberada </td>"
                + "  </tr>"
                + "  <tr>"
                + "    <td>Constancia de Registro Ante El I.M.S.S. (Cualquier documento oficial no mayor 01 Año) </td>"
                + "  </tr>"
                + "  <tr>"
                + "    <td>Constancia de Inscripción al Registro Federal de Contribuyentes (R.F.C.), Expedida por el SAT </td>"
                + "  </tr>"
                + "  <tr>"
                + "    <td>Comprobante de Afore,(si no Estas Aforado, Realiza tu Tramite) </td>"
                + "  </tr>"
                + "  <tr>"
                + "    <td>Cartas de Recomendación de los últimos 02 Empleos (si no ha Trabajado Anteriormente, Deberán ser 02 Cartas Personales con Copia de IFE/INE por ambos lados, anexar número telefónico y firma Autógrafa en tinta azul) </td>"
                + "  </tr>"
                + "  <tr>"
                + "    <td>Comprobante de Ingresos del ultimo empleo (Recibo de Nomina ó Estado de Cuenta Bancaria – en caso de tenerlo) </td>"
                + "  </tr>"
                + "  <tr>"
                + "    <td>Número de afiliación y/o comprobante de Fonacot (en caso de tenerlo) </td>"
                + "  </tr>"
                + "  <tr>"
                + "    <td>Comprobante de Infonavit – aviso de retención de descuentos (en caso de tenerlo) </td>"
                + "  </tr>"
                + "  <tr>"
                + "    <td>Licencia de conducir vigente – (en caso de tenerla) </td>"
                + "  </tr>"
                + "  <tr>"
                + "    <td>Tarjeta de circulación de su vehículo – (en caso de tener) </td>"
                + "  </tr>"
                + "  <tr>"
                + "    <td>Pasaporte mexicano y visa – aun vencidos (en caso de tenerlos) </td>"
                + "  </tr>"
                + "  <tr>"
                + "    <td>Acta de matrimonio ó divorcio, acta de nacimiento de su esposo(a) ó cónyuge (según su estado civil) – reciente (con fecha de certificación, no mayor a 01 año) </td>"
                + "  </tr>"
                + "  <tr>"
                + "    <td>Acta de nacimiento de hijo(s) - todos (en caso de tener) – reciente(s), (con fecha de certificación, no mayor a 01 año) </td>"
                + "  </tr>"
                + "  <tr>"
                + "    <td>Acta de nacimiento de beneficiario(s) para el seguro de vida – en caso de ser padres, hermanos u otra persona – reciente (con fecha de certificación, no mayor a 01 año)</td>"
                + "  </tr>"
                + "  <tr>"
                + "    <td>Formato de recomendación con copia del gafete ó credencial de la persona que lo recomienda – debe de contener fecha y firma autógrafa en tinta azul </td>"
                + "  </tr>"
                + "  <tr>"
                + "    <td>10 Fotografías tamaño infantil a color – recientes (no mas de 02 meses) </td>"
                + "  </tr>"
                + "  <tr>"
                + "    <td style=\"text-align: center; font-weight:bold; \">Nota: los documentos tamaño oficio, solicitar reducción para que las copias sean tamaño carta</td>"
                + "  </tr>"
                + "</table>"
                + "</body></html>";

        System.out.println(str);

        return str;

    }

    /**
     *
     * @param candidatos
     * @return
     */
    public String getReporte5(String candidatos) {

        String str;

        str = "<html>"
                + "<head>"
                + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />"
                + "<title>Entrevista</title>"
                + "</head>"
                + "<body>"
                + "<table style=\" width:100%\">"
                + "  <tr>"
                + "    <td  style=\" width:30%\">"
                + "    <table  style=\" width:100%\">"
                + "        <tr>"
                + "          <td style=\"text-align: center;\"><div>COMITE EJECUTIVO</div></td>"
                + "        </tr>"
                + "        <tr>"
                + "          <td style=\"text-align: center;\"><span style=\"font-size: x-small;\">HUGO GUILLERMO DIAZ COVARRUBIAS</span>"
                + "            <div><span style=\"font-size: x-small;\">Secretario General</span></div></td>"
                + "        </tr>"
                + "        <tr>"
                + "          <td style=\"text-align: center;\"><span style=\"font-size: x-small;\">GUILLERMO DIAZ CASTA&Ntilde;EDA</span>"
                + "            <div><span style=\"font-size: x-small;\">Secretario del Interior</span></div></td>"
                + "        </tr>"
                + "        <tr>"
                + "          <td style=\"text-align: center;\"><span style=\"font-size: x-small;\">CARLOS ALBERTO VAZQUEZ RODRIGUEZ</span>"
                + "            <div><span style=\"font-size: x-small;\">Secretario del Exterior</span></div></td>"
                + "        </tr>"
                + "        <tr>"
                + "          <td style=\"text-align: center;\"><span style=\"font-size: x-small;\">ALBERTO RABAGO CAMACHO</span>"
                + "            <div><span style=\"font-size: x-small;\">Secretario Tesorero</span></div></td>"
                + "        </tr>"
                + "        <tr>"
                + "          <td style=\"text-align: center;\"><span style=\"font-size: x-small;\">DANTE ENRIQUE INTRIAGO SIERRA</span>"
                + "            <div><span style=\"font-size: x-small;\">Secretario de Actas</span></div></td>"
                + "        </tr>"
                + "      </table>"
                + "    </td>"
                + "    <td  style=\" width:70%\">"
                + "    <table style=\" width:100%\">"
                + "        <tr>"
                + "          <td style=\"text-align: right;\">" + ubicacion.toUpperCase() + "., a " + FacesUtil.getDatewFormat() + "</td>"
                + "        </tr>"
                + "        <tr>"
                + "          <td style=\"text-align: right;\">HDC/" + oetraA + "</td>"
                + "        </tr>"
                + "        <tr>"
                + "          <td>LIC. FRANCISCO JAVIER ARAGON NAVARRO"
                + "            <div>Director de Recursos Humanos</div>"
                + "            <div><span style=\"font-size: x-small;\">y Relaciones Laborales</span></div>"
                + "            <div><span style=\"font-size: x-small;\">FCA México S.A. de C.V.</span></div>"
                + "            <div><span style=\"font-size: x-small;\">Presente</span></div></td>"
                + "        </tr>"
                + "        <tr><td style=\"text-align: right;\"><div><span style= \"\">Asunto: Envió de Documentos para Tramite de Contratación.</span></div></td></tr>"
                + "        <tr>"
                + "          <td style=\"width: 100%; text-align: left;\"><div>&nbsp;</div>"
                + "            <div>&nbsp;</div>"
                + "            <div><span style= \"\">Por medio de la presente le enviamos a usted, los juego(s) de copias de documentos, para el tramite de contratación de personal, como a continuación se detalla::</span></div>"
                + "            <div>&nbsp;</div>"
                + "            <div><span style= \"\">Planta: " + enterpriseName + "</span></div>"
                + "            <div><span style= \"\">Aspirantes: " + candidatos + "</span></div>"
                + "            <div><span style= \"\">Nota: Se Anexa Relación y copia formato de verificación de documentos por cada Aspirante.</span></div>"
                + "            <p>&nbsp;</p></td>"
                + "        </tr>"
                + "        <tr>"
                + "          <td >Agradeciendo la atención que se sirva brindar a la presente, quedo de usted.</td>"
                + "        </tr>"
                + "        <tr>"
                + "          <td ><p>&nbsp;</p>"
                + "            <div><span style=\"\">A T E N T A M E N T E</span></div>"
                + "            <div><span style=\"\">POR UN MEXICO MEJOR</span></div>"
                + "            <div><span style=\"\">P/EL COMITE EJECUTIVO</span></div></td>"
                + "        </tr>"
                + "        <tr>"
                + "          <td ><p>&nbsp;</p></td>"
                + "        </tr>"
                + "        <tr>"
                + "          <td ><p>&nbsp;</p></td>"
                + "        </tr>"
                + "        <tr>"
                + "          <td ><div><span style=\"\">C. HUGO GUILLERMO DIAZ COVARRUBIAS</span></div>"
                + "            <div><span style=\"\">Secretario General</span></div>"
                + "            <p>&nbsp;</p></td>"
                + "        </tr>"
                + "        <tr>"
                + "          <td ><div><span style=\"font-size: x-small;\">ccp</span></div>"
                + "            <span style=\"font-size: x-small;\">/LIC. JAVIER CASTA—EDA CORDOBA</span>"
                + "            <div><span style=\"font-size: x-small;\">/LIC. GUSTAVO GALINDO VALDES</span></div>"
                + "            <div><span style=\"font-size: x-small;\">/H. COMIT… EJECUTIVO</span></div>"
                + "            <div><span style=\"font-size: x-small;\">/Archivo</span></div></td>"
                + "        </tr>"
                + "      </table>"
                + "    </td>"
                + "  </tr>"
                + "</table>"
                + "</body>"
                + "</html>";

        System.out.println(str);

        return str;

    }

    /**
     *
     * @param selectedTraba36aList
     * @param candidatos
     * @return
     */
    public String getReporte6(List<Traba36a> selectedTraba36aList, String candidatos) {

        String str;
        String strTraba36a = "";

        int i;

        str = "<html>"
                + "<head>"
                + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />"
                + "<title>Entrega de Documetación</title>"
                + "</head>"
                + "<body>";

        str = str + "<table width=\"100%\">"
                + "  <tr>"
                + "    <td style=\"text-align:right;\">" + ubicacion.toUpperCase() + "., a " + FacesUtil.getDatewFormat() + "</td>"
                + "  </tr>"
                + "  <tr>"
                + "    <td style=\"text-align: right;\">HDC/" + oetraA + "</td>"
                + "  </tr>"
                + "  <tr>"
                + "    <td ><div><span >Relación de personal para entrega de documentación el día</span> <span >" + FacesUtil.getDatewFormat(entraA) + "</span> <span >a la Planta: " + enterpriseName + "</span></div></td>"
                + "  </tr>";
        if (!selectedTraba36aList.isEmpty()) {
            str = str + "       <tr><td><br/><br/><table width=\"100%\" border=\"1\">"
                    + "          <tr>"
                    + "            <td>No</td>"
                    + "            <td>Nombre:</td>"
                    + "            <td>Especialidad</td>"
                    + "          </tr>";
            i = 1;
            for (Traba36a e : selectedTraba36aList) {
                strTraba36a = strTraba36a + "<tr>"
                        + "<td>" + i++ + "</td>"
                        + "<td>" + e.getNctraA() + "</td>"
                        + "<td>" + e.getEstraA() + "</td>"
                        + "</tr>";
            }
            str += strTraba36a;
            str = str + " </table></td></tr>";
        }

        str = str + " <tr>"
                + "    <td ><span ><strong>Total de Registros: " + candidatos + "</strong></span></td>"
                + "  </tr>"
                + "</table>";

        System.out.println(str);

        return str;

    }

    /**
     *
     * @param candidatos
     * @return
     */
    public String getReporte32(String candidatos) {

        String str;

        str = "<html>"
                + "<head>"
                + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />"
                + "<title>Entrevista</title>"
                + "</head>"
                + "<body>"
                + "<table style=\" width:100%\">"
                + "  <tr>"
                + "    <td  style=\" width:30%\">"
                + "    <table  style=\" width:100%\">"
                + "        <tr>"
                + "          <td style=\"text-align: center;\"><div>COMITE EJECUTIVO</div></td>"
                + "        </tr>"
                + "        <tr>"
                + "          <td style=\"text-align: center;\"><span style=\"font-size: x-small;\">HUGO GUILLERMO DIAZ COVARRUBIAS</span>"
                + "            <div><span style=\"font-size: x-small;\">Secretario General</span></div></td>"
                + "        </tr>"
                + "        <tr>"
                + "          <td style=\"text-align: center;\"><span style=\"font-size: x-small;\">GUILLERMO DIAZ CASTA&Ntilde;EDA</span>"
                + "            <div><span style=\"font-size: x-small;\">Secretario del Interior</span></div></td>"
                + "        </tr>"
                + "        <tr>"
                + "          <td style=\"text-align: center;\"><span style=\"font-size: x-small;\">CARLOS ALBERTO VAZQUEZ RODRIGUEZ</span>"
                + "            <div><span style=\"font-size: x-small;\">Secretario del Exterior</span></div></td>"
                + "        </tr>"
                + "        <tr>"
                + "          <td style=\"text-align: center;\"><span style=\"font-size: x-small;\">ALBERTO RABAGO CAMACHO</span>"
                + "            <div><span style=\"font-size: x-small;\">Secretario Tesorero</span></div></td>"
                + "        </tr>"
                + "        <tr>"
                + "          <td style=\"text-align: center;\"><span style=\"font-size: x-small;\">DANTE ENRIQUE INTRIAGO SIERRA</span>"
                + "            <div><span style=\"font-size: x-small;\">Secretario de Actas</span></div></td>"
                + "        </tr>"
                + "      </table>"
                + "    </td>"
                + "    <td  style=\" width:70%\">"
                + "    <table style=\" width:100%\">"
                + "        <tr>"
                + "          <td style=\"text-align: right;\">" + ubicacion.toUpperCase() + "., a " + FacesUtil.getDatewFormat() + "</td>"
                + "        </tr>"
                + "        <tr>"
                + "          <td style=\"text-align: right;\">HDC/" + oetraA + "</td>"
                + "        </tr>"
                + "        <tr>"
                + "          <td>LIC. FRANCISCO JAVIER ARAGON NAVARRO"
                + "            <div>Director de Recursos Humanos</div>"
                + "            <div><span style=\"\">y Relaciones Laborales</span></div>"
                + "            <div><span style=\"\">FCA México S.A. de C.V.</span></div>"
                + "            <div><span style=\"\">Presente</span></div></td>"
                + "        </tr>"
                + "        <tr><td style=\"text-align: right;\"><div><span style= \"\">Asunto: Contratros de Nuevo Ingreso.</span></div></td></tr>"
                + "        <tr>"
                + "          <td style=\"width: 100%; text-align: left;\"><div>&nbsp;</div>"
                + "            <div>&nbsp;</div>"
                + "            <div><span style= \"\">Por medio de la presente le enviamos a usted, el(los) contrato(s) individual(es) de trabajo, firmados por el grupo de personal de nuevo ingreso, con efectividad a partir del dia que a continuacion se detalla:</span></div>"
                + "            <div>&nbsp;</div>"
                + "            <div><span style= \"\">Planta: " + enterpriseName + "</span></div>"
                + "            <div><span style= \"\">Fecha de Ingreso: " + FacesUtil.getDatewFormat(new Date()) + "</span></div>"
                + "            <div><span style= \"\">Candidatos de personal: " + candidatos + "</span></div>"
                + "            <div><span style= \"\">Nota: Se Anexa Relación.</span></div>"
                + "            <p>&nbsp;</p></td>"
                + "        </tr>"
                + "        <tr>"
                + "          <td >Agradeciendo la atención que se sirva brindar a la presente, quedo de usted.</td>"
                + "        </tr>"
                + "        <tr>"
                + "          <td ><p>&nbsp;</p>"
                + "            <div><span style=\"\">A T E N T A M E N T E</span></div>"
                + "            <div><span style=\"\">POR UN MEXICO MEJOR</span></div>"
                + "            <div><span style=\"\">P/EL COMITE EJECUTIVO</span></div></td>"
                + "        </tr>"
                + "        <tr>"
                + "          <td ><p>&nbsp;</p></td>"
                + "        </tr>"
                + "        <tr>"
                + "          <td ><p>&nbsp;</p></td>"
                + "        </tr>"
                + "        <tr>"
                + "          <td ><div><span style=\"\">C. HUGO GUILLERMO DIAZ COVARRUBIAS</span></div>"
                + "            <div><span style=\"\">Secretario General</span></div>"
                + "            <p>&nbsp;</p></td>"
                + "        </tr>"
                + "        <tr>"
                + "          <td ><div><span style=\"font-size: x-small;\">ccp</span></div>"
                + "            <span style=\"font-size: x-small;\">/LIC. JAVIER CASTA—EDA CORDOBA</span>"
                + "            <div><span style=\"font-size: x-small;\">/LIC. GUSTAVO GALINDO VALDES</span></div>"
                + "            <div><span style=\"font-size: x-small;\">/H. COMIT… EJECUTIVO</span></div>"
                + "            <div><span style=\"font-size: x-small;\">/Archivo</span></div></td>"
                + "        </tr>"
                + "      </table>"
                + "    </td>"
                + "  </tr>"
                + "</table>"
                + "</body>"
                + "</html>";

        System.out.println(str);

        return str;

    }

    /**
     *
     * @param selectedTraba36aList
     * @param candidatos
     * @return
     */
    public String getReporte33(List<Traba36a> selectedTraba36aList, String candidatos) {

        String str;
        String strTraba36a = "";

        int i;

        str = "<html>"
                + "<head>"
                + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />"
                + "<title>Entrevista</title>"
                + "</head>"
                + "<body>";

        str = str + "<table width=\"100%\">"
                + "  <tr>"
                + "    <td style=\"text-align:right;\">" + ubicacion.toUpperCase() + "., a " + FacesUtil.getDatewFormat() + "</td>"
                + "  </tr>"
                + "  <tr>"
                + "    <td style=\"text-align: right;\">HDC/" + oetraA + "</td>"
                + "  </tr>"
                + "  <tr>"
                + "    <td ><div><span >Relación de Personal de Nuevo Ingreso a Partir del</span> <span >" + FacesUtil.getDatewFormat(new Date()) + "</span> <span >a la Planta: " + enterpriseName + "</span></div></td>"
                + "  </tr>";
        if (!selectedTraba36aList.isEmpty()) {
            str = str + "       <tr><td><br/><br/><table width=\"100%\" border=\"1\">"
                    + "          <tr>"
                    + "            <td>No</td>"
                    + "            <td>Nombre:</td>"
                    + "            <td>Especialidad</td>"
                    + "          </tr>";
            i = 1;
            for (Traba36a e : selectedTraba36aList) {
                strTraba36a = strTraba36a + "<tr>"
                        + "<td>" + i++ + "</td>"
                        + "<td>" + e.getNctraA() + "</td>"
                        + "<td>" + e.getEstraA() + "</td>"
                        + "</tr>";
            }
            str += strTraba36a;
            str = str + " </table></td></tr>";
        }

        str = str + " <tr>"
                + "    <td ><span ><strong>Total de Registros: " + candidatos + "</strong></span></td>"
                + "  </tr>"
                + "</table>";

        System.out.println(str);

        return str;

    }

}
