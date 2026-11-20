package com.tarifler.yemektarifleri.gui;

import com.tarifler.yemektarifleri.TarifApiServisi;
import com.tarifler.yemektarifleri.FavoriTarifServisi;

public class MainGui {
    public static void main(String[] args) {
        TarifApiServisi tarifServisi = new TarifApiServisi();
        FavoriTarifServisi favoriServisi = new FavoriTarifServisi();

        TarifGui gui = new TarifGui(tarifServisi, favoriServisi);
        gui.setVisible(true);
    }
}