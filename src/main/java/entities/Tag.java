package entities;

import java.util.ArrayList;
import java.util.List;

public class Tag {
    // The name of the tag
    private final String name;

    // Array of posts that are under this tag
    private final List<Post> taggedPosts;

    /**
     * Initializes an instance of Tag.
     *
     * @param name The name of this tag.
     */
    public Tag(String name) {
        this.name = name;
        this.taggedPosts = new ArrayList<>();
    }

    /**
     * Returns whether the post was successfully added to this tag.
     *
     * Adds the given post to this tag, if not already in the list.
     *
     * @param post The post that has this name as a tag.
     */
    public boolean addPostToTag(Post post) {
        if (!taggedPosts.contains(post)) {
            taggedPosts.add(post);
            return true;
        }
        return false;
    }

    /**
     * Returns whether the post that has this tag was successfully removed.
     *
     * Removes the post from this tag, if possible.
     */
    public boolean removePostFromTag(Post post) {
        for (Post p: taggedPosts) {
            if (p.equals(post)) {
                return taggedPosts.remove(post);
            }
        }
        return false;
    }

    /**
     * Returns the name of this tag.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the list of posts that has this tag.
     */
    public List<Post> getTaggedPosts() { return this.taggedPosts; }
}