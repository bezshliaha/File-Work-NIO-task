package app;

import app.services.FileReadService;
import app.services.FileWriteService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

import static app.utils.Constants.welcomeMessage;

public class FileManager {

    private final FileReadService fileReadService = new FileReadService();
    private final FileWriteService fileWriteService = new FileWriteService();

    public void start() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println(welcomeMessage);

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    doCreateAndWriteFile(sc);
                    break;
                case 2:
                    doReadFile(sc);
                    break;
                case 3:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Wrong value: " + choice);
            }
        }
    }

    private void doReadFile(Scanner scanner) {
        System.out.println("Enter the file name (without extension): ");
        try {
            System.out.println("\nFile content: \n" + fileReadService.read(scanner.nextLine()));
            System.out.println("\n");
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
        } catch (IOException e) {
            e.getMessage();
        }
    }

    private void doCreateAndWriteFile(Scanner scanner) {
        System.out.println("Enter the file name (without extension): ");
        Path path;
        try {
            path = fileWriteService.createFile(scanner.nextLine());
        } catch (IOException e) {
            System.err.println("Failed to create file: " + e.getMessage());
            return;
        }

        System.out.println("Enter the content to write to the file: ");
        try {
            fileWriteService.writeToFile(path, scanner.nextLine());
            System.out.println("\n File written successfully! \n");
        } catch (IOException e) {
            System.err.println("Failed to write to file: " + e.getMessage());
        }
    }
}
