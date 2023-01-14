package hellojpa;

import javax.persistence.Entity;

@Entity
public class Book extends Item {
    private String auther;
    private String isbn;
}
