package make_post.drivers;

import com.opencsv.exceptions.CsvException;
import make_post.use_case.MakePostDsGateway;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.*;

public class MakePostDatabaseAccess implements MakePostDsGateway {
    private final String filepath;

    /**
     * Initialise a MakePostDatabaseAccess object. It is responsible for retrieving and rewriting data in the db.
     * @param filepath  the file path from the repository root up until the file name.
     */
    public MakePostDatabaseAccess(String filepath){
        this.filepath = filepath;
    }

    /**
     * Gets the number of posts made from the db.
     * @return number of posts.
     */
    @Override
    public int getNumberOfPosts(){
        String filePath = filepath + "numPostsCreated.csv";
        File file = new File(filePath);
        try {
            FileReader fileReader = new FileReader(file);
            CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(1).build();
            int numPostsCreated = Integer.parseInt(csvReader.peek()[0]);
            csvReader.close();
            return numPostsCreated;
        } catch (IOException e) {
            System.out.println("Either wrong path or file has not been formatted correctly.");
            return 0;
        }
    }

    /**
     * Updates the number of posts created so far when a new post is made.
     * @param newNumPostsCreated the new number of posts.
     */
    @Override
    public void setNumberOfPosts(int newNumPostsCreated) {
        String filePath = filepath + "numPostsCreated.csv";
        File file = new File(filePath);
        try {
            FileReader fileReader = new FileReader(file);
            CSVReader csvReader = new CSVReader(fileReader);
            List<String[]> csvBody = csvReader.readAll();
            csvBody.get(1)[0] = String.valueOf(newNumPostsCreated);
            //removing empty spaces added by csvReader
            csvBody = removeEmptyLines(csvBody);
            FileWriter outputFile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputFile);
            writer.writeAll(csvBody);
            writer.flush();
            writer.close();
            csvReader.close();
        } catch (IOException | CsvException e) {
            System.out.println("Wrong path");
        }
    }

    /**
     * Saves a new post to the database.
     * @param postAttributes The attributes of the new post.
     */
    @Override
    public void savePost(Map<String, String> postAttributes) {
        String filePath = filepath + "posts.csv";
        File file = new File(filePath);
        String[] postAttributes1 = new String[postAttributes.size()];
        postAttributes1[0] = postAttributes.get("postID");
        postAttributes1[1] = postAttributes.get("posterID");
        postAttributes1[2] = postAttributes.get("title");
        postAttributes1[3] = postAttributes.get("mainDescription");
        postAttributes1[4] = postAttributes.get("tags");
        postAttributes1[5] = postAttributes.get("collaborators");
        postAttributes1[6] = postAttributes.get("deadline");
        postAttributes1[7] = postAttributes.get("creationDate");
        postAttributes1[8] = postAttributes.get("favouritedUsersIDs");
        postAttributes1[9] = postAttributes.get("repliesIDs");

        try {
            FileReader fileReader = new FileReader(file);
            CSVReader csvReader = new CSVReader(fileReader);
            List<String[]> csvBody = csvReader.readAll();
            csvBody.add(postAttributes1);
            //remove extra lines that get added by the csvReader!
            csvBody = removeEmptyLines(csvBody);
            FileWriter outputFile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputFile);
            writer.writeAll(csvBody);
            writer.flush();
            writer.close();
            csvReader.close();
        } catch (IOException | CsvException e) {
            System.out.println("Cannot find file or incorrect file format.");;
        }

    }

    /**
     * Removes empty lines from the table. Sometimes, the csvWriter adds empty rows.
     * @param csvBody the table to be updated.
     * @return a new table without the empty lines.
     */
    private List<String[]> removeEmptyLines(List<String[]> csvBody){
        List<String[]> newCsvBody = new LinkedList<>();
        for(String[] entry : csvBody){
            if(!(entry.length == 1 && entry[0].equals(""))){
                newCsvBody.add(entry);
            }
        }
        return newCsvBody;
    }
}
