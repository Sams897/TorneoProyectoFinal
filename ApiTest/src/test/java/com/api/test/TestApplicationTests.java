package com.api.test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.api.test.data.TorneoData;
import com.api.test.model.Torneo;

@SpringBootTest
class TestApplicationTests {

    @Test
    void contextLoads() {
        TorneoData TorneoData = new TorneoData();
        List<Torneo> data = TorneoData.GetData();
        assertNotNull(data);
    }
}
