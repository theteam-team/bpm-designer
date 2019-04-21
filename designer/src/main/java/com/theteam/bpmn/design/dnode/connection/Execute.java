package com.theteam.bpmn.design.dnode.connection;

import com.sun.scenario.effect.Bloom;
import com.theteam.bpmn.design.dnode.DNodeEventHandler;
import com.theteam.bpmn.design.dnode.Line;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;

public class Execute extends PropertyBox
{

    ExecutionConnection triangle;
    public Execute(Boolean in)
    {

        triangle = new ExecutionConnection(Color.WHITE);

        if(in)
            setAlignment(Pos.CENTER_LEFT);
        else
            setAlignment(Pos.CENTER_RIGHT);

        getChildren().addAll(triangle);
    }

    @Override
    public Line getLine()
    {
        return triangle.getLine();
    }

    @Override
    public Line removeLine()
    {
        Line line = triangle.getLine();
        
        if(line != null)
            line.removeLine();

        return line;
    }

}