package string.class_problems;

public class FileExtensionValidator {

    public static String validateFileExtension(String filename) {
        int dotIndex = filename.lastIndexOf('.');
        String extension = filename.substring(dotIndex + 1);

        if (extension.equalsIgnoreCase("pdf") || extension.equalsIgnoreCase("docx") || extension.equalsIgnoreCase("zip")) {
            return "Accepted";
        } else {
            return "Rejected — invalid file type";
        }
    }

    public static void main(String[] args) {
        String filename = "Assignment1.PDF";
        String result = validateFileExtension(filename);
        System.out.println(result);
    }
}
