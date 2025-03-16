import java.io.*;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Scanner Input = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        while(true)
        {
            System.out.println("Choose one of the following: ");
            System.out.println("1-Add on the notebook.");
            System.out.println("2-Delete the notebook");
            System.out.println("3-Update the notebook");
            System.out.println("4-Read the notebook.");
            String S = Input.nextLine();
            UserNoteBookChoice(S);
        }
    }
    public static void UserNoteBookChoice(String S1) throws IOException {
        switch(NoteBookStatus.valueOf(S1.toUpperCase()))
        {
            case NoteBookStatus.ADD:
                AddOnNoteBook();
                break;
            case NoteBookStatus.DELETE:
                DeleteNoteBook();
                break;
            case NoteBookStatus.UPDATE:
                UpdateNoteBook();
                break;
            case NoteBookStatus.READ:
                ReadNoteBook();
                break;
        }
    }
    public static void AddOnNoteBook() throws IOException {
        System.out.println("Enter what you want to add on your notebook :-");
        String S1 = Input.nextLine();
        File F = new File("DailyNoteBook.txt");
        FileWriter FW = new FileWriter(F);
        PrintWriter PW = new PrintWriter(FW);
        PW.println(S1);
        PW.flush();
        FW.close();
        PW.close();
        Input.nextLine();
        System.out.println("Your notes has been added successfully.");
    }
    public static void DeleteNoteBook()
    {
        File F = new File("DailyNoteBook.txt");
        F.delete();
        System.out.println("Your DailyNoteBook has been deleted Successfully.");
    }
    public static void UpdateNoteBook() throws IOException {
        File F = new File("DailyNoteBook.txt");
        FileWriter FW = new FileWriter(F,true);
        PrintWriter PW = new PrintWriter(FW);
        System.out.println("Enter what you want to add on the notebook.");
        String S1 = Input.nextLine();
        PW.print(S1);
        PW.flush();
        FW.close();
        PW.close();
        Input.nextLine();
        System.out.println("Your notes has been Updated Successfully.");
    }
    public static void ReadNoteBook() throws FileNotFoundException {
        File F = new File("DailyNoteBook.txt");
        Scanner Scan = new Scanner(F);
        while(Scan.hasNextLine())
        {
            System.out.println(Scan.nextLine());
        }
        Scan.close();
    }
}