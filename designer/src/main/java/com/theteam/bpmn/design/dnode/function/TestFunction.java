package com.theteam.bpmn.design.dnode.function;

import java.util.ArrayList;
import java.util.List;

import com.theteam.bpmn.design.controller.StageController;
import com.theteam.bpmn.design.dnode.model.IDrawable;
import com.theteam.snodes.SXML;

import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;


public class TestFunction
{

    /*
    public TestFunction(StageController controller, SXML sxml, Boolean drawNode)
    {
        this.controller = controller;
        this.sxml = sxml;

        defineVariables();
        Draw(header, tooltip, inputs, outputs);
    }

    @Override
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
    public Color dGetColor() { return Color.AQUA; }

    @Override
    public void dDraw()
    {

    }

    @Override
    public void dSetCursor(Cursor c)
    {
        setCursor(c);
    }

    @Override
    public boolean dMoveable()
    {
        return false;
    }

    @Override
    public List<L> getLines()
    {
        List<L> temp = new ArrayList<>()
        {
            @Override
            public boolean add(L s ) {

                if( s != null ) {
                    return super.add( s );
                }
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
        
        input.forEach(a -> controller.getWorkArea().getChildren().remove(a.removeLine()));
        output.forEach(a -> controller.getWorkArea().getChildren().remove(a.removeLine()));

        controller.getWorkArea().getChildren().remove(this);
        
    }

    */


}