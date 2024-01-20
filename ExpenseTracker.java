import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.io.*;
import java.time.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ExpenseTracker {
    static HashMap<String, String> hm = new HashMap<>();

    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        // Welcome Frame
        JFrame welcomeFrame = new JFrame("Welcome to Expense Tracker");
        JLabel welcomeLabel = new JLabel("Welcome to Expense Tracker");
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        Font largerfont = new Font(welcomeLabel.getFont().getName(), Font.PLAIN, 24);
        welcomeLabel.setFont(largerfont); // Set the larger font

        welcomeFrame.add(welcomeLabel);
        welcomeFrame.setSize(200, 200);
        welcomeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        welcomeFrame.setVisible(true);

        int frameWidth = 600;
        int frameHeight = 400;
        welcomeFrame.setSize(frameWidth, frameHeight);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frameWidth) / 2;
        int y = (screenSize.height - frameHeight) / 2;
        welcomeFrame.setLocation(x, y);

        welcomeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        welcomeFrame.setVisible(true);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        welcomeFrame.dispose();
        BufferedReader br = new BufferedReader(new FileReader("UserNamePassword.txt"));
        String line = "";

        // Login_Registration Module
        while ((line = br.readLine()) != null) {
            String[] str = line.trim().split("=");
            hm.put(str[0], str[1]);
        }
        System.out.println("\nNew User ?   \"Click-1\" for Sign-Up");
        Thread.sleep(1000);
        System.out.println("\nAlready Registered User ?   \"Click-2\" for Sign-In");
        Thread.sleep(1000);
        System.out.println("\nWant To Exit System ?   \"Click-3\" to exit the system");
        System.out.print("Choose the option to select: ");
        int key = s.nextInt();
        s.nextLine();
        switch (key) {
            case 1:
                SignUp signup = new SignUp();
                // Calling SignUp Module
                break;
            case 2:
                SignIn signin = new SignIn();
                signin.SignInYourAccount();
                // Calling Sign-in Module
                break;
            case 3:
                System.exit(0);
                break;
            default:
                System.out.println("invalid choice !");
                break;
        }
    }
}

class SignUp {
    Scanner s = new Scanner(System.in);
    static String name, bday, email, gender, phoneNumber, username, password;
    int age;

