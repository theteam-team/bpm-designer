package com.theteam.bpmn.design.controller;


import com.theteam.bpmn.design.dnode.*;
import com.theteam.bpmn.design.dnode.event.DExternalEvent;
import com.theteam.bpmn.design.dnode.event.DTimerEvent;
import com.theteam.bpmn.design.dnode.model.IConnectable;
import com.theteam.bpmn.design.dnode.model.IDrawable;
import com.theteam.bpmn.design.io.Variable;
import com.theteam.snodes.SXML;

import org.reflections.Reflections;
import org.reflections.Reflections.*;

import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;


import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

import com.jfoenix.controls.JFXTextField;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;

import javafx.fxml.FXML;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


/**
 * Control BPMN Stage Window (Nodes, Tools, properties, ...)
 */
public class BPMNStageController implements StageController
{

    @FXML
    public AnchorPane drawArea;

    @FXML
    private VBox gridNodes;

    @FXML
    public VBox propertiesView;
    
    @FXML
    public VBox variablesView;

    private ToolBar toolBar;

    private StringProperty logText = new SimpleStringProperty();

    public DNode selectedNode = null;
    public DNode firstNode = null;
    public DNode secondNode = null;

    public IConnectable firstNode1 = null;
    public IConnectable secondNode1 = null;

    Tab tab;

    SXML sxml;

    Integer i = 0;

    StageController controller;

    public void createNode(Class<? extends DNode> c)
    {
        try { IDrawable node = (IDrawable) c.getDeclaredConstructor().newInstance(); }
        catch(Exception e) { }
    }

    public void loadNodes()
    {
        sxml = new SXML();

        sxml.setWorkflowName("design");

        controller = this;

        NodeConstants.sxml = sxml;
        NodeConstants.controller = controller;

        Reflections reflections = new Reflections("com.theteam.bpmn.design.dnode.drawable");

        Set<Class<? extends DNode>> drawbleNodes = reflections.getSubTypesOf(DNode.class);
        //drawbleNodes.forEach(c -> createNode(c));

        // ADD NODE

        DStartNode start = new DStartNode(UUID.randomUUID(), false);

        DEndNode end = new DEndNode(UUID.randomUUID(), false);

        DServiceTaskNode serviceTask = new DServiceTaskNode(UUID.randomUUID(), false);

        DDBNode db = new DDBNode(UUID.randomUUID(), false);

        DExternalEvent ext = new DExternalEvent(UUID.randomUUID(), false);

        DTimerEvent timer = new DTimerEvent(UUID.randomUUID(), false);

        DScriptNode script = new DScriptNode(UUID.randomUUID(), false);

        DTestNode test = new DTestNode(UUID.randomUUID(), false);
        
        gridNodes.getChildren().add(start);
        gridNodes.getChildren().add(end);
        gridNodes.getChildren().add(serviceTask);
        gridNodes.getChildren().add(db);
        gridNodes.getChildren().add(ext);
        gridNodes.getChildren().add(timer);
        gridNodes.getChildren().add(script);
        gridNodes.getChildren().add(test);
        

        MaterialIconView saveIcon = new MaterialIconView(MaterialIcon.SAVE, "25");
        saveIcon.setOnMouseClicked(saveButtonHandler);

        MaterialIconView addIcon = new MaterialIconView(MaterialIcon.ADD_CIRCLE, "25");
        addIcon.setOnMouseClicked(addVariableButtonHandler);

        MaterialIconView clearIcon = new MaterialIconView(MaterialIcon.REMOVE_CIRCLE, "25");
        clearIcon.setOnMouseClicked(clearButtonHandler);

        toolBar.getItems().addAll(saveIcon, addIcon, clearIcon);

    }

    public void setLogText(JFXTextField logText)
    {
        logText.textProperty().bind(this.logText);
    }

    public void setToolBar(ToolBar toolBar)
    {
        this.toolBar = toolBar;
    }

    public void setTab(Tab tab)
    {
        this.tab = tab;
    }

    @Override
    public Pane getWorkArea()
    {
        return drawArea;
    }


    @FXML
    private void drawAreaClicked(MouseEvent me)
    {

        if(!me.isStillSincePress())
        {
            return;
        }

        if(selectedNode == null)
        {
            logText.set("Select a Node");
            return;
        }

        PickResult pe = me.getPickResult();
        Node n = pe.getIntersectedNode();

        if(n.getId() == null)
            return;

        if(n.getId().equals("drawArea"))
        {

            TestFunction node = new TestFunction(controller, sxml, true);
            drawArea.getChildren().add(node);

            node.setLayoutX(me.getX());
            node.setLayoutY(me.getY());

            logText.set("Node Created");

            //node.setX(me.getX());
            //node.setY(me.getY());
            /*
            switch (selectedNode.getDType())
            {

                // ADD NODE
                
                case "start":
                    createDrawNode(new DStartNode(sxml, UUID.randomUUID(), true), me);
                    logText.set("Start Node Created");
                    break;
    
                case "end":
                    createDrawNode(new DEndNode(sxml, UUID.randomUUID(), true), me);
                    logText.set("End Node Created");
                    break;
    
                case "service_task":
                    createDrawNode(new DServiceTaskNode(sxml, UUID.randomUUID(), true), me);
                    logText.set("Service Task Node Created");
                    break;

                case "db":
                    createDrawNode(new DDBNode(sxml, UUID.randomUUID(), true), me);
                    logText.set("Database Node Created");
                    break;

                case "external":
                    createDrawNode(new DExternalEvent(sxml, UUID.randomUUID(), true), me);
                    logText.set("External Node Created");
                    break;
    
                case "timer":
                    createDrawNode(new DTimerEvent(sxml, UUID.randomUUID(), true), me);
                    logText.set("Timer Node Created");
                    break;
    
                case "script":
                    createDrawNode(new DScriptNode(sxml, UUID.randomUUID(), true), me);
                    logText.set("Script Task Node Created");
                    break;

                case "triangle":
                    createDrawNode(new DTestNode(sxml, UUID.randomUUID(), true), me);
                    logText.set("Test Node Created");
                    break;
            
                default:
                    break;
            }
            */

        }

    }

