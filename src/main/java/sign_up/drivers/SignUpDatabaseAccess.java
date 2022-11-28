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

public class SignUpDatabaseAccess implements SignUpDsGateway {
    private final String userFilePath;
    private final String adminFilePath;
    private final String numUsersCreatedFilePath;

    public SignUpDatabaseAccess(String userFilePath, String adminFilePath, String numUsersCreatedFilePath) {
        this.userFilePath = userFilePath;
        this.adminFilePath = adminFilePath;
        this.numUsersCreatedFilePath = numUsersCreatedFilePath;
    }

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
            emails.add(subUserInfo[4]);
        }

        return emails;
    }

    @Override
    public void saveUser(User toSave) {
        File file = new File(userFilePath);
        String[] toAdd = {convertListToString((ArrayList<Integer>) toSave.getFavourites()),
                convertListToString((ArrayList<Integer>) toSave.getPosts()), Boolean.toString(toSave.isAdmin()),
                String.valueOf(toSave.getId()), toSave.getPassword(), toSave.getEmail()};

        try {
            FileReader fileReader = new FileReader(file);
            // create FileWriter object with file as parameter
            CSVReader csvReader = new CSVReader(fileReader);
            // create CSVWriter object filewriter object as parameter
            List<String[]> csvBody = csvReader.readAll();
            csvBody.add(toAdd);
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
            System.out.println("Cannot find file");;
        } catch (CsvException e) {
            System.out.println("Incorrect File Format");
        }

    }

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

    private String convertListToString(ArrayList<Integer> list) {
        String toReturn = "";

        for (Integer integer: list) {
            if (list.indexOf(integer) == 0) {
                toReturn = integer.toString().concat(toReturn);
            } else {
                toReturn = integer.toString().concat(" ").concat(toReturn);
            }
        }
        return toReturn;
    }

    private String convertDateToString(LocalDate toConvert) {
        String toReturn = "";
        toReturn = toReturn.concat(String.valueOf(toConvert.getYear()));
        toReturn = toReturn.concat(" ");
        toReturn = toReturn.concat(String.valueOf(toConvert.getMonth()));
        toReturn = toReturn.concat(" ");
        toReturn = toReturn.concat(String.valueOf(toConvert.getDayOfMonth()));

        return toReturn;
    }

}