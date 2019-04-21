package com.theteam.bpmn.design.dnode.model;

import com.theteam.bpmn.design.dnode.Line;

import javafx.scene.paint.Color;

public interface IConnectable 
{

    public double getX();
    public double getY();

    public Color getColor();
    public void setColor(Color p);

    public boolean getConnected();

    public void setConnectedEnabled();
    public void setConnectedDisabled();

    public void removeLine();
    public void setLine(Line line);
    public Line getLine();

    
}