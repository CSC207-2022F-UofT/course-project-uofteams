package log_in.drivers;

import com.opencsv.exceptions.CsvException;
import entities.User;
import com.opencsv.CSVReader;
import log_in.use_case.LogInDsGateway;
import use_case_general.UserReaderInterface;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class LogInDatabaseAccess implements LogInDsGateway {
    private final String userPath;
    private final UserReaderInterface userReader;

    public LogInDatabaseAccess(String userPath, UserReaderInterface userReader){
        this.userPath = userPath;
        this.userReader = userReader;
    }


    /**
     * Method will call all emails in the database and check if the email given in the parameter
     * is in the database.
     * @param email email to be checked in the database.
     * @return boolean of if the email exists in the database or not.
     */
    @Override
    public boolean checkUserEmailExists(String email){
        ArrayList<String> emails = this.getData(4);
        for (int i = 0 ; i < emails.size(); i++ ){
            if (emails.get(i).equals(email)){
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
        ArrayList<String> emails = this.getData(4);
        ArrayList<String> passwords = this.getData(3);
        int emailIndex;
        int passIndex;
        if (emails.contains(email)){
            emailIndex = emails.indexOf(email);
        } else {
            return false;
        }
        if (passwords.contains(pass)){
            passIndex = passwords.indexOf(pass);
        }  else {
            return false;
        }

        return emailIndex == passIndex;
    }

    @Override
    public User getUser(boolean success, String email){
        if(success) {
            String[] userInfo = new String[6];

            ArrayList<String> emails = this.getData(2);
            int index = emails.indexOf(email);
            userInfo[2] = String.valueOf(index);

            ArrayList<String> ids = this.getData(0);
            userInfo[0] = ids.get(index);

            ArrayList<String> admins = this.getData(1);
            userInfo[1] = admins.get(index);

            ArrayList<String> passwords = this.getData(3);
            userInfo[3] = passwords.get(index);

            ArrayList<String> posts = this.getData(4);
            userInfo[4] = posts.get(index);

            ArrayList<String> replies = this.getData(5);
            userInfo[5] = replies.get(index);

            User user = userReader.readUser(userInfo);

            return user;
        } else {
            return null;
        }
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

        ArrayList<String> data = new ArrayList<String>();

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
