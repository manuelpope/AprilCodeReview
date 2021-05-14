package ai.aprillabs.listingapp.service;

import ai.aprillabs.listingapp.domain.User;
import ai.aprillabs.listingapp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static ai.aprillabs.listingapp.domain.UserType.PARTNER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * The type User service test.
 */
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    
    /**
     * The User repository.
     */
    @Mock
    UserRepository userRepository;
    /**
     * The User service.
     */
    @InjectMocks
    UserService userService;
    
    
    /**
     * Gets user service discount 10.
     */
    @Test
    void getUserServiceDiscount10 () {
        userService = new UserService(userRepository);
        User user = new User();
        user.setAge(24);
        user.setType(PARTNER);
        user.setNumOfReferral(10);
        when(userRepository.findById(1)).thenReturn(java.util.Optional.of(user));
        assertEquals(10, userService.getDiscountRateForUser(1));
        
    }
    
    /**
     * Gets user service discount 5.
     */
    @Test
    void getUserServiceDiscount5 () {
        userService = new UserService(userRepository);
        User user = new User();
        user.setAge(26);
        user.setType(PARTNER);
        user.setNumOfReferral(10);
        when(userRepository.findById(1)).thenReturn(java.util.Optional.of(user));
        assertEquals(5, userService.getDiscountRateForUser(1));
        
    }
    
}