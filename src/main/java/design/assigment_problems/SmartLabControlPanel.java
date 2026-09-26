package design.assigment_problems;

import java.util.*;

interface Capability {
    String getName();
    boolean isValid(double value);
    void apply(double value);
    String describe(double value);
}

class PowerCapability implements Capability {
    private boolean on = false;

    public String getName() { return "Power"; }

    public boolean isValid(double value) { return value == 0 || value == 1; }

    public void apply(double value) { on = value == 1; }

    public String describe(double value) { return on ? "ON" : "OFF"; }
}

class BrightnessCapability implements Capability {
    private int brightness;

    public String getName() { return "Brightness"; }

    public boolean isValid(double value) { return value >= 0 && value <= 100; }

    public void apply(double value) { brightness = (int) value; }

    public String describe(double value) { return "brightness set to " + (int) value + "%"; }
}

class TemperatureCapability implements Capability {
    private int temperature;

    public String getName() { return "Temperature"; }

    public boolean isValid(double value) { return value >= 16 && value <= 30; }

    public void apply(double value) { temperature = (int) value; }

    public String describe(double value) { return "temperature set to " + (int) value + "°C"; }
}

class Device {
    private String name;
    private Map<String, Capability> capabilities = new LinkedHashMap<>();

    public Device(String name, Capability... caps) {
        this.name = name;
        for (Capability c : caps) capabilities.put(c.getName(), c);
    }

    public String getName() { return name; }

    public boolean has(String capabilityName) { return capabilities.containsKey(capabilityName); }

    public void addCapability(Capability c) {
        capabilities.put(c.getName(), c);
        System.out.println(name + ": " + c.getName() + " capability added.");
    }

    public boolean applyStep(String capabilityName, double value, String rangeErrorMessage) {
        Capability c = capabilities.get(capabilityName);
        if (c == null) return false;
        if (!c.isValid(value)) {
            System.out.println("Rejected: " + rangeErrorMessage);
            return false;
        }
        c.apply(value);
        System.out.println(name + ": " + c.describe(value));
        return true;
    }
}

class SceneStep {
    String capabilityName;
    double value;
    String label;

    SceneStep(String capabilityName, double value, String label) {
        this.capabilityName = capabilityName;
        this.value = value;
        this.label = label;
    }
}

class Scene {
    private String name;
    private List<SceneStep> steps = new ArrayList<>();

    public Scene(String name) { this.name = name; }

    public void addStep(String capabilityName, double value, String label) {
        steps.add(new SceneStep(capabilityName, value, label));
    }

    public void run(List<Device> devices) {
        System.out.println("Scene '" + name + "' started.");
        int applied = 0;
        for (SceneStep step : steps) {
            for (Device d : devices) {
                if (d.has(step.capabilityName)) {
                    if (d.applyStep(step.capabilityName, step.value, step.label)) applied++;
                }
            }
        }
        System.out.println("Scene '" + name + "' completed: " + applied + " actions applied.");
    }
}

public class SmartLabControlPanel {
    public static void main(String[] args) {
        Device labAC = new Device("Lab AC", new PowerCapability(), new TemperatureCapability());
        Device ceilingLights = new Device("Ceiling Lights", new PowerCapability(), new BrightnessCapability());
        Device projector = new Device("Projector", new PowerCapability());

        List<Device> devices = Arrays.asList(labAC, ceilingLights, projector);

        Scene lectureMode = new Scene("Lecture Mode");
        lectureMode.addStep("Power", 1, "power must be 0 or 1");
        lectureMode.addStep("Brightness", 40, "brightness must be between 0% and 100%");
        lectureMode.addStep("Temperature", 24, "temperature must be between 16°C and 30°C");
        lectureMode.run(devices);

        labAC.applyStep("Temperature", 12, "Lab AC temperature must be between 16°C and 30°C");

        projector.addCapability(new BrightnessCapability());
        projector.applyStep("Brightness", 70, "brightness must be between 0% and 100%");
    }
}
