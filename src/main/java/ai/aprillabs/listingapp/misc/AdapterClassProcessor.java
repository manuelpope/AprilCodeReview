package ai.aprillabs.listingapp.misc;

import ai.aprillabs.listingapp.domain.User;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * The type Adapter class processor.
 */
@Component
public class AdapterClassProcessor {
    
    
    /**
     * The constant USER_PREDICATE_25_10_PARTNER.
     */
    public static final Predicate<User> USER_PREDICATE_25_10_PARTNER = r -> r.getAge() < 25 && r.getNumOfReferral() > 10;
    /**
     * The constant MAPPER.
     */
    public static final Function<User, Integer> MAPPER = d -> 20;
    
    /**
     * Return discount for class partner integer.
     *
     * @param user the user
     * @return the integer
     */
    public static Integer returnDiscountForClassPartner (User user) {
        return Optional.of(user).
                filter(r -> r.getAge() > 25).map(d -> 5).
                orElse(Optional.of(user).
                        filter(USER_PREDICATE_25_10_PARTNER).
                        map(MAPPER).orElse(10));
    }
    
    /**
     * Return discount for class agent integer.
     *
     * @param user the user
     * @return the integer
     */
    public static Integer returnDiscountForClassAgent (User user) {
        if (user.getAge() < 25) {
            return 10;
        } else {
            return 20;
        }
    }
    
    /**
     * Return discount for class contractor integer.
     *
     * @param user the user
     * @return the integer
     */
    public static Integer returnDiscountForClassContractor (User user) {
        if (user.getAge() < 25) {
            if (user.getNumOfReferral() > 10) {
                return 20;
            } else {
                return 10;
            }
        } else {
            return 5;
        }
    }
    
    /**
     * Return discount for class broker integer.
     *
     * @param user the user
     * @return the integer
     */
    public static Integer returnDiscountForClassBroker (User user) {
        return 40;
    }
    
    /**
     * Return discount for class land lord integer.
     *
     * @param user the user
     * @return the integer
     */
    public static Integer returnDiscountForClassLandLord (User user) {
        return 30;
    }
}
