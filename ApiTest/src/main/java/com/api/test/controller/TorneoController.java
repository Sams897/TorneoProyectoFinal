package com.api.test.controller;

import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import com.api.test.data.TorneoData;
import com.api.test.model.Torneo;


@RestController
@RequestMapping("/api/torneo")
public class TorneoController
{
    @GetMapping
    public List<Torneo> getTest(HttpServletRequest request)
    {
        TorneoData TorneoData = new TorneoData();
        List<Torneo> data = TorneoData.GetData();


        // Imprimir datos en consola
        data.forEach(Torneo -> System.out.println(Torneo));

        return data;
    }    
}
