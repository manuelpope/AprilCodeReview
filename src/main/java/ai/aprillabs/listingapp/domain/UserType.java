package ai.aprillabs.listingapp.domain;

import ai.aprillabs.listingapp.domain.subdomainuser.UserExt;

import static ai.aprillabs.listingapp.misc.AdapterClassProcessor.*;

/**
 * The enum User type.
 */
public enum UserType {
    /**
     * The Partner.
     */
    PARTNER {
        public UserI getInstance (User user) {
            UserI partner = new UserExt();
            partner.setAge(user.getAge());
            partner.setType(user.getType());
            partner.setDiscount(returnDiscountForClassPartner(user));
            
            return partner;
        }
    },
    /**
     * The Landlord.
     */
    LANDLORD {
        public UserI getInstance (User user) {
            UserI partner = new UserExt();
            partner.setAge(user.getAge());
            partner.setType(user.getType());
            partner.setDiscount(returnDiscountForClassLandLord(user));
            
            return partner;
        }
    },
    /**
     * The Agent.
     */
    AGENT {
        public UserI getInstance (User user) {
            UserI partner = new UserExt();
            partner.setAge(user.getAge());
            partner.setType(user.getType());
            partner.setDiscount(returnDiscountForClassAgent(user));
            
            
            return partner;
        }
    },
    /**
     * The Broker.
     */
    BROKER {
        public UserI getInstance (User user) {
            UserI partner = new UserExt();
            partner.setAge(user.getAge());
            partner.setType(user.getType());
            partner.setDiscount(returnDiscountForClassBroker(user));
            
            return partner;
        }
    },
    /**
     * The Contractor.
     */
    CONTRACTOR {
        public UserI getInstance (User user) {
            UserI partner = new UserExt();
            partner.setAge(user.getAge());
            partner.setType(user.getType());
            partner.setDiscount(returnDiscountForClassContractor(user));
            
            
            return partner;
        }
        
        
    };
    
    /**
     * Gets instance.
     *
     * @param user the user
     * @return the instance
     */
    public abstract UserI getInstance (User user);
    
    
}

