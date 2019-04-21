package com.theteam.bpmn.design.dnode.drawable;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import com.theteam.bpmn.design.controller.StageController;
import com.theteam.bpmn.design.dnode.DNode;
import com.theteam.bpmn.design.dnode.DNodeEventHandler;
import com.theteam.bpmn.design.dnode.Line;
import com.theteam.bpmn.design.dnode.NodeConstants;
import com.theteam.bpmn.design.dnode.NodeHeader;
import com.theteam.bpmn.design.dnode.NodeConstants.DrawMethod;
import com.theteam.bpmn.design.dnode.connection.Execute;
import com.theteam.bpmn.design.dnode.connection.StrProp;
import com.theteam.bpmn.design.dnode.model.IDrawable;
import com.theteam.bpmn.design.loader.ImagesLoader;
import com.theteam.snodes.SXML;

import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

import lombok.Getter;
import lombok.Setter;


public class DFunctionNode extends DNode implements IDrawable
{
    private Rectangle shape;

    protected MaterialIconView removeIcon;

    protected int maxNumberOfV = 0;
    protected int maxNumberOfLH = 0;
    protected int maxNumberOfRH = 0;

    DrawMethod dm;

    public DFunctionNode(NodeHeader header, Tooltip tooltip, VBox inputs, VBox outputs, DrawMethod dm, Boolean drawNode)
    {

        String id = UUID.randomUUID().toString();
        setId(id);
        idProperty().set(id);

        this.type = "function";
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

    }

    public void calculateSize()
    {
        if(dm == DrawMethod.Square)
        {
            // to be modified
            width = 200;
            height = 150;

        }

        else if(dm == DrawMethod.Image)
        {
            width = NodeConstants.nodeSize;
            height = NodeConstants.nodeSize;
        }
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
        StrProp s = new StrProp(true);


        input.add(ex);
        input.add(s);

        inputs.getChildren().addAll(input);
    }

    public void prepareOutputs()
    {
        Execute ex = new Execute(false);
        StrProp s = new StrProp(false);

        output.add(ex);
        output.add(s);

        outputs.getChildren().addAll(output);
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
        getChildren().clear();

        if(dm == DrawMethod.Square)
        {
            calculateSize();

            shape = new Rectangle();
            shape.setArcWidth(10);
            shape.setArcHeight(10);
            shape.setFill(null);
            shape.setStroke(Color.BLACK);
            shape.setStrokeType(StrokeType.CENTERED);
            shape.setStrokeWidth(2);
    
            Tooltip.install(shape, this.tooltip);

            shape.setWidth(width);
            shape.setHeight(height);

            setPickOnBounds(true);

            if(drawNode)
            {
                this.inputs.setSpacing(10);
        
                this.inputs.setTranslateX(10);
                this.inputs.setTranslateY(50);
                
                this.outputs.setSpacing(10);
        
                // to be modified
                this.outputs.setTranslateX(170);
                this.outputs.setTranslateY(50);
                
        
                removeIcon = new MaterialIconView(MaterialIcon.CLOSE, "17");
                removeIcon.setOnMouseClicked(me -> removeNode());
                removeIcon.setTranslateX(180);
                removeIcon.setTranslateY(20);
        
                getChildren().addAll(shape, header, this.inputs, this.outputs, removeIcon);
            }

            else
            {
                getChildren().addAll(shape, header);
            }
    
        }

        else if(dm == DrawMethod.Image)
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
    
                this.outputs.setTranslateX(width+5);
                this.outputs.setTranslateY(height/2);
            }

            else
            {
                getChildren().addAll(im, header);
            }
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

    }

    
}