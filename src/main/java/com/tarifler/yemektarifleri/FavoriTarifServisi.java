package com.tarifler.yemektarifleri;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FavoriTarifServisi {

    public List<Tarif> getTumFavoriler() {
        return FavoriDosyaIsleyici.favorileriOku();
    }

    public void favoriEkle(Tarif tarif) {
        FavoriDosyaIsleyici.favoriEkle(tarif);
    }

    public void favoriSil(String id) {
        FavoriDosyaIsleyici.favoriSil(id);
    }
}