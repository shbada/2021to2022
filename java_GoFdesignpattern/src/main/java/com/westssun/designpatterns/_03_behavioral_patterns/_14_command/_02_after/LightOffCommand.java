package com.westssun.designpatterns._03_behavioral_patterns._14_command._02_after;

import com.westssun.designpatterns._03_behavioral_patterns._14_command._01_before.Light;

/**
 * 수정이 발생하면 Command 코드가 바뀐다.
 * 하지만, Button 등의 invoker 들의 코드가 바뀌지 않는다.
 */
public class LightOffCommand implements Command {

    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        new LightOnCommand(this.light).execute();
    }
}
