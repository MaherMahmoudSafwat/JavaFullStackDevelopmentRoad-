package Dealer;

public class Car
{
    private int CarID;
    private String CarBrand;
    private String CarModel;
    private int CarQuantity;
    private int CarPrice;

    public Car(int CarID,String CarBrand,String CarModel,int CarQuantity, int CarPrice)
    {
        this.CarID = CarID;
        this.CarBrand = CarBrand;
        this.CarModel = CarModel;
        this.CarQuantity = CarQuantity;
        this.CarPrice = CarPrice;
    }
    public Car() {}

    public int getCarID() {
        return CarID;
    }

    public void setCarID(int carID) {
        CarID = carID;
    }

    public String getCarBrand() {
        return CarBrand;
    }

    public void setCarBrand(String carBrand) {
        CarBrand = carBrand;
    }

    public String getCarModel() {
        return CarModel;
    }

    public void setCarModel(String carModel) {
        CarModel = carModel;
    }

    public int getCarQuantity() {
        return CarQuantity;
    }

    public void setCarQuantity(int carQuantity) throws InvalidQuantityException {
        if(carQuantity < 1)
        {
            throw new InvalidQuantityException("The quantity can't be negative or zero.");
        }
        CarQuantity = carQuantity;
    }

    public int getCarPrice() {
        return CarPrice;
    }

    public void setCarPrice(int carPrice) throws InvalidPriceException {
        if(carPrice < 0)
        {
            throw new InvalidPriceException("The price can't be negative.");
        }
        CarPrice = carPrice;
    }
}