    private void createDrawNode(DNode node, MouseEvent me)
    {
        node.setFitHeight(NodeConstants.nodeSize);
        node.setFitWidth(NodeConstants.nodeSize);

        //node.setId(UUID.randomUUID().toString());

        drawArea.getChildren().add(node);

        node.setX(me.getX()-node.getFitWidth()/2);
        node.setY(me.getY()-node.getFitHeight()/2);

    }

    public void createLine(DNode nodeFrom, DNode nodeTo)
    {

        if(nodeFrom.getNextDNode() != null)
        {
            sxml.setPrevNode(nodeFrom.getNextDNode().getId(), null);
            //setNextDNode(null);
            nodeFrom.getNextDNode().setPPrevDNode(null);
        }

        if(nodeTo.getPrevDNode() != null)
        {
            sxml.setNextNode(nodeTo.getPrevDNode().getId(), null);
            //setPrevDNode(null);
            nodeTo.getPrevDNode().setNNextDNode(null);
        }

        for (DLine l : nodeFrom.getStartLines()) {
            drawArea.getChildren().remove(l);
        }

        for (DLine l : nodeTo.getEndLines()) {
            drawArea.getChildren().remove(l);
        }

        nodeFrom.getStartLines().clear();
        nodeTo.getEndLines().clear();

        DLine line = new DLine(nodeFrom, nodeTo);

        nodeFrom.getStartLines().add(line);
        nodeTo.getEndLines().add(line);

        
        drawArea.getChildren().add(line);
    }

    public void createLine1(IConnectable nodeFrom, IConnectable nodeTo , Color c)
    {
        

        if(nodeFrom.getColor() == nodeTo.getColor())
        {
            L line = new L(nodeFrom, nodeTo, c);
            drawArea.getChildren().add(line);

            logText.set("Line Created");
            
        }
        
        else
        {
            nodeFrom.setConnectedDisabled();
            nodeTo.setConnectedDisabled();

            logText.set("Connection couldn't be established");
        }
    }

    public EventHandler<MouseEvent> saveButtonHandler =  new EventHandler<MouseEvent>()
    {
        @Override
        public void handle(MouseEvent me)
        {

            // Change tab name here
            //
            //
            //
            //
            //
            try
            {
                //sxml.saveToXML("../xml/nodeXML.xml");
                sxml.saveToXML("../xml/" + tab.getText() + ".xml");
                logText.set("Saved to : " + tab.getText() + ".xml");
            } catch(Exception e)
            {
                System.out.println(e);
            }
        }
    };

    @FXML
    public void nameChanged(KeyEvent me)
    {
        String name = ((JFXTextField) me.getSource()).getText();

        tab.setText(name);
        sxml.setWorkflowName(name);
    }

    public EventHandler<MouseEvent> clearButtonHandler =  new EventHandler<MouseEvent>()
    {
        @Override
        public void handle(MouseEvent me)
        {
            drawArea.getChildren().clear();
            sxml.clear();
        }
    };

    List<StringProperty> n = new LinkedList<>();
    List<StringProperty> v = new LinkedList<>();
    public EventHandler<MouseEvent> addVariableButtonHandler =  new EventHandler<MouseEvent>()
    {
        @Override
        public void handle(MouseEvent me)
        {
            StringProperty nameProperty = new SimpleStringProperty();
            n.add(nameProperty);
            nameProperty.set("var" + i.toString());
            i += 1;

            StringProperty valueProperty = new SimpleStringProperty();
            v.add(valueProperty);
            valueProperty.set("null");

            //SingleSelectionModel<String> type;

            ObservableList<String> typeList = FXCollections.observableArrayList(
                "string",
                "int",
                "float",
                "bool"

            );

            Variable var = new Variable(nameProperty,
                                        typeList,
                                        valueProperty);

            String id = UUID.randomUUID().toString();

            var.setId(id);
            
            sxml.addVariable(id, nameProperty.get(), (String) var.getComboSelectionModel().getSelectedItem(), valueProperty.get());

            nameProperty.addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue)
                {
                    sxml.setVariableName(var.getId(), newValue);
                }
            });

            valueProperty.addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue)
                {
                    sxml.setVariableValue(var.getId(), newValue);
                }
            });
            
            var.getComboSelectionModel().selectedIndexProperty().addListener((Observable o) ->
            {
                String selectedItem = (String) var.getComboSelectionModel().getSelectedItem();
                sxml.setVariableType(var.getId(), selectedItem);
                
            });

            variablesView.getChildren().add(var);

        }
    };
}
