package cartester;

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

/**
 *
 * @author kyleluka
 */
public class Car {

    public static String carMake, carModel, condition, year, mileage, price, input;
    public static int window = 1;
    public static ImageIcon icon = new ImageIcon("src/assets/Car_1.png");

    /**
     *
     */
    public static void makeCar() {  //store car object make, model, year, price, mileage, condition, and check for proper inputs
        switch (window) {

            case 1:
                carMake = (String) JOptionPane.showInputDialog(null, "What make is this vehicle?", "", JOptionPane.DEFAULT_OPTION, icon, null, null);
                input = carMake;
                check();
                break;

            case 2:
                carModel = (String) JOptionPane.showInputDialog(null, "What model is this vehicle?", "", JOptionPane.DEFAULT_OPTION, icon, null, null);
                input = carModel;
                check();
                break;

            case 3:
                year = (String) JOptionPane.showInputDialog(null, "What is the year of this vehicle?", "", JOptionPane.DEFAULT_OPTION, icon, null, null);
                input = year;
                check();
                break;

            case 4:
                price = (String) JOptionPane.showInputDialog(null, "How much would you like to sell this vehicle for?", "", JOptionPane.DEFAULT_OPTION, icon, null, null);
                input = price;
                check();
                break;

            case 5:
                mileage = (String) JOptionPane.showInputDialog(null, "What is the mileage on the vehicle?", "", JOptionPane.DEFAULT_OPTION, icon, null, null);
                input = mileage;
                check();
                break;

            case 6:
                condition = (String) JOptionPane.showInputDialog(null, "What condition is this vehicle in?", "", JOptionPane.DEFAULT_OPTION, icon, null, null);
                input = condition;
                check();
                window = 1;
                break;
        }

    }

    public static void check() {    //method to check proper inputs

        if (input == null) {    //terminate program on exit
            System.exit(0);
        } else if (isNullOrWhitespace(input)) {     //force input
            makeCar();
        } else {    //next input
            window++;
            makeCar();
        }
    }

    public static boolean isNullOrWhitespace(String input) { //check for proper inputs
        if (input == null) {
            return true;
        }

        for (int i = 0; i < input.length(); i++) {
            if (!Character.isWhitespace(input.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public Car() {  //create car object

        makeCar();

    }

    public String toString() {  //car object to string

        String output = "Make: " + carMake + "\n";
        output += "Model: " + carModel + "\n";
        output += "Year: " + year + "\n";
        output += "Price: $" + price + "\n";
        output += "Mileage: " + mileage + " km\n";
        output += "Condition: " + condition + "\n";
        return output;

    }

}
