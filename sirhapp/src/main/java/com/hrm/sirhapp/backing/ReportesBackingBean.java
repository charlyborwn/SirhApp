package com.hrm.sirhapp.backing;

import com.hrm.sirhapp.service.Apriv25ManagerLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.component.ValueHolder;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Named
@ViewScoped
public class ReportesBackingBean extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    private PieChartModel pieModel1;
    private PieChartModel pieModel2;
    private PieChartModel livePieModel;

    @EJB
    private Apriv25ManagerLocal apriv25Manager;

    private String enterprise;
    private String enterpriseName;
    private String center;
    private String department;

    private Integer year;
    private String month;

    private String period;

    private String report;

    private List<Integer> yearList;

    private String html;

    /**
     *
     */
    @PostConstruct
    public void init() {

        createPieModels();

    }

    private void createPieModels() {
        createPieModel1();
        createPieModel2();
        createLivePieModel();
    }

    /**
     *
     * @return
     */
    public PieChartModel getLivePieModel() {
        int random1 = (int) (Math.random() * 1000);
        int random2 = (int) (Math.random() * 1000);

        livePieModel.getData().put("Candidate 1", random1);
        livePieModel.getData().put("Candidate 2", random2);

        livePieModel.setTitle("Votes");
        livePieModel.setLegendPosition("ne");

        return livePieModel;
    }

    private void createLivePieModel() {
        livePieModel = new PieChartModel();

        livePieModel.set("Candidate 1", 540);
        livePieModel.set("Candidate 2", 325);
    }

    private void createPieModel1() {
        List<Object[]> my_list = new ArrayList<Object[]>();

        my_list = apriv25Manager.retrieveApriv25Repasp(null, null, null, null, null, "20180124", null, "4");

        pieModel1 = new PieChartModel();

        for (int i = 0; i < my_list.size(); i++) {
            Object[] arr = my_list.get(i);

            pieModel1.set(arr[0].toString(), Integer.valueOf(arr[1].toString()));
        }

        pieModel1.setTitle("Ausencias");
        pieModel1.setLegendPosition("w");
        pieModel1.setExtender("skinPie");
    }

    private void createPieModel2() {
        List<Object[]> my_list = new ArrayList<Object[]>();
        my_list = apriv25Manager.retrieveApriv25Repasp(null, null, null, null, null, "20180124", null, "5");

        pieModel2 = new PieChartModel();

          for (int i = 0; i < my_list.size(); i++) {
            Object[] arr = my_list.get(i);

            pieModel2.set(arr[0].toString(), Integer.valueOf(arr[1].toString()));
        }

        pieModel2.setTitle("Tipos de ausencias generadas hoy");
        pieModel2.setLegendPosition("e");
        pieModel2.setFill(false);
        pieModel2.setShowDataLabels(true);
        pieModel2.setDiameter(150);
    }

    /**
     *
     */
    public ReportesBackingBean() {
        this.month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
    }

    /**
     *
     * @param text
     * @param size
     * @return
     */
    public static String fill(String text, int size) {
        StringBuilder builder = new StringBuilder(text);
        while (builder.length() < size) {
            builder.append('0');
        }
        return builder.toString();
    }

    /**
     *
     * @param vce
     */
    public void handleChange(AjaxBehaviorEvent vce) {

        String id = ((UIComponent) vce.getSource()).getId();
        String value = "";

        List<Object[]> my_list = new ArrayList<Object[]>();

        switch (id) {
            case "rep_go":
                value = (String) ((ValueHolder) vce.getSource()).getValue();
                break;
            case "ent_go":
                value = (String) ((ValueHolder) vce.getSource()).getValue();
                break;
            case "yea_go":
                Integer valueInteger = (Integer) ((ValueHolder) vce.getSource()).getValue();
                value = valueInteger.toString();
                break;
            case "mon_go":
                value = (String) ((ValueHolder) vce.getSource()).getValue();
                break;
            case "per_go":
                value = (String) ((ValueHolder) vce.getSource()).getValue();
                break;
            default:
                break;
        }
        my_list = apriv25Manager.retrieveApriv25Repasp(center, enterprise, department, null, null, String.valueOf(year), null, null);

        StringBuilder buf = new StringBuilder();
        buf.append("<html>"
                + "<body>"
                + "<h1>" + report + "</h1>"
                + "<table border='1'>"
                + "<tr>"
                + "<th>Empresa:</th>"
                + "<th>Año:</th>"
                + "<th>Mes:</th>"
                + "<th>Tipo Ausencia</th>"
                + "<th>Total:</th>"
                + "</tr>");

        for (int i = 0; i < my_list.size(); i++) {
            Object[] arr = my_list.get(i);
            buf.append("<tr><td>")
                    .append(arr[3])
                    .append("</td><td>")
                    .append(arr[4])
                    .append("</td><td>")
                    .append(arr[5])
                    .append("</td><td>")
                    .append(arr[6])
                    .append("</td><td>")
                    .append(arr[7])
                    .append("</td></tr>");

        }

        buf.append("</table>"
                + "</body>"
                + "</html>");
        this.html = buf.toString();
        System.out.println(html);

    }

    /**
     *
     * @return
     */
    public List<Integer> getYearList() {
        year = java.time.Year.now().getValue();
        yearList = new ArrayList<Integer>();
        yearList.add(year - 2);
        yearList.add(year - 1);
        yearList.add(year);

        return yearList;
    }

    /**
     *
     * @param yearList
     */
    public void setYearList(List<Integer> yearList) {
        this.yearList = yearList;
    }

    /**
     *
     * @return
     */
    public Integer getYear() {
        return year;
    }

    /**
     *
     * @param year
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     *
     * @return
     */
    public String getEnterprise() {
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
    public String getEnterpriseName() {
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
    public String getMonth() {
        return month;
    }

    /**
     *
     * @param month
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     *
     * @return
     */
    public String getCenter() {
        return center;
    }

    /**
     *
     * @param center
     */
    public void setCenter(String center) {
        this.center = center;
    }

    /**
     *
     * @return
     */
    public String getDepartment() {
        return department;
    }

    /**
     *
     * @param department
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     *
     * @return
     */
    public String getReport() {
        return report;
    }

    /**
     *
     * @param report
     */
    public void setReport(String report) {
        this.report = report;
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
    public PieChartModel getPieModel1() {
        return pieModel1;
    }

    /**
     *
     * @param pieModel1
     */
    public void setPieModel1(PieChartModel pieModel1) {
        this.pieModel1 = pieModel1;
    }

    /**
     *
     * @return
     */
    public PieChartModel getPieModel2() {
        return pieModel2;
    }

    /**
     *
     * @param pieModel2
     */
    public void setPieModel2(PieChartModel pieModel2) {
        this.pieModel2 = pieModel2;
    }

}
