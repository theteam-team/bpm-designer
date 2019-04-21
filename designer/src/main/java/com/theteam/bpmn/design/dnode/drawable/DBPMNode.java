package com.theteam.bpmn.design.dnode.drawable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.theteam.bpmn.design.dnode.DNode;
import com.theteam.bpmn.design.dnode.DNodeEventHandler;
import com.theteam.bpmn.design.dnode.Line;
import com.theteam.bpmn.design.dnode.NodeConstants;
import com.theteam.bpmn.design.dnode.NodeHeader;
import com.theteam.bpmn.design.dnode.NodeConstants.DrawMethod;
import com.theteam.bpmn.design.dnode.connection.Execute;
import com.theteam.bpmn.design.dnode.model.IBPM;
import com.theteam.bpmn.design.dnode.model.IDrawable;
import com.theteam.bpmn.design.loader.ImagesLoader;

import javafx.scene.Cursor;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class DBPMNode extends DNode implements IDrawable
{
    IBPM ibpm;
    DrawMethod dm;

    public DBPMNode(NodeHeader header, Tooltip tooltip, VBox inputs, VBox outputs, DrawMethod dm, Boolean drawNode)
    {

        String id = UUID.randomUUID().toString();
        setId(id);
        idProperty().set(id);

        this.type = "end";
        this.dm = dm;
        this.drawNode = drawNode;

        defineVariables();

        this.header = header;
        this.tooltip = tooltip;
        this.inputs = inputs;
        this.outputs = outputs;

        dDraw(dm);

        //setOnMouseClicked(DNodeEventHandler.mouseClickedHandler1);
        setOnMousePressed(DNodeEventHandler.mousePressedHandler1);
        setOnMouseReleased(DNodeEventHandler.mouseReleasedHandler1);
        setOnMouseDragged(DNodeEventHandler.mouseDraggedHandler1);
        setOnMouseEntered(DNodeEventHandler.mouseEnteredHandler1);
        setOnMouseExited(DNodeEventHandler.mouseExitedHandler1);


        if(drawNode)
            NodeConstants.sxml.addEndNode(id);
    }

    public void calculateSize()
    {
        
        width = NodeConstants.nodeSize;
        height = NodeConstants.nodeSize;
    }

    public void defineVariables()
    {
        
        tooltip = new Tooltip("Help Text");

        prepareInputs();
        prepareOutputs();

        calculateSize();
        header = new NodeHeader("Test Node", width, height);
    }

    public void prepareInputs()
    {

        Execute ex = new Execute(true);
        input.add(ex);

        inputs.getChildren().addAll(input);
    }

    public void prepareOutputs()
    {

    }

    //
    // DNode Abstract functions

    @Override
    public void setClicked(boolean c)
    {
        clicked = c;
    }


    //
    // IDrawble Functions

    @Override
    public void dDraw(DrawMethod dm)
    {

        calculateSize();

        ImageView im = new ImageView(ImagesLoader.nodeImages.get(type));
        Tooltip.install(im, this.tooltip);
        im.setFitWidth(width);
        im.setFitHeight(height);

        setPickOnBounds(true);

        if(drawNode)
        {
            this.inputs.setSpacing(10);
    
            this.inputs.setTranslateX(-5);
            this.inputs.setTranslateY(height/2);
            
            this.outputs.setSpacing(10);
    
            // to be modified
            this.outputs.setTranslateX(width+5);
            this.outputs.setTranslateY(height/2);
    
            getChildren().addAll(im, header, this.inputs, this.outputs);
            
        }
        
        else
        {
            getChildren().addAll(im, header);
        }


    }

    @Override
    public double dGetX() { return getLayoutX(); }

    @Override
    public double dGetY() { return getLayoutY(); }

    @Override
    public void dSetX(double x) { setLayoutX(x); }

    @Override
    public void dSetY(double y) { setLayoutY(y); }
    
    @Override
    public void setPosition(double x, double y)
    {
        setLayoutX(x);
        setLayoutY(y);
    }

    @Override
    public Color dGetColor() { return color; }

    @Override
    public void dSetColor(Color color) { this.color = color; }

    @Override
    public void dSetCursor(Cursor c) { setCursor(c); }

    @Override
    public boolean dMoveable()
    {
        return false;
    }

    @Override
    public List<Line> getLines()
    {
        List<Line> temp = new ArrayList<>()
        {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean add(Line s)
            {

                if(s != null )
                    return super.add( s );
                return false;
            }
        };

        input.forEach(a -> temp.add(a.getLine()));
        output.forEach(a -> temp.add(a.getLine()));

        return temp;
    }

    @Override
    public void removeNode()
    {
        input.forEach(a -> NodeConstants.controller.getWorkArea().getChildren().remove(a.removeLine()));
        output.forEach(a -> NodeConstants.controller.getWorkArea().getChildren().remove(a.removeLine()));

        NodeConstants.controller.getWorkArea().getChildren().remove(this);

        if(getNextDNode() != null)
            getNextDNode().setPrevDNode(null);

        if(getPrevDNode() != null)
            getPrevDNode().setNextDNode(null);

        NodeConstants.sxml.removeNode(getId());

    }
    
}