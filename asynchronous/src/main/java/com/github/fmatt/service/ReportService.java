package com.github.fmatt.service;

import jakarta.ejb.AsyncResult;
import jakarta.ejb.Asynchronous;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.concurrent.Future;

@Stateless
public class ReportService {

    @Inject
    private DatabaseMockService databaseMockService;

    @Asynchronous
    public Future<String> getMessage() throws InterruptedException {
        databaseMockService.setStatus("Starting first step... ");
        Thread.sleep(3000);
        databaseMockService.setStatus(databaseMockService.getStatus() + "Done.\nStarting second step... ");
        Thread.sleep(2000);
        databaseMockService.setStatus(databaseMockService.getStatus() + "Done.\nStarting final step... ");
        Thread.sleep(5000);
        databaseMockService.setStatus(databaseMockService.getStatus() + "Done.");
        return new AsyncResult<>("Generated with OpenPDF");
    }

}
