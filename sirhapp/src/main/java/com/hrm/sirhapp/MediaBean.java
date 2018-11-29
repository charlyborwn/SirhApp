package com.hrm.sirhapp;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.ElementList;
import com.itextpdf.tool.xml.Pipeline;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import com.hrm.sirhapp.model.Usuar24;
import com.hrm.sirhapp.util.FacesUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.primefaces.model.DefaultStreamedContent;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Named
@SessionScoped
public class MediaBean extends PdfPageEventHelper implements Serializable {

    private static final long serialVersionUID = 1L;

    private Font font;
    private PdfTemplate t;
    private Image total;
    private String html;

    private static String reporte;
    private static Boolean direccion;
    private static Usuar24 user;

    private byte[] data;
    private DefaultStreamedContent streamedContent;

    /**
     *
     */
    public static final String FONT = "reports/OpenSans-Regular.ttf";

    /**
     *
     */
    public static final String BOLD = "reports/OpenSans-Bold.ttf";

    /**
     *
     */
    public static final String CSS_DIR = "reports/";

    /**
     *
     */
    public static final String HEADER = ""
            + "<table width=\"19.59cm\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">"
            + "<tr>"
            + "<td><img style=\"width: 19.59cm;\" src=\"http://localhost:8080/sirhapp/resources/ultima-layout/images/Header.png\" /></td>"
            + "</tr>"
            + "</table>";

    /**
     *
     */
    public static final String FOOTER = ""
            + "<table width=\"19.59cm\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">"
            + "<tr>"
            + "<td align=\"center\"><span style=\"text-align=center;font-size:8pt;\"></span></td>"
            + "</tr>"
            + "</table>";

    /**
     *
     */
    public static final String SINDATOS = ""
            + "<html><head></head><body>"
            + "<br/>"
            + "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">"
            + "<tr>"
            + "<td align=\"center\"><span style=\"text-align=center;font-size:20pt;\">SIN DATOS</span></td>"
            + "</tr>"
            + "<tr>"
            + "<td align=\"center\"><span style=\"text-align=center;font-size:20pt;\">PAGINA EN BLANCO</span></td>"
            + "</tr>"
            + "</table>"
            + "</body></html>";

    /**
     *
     */
    protected ElementList header;

    /**
     *
     */
    protected ElementList footer;

