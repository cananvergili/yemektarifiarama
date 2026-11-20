package com.tarifler.yemektarifleri;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class TarifApiServisi {

    private final String API_URL = "https://www.themealdb.com/api/json/v1/1/search.php?s=";

    public List<Tarif> tarifAra(String isim) {
        RestTemplate restTemplate = new RestTemplate();
        TarifApiYaniti yanit = restTemplate.getForObject(API_URL + isim, TarifApiYaniti.class);
        return (yanit != null && yanit.getMeals() != null) ? yanit.getMeals() : new ArrayList<>();
    }
}


