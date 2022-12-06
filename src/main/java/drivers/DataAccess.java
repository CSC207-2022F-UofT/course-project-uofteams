package drivers;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.validators.RowMustHaveSameNumberOfColumnsAsFirstRowValidator;
import com.opencsv.validators.RowValidator;
import entities.Post;
import entities.User;
import favourite.use_case.FavouriteDsGateway;
import filter_post.use_case.FilterPostDsGateway;
import log_in.use_case.LogInDsGateway;
import make_comment.use_case.MakeCommentDsGateway;
import make_post.use_case.MakePostDsGateway;
import make_post.use_case.make_post_exceptions.MakePostException;
import sign_up.use_case.SignUpDsGateway;
import use_case_general.PostReaderInterface;
import use_case_general.UserReaderInterface;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

 public class DataAccess implements FilterPostDsGateway, LogInDsGateway, MakePostDsGateway, SignUpDsGateway, FavouriteDsGateway, MakeCommentDsGateway {
    private final String generalPath;
    private final PostReaderInterface postReader;
    private final UserReaderInterface userReader;

    public DataAccess(String generalPath, PostReaderInterface postReader, UserReaderInterface userReader){
        this.generalPath = generalPath;
        this.postReader = postReader;
        this.userReader = userReader;
    }

    // methods for filter post use case
    /**
     * Return a list of posts from the repository.
     */
    @Override
    public List<String[]> getPosts() {
        File file = new File(generalPath + "posts.csv");

        try {
            FileReader fileReader = new FileReader(file);
            CSVReaderBuilder csvReaderBuilder = new CSVReaderBuilder(fileReader);
            RowValidator rowValidator = new RowMustHaveSameNumberOfColumnsAsFirstRowValidator();
            csvReaderBuilder.withRowValidator(rowValidator);

            CSVReader reader = csvReaderBuilder.build();
            List<String[]> postData = reader.readAll();
            // Remove the row corresponding to the header
            postData.remove(0);

            return postData;
        } catch (IOException e) {
            System.out.println("Error during accessing file.");
        } catch (CsvException e) {
            System.out.println("Error during reading file");
        }
        return new ArrayList<>();
    }

    // methods for log in use case

    /**
     * Method will call all emails in the database and check if the email given in the parameter
     * is in the database.
     * @param email email to be checked in the database.
     * @return boolean of if the email exists in the database or not.
     */
    @Override
    public boolean checkUserEmailExists(String email) {
        ArrayList<String> emails = this.getUserData(4);
        for (int i = 0 ; i < emails.size(); i++ ){
            if (emails.get(i).equals(email)){
                return true;
            }
        }
        return false;
    }

    /**
     * this method will check if a password matches an email by checking if their indexes
     * in the database are the same
     * @param email the inputted email by the user to be checked
     * @param pass the inputted password by the user to be checked that it matches the email
     * @return boolean on if email and pass match and pertain to one user.
     */
    @Override
    public boolean checkPasswordMatches(String email, String pass) {
        ArrayList<String> emails = this.getUserData(4);
        ArrayList<String> passwords = this.getUserData(3);
        int emailIndex;
        int passIndex;
        if (emails.contains(email)){
            emailIndex = emails.indexOf(email);
        } else {
            return false;
        }
        if (passwords.contains(pass)){
            passIndex = passwords.indexOf(pass);
        }  else {
            return false;
        }

        return emailIndex == passIndex;
    }

    @Override
    public User getUser(boolean success, String email) {
        if(success) {
            String[] userInfo = new String[6];

            ArrayList<String> emails = this.getUserData(2);
            int index = emails.indexOf(email);
            userInfo[2] = String.valueOf(index);

            ArrayList<String> ids = this.getUserData(0);
            userInfo[0] = ids.get(index);

            ArrayList<String> admins = this.getUserData(1);
            userInfo[1] = admins.get(index);

            ArrayList<String> passwords = this.getUserData(3);
            userInfo[3] = passwords.get(index);

            ArrayList<String> posts = this.getUserData(4);
            userInfo[4] = posts.get(index);

            ArrayList<String> replies = this.getUserData(5);
            userInfo[5] = replies.get(index);

            return userReader.readUser(userInfo);
        } else {
            return null;
        }
    }


    @Override
    public void addUser(User user) {

 }

     // methods for make post use case
    @Override
    public int getNumberOfPosts() {
        String filePath = generalPath + "numPostsCreated.csv";
        File file = new File(filePath);
        try {
            FileReader fileReader = new FileReader(file);
            CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(1).build();
            int numPostsCreated = Integer.parseInt(csvReader.peek()[0]);
            csvReader.close();
            return numPostsCreated;
        } catch (IOException e) {
            System.out.println("Either wrong path or file has not been formatted correctly. ");
            return 0;
        }
    }

    @Override
    public void setNumberOfPosts(int newNumPostsCreated) {
        String filePath = generalPath + "numPostsCreated.csv";
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

    @Override
    public void savePost(Map<String, String> postAttributes) {
        String filePath = generalPath + "posts.csv";
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

    // methods for sign up use case
    @Override
    public int getNumberUsers() {
        String filePath = generalPath + "numUsersCreated.csv";
        File file = new File(filePath);
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
        String filePath = generalPath + "numUsersCreated.csv";
        File file = new File(filePath);
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
    public List<String> getEmails() {
        return getUserData(2);
    }

    @Override
    public void saveUser(User toSave) {
        String filePath = generalPath + "users.csv";
        String[] toAdd = {convertListToString((ArrayList<Integer>) toSave.getFavourites()),
                convertListToString((ArrayList<Integer>) toSave.getPosts()), Boolean.toString(toSave.isAdmin()),
                String.valueOf(toSave.getId()), toSave.getPassword(), toSave.getEmail()};

        try {
            File file = new File(filePath);
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
            csvReader.close();
        } catch (IOException e) {
            System.out.println("Cannot find file");;
        } catch (CsvException e) {
            System.out.println("Incorrect File Format");
        }

    }

     @Override
     public String getAdminPassword() {
         File file = new File(generalPath + "admin.csv");
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

     // methods for favourite use case
     // methods for favourite use case
     /**
      * Retrieves current user as a User object from the database
      * @param userID the integer ID of the user being retrieved from the database
      * @return the current user as a User object
      */
     @Override
     public User getUser(int userID){
         try {
             // finding and retrieving the current user's data
             List<String[]> allUsers = readAllLines(Paths.get(generalPath+"users.csv"));
             String[] userData = new String[6];
             for (String[] user : allUsers) {
                 try {
                     int id = Integer.parseInt(user[0]);
                     if (id == userID) {
                         userData = user;
                     }
                 } catch (NumberFormatException ex) {
                     System.out.println("Reading the header of the csv");
                 }
             }

             // turning the current user's data into a user object
             return userReader.readUser(userData);
         }catch (IOException e1){
             System.out.println("Error occurred while accessing file");
         }catch (CsvException e2){
             System.out.println("Error occurred while reading file");
         }
         return null;
     }

     /**
      * Retrieves the post being favourited/unfavourited as a Post object from the database
      *
      * @param postID the integer ID of the post being favourited/unfavourited
      * @return a Post object
      */
     @Override
     public Post getPost(int postID){
         try {
             // finding and retrieving the current user's data
             List<String[]> allPosts = readAllLines(Paths.get(generalPath+"posts.csv"));
             String[] postData = new String[10];
             for (String[] post : allPosts) {
                 try {
                     int id = Integer.parseInt(post[0]);
                     if (id == postID) {
                         postData = post;
                     }
                 } catch (NumberFormatException ex) {
                     System.out.println("Reading the header of the csv");
                 }
             }
             return postReader.readPost(postData);
         }catch (IOException e1){
             System.out.println("Error occurred while accessing file");
         }catch (CsvException e2){
             System.out.println("Error occurred while reading file");
         }
         return null;
     }

     /**
      * Reads all the data from a csv file
      * @param path the path of the csv file being read
      * @return all the data from a csv file in a List of String Arrays
      */
     private List<String[]> readAllLines(Path path) throws IOException, CsvException{
         try(Reader reader = Files.newBufferedReader(path)){
             try (CSVReader csvReader = new CSVReader(reader)){
                 return csvReader.readAll();
             }
         }
     }

     /**
      * Rewrites entire csv with the updated User
      * @param updatedUser a String array of the data of the updated user
      * @param userID the integer ID of the updated user
      */
     @Override
     public void saveUserInfo(String[] updatedUser, int userID) {
         try {
             Path usersDB = Paths.get(generalPath+"users.csv");
             // re-reading all user data
             List<String[]> userData = readAllLines(usersDB);

             // identifying index of the user in the old data
             int i = 1; // indexes from 1 because the first line is a header
             boolean lineFound = false;
             while (i < userData.size() && lineFound == false) {
                 int id = Integer.parseInt(userData.get(i)[0]);
                 if (id == userID) {
                     lineFound = true;
                 }
                 i += 1;
             }
             userData.remove(i - 1);
             userData.add(updatedUser);
             writeAllLines(usersDB, userData);
         }catch (IOException e1){
             System.out.println("Error occurred while accessing file");
         }catch (CsvException e2){
             System.out.println("Error occurred while reading file");
         }
     }

     /**
      * Rewrites entire csv with the updated Post
      * @param updatedPost a String array of the data of the updated post
      * @param postID the integer ID of the updated post
      */
     @Override
     public void savePostInfo(String[] updatedPost, int postID){
         try {
             Path postsDB = Paths.get(generalPath+"posts.csv");
             // re-reading all post data
             List<String[]> postData = readAllLines(postsDB);

             // identifying index of the user in the old data
             int i = 1; // indexes from 1 because the first line is a header
             boolean lineFound = false;
             while (i < postData.size() && lineFound == false) {
                 int id = Integer.parseInt(postData.get(i)[0]);
                 if (id == postID) {
                     lineFound = true;
                 }
                 i += 1;
             }
             postData.remove(i - 1);
             postData.add(updatedPost);
             writeAllLines(postsDB, postData);
         }catch (IOException e1){
             System.out.println("Error occurred while accessing file");
         }catch (CsvException e2){
             System.out.println("Error occurred while writing file");
         }

     }

     /**
      * Writes all the data into a csv file
      * @param path the path of the csv file being written into
      */
     private void writeAllLines(Path path, List<String[]> data) throws IOException, CsvException{
         try (CSVWriter writer = new CSVWriter(new FileWriter(path.toString()))){
             writer.writeAll(data);
         }
     }

     // methods for comment use case
     /**
      * @return the number of comments created so far.
      */
     @Override
     public int getNumComments(){
         String filePath = generalPath + "numCommentsCreated.csv";
         File file = new File(filePath);
         try{
             FileReader fileReader = new FileReader(file);
             CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(1).build();
             int numCommentsCreated = Integer.parseInt(csvReader.peek()[0]);
             csvReader.close();
             return numCommentsCreated;


         } catch (IOException e) {
             System.out.println("file not found or incorrect format, comment is not saved");
             return 0;
         }
     }

     /**
      * sets updated number of comments.
      * @param newNumCommentCreated desired number of comments to store.
      */
     @Override
     public void setNumComments(int newNumCommentCreated) {

         File file = fileGetter("numCommentsCreated.csv");

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
             csvReader.close();
         } catch (IOException | CsvException e) {
             System.out.println("file not found or incorrect format, comment is not saved");
         }
     }

     /**
      * saves new comment in to comment database.
      * @param commentAttributes Map representation of new comment.
      */
     @Override
     public void saveComment(Map<String, String> commentAttributes) {
         File file = fileGetter("comments.csv");
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
             csvReader.close();
         } catch (IOException | CsvException e) {
             System.out.println("file not found or incorrect format, comment is not saved");
         }

     }
     /**
      * updates post to contain new comment in its list of replies
      * @param updatedPosts updated post along with all other posts
      */
     @Override
     public void updatePostDatabase(List<String[]> updatedPosts) {
         File file = fileGetter("posts.csv");
         try {
             FileWriter filewriter = new FileWriter(file);
             CSVWriter writer = new CSVWriter(filewriter);
             writer.writeAll(updatedPosts);
             writer.flush();
             writer.close();
         } catch (IOException e) {
             System.out.println("file not found or incorrect format, comment is not saved");
         }


     }

     /**
      *
      * @return Retrieves list of every post
      */
     @Override
     public List<String[]> getCurrentPosts() {

         File file = fileGetter("posts.csv");

         try {
             FileReader fileReader = new FileReader(file);
             CSVReaderBuilder csvReaderBuilder = new CSVReaderBuilder(fileReader);
             CSVReader reader = csvReaderBuilder.build();
             List<String[]> postData = reader.readAll();
             reader.close();
             return postData;


         } catch (FileNotFoundException e) {
             System.out.println("file not found or incorrect format");
             return null;
         } catch (IOException e) {
             System.out.println("Error during accessing file");
             return null;
         } catch (CsvException e) {
             System.out.println("Error during reading file");
             return null;
         }
     }
     /**
      *
      * @return helper that returns a File object.
      */
     private File fileGetter(String fileName){
         String filePath = generalPath + fileName;
         return new File(filePath);
     }




     // Helper methods
    /**
     * This method will return a List of all the values in a csv file
     * @param indexInfo the attribute of users in interest
     * @return a List of a specific attribute of all users in a csv file
     */
    private ArrayList<String> getUserData(int indexInfo){
        String filePath = generalPath + "users.csv";
        try {
            FileReader fileReader = new FileReader(filePath);

            CSVReaderBuilder csvReaderBuilder = new CSVReaderBuilder(fileReader);
            RowValidator rowValidator = new RowMustHaveSameNumberOfColumnsAsFirstRowValidator();
            csvReaderBuilder.withRowValidator(rowValidator);

            CSVReader reader = csvReaderBuilder.build();
            List<String[]> usersInfo = reader.readAll();
            // Remove the row corresponding to the header
            usersInfo.remove(0);

            ArrayList<String> data = new ArrayList<String>();

            for (String[] userInfo: usersInfo){
                data.add(userInfo[indexInfo]);
            }

            return data;
        } catch (IOException e){
            System.out.println("Wrong Path");
            return new ArrayList<String>();
        } catch (CsvException e){
            System.out.println("Line Invalid");
            return new ArrayList<String>();
        }
    }

    private List<String[]> removeEmptyLines(List<String[]> csvBody){
        List<String[]> newCsvBody = new LinkedList<>();
        for(String[] entry : csvBody){
            if(!(entry.length == 1 && entry[0].equals(""))){
                newCsvBody.add(entry);
            }
        }
        return newCsvBody;
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
}


