import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class SimulationDiver {
    public static void main(String[] args) {
        Random rand = new Random();
        //store created students in a set so the voting service "harder" to manipulate all the student answer
        //as there is no public method to get the names of ID in votingService
        Set<String> studentsCreated = new HashSet<>();
        //created to automatically test all methods created everywhere else
        VotingServices test = new VotingServices();
        for(int i=0;i<100;i++)
        {
            test.addStudent(String.valueOf(i));
            studentsCreated.add(String.valueOf(i));
        }
        //test if ID validation is working
        test.removeStudent("101");
        test.addStudent("1");
        System.out.println();
        //create a single choice question
        Questions Q1 = new singleChoiceQuestion();
        Q1.changeQuestion("What is the color of a banana?");
        Q1.addAnswers("yellow");
        Q1.addAnswers("red","blue");
        test.addQuestion(Q1.copy());

        //create a multiple choice question
        Questions Q2 = new multipleChoiceQuestion();
        Q2.changeQuestion("What does PEAS in Artificial Intelligence stand for?");
        Q2.addAnswers("Performance","Environment");
        Q2.addAnswers("Actuators","Sensors");
        test.addQuestion(Q2.copy());

        //reuse the Q1 to make a new question and show that passing a copy is important
        Q1 = new singleChoiceQuestion();
        Q1.changeQuestion("Is this assignment#1?");
        Q1.addAnswers("Yes","No");
        test.addQuestion(0,Q1.copy());
        //all questions are created so push to all students
        test.pushQuestionToAllStudents();

        //automatic simulation of 100 students
        for (int i=0;i< test.questionSize();i++)
        {
            int answerSize=test.getQuestion(i).getAnswers().size();
            //use stored
            for (String s:studentsCreated)
            {
                for(int j=0; j< rand.nextInt(answerSize)+1;j++) {
                    test.addStudentAnswer(s, i, rand.nextInt(answerSize));
                }
            }
        }
        //get questions statistics for all Questions
        for(int i=0;i<test.questionSize();i++)
        {
            System.out.printf("%d:%s\n",i+1,test.getQuestion(i));
            System.out.println(test.questionStatics(i));
        }
        //show that it is available for user input and not just automatic testing
        System.out.print("Do you want to try to answer the questions? (Y/N) ");
        Scanner scan = new Scanner(System.in);
        String input= scan.nextLine();
        if(input.equals("Y")||input.equals("y")) {
            userStudent(test);
        }
    }
    public static void userStudent(VotingServices test) {
        Scanner scan = new Scanner(System.in);
        String name;
        int condition;
        //won't let you input a student that already exists
        do {
            System.out.print("\nWhat is your name? ");
            name = scan.nextLine();
            System.out.println();
            condition = test.addStudent(name);
        } while (condition < 0);

        test.pushQuestionToOneStudent(name);
        System.out.println("When giving answers put one space in between to denote multiple choices.");
        System.out.println("If the question only takes one choice the last choice given will be stored\n");
        //go through each question
        for (int i = 0; i < test.questionSize(); i++) {
            System.out.printf("%d:%s\n", i + 1, test.getQuestion(i));
            System.out.print("Answer:");
            String temp = scan.nextLine();
            String[] ans = temp.strip().split(" ");
            for (String to : ans) {
                test.addStudentAnswer(name, i, Integer.parseInt(to));
            }
            System.out.println(test.questionStatics(i));
        }
        System.out.println("Your Answers");
        System.out.println(test.getStudentAnswer(name));
    }
}
