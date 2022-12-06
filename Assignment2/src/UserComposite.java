/*
User Composite is the interface for User and is visitable
so the visitor can look for Users and UserGroups to get statistics
 */
import javax.swing.tree.*;

public abstract class UserComposite extends DefaultMutableTreeNode implements Visitable {
    public long getCreationTime(){
        return 0;
    }

    public String toString() {
        return null;
    }
}
