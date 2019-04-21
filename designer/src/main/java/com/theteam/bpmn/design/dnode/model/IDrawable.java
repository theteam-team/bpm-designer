package com.theteam.bpmn.design.dnode.model;

import java.util.List;

import com.theteam.bpmn.design.dnode.Line;
import com.theteam.bpmn.design.dnode.NodeConstants.DrawMethod;

import javafx.scene.Cursor;
import javafx.scene.paint.Color;

public interface IDrawable 
{

    public double dGetX();
    public double dGetY();

    public void dSetX(double x);
    public void dSetY(double y);

    public void setPosition(double x, double y);

    public Color dGetColor();
    public void dSetColor(Color c);

    public void dDraw(DrawMethod dm);

    public void dSetCursor(Cursor c);

    public boolean dMoveable();

    public List<Line> getLines();

    public void removeNode();

}