package sign_up.drivers;

import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import entities.User;
import sign_up.use_case.SignUpDsGateway;
import com.opencsv.CSVReader;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Database access for the Sign Up use case
 */
public class SignUpDatabaseAccess implements SignUpDsGateway {
    private final String userFilePath;
    private final String adminFilePath;
    private final String numUsersCreatedFilePath;

    /**
     * Intialize a new SignUpDatabaseAccess
     * @param generalPath the path in the computer which leads to the database
     */
    public SignUpDatabaseAccess(String generalPath) {
        this.userFilePath = generalPath + "users.csv";
        this.adminFilePath = generalPath + "admin.csv";
        this.numUsersCreatedFilePath = generalPath + "numUsersCreated.csv";
    }

    /**
     * Return the number of users created in the system
     * @return An int that represents how many users have been created
     */
    @Override
    public int getNumberUsers() {
        File file = new File(numUsersCreatedFilePath);
        try {
            // Create an object of fileReader
            // class with CSV file as a parameter.
            FileReader fileReader = new FileReader(file);

            // create csvReader object passing
            // file reader as a parameter
            CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(1).build();
            int toReturn = Integer.parseInt(csvReader.peek()[0]);
            csvReader.close();
            return toReturn;
        } catch (IOException e) {
            System.out.println("Either wrong path or file has not been formatted correctly. ");
            return 0;
        }
    }

    /**
     * Update the database with the new number of users
     * @param numberUsers The current number of users created
     */
    @Override
    public void setNumberUsers(int numberUsers) {
        File file = new File(numUsersCreatedFilePath);
        try {
            FileReader fileReader = new FileReader(file);
            // create FileWriter object with file as parameter
            CSVReader csvReader = new CSVReader(fileReader);
            // create CSVWriter object filewriter object as parameter
            List<String[]> csvBody = csvReader.readAll();
            csvBody.get(1)[0] = String.valueOf(numberUsers);
            for(int i = 0; i < csvBody.size(); i++){
                if(csvBody.get(i).length == 0 && csvBody.get(i)[0].equals("")){
                    csvBody.remove(i);
                    break;
                }
            }
            FileWriter outputFile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputFile);
            writer.writeAll(csvBody);
            writer.flush();
            // closing writer connection
            writer.close();
        } catch (IOException e) {
            System.out.println("Wrong path");
        } catch (CsvException e) {
            System.out.println("CSV error");
        }
    }

    /**
     * Return all emails in the system
     * @return An ArrayList containing all emails
     */
    @Override
    public ArrayList<String> getEmails() {
        ArrayList<String[]> userInfo;
        try {
            userInfo = readLineByLine(userFilePath);
        } catch(IOException e) {
            System.out.println("Wrong Path");
            userInfo = new ArrayList<>();
        } catch(CsvException e) {
            System.out.println("Line Invalid in CSV Reader");
            userInfo = new ArrayList<>();
        }

        ArrayList<String> emails = new ArrayList<String>();

        for (String[] subUserInfo: userInfo) {

            emails.add(subUserInfo[2]);
        }

        return emails;
    }

    /**
     * Save the given User to the database
     * @param toSave the User that is going to be saved
     */
    @Override
    public void saveUser(String[] toSave) {
        File file = new File(userFilePath);
        try {
            FileReader fileReader = new FileReader(file);
            // create FileWriter object with file as parameter
            CSVReader csvReader = new CSVReader(fileReader);
            // create CSVWriter object filewriter object as parameter
            List<String[]> csvBody = csvReader.readAll();
            csvBody.add(toSave);
            //this is to get rid of extra line that gets added by the csvReader!
            for(int i = 0; i < csvBody.size(); i++){
                if(csvBody.get(i).length == 0 && csvBody.get(i)[0].equals("")){
                    csvBody.remove(i);
                    break;
                }
            }
            FileWriter outputFile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputFile);
            writer.writeAll(csvBody);
            writer.flush();
            // closing writer connection
            writer.close();
        } catch (IOException e) {
            System.out.println("Cannot find file");
        } catch (CsvException e) {
            System.out.println("Incorrect File Format");
        }

    }

    /**
     * Return the password for the administrator account
     * @return A String representation of the admin password
     */
    @Override
    public String getAdminPassword() {
        File file = new File(adminFilePath);
        try {
            // Create an object of fileReader
            // class with CSV file as a parameter.
            FileReader fileReader = new FileReader(file);

            // create csvReader object passing
            // file reader as a parameter
            CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(1).build();
            String toReturn = csvReader.peek()[0];
            csvReader.close();
            return toReturn;
        } catch (IOException e) {
            System.out.println("Either wrong path or file has not been formatted correctly. ");
            return "";
        }
    }

    /**
     * Returns each line of the database in a List
     * @param filePath The path to whatever file is being read
     * @return A List containing every line in the database, where every element is an array
     * containing the elements of that row
     * @throws IOException If the filepath is wrong
     * @throws CsvValidationException If the CSV reader cannot read the given file
     */
    private ArrayList<String[]> readLineByLine(String filePath) throws IOException, CsvValidationException {
        ArrayList<String[]> list = new ArrayList<>();
        Reader reader = Files.newBufferedReader(Path.of(filePath));
        CSVReader csvReader = new CSVReader(reader);
        String[] line;
        while ((line = csvReader.readNext()) != null) {
            list.add(line);
        }
        return list;
    }
}