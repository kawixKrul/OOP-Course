package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.List;


public class SimulationPresenter implements MapChangeListener {
    @FXML
    public Label descriptionLabel;
    @FXML
    private TextField movementTextField;
    @FXML
    private GridPane mapGrid;
    private WorldMap map;
    private final int CELL_WIDTH = 50;
    private final int CELL_HEIGHT = 50;

    public void setWorldMap(WorldMap map) {
        this.map = map;
    }

    private void drawMap() {
        clearGrid();
        Boundary boundary = map.getCurrentBounds();
        Label mainLabel = new Label("y/x");
        GridPane.setHalignment(mainLabel, HPos.CENTER);
        mapGrid.add(mainLabel, 0, 0,1 ,1);
        mapGrid.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
        mapGrid.getRowConstraints().add(new RowConstraints(CELL_HEIGHT));

        for (int i = boundary.lowerLeft().getX(), colIdx = 1; i <= boundary.upperRight().getX(); i++, colIdx++) {
            Label label = new Label(String.valueOf(i));
            mapGrid.add(label, colIdx, 0, 1, 1);
            mapGrid.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
            GridPane.setHalignment(label, HPos.CENTER);
        }

        for (int i = boundary.lowerLeft().getY(), rowIdx = 1; i <= boundary.upperRight().getY(); i++, rowIdx++) {
            Label label = new Label(String.valueOf(i));
            mapGrid.add(label, 0, rowIdx, 1, 1);
            mapGrid.getRowConstraints().add(new RowConstraints(CELL_HEIGHT));
            GridPane.setHalignment(label, HPos.CENTER);
        }

        for (WorldElement element: map.getElements()) {
            Vector2d pos = element.getPosition();
            Label label = new Label(element.toString());
            GridPane.setHalignment(label, HPos.CENTER);
            if (element.getClass().equals(map.objectAt(pos).getClass())) {
                mapGrid.add(label, pos.getX() - boundary.lowerLeft().getX() + 1, boundary.upperRight().getY() - pos.getY() + 1, 1, 1);
            }
        }
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        Platform.runLater(() -> {
            this.descriptionLabel.setText(message);
            this.drawMap();
        });
    }

    public void onSimulationStartClicked(ActionEvent actionEvent) {
        List<String> args = List.of(movementTextField.getText().split(" "));
        List<MoveDirection> directions = OptionsParser.parse(args.toArray(new String[0]));
        Simulation simulation = new Simulation(directions, List.of(
                new Vector2d(2, 2),
                new Vector2d(7, 5),
                new Vector2d(3, 8)),
                map);
        var simulationEngine = new SimulationEngine(List.of(simulation));
        simulationEngine.runAsyncInThreadPool();
    }

    private void clearGrid() {
        mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0)); // hack to retain visible grid lines
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }
}
