import java.util.HashMap;
import java.util.List;

public class latestUpdateUserVisitor implements Visitor{
    long latestMessage;
    User latestUser;
    //Constructor
    public latestUpdateUserVisitor()
    {
        latestMessage=0;
    }
    //Method to find all Users through recursion
    @Override
    public void visit(UserComposite node) {
        if(node.getChildCount()==0)
        {
            if(node instanceof User)
            {
                if(((User) node).getLatestUpdateTime()>latestMessage)
                {
                    latestMessage=((User) node).getLatestUpdateTime();
                    latestUser= (User) node;
                }
            }
        }
        else{
            for (int i=0;i<node.getChildCount();i++)
            {
                visit((UserComposite) node.getChildAt(i));
            }
        }
    }

    @Override
    public Object visitorValue() {
        return latestUser.getName();
    }

}
