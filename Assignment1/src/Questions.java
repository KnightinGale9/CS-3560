import java.util.List;
public interface Questions {
    //setter method for the question
    public void changeQuestion(String quest);
    //getter method for the question
    public String getQuestion();
    //setter method for the answers
    public void addAnswers(String... input);
    //getter method for the answers
    public List<String> getAnswers();
    //setter method for the student choice
    public void addChoice(int input);
    //getter method for the student choice
    public String[] getChoice();
    //copy method for Student implementation later as without copy method all students would link to the same question
    public Questions copy();
    //toString to print out the question in a viewable format
    public String toString();
}
