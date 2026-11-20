package com.tarifler.yemektarifleri;

import org.junit.jupiter.api.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FavoriTarifServisiTest {

    private FavoriTarifServisi servis;
    private static final String TEST_DOSYA = "test-favoriler.json";

    @BeforeEach
    public void setup() {
        // Teste özel dosya kullan
        System.setProperty("favori.dosya", TEST_DOSYA);
        servis = new FavoriTarifServisi();

        // Dosyayı boş hale getir
        FavoriDosyaIsleyici.favorileriYaz(new ArrayList<>());
    }

    @AfterEach
    public void temizle() {
        File dosya = new File(TEST_DOSYA);
        if (dosya.exists()) {
            dosya.delete();
        }
    }

    @Test
    public void testFavoriEkle() {
        Tarif t = new Tarif();
        t.setId("1");
        t.setAd("Test Tarif");

        servis.favoriEkle(t);
        List<Tarif> favoriler = servis.getTumFavoriler();
        assertEquals(1, favoriler.size());
        assertEquals("Test Tarif", favoriler.get(0).getAd());
    }

    @Test
    public void testFavoriSil() {
        Tarif t = new Tarif();
        t.setId("2");
        t.setAd("Silinecek Tarif");

        servis.favoriEkle(t);
        servis.favoriSil("2");
        List<Tarif> favoriler = servis.getTumFavoriler();
        assertEquals(0, favoriler.size());
    }

    @Test
    public void testTumFavorilerBosIse() {
        List<Tarif> favoriler = servis.getTumFavoriler();
        assertTrue(favoriler.isEmpty());
    }
}
