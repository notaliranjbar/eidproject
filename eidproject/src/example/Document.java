package example;

import db.*;

import java.util.Date;

public class Document extends Entity implements Trackable {
    public String content;

    public static final int DOCUMENT_ENTITY_CODE = 77;

    private Date creationDate, lastModificationDate;

    public Document (String content) {
        this.content = content;
    }

    @Override
    public Document copy() {
        Document documentCopy = new Document(content);
        documentCopy.id = id;
        documentCopy.creationDate = new Date(creationDate.getTime());
        documentCopy.lastModificationDate = new Date(lastModificationDate.getTime());
        return documentCopy;
    }

    @Override
    public int getEntityCode() {
        return DOCUMENT_ENTITY_CODE;
    }

    @Override
    public void setCreationDate(Date date) {
        creationDate = date;
    }

    @Override
    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public void setLastModificationDate(Date date) {
        lastModificationDate = date;
    }

    @Override
    public Date getLastModificationDate() {
        return lastModificationDate;
    }
}