package com.theteam.bpmn.design.dnode;

import com.theteam.bpmn.design.controller.StageController;
import com.theteam.snodes.SXML;

import javafx.geometry.Bounds;

public final class NodeConstants
{
    public static int nodeSize = 70;
    public static int nodeSize1 = 5;

    public static Bounds drawAreaLocalToScene = null;

    public static SXML sxml;
    public static StageController controller;

    public static enum DrawMethod
    {
        Image,
        Square
    }
}