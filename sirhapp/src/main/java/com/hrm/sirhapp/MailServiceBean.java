package com.hrm.sirhapp;

import com.google.common.base.Charsets;
import com.itextpdf.text.DocumentException;
import com.hrm.sirhapp.util.FacesUtil;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Manage de Mail Service
 * 
 * 
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Named
@ViewScoped
public class MailServiceBean implements Serializable {

    private static final org.apache.logging.log4j.Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(MailServiceBean.class);

    private static final long serialVersionUID = 1L;

    private String toAddress;
    private String subject;
    private String text;
    private String name;

    private String infoMessageModule;
    private String infoMessage;

    @PostConstruct
    private void init() {

        this.infoMessageModule = "Modulo Envio de Correo";
        this.toAddress = "";
        this.subject = null;
        this.text = null;
        this.name = null;

    }

    /**
     *
     */
    public void sendMailO() {
        final String username = "charlyborwn@gmail.com";
        final String password = "Infinito4321";

        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("charlyborwn@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(getAdminEmail()));
            message.setSubject("Testing Subject");
            message.setText("Dear Mail Crawler,"
                    + "\n\n No spam to my email, please!");

            Transport.send(message);

            infoMessage = "El correo fue enviado correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, "Información", "El correo fue enviado correctamente");
            LOGGER.info(FacesUtil.getUserIpAddressLog() + " | ACT: Se envio un correo electronico de prueba");

        } catch (MessagingException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     *
     * @return
     */
    public boolean sendEmail() {

        Session mailSession = getMailSession("java:comp/env/SirhApp-mail");

        final Message message = new MimeMessage(mailSession);
        try {
            message.setRecipients(Message.RecipientType.TO, new Address[]{
                new InternetAddress(getAdminEmail())
            });
            message.addRecipient(Message.RecipientType.CC, new InternetAddress(toAddress));

            message.setSubject(subject);
            message.setSentDate(new Date());

            // This mail has 2 part, the BODY and the embedded image
            MimeMultipart multipart = new MimeMultipart("mixed");

            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlTextUsuario = "<H1>SirhApp -  Sistema de Información de Recursos Humanos</H1>"
                    + "<img style=\"width: 200px\" src=\"" + FacesUtil.getUrl() + "/sirhapp/files/Logo4b.png\">"
                    + "<h4>Correo de: " + name + "</h4>"
                    + "<h4>Mensaje: " + text + "</h4>";

            String newStringUsuario = new String(htmlTextUsuario.getBytes(Charsets.ISO_8859_1), Charsets.ISO_8859_1);

            messageBodyPart.setContent(newStringUsuario, "text/html; charset=ISO-8859-1");

            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);

            Transport.send(message);

            infoMessage = "El correo fue enviado correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, "Información", "El correo fue enviado correctamente");
            LOGGER.info(FacesUtil.getUserIpAddressLog() +" | ACT: Se envio un correo electronico al admin con mensaje desde el modulo Contacto");

        } catch (MessagingException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
            return false;
        }

        return true;
    }

    /**
     *
     * @param email
     * @param usuario
     * @param claveDinamica
     * @return
     */
    public boolean sendRecuperaClaveEmail(String email, String usuario, String claveDinamica) {

        Session mailSession = getMailSession("java:comp/env/SirhApp-mail");

        final Message message = new MimeMessage(mailSession);
        try {
            message.setRecipients(Message.RecipientType.TO, new Address[]{
                new InternetAddress(email)
            });
            message.setSubject("Recuperación de Password");
            message.setSentDate(new Date());
            message.setText(" " + claveDinamica);
            // This mail has 2 part, the BODY and the embedded image
            MimeMultipart multipart = new MimeMultipart("mixed");

            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlTextUsuario = "<H1>SirhApp -  Sistema de Información de Recursos Humanos</H1>"
                    + "<img style=\"width: 200px\" src=\"" + FacesUtil.getUrl() + "/sirhapp/files/Logo4b.png\">"
                    + "<h4>Usuario: " + usuario + "</h4>"
                    + "<h4>Este es el nuevo password para acceder: " + claveDinamica + "</h4>"
                    + "<h4>Bienvenido</h4>";

            String newStringUsuario = new String(htmlTextUsuario.getBytes(Charsets.ISO_8859_1), Charsets.ISO_8859_1);

            messageBodyPart.setContent(newStringUsuario, "text/html; charset=ISO-8859-1");

            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);
            Transport.send(message);

            infoMessage = "El correo fue enviado correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, "Información", "El correo fue enviado correctamente");
            LOGGER.info(FacesUtil.getUserIpAddressLog() +" | ACT: Se envio al correo electronico del usuario, el password del usuario se actualizó: " + usuario);

        } catch (MessagingException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
            return false;
        }

        return true;
    }

