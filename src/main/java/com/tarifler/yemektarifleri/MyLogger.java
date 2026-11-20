package com.tarifler.yemektarifleri;

import java.io.IOException;
import java.util.logging.*;
import java.io.File;


public class MyLogger {
    private static final Logger logger = Logger.getLogger(MyLogger.class.getName());

    static {
        try {
            File logDir = new File("logs");
            if (!logDir.exists()) {
                logDir.mkdirs();
            }
            FileHandler fileHandler = new FileHandler("logs/log_kayitlari.txt", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);
        } catch (IOException e) {
            System.err.println("Log dosyası oluşturulamadı: " + e.getMessage());
        }
    }

    public static Logger getLogger() {
        return logger;
    }
}
