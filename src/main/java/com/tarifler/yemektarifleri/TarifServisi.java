package com.tarifler.yemektarifleri;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class TarifServisi {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String API_URL = "https://www.themealdb.com/api/json/v1/1/search.php?s=";

    public List<Tarif> tarifAra(String yemekAdi) {
        try {
            String url = API_URL + yemekAdi;
            Map response = restTemplate.getForObject(url, Map.class);

            if (response != null && response.containsKey("meals")) {
                List<Map<String, Object>> meals = (List<Map<String, Object>>) response.get("meals");
                return meals.stream().map(this::mapToTarif).toList();
            }
        } catch (Exception e) {
            System.out.println("Hata oluştu: " + e.getMessage());
        }

        return Collections.emptyList();
    }

    private Tarif mapToTarif(Map<String, Object> json) {
        Tarif t = new Tarif();
        t.setId((String) json.get("idMeal"));
        t.setAd((String) json.get("strMeal"));
        t.setKategori((String) json.get("strCategory"));
        t.setBolge((String) json.get("strArea"));
        t.setTalimat((String) json.get("strInstructions"));
        t.setResimUrl((String) json.get("strMealThumb"));
        t.setYoutubeUrl((String) json.get("strYoutube"));
        t.setMalzeme1((String) json.get("strIngredient1"));
        t.setMalzeme2((String) json.get("strIngredient2"));
        t.setMalzeme3((String) json.get("strIngredient3"));
        t.setMalzeme4((String) json.get("strIngredient4"));
        t.setMalzeme5((String) json.get("strIngredient5"));
        return t;
    }
}
