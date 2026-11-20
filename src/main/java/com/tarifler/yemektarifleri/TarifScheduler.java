package com.tarifler.yemektarifleri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class TarifScheduler {

    @Autowired
    private FavoriTarifServisi favoriServisi;

    @Autowired
    private MailService mailService;

    // Her pazartesi saat 10:00'da çalışır
    @Scheduled(cron = "0 0 10 * * MON")
    public void haftalikTarifMailGonder() {
        List<Tarif> favoriler = favoriServisi.getTumFavoriler();

        if (favoriler.isEmpty()) {
            System.out.println("Favori listesi boş, mail gönderilmedi.");
            return;
        }

        // Rastgele bir tarif seç
        Tarif secilen = favoriler.get(new Random().nextInt(favoriler.size()));
        String pdfYolu = "src/main/resources/reports/haftalik_tarif_onerisi.pdf";

        // PDF oluştur
        PdfHelper.tekTarifPdfOlustur(secilen, pdfYolu);

        // Mail gönder
        mailService.pdfMailGonder(
                "ornekmail@gmail.com",
                "Haftalık Favori Tarif Önerisi",
                "Bu haftanın önerilen favori tarifi ekteki PDF'te yer almaktadır. Afiyet olsun!",
                pdfYolu
        );
    }
}
