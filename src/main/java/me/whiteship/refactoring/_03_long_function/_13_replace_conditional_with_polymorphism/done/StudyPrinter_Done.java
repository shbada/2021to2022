package me.whiteship.refactoring._03_long_function._13_replace_conditional_with_polymorphism.done;

import me.whiteship.refactoring._03_long_function._13_replace_conditional_with_polymorphism.Participant;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public abstract class StudyPrinter_Done {

    protected int totalNumberOfEvents;

    protected List<Participant> participants;

    public StudyPrinter_Done(int totalNumberOfEvents, List<Participant> participants) {
        this.totalNumberOfEvents = totalNumberOfEvents;
        this.participants = participants;
        this.participants.sort(Comparator.comparing(Participant::username));
    }

    public abstract void execute() throws IOException;


    /**
     * |:white_check_mark:|:white_check_mark:|:white_check_mark:|:x:|
     */
    protected String checkMark(Participant p) {
        StringBuilder line = new StringBuilder();
        for (int i = 1 ; i <= this.totalNumberOfEvents ; i++) {
            if(p.homework().containsKey(i) && p.homework().get(i)) {
                line.append("|:white_check_mark:");
            } else {
                line.append("|:x:");
            }
        }
        return line.toString();
    }
}
