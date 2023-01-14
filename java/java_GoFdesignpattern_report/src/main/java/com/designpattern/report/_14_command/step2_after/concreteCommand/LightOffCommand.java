package com.designpattern.report._14_command.step2_after.concreteCommand;

import com.designpattern.report._14_command.step2_after.Light;
import com.designpattern.report._14_command.step2_after.command.Command;
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
