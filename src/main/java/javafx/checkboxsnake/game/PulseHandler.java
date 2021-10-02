package javafx.checkboxsnake.game;

import javafx.checkboxsnake.data.Position;
import javafx.checkboxsnake.data.ViewModel;

public abstract class PulseHandler {

    protected ViewModel viewModel;

    public PulseHandler(ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public abstract void handlePulse();

    public abstract void reset(Position startingPosition);
}
