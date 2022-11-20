package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * Comment extends abstract class Postable and inherits all of its
 * instance attributes (user, body, replies) and addComment method.
 *
 */
public class Comment extends Postable{

    /**
     * Initializes an instance of Comment.
     *
     * @param commenterID The user that posted this comment.
     * @param body The text content of the comment.
     * @param id The unique id for this comment
     */
    public Comment(int commenterID, String body, int id){
        super.userID = commenterID;
        super.body = body;
        super.creationDate = LocalDate.now();
        super.id = id;
    }

    public Comment(int commenterID, String body, int id, String creationDate){
        super.userID = commenterID;
        super.body = body;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Locale locale = new Locale("en", "ca");
        formatter = formatter.withLocale(locale);  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
        super.creationDate = LocalDate.parse(creationDate, formatter);
        super.id = id;
    }

    /**
     * Return if Comment is equal to o
     *
     * @param o The Object to be compared to
     * @return boolean representing whether Comment is equal to o
     * */
    @Override
    public boolean equals (Object o) {
        if (o == null) {
            return false;
        } else if (!(o instanceof Comment)) {
            return false;
        }  else {
            return (this.id == ((Comment) o).id);
        }
    }
}
