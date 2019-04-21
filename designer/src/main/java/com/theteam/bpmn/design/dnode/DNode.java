package com.theteam.bpmn.design.dnode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.theteam.bpmn.design.controller.StageController;
import com.theteam.bpmn.design.dnode.connection.PropertyBox;
import com.theteam.bpmn.design.dnode.dproperty.DProperty;
import com.theteam.bpmn.design.dnode.dproperty.DTextProperty;
import com.theteam.bpmn.design.loader.ImagesLoader;
import com.theteam.snodes.SNode;
import com.theteam.snodes.SXML;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


/**
 * The parent class for all design nodes (dnodes)
 * All nodes are ImageView Nodes from the javafx library
 * So all dnodes can be rendered using javafx application
 * All dnodes are represented by images
 */
public abstract class DNode extends Group
{

    protected SNode snode;

    protected String type;

    protected Color color;

    protected NodeHeader header;
    protected Tooltip tooltip;

    protected List<PropertyBox> input = new LinkedList<>();
    protected List<PropertyBox> output = new LinkedList<>();

    protected double width;
    protected double height;

    protected DNode nextDNode;
    protected DNode prevDNode;

    protected VBox inputs = new VBox();
    protected VBox outputs = new VBox();

    protected List<DProperty> allDProperties;

    //protected BooleanProperty clicked = new SimpleBooleanProperty(this, "clicked", false);
    protected Boolean clicked = false;

    protected boolean drawNode = false;

    public abstract void setClicked(boolean c);

    public boolean getClicked() { return clicked; }

    public double getWidth() { return width; }
    public void setWidth(double width) { this.width = width; }
    
    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; }

    public String getType() { return this.type; }
    public void setType(String type) { this.type = type; }

    public List<DProperty> getIDPropertNode() { return allDProperties; }

    public boolean isDrawNode() { return drawNode; }
    public void setDrawNode(boolean i) { drawNode = i; }

    public DNode getNextDNode() { return this.nextDNode; }
    public void setNextDNode(DNode node)
    {
        this.nextDNode = node;

        if(node != null)
            NodeConstants.sxml.setNextNode(getId(), node.getId());
        else
            NodeConstants.sxml.setNextNode(getId(), null);
        
    }

    public DNode getPrevDNode() { return this.prevDNode; }
    public void setPrevDNode(DNode node)
    { 
        this.prevDNode = node;

        if(node != null)
            NodeConstants.sxml.setPrevNode(getId(), node.getId());
        else
            NodeConstants.sxml.setPrevNode(getId(), null);
            

    }

}