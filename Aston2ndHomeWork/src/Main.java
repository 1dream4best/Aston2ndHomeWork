import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        FileService fileService = new FileService();

        try
        {
            List<Student> students = fileService.readStudents(Path.of("students.txt"));

            System.out.println("Количество студентов: " + students.size());
            System.out.println();

            students.forEach(student -> System.out.println("Студент: " + student.getName()));

            System.out.println();
            System.out.println("Книги после сортировки и фильтрации:");

            students.stream()
                    .flatMap(student -> student.getBooks().stream())
                    .sorted(Comparator.comparingInt(Book::getPagesCount))
                    .distinct()
                    .filter(book -> book.getPublicationYear() > 2000)
                    .limit(3)
                    .forEach(book -> System.out.println("Книга: " + book.getName() + ", страниц: " + book.getPagesCount() + ", год: " + book.getPublicationYear()));
        }

        catch (IllegalArgumentException | IllegalStateException e)
        {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
