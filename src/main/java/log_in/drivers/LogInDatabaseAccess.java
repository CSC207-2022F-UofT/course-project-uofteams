package log_in.drivers;

import com.opencsv.exceptions.CsvException;
import entities.User;
import com.opencsv.CSVReader;
import log_in.use_case.LogInDsGateway;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class LogInDatabaseAccess implements LogInDsGateway {
    private final String userPath;

    public LogInDatabaseAccess(String userPath){
        this.userPath = userPath;
    }


    /**
     * Method will call all emails in the database and check if the email given in the parameter
     * is in the database.
     * @param email email to be checked in the database.
     * @return boolean of if the email exists in the database or not.
     */
    @Override
    public boolean checkUserEmailExists(String email){
        ArrayList<String> emails = this.getData(2);
        for (String s : emails) {
            if (s.equals(email)) {
                return true;
            }
        }
        return false;
    }

    /**
     * this method will check if a password matches an email by checking if their indexes
     * in the database are the same
     * @param email the inputted email by the user to be checked
     * @param pass the inputted password by the user to be checked that it matches the email
     * @return boolean on if email and pass match and pertain to one user.
     */
    @Override
    public boolean checkPasswordMatches(String email, String pass){
        ArrayList<String> emails = this.getData(2);
        ArrayList<String> passwords = this.getData(3);
        int emailIndex;
        if (emails.contains(email)){
            emailIndex = emails.indexOf(email);
        } else {
            return false;
        }
        if(passwords.get(emailIndex).equals(pass)){
            return true;
        }
       else {
            return false;
        }
    }

    @Override
    public String[] getUser(boolean success, String email, String pass){
        String[] userInfo = new String[6];
        if (success){
            ArrayList<String> emails = this.getData(2);
            ArrayList<String> admins = this.getData(1);
            ArrayList<String> posts = this.getData(4);
            ArrayList<String> favs = this.getData(5);
            ArrayList<String> ids = this.getData(0);

            int emailIndex = emails.indexOf(email);
            String adminValueString = admins.get(emailIndex);

            userInfo[0] = ids.get(emailIndex);
            userInfo[1] = adminValueString;
            userInfo[2] = email;
            userInfo[3] = pass;
            userInfo[4] = posts.get(emailIndex);
            userInfo[5] = favs.get(emailIndex);
        } else {
            return null;
        }

        return userInfo;
    }

    //testing method see LogInTest for usage
    @Override
    public void addUser(User user){

    }


    /**
     * This method will return a List of all the values in a csv file
     * @param indexInfo the attribute of users in interest
     * @return a List of a specific attribute of all users in a csv file
     */
    private ArrayList<String> getData(int indexInfo){
        ArrayList<String[]> userInfo;
        try {
            userInfo = readLines(userPath);
        } catch (IOException e){
            System.out.println("Wrong Path");
            userInfo = new ArrayList<>();
        } catch (CsvException e){
            System.out.println("Line Invalid");
            userInfo = new ArrayList<>();
        }

        ArrayList<String> data = new ArrayList<>();

        for (String[] usersInfo: userInfo){
            data.add(usersInfo[indexInfo]);
        }

        return data;
    }

    private ArrayList<String[]> readLines(String path) throws IOException, CsvException {
        ArrayList<String[]> list = new ArrayList<>();
        Reader reader = Files.newBufferedReader(Path.of(path));
        CSVReader csvReader = new CSVReader(reader);
        String[] line;
        while ((line = csvReader.readNext()) != null) {
            list.add(line);
        }
        return list;
    }
}
