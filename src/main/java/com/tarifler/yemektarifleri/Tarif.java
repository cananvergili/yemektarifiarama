package com.tarifler.yemektarifleri;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)

public class Tarif {
    @Override
    public String toString() {
        return ad + " (" + kategori + ")";
    }


    @JsonProperty("idMeal")
    private String id;

    @JsonProperty("strMeal")
    private String ad;

    @JsonProperty("strCategory")
    private String kategori;

    @JsonProperty("strArea")
    private String bolge;

    @JsonProperty("strInstructions")
    private String talimat;

    @JsonProperty("strMealThumb")
    private String resimUrl;

    @JsonProperty("strYoutube")
    private String youtubeUrl;

    @JsonProperty("strIngredient1")
    private String malzeme1;

    @JsonProperty("strIngredient2")
    private String malzeme2;

    @JsonProperty("strIngredient3")
    private String malzeme3;

    @JsonProperty("strIngredient4")
    private String malzeme4;

    @JsonProperty("strIngredient5")
    private String malzeme5;


    // Getter ve Setter'lar

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getBolge() {
        return bolge;
    }

    public void setBolge(String bolge) {
        this.bolge = bolge;
    }

    public String getTalimat() {
        return talimat;
    }

    public void setTalimat(String talimat) {
        this.talimat = talimat;
    }

    public String getResimUrl() {
        return resimUrl;
    }

    public void setResimUrl(String resimUrl) {
        this.resimUrl = resimUrl;
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    public void setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }

    public String getMalzeme1() {
        return malzeme1;
    }

    public void setMalzeme1(String malzeme1) {
        this.malzeme1 = malzeme1;
    }

    public String getMalzeme2() {
        return malzeme2;
    }

    public void setMalzeme2(String malzeme2) {
        this.malzeme2 = malzeme2;
    }

    public String getMalzeme3() {
        return malzeme3;
    }

    public void setMalzeme3(String malzeme3) {
        this.malzeme3 = malzeme3;
    }

    public String getMalzeme4() {
        return malzeme4;
    }

    public void setMalzeme4(String malzeme4) {
        this.malzeme4 = malzeme4;
    }

    public String getMalzeme5() {
        return malzeme5;
    }

    public void setMalzeme5(String malzeme5) {
        this.malzeme5 = malzeme5;
    }
}

