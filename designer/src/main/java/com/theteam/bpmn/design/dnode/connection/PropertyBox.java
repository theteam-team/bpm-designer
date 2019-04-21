package com.theteam.bpmn.design.dnode.connection;

import com.theteam.bpmn.design.dnode.Line;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.HBox;

public abstract class PropertyBox extends HBox
{
    public PropertyBox()
    {
        setSpacing(10);
    }

    public abstract Line getLine();
    public abstract Line removeLine();
    
}