
package delete_post.drivers;

import delete_post.use_case.DeletePostDsGateway;
import delete_post.use_case.DeletePostReaderInterface;
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

    private final DeletePostReaderInterface postReader;
    private final String postPath;
    private final String userPath;
    private final String commentPath;

    /**
     * Initialize DeletePostDataAccess object
     * @param generalPath path to location of post, user, comment csv files
     * @param postReader DeletePostReaderInterface instance
     */
    public DeletePostDataAccess(String generalPath, DeletePostReaderInterface postReader){
        this.postPath = generalPath + "posts.csv";
        this.userPath = generalPath + "users.csv";
        this.commentPath = generalPath + "comments.csv";
        this.postReader = postReader;
    }

    /**
     * Retrieve post data from database and create Post object
     * @param postId ID of post to created
     * @return Post object
     */
    @Override
    public Post getPostDelete(int postId){
        List<String[]> postData = readFile(postPath);
        postData.remove(0);
        for (String[] row : postData) {
            if (Integer.parseInt(row[0]) == postId) {
                return postReader.readPost(row);
            }
        }
        System.out.println("Could not find post in database");
        return null;
    }

    /**
     * Delete post from a user's favourite list in database
     * @param postId ID of post to be deleted
     * @param userId ID of user to delete from
     */
    public void removeFavourite(int postId, int userId){
        removeFromUserList(postId, userId, 5);
    }

    /**
     * Delete post from its user's post list in database
     * @param postId ID of post to be deleted
     * @param userId ID of user to delete from
     */
    public void removeUser(int postId, int userId){
        removeFromUserList(postId, userId, 4);
    }

    /**
     * Delete post from post csv file
     * @param postId ID of post to be deleted
     */
    public void deletePost(int postId){
        deleteRow(postPath, postId);
    }

    /**
     * Delete comment from comment csv file
     * @param commentId ID of comment to be deleted
     */
    public void deleteComment(int commentId){
        deleteRow(commentPath, commentId);
    }

    /**
     * Helper method to read data from csv file
     * @param path path to file
     * @return Data contained in file as a List<String[]>
     */
    private List<String[]> readFile(String path) {
        File file = new File(path);
        List<String[]> data = null;
        try {
            // read
            FileReader fileReader = new FileReader(file);
            CSVReaderBuilder csvReaderBuilder = new CSVReaderBuilder(fileReader);

            CSVReader reader = csvReaderBuilder.build();
            data = reader.readAll();

        } catch (IOException e) {
            System.out.println("Error during accessing file.");
        } catch (CsvException e) {
            System.out.println("Error during reading file");
        }
        return data;
    }

    /**
     * Helper method to write data to csv file
     * @param path path to file
     * @param data data to write in file
     */
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

    /**
     * Helper method  to delete a row from csv file
     * @param path path to file
     * @param id ID of comment or post of which to delete row
     */
    private void deleteRow(String path, int id){
        List<String[]> oldData = readFile(path);
        List<String[]> newData = new ArrayList<>();
        for (String[] row : oldData){
            if (row[0].contains("ID") || Integer.parseInt(row[0]) != id){
                newData.add(row);
            }
        }
        fileWriter(path, newData);
    }


    /**
     * Helper method to delete a post from a User's list of favourites or posts in database
     * @param postId ID of post to be deleted
     * @param userId ID of user to delete from
     * @param listCol Column number of list to delete from in database
     */
    private void removeFromUserList(int postId, int userId, int listCol){
        List<String[]> userData = readFile(userPath);
        String[] header = userData.get(0);
        userData.remove(0);
        for (String[] row: userData){
            if (userId == Integer.parseInt(row[0])){
                StringBuilder newList = new StringBuilder();
                String[] oldList = row[listCol].split(" ");
                for (String id : oldList){
                    if (!id.isEmpty() && Integer.parseInt(id) != postId) {
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
        ArrayList<String[]> newData = new ArrayList<>();
        newData.add(header);
        newData.addAll(userData);
        fileWriter(userPath, newData);
    }
}

