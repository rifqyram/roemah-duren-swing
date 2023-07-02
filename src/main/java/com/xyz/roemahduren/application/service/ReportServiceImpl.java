package com.xyz.roemahduren.application.service;

import com.xyz.roemahduren.domain.service.ReportService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;

public class ReportServiceImpl implements ReportService {

    private final Connection connection;

    public ReportServiceImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void generateInvoice(String orderId, String user) {
        try (InputStream inputStream = getClass().getResourceAsStream("/JRXML/invoice.jrxml")) {
            JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            HashMap<String, Object> params = new HashMap<>();
            Image image = new ImageIcon(getClass().getResource("/images/Logo.png")).getImage();
            params.put("ORDER_ID", orderId);
            params.put("USER_ID", user);
            params.put("LOGO", image);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, connection);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setVisible(true);
        } catch (IOException | JRException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void generateCustomerReport(String user) {
        try (InputStream inputStream = getClass().getResourceAsStream("/JRXML/customer.jrxml")) {
            loadJRXML(user, inputStream);
        } catch (IOException | JRException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void generateTransactionReport(String user) {
        try (InputStream inputStream = getClass().getResourceAsStream("/JRXML/order.jrxml")) {
            loadJRXML(user, inputStream);
        } catch (IOException | JRException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void generateSupplierReport(String user) {
        try (InputStream inputStream = getClass().getResourceAsStream("/JRXML/supplier.jrxml")) {
            loadJRXML(user, inputStream);
        } catch (IOException | JRException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadJRXML(String user, InputStream inputStream) throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        HashMap<String, Object> params = new HashMap<>();
        Image image = new ImageIcon(getClass().getResource("/images/Logo.png")).getImage();
        params.put("USER_ID", user);
        params.put("LOGO", image);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, connection);
        JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
        jasperViewer.setVisible(true);
    }
}
