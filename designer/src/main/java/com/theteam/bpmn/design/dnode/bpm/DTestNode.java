package com.theteam.bpmn.design.dnode.bpm;

import java.util.UUID;

import com.theteam.bpmn.design.dnode.dproperty.DComboBoxProperty;
import com.theteam.bpmn.design.dnode.dproperty.DTextProperty;
import com.theteam.bpmn.design.loader.ImagesLoader;
import com.theteam.snodes.SXML;

import javafx.beans.property.StringProperty;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SingleSelectionModel;


/**
 * DServiceTaskNode ** Service Task **
 * All nodes are ImageView Nodes from the javafx library
 * So all dnodes can be rendered using javafx application
 * All dnodes are represented by images
 */
public class DTestNode extends DNode
{

    public DTestNode(String id)
    {
        super();

        hoverProperty().addListener((observable) -> {
            
        });
        
        setPickOnBounds(true);

        allDProperties = new ArrayList<DProperty>();

        DProperty dIdProperty = new DTextProperty("ID", idProperty());

        allDProperties.add(dIdProperty);

        setId(id);

        clicked.addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue)
            {
                if(newValue)
                    setImage(ImagesLoader.nodeImages.get(getDType() + "_chosen"));
                else
                    setImage(ImagesLoader.nodeImages.get(getDType()));
            }
        });

        nextDNode = null;
        prevDNode = null;

        setOnMouseClicked(DNodeEventHandler.mouseClickedHandler1);
        setOnMousePressed(DNodeEventHandler.mousePressedHandler1);
        setOnMouseReleased(DNodeEventHandler.mouseReleasedHandler1);
        setOnMouseDragged(DNodeEventHandler.mouseDraggedHandler1);
        setOnMouseEntered(DNodeEventHandler.mouseEnteredHandler1);
        setOnMouseExited(DNodeEventHandler.mouseExitedHandler1);
    }

    public DNode(Image im, String id)
    {
        this(id);
        setImage(im);
    }
}