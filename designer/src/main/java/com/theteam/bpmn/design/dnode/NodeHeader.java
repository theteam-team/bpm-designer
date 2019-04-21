package com.theteam.bpmn.design.dnode;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class NodeHeader extends Group
{
    public NodeHeader(String name, double width, double height)
    {
        Rectangle shape = new Rectangle();
        shape.setArcWidth(10);
        shape.setArcHeight(10);
        shape.setFill(null);
        shape.setStroke(Color.BLACK);
        shape.setStrokeWidth(2);

        shape.setWidth(width);
        shape.setHeight(height);

        Text nameText = new Text(getLayoutX()+20, getLayoutY()+20, name);
        nameText.setFont(Font.font(14));

        getChildren().addAll(shape, nameText);
    }
}