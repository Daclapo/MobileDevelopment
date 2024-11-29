package dm2e.davidclarkson.basededatos;

public class Registro {
    private int id;
    private String name;
    private int age;
    private String email;
    private String date;
    private String note;

    public Registro(int id, String name, int age, String email, String date, String note) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.date = date;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getDate() {
        return date;
    }

    public String getNote() {
        return note;
    }
}
