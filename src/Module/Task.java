package Module;

public class Task {
    protected String name;
    protected String description;
    protected Integer id;
    protected Status status;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Task( String name, String description, Status status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    @Override
    public String toString() {
        String result = "Task{" +
                "id=" + id +
                ", name=" + name + '\'' +
                ", status=" + status +
                ", description=" + description;
        return result + "}";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}