import java.nio.file.Path;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        FileService fileService = new FileService();

        Path filePath = Path.of("students.txt");

        try {
            List<Student> students = fileService.readStudents(filePath);

            students.forEach(System.out::println);

        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
