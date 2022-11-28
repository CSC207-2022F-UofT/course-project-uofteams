package delete_post.drivers;

import delete_post.use_case.DeletePostDsGateway;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeletePostDataAccess implements DeletePostDsGateway{

    private final String postPath;
    private final String userPath;

    public DeletePostDataAccess(String postPath, String userPath){
        this.postPath = postPath;
        this.userPath = userPath;
    }

    @Override
    public int getPostUser(int postId){
        List<String[]> postData = readFile(postPath);
        try {
            for (String[] row : postData) {
                if (Integer.parseInt(row[0]) == postId) {
                    return Integer.parseInt(row[1]);
                }
            }
        }
        catch (Exception e){
            System.out.println("Error: Could not find post in database.");
        }
        return -1;
    }

    public void removeFavourites(int postId){
        List<String> favUsers;
        favUsers = new ArrayList<String>(Arrays.asList(getFavourite(postId).split(" ")));

        List<String[]> userData = readFile(userPath);

        for (String[] row: userData){
            if (favUsers.contains(row[0])){
                StringBuilder updateFav = new StringBuilder();
                String[] oldFavList = row[8].split(" ");
                for (String favId : oldFavList){
                    if (Integer.parseInt(favId) != postId) {
                        if (updateFav != null){
                            updateFav.append(" ");
                        }
                        updateFav.append(favId);
                    }
                }
                row[8] = updateFav.toString();
            }
        }
        fileWriter(userPath, userData);
    }
    public void deletePost(int postId){

        List<String[]> postData = readFile(postPath);

        int rowNum = 0;
        for (String[] row: postData){
            if (Integer.parseInt(row[0]) == postId){
                postData.remove(rowNum);
            }
            rowNum++;
        }

        fileWriter(postPath, postData);
    }

    private List<String[]> readFile(String path) {
        File file = new File(path);
        List<String[]> data = null;
        try {
            // read
            FileReader fileReader = new FileReader(file);
            CSVReaderBuilder csvReaderBuilder = new CSVReaderBuilder(fileReader);

            CSVReader reader = csvReaderBuilder.build();
            data = reader.readAll();
            // Remove the row corresponding to the header
            data.remove(0);
        } catch (IOException e) {
            System.out.println("Error during accessing file.");
        } catch (CsvException e) {
            System.out.println("Error during reading file");
        }
        return data;
    }
    private void fileWriter(String path, List<String[]> data) {
        try {
            FileWriter newFile = new FileWriter(path);
            CSVWriter writer = new CSVWriter(newFile);
            writer.writeAll(data);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error during writing file.");
        }
    }

    private String getFavourite(int postId){
        List<String[]> userData = readFile(userPath);
        for (String[] row: userData){
            if (Integer.parseInt(row[0]) == postId){
                return row[8];
            }
        }
        return null;
    }
}
