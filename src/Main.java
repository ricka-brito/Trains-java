import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    /**
     * Function that simulate a train collision
     *
     * @param posA The position of train A (0 -> 10000) in KM
     * @param posB The position of train B (0 -> 10000) in KM
     * @param velA The velocity of train A (0 -> 300) in KM/h
     * @param velB The velocity of train B (0 -> -300) in KM/h
     * @return The time left till the collision
     **/
    public static float colisionInstant(float posA, float posB, float velA, float velB) throws Exception {


        if ((posA > 10000 || posA < 0) || (posB > 10000 || posB < 0)){
            throw new Exception("Invalid position, the trains must be between 0 and 10000KMs");
        }

        else if(velA < 0){
            throw new Exception("The train B velocity must be a positive value");
        }
        else if(velB > 0){
            throw new Exception("The train B velocity must be a negative value");
        }
        else if(velA > 300 || velB < -300){
            throw new Exception("Invalid velocity, the trains velocity must be lesser than 300km/h");
        }

        else if (posA > posB || (velA == 0 || velB == 0)){
            return -1;
        }
        else{
            float t = (posA - posB) / (velB - velA);
            return t;
        }
    }
    /**
     * Function that calculate the collision position
     *
     * @param pos0 The initial position of the train (0 -> 10000) in KM
     * @param V The velocity of the train (0 -> 300) in KM/h
     * @param t The time left till the collision
     * @return The position that the collision will happen
     **/
    public static float collisionPos(float pos0, float V, float t){
        return pos0 + V * t;
    }

    /**
     * Function that instantiate a LocalTime object with 17:00:00 value and add the time
     *
     * @param t The time left till the collision
     * @return The LocalTime object plus the time left to the collision
     **/
    public static LocalTime PositionTime(float t){
        LocalTime start = LocalTime.of(17, 0, 0);
        start = start.plusSeconds((long) (t*60*60));
        return start;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int choice;

        while (true){
            System.out.println("\nWelcome to the trains collision simulator");
            System.out.println("(1) - Collide Train");
            System.out.println("(2) - Exit");
            System.out.print("Select an option above:");
            try {
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        float posA, posB, velA, velB, t;
                        while (true){
                            try {
                                try{
                                    System.out.print("\nType the position of train A (0-10000): ");
                                    posA = sc.nextFloat();
                                    System.out.print("\nType the position of train B (0-10000): ");
                                    posB = sc.nextFloat();
                                    System.out.print("\nType the velocity of train A (0-300): ");
                                    velA = sc.nextFloat();
                                    System.out.print("\nType the velocity of train B (0-300): ");
                                    velB = sc.nextFloat();
                                } catch (Exception e){
                                    sc.next();
                                    System.out.println("\nYou type a invalid character, please try again");
                                    continue;
                                }


                                t = colisionInstant(posA, posB, velA, velB);

                                if(t == -1){
                                    System.out.println("There will be no collision (The trains are stopped or the posA is greater than the posB)");
                                }
                                else {
                                    System.out.println("\nThe colision will be in the "+collisionPos(posA, velA, t)+"KM in "+t*60*60+" seconds at "+PositionTime(t));
                                }

                                break;
                            } catch (Exception e) {
                                System.out.println("\n"+e.getMessage());
                            }
                        }
                        break;
                    case 2:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("\nInvalid option");
                }
            }
            catch (Exception e) {
                System.out.println("\nInvalid option");
                sc.nextLine();
            }
        }

    }
}

