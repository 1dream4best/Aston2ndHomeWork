import java.util.Objects;

public class Book
{
    private static final int MAX_PUBLICATION_YEAR = 2026;

    private final String name;
    private final int pagesCount;
    private final int publicationYear;

    public Book(String name, int pagesCount, int publicationYear)
    {
        validateName(name);
        validatePagesCount(pagesCount);
        validatePublicationYear(publicationYear);

        this.name = name;
        this.pagesCount = pagesCount;
        this.publicationYear = publicationYear;
    }

    private static void validateName(String name)
    {
        if(name == null || name.isBlank())
        {
            throw new IllegalArgumentException("Название книги не может быть пустым!");
        }
    }

    private static void validatePagesCount(int pagesCount)
    {
        if(pagesCount <= 0)
        {
            throw new IllegalArgumentException("Количество страниц доджно быть больше 0!");
        }
    }

    private static void validatePublicationYear(int publicationYear)
    {
        if(publicationYear <= 0 || publicationYear > MAX_PUBLICATION_YEAR)
        {
            throw new IllegalArgumentException("Ваша книга не может быть выпущена так давно или принесена из будущего!");
        }
    }

    public String getName()
    {
        return name;
    }

    public int getPagesCount()
    {
        return pagesCount;
    }

    public int getPublicationYear()
    {
        return publicationYear;
    }

    @Override
    public boolean equals(Object object)
    {
        if(this == object)
        {
            return true;
        }

        if(!(object instanceof Book book))
        {
            return false;
        }

        return pagesCount == book.pagesCount
                && publicationYear == book.publicationYear
                && name.equals(book.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, pagesCount, publicationYear);
    }

    @Override
    public String toString()
    {
        return String.format("{Название = '%s', Страницы = %d, Год издания = %d}",
                                    name, pagesCount, publicationYear);
    }
}

