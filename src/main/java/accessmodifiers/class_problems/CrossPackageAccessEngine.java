package accessmodifiers.class_problems;

public class CrossPackageAccessEngine {

    static String classifyAccess(String fieldModifier, String accessorContext) {
        boolean allowed;

        if (fieldModifier.equals("public")) {
            allowed = true;
        } else if (fieldModifier.equals("private")) {
            allowed = accessorContext.equals("SAME_CLASS");
        } else if (fieldModifier.equals("default")) {
            allowed = accessorContext.equals("SAME_CLASS") || accessorContext.equals("SAME_PACKAGE");
        } else if (fieldModifier.equals("protected")) {
            allowed = accessorContext.equals("SAME_CLASS")
                    || accessorContext.equals("SAME_PACKAGE")
                    || accessorContext.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE");
        } else {
            allowed = false;
        }

        return allowed ? "ALLOWED" : "DENIED";
    }

    static String describeContext(String accessorContext) {
        String[] words = accessorContext.split("_");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String word = words[i].toLowerCase();
            String capitalized = Character.toUpperCase(word.charAt(0)) + word.substring(1);
            result.append(capitalized);
            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));
        System.out.println(classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));
        System.out.println(describeContext("SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));
    }
}
