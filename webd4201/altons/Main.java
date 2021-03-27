package webd4201.altons;

import java.util.Date;

/**
 * Main.java
 * This is the main class for the program execution and is primarily used to debug.
 * @version 1.0
 * @since 1.0 (2021/1/13)
 */
public class Main {

    public static void main(String[] args) throws InvalidUserDataException{
        try
        {
            Faculty scott = new Faculty();
            System.out.println(scott.toString());

            Mark webd = new Mark("WEBD2201", "Web Development - Fundamentals", 80, 4.0f);
            webd.setGpaWeighting(4.011f);
            scott.setFirstName("Scott");
            System.out.println(webd.toString());

            Student defaultStudent = new Student();
            defaultStudent.setYear(3);
            System.out.println(defaultStudent.toString());

            defaultStudent.setId(111222333);
            defaultStudent.setPassword("");
            System.out.println("Password:" +  defaultStudent.getPassword());

            Student brokenStudent = new Student(12346789, "password", "Afie", "Burger", "test@gmail.com", new Date(), new Date(), true, 's', "COD", "Test description", 2);
            System.out.println(brokenStudent.toString());
        }
        catch (InvalidIdException e)
        {
            System.out.println(e.getMessage());
        }
        catch (InvalidPasswordException e)
        {
            System.out.println(e.getMessage());
        }
        catch (InvalidNameException e) {
            System.out.println(e.getMessage());
        }
        catch (InvalidUserDataException e) {
            System.out.println(e.getMessage());
        }
    }
}
