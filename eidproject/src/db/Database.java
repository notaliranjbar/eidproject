package db;
import db.exception.EntityNotFoundException;
import db.exception.InvalidEntityException;

import java.util.*;
public class Database {
    private static ArrayList<Entity> entities = new ArrayList<>();


    private static HashMap<Integer, Validator> validators = new HashMap<>();


    private static int lastId = 1;


    public static void add(Entity e) throws InvalidEntityException {
        Validator validator = validators.get(e.getEntityCode());
        if ( validator != null)
            validator.validate(e);

        if (e instanceof Trackable) {
            ((Trackable) e).setCreationDate(new Date());
            ((Trackable) e).setLastModificationDate(new Date());
        }

        e.id = lastId;
        lastId ++;
        entities.add(e.copy());
    }

    public static Entity get(int id) {
        for (Entity entity: entities)
            if (entity.id == id)
                return entity.copy();
        throw new EntityNotFoundException(id);
    }

    public static void delete(int id) {
        int flag = 0 ;
        for (int i = 0; i < entities.size(); i ++)
            if (entities.get(i).id == id) {
                entities.remove(i);
                flag=1;
            }
        if (flag == 0)
            throw new EntityNotFoundException(id);
    }

    public static void update(Entity e) throws InvalidEntityException {
        Validator validator = validators.get(e.getEntityCode());
        validator.validate(e);

        if (e instanceof Trackable)
            ((Trackable) e).setLastModificationDate(new Date());

        int flag = 0 ;
        for (int i = 0; i < entities.size(); i ++)
            if (entities.get(i).id == e.id) {
                entities.remove(i);
                entities.add(e.copy());
                flag = 1;
            }
        if (flag == 0)
            throw new EntityNotFoundException(e.id);
    }
    public static void registerValidator(int entityCode, Validator validator) {
        if (validators.containsKey(entityCode))// from stackoverflow "containsKey"

            throw new IllegalArgumentException("Validator with " + entityCode + " already exists");


        validators.put(entityCode, validator);
    }

}