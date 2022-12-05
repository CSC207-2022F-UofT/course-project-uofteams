package delete_post;

import com.opencsv.CSVWriter;
import entities.Post;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.After;
import delete_post.interface_adapters.*;
import delete_post.use_case.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DeletePostTest {
    DeletePostController controller;
    DeletePostInputBoundary interactor;
    DeletePostOutputBoundary presenter;
    DeletePostDsGateway dsgateway;

    @Before
    public void setup(){
        String[] postHeader = new String[]{"postID", "userID", "title", "mainDescription", "tags", "collaborators",
                "deadline", "creationDate", "favouritedUsersIDs"};
        String[] post1 = new String[]{"0", "0", "title0", "desc0", "tag0", "collab0", "deadline0",
                "creation0", "1 2"};
        String[] post2 = new String[]{"1", "2", "title1", "desc1", "tag0 tag1", "collab1", "deadline1",
                "creation1", ""};

        String[] userHeader = new String[]{"userID", "favourites", "posts", "password", "email"};
        String[]
    }

    @After
    public void teardown(){}

    private void createTestFile(String path, String[] header, List<String[]> body){
        File file = new File(path);
        try {
            LinkedList<String[]> content = new LinkedList<String[]>();
            content.add(header);
            for (String[] row : body){
                content.add(row);
            }
            FileWriter outputFile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputFile);
            writer.writeAll(content);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error creating test file");
        }
    }
}
