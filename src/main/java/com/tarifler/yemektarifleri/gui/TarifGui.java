package com.tarifler.yemektarifleri.gui;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import com.tarifler.yemektarifleri.Tarif;
import com.tarifler.yemektarifleri.TarifApiServisi;
import com.tarifler.yemektarifleri.FavoriTarifServisi;
import java.awt.Desktop;

public class TarifGui extends JFrame {

    private JTextField aramaKutusu;
    private JButton araButonu;

    private DefaultListModel<Tarif> aramaModel;
    private JList<Tarif> aramaSonuclariListesi;

    private DefaultListModel<Tarif> favoriModel;
    private JList<Tarif> favoriListesi;

    private JButton favoriEkleButonu;
    private JButton favoriSilButonu;

    private JTextArea detayAlani;
    private JLabel resimLabel;
    private JButton youtubeButonu;
    private String youtubeUrl;

    private TarifApiServisi tarifApiServisi;
    private FavoriTarifServisi favoriTarifServisi;

    public TarifGui(TarifApiServisi tarifApiServisi, FavoriTarifServisi favoriTarifServisi) {
        this.tarifApiServisi = tarifApiServisi;
        this.favoriTarifServisi = favoriTarifServisi;

        setTitle("Yemek Tarifi Arama");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        aramaKutusu = new JTextField(20);
        araButonu = new JButton("Ara");

        // Arama sonuçları listesi
        aramaModel = new DefaultListModel<>();
        aramaSonuclariListesi = new JList<>(aramaModel);
        aramaSonuclariListesi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Favori tarifler listesi
        favoriModel = new DefaultListModel<>();
        favoriListesi = new JList<>(favoriModel);
        favoriListesi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        favoriEkleButonu = new JButton("Favorilere Ekle");
        favoriEkleButonu.setEnabled(false);

        favoriSilButonu = new JButton("Favoriden Sil");
        favoriSilButonu.setEnabled(false);

        detayAlani = new JTextArea(15, 40);
        detayAlani.setEditable(false);
        JScrollPane scrollDetay = new JScrollPane(detayAlani);

        resimLabel = new JLabel();
        resimLabel.setPreferredSize(new Dimension(200, 150));

        youtubeButonu = new JButton("YouTube'da İzle");
        youtubeButonu.setEnabled(false);

        youtubeButonu.addActionListener(e -> {
            try {
                if (youtubeUrl != null && !youtubeUrl.isEmpty()) {
                    Desktop.getDesktop().browse(new java.net.URI(youtubeUrl));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        JPanel ustPanel = new JPanel();
        ustPanel.add(new JLabel("Tarif Ara:"));
        ustPanel.add(aramaKutusu);
        ustPanel.add(araButonu);

        JPanel aramaPanel = new JPanel(new BorderLayout());
        aramaPanel.add(new JLabel("Arama Sonuçları"), BorderLayout.NORTH);
        aramaPanel.add(new JScrollPane(aramaSonuclariListesi), BorderLayout.CENTER);
        aramaPanel.add(favoriEkleButonu, BorderLayout.SOUTH);

        JPanel favoriPanel = new JPanel(new BorderLayout());
        favoriPanel.add(new JLabel("Favori Tarifler"), BorderLayout.NORTH);
        favoriPanel.add(new JScrollPane(favoriListesi), BorderLayout.CENTER);
        favoriPanel.add(favoriSilButonu, BorderLayout.SOUTH);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, aramaPanel, favoriPanel);
        splitPane.setDividerLocation(400);

        JPanel detayPanel = new JPanel(new BorderLayout());
        detayPanel.add(scrollDetay, BorderLayout.CENTER);

        JPanel altPanel = new JPanel();
        altPanel.add(resimLabel);
        altPanel.add(youtubeButonu);

        detayPanel.add(altPanel, BorderLayout.SOUTH);

        add(ustPanel, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
        add(detayPanel, BorderLayout.SOUTH);

        // Ara butonu işlemi
        araButonu.addActionListener(e -> {
            String arama = aramaKutusu.getText().trim();
            if (!arama.isEmpty()) {
                List<Tarif> tarifler = tarifApiServisi.tarifAra(arama);
                aramaModel.clear();
                for (Tarif t : tarifler) {
                    aramaModel.addElement(t);
                }
            }
        });

        // Arama sonuçları seçim dinleyicisi
        aramaSonuclariListesi.addListSelectionListener(e -> {
            boolean secimVar = aramaSonuclariListesi.getSelectedIndex() != -1;
            favoriEkleButonu.setEnabled(secimVar);

            Tarif secilen = aramaSonuclariListesi.getSelectedValue();
            if (secilen != null) {
                gosterDetay(secilen);
            }
        });

        // Favori listesi seçim dinleyicisi
        favoriListesi.addListSelectionListener(e -> {
            boolean secimVar = favoriListesi.getSelectedIndex() != -1;
            favoriSilButonu.setEnabled(secimVar);

            Tarif secilen = favoriListesi.getSelectedValue();
            if (secilen != null) {
                gosterDetay(secilen);
            }
        });

        // Favori ekle butonu
        favoriEkleButonu.addActionListener(e -> {
            Tarif secilen = aramaSonuclariListesi.getSelectedValue();
            if (secilen != null) {
                favoriTarifServisi.favoriEkle(secilen);
                if (!favoriModel.contains(secilen)) {
                    favoriModel.addElement(secilen);
                }
                JOptionPane.showMessageDialog(this, secilen.getAd() + " favorilere eklendi.");
            }
        });

        // Favori sil butonu
        favoriSilButonu.addActionListener(e -> {
            Tarif secilen = favoriListesi.getSelectedValue();
            if (secilen != null) {
                favoriTarifServisi.favoriSil(secilen.getId());
                favoriModel.removeElement(secilen);
                JOptionPane.showMessageDialog(this, secilen.getAd() + " favorilerden silindi.");
                detayAlani.setText("");
                resimLabel.setIcon(null);
                youtubeButonu.setEnabled(false);
                youtubeUrl = null;
            }
        });

        // Favori listeyi başta doldur
        List<Tarif> mevcutFavoriler = favoriTarifServisi.getTumFavoriler();
        for (Tarif t : mevcutFavoriler) {
            favoriModel.addElement(t);
        }
    }

    private void gosterDetay(Tarif secilen) {
        StringBuilder detaylar = new StringBuilder();
        detaylar.append("Ad: ").append(secilen.getAd()).append("\n");
        detaylar.append("Kategori: ").append(secilen.getKategori()).append("\n");
        detaylar.append("Bölge: ").append(secilen.getBolge()).append("\n\n");
        detaylar.append("Talimat:\n").append(secilen.getTalimat()).append("\n\n");

        detaylar.append("Malzemeler:\n");
        if (secilen.getMalzeme1() != null && !secilen.getMalzeme1().isEmpty())
            detaylar.append("- ").append(secilen.getMalzeme1()).append("\n");
        if (secilen.getMalzeme2() != null && !secilen.getMalzeme2().isEmpty())
            detaylar.append("- ").append(secilen.getMalzeme2()).append("\n");
        if (secilen.getMalzeme3() != null && !secilen.getMalzeme3().isEmpty())
            detaylar.append("- ").append(secilen.getMalzeme3()).append("\n");
        if (secilen.getMalzeme4() != null && !secilen.getMalzeme4().isEmpty())
            detaylar.append("- ").append(secilen.getMalzeme4()).append("\n");
        if (secilen.getMalzeme5() != null && !secilen.getMalzeme5().isEmpty())
            detaylar.append("- ").append(secilen.getMalzeme5()).append("\n");

        detayAlani.setText(detaylar.toString());

        try {
            java.net.URL url = new java.net.URL(secilen.getResimUrl());
            ImageIcon icon = new ImageIcon(url);
            Image img = icon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
            resimLabel.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            resimLabel.setIcon(null);
        }

        youtubeUrl = secilen.getYoutubeUrl();
        if (youtubeUrl != null && !youtubeUrl.isEmpty()) {
            youtubeButonu.setEnabled(true);
        } else {
            youtubeButonu.setEnabled(false);
        }
    }
}