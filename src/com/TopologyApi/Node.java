package com.TopologyApi;


/**
 * Node class represents a node in a device
 */
public class Node {
    /**
     * ID represents the ID of the node
     */
    private final String ID;

    /**
     * Explicit value Constructor for Node
     * @param ID parameter for intializing id of the node.
     */
    public Node(String ID) {
        this.ID = ID;
    }
    public Node() {
        this.ID = "";
    }

    /**
     * this is getID() method
     * @return ID of a Node object
     */
    public String getID() {
        return ID;
    }
}
