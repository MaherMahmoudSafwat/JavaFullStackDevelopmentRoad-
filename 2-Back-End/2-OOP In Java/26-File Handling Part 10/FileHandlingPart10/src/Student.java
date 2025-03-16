import java.io.Serializable;

public class Student implements Serializable
{
    String Name;
    int ID;
    int Grade;
    Student(String Name, int ID, int Grade)
    {
        this.Name=Name;
        this.ID = ID;
        this.Grade = Grade;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getGrade() {
        return Grade;
    }

    public void setGrade(int grade) {
        Grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "Name='" + Name + '\'' +
                ", ID=" + ID +
                ", Grade=" + Grade +
                '}';
    }
}


