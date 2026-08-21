import java.nio.file.Path;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        testBookCreation();
        testInvalidBookTitle();
        testInvalidBookPages();
        testInvalidBookYear();
        testStudentCreation();
        testStudentWithLessThanFiveBooks();
        testStudentWithNullBooks();
        testBookEquals();
        testFileReading();

        System.out.println("Все тесты пройдены успешно!");
    }

    private static void testBookCreation() {
        Book book = new Book("Java", 500, 2020);

        if (!book.getName().equals("Java")
                || book.getPagesCount() != 500
                || book.getPublicationYear() != 2020) {
            throw new AssertionError("Ошибка создания Book");
        }
    }

    private static void testInvalidBookTitle() {
        try {
            new Book("", 500, 2020);
            throw new AssertionError("Не сработала проверка названия");
        } catch (IllegalArgumentException e) {
            System.out.println("Тест названия пройден");
        }
    }

    private static void testInvalidBookPages() {
        try {
            new Book("Java", -100, 2020);
            throw new AssertionError("Не сработала проверка страниц");
        } catch (IllegalArgumentException e) {
            System.out.println("Тест страниц пройден");
        }
    }

    private static void testInvalidBookYear() {
        try {
            new Book("Java", 500, 2027);
            throw new AssertionError("Не сработала проверка года");
        } catch (IllegalArgumentException e) {
            System.out.println("Тест года пройден");
        }
    }

    private static void testStudentCreation() {
        List<Book> books = createBooks();

        Student student = new Student("Иван", books);

        if (!student.getName().equals("Иван")
                || student.getBooks().size() != 5) {
            throw new AssertionError("Ошибка создания Student");
        }
    }

    private static void testStudentWithLessThanFiveBooks() {
        try {
            List<Book> books = List.of(
                    new Book("Java", 500, 2020),
                    new Book("Clean Code", 400, 2008)
            );

            new Student("Иван", books);

            throw new AssertionError(
                    "Не сработала проверка количества книг"
            );
        } catch (IllegalArgumentException e) {
            System.out.println("Тест количества книг пройден");
        }
    }

    private static void testStudentWithNullBooks() {
        try {
            new Student("Иван", null);

            throw new AssertionError(
                    "Не сработала проверка null списка"
            );
        } catch (IllegalArgumentException e) {
            System.out.println("Тест null списка пройден");
        }
    }

    private static void testBookEquals() {
        Book firstBook = new Book("Java", 500, 2020);
        Book secondBook = new Book("Java", 500, 2020);

        if (!firstBook.equals(secondBook)) {
            throw new AssertionError(
                    "Одинаковые книги не считаются равными"
            );
        }

        if (firstBook.hashCode() != secondBook.hashCode()) {
            throw new AssertionError(
                    "hashCode одинаковых книг отличается"
            );
        }

        System.out.println("Тест equals/hashCode пройден");
    }

    private static void testFileReading() {
        FileService fileService = new FileService();

        List<Student> students = fileService.readStudents(
                Path.of("students.txt")
        );

        if (students.size() != 3) {
            throw new AssertionError(
                    "Ожидалось 3 студента, получено: " + students.size()
            );
        }

        for (Student student : students) {
            if (student.getBooks().size() < 5) {
                throw new AssertionError(
                        "У студента меньше 5 книг: "
                                + student.getName()
                );
            }
        }

        System.out.println("Тест чтения файла пройден");
    }

    private static List<Book> createBooks() {
        return List.of(
                new Book("Java", 500, 2020),
                new Book("Clean Code", 400, 2008),
                new Book("Effective Java", 450, 2018),
                new Book("Head First Java", 600, 2005),
                new Book("Spring in Action", 550, 2018)
        );
    }
}