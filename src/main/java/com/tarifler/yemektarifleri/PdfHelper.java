package com.tarifler.yemektarifleri;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class PdfHelper {

    public static Tarif rastgeleFavoriSec(List<Tarif> liste) {
        return liste.isEmpty() ? null : liste.get(new Random().nextInt(liste.size()));
    }

    public static void tekTarifPdfOlustur(Tarif tarif, String dosyaAdi) {
        try {
            Path pdfPath = Paths.get(System.getProperty("user.dir"), "reports", dosyaAdi);
            File dosya = pdfPath.toFile();
            dosya.getParentFile().mkdirs(); // klasör yoksa oluştur

            Document belge = new Document();
            PdfWriter.getInstance(belge, new FileOutputStream(dosya));
            belge.open();
            belge.add(new Paragraph("Haftalık Favori Tarif Önerisi\n\n"));
            belge.add(new Paragraph("Ad: " + tarif.getAd()));
            belge.add(new Paragraph("Kategori: " + tarif.getKategori()));
            belge.add(new Paragraph("Talimat: " + tarif.getTalimat()));
            belge.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void tumFavorileriPdfOlustur(List<Tarif> liste, String dosyaAdi) {
        try {
            Path pdfPath = Paths.get(System.getProperty("user.dir"), "reports", dosyaAdi);
            File dosya = pdfPath.toFile();
            dosya.getParentFile().mkdirs(); // klasör yoksa oluştur

            Document belge = new Document();
            PdfWriter.getInstance(belge, new FileOutputStream(dosya));
            belge.open();
            for (Tarif t : liste) {
                belge.add(new Paragraph("Ad: " + t.getAd()));
                belge.add(new Paragraph("Kategori: " + t.getKategori()));
                belge.add(new Paragraph("Talimat: " + t.getTalimat()));
                belge.add(new Paragraph("------------------------------"));
            }
            belge.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
