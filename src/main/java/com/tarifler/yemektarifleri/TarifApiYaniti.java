package com.tarifler.yemektarifleri;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class TarifApiYaniti {

    @JsonProperty("meals")
    private List<Tarif> meals;

    public List<Tarif> getMeals() {
        return meals;
    }

    public void setMeals(List<Tarif> meals) {
        this.meals = meals;
    }
}
