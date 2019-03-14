/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartester;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

/**
 *
 * @author kyleluka
 */
public class CarTester {

    public static ArrayList<Car> cars = new ArrayList<>();  //store information of various cars
    public static ArrayList<String> outs = new ArrayList<>();   //store output information for final window
    public static Car car;
    public static String out, path;
    public static int carNum = 1;
    public static ImageIcon icon2 = new ImageIcon("src/assets/Car_1.png");
    public static boolean on = true;
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //find screensize
    public final static int WIDTH = (int) screenSize.getWidth();
    public final static int HEIGHT = (int) screenSize.getHeight();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {    //add cars and display last window
        // TODO code application logic here
        while (on != false) {
            newCar();
        }
        lastWindow();
    }

    public static void honk() {     //honk method
        JOptionPane.showMessageDialog(null, "HONK!", "HONK", JOptionPane.DEFAULT_OPTION, icon2);
    }

    public static void lastWindow() {

        KeyListener listener = new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                honk();
            }    //when key pressed in last window, honk.

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        };

        out = "";

        ImageIcon icon = new ImageIcon("src/assets/Car.png");

        JFrame output = new JFrame("Cars");
        output.setResizable(false);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(null);

        for (int i = 0; i < outs.size(); i++) {
            out += outs.get(i);
        }

        JOptionPane last = new JOptionPane(out + "\nPress any key to honk your horn!" + "\nSaved to " + path, JOptionPane.INFORMATION_MESSAGE);   //Final window text
        last.setIcon(icon);
        last.createDialog("");
        writeBufferedWriter(out);

        //Finalize last window
        Dimension d = new Dimension(last.getWidth() + 20, last.getHeight() - 15);
        contentPane.setBackground(last.getBackground());
        contentPane.add(last);
        contentPane.setSize(d);
        output.setSize(d);
        output.addKeyListener(listener);
        output.setFocusable(true);
        output.setContentPane(contentPane);
        Point p = new Point(((WIDTH / 2) - ((last.getWidth() + 20) / 2)), ((HEIGHT / 2) - (last.getHeight() / 2)));
        output.setLocation(p); //set middle of screen
        output.setVisible(true);
        output.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    //create new car from car object, add car to cars arraylist, and add output information of car to outs arraylist
    public static void newCar() {
        car = new Car();

        //check if car information is correct, if not then repeat creating car 
        int k = JOptionPane.showConfirmDialog(null, "Is this correct for car #" + carNum + "?\n" + car.toString(), "Confirmation", 0, JOptionPane.DEFAULT_OPTION, icon2);
        if (k == 0) {
            cars.add(car);
            outs.add("Car " + carNum + ":\n\n" + cars.get(carNum - 1) + "\n");

            //check to add new car or display final window
            int n = JOptionPane.showOptionDialog(null, "Would you like to add another car?", "Another Car?", 0, JOptionPane.YES_NO_OPTION, icon2, null, null);
            //if you choose yes (YES = 0) then continue the loop and increase carNum, or else end loop and display last window
            if (n == JOptionPane.YES_OPTION) {
                carNum++;
            } else {
                savePath();
            }
        }
    }

    public static void savePath() {
        path = JOptionPane.showInputDialog("Please indicate where you would like to save your car information.\n"
                + "If the field is left blank, all information will be saved to CarTester\\Car Lists\\");
        if (path == null) {    //terminate program on exit
            System.exit(0);
        } else if (isNullOrWhitespace(path)) {     //set default path
            path = "Car Lists/Cars.txt";
        } else {
            path += "\\Cars.txt";
        }
        on = false;
    }

    public static boolean isNullOrWhitespace(String path) { //check for proper inputs
        if (path == null) {
            return true;
        }
        for (int i = 0; i < path.length(); i++) {
            if (!Character.isWhitespace(path.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    //Write cars to text file in CarTester Folder
    public static void writeBufferedWriter(String out) {
        File file = new File(path);
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fr);
            for (Character a : out.toCharArray()) {
                if (a == '\n') {
                    bw.newLine();
                } else {
                    bw.write(a);
                }
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
