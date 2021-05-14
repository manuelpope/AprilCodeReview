package ai.aprillabs.listingapp.web;

import ai.aprillabs.listingapp.domain.Listing;
import ai.aprillabs.listingapp.service.ListingService;
import ai.aprillabs.listingapp.web.dto.request.CreateListingRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

import static ai.aprillabs.listingapp.misc.MapperDTO.getISODate;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * The type Listing controller test.
 */
@WebMvcTest(ListingController.class)
class ListingControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private ListingService listingService;
    
    /**
     * Gets listing.
     *
     * @throws Exception the exception
     */
    @Test
    public void getListing () throws Exception {
        Listing listing = new Listing(1, "5th avenue", 2, 1, new BigDecimal(2500));
        when(listingService.getListing(1)).thenReturn(Optional.of(listing));
        this.mockMvc.perform(get("/listings/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(listing)));
    }
    
    /**
     * Gets listing not exists.
     *
     * @throws Exception the exception
     */
    @Test
    public void getListingNotExists () throws Exception {
        when(listingService.getListing(1)).thenReturn(Optional.empty());
        this.mockMvc.perform(get("/listings/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").doesNotExist());
    }
    
    /**
     * Gets listing input not valid.
     *
     * @throws Exception the exception
     */
    @Test
    public void getListingInputNotValid () throws Exception {
        this.mockMvc.perform(get("/listings/abc"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").doesNotExist());
    }
    
    /**
     * Gets listing all.
     *
     * @throws Exception the exception
     */
    @Test
    public void getListingAll () throws Exception {
        Listing listing = new Listing(1, "5th avenue", 2, 1, new BigDecimal(2500));
        when(listingService.getAllListings()).thenReturn(Collections.singletonList(listing));
        this.mockMvc.perform(get("/listings/all"))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(Collections.singletonList(listing))));
    }
    
    /**
     * Gets listing all by page.
     *
     * @throws Exception the exception
     */
    @Test
    public void getListingAllByPage () throws Exception {
        Listing listing = new Listing(1, "5th avenue", 2, 1, new BigDecimal(2500));
        Pageable allSplitData =
                PageRequest.of(1, 1);
        when(listingService.getAllListingsByPage(allSplitData)).thenReturn(Collections.singletonList(listing));
        this.mockMvc.perform(get("/listings/allbypage/1/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(Collections.singletonList(listing))));
    }
    
    /**
     * Post creating listings.
     *
     * @throws Exception the exception
     */
    @Test
    public void PostCreatingListings () throws Exception {
        CreateListingRequestDto listing = new CreateListingRequestDto();
        listing.setAddress("Avenida Siempre Viva");
        Gson gson = new Gson();
        CreateListingRequestDto listingAnswer = new CreateListingRequestDto();
        listingAnswer.setAddress("Avenida Siempre Viva");
        listingAnswer.setCreatedAt(getISODate());
        
        
        this.mockMvc.perform(post("/listings")
                .content(gson.toJson(listing))
                .contentType("application/json")
        )
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(listingAnswer)));
        
    }
    
    
    /**
     * Post delete listings.
     *
     * @throws Exception the exception
     */
    @Test
    public void PostDeleteListings () throws Exception {
        Listing listing = new Listing(1, "5th avenue", 2, 1, new BigDecimal(2500));
        when(listingService.getListing(1)).thenReturn(Optional.of(listing));
        when(listingService.deleteListing(1)).thenReturn(1);
        
        this.mockMvc.perform(delete("/listings/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(1)));
    }
    
}