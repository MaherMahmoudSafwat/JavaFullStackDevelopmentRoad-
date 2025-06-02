package Dealer;

import javax.management.Query;
import java.sql.*;
import java.util.ArrayList;

public class CarSettings extends Car
{
    private static String URL = "jdbc:postgresql://localhost:5432/CarManagementSystemProject";
    private static String User = "postgres";
    private static String Password = "User";
    private static Connection Con;
    public CarSettings()
    {
        try {
            Class.forName("org.postgresql.Driver");
            Con = DriverManager.getConnection(URL,User,Password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void AddNewCar() throws SQLException {
        // Remove CarID from both column list and values
        String Query = "INSERT INTO public.\"CarsData\" (\"CarBrand\", \"CarModel\", \"CarQuantity\", \"CarPrice\") " +
                "VALUES (?, ?, ?, ?)";

        try (PreparedStatement pst = Con.prepareStatement(Query, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, this.getCarBrand());
            pst.setString(2, this.getCarModel());
            pst.setInt(3, this.getCarQuantity());
            pst.setInt(4, this.getCarPrice());
            pst.executeUpdate();
        } // Connection remains open for other operations
    }
    public boolean checkIfCarDataAlreadyExists() {
        String query = "SELECT 1 FROM public.\"CarsData\" WHERE \"CarBrand\" = ? AND \"CarModel\" = ? LIMIT 1";

        try (PreparedStatement pst = Con.prepareStatement(query)) {
            pst.setString(1, this.getCarBrand());  // First parameter (index 1)
            pst.setString(2, this.getCarModel());   // Second parameter (index 2)

            ResultSet rs = null;
            try {
                rs = pst.executeQuery();
                return rs.next();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking car existence", e);
        }
    }
    public void UpdateExistingCar() throws SQLException {
        // Remove CarID from both column list and values
        String Query = "UPDATE public.\"CarsData\" SET \"CarQuantity\"=?, \"CarPrice\"=? WHERE \"CarBrand\" = ? AND \"CarModel\" = ?";

        try (PreparedStatement pst = Con.prepareStatement(Query)) {// Second parameter (index 2)
            pst.setInt(1, this.getCarQuantity());  // First parameter (index 1)
            pst.setInt(2, this.getCarPrice());   // Second parameter (index 2)
            pst.setString(3, this.getCarBrand());  // First parameter (index 1)
            pst.setString(4, this.getCarModel());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error checking car existence", e);
        }
    }
    public void DeleteExistingCar() throws SQLException {
        // Remove CarID from both column list and values
        String Query = "DELETE FROM public.\"CarsData\" WHERE \"CarBrand\" = ? AND \"CarModel\" = ?";

        try (PreparedStatement pst = Con.prepareStatement(Query)) {// Second parameter (index 2)
            pst.setString(1, this.getCarBrand());  // First parameter (index 1)
            pst.setString(2, this.getCarModel());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error checking car existence", e);
        }
    }
    public ArrayList<Car> SearchByBrand(String SearchBy)
    {
        String Query = "SELECT * FROM public.\"CarsData\" WHERE \"CarBrand\" = ?";
        ArrayList<Car>ListOfCars = new ArrayList<Car>();
        try (PreparedStatement pst = Con.prepareStatement(Query)) {// Second parameter (index 2)
            pst.setString(1, SearchBy);  // First parameter (index 1)
            ResultSet Results = pst.executeQuery();
            while(Results.next())
            {
                ListOfCars.add(new Car(Results.getInt(1),Results.getString(2),Results.getString(3),Results.getInt(4),
                        Results.getInt(5)));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking car existence", e);
        }
        return ListOfCars;
    }
    public ArrayList<Car> SearchByModel(String SearchBy)
    {
        String Query = "SELECT * FROM public.\"CarsData\" WHERE \"CarModel\" = ?";
        ArrayList<Car>ListOfCars = new ArrayList<Car>();
        try (PreparedStatement pst = Con.prepareStatement(Query)) {// Second parameter (index 2)
            pst.setString(1, SearchBy);  // First parameter (index 1)
            ResultSet Results = pst.executeQuery();
            while(Results.next())
            {
                ListOfCars.add(new Car(Results.getInt(1),Results.getString(2),Results.getString(3),Results.getInt(4),
                        Results.getInt(5)));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking car existence", e);
        }
        return ListOfCars;
    }
    public ArrayList<Car> SearchByQuantity(int SearchBy)
    {
        String Query = "SELECT * FROM public.\"CarsData\" WHERE \"CarQuantity\" = ?";
        ArrayList<Car>ListOfCars = new ArrayList<Car>();
        try (PreparedStatement pst = Con.prepareStatement(Query)) {// Second parameter (index 2)
            pst.setInt(1, SearchBy);  // First parameter (index 1)
            ResultSet Results = pst.executeQuery();
            while(Results.next())
            {
                ListOfCars.add(new Car(Results.getInt(1),Results.getString(2),Results.getString(3),Results.getInt(4),
                        Results.getInt(5)));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking car existence", e);
        }
        return ListOfCars;
    }
    public ArrayList<Car> SearchByPrice(int SearchBy)
    {
        String Query = "SELECT * FROM public.\"CarsData\" WHERE \"CarPrice\" = ?";
        ArrayList<Car>ListOfCars = new ArrayList<Car>();
        try (PreparedStatement pst = Con.prepareStatement(Query)) {// Second parameter (index 2)
            pst.setInt(1, SearchBy);  // First parameter (index 1)
            ResultSet Results = pst.executeQuery();
            while(Results.next())
            {
                ListOfCars.add(new Car(Results.getInt(1),Results.getString(2),Results.getString(3),Results.getInt(4),
                        Results.getInt(5)));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking car existence", e);
        }
        return ListOfCars;
    }

    public ArrayList<Car> GetAllCars()
    {
        String Query = "SELECT * FROM public.\"CarsData\" ORDER BY \"CarPrice\" DESC , \"CarQuantity\" ASC";
        ArrayList<Car>ListOfCars = new ArrayList<Car>();
        try (PreparedStatement pst = Con.prepareStatement(Query)) {// Second parameter (index 2)
            ResultSet Results = pst.executeQuery();
            while(Results.next())
            {
                ListOfCars.add(new Car(Results.getInt(1),Results.getString(2),Results.getString(3),Results.getInt(4),
                        Results.getInt(5)));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking car existence", e);
        }
        return ListOfCars;
    }
}
