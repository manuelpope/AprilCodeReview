package ai.aprillabs.listingapp.domain;

public interface UserI {
    Integer getId ();
    
    void setId (Integer id);
    
    String getName ();
    
    void setName (String name);
    
    UserType getType ();
    
    void setType (UserType type);
    
    Integer getAge ();
    
    void setAge (Integer age);
    
    Integer getNumOfReferral ();
    
    void setNumOfReferral (Integer numOfReferral);
    
    Integer getDiscount ();
    
    void setDiscount (Integer integer);
}