    @PostConstruct
    private void init() {
        try {

            this.generatePrintOutput();

        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param htmlStrings
     * @param repName
     * @param repDireccion
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public InputStream getPdf(String htmlStrings, String repName, Boolean repDireccion) throws IOException, DocumentException {

        user = FacesUtil.getUsuar24();
        reporte = repName;
        direccion = repDireccion;

        if (htmlStrings.isEmpty()) {
            htmlStrings = SINDATOS;
        }

        InputStream in = new ByteArrayInputStream(new MediaBean().createPdf(htmlStrings));

        return in;
    }

    /**
     *
     * @param path
     */
    public void getPdf(Object path) {

        ResourceBundle bundle = ResourceBundle.getBundle("Bundle");
        String PathFileSystemFull = bundle.getString("PathFileSystemFull");

        byte[] bFile;

        if (path != null) {
            try {
                bFile = FileUtils.readFileToByteArray(new File(PathFileSystemFull + path));
                //System.out.println(PathFileSystemFull + path);
                streamedContent = new DefaultStreamedContent(new ByteArrayInputStream(bFile), "application/pdf");
            } catch (IOException ex) {
                Logger.getLogger(MediaBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     *
     * @return
     * @throws IOException
     */
    public DefaultStreamedContent getMedia() throws IOException {
        if (data == null) {
            return null;
        }
        DefaultStreamedContent pdf;
        try (InputStream stream = new ByteArrayInputStream(data)) {
            pdf = new DefaultStreamedContent(stream, "application/pdf");
            stream.close();
        }

        return pdf;
    }

    /**
     *
     * @param htmlStrings
     * @param repName
     * @param repDireccion
     * @return
     */
    public byte[] getBytePdf(String htmlStrings, String repName, Boolean repDireccion) {

        byte[] out = null;
        try {
            user = FacesUtil.getUsuar24();
            reporte = repName;
            direccion = repDireccion;
            if (htmlStrings.isEmpty()) {
                htmlStrings = SINDATOS;
            }
            out = new MediaBean().createPdf(htmlStrings);

        } catch (DocumentException | IOException ex) {
            Logger.getLogger(MediaBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return out;
    }

    /**
     *
     * @param htmlStrings
     * @throws IOException
     * @throws DocumentException
     */
    public void printPDF(String htmlStrings) throws IOException, DocumentException {

        user = FacesUtil.getUsuar24();

        reporte = "";
        direccion = false;

        if (htmlStrings.isEmpty()) {
            htmlStrings = SINDATOS;
        }

        InputStream in = new ByteArrayInputStream(new MediaBean().createPdf(htmlStrings));
        in = new ByteArrayInputStream(new MediaBean().preventCopyingPdf(in, null, null));
        this.streamedContent = new DefaultStreamedContent(in, "application/pdf");

    }

    /**
     *
     * @param htmlStrings
     * @param repName
     * @param repDireccion
     * @throws IOException
     * @throws DocumentException
     */
    public void printPDF(String htmlStrings, String repName, Boolean repDireccion) throws IOException, DocumentException {

        user = FacesUtil.getUsuar24();

        reporte = repName;
        direccion = repDireccion;

        if (htmlStrings.isEmpty()) {
            htmlStrings = SINDATOS;
        }

        InputStream in = new ByteArrayInputStream(new MediaBean().createPdf(htmlStrings));
        in = new ByteArrayInputStream(new MediaBean().preventCopyingPdf(in, null, null));
        this.streamedContent = new DefaultStreamedContent(in, "application/pdf");

    }

    /**
     *
     * @param beanName
     * @param oPassword
     * @param uPassword
     */
    public void printPDF(String beanName, String oPassword, String uPassword) {

        user = FacesUtil.getUsuar24();

        try {
            Object book = FacesUtil.getManagedBean(beanName, Object.class);
            Method method = book.getClass().getMethod("contentPdf");
            Object returnValue = method.invoke(book);

            //System.out.println(beanName);
            if (returnValue.toString().length() > 0) {
                InputStream in = new ByteArrayInputStream(new MediaBean().createPdf((String) returnValue));
                if (oPassword != null && oPassword.length() > 0 || uPassword != null && uPassword.length() > 0) {
                    in = new ByteArrayInputStream(new MediaBean().preventCopyingPdf(in, oPassword, uPassword));
                    //in = new ByteArrayInputStream((byte[]) new MediaBean().encryptPdf(in, oPassword, uPassword));
                }

                this.streamedContent = new DefaultStreamedContent(in, "application/pdf");

            } else {
                this.streamedContent = null;
            }
        } catch (DocumentException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * @throws IOException
     */
    public MediaBean() throws IOException {
        header = XMLWorkerHelper.parseToElementList(HEADER, null);
        footer = XMLWorkerHelper.parseToElementList(FOOTER, null);
    }

    @Override
    public void onOpenDocument(PdfWriter writer, Document document) {
        t = writer.getDirectContent().createTemplate(32, 16);//Position of Page Count
        try {

            total = Image.getInstance(t);
            total.setRole(PdfName.ARTIFACT);

            font = new Font(FontFactory.getFont(FontFactory.TIMES, 8, Font.NORMAL, BaseColor.BLACK));

        } catch (DocumentException e) {
            throw new ExceptionConverter(e);
        }
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {

        try {
            //Add Header
            ColumnText ct = new ColumnText(writer.getDirectContent());
            ct.setSimpleColumn(new Rectangle(26, 80, 612, 792));//position of header X26Start, Y80Height, 612Widht,Y792Start last both are Pixels from LETTER
            header.forEach((e) -> {
                ct.addElement(e);
            });
            ct.go();

            //Add Footer Detail
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date localdate = new Date();
            String stringDate = dateFormat.format(localdate);

            PdfPTable table = new PdfPTable(4);

            table.setWidths(new int[]{15, 20, 12, 3});
            table.setTotalWidth(570);//widht del footer
            table.getDefaultCell().setFixedHeight(14);
            table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            table.getDefaultCell().setPadding(0);
            table.getDefaultCell().setPaddingTop(0);
            table.getDefaultCell().setPaddingBottom(0);
            table.getDefaultCell().setPaddingLeft(0);
            table.getDefaultCell().setPaddingRight(0);
            table.setSpacingAfter(0);
            table.setSpacingBefore(0);

            PdfPCell userName = new PdfPCell(new Phrase(user.getNcusu(), font));
            userName.setHorizontalAlignment(Element.ALIGN_LEFT);
            userName.setBorder(Rectangle.NO_BORDER);
            userName.setBackgroundColor(BaseColor.WHITE);
            userName.setPadding(0);
            userName.setPaddingTop(0);
            userName.setPaddingBottom(0);
            userName.setPaddingLeft(0);
            userName.setPaddingRight(0);
            table.addCell(userName);

            PdfPCell date = new PdfPCell(new Phrase(stringDate, font));
            date.setHorizontalAlignment(Element.ALIGN_CENTER);
            date.setBorder(Rectangle.NO_BORDER);
            date.setBackgroundColor(BaseColor.WHITE);
            date.setPadding(0);
            date.setPaddingTop(0);
            date.setPaddingBottom(0);
            date.setPaddingLeft(0);
            date.setPaddingRight(0);
            table.addCell(date);

            PdfPCell page = new PdfPCell(new Phrase(String.format("Pagina %d de ", writer.getPageNumber()), font));
            page.setHorizontalAlignment(Element.ALIGN_RIGHT);
            page.setBorder(Rectangle.NO_BORDER);
            page.setBackgroundColor(BaseColor.WHITE);
            page.setPadding(0);
            page.setPaddingTop(0);
            page.setPaddingBottom(0);
            page.setPaddingLeft(0);
            page.setPaddingRight(0);
            table.addCell(page);

            PdfPCell count = new PdfPCell(total);
            count.setHorizontalAlignment(Element.ALIGN_LEFT);
            count.setBorder(Rectangle.NO_BORDER);
            count.setBackgroundColor(BaseColor.WHITE);
            count.setPadding(0);
            count.setPaddingTop(0);
            count.setPaddingBottom(0);
            count.setPaddingLeft(0);
            count.setPaddingRight(0);
            table.addCell(count);

            PdfPTable table2 = new PdfPTable(1);
            table2.setTotalWidth(570);//widht del footer
            table2.getDefaultCell().setFixedHeight(14);
            table2.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            table2.getDefaultCell().setPadding(0);
            table2.getDefaultCell().setPaddingTop(0);
            table2.getDefaultCell().setPaddingBottom(0);
            table2.getDefaultCell().setPaddingLeft(0);
            table2.getDefaultCell().setPaddingRight(0);
            table2.setSpacingAfter(0);
            table2.setSpacingBefore(0);

            PdfPCell cell2 = new PdfPCell(new Phrase("KM. 60.5 DE LA CARR. MEXICO-TOLUCA, C.P. 50071 MUNICIPIO DE TOLUCA, EDO. DE MEX. TEL. 01 722 216 22 63", font));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setBackgroundColor(BaseColor.WHITE);
            cell2.setPadding(0);
            cell2.setPaddingTop(0);
            cell2.setPaddingBottom(0);
            cell2.setPaddingLeft(0);
            cell2.setPaddingRight(0);
            if (direccion) {

                table2.addCell(cell2);

            }
            if (!reporte.isEmpty()) {
                cell2 = new PdfPCell(new Phrase(reporte, font));
                cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell2.setBorder(Rectangle.NO_BORDER);
                cell2.setBackgroundColor(BaseColor.WHITE);
                cell2.setPadding(0);
                cell2.setPaddingTop(0);
                cell2.setPaddingBottom(0);
                cell2.setPaddingLeft(0);
                cell2.setPaddingRight(0);
                table2.addCell(cell2);
            }

            PdfContentByte canvas = writer.getDirectContent();
            canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
            table.writeSelectedRows(0, -1, 26, 46, canvas);//36 position of row of footer user "date" "page no" "pag count"
            table2.writeSelectedRows(0, -1, 26, 36, canvas);
            canvas.endMarkedContentSequence();

        } catch (DocumentException ex) {
            Logger.getLogger(MediaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void onCloseDocument(PdfWriter writer, Document document) {
        ColumnText.showTextAligned(t, Element.ALIGN_LEFT,
                new Phrase(" " + String.valueOf(writer.getPageNumber()), font),
                0, 8, 0);
    }

    /**
     *
     * @param event
     */
    public void onPrerender(ComponentSystemEvent event) {

        try {

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            Document document = new Document();
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();

            for (int i = 0; i < 50; i++) {
                document.add(new Paragraph("All work and no play makes Jack a dull boy"));
            }

            streamedContent = new DefaultStreamedContent(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), "application/pdf");
            document.close();

        } catch (DocumentException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     *
     * @param in
     * @param oPassword
     * @param uPassword
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public byte[] preventCopyingPdf(InputStream in, String oPassword, String uPassword) throws IOException, DocumentException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] uHardPasswordByte = null;
        byte[] oHardPasswordByte;

        if (uPassword != null) {
            uHardPasswordByte = uPassword.getBytes();
        }

        if (oPassword != null) {
            oHardPasswordByte = oPassword.getBytes();
        } else {
            oPassword = "D 5BaIZQ@ CqAk+NQCW)7Dkgb@i&02ifu!2TMX*d 0TGK(j(Kq";
            oHardPasswordByte = oPassword.getBytes();
        }

        PdfReader reader = new PdfReader(in);

        PdfStamper stamp = new PdfStamper(reader, byteArrayOutputStream);

        //first argument is the user password. If set to something it asks for password when opening file, not wanted.
        stamp.setEncryption(uHardPasswordByte, oHardPasswordByte, ~(PdfWriter.ALLOW_COPY | PdfWriter.ALLOW_PRINTING), PdfWriter.STANDARD_ENCRYPTION_128);
        stamp.flush();
        stamp.close();

        //do stuff on the stamper, save file.
        byte[] bytes = byteArrayOutputStream.toByteArray();
        reader.close();
        return bytes;

    }

    /**
     *
     * @param in
     * @param oPassword
     * @param uPassword
     * @return
     * @throws IOException
     */
    public byte[] encryptPdf(InputStream in, String oPassword, String uPassword) throws IOException {
        //aadfasdfasdfasdfasdfasdf
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try ( // Create a document and add a page to it
                PDDocument pDDocument = PDDocument.load(in)) {
            int keyLength = 128;

            AccessPermission ap = new AccessPermission();

            // disable printing, everything else is allowed
            ap.setCanPrint(false);

            // Owner password (to open the file with all permissions) is "12345"
            // User password (to open the file but with restricted permissions, is empty here)
            StandardProtectionPolicy spp = new StandardProtectionPolicy(oPassword, uPassword, ap);

            spp.setEncryptionKeyLength(keyLength);
            spp.setPermissions(ap);
            pDDocument.protect(spp);
            pDDocument.save(byteArrayOutputStream);

            byte[] bytes = byteArrayOutputStream.toByteArray();
            pDDocument.close();

            return bytes;
        }

    }

    /**
     *
     * @param htmlStrings
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public byte[] createPdf(String htmlStrings) throws IOException, DocumentException {
        System.out.println(htmlStrings);
        byte[] bytes = null;
        OutputStream out = new ByteArrayOutputStream();
        InputStream str2Pdf = new ByteArrayInputStream(htmlStrings.getBytes("UTF-8"));

        Document document = new Document(PageSize.LETTER, 9, 30, 86, 50);//margins of document
        /* @param marginLeft
            *            the margin on the left
            * @param marginRight
            *            the margin on the right
            * @param marginTop
            *            the margin on the top
            * @param marginBottom
            *            the margin on the bottom*/

        PdfWriter writer = PdfWriter.getInstance(document, out);
        writer.setPageEvent(new MediaBean());

        document.open();
        document.addAuthor(user.getNcusu());
        document.addProducer();
        document.addCreationDate();

        InputStream csspathtest = Thread.currentThread().getContextClassLoader().getResourceAsStream("reports/style.css");

        CSSResolver cssResolver = new StyleAttrCSSResolver();
        CssFile cssFile = XMLWorkerHelper
                .getCSS(csspathtest);
        cssResolver.addCss(cssFile);

        XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
        fontProvider.register("reports/OpenSans-Regular.ttf");

        CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);
        HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
        htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());

        PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
        HtmlPipeline htmlPipeline = new HtmlPipeline(htmlContext, pdf);
        CssResolverPipeline css = new CssResolverPipeline(cssResolver, htmlPipeline);
        
        Pipeline<?> pipeline = new CssResolverPipeline(cssResolver, new HtmlPipeline(htmlContext, new PdfWriterPipeline(document, writer)));

        XMLWorker worker = new XMLWorker(pipeline, true);
        XMLParser p = new XMLParser(worker);

        StringBuilder contentBuilder = new StringBuilder();
        contentBuilder.append(str2Pdf);

        p.parse(str2Pdf);

        document.close();

        writer.flush();
        writer.close();

        bytes = IOUtils.toByteArray(new ByteArrayInputStream(((ByteArrayOutputStream) out).toByteArray()));
        out.flush();

        return bytes;

    }

    /**
     *
     * @throws IOException
     */
    public void generatePrintOutput() throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle("Bundle");
        String pathFileSystem = bundle.getString("PathFileSystem");
        //Simulates the generation of the pdf
        data = FileUtils.readFileToByteArray(new File(pathFileSystem + "/ManualUsuarioGR.pdf"));
    }

    /**
     *
     * @return
     */
    public DefaultStreamedContent getStreamedContent() {
        return streamedContent;
    }

    /**
     *
     * @param streamedContent
     */
    public void setStreamedContent(DefaultStreamedContent streamedContent) {
        this.streamedContent = streamedContent;
    }

    /**
     *
     * @return
     */
    public String getHtml() {
        return html;
    }

    /**
     *
     * @param html
     */
    public void setHtml(String html) {
        this.html = html;
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
     * @param fileName
     */
    public void printMethodParams(final String fileName) {

        //System.out.println(fileName);
        getPdf(fileName);

    }

}
