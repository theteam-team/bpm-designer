package com.theteam.bpmn.design.dnode.connection;



import com.theteam.bpmn.design.dnode.DNodeEventHandler;
import com.theteam.bpmn.design.dnode.Line;
import com.theteam.bpmn.design.dnode.NodeConstants;
import com.theteam.bpmn.design.dnode.model.IConnectable;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.effect.Bloom;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;

public class ExecutionConnection extends Polygon implements IConnectable
{

    private Boolean connected = false;

    private Color color = null;

    private Line line = null;

    public ExecutionConnection(Color color)
    {

        this.color = color;

        getPoints().addAll(new Double[]{
            0.0, 0.0,
            10.0, 5.0,
            0.0, 10.0 });

        setFill(null);
        setStroke(Color.WHITE);
        setStrokeWidth(1);

        setCache(true);
        setEffect(new Bloom());

        setId("connectable");

        setPickOnBounds(true);

        setOnMouseClicked(DNodeEventHandler.mouseClickedHandler1);

    }

    public Color getColor()
    {
        return color;
    }

    public void setColor(Color c)
    {
        color = c;
    }

    public double getX()
    {
        Bounds boundsInScene = localToScene(getBoundsInLocal());
        return (boundsInScene.getMinX() - NodeConstants.drawAreaLocalToScene.getMinX());
    }
    
    public double getY()
    {
        Bounds boundsInScene = localToScene(getBoundsInLocal());
        return (boundsInScene.getMinY() - NodeConstants.drawAreaLocalToScene.getMinY());
    }

    @Override
    public boolean getConnected()
    {
        return connected;
    }

    @Override
    public void setConnectedEnabled()
    {
        connected = true;
        setFill(color);
    }

    @Override
    public void setConnectedDisabled()
    {
        connected = false;
        setFill(null);
    }

    @Override
    public void removeLine()
    {
        setConnectedDisabled();
    }

    @Override
    public void setLine(Line line)
    {
        this.line = line;
    }

    @Override
    public Line getLine()
    {
        return line;
    }
}