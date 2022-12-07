package com.designpattern.report._14_command.step2_after;

import com.designpattern.report._14_command.step2_after.command.Command;
import com.designpattern.report._14_command.step2_after.concreteCommand.GameStartCommand;

public class MyApp {

    private Command command;

    public MyApp(Command command) {
        this.command = command;
    }

    public void press() {
        command.execute();
    }

    public static void main(String[] args) {
        MyApp myApp = new MyApp(new GameStartCommand(new Game()));
    }
}
