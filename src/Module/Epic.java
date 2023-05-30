package Module;

import java.util.ArrayList;

public class Epic extends Task {

    private  ArrayList<Integer> subTaskIds = new ArrayList<>();

    public Epic(String name, String description){
        super(name,  description);
    }

    public ArrayList<Integer> getSubTaskIds() {
        return subTaskIds;
    }

    public void setSubTaskIds(ArrayList<Integer> subTaskIds) {
        this.subTaskIds = subTaskIds;
    }
}
