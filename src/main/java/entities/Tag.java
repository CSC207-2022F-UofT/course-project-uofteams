package entities;

import java.util.ArrayList;

public class Tag {
    // The name of the tag
    final String name;

    // Array of posts that are under this tag
    final ArrayList<Post> taggedPosts;

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
     * Add a post that has this tag.
     *
     * @param post The post that has this name as a tag.
     */
    public void addTag(Post post) {
        taggedPosts.add(post);
    }

    /**
     * Returns whether the post that has this tag was successfully removed.
     */
    public boolean removeTag(Post post) { return taggedPosts.remove(post); }

    /**
     * Returns the name of this tag.
     */
    public String getName() {
        return this.name;
    }
}