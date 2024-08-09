import java.util.*;

class InPersonCourse implements Course {
    private final String courseId;
    private final String courseName;
    private final Teacher instructor;
    private final Map<Student, Integer> scores;
    private final String type = "inperson";
    private final String classroom;

    InPersonCourse(String courseId, String courseName, Teacher instructor, String classroom, Map<Student, Integer> scores) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.instructor = instructor;
        this.classroom = classroom;
        this.scores = scores;
    }

    @Override
    public String getCourseId() {
        return courseId;
    }

    @Override
    public String getCourseName() {
        return courseName;
    }

    @Override
    public Teacher getInstructor() {
        return instructor;
    }

    @Override
    public Map<Student, Integer> getScores() {
        return new HashMap<>(scores);
    }

    @Override
    public InPersonCourse setScore(Student student, int score) {
        Map<Student, Integer> newScores = new HashMap<>(this.scores);
        newScores.put(student, score);
        return new InPersonCourse(this.courseId, this.courseName, this.instructor, this.classroom, newScores);
    }

    @Override
    public InPersonCourse clone() {
        return new InPersonCourse(this.courseId, this.courseName, this.instructor, this.classroom, this.scores);
    }

    @Override
    public void displayCourseInfo() {
        System.out.println("コースID: " + courseId);
        System.out.println("講義名: " + courseName);
        System.out.println("教師: " + instructor.getName());
        System.out.println("教室: " + classroom);
    }

    String getClassroom() {
        return classroom;
    }
}
