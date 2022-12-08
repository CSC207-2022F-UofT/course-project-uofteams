# UofTeams

UofTeams allows UofT students to create accounts with the purpose of creating time-sensitive posts to advertise extracurricular opportunities. The user may search for other posts by filtering by user or specific tags. A user also has the option of commenting on or favouriting a post if they’re interested in an opportunity. Users should have the option to edit and delete their own posts, respond to posts and comments, and view users who have “favourited” their own posts. Certain users are designated as “administrator accounts” which help moderate the forums.

## Dependencies
- JUnit (4.13.1)
- OpenCSV (5.7.1)

## Getting Started
1. Clone this repository into IntelliJ.
2. Mark `src/main/java` as "Sources Root" and `src/test/java` as "Test Sources Root".
3. Ensure the `build.gradle` includes the previously listed dependencies.
4. Open `src/main/java/UofTeams.java` to use the app! Compile and run.

## Addressing Milestone 4 Feedback
### Changes Since Milestone 4
- More use of GitHub features (e.g. issues and linking them to pull requests).
- More testing & documentation.

### Use of Design Patterns
We chose to use the Observer pattern instead of another design pattern when implementing our program because when one part of our program changes, we want other connected parts to automatically be updated as well (e.g. you can filter posts which must reflect the available posts that you can click on in the lists of posts). This also allows us to have this subject-observer relationship between UI and interface adapter components without hardcoding any dependencies.

### Explanation of Code Structure
We have our individual use case folders in the same level as our entities folder because we want all our use cases to be able to manipulate the entities without having to redefine multiple of the same entity across all use cases.
