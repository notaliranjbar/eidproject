package db;

import java.util.*;

public interface Trackable {
    void setCreationDate(Date date);
    Date getCreationDate();
    void setLastModificationDate(Date date);// stackoverflow
    Date getLastModificationDate();
}