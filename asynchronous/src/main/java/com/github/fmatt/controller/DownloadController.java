package com.github.fmatt.controller;

import java.io.*;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.github.fmatt.service.DatabaseMockService;
import com.github.fmatt.service.ReportService;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import org.primefaces.PrimeFaces;
import org.primefaces.component.log.Log;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.inject.Named;

@Named
@ViewScoped
public class DownloadController implements Serializable {

    @Inject
    private ReportService reportService;

    @Inject
    private DatabaseMockService databaseMockService;

    private String message;

    private Future<String> resultMessage;

    private String status;

    public void create() {
        try {
            PrimeFaces.current().executeScript("PF('poll').start()");
            Logger.getAnonymousLogger().severe("Getting report data...");
            resultMessage = reportService.getMessage();
            Logger.getAnonymousLogger().severe("Done..");
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

	public StreamedContent download() {
        try {
            Logger.getAnonymousLogger().severe("Generating report...");
			Document document = new Document(PageSize.A4, 10f, 10f, 10f, 10f);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PdfWriter.getInstance(document, baos);

            document.open();
            Paragraph p = new Paragraph(new Phrase(message));
            document.add(p);
            document.close();

            InputStream bais = new ByteArrayInputStream(baos.toByteArray());

            return DefaultStreamedContent
                .builder()
                .stream(() -> bais)
                .contentType("application/pdf")
                .name("report.pdf")
                .build();
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage(), e);
            return null;
        }
    }

    public void updateStatus() {
        Logger.getAnonymousLogger().severe("Update status");
        status = databaseMockService.getStatus();
        PrimeFaces.current().executeScript("PF('poll').start()");

        if (Objects.nonNull(resultMessage) && resultMessage.isDone()) {
            try {
                message = resultMessage.get();
                PrimeFaces.current().executeScript("PF('poll').stop()");
            } catch (InterruptedException | ExecutionException e) {
                Logger.getAnonymousLogger().severe("Exception reached while getting message value.");
                throw new RuntimeException(e);
            }
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
