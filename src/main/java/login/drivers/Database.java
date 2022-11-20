package login.drivers;

import entities.User;
import login.use_case.LogInDsGateway;

public class Database implements LogInDsGateway {


    @Override
    public boolean checkUserEmailExists(String email){
        return false;
    }

    @Override
    public boolean checkPasswordMatches(String email, String pass){
        return false;
    }

    // this is just to set up will figure out how to implement this
    @Override
    public User getUser(){
        return new User(false, 123, "123", "123");
    }

}
