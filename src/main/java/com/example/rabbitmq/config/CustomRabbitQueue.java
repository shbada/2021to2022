package com.example.rabbitmq.config;

public enum CustomRabbitQueue {
    SAMPLE_TASK("photo.sample"),
    READ("photo.read"),
    WRITE("photo.write"),
    EMPTY("photo.empty");

    private String queueName;

    CustomRabbitQueue(String queueName) {
        this.queueName = queueName;
    }

    public String getQueueName() {
        return queueName;
    }

    public static CustomRabbitQueue find(String name) {
        for (CustomRabbitQueue queue : CustomRabbitQueue.values()) {
            if (queue.getQueueName().equalsIgnoreCase(name)) {
                return queue;
            }
        }
        return CustomRabbitQueue.EMPTY;
    }
}
