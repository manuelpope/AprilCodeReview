package ai.aprillabs.listingapp.web;

import ai.aprillabs.listingapp.domain.Listing;
import ai.aprillabs.listingapp.misc.MapperDTO;
import ai.aprillabs.listingapp.service.ListingService;
import ai.aprillabs.listingapp.web.dto.request.CreateListingRequestDto;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static ai.aprillabs.listingapp.misc.MapperDTO.LISTING_REQUEST_DTO_PREDICATE;

/**
 * The type Listing controller.
 */
@RestController
@RequestMapping(path = "/listings")
public class ListingController {
    private final ListingService listingService;
    private final Gson gson = new Gson();
    
    /**
     * Instantiates a new Listing controller.
     *
     * @param listingService the listing service
     */
    @Autowired
    public ListingController (ListingService listingService) {
        this.listingService = listingService;
    }
    
    /**
     * Create listing response entity.
     *
     * @param listingDto the listing dto
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity<CreateListingRequestDto> createListing (@Valid @RequestBody CreateListingRequestDto listingDto) {
        return Optional.ofNullable(listingDto).
                filter(LISTING_REQUEST_DTO_PREDICATE)
                .map(d -> new ResponseEntity<CreateListingRequestDto>(HttpStatus.BAD_REQUEST)).orElseGet(() -> {
                    assert listingDto != null;
                    listingService.createListing(MapperDTO.mapperDTOListing(listingDto));
                    listingDto.setCreatedAt(MapperDTO.getISODate());
                    return new ResponseEntity<>(
                            listingDto,
                            HttpStatus.OK);
                });
    }
    
    /**
     * Gets listing.
     *
     * @param id the id
     * @return the listing
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<Listing> getListing (@Valid @PathVariable Integer id) {
        return listingService.getListing(id)
                .map(listing -> new ResponseEntity<>(
                        listing,
                        HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    /**
     * Gets listing.
     *
     * @return the listing
     */
    @GetMapping(path = "/all")
    public ResponseEntity<String> getListing () {
        return new ResponseEntity<>(
                gson.toJson(listingService.getAllListings()),
                HttpStatus.OK);
        
    }
    
    /**
     * Gets listing.
     *
     * @param page the page
     * @param size the size
     * @return the listing
     */
    @GetMapping(path = "/allbypage/{page}/{size}")
    public ResponseEntity<String> getListing (@Valid @PathVariable Integer page, @PathVariable Integer size) {
        Pageable allSplitData =
                PageRequest.of(page, size);
        
        List<Listing> allListingByPage = listingService.getAllListingsByPage(allSplitData);
        
        return new ResponseEntity<>(
                gson.toJson(allListingByPage),
                HttpStatus.OK);
        
    }
    
    /**
     * Delete listings response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Integer> deleteListings (@PathVariable Integer id) {
        
        
        return Optional.of(listingService.getListing(id)).filter(r -> Objects.nonNull(r.get())).map(
                (d) -> {
                    listingService.deleteListing(id);
                    return new ResponseEntity<>(id, HttpStatus.OK);
                }).orElse(new ResponseEntity<>(id, HttpStatus.NO_CONTENT));
    }
}
