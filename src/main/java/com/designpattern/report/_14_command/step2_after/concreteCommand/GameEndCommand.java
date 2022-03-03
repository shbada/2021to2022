package com.designpattern.report._14_command.step2_after.concreteCommand;

import com.designpattern.report._14_command.step2_after.Game;
import com.designpattern.report._14_command.step2_after.command.Command;

public class GameEndCommand implements Command {

    private Game game;

    public GameEndCommand(Game game) {
        this.game = game;
    }

    @Override
    public void execute() {
        game.end();
    }

    @Override
    public void undo() {
        new GameStartCommand(this.game).execute();
    }
}
