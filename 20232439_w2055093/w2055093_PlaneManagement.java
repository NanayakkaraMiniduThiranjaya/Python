import java.util.InputMismatchException;
import java.util.Scanner;

public class w2055093_PlaneManagement {

    static int[] seat_no_A = new int[14];
    static int[] seat_no_B = new int[12];
    static int[] seat_no_C = new int[12];
    static int[] seat_no_D = new int[14];
    static Ticket[] array_tickets = new Ticket[52];
    static int ticketcount = 0;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println();
        System.out.println("Welcome To The Plane Management Application");
        System.out.println();
        initializeSeats();
        displayMenu();
    }

    static void initializeSeats() {
        for (int i = 0; i < seat_no_A.length; i++) {
            seat_no_A[i] = 0;
        }
        for (int i = 0; i < seat_no_B.length; i++) {
            seat_no_B[i] = 0;
        }
        for (int i = 0; i < seat_no_C.length; i++) {
            seat_no_C[i] = 0;
        }
        for (int i = 0; i < seat_no_D.length; i++) {
            seat_no_D[i] = 0;
        }
    }

    static void displayMenu() {

        while (true) {
            try {
                // print menu
                System.out.println("\n********************************************");
                System.out.println("*                                          *");
                System.out.println("*                  Menu                    *");
                System.out.println("*                                          *");
                System.out.println("********************************************");
                System.out.println("1. Buy a seat                                ");
                System.out.println("2. Cancel a seat                             ");
                System.out.println("3. Find first available seat                 ");
                System.out.println("4. Show seating plan                         ");
                System.out.println("5. Print tickets information and total sales ");
                System.out.println("6. Search ticket                             ");
                System.out.println("0. Exit                                      ");
                System.out.println("********************************************");
                System.out.print("\nPlease select an option: ");

                int option = scanner.nextInt();

                switch (option) {
                    case 0:
                        System.out.println("\nThank you For Using Our Service.");
                        System.out.println("Exiting........\n");
                        System.exit(0);
                        break;
                    case 1:
                        buy_Seat();
                        break;
                    case 2:
                        cancel_Seat();
                        break;
                    case 3:
                        find_First_Available();
                        break;
                    case 4:
                        show_Seating_Plan();
                        break;
                    case 5:
                        print_tickets_info();
                        break;
                    case 6:
                        searchTicket();
                        break;
                    default:
                        System.out.println("Invalid Option. Please Try Again.");
                }
            }

            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please Try Again.");
                System.out.println();
                scanner.next(); // Clear the invalid input from the scanner

            }
        }

    }

    // method of buy seat
    static void buy_Seat() {
        while (true) {
            try {
                // print buy seat
                System.out.println("\n--------------------------------");
                System.out.println("-           Buy Seat           -");
                System.out.println("--------------------------------\n");
                System.out.println("Seat no 1 to 5   = £200");
                System.out.println("Seat no 6 to 9   = £150");
                System.out.println("Seat no 10 to 14 = £180");
                System.out.println("Seat rows A - 1 to 14 ");
                System.out.println("seat rows B - 1 to 12 ");
                System.out.println("Seat rows C - 1 to 12 ");
                System.out.println("Seat rows D - 1 to 14 ");
                System.out.print("Enter row letter (A-D): ");
                char row = Character.toUpperCase(scanner.next().charAt(0));
                System.out.print("Enter seat number: ");
                int seatNumber = scanner.nextInt();

                int[] seats = getSeatsArray(row);

                if (isValidSeat(row, seatNumber)) {
                    if (seats[seatNumber - 1] == 0) {
                        System.out.print("Enter First Name: ");
                        String name = scanner.next();

                        System.out.print("Enter Surname: ");
                        String surname = scanner.next();

                        System.out.print("Enter Email: ");
                        String email = scanner.next();

                        Person person = new Person(name, surname, email);
                        Ticket ticket = new Ticket(row, seatNumber, calculatePrice(row, seatNumber), person);
                        array_tickets[ticketcount++] = ticket;
                        Ticket save_ticket = new Ticket(row, seatNumber, calculatePrice(row, seatNumber), person);
                        save_ticket.save();

                        seats[seatNumber - 1] = 1; // Mark seat as sold
                        System.out.println("Seat successfully sold!");
                        break;
                    } else {
                        System.out.println(
                            "Seat already sold.");
                    }
                } else {
                    System.out.println();
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid row or seat number. Please try again.");
                scanner.next(); // Clear the invalid input from the scanner
            }

        }
    }

    static void cancel_Seat() {

        try {
            System.out.println("\n--------------------------------");
            System.out.println("-         Cancel Seat          -");
            System.out.println("--------------------------------\n");
            System.out.print("Enter row letter (A-D): ");
            char row = Character.toUpperCase(scanner.next().charAt(0));
            System.out.print("Enter seat number: ");
            int seatNumber = scanner.nextInt();// calling scanner method

            int[] seats = getSeatsArray(row);

            if (isValidSeat(row, seatNumber)) {
                if (seats[seatNumber - 1] == 1) {
                    seats[seatNumber - 1] = 0; // Mark seat as available again

                    System.out.println("Seat Successfully Cancelled And Made Available Again!");

                } else {
                    System.out.println("Seat Is Already Available.");
                }
            }

            else {
                System.out.println("Invalid Row Or Seat Number. Please Try Again.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid Row Or Seat Number. Please Try Again.");
            scanner.next(); // Clear the invalid input from the scanner
        }

    }

    static void find_First_Available() {
        System.out.println("\n--------------------------------");
        System.out.println("-    Find First Available      -");
        System.out.println("--------------------------------\n");
        for (char i = 'A'; i < 'E'; i++) {
            int[] seats_ = firstavailabseats(i);
            int j = 0;
            while (j < seats_.length) {
                if (seats_[j] == 0) {
                    System.out.println("Found a seat in row " + i + " seat number is " + Integer.valueOf(j + 1));

                    break;
                }
                j++;
            }

        }

    }

    // print available and sold seats
    static void show_Seating_Plan() {

        System.out.println("\n--------------------------------");
        System.out.println("-      Show seating plan       -");
        System.out.println("--------------------------------\n");
        System.out.println();

        System.out.println("Seating Plan:");
        System.out.println("       1  2  3  4  5  6  7  8  9 10 11 12 13 14");
        System.out.print("Row A: ");
        printSeatRow(seat_no_A);
        System.out.print("Row B: ");
        printSeatRow(seat_no_B);
        System.out.print("Row C: ");
        printSeatRow(seat_no_C);
        System.out.print("Row D: ");
        printSeatRow(seat_no_D);
    }

    static void printSeatRow(int[] seats) {

        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 0) {
                System.out.print("O  "); // Available seat icon
            } else {
                System.out.print("X  "); // Sold seat icon
            }
        }
        System.out.println();
    }

    static boolean isValidSeat(char row, int seatNumber) {
        switch (row) {
            case 'A':
                return (seatNumber >= 1 && seatNumber <= 14);
            case 'B':
                return (seatNumber >= 1 && seatNumber <= 12);
            case 'C':
                return (seatNumber >= 1 && seatNumber <= 12);
            case 'D':
                return (seatNumber >= 1 && seatNumber <= 14);
            default:
                return false;
        }
    }

    static int[] getSeatsArray(char row) {
        switch (row) {
            case 'A':
                return seat_no_A;
            case 'B':
                return seat_no_B;
            case 'C':
                return seat_no_C;
            default:
                return seat_no_D;
        }
    }

    static double calculatePrice(char row, int seatNumber) {
        switch (seatNumber) {
            case 1, 2, 3, 4, 5:
                return 200.00;
            case 6, 7, 8, 9:
                return 150.00;
            default:
                return 180.00;
        }
    }

    // checking ticket information
    static void print_tickets_info() {
        System.out.println("\n------------------------------------------------------");
        System.out.println("-     Tickets information and total sales            -");
        System.out.println("------------------------------------------------------\n");

        double totalAmount = 0; // Total amount

        // Iterate over each row
        for (char i = 'A'; i <= 'D'; i++) {
            int[] seats_ = firstavailabseats(i);

            // Iterate over each seat in the row
            for (int j = 0; j < seats_.length; j++) {
                if (seats_[j] == 1) { // Check if the seat is sold
                    for (Ticket ticket : array_tickets) {
                        // Check if the ticket matches the row and seat number
                        if (ticket != null && ticket.getRow() == i && ticket.getSeatNumber() == j + 1) {
                            // Print the information of the found ticket
                            System.out.println("Ticket Information:");
                            ticket.printTicketInfo();
                            totalAmount += ticket.getPrice(); // Add ticket price to total amount
                            System.out.println();
                        }
                    }
                }
            }
        }
        // Print total amount after printing all ticket information
        System.out.println("Total Amount Of Sales: £" + totalAmount);
    }

    static int[] firstavailabseats(char i) {
        switch (i) {
            case 'A':
                return seat_no_A;
            case 'B':
                return seat_no_B;
            case 'C':
                return seat_no_C;
            default:
                return seat_no_D;

        }
    }

    // search tickets is Available or not
    static void searchTicket() {

        try {
            System.out.println("\n--------------------------------");
            System.out.println("-       Search Ticket          -");
            System.out.println("--------------------------------\n");
            System.out.print("Enter row letter (A-D): ");
            char row = Character.toUpperCase(scanner.next().charAt(0));
            System.out.print("Enter seat number: ");
            int seatNumber = scanner.nextInt();

            int[] seats = getSeatsArray(row);

            if (isValidSeat(row, seatNumber)) {
                if (seats[seatNumber - 1] == 0) {

                    System.out.println("This seat is available.");
                    // Iterate through each ticket in the tickets ArrayList

                } else {
                    System.out.println("Seat already sold.Here required informations about that ticket's buyer.");
                    for (Ticket ticket : array_tickets) {
                        // Check if the ticket matches the provided row and seat number
                        if (ticket.getRow() == row && ticket.getSeatNumber() == seatNumber) {
                            // Print the information of the found ticket
                            System.out.println("Ticket Information:");
                            ticket.printTicketInfo();
                            break; // Exit the loop once the ticket is found

                        }
                    }
                }
            } else {
                System.out.println("Invalid row or seat number. Please try again.");

            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid row or seat number. Please try again.");

        }

    }
}
