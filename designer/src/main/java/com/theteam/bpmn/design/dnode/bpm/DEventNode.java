package com.theteam.bpmn.design.dnode.bpm;

import java.util.UUID;

import com.theteam.bpmn.design.dnode.DNode;
import com.theteam.bpmn.design.dnode.dproperty.DComboBoxProperty;
import com.theteam.bpmn.design.dnode.dproperty.DTextProperty;
import com.theteam.bpmn.design.dnode.model.IDrawable;
import com.theteam.bpmn.design.dnode.model.IEvent;
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
import javafx.scene.image.Image;



public class DEventNode extends DNode implements IDrawable
{

    IEvent e;

    public DEventNode()
    {
        
    }
    
}