package nl.marijnploeg.kitereparatie.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import nl.marijnploeg.kitereparatie.model.Address;
import nl.marijnploeg.kitereparatie.model.AppUser;
import nl.marijnploeg.kitereparatie.service.AddressService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AddressController.class})
@ExtendWith(SpringExtension.class)
public class AddressControllerTest {
    @Autowired
    private AddressController addressController;

    @MockBean
    private AddressService addressService;

    @Test
    public void testGetAddresses() throws Exception {
        when(this.addressService.getAddresses()).thenReturn(new ArrayList<Address>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/addresses");
        MockMvcBuilders.standaloneSetup(this.addressController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("[]")));
    }

    @Test
    public void testGetAddresses2() throws Exception {
        when(this.addressService.getAddresses()).thenReturn(new ArrayList<Address>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/addresses");
        getResult.contentType("Adres hier");
        MockMvcBuilders.standaloneSetup(this.addressController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("[]")));
    }

    @Test
    public void testDeleteAddressById() throws Exception {
        doNothing().when(this.addressService).deleteAddress(anyLong());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/addresses/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.addressController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testGetAddressById() throws Exception {
        Address address = new Address();
        address.setPostalCode("2011 RT");
        address.setStreetName("Jansstraat");
        address.setAddressID(1L);
        address.setCountry("Nederland");
        address.setAppUsers(new ArrayList<AppUser>());
        address.setCity("Haarlem");
        address.setHouseNumber(33);
        address.setState("Noord Holland");
        when(this.addressService.getAddressById(anyLong())).thenReturn(address);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/addresses/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.addressController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(Matchers.containsString(
                                "{\"addressID\":1,\"appUsers\":[],\"streetName\":\"Jansstraat\",\"houseNumber\":33,\"city\":\"Haarlem\",\"postalCode\":\"2011"
                                        + " RT\",\"country\":\"Nederland\",\"state\":\"Noord Holland\"}")));
    }

    @Test
    public void testGetAddressByUserId() throws Exception {
        Address address = new Address();
        address.setPostalCode("2011 RT");
        address.setStreetName("Jansstraat");
        address.setAddressID(1L);
        address.setCountry("Nederland");
        address.setAppUsers(new ArrayList<AppUser>());
        address.setCity("Haarlem");
        address.setHouseNumber(10);
        address.setState("Noord Holland");
        when(this.addressService.getAddressByUserId(anyLong())).thenReturn(address);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/addresses/klant/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.addressController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(Matchers.containsString(
                                "{\"addressID\":1,\"appUsers\":[],\"streetName\":\"Jansstraat\",\"houseNumber\":33,\"city\":\"Haarlem\",\"postalCode\":\"2011"
                                        + " RT\",\"country\":\"Nederland\",\"state\":\"Noord Holland\"}")));
    }

    @Test
    public void testUpdateAddress() throws Exception {
        doNothing().when(this.addressService).updateAddress(anyLong(), any());

        Address address = new Address();
        address.setPostalCode("2011 RT");
        address.setStreetName("Jansstraat");
        address.setAddressID(1L);
        address.setCountry("Nederland");
        address.setAppUsers(new ArrayList<AppUser>());
        address.setCity("Haarlem");
        address.setHouseNumber(10);
        address.setState("Noord Holland");
        String content = (new ObjectMapper()).writeValueAsString(address);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/addresses/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.addressController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}

