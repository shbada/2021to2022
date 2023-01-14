package dto.Product;

import java.util.Optional;

public class Person {
    private int idx;
    private String personName;
    private Optional<Job> job;

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Optional<Job> getJob() {
        return job;
    }

    public void setJob(Optional<Job> job) {
        this.job = job;
    }

    public Person(int idx, String personName) {
        this.idx = idx;
        this.personName = personName;
    }
}