    /**
     *
     * @param email
     * @param usuario
     * @param clave
     * @return
     */
    public boolean sendNuevoUsuarioEmail(String email, String usuario, String clave) {

        Session mailSession = getMailSession("java:comp/env/SirhApp-mail");

        final Message message = new MimeMessage(mailSession);
        try {
            message.setRecipients(Message.RecipientType.TO, new Address[]{
                new InternetAddress(email)
            });
            message.setSubject("Bienvenido a SIRH");
            message.setSentDate(new Date());

            // This mail has 2 part, the BODY and the embedded image
            MimeMultipart multipart = new MimeMultipart("mixed");

            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlTextUsuario = "<H1>SirhApp -  Sistema de Información de Recursos Humanos</H1>"
                    + "<img style=\"width: 200px\" src=\"" + FacesUtil.getUrl() + "/sirhapp/files/Logo4b.png\">"
                    + "<h4>Usuario: " + usuario + "</h4>"
                    + "<h4>Password: " + clave + "</h4>"
                    + "<h4>Bienvenido</h4>";

            String newStringUsuario = new String(htmlTextUsuario.getBytes(Charsets.ISO_8859_1), Charsets.ISO_8859_1);

            messageBodyPart.setContent(newStringUsuario, "text/html; charset=ISO-8859-1");

            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);
            Transport.send(message);

            infoMessage = "El correo fue enviado correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, "Información", "El correo fue enviado correctamente");
            LOGGER.info(FacesUtil.getUserIpAddressLog() +" | ACT: Favor de realizar la activacion del usuario: " + usuario);

        } catch (MessagingException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
            return false;
        }

