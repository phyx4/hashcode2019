import java.util.ArrayList;

public class Photo {
    private String orientation;
    private ArrayList<String> tags;
    private int id;

    public Photo(String orientation, ArrayList<String> tags, int id) {
        this.orientation = orientation;
        this.tags = tags;
        this.id = id;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
