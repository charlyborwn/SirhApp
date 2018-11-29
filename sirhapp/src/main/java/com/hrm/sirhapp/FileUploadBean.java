package com.hrm.sirhapp;

import com.hrm.sirhapp.model.Constants;
import com.hrm.sirhapp.model.Docum29a;
import com.hrm.sirhapp.service.Traba36aManagerLocal;
import com.hrm.sirhapp.util.FacesUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Named
@RequestScoped
public class FileUploadBean implements Serializable {

    private static final org.apache.logging.log4j.Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(FileUploadBean.class);

    private static final long serialVersionUID = 1L;

    private String pathFileSystemFull;
    private String webFolder;
    private String folder;
    private String fileName;
    private String fileNameSrc;
    private String fileType;
    private String extension;
    private String extensionSrc;
    private UploadedFile uploadedFile;

    @EJB
    private Traba36aManagerLocal traba36aManager;

    @PostConstruct
    private void init() {
        //Se obtiene la ruta definida en el archivo Bundle de recursos del sistema
        ResourceBundle bundle = ResourceBundle.getBundle("Bundle");
        pathFileSystemFull = bundle.getString("PathFileSystemFull");

        this.folder = null;
        this.fileName = null;
        this.fileNameSrc = null;
        this.fileType = null;
        this.extensionSrc = null;
        this.uploadedFile = null;

    }

    /**
     *
     */
    public void cleanFile() {

        this.folder = null;
        this.fileName = null;
        this.fileNameSrc = null;
        this.fileType = null;
        this.extensionSrc = null;
        this.uploadedFile = null;

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Carga Archivos", "Borrado"));

    }

