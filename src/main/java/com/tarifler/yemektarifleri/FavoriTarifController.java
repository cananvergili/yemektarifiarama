package com.tarifler.yemektarifleri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favoriler")
public class FavoriTarifController {

    @Autowired
    private FavoriTarifServisi favoriServisi;

    @Autowired
    private TarifApiServisi tarifApiServisi;

    @Autowired
    private MailService mailService;

    // TÜM FAVORİLERİ GETİR
    @GetMapping("/liste")
    public List<Tarif> favorileriGetir() {
        return favoriServisi.getTumFavoriler();
    }

    // YENİ TARİF EKLE
    @PostMapping("/ekle")
    public String favoriEkle(@RequestBody Tarif tarif) {
        favoriServisi.favoriEkle(tarif);
        return "Favori eklendi!";
    }

    // ID'YE GÖRE SİL
    @DeleteMapping("/sil/{id}")
    public String favoriSil(@PathVariable String id) {
        favoriServisi.favoriSil(id);
        return "Favori silindi!";
    }

    // TEK TARİF PDF OLUŞTUR
    @GetMapping("/pdf")
    public String tekFavoriPdfOlustur() {
        Tarif secilen = PdfHelper.rastgeleFavoriSec(favoriServisi.getTumFavoriler());
        String dosyaYolu = "src/main/resources/reports/haftalik_tarif_onerisi.pdf";
        PdfHelper.tekTarifPdfOlustur(secilen, dosyaYolu);
        return "Tek favori PDF oluşturuldu.";
    }

    // POSTMAN'DEN MANUEL PDF GÖNDER
    @PostMapping("/mailGonder")
    public String mailGonder(@RequestParam String alici) {
        List<Tarif> favoriler = favoriServisi.getTumFavoriler();
        if (favoriler == null || favoriler.isEmpty()) {
            return "Gönderilecek favori tarif bulunamadı.";
        }
        Tarif secilen = PdfHelper.rastgeleFavoriSec(favoriler);
        String pdfYolu = "src/main/resources/reports/haftalik_tarif_onerisi.pdf";

        PdfHelper.tekTarifPdfOlustur(secilen, pdfYolu);

        mailService.pdfMailGonder(
                alici,
                "Haftalık Favori Tarif Önerisi",
                "Bu haftanın önerilen favori tarifi ekteki PDF'te yer almaktadır. Afiyet olsun!",
                pdfYolu
        );

        return "Mail gönderildi.";
    }

    // TARİF ARA (TheMealDB API üzerinden)
    @GetMapping("/tarif/ara")
    public List<Tarif> tarifAra(@RequestParam String isim) {
        return tarifApiServisi.tarifAra(isim);
    }
}
