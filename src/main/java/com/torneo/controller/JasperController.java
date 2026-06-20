package com.torneo.controller;

import java.io.InputStream;
import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Controller
public class JasperController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/reportes/clasificacion")
    public void reporteClasificacion(HttpServletResponse response) throws Exception {

        System.out.println(">>> Iniciando generación de reporte");

        InputStream reportStream = new ClassPathResource(
                "reports/ReporteClasificacion.jrxml").getInputStream();

        System.out.println(">>> JRXML cargado correctamente");

        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

        System.out.println(">>> Reporte compilado correctamente");

        try (Connection conn = dataSource.getConnection()) {

            System.out.println(">>> Conexión obtenida: " + conn.getCatalog());

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport, null, conn);

            System.out.println(">>> Páginas generadas: " + jasperPrint.getPages().size());

            response.setContentType("application/pdf");
            response.setHeader(
                    "Content-Disposition",
                    "inline; filename=clasificacion.pdf");

            JasperExportManager.exportReportToPdfStream(
                    jasperPrint, response.getOutputStream());

            System.out.println(">>> PDF exportado correctamente");
        }
    }
    
    @GetMapping("/reportes/torneos-inscripciones")
    public void reporteTorneosInscripciones(HttpServletResponse response) throws Exception {

        InputStream reportStream = new ClassPathResource(
                "reports/ReporteTorneosPorInscripciones.jrxml").getInputStream();

        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

        try (Connection conn = dataSource.getConnection()) {

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport, null, conn);

            response.setContentType("application/pdf");
            response.setHeader(
                    "Content-Disposition",
                    "inline; filename=torneos_inscripciones.pdf");

            JasperExportManager.exportReportToPdfStream(
                    jasperPrint, response.getOutputStream());
        }
    }
    
}