package design.class_problems;

import java.util.*;

interface Question {
    String getPrompt();
    boolean isCorrect(String answer);
}

class MCQQuestion implements Question {
    private String prompt;
    private String correctAnswer;

    public MCQQuestion(String prompt, String correctAnswer) {
        this.prompt = prompt;
        this.correctAnswer = correctAnswer;
    }

    public String getPrompt() { return prompt; }

    public boolean isCorrect(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class Examination {
    private String name;
    private List<Question> questions = new ArrayList<>();

    public Examination(String name) { this.name = name; }

    public void addQuestion(Question q) { questions.add(q); }

    public String getName() { return name; }

    public List<Question> getQuestions() { return questions; }
}

class Student {
    private String name;

    public Student(String name) { this.name = name; }

    public String getName() { return name; }
}

class Attempt {
    private Student student;
    private Examination exam;
    private Map<Integer, String> answers = new HashMap<>();
    private boolean submitted = false;

    public Attempt(Student student, Examination exam) {
        this.student = student;
        this.exam = exam;
        System.out.println("Examination '" + exam.getName() + "' started by " + student.getName() + ".");
    }

    public void answer(int index, String answer) {
        if (submitted) {
            System.out.println("Cannot answer: attempt already submitted.");
            return;
        }
        answers.put(index, answer);
        System.out.println("Question " + (index + 1) + " answered with '" + answer + "'.");
    }

    public void submit() {
        if (submitted) {
            System.out.println("Attempt already submitted.");
            return;
        }
        submitted = true;
        System.out.println("Examination '" + exam.getName() + "' submitted successfully.");
        evaluate();
    }

    private void evaluate() {
        int correct = 0;
        List<Question> questions = exam.getQuestions();
        for (int i = 0; i < questions.size(); i++) {
            String given = answers.get(i);
            if (given != null && questions.get(i).isCorrect(given)) correct++;
        }
        System.out.println("Result for '" + exam.getName() + "' attempt: " + correct + "/" + questions.size() + " correct.");
    }
}

public class OnlineExaminationSystem {
    public static void main(String[] args) {
        Examination mathQuiz = new Examination("Math Quiz");
        mathQuiz.addQuestion(new MCQQuestion("2 + 2 = ?", "A"));
        mathQuiz.addQuestion(new MCQQuestion("3 + 3 = ?", "B"));

        Attempt attempt = new Attempt(new Student("Alice"), mathQuiz);
        attempt.answer(0, "A");
        attempt.answer(1, "C");
        attempt.submit();
    }
}
