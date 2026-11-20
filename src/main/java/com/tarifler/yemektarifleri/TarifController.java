package com.tarifler.yemektarifleri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarifler")
public class TarifController {

    @Autowired
    private TarifServisi tarifServisi;
    @GetMapping("/ara")
    public List<Tarif> tarifAra(@RequestParam(name = "isim") String yemek) {
        return tarifServisi.tarifAra(yemek);
    }

}
