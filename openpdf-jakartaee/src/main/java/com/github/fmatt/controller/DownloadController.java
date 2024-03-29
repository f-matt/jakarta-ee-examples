package com.github.fmatt.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Named
@RequestScoped
public class DownloadController {

	public StreamedContent download() {
        try {
			Document document = new Document(PageSize.A4, 10f, 10f, 10f, 10f);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PdfWriter.getInstance(document, baos)

			String path = "/reports/hello.jrxml";
			InputStream jasperTemplate = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream(path);
     
			// Compila o template.
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperTemplate);

			// Configura os parâmetros.
			/*
			Map parametros = new HashMap();
			parametros.put("clienteDe", this.codigoClienteDe);
			parametros.put("clienteAte", this.codigoClienteAte);

			// Cria a conexão com o banco de dados.
			Connection conexao = null;
			try {
			Class.forName("com.mysql.jdbc.Driver");
			conexao = DriverManager.getConnection("jdbc:mysql://localhost/arquivojm", "root", "root");
			} catch (Exception e) {
			// TODO: handle exception
			}
			*/

			// Passagem dos parâmetros e preenchimento do relatório - informamos um
			// datasource vazio, pois a query do relatório irá trazer os dados.
			//JasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conexao);
			Map<String, Object> parametros = new HashMap<>();
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, new JREmptyDataSource());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
			// JasperFillManager.fillReportToStream(jasperReport, baos, parametros);
			JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
            InputStream bais = new ByteArrayInputStream(baos.toByteArray());
            StreamedContent file = DefaultStreamedContent
                .builder()
                .stream(() -> bais)
                .contentType("application/pdf")
                .name("report.pdf")
                .build();

            return file;
        } catch (Exception e) {
			e.printStackTrace();
            return null;
        }
    }

}
