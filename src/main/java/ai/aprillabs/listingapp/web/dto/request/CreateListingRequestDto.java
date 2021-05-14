package ai.aprillabs.listingapp.web.dto.request;

import lombok.Data;

import java.math.BigDecimal;

/**
 * The type Create listing request dto.
 */
@Data
public class CreateListingRequestDto {
    
    
    private String address;
    private Integer beds;
    private Integer baths;
    private BigDecimal price;
    private String createdAt;
}
