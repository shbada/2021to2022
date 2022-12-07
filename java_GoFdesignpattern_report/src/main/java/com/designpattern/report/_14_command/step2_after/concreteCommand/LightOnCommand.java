package com.designpattern.report._14_command.step2_after.concreteCommand;

import com.designpattern.report._14_command.step2_after.Light;
import com.designpattern.report._14_command.step2_after.command.Command;

public class LightOnCommand implements Command {

    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        new LightOffCommand(this.light).execute();
    }
}
