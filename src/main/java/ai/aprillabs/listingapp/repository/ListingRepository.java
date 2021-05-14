package ai.aprillabs.listingapp.repository;

import ai.aprillabs.listingapp.domain.Listing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Listing repository.
 */
@Repository
public interface ListingRepository extends CrudRepository<Listing, Integer> {
    
    /**
     * Find all page.
     *
     * @param pageable the pageable
     * @return the page
     */
    Page<Listing> findAll (Pageable pageable);
}
