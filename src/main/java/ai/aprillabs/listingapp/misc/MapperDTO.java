package ai.aprillabs.listingapp.misc;

import ai.aprillabs.listingapp.domain.Listing;
import ai.aprillabs.listingapp.web.dto.request.CreateListingRequestDto;
import org.apache.logging.log4j.util.Strings;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.function.Predicate;

/**
 * The type Mapper dto.
 */
public class MapperDTO {
    
    
    /**
     * The constant LISTING_REQUEST_DTO_PREDICATE.
     */
    public static final Predicate<CreateListingRequestDto> LISTING_REQUEST_DTO_PREDICATE = r -> Strings.isBlank(r.getAddress());
    
    
    /**
     * Mapper dto listing listing.
     *
     * @param createListingRequestDto the create listing request dto
     * @return the listing
     */
    public static Listing mapperDTOListing (CreateListingRequestDto createListingRequestDto) {
        
        
        return Listing.builder()
                .address(createListingRequestDto.getAddress())
                .baths(createListingRequestDto.getBaths())
                .price(createListingRequestDto.getPrice())
                .beds(createListingRequestDto.getBeds()).build();
    }
    
    
    /**
     * Gets iso date.
     *
     * @return the iso date
     */
    public static String getISODate () {
        
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
        df.setTimeZone(tz);
        return df.format(new Date());
    }
}
