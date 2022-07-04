package htw.berlin.webtech.matefinder.web;

import htw.berlin.webtech.matefinder.service.MateService;
import htw.berlin.webtech.matefinder.web.api.Mate;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MateController.class)
public class MateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MateService mateService;
    private List<Integer> ratingIds;

    @Test
    @DisplayName("should return found mate from mateservice")
    void foundMateTest() throws Exception {
        // given
        var mates = List.of(
                new Mate(1, "Club-Mate", new BigDecimal("1.19"), ratingIds),
                new Mate(2, "Ulticha Mate", new BigDecimal("1.29"), ratingIds),
                new Mate(3, "ChariTea Mate", new BigDecimal("1.79"), ratingIds)
        );
        doReturn(mates).when(mateService).findAll();

        // when
        mockMvc.perform(get("/api/mates"))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Club-Mate"))
                .andExpect(jsonPath("$[0].price").value("1.19"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Ulticha Mate"))
                .andExpect(jsonPath("$[1].price").value("1.29"))
                .andExpect(jsonPath("$[2].id").value(3))
                .andExpect(jsonPath("$[2].name").value("ChariTea Mate"))
                .andExpect(jsonPath("$[2].price").value("1.79"));
    }

    @Test
    @DisplayName("should return 404 if mate not found")
    void mateNotFoundTest() throws Exception {
        // given
        doReturn(null).when(mateService).findById(anyInt());

        // when
        mockMvc.perform(get("/api/mates/9001"))
                // then
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("should return 201 and Location Header when creating a Mate")
    void createMateTest() throws Exception {
        String mateToCreateAsJson = "{\"name\": \"Club-Mate\", \"price\": \"1.19\"}";
        var mate = new Mate(9001,null, null, ratingIds);
        doReturn(mate).when(mateService).create(any());

        // when
        mockMvc.perform(
                post("/api/mates")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mateToCreateAsJson)
                )
                // then
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", Matchers.equalTo(("/api/mates/" + mate.getId()))));
    }

    @Test
    @DisplayName("should validate create Mate request")
    void validateCreateRequestTest() throws Exception {
        // given
        String mateToCreateAsJson = "{\"name\": \", \"price\": \"1.19\"}";

        mockMvc.perform(
                post("/api/mates")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mateToCreateAsJson)
            )
                // then
                .andExpect(status().isBadRequest());
    }
}
