package com.designpattern.report._14_command.step2_after.concreteCommand;

import com.designpattern.report._14_command.step2_after.Game;
import com.designpattern.report._14_command.step2_after.command.Command;

public class GameStartCommand implements Command {

    private Game game;

    public GameStartCommand(Game game) {
        this.game = game;
    }

    @Override
    public void execute() {
        game.start();
    }

    @Override
    public void undo() {
        new GameEndCommand(this.game).execute();
    }
}
