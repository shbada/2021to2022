package me.whiteship.refactoring._14_lazy_elements._34_collapse_hierarchy.done;

import java.time.LocalDateTime;
import java.util.List;
/*
필드들을 하위 클래스로 내리던가, 또는 하위 클래스를 상위클래스로 올리던가.
 */
public class Reservation {

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private List<String> members;

    private String owner;

    private boolean paid;

}
