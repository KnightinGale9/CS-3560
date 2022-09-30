import java.util.*;

public class Student {
    private String id;
    //the storage for questions in answers
    //ArrayList is the best implementation as it allows for O(1) time access of questions and to easily change the order
    // and implement new questions in the middle of the test
    private List<Questions> studentAnswers;
    //constructor method
    public Student(String identity)
    {
        id=identity;
        studentAnswers= new ArrayList<>();
    }
    //setter method for questions by adding into arraylist
    public void addQuestions(List<Questions> quest)
    {
        for(int i=0;i<quest.size();i++)
        {
            studentAnswers.add(quest.get(i).copy());
        }
    }
    //getter method for questions
    public Questions getQuestion(int num)
    {
        return studentAnswers.get(num);
    }
    //setter method for student choice
    public void addStudentAnswer(int number, int input)
    {
        studentAnswers.get(number).addChoice(input);

    }
    //toString method to show the student progress in test
    public String toString()
    {
        String result=id+"\n";
        for (int i=0;i<studentAnswers.size();i++) {
            result= result + i+1 + " : " + studentAnswers.get(i).toString();
        }
        return result;
    }
}
