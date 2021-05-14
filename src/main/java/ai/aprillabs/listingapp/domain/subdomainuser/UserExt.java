package ai.aprillabs.listingapp.domain.subdomainuser;

import ai.aprillabs.listingapp.domain.User;
import ai.aprillabs.listingapp.domain.UserI;

/**
 * The type User ext.
 */
public class UserExt extends User implements UserI {
    
    private Integer discount;
    
    
    @Override
    public Integer getDiscount () {
        return discount;
    }
    
    @Override
    public void setDiscount (Integer integer) {
        this.discount = integer;
    }
}
