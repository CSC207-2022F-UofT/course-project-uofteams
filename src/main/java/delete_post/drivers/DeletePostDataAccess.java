
package delete_post.drivers;

import delete_post.use_case.DeletePostDsGateway;
import delete_post.use_case.PostReaderInterface;
import entities.Post;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeletePostDataAccess implements DeletePostDsGateway{

    private final PostReaderInterface postReader;
    private final String postPath;
    private final String userPath;
    private final String commentPath;

    public DeletePostDataAccess(String postPath, String userPath, String commentPath,
                                PostReaderInterface postReader){
        this.postPath = postPath;
        this.userPath = userPath;
        this.commentPath = commentPath;
        this.postReader = postReader;
    }

    @Override
    public Post getPost(int postId){
        List<String[]> postData = readFile(postPath);
        for (String[] row : postData) {
            if (Integer.parseInt(row[0]) == postId) {
                return postReader.readPost(row);
            }
        }
        System.out.println("Could not find post in database");
        return null;
    }

    public void removeFavourite(int postId, int userId){
        removeFromUserList(postId, userId, 5);
    }

    public void removeUser(int postId, int userId){
        removeFromUserList(postId, userId, 4);
    }

    public void deletePost(int postId){
        deleteRow(postPath, postId);
    }

    public void deleteComment(int commentId){
        deleteRow(commentPath, commentId);
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

    private void deleteRow(String path, int id){
        List<String[]> oldData = readFile(path);
        List<String[]> newData = new ArrayList<>();
        for (String[] row : oldData){
            if (Integer.parseInt(row[0]) != id){
                newData.add(row);
            }
        }
        fileWriter(path, newData);
    }

    private void removeFromUserList(int postId, int userId, int listCol){
        List<String[]> userData = readFile(userPath);

        for (String[] row: userData){
            if (userId == Integer.parseInt(row[0])){
                StringBuilder newList = new StringBuilder();
                String[] oldList = row[listCol].split(" ");
                for (String id : oldList){
                    if (Integer.parseInt(id) != postId) {
                        if (newList.length() != 0){
                            newList.append(" ");
                        }
                        newList.append(id);
                    }
                }
                row[listCol] = newList.toString();
                break;
            }
        }
        fileWriter(userPath, userData);
    }
}

