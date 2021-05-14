package ai.aprillabs.listingapp.service;

import ai.aprillabs.listingapp.domain.Listing;
import ai.aprillabs.listingapp.repository.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The type Listing service.
 */
@Service
public class ListingService {
    
    private final ListingRepository listingRepository;
    
    /**
     * Instantiates a new Listing service.
     *
     * @param listingRepository the listing repository
     */
    @Autowired
    public ListingService (ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }
    
    /**
     * Create listing listing.
     *
     * @param listing the listing
     * @return the listing
     */
    public Listing createListing (Listing listing) {
        return listingRepository.save(listing);
    }
    
    /**
     * Gets all listings.
     *
     * @return the all listings
     */
    public List<Listing> getAllListings () {
        return Streamable.of(listingRepository.findAll()).toList();
    }
    
    /**
     * Gets all listings by page.
     *
     * @param pageable the pageable
     * @return the all listings by page
     */
    public List<Listing> getAllListingsByPage (Pageable pageable) {
        return listingRepository.findAll(pageable).getContent();
    }
    
    /**
     * Gets listing.
     *
     * @param id the id
     * @return the listing
     */
    public Optional<Listing> getListing (Integer id) {
        return listingRepository.findById(id);
    }
    
    /**
     * Delete listing object.
     *
     * @param id the id
     * @return the object
     */
    public Object deleteListing (Integer id) {
        listingRepository.deleteById(id);
        return id;
    }
}
