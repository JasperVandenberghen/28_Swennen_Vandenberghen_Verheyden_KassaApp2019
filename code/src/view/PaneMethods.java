package view;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class PaneMethods {
    public static void addToGridPaneAscendingColumn(Node[] nodes, GridPane pane, int row){
        for(int i = 0; i<nodes.length; i++){
            pane.add(nodes[i], i, row);
        }
    }
    public static void addToGridPaneAscendingRow(Node[] nodes, GridPane pane, int column){
        for(int i = 0; i<nodes.length; i++){
            pane.add(nodes[i], column, i);
        }
    }
}
