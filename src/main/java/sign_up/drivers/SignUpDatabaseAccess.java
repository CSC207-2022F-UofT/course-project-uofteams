package sign_up.drivers;

import com.opencsv.CSVWriter;
import entities.User;
import sign_up.use_case.SignUpDSGateway;
import com.opencsv.CSVReader;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;

public class SignUpDatabaseAccess implements SignUpDSGateway {
    private final String userFilePath;
    private final String adminFilePath;

    public SignUpDatabaseAccess(String userFilePath, String adminFilePath) {
        this.userFilePath = userFilePath;
        this.adminFilePath = adminFilePath;
    }

    @Override
    public int getNumberUsers() {
        ArrayList<String[]> userInfo;
        try {
            userInfo = readLineByLine(userFilePath);
        } catch(Exception e) {
            System.out.println("Wrong Path");
            userInfo = new ArrayList<>();
        }
        return userInfo.size();
    }

    @Override
    public ArrayList<String> getEmails() {
        ArrayList<String[]> userInfo;
        try {
            userInfo = readLineByLine(userFilePath);
        } catch(Exception e) {
            System.out.println("Wrong Path");
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
        ArrayList<String[]> userInfo;
        try {
            userInfo = readLineByLine(userFilePath);
        } catch(Exception e) {
            System.out.println("Wrong Path");
            userInfo = new ArrayList<>();
        }

        String[] toAdd = {convertListToString((ArrayList<Integer>) toSave.getFavourites()),
                convertListToString((ArrayList<Integer>) toSave.getPosts()), convertBoolToString(toSave.isAdmin()),
                String.valueOf(toSave.getId()), toSave.getPassword(), toSave.getEmail()};

        userInfo.add(toAdd);

        try {
            write(userInfo, userFilePath);
        } catch (Exception e) {
            System.out.println("oopsy daisy");
        }
    }

    @Override
    public String getAdminPassword() {
        ArrayList<String> userInfo;
        try {
            userInfo = (ArrayList<String>) readLineByLine(adminFilePath);
        } catch(Exception e) {
            System.out.println("Wrong Path");
            userInfo = new ArrayList<>();
        }
        return userInfo.get(0);
    }

    private ArrayList<String[]> readLineByLine(String filePath) throws Exception {
        ArrayList<String[]> list = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(Path.of(filePath))) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                String[] line;
                while ((line = csvReader.readNext()) != null) {
                    list.add(line);
                }
            }
        }
        return list;
    }

    private String convertListToString(ArrayList<Integer> list) {
        String toReturn = "";

        for (Integer integer: list) {
            if (list.indexOf(integer) == 0) {
                toReturn = integer.toString().concat(toReturn);
            } else {
                toReturn = integer.toString().concat(".").concat(toReturn);
            }
        }
        return toReturn;
    }

    private String convertDatetoString(LocalDate toConvert) {
        String toReturn = "";
        toReturn = toReturn.concat(String.valueOf(toConvert.getYear()));
        toReturn = toReturn.concat(".");
        toReturn = toReturn.concat(String.valueOf(toConvert.getMonth()));
        toReturn = toReturn.concat(".");
        toReturn = toReturn.concat(String.valueOf(toConvert.getDayOfMonth()));

        return toReturn;
    }

    private String convertBoolToString(boolean toConvert) {
        if (toConvert) {
            return "1";
        } else {
            return "0";
        }
    }

    private void write(ArrayList<String[]> toWrite, String filePath) throws Exception {
        try (CSVWriter writer = new CSVWriter(new FileWriter(Path.of(filePath).toFile()))) {
            for (String[] line : toWrite) {
                writer.writeNext(line);
            }
        }
    }
    }
