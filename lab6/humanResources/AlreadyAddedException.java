package humanResources;

public class AlreadyAddedException extends RuntimeException
{
    public AlreadyAddedException()
    {
        super();
    }
    public AlreadyAddedException(String s)
    {
        super(s);
    }
}
