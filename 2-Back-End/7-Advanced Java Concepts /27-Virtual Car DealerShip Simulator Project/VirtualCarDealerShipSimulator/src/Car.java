import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Car {
    private int ManufacturingYear;
    private String Brand;
    private String Model;
    private String Color;
    private int ID;
    private int Price;

    private String AddDataToFile(Car car) {
        String Str = "";
        Str += String.valueOf(car.ManufacturingYear) + "-";
        Str += car.Model + "-";
        Str += car.Brand + "-";
        Str += car.Color + "-";
        Str += String.valueOf(car.ID) + "-";
        Str += String.valueOf(car.Price);
        return Str;
    }

    private Car getDataFromFile(String Data) throws IOException {
        String[] CarsData = Data.split("-");
        return new Car(Integer.parseInt(CarsData[0]), CarsData[1], CarsData[2], CarsData[3],
                Integer.parseInt(CarsData[4]), Integer.parseInt(CarsData[5]));
    }

    public Car(int ManufacturingYear, String Brand, String Model, String Color, int ID, int Price) throws IOException {
        this.ManufacturingYear = ManufacturingYear;
        this.Brand = Brand;
        this.Model = Model;
        this.Color = Color;
        this.ID = ID;
        this.Price = Price;
    }

    public Car() throws IOException {}

    public int getManufacturingYear() {
        return ManufacturingYear;
    }

    public void setManufacturingYear(int manufacturingYear) {
        ManufacturingYear = manufacturingYear;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public void AddCar() throws IOException {
        try (FileWriter F = new FileWriter("Car.txt", true);
             PrintWriter Writer = new PrintWriter(F, true)) {
            Writer.println(AddDataToFile(this));
        }
    }

    public ArrayList<Car> GetCarData() throws IOException {
        ArrayList<Car> ListOfCars = new ArrayList<Car>();
        File file = new File("Car.txt");
        try (Scanner Scan = new Scanner(file)) {
            while (Scan.hasNextLine()) {
                ListOfCars.add(getDataFromFile(Scan.nextLine()));
            }
        }
        return ListOfCars;
    }

    public boolean IsCarID_AlreadyExists(int ID) throws IOException {
        ArrayList<Car> ListOfCars = GetCarData();
        return ListOfCars.stream().anyMatch(c -> c.ID == ID);
    }
}