import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.List;

public class UniqueIDVisitor implements Visitor{
    Set<String> valid;
    List<String> invalid;
    //Constructor
    public UniqueIDVisitor()
    {
        valid = new HashSet<>();
        invalid = new LinkedList<>();
    }
    //Method to find all Users through recursion
    @Override
    public void visit(UserComposite node) {
        if(node.getChildCount()==0)
        {
            if(valid.contains(node.toString()))
            {
                invalid.add(node.toString());
            }
            else
            {
                valid.add(node.toString());
            }
            if((node.toString()).contains(" ")) {
                invalid.add((node.toString()));
            }
        }
        else{
            for (int i=0;i<node.getChildCount();i++)
            {
                visit((UserComposite) node.getChildAt(i));
            }
            if(valid.contains(node.toString()))
            {
                invalid.add(node.toString());
            }
            else
            {
                valid.add(node.toString());
            }
            if((node.toString()).contains(" ")) {
                invalid.add((node.toString()));
            }

        }
    }
    @Override
    public Object visitorValue() {
        String temp="";
        for (String num:invalid) {
            temp += num;
            temp += ",";
        }
        temp =temp.substring(0,temp.length()-1);
        return temp;
    }

}
