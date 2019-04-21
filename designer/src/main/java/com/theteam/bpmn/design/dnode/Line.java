package com.theteam.bpmn.design.dnode;


import com.theteam.bpmn.design.dnode.NodeConstants;
import com.theteam.bpmn.design.dnode.model.IConnectable;


import javafx.scene.effect.Bloom;
import javafx.scene.paint.Color;


import javafx.scene.shape.CubicCurveTo;

import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;


/**
 * Draw a line between nodes
 * Lines could be drawn as straight line or as a cubic curve
 * Lines aren't considered design nodes (dnodes) as such
 * It doesn't inherit from dnode
 * Should be move to a new package
 */
public class Line extends Path
{

    double startX;
    double startY;
    double endX;
    double endY;
    double controlX1;
    double controlY1;
    double controlX2;
    double controlY2;
    double arrowHeadSize;

    private Color color;

    // Assign them
    private IConnectable from = null;
    private IConnectable to = null;

    private MoveTo moveTo = null;
    private CubicCurveTo cubicCurveTo = null;
    private LineTo lineTo = null;

    private static final double defaultArrowHeadSize = 3.0;
    
    public Line(double startX, double startY, double endX, double endY,
     double controlX1, double controlY1 ,  double controlX2, double controlY2, double arrowHeadSize, Color color){

        super();

        this.color = color;

        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.controlX1 = controlX1;
        this.controlY1 = controlY1;
        this.controlX2 = controlX2;
        this.controlY2 = controlY2;
        this.arrowHeadSize = arrowHeadSize;
        
        // Stop[] stops = new Stop[] { new Stop(0, Color.VIOLET), new Stop(1, Color.DARKCYAN)};
        // LinearGradient lg = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);

        setStroke(this.color);
        setStrokeWidth(2);
        setFill(null);

        setEffect(new Bloom());
        
        moveTo = new MoveTo(startX, startY);
        cubicCurveTo = new CubicCurveTo(controlX1, controlY1, controlX2, controlY2, endX, endY);
        lineTo = new LineTo(endX, endY);

        getElements().add(moveTo);


        if(Math.abs(endY - startY) > 40) 
            getElements().add(cubicCurveTo);
        else
            getElements().add(lineTo);
        
    }
    
    public Line(double startX, double startY, double endX, double endY,
    double controlX1, double controlY1 ,  double controlX2, double controlY2, Color color){

        this(startX, startY, endX, endY,
                controlX1, controlY1, controlX2, controlY2, defaultArrowHeadSize, color);
    }

    public Line(IConnectable nodeFrom, IConnectable nodeTo, Color color)
    {

        this(   nodeFrom.getX()+NodeConstants.nodeSize1,
                nodeFrom.getY()+NodeConstants.nodeSize1/2,
                nodeTo.getX(),
                nodeTo.getY()+NodeConstants.nodeSize1/2,
                nodeFrom.getX()+NodeConstants.nodeSize1+100,
                nodeFrom.getY()+NodeConstants.nodeSize1/2,
                nodeTo.getX()-100,
                nodeTo.getY()+NodeConstants.nodeSize1/2,
                color);

        from = nodeFrom;
        to = nodeTo;

        from.setLine(this);
        to.setLine(this);

    }

    public IConnectable getNodeFrom() { return from; }
    public IConnectable getNodeTo() { return to; }


    public void changeLinePosition()
    {
        getElements().clear();

        this.startX = from.getX()+NodeConstants.nodeSize1;
        this.startY = from.getY()+NodeConstants.nodeSize1/2;
        this.controlX1 = from.getX()+NodeConstants.nodeSize1+100;
        this.controlY1 = from.getY()+NodeConstants.nodeSize1/2;

        this.endX = to.getX();
        this.endY = to.getY()+NodeConstants.nodeSize1/2;
        this.controlX2 = to.getX()-100;
        this.controlY2 = to.getY()+NodeConstants.nodeSize1/2;
        
        moveTo = new MoveTo(startX, startY);
        cubicCurveTo = new CubicCurveTo(controlX1, controlY1, controlX2, controlY2, endX, endY);
        lineTo = new LineTo(endX, endY);

        getElements().add(moveTo);


        if(Math.abs(endY - startY) > 40) 
            getElements().add(cubicCurveTo);

        else
            getElements().add(lineTo);

    }

    public void removeLine()
    {
        from.removeLine();
        to.removeLine();
    }

}