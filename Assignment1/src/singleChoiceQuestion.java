import java.util.ArrayList;
import java.util.List;

public class singleChoiceQuestion implements Questions {
    private String question;
    private List<String> answer;
    //create a question with only one possible choice
    private String choice;
    //constructor method
    public singleChoiceQuestion(){
        question="";
        answer= new ArrayList<>();
        choice= "";
    }
    //setter method for question
    @Override
    public void changeQuestion(String quest) {
        question=quest;
    }
    //getter method for question
    @Override
    public String getQuestion() {
        return question;
    }
    //setter method for answer
    @Override
    public void addAnswers(String... input) {
        for (String ans:input){
            answer.add(ans);
        }
    }
    //getter method for answer
    @Override
    public List<String> getAnswers() {
        return answer;
    }
    //setter method for choice
    public void addChoice(int input)
    {
        choice=answer.get(input);
    }
    //getter method for answer
    public String[] getChoice()
    {
        return new String[]{choice};
    }
    //copy method
    public Questions copy()
    {
        Questions newQuestion = new singleChoiceQuestion();
        newQuestion.changeQuestion(this.question);
        for (String ans:answer)
        {
            newQuestion.addAnswers(ans);
        }
        return newQuestion;
    }
    //to String method that prints the question and answers and choices if available
    @Override
    public String toString() {
        StringBuilder temp = new StringBuilder(question).append("(Choose one)");
        for (int i=0;i<answer.size();i++) {
            temp.append("\n\t").append(i).append(":").append(answer.get(i));
        }
        if (choice != "") {
            temp.append("\nAnswers : \n\t").append(choice).append("\n");
        }
        return temp.toString();
    }
}
