package ai.aprillabs.listingapp.service;

import ai.aprillabs.listingapp.domain.User;
import ai.aprillabs.listingapp.domain.UserI;
import ai.aprillabs.listingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

import static ai.aprillabs.listingapp.domain.UserType.*;

/**
 * The type User service.
 */
@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    
    /**
     * Instantiates a new User service.
     *
     * @param userRepository the user repository
     */
    public UserService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    /**
     * Gets discount rate for user.
     *
     * @param id the id
     * @return the discount rate for user
     */
    public Integer getDiscountRateForUser (Integer id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            return 0;
        }
        
        
        return Optional.of(Objects.requireNonNull(returnInstance(userOpt.get())).getDiscount()).orElse(0);
        
    }
    
    
    private UserI returnInstance (User user) {
        switch (user.getType()) {
            
            case CONTRACTOR:
                return CONTRACTOR.getInstance(user);
            
            case PARTNER:
                return PARTNER.getInstance(user);
            
            case AGENT:
                return AGENT.getInstance(user);
            
            case LANDLORD:
                return LANDLORD.getInstance(user);
            
            case BROKER:
                return BROKER.getInstance(user);
            
            default:
                return null;
        }
    }
}


