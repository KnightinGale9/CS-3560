/*
User Class which is part of the Composite Pattern.
Holds all the info important to User
 */
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

public class User extends UserComposite implements Observer, Subject {

    private String name;
    private String latestMessage;

    private long creationTime;
    private long latestUpdateTime;

    private Set<Observer> followers;
    private Set<User> following;
    private List<String> timeline;
    private List<String> myTweets;


    //Constructor
    public User(String ID)
    {
        super.userObject=this;
        super.allowsChildren =false;
        this.name=ID;
        this.creationTime=System.currentTimeMillis();
        this.latestUpdateTime=System.currentTimeMillis();
        followers=new HashSet<>();
        following=new HashSet<>();
        timeline=new ArrayList<>();
        myTweets=new ArrayList<>();
    }
    public String getName()
    {
        return name;
    }
    //returns the myTweet array for the visitors to use
    public List<String> getTimeline()
    {
        return timeline;
    }
    public List<String> getMyTweets()
    {
        return myTweets;
    }
    public Set<User> getFollowing()
    {
        return following;
    }
    //returns the latestTweet for the observers
    public String getLatestMessage() {
        return latestMessage;
    }
    //A new tweet that notify all observers
    public void setLatestMessage(String latestMessage) {
        this.latestUpdateTime=System.currentTimeMillis();
        this.latestMessage = String.format("%s : %s",this.toString(),latestMessage);
        this.timeline.add(this.latestMessage);
        this.myTweets.add(this.latestMessage);
        this.notifyObservers();
    }
    public long getCreationTime() {
        return creationTime;
    }

    public long getLatestUpdateTime() {
        return latestUpdateTime;
    }
    //A Set of people you follow in order to not put duplicates in the tree view in UserFrame
    public void setFollowing(User user)
    {
        this.following.add(user);
        user.attach(this);
    }
    //A check to see if we already follow the user
    public boolean amFollowing(User test)
    {
        return following.contains(test);
    }

    //Methods implemented by Subject
    @Override
    public void attach(Observer observer)
    {
        this.followers.add(observer);
    }
    @Override
    public void detach(Observer observer)
    {
        this.followers.remove(observer);
    }
    @Override
    public void notifyObservers()
    {
        for(Observer ob: followers)
        {
            ob.update(this);
        }
    }
    //Entry point for Visitor
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void update(User user) {
        timeline.add(user.getLatestMessage());
    }

    //toString method
    @Override
    public String toString()
    {
        return name;
    }
}
