package epgguide.infosys.optimumguide;

/**
 * Created by Sathishkumar.P07 on 7/20/2016.
 */
public class Program {


    private String channel, title,description;
    private boolean isNew;

    public Program() {
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
