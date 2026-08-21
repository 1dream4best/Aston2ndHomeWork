import java.util.List;

public class Student
{
private static final int MIN_BOOKS_COUNT = 5;

private final String name;
private final List<Book> books;

public Student(String name, List<Book> books)
    {
        validateName(name);
        validateBooks(books);

        this.name = name;
        this.books = List.copyOf(books);
    }

    private static void validateName(String name)
    {
        if(name == null || name.isBlank())
        {
            throw new IllegalArgumentException("Имя студента не может быть пустым!");
        }
    }

    private static void validateBooks(List<Book> books)
    {
        if(books == null)
        {
            throw new IllegalArgumentException("Список книг не может быть пустым!");
        }
        if(books.size() < MIN_BOOKS_COUNT)
        {
            throw new IllegalArgumentException("У студента должно быть минимум " + MIN_BOOKS_COUNT + "книг!");
        }
    }

    public String getName()
    {
        return name;
    }

    public List<Book> getBooks()
    {
        return books;
    }

    @Override
    public String toString()
    {
        return String.format("{Имя студента = '%s', книги = %s}", name, books);
    }
}
