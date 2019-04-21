package com.theteam.bpmn.design.dnode.connection;

import com.jfoenix.controls.JFXTextField;
import com.theteam.bpmn.design.dnode.DNodeEventHandler;
import com.theteam.bpmn.design.dnode.Line;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class StrProp extends PropertyBox
{

    NormalConnection circle = null;
    public StrProp(Boolean in)
    {
        circle = new NormalConnection(Color.DARKCYAN);

        Text text = new Text("a");

        JFXTextField textField = new JFXTextField("10");
        textField.setMinSize(25, 10);
        textField.setMaxSize(30, 10);

        if(in)
        {
            setAlignment(Pos.CENTER_LEFT);
            getChildren().addAll(circle, text, textField);
            
        }
        else
        {
            setAlignment(Pos.CENTER_RIGHT);
            getChildren().addAll(text, circle);
        }

    }

    @Override
    public Line getLine()
    {
        return circle.getLine();
    }

    @Override
    public Line removeLine()
    {
        Line line = circle.getLine();
        
        if(line != null)
            line.removeLine();

        return line;
    }
}