package com.tarifler.yemektarifleri;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FavoriDosyaIsleyici {

    // Artık src/main/resources değil → çalıştırma dizininde olacak
    private static final String DOSYA_YOLU = Paths.get(System.getProperty("user.dir"), "favoriler.json").toString();
    private static final ObjectMapper mapper = new ObjectMapper();

    public static List<Tarif> favorileriOku() {
        File dosya = new File(DOSYA_YOLU);
        if (!dosya.exists()) return new ArrayList<>();
        try {
            return mapper.readValue(dosya, new TypeReference<List<Tarif>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void favorileriYaz(List<Tarif> favoriListesi) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(DOSYA_YOLU), favoriListesi);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void favoriEkle(Tarif yeniTarif) {
        List<Tarif> mevcutlar = favorileriOku();
        mevcutlar.add(yeniTarif);
        favorileriYaz(mevcutlar);
    }

    public static void favoriSil(String id) {
        List<Tarif> mevcutlar = favorileriOku();
        mevcutlar.removeIf(t -> t.getId().equals(id));
        favorileriYaz(mevcutlar);
    }
}
