import java.util.*;

public class VotingServices {
    //Store the questions created for the test
    private List<Questions> questions;
    //Dictionary is the best way to store student data as not only does its implementation create unique ID it allows
    //for constant time access to all students
    private Map<String,Student> studentPresent;
    //constructor method
    public VotingServices()
    {
        questions=new ArrayList();
        studentPresent = new HashMap<>();
    }
    //setter method for student will also validate if the addition went through
    public int addStudent(String id)
    {
        if(studentPresent.containsKey(id))
        {
            System.out.printf("%s is already a student.\n",id);
            return -1;
        }
        else
        {
            studentPresent.put(id, new Student(id));
            this.pushQuestionToOneStudent(id);
            return 0;
        }
    }
    //remove method for student will validate if a student with that ID exists
    public void removeStudent(String id)
    {
        if(studentPresent.containsKey(id))
        {
            studentPresent.remove(id);
        }
        else
        {
            System.out.printf("%s is not a current student.\n",id);
        }
    }
    //setter method to add question at any point
    public void addQuestion(int num,Questions input)
    {
        questions.add(num,input);
    }
    //setter method for question to append to end
    public void addQuestion(Questions input)
    {
       questions.add(input);
    }
    //getter method for question
    public Questions getQuestion(int num)
    {
        return questions.get(num);
    }
    //once all your questions are ready push it to all students
    public void pushQuestionToAllStudents()
    {
        for (String key:studentPresent.keySet()) {
            studentPresent.get(key).addQuestions(questions);
        }
    }
    //push all question to one specific student
    public void pushQuestionToOneStudent(String ID)
    {
        studentPresent.get(ID).addQuestions(questions);

    }
    //setter method for student
    public void addStudentAnswer(String ID,int num,int ans)
    {
        studentPresent.get(ID).addStudentAnswer(num,ans);
    }
    //getter method for student
    public String getStudentAnswer(String id)
    {
        return studentPresent.get(id).toString();
    }
    //return the amount of questions created
    public int questionSize()
    {
        return questions.size();
    }
    //compile statistics for the questions
    public String questionStatics(int num)
    {
        Map<String,Integer> statistics = new HashMap<>();
        for(String ans: questions.get(num).getAnswers())
        {
            statistics.put(ans,0);
        }
        for(String key:studentPresent.keySet())
        {
            String[] temp = studentPresent.get(key).getQuestion(num).getChoice();
            for(int i=0;i<temp.length;i++ )
            {
                statistics.put(temp[i],statistics.get(temp[i])+1);
            }

        }
        String temp= String.format("Statistics for question %d\n\t ",num+1);
        return temp+statistics.toString();
    }

}
