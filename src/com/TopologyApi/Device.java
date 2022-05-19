package com.TopologyApi;
import org.json.simple.JSONObject;

/**
 * Device class is an abstract class which all device types will inherit from.
 * There are two Classes inherit Device class Resistant and NMOS. Other Devices can be added as needed.
 * @see Resistant
 * @see NMOS
 */
public abstract class Device {
    protected String type,ID;
    protected double min, max, Default;

    /**
     *  getID() method
     * @return ID of the device.
     */
    public String getID(){
        return ID;
    }

    /**
     *  ConvertToJson() is an abstract method which is used to convert Device object to a <code>jsonObject</code> .
     * @return the <code>jsonObject</code> .
     */
     public abstract JSONObject ConvertToJson();

    /**
     * isNodeExist() is the second abstract method in abstact Device class
     * It's used for searching if a Node exists in the device or not.
     * @param NodeID String object represents the node to be search in the Device object.
     * @return returns true if the node exist, otherwise return false.
     */
     public abstract boolean isNodeExist(String NodeID);
}
