package logIn.drivers;

import entities.User;

import logIn.use_case.LogInDsGateway;

public class LogInDatabaseAccess implements LogInDsGateway {

    public LogInDatabaseAccess(){
    }


    @Override
    public boolean checkUserEmailExists(String email){
        // update when csv is up
        return false;
    }

    @Override
    public boolean checkPasswordMatches(String email, String pass){
        // update when csv is up
        return false;
    }

    @Override
    public User getUser(boolean success, String email, String pass){
        // update when csv is up
        return new User(false, 0,"asda", "asda");
    }


    //testing method see LogInTest for usage
    @Override
    public void addUser(User user){

    }

}
