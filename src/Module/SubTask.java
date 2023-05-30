package Module;

public class SubTask extends Task {

    private int epicId;

    public SubTask(String name, String description,  int epicId){
        super(name, description);
        this.epicId = epicId;
    }
    public SubTask(String name, String description, int epicId, Status status){
        super(name, description, status);
        this.epicId = epicId;
    }

    public int getEpicId(){
        return epicId;
    }

}
