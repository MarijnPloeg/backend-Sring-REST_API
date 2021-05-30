package nl.marijnploeg.kitereparatie.controller;

import nl.marijnploeg.kitereparatie.service.RepairService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {RepairController.class})
@ExtendWith(SpringExtension.class)
public class RepairControllerTest {
    @Autowired
    private RepairController repairController;

    @MockBean
    private RepairService repairService;

    @Test
    public void testGetRepairs() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/repairs");
        MockMvcBuilders.standaloneSetup(this.repairController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("[]")));
    }

    @Test
    public void testGetRepairs2() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/repairs");
        getResult.contentType("Show all repairs");
        MockMvcBuilders.standaloneSetup(this.repairController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("[]")));
    }
}

