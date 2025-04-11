package example;
import db.*;
import db.exception.InvalidEntityException;
public class HumanValidator implements Validator {
    @Override
    public void validate(Entity entity) throws InvalidEntityException {
        if (!(entity instanceof Human))
            throw new IllegalArgumentException("that is not a human");
        if (((Human) entity).name == null)
            throw new InvalidEntityException("the human must have a name");
        if (((Human) entity).age < 0)
            throw new InvalidEntityException("the human must have an positive age");
    }
}