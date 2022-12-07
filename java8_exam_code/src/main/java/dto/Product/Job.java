package dto.Product;

public class Job {
    private int idx;
    private String jobName;

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Job(int idx, String jobName) {
        this.idx = idx;
        this.jobName = jobName;
    }
}
