package make_comment.driver;

import com.opencsv.exceptions.CsvException;
import make_comment.use_case.MakeCommentGateway;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.*;

public class MakeCommentDatabaseAccess implements MakeCommentGateway {

    @Override
    public int getNumComments(){
        String filePath = "java/database/numCommentCreated.csv";
        File file = new File(filePath);
        try{
            FileReader fileReader = new FileReader(file);
            CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(1).build();
            int numCommentsCreated = Integer.valueOf(csvReader.peek()[0]);
            return numCommentsCreated;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getCurrentUserid() {
        int temp = Integer.parseInt(getCurrentUser().get("currentUserID"));
        return temp;
    }

    @Override
    public void setNumComments(int newNumCommentCreated) {

        int numCommentsCreated = this.getNumComments();
        String filePath = "java/database/numCommentCreated.csv";
        File file = new File(filePath);

        try {
            FileReader fileReader = new FileReader(file);

            CSVReader csvReader = new CSVReader(fileReader);

            List<String[]> csvBody = csvReader.readAll();
            csvBody.get(1)[0] = String.valueOf(newNumCommentCreated);
            FileWriter outputFile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputFile);
            writer.writeAll(csvBody);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveComment(Map<String, String> commentAttributes) {
        String filePath = "java/Database/comments.csv";
        File file = new File(filePath);
        String[] commentAtt = new String[commentAttributes.size()];
        commentAtt[0] = commentAttributes.get("commentID");
        commentAtt[1] = commentAttributes.get("commenterID");
        commentAtt[2] = commentAttributes.get("body");
        commentAtt[3] = commentAttributes.get("creationDate");
        try {
            FileReader filereader = new FileReader(file);
            CSVReader csvReader = new CSVReader(filereader);

            List<String[]> csvBody = csvReader.readAll();
            csvBody.add(commentAtt);
            FileWriter outputFile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputFile);
            writer.writeAll(csvBody);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updatePostDB() {

    }

    @Override
    public Map<String, String> getCurrentUser() {
        String filePath = "java/Database/currentUser.csv";
        File file = new File(filePath);
        try {

            FileReader filereader = new FileReader(file);

            CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
            String[] currentUserArray = csvReader.peek();
            Map<String, String> currentUser = new HashMap<>();
            currentUser.put("currentUserID", currentUserArray[0]);
            currentUser.put("isAdmin", currentUserArray[1]);
            currentUser.put("email", currentUserArray[2]);
            currentUser.put("password", currentUserArray[3]);
            currentUser.put("postIDs", currentUserArray[4]);
            currentUser.put("favouritesIDs", currentUserArray[5]);
            return currentUser;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getCurrentPostID() {
        return 0;
    }
}