import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class FileService
{
    private static final String SEPARATOR = ";";

    public List<Student> readStudents(Path path)
    {
        if (path == null)
        {
            throw new IllegalArgumentException("Путь к файлу не может быть пустым!");
        }

        if (!Files.exists(path))
        {
            throw new IllegalArgumentException("Файл не найден: " + path);
        }

        Map<String, List<Book>> studentBooks = new LinkedHashMap<>();

        try (Stream<String> lines = Files.lines(path))
        {
            lines.filter(line -> !line.isBlank()).forEach(line ->
            {
                String[] data = line.split(SEPARATOR);

                if (data.length != 4)
                {
                    throw new IllegalArgumentException("Неверный формат строки: " + line);
                }

                String studentName = data[0].trim();
                String title = data[1].trim();

                int pages = parseInteger(data[2].trim(), "количество страниц", line);

                int year = parseInteger(data[3].trim(), "год издания", line);

                Book book = new Book(title, pages, year);

                studentBooks.computeIfAbsent(studentName, key -> new ArrayList<>()).add(book);
            });

        }

        catch (IOException e)
            {
                throw new IllegalStateException("Ошибка при чтении файла: " + path, e);
            }

            return studentBooks.entrySet().stream().map(entry -> new Student(entry.getKey(), entry.getValue())).toList();
    }

    private static int parseInteger(String value, String fieldName, String line)
    {
        try
        {
            return Integer.parseInt(value);
        }
        catch (NumberFormatException e)
        {
            throw new IllegalArgumentException("Некорректное значение " + fieldName + " в строке: " + line, e);
        }
    }
}
