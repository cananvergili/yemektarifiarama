package com.tarifler.yemektarifleri;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class MailServiceTest {

    @Test
    public void testPdfMailGonder() {
        MailService mailService = mock(MailService.class);

        // Örnek veriler
        String alici = "test@example.com";
        String konu = "Test Konu";
        String mesaj = "Test mesajı";
        String pdfYolu = "test.pdf";

        // Metodu çağır
        mailService.pdfMailGonder(alici, konu, mesaj, pdfYolu);

        // Doğru çağrılıp çağrılmadığını kontrol et
        verify(mailService).pdfMailGonder(alici, konu, mesaj, pdfYolu);
    }
}