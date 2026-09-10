package accessmodifiers.assigment_problems;

public class LibraryMemberBean {
    private String membershipId;
    private String name;
    private boolean premiumMember;
    private boolean membershipIdSet;
    private String securityAnswerHash;

    public LibraryMemberBean() {
    }

    public LibraryMemberBean(String name) {
        this();
        this.name = name;
    }

    public LibraryMemberBean(String membershipId, String name) {
        this(name);
        this.membershipId = membershipId;
        this.membershipIdSet = (membershipId != null);
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String id) {
        if (!membershipIdSet) {
            this.membershipId = id;
            this.membershipIdSet = true;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premium) {
        this.premiumMember = premium;
    }

    public void setSecurityAnswer(String answer) {
        if (answer != null) {
            this.securityAnswerHash = String.valueOf(answer.hashCode());
        }
    }

    public static void main(String[] args) {
        LibraryMemberBean nameOnly = new LibraryMemberBean("Priya Nair");
        System.out.println(nameOnly.getMembershipId());

        LibraryMemberBean withId = new LibraryMemberBean("LIB-8841", "Priya Nair");
        System.out.println(withId.getMembershipId());

        LibraryMemberBean m = new LibraryMemberBean();
        m.setMembershipId("LIB-8841");
        m.setMembershipId("FAKE-0000");
        System.out.println(m.getMembershipId());
    }
}
