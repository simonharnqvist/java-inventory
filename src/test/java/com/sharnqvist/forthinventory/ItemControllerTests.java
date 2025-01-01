package com.sharnqvist.forthinventory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.json.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ItemControllerTests {

    @Autowired
    private MockMvc mockMvc;

    private static String boat1Json;

    @BeforeAll
    public static void setup() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "boat1");
        jsonObject.put("category", "polo kayak");
        jsonObject.put("location", "sprint corridor");
        boat1Json = jsonObject.toString();
    }

    @Test
    public void emptyGetItems() throws Exception {
        // Act
        ResultActions response = mockMvc.perform(
                get("/items"));

        // Assert
        response.andDo(print()).//
                andExpect(status().isOk());
    }

    @Test
    public void addItem() throws Exception {

        ResultActions response = mockMvc.perform(
                post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(boat1Json))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testAndGetItem() throws Exception {}
}