package humanResources;

public class AlreadyAddedException extends RuntimeException
{
    AlreadyAddedException()
    {
        super();
    }
    AlreadyAddedException(String s)
    {
        super(s);
    }
}
