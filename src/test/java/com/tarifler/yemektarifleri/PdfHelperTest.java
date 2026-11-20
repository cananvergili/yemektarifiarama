package com.tarifler.yemektarifleri;

import org.junit.jupiter.api.*;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class PdfHelperTest {

    private final String testDosyaAdi = "test-tek-tarif.pdf";
    private final File pdfDosya = new File("reports/" + testDosyaAdi);

    @BeforeEach
    public void setup() {
        // Önceki testten kalmışsa sil
        if (pdfDosya.exists()) {
            pdfDosya.delete();
        }
    }

    @Test
    public void testTekTarifPdfOlustur() {
        Tarif t = new Tarif();
        t.setAd("Test Tarif");
        t.setKategori("Test Kategori");
        t.setTalimat("Test talimat...");

        PdfHelper.tekTarifPdfOlustur(t, testDosyaAdi);

        // PDF dosyası gerçekten oluşmuş mu?
        assertTrue(pdfDosya.exists(), "PDF dosyası oluşturulamadı.");
        assertTrue(pdfDosya.length() > 0, "PDF dosyası boş.");
    }

    @AfterEach
    public void temizle() {
        if (pdfDosya.exists()) {
            pdfDosya.delete();
        }
    }
}