    /**
     *
     * @param event
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public void handleFileUpload(FileUploadEvent event) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        try {

            //Nombre dinamico compuesto por: 
            //1. Hash  
            //2. Fecha y hora  de carga  TimeStamp 
            //3. Numero long dinamico de 6 digitos
            String dynamicFileName;

            //Se obtiene el nombre original del archivo cargado
            fileNameSrc = event.getFile().getFileName();
            //Se obtiene el nombre de la extension desde el nombre original del archivo
            extensionSrc = FilenameUtils.getExtension(fileNameSrc);

            //Se obtiene el Tipo de archivo que se esta cargando en el sistema, Ejemplo: FOTO,CURP, etc, el dato se obtiene de los atributos del componente p:fileUpload
            fileType = (String) event.getComponent().getAttributes().get("fileTypeAttr");

            //Se obtiene el folder raiz Ejemplo LULA900510/ [RFC]/ con "/" para identificarlo como folder, el dato se obtiene de los atributos del componente p:fileUpload
            String rootFolder = (String) event.getComponent().getAttributes().get("rootFolderAttr");

            //Se obtiene el arbol de subdirectorio(s), Ejemplo DOCS/ [Cualquier Folder secundario despues del folde raiz] donde sera guardado el archivo, el dato se obtiene de los atributos del componente p:fileUpload
            String folderTree = (String) event.getComponent().getAttributes().get("folderTreeAttr");

            //Se obtiene el nombre de usuario, el dato se obtiene de los atributos del componente p:fileUpload
            String user = (String) event.getComponent().getAttributes().get("userAttr");

            //Se obtiene el id del archivo, Ejemplo LULA900510 [RFC], el dato se obtiene de los atributos del componente p:fileUpload
            String id = (String) event.getComponent().getAttributes().get("idAttr");

            //Se obtiene el Nombre del backingBean que va a actualizar, el dato se obtiene de los atributos del componente p:fileUpload
            String ownBacking = (String) event.getComponent().getAttributes().get("ownBackingAttr");

            //Se obtiene el Nombre de la variable del backingBean que va a actualizar, el dato se obtiene de los atributos del componente p:fileUpload
            String ownVariableBacking = (String) event.getComponent().getAttributes().get("ownVariableBackingAttr");

            //Se obtiene el backing que va a actualizar, el dato se obtiene de los atributos del componente p:fileUpload
            String backing = (String) event.getComponent().getAttributes().get("backingAttr");

            uploadedFile = event.getFile();

            if (folderTree == null) {
                folderTree = "";
            }

            folder = pathFileSystemFull + rootFolder + folderTree;
            File directory = new File(folder);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            webFolder = rootFolder + folderTree;

            System.out.println("webFolder" + webFolder);

            dynamicFileName = getDynamicFileName();
            this.fileName = fileType + "_" + dynamicFileName + "." + extensionSrc;
            if (dynamicFileName != null) {
                copyFile(uploadedFile.getInputstream());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Carga Archivos", "Copiado"));
            }

            if (backing == null) {
                backing = "";
            }

            if (ownBacking == null) {
                ownBacking = "";
            }
            if (ownVariableBacking == null) {
                ownVariableBacking = "setPath";
            }
            switch (backing) {
                case "docum29aAddBacking":
                    Docum29a docum29a = new Docum29a();

                    docum29a.setRfdocA(id);
                    docum29a.setTddocA(fileType);
                    docum29a.setPadocA(webFolder + fileName);
                    docum29a.setUsdocA(user);
                    docum29a.setStdocA(Constants.RECORD_ACTIVED);
                    docum29a.setFedocA(new Date());

                    Object docum29aAddBacking = FacesUtil.getManagedBean("docum29aAddBacking", Object.class);
                    Method register = docum29aAddBacking.getClass().getMethod("register", Docum29a.class);

                    Object returnValue = register.invoke(docum29aAddBacking, docum29a);
                    break;
                default:
                    FacesUtil.setSessionBeanAttValue(ownBacking, ownVariableBacking, webFolder + fileName, String.class);

                    System.out.println("backing: " + ownBacking + " ID: " + id + " " + ownVariableBacking + ": /" + webFolder + fileName);

                    break;
            }

            extension = FilenameUtils.getExtension(fileName);

            LOGGER.info(FacesUtil.getUserIpAddressLog() + "| ID: " + id + " | ARC: " + folder + fileName + " | ACT: " + backing + " : " + ownBacking);

        } catch (IOException ex) {
            LOGGER.error(FacesUtil.getUserIpAddressLog() + "Error en la creacion del archivo | ARC: " + folder);
            Logger.getLogger(FileUploadBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @return
     * @throws IOException
     */
    public String getDynamicFileName() throws IOException {

        try {
            String result;
            String md5;
            long unixTimestamp;

            DecimalFormat fmt = new DecimalFormat("#####");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG", "SUN");
            long secureInitializer = secureRandom.nextLong();
            Random random = new Random(secureInitializer + Runtime.getRuntime().freeMemory());

            md5 = org.apache.commons.codec.digest.DigestUtils.md5Hex(uploadedFile.getInputstream());
            unixTimestamp = Instant.now().getEpochSecond();

            //md5 Hash and TimeStamp for file name
            int code = (100000 + random.nextInt(899999));

            result = md5 + "_" + unixTimestamp + "_" + code;
            return result;

        } catch (NoSuchAlgorithmException | NoSuchProviderException ex) {
            Logger.getLogger(FileUploadBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    /**
     *
     * @param in
     */
    public void copyFile(InputStream in) {

        //Write the inputStream to a FileOutputStream
        try (OutputStream out = new FileOutputStream(new File(folder + fileName))) {

            System.out.println(folder + fileName);
            int read;
            byte[] bytes = new byte[8000];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(FileUploadBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @return
     */
    public String getWebFolder() {
        return webFolder;
    }

    /**
     *
     * @param webFolder
     */
    public void setWebFolder(String webFolder) {
        this.webFolder = webFolder;
    }

    /**
     *
     * @return
     */
    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    /**
     *
     * @param uploadedFile
     */
    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    /**
     *
     * @return
     */
    public String getFileName() {
        return fileName;
    }

    /**
     *
     * @param fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     *
     * @return
     */
    public String getFileNameSrc() {
        return fileNameSrc;
    }

    /**
     *
     * @param fileNameSrc
     */
    public void setFileNameSrc(String fileNameSrc) {
        this.fileNameSrc = fileNameSrc;
    }

    /**
     *
     * @return
     */
    public String getFolder() {
        return folder;
    }

    /**
     *
     * @param folder
     */
    public void setFolder(String folder) {
        this.folder = folder;
    }

    /**
     *
     * @return
     */
    public String getFileType() {
        return fileType;
    }

    /**
     *
     * @param fileType
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     *
     * @return
     */
    public String getExtensionSrc() {
        return extensionSrc;
    }

    /**
     *
     * @param extensionSrc
     */
    public void setExtensionSrc(String extensionSrc) {
        this.extensionSrc = extensionSrc;
    }

    /**
     *
     * @return
     */
    public String getPathFileSystemFull() {
        return pathFileSystemFull;
    }

    /**
     *
     * @param pathFileSystemFull
     */
    public void setPathFileSystemFull(String pathFileSystemFull) {
        this.pathFileSystemFull = pathFileSystemFull;
    }

    /**
     *
     * @return
     */
    public String getExtension() {
        return extension;
    }

    /**
     *
     * @param extension
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }

}