        return true;
    }

    /**
     *
     * @param usuario
     * @return
     */
    public boolean sendNvoUsuarioInfoEmail(String usuario) {

        Session mailSession = getMailSession("java:comp/env/SirhApp-mail");

        final Message message = new MimeMessage(mailSession);
        try {
            message.setRecipients(Message.RecipientType.TO, new Address[]{
                new InternetAddress(getAdminEmail())
            });
            message.setSubject("Se solicita la activacion de un nuevo usuario registrado");
            message.setSentDate(new Date());

            // This mail has 2 part, the BODY and the embedded image
            MimeMultipart multipart = new MimeMultipart("mixed");

            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText = "<H1>SirhApp -  Sistema de Información de Recursos Humanos</H1>"
                    + "<img style=\"width: 200px\" src=\"" + FacesUtil.getUrl() + "/sirhapp/files/Logo4b.png\">"
                    + "<h4>Favor de realizar la activacion del usuario: " + usuario + "</h4>";

            String newString = new String(htmlText.getBytes(Charsets.ISO_8859_1), Charsets.ISO_8859_1);

            messageBodyPart.setContent(newString, "text/html; charset=ISO-8859-1");

            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);
            Transport.send(message);

            infoMessage = "El correo fue enviado correctamente";
            LOGGER.info(FacesUtil.getUserIpAddressLog() +" | ACT: Se envió el correo electronico al admin del sistema para realizar la activacion del usuario: " + usuario);

        } catch (MessagingException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
            return false;
        }

        return true;
    }

    /**
     *
     * @param value
     * @return
     * @throws AddressException
     * @throws MessagingException
     * @throws DocumentException
     */
    public boolean sendEmailWithAttachments(String value)
            throws AddressException, MessagingException, DocumentException {

        Session mailSession = getMailSession("java:comp/env/SirhApp-mail");
        String claveDianmica = FacesUtil.getClaveDianmica();

        final Message message = new MimeMessage(mailSession);
        try {
            message.setRecipients(Message.RecipientType.TO, new Address[]{
                new InternetAddress(toAddress)
            });
            message.setSubject(subject);
            message.setSentDate(new Date());

            // This mail has 2 part, the BODY and the embedded image
            MimeMultipart multipart = new MimeMultipart("mixed");

            // first part (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText = "<H1>SirhApp - Reporte del Sistema de Información de Recursos Humanos</H1>"
                    + "<img style=\"width: 200px\" src=\"" + FacesUtil.getUrl() + "/sirhapp/files/Logo4b.png\">"
                    + "<h4>Sistema de Información de Recursos Humanos</h4>"
                    + "<br/><h4>Password del archivo:</h4>" + claveDianmica
                    + "<br/><h4>Mensaje:</h4>" + text
                    + "<br/><br/>";

            String newString = new String(htmlText.getBytes(Charsets.ISO_8859_1), Charsets.ISO_8859_1);
            messageBodyPart.setContent(newString, "text/html; charset=ISO-8859-1");

            multipart.addBodyPart(messageBodyPart);

            InputStream in = new ByteArrayInputStream((byte[]) new MediaBean().createPdf((String) value));
            InputStream encrypt = new ByteArrayInputStream((byte[]) new MediaBean().preventCopyingPdf(in, null, claveDianmica));

            if (encrypt instanceof ByteArrayInputStream) {
                // create the second message part with the attachment from a OutputStrean
                MimeBodyPart attachment = new MimeBodyPart();
                ByteArrayDataSource ds = new ByteArrayDataSource(encrypt, "application/pdf");
                attachment.setDataHandler(new DataHandler(ds));
                attachment.setFileName("Reporte.pdf");
                attachment.setDisposition(MimeBodyPart.ATTACHMENT);
                multipart.addBodyPart(attachment);
            }

            message.setContent(multipart);

            in.close();
            encrypt.close();

            message.saveChanges();

            // Send message
            Transport.send(message);

            infoMessage = "El correo fue enviado correctamente";
            FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, "Información", "El correo fue enviado correctamente");
            LOGGER.info(FacesUtil.getUserIpAddressLog() +" | ACT: Se envió el correo electronico con información del sistema a la siguiente direccion: " + toAddress);

        } catch (IOException | IllegalArgumentException | SecurityException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return false;

    }

    private String getAdminEmail() {
        ResourceBundle bundle = ResourceBundle.getBundle("Bundle");
        String adminEmail = bundle.getString("AdminEmail");
        return adminEmail;
    }

    private static Session getMailSession(String jndiName) {
        try {
            InitialContext ic = new InitialContext();
            //System.out.println(jndiName + "...");
            Session session = (Session) ic.lookup(jndiName);
            System.out.println("Success! Getting " + jndiName);
            return session;
        } catch (NamingException e) {
            System.out.println("Failed: Getting " + jndiName + "|" + e);
            return null;
        }
    }

    /**
     *
     * @return
     */
    public String getToAddress() {
        return toAddress;
    }

    /**
     *
     * @param toAddress
     */
    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    /**
     *
     * @return
     */
    public String getSubject() {
        return subject;
    }

    /**
     *
     * @param subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     *
     * @return
     */
    public String getText() {
        return text;
    }

    /**
     *
     * @param text
     */
    public void setText(String text) {
        System.out.println("hola" + text);
        this.text = text;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
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

}
