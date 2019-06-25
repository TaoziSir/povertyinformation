package org.poverty.povertyinformation.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class exprotThread implements Runnable {
    private String filename;
    private List<Map<String, String>> list;
    private CountDownLatch countDownLatch;

    public exprotThread(String filename, List<Map<String, String>> list, CountDownLatch countDownLatch) {
        this.filename = filename;
        this.list = list;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            CSVPrinter printer = new CSVPrinter(new FileWriter(filename), CSVFormat.EXCEL.withHeader("NAME", "CODE"));
            for (Map<String, String> map : list) {
                printer.printRecord(map.values());
            }
            printer.close();
            countDownLatch.countDown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}