package string.class_problems;

import java.util.Random;

public class TeamWellnessReport {

    public static double calculateBmi(double heightMeters, double weightKg) {
        return weightKg / (heightMeters * heightMeters);
    }

    public static String getBmiStatus(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25) {
            return "Normal";
        } else if (bmi < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    public static void main(String[] args) {
        int teamSize = 10;
        Random randomGenerator = new Random();

        double[] heights = new double[teamSize];
        double[] weights = new double[teamSize];

        System.out.println("Person	Height (m)	Weight (kg)	BMI	Status");

        for (int i = 0; i < teamSize; i++) {
            heights[i] = 1.50 + randomGenerator.nextDouble() * 0.5;
            weights[i] = 50 + randomGenerator.nextDouble() * 50;

            double bmi = calculateBmi(heights[i], weights[i]);
            String status = getBmiStatus(bmi);

            System.out.println((i + 1) + "	" + heights[i] + "	" + weights[i] + "	" + bmi + "	" + status);
        }
    }
}
