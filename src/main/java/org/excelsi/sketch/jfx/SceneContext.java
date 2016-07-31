package org.excelsi.sketch.jfx;


import java.util.HashMap;
import java.util.Map;

import com.jme3.scene.Node;
import com.jme3.scene.Spatial;


public class SceneContext {
    private final NodeFactory _nfactory;
    private final Map<String,Spatial> _objects = new HashMap<>();
    private final Node _root;


    public SceneContext(final Node root, final NodeFactory nfactory) {
        _nfactory = nfactory;
        addNode(root);
        _root = root;
    }

    public Node getRoot() {
        return _root;
    }

    public NodeFactory getNodeFactory() {
        return _nfactory;
    }

    public Node getNode(final String name) {
        return (Node) _objects.get(name);
    }

    public Spatial getSpatial(final String name) {
        return _objects.get(name);
    }

    public void addNode(final Spatial node) {
        final String name = node.getName();
        if(_objects.containsKey(name)) {
            throw new IllegalArgumentException("already contains node named '"+name+"': "+_objects.get(name));
        }
        _objects.put(name, node);
    }
}
