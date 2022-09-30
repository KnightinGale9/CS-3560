import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class multipleChoiceQuestion implements Questions {
    private String question;
    private List<String> answer;
    //allow for multiple answer question and to not have redundant answers based on automatic implementation
    private Set<String> choice;

    //Constructors for the classes based
    public multipleChoiceQuestion(){
        question="";
        answer= new ArrayList<>();
        choice= new HashSet<>();
    }
    //setter method for question
    @Override
    public void changeQuestion(String quest) {
        question=quest;
    }
    //getter method for questions
    @Override
    public String getQuestion() {
        return question;
    }
    //setter method for question which allow for multiple answer inputs at the same time
    @Override
    public void addAnswers(String... input) {
        for (String ans:input){
            answer.add(ans);
        }
    }
    //getter method for answers
    @Override
    public List<String> getAnswers() {
        return answer;
    }
    //setter method for choice
    @Override
    public void addChoice(int input)
    {
        choice.add(answer.get(input));
    }
    //getter method for choice
    public String[] getChoice() {
        return choice.toArray(new String[0]);
    }
    //copy method for Student implementation later as without copy method all students would link to the same question
    @Override
    public Questions copy()
    {
        Questions newQuestion = new multipleChoiceQuestion();
        newQuestion.changeQuestion(this.question);
        for (String ans:answer)
        {
            newQuestion.addAnswers(ans);
        }
        return newQuestion;
    }
    //to String method that prints the question and answers and choices if available
    @Override
    public String toString(){
        StringBuilder temp = new StringBuilder(question).append("(Choose one or more)");
        for (int i=0;i<answer.size();i++) {
            temp.append("\n\t").append(i).append(":").append(answer.get(i));
        }
        if(choice.size()>0) {
            temp.append("\nAnswers : ");
            for (String c : choice) {
                temp.append("\n\t").append(c);
            }
        }
        return temp.toString();
    }
}
