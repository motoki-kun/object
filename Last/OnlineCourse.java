import java.util.*;

class OnlineCourse implements Course {
    private final String courseId;
    private final String courseName;
    private final Teacher instructor;
    private final Map<Student, Integer> scores;
    private final String type = "Online";
    private final String platform;

    OnlineCourse(String courseId, String courseName, Teacher instructor, String platform, Map<Student, Integer> scores) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.instructor = instructor;
        this.platform = platform;
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
    public OnlineCourse setScore(Student student, int score) {
        Map<Student, Integer> newScores = new HashMap<>(this.scores);
        newScores.put(student, score);
        return new OnlineCourse(this.courseId, this.courseName, this.instructor, this.platform, newScores);
    }

    @Override
    public OnlineCourse clone() {
        return new OnlineCourse(this.courseId, this.courseName, this.instructor, this.platform, this.scores);
    }

    @Override
    public void displayCourseInfo() {
        System.out.println("コースID: " + this.courseId);
        System.out.println("講義名: " + this.courseName);
        System.out.println("教師: " + this.instructor.getName());
        System.out.println("プラットフォーム: " + this.platform);
    }

    String getPlatform() {
        return platform;
    }
}
