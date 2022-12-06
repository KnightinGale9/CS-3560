/*
User Group is the Composite that Groups users together
 */
import java.util.HashSet;
import java.util.Set;

public class UserGroup extends UserComposite {
    private String name;
    private long creationTime;
    private Set<String> uniqueNames;
    //Constructor
    public UserGroup(String name)
    {
        super.allowsChildren=true;
        super.setUserObject(this);
        this.name=name;
        this.creationTime=System.currentTimeMillis();
        System.out.println(String.format("Creation of UserGroup named %s at %s",name,creationTime));
        this.uniqueNames = new HashSet<>();
    }
    //Check if the UserGroup has the name already
    public boolean containUniqueNames(String name) {
        return uniqueNames.contains(name);
    }
    //add new names that are in this group
    public void addUniqueNames(String name) {
        this.uniqueNames.add(name);
    }

    //Entry point for visitor
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    @Override
    public long getCreationTime()
    {
        return creationTime;
    }
    //ToString Method
    @Override
    public String toString() {
        return name;
    }
}
