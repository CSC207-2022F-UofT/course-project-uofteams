package deadline_timer.drivers;

import com.opencsv.CSVReader;
import deadline_timer.use_case.PostReader;
import deadline_timer.use_case.TimerDSGateway;
import entities.Post;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class TimerDataAccess implements TimerDSGateway {
    private final String postFilePath;
    private final PostReader postReader;

    public TimerDataAccess(String postFilePath, PostReader postReader) {
        this.postFilePath = postFilePath;
        this.postReader = postReader;
    }

    public HashMap<Integer, Post> getPosts() {
        HashMap<Integer, Post> toReturn = new HashMap<Integer, Post>();
        ArrayList<String[]> allPosts;
        try {
            allPosts = readLineByLine(postFilePath);
        } catch (Exception e) {
            System.out.println("Wrong file");
            allPosts = new ArrayList<String[]>();
        }
        for (String[] postInfo: allPosts) {
            HashMap<String, Object> hashMap = convertToHash(postInfo);
            toReturn.put((Integer) hashMap.get("postID"), (Post) postReader.readPost(hashMap));
        }

        return toReturn;
    }

    private ArrayList<String[]> readLineByLine(String filePath) throws Exception{
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

    private HashMap<String, Object> convertToHash(String[] strings) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();

        hashMap.put("postID", String.valueOf(strings[0]));
        hashMap.put("posterID", String.valueOf(strings[1]));
        hashMap.put("title", strings[2]);
        hashMap.put("mainDescription", strings[3]);
        hashMap.put("tags", fileToString(strings[4]));
        hashMap.put("collaborators", strings[5]);
        hashMap.put("deadline", fileToDate(strings[6]));
        hashMap.put("creationDate", fileToDate(strings[7]));
        hashMap.put("favourites", fileToInt(strings[8]));
        hashMap.put("replies", fileToInt(strings[9]));

        return hashMap;
    }

    private ArrayList<String> fileToString(String fileFormat) {
        return new ArrayList<String>(Arrays.asList(fileFormat.split(" ")));

    }

    private LocalDate fileToDate(String fileFormat) {
        int year = Integer.parseInt(fileFormat.substring(0, 4));
        int month = Integer.parseInt(fileFormat.substring(5, 7));
        int day = Integer.parseInt(fileFormat.substring(8, 10));

        return LocalDate.of(year, month, day);
    }

    private ArrayList<Integer> fileToInt(String fileFormat) {
        ArrayList<String> strings = fileToString(fileFormat);
        ArrayList<Integer> ints = new ArrayList<Integer>();
        for (String string: strings) {
            ints.add(Integer.parseInt(string));
        }

        return ints;
    }
}
