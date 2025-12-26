import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    static int getClosestAverageSweetnessLevel(File file) {
        double closestAverageSweetnessLevel = 0;

        try (Scanner scanner = new Scanner(file)) {
            int targetSweetnessValue = Integer.parseInt(scanner.nextLine());
            List<Integer> sweetnessLevelsOfCocoa = Arrays.stream(scanner.nextLine().split(","))
                    .map(Integer::parseInt)
                    .toList();
            int rightBorder = 0;

            do {
                rightBorder++;
                closestAverageSweetnessLevel =
                        (sweetnessLevelsOfCocoa.getFirst() + sweetnessLevelsOfCocoa.get(rightBorder)) / 2.0;
            } while ((sweetnessLevelsOfCocoa.getFirst() +
                    sweetnessLevelsOfCocoa.get(rightBorder)) / 2.0 <= targetSweetnessValue);

            for (int i = 0; i < rightBorder; i++) {
                for (int j = rightBorder - 1; j > i; j--) {
                    double averageSweetnessLevel =
                            (sweetnessLevelsOfCocoa.get(i) + sweetnessLevelsOfCocoa.get(j)) / 2.0;

//                    System.out.printf("%d %d %f %f%n", i, j, closestAverageSweetnessLevel, averageSweetnessLevel);

                    if (Math.abs(targetSweetnessValue - averageSweetnessLevel) <
                            Math.abs(targetSweetnessValue - closestAverageSweetnessLevel)) {
                        closestAverageSweetnessLevel = averageSweetnessLevel;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (closestAverageSweetnessLevel - Math.floor(closestAverageSweetnessLevel) == 0.5) {
            return (int) Math.floor(closestAverageSweetnessLevel);
        } else {
            return (int) Math.round(closestAverageSweetnessLevel);
        }
    }

    public static void main(String[] args) {
        File file = new File("src/main/resources/hyperskill-dataset-118996353.txt");
        System.out.println(getClosestAverageSweetnessLevel(file));
    }
}
