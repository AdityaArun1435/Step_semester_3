package accessmodifiers.class_problems;

public class PatientProfile {
    private String patientId;
    private String name;
    private boolean discharged;
    private boolean patientIdSet;
    private String lockerPinHash;

    public PatientProfile() {
    }

    public PatientProfile(String name) {
        this();
        this.name = name;
    }

    public PatientProfile(String patientId, String name) {
        this(name);
        this.patientId = patientId;
        this.patientIdSet = (patientId != null);
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String id) {
        if (!patientIdSet) {
            this.patientId = id;
            this.patientIdSet = true;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDischarged() {
        return discharged;
    }

    public void setDischarged(boolean discharged) {
        this.discharged = discharged;
    }

    public void setLockerPin(String pin) {
        if (pin != null) {
            this.lockerPinHash = String.valueOf(pin.hashCode());
        }
    }

    public static void main(String[] args) {
        System.out.println(new PatientProfile("Arjun Iyer").getPatientId());
        System.out.println(new PatientProfile("MT2026-0142", "Arjun Iyer").getPatientId());

        PatientProfile p = new PatientProfile();
        p.setPatientId("MT2026-0142");
        p.setPatientId("HACKED-0000");
        System.out.println(p.getPatientId());
    }
}
