package accessmodifiers.class_problems;

public class PatientVitals {
    private double[] readings;
    private int count;

    public PatientVitals(double[] initialReadings) {
        this.readings = new double[initialReadings.length];
        this.count = 0;
        for (int i = 0; i < initialReadings.length; i++) {
            recordReading(initialReadings[i]);
        }
    }

    public void recordReading(double reading) {
        if (reading <= 0 || reading > 45) {
            return;
        }
        readings[count] = reading;
        count++;
    }

    public double getAverage() {
        if (count == 0) {
            return 0;
        }
        double total = 0;
        for (int i = 0; i < count; i++) {
            total += readings[i];
        }
        return total / count;
    }

    public double[] getAllReadings() {
        double[] copy = new double[count];
        for (int i = 0; i < count; i++) {
            copy[i] = readings[i];
        }
        return copy;
    }

    public static void main(String[] args) {
        PatientVitals v = new PatientVitals(new double[]{36.5, -2, 37.1});
        double[] readings = v.getAllReadings();
        for (int i = 0; i < readings.length; i++) {
            System.out.print(readings[i] + " ");
        }
        System.out.println();

        double[] copy = v.getAllReadings();
        copy[0] = 999;
        System.out.println(v.getAllReadings()[0]);
    }
}
