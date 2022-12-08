package delete_post;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import delete_post.drivers.DeletePostDataAccess;
import entities.CurrentUser;
import org.junit.Before;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.After;
import delete_post.interface_adapters.*;
import delete_post.use_case.*;
import entities.Post;
import entities.User;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DeletePostDataAccessTest {
    DeletePostDataAccess dataAccess;
    String postPath = "src/test/java/delete_post/posts.csv";
    String userPath = "src/test/java/delete_post/users.csv";
    String commentPath = "src/test/java/delete_post/comments.csv";
    List<String[]> userData;
    List<String[]> postData;

    @Before
    public void setup(){
        userData = readFile(userPath);
        postData = readFile(postPath);

        PostReaderInterface postFactory = new PostFactory();
        dataAccess = new DeletePostDataAccess(postPath, userPath, commentPath, postFactory);
    }

    @Test
    public void DeletePostDataAccessGetPost(){
        Post post = dataAccess.getPost(0);
        assertEquals(0, post.getID());
        assertEquals(0, post.getUser());
        List<Integer> expected = new ArrayList<Integer>();
    }

    @Test
    public void DeletePostDataAccessRemoveFavourite(){
        dataAccess.removeFavourite(0, 1);
        List<Integer> expected = new ArrayList<>();
        expected.add(2);
        assertEquals(expected, getUserFavourites(1));
    }

    @Test
    public void DeletePostDataAccessRemovePost(){
        dataAccess.deletePost(1);
        List<String[]> data = readFile(postPath);
        data.remove(0);
        for (String[] row : data){
            assertNotEquals(1, Integer.parseInt(row[0]));
        }
    }

    @After
    public void teardown(){
        fileWriter(userPath, userData);
        fileWriter(postPath, postData);
    }

    private List<Integer> getUserFavourites(int userId){
        List<String[]> data = readFile(userPath);
        data.remove(0);
        for (String[] row : data){
            if (Integer.parseInt(row[0]) == userId){
                List<Integer> favs = new ArrayList<Integer>();
                for (String fav : row[5].split(" ")){
                    favs.add(Integer.parseInt(fav));
                }
                return favs;
            }
        }
        return new ArrayList<Integer>();
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
}