    public SignUp() throws Exception {
        while (true) {
            System.out.println(
                    "                                                  --> (USER'S REGISTRATION) <--                                                              ");
            // String Validation Using Regex , Until User Enters First And Last Name In
            // Proper Format, it will show error
            System.out.print("\nPlease enter your full name (First Last): ");
            name = s.nextLine();

            String namePattern = "^[A-Z][a-z]+\\s[A-Z][a-z]+$";

            Pattern regex = Pattern.compile(namePattern);

            Matcher matcher = regex.matcher(name);

            if (matcher.matches()) {
                break;
            } else {
                System.out.println(
                        "Invalid name format. There Should be capital letter in First Name .\nPl1ease enter your full name.");
            }
        }

        // Gathering User Data
        System.out.print("Please enter your date of birth (YYYY-MM-DD): ");
        boolean b=true;
        while(b){}
        bday = s.nextLine();
        
        boolean check = true;
        while (check) {
            System.out.print("Enter your Email address: ");

            email = s.nextLine();
            String e = "^[A-Za-z0-9+_.-]+@(.+)$";
            if (email.matches(e)) {
                check = false;
                break;
            } else {
                System.out.println("Invalid email address.");
                System.out.print("Enter again : ");
            }
        }

        System.out.print("Please enter your gender: ");
        gender = s.nextLine();
        boolean f=false;
        System.out.print("Please enter your phone number: ");
        phoneNumber = s.nextLine();
        if(phoneNumber.charAt(0)!='9'|| phoneNumber.charAt(0)!='8'|| phoneNumber.charAt(0)!='7'){
            if(phoneNumber.length()!=10){
                System.out.println("Invalid Mobile Number");
                f=true;
            }
        }
        if(f==true){
        // OTP Based Registration Frame
         check = true;
        while (check) {
            JFrame jFrame = new JFrame();
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame.setVisible(true);
            int num = (int) (Math.random() * 9000) + 1000;
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int centerX = (int) ((screenSize.getWidth() - jFrame.getWidth()) / 2);
            int centerY = (int) ((screenSize.getHeight() - jFrame.getHeight()) / 2);
            jFrame.setLocation(centerX, centerY);
            JOptionPane.showMessageDialog(jFrame, "Your OTP: " + num);
            System.out.print("Enter OTP recieved on your number: ");
            int otp = s.nextInt();
            if (otp == num) {
                System.out.println("Entered OTP is correct");
                jFrame.dispose();
                String number = Integer.toString((int) (Math.random() * (30 - 11 + 1)) + 11);
                int index = name.indexOf(' ');
                String autoString = name.substring(0, index).concat(number);
                System.out.println("\nThe system's automatically generated username: " + autoString);
                System.out.println("\"Click 1\" to select automated username");
                System.out.println("\"Click 2\" for manually entering the password");
                System.out.print("Enter your choice: ");
                BufferedWriter bw = new BufferedWriter(new FileWriter("UserNamePassword.txt", true));
                int Choice = s.nextInt();
                s.nextLine();
                if (Choice == 1) {
                    if (ExpenseTracker.hm.containsKey(username)) {

                        number = Integer.toString((int) (Math.random() * (30 - 11 + 1)) + 11);
                        index = name.indexOf(' ');
                        autoString = name.substring(0, index).concat(number);
                        username = autoString;
                        System.out.println("Automated username selected");
                        break;
                    } else {
                        System.out.println("Automated username selected");
                        username = autoString;
                    }
                } else if (Choice == 2) {
                    System.out.print("\nEnter username manually: ");
                    boolean check1 = true;
                    while (check1) {
                        username = s.nextLine();
                        if (ExpenseTracker.hm.containsKey(username)) {
                            System.out.println("Entered username is already taken");
                            System.out.print("Enter again :");
                        } else {
                            System.out.println("Entered username accepted");
                            check1 = false;
                            break;
                        }
                    }
                }
                System.out.print("Please enter your password: ");
                password = s.nextLine();
                bw.write(username + "=" + password);
                bw.newLine();
                bw.flush();
                bw.close();
                check = false;
                int count = 5;
                System.out.println("\nSigning-up into the system ! please wait.");
                while (count > 0) {
                    System.out.print(count + " .... ");
                    Thread.sleep(1000);
                    count--;
                }
                System.out.println(count);
                System.out.println("\nSign-up successfull , welcome to the system !");
                Expense e = new Expense(username);
                e.main(null);
                break;
            } else {
                System.out.println("Entered OTP Is Incorrect!");
                System.out.print("Enter Again: ");
                jFrame.dispose();
            }
        }
        }
        else{
            System.out.println("Invalid Phone number");
    
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class SignIn {
    public void SignInYourAccount() throws Exception {
        Scanner s = new Scanner(System.in);
        boolean userFound = false;
        System.out.println(
                "                                                  --> (USER'S LOGIN) <--                                                              ");
        while (!userFound) {
            System.out.print("Enter Your Username: ");
            String enteredUsername = s.nextLine();

            BufferedReader br = new BufferedReader(new FileReader("UserNamePassword.txt"));
            String line;

            while ((line = br.readLine()) != null) {
                String[] arr = line.trim().split("=");
                String usernameFromFile = arr[0];
                String passwordFromFile = arr[1];

                if (usernameFromFile.equals(enteredUsername)) {
                    System.out.print("Enter Your Password: ");
                    String enteredPassword = s.nextLine();

                    if (passwordFromFile.equals(enteredPassword)) {
                        int count = 5;
                        System.out.println("\nSigning-in into the system! Please wait.");
                        while (count > 0) {
                            System.out.print(count + " .... ");
                            Thread.sleep(1000);
                            count--;
                        }
                        System.out.println("\nSign-in successful, welcome to the system!");
                        userFound = true;
                        Expense e = new Expense(usernameFromFile);
                        e.main(null);
                        break;
                    } else {
                        System.out.println("\"WARNING\" :: Password Is Incorrect");
                    }
                }
            }

            br.close();

            if (!userFound) {
                System.out.println("\"WARNING\" :: Username Not Found");
                System.out.println("Please try again.");
            }
        }
    }
}
