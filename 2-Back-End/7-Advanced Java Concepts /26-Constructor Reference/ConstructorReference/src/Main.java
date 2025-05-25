import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Student
{
    String Name;
    int Age;
    Student(String Name,int Age)
    {
        this.Name = Name;
        this.Age = Age;
    }

    Student(String Name)
    {
        this.Name = Name;
    }
    public void setName(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public int getAge() {
        return Age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "Name='" + Name + '\'' +
                ", Age=" + Age +
                '}';
    }

}
public class Main {
    public static void main(String[] args)
    {
        List<String> names = Arrays.asList("Youssef","Mostafa","Omar");
        List<Student>Students = new ArrayList<Student>();
        Students = names.stream().map(Student::new).toList();

        System.out.println(Students);
    }
}