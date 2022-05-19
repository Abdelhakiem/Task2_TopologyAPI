package com.TopologyApi;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.*;

/**
 * Topology class represents a topology of devices.
 */
public class Topology {
    /**
     * ID of the topology object
     */
    private String ID;
    /**
     * Hashmap object for mapping between ids of nodes in this topology ond its corresponding node object.
     */
    private HashMap<String,Node>nodes;
    /**
     * Hashmap object for mapping between ids of devices in this topology ond its corresponding node object.
     */
    private  HashMap<String,Device>devices;

    /**
     * Default constructor of topology class.
     */
    public Topology() {
    }

    /**
     * Constructor : intialize data members by jsonObject
     * @param json jsonObject from which Topology object is intialized.
     */
    public Topology(JSONObject json) throws Exception {
        nodes=new HashMap<String,Node>();
        devices= new HashMap<String,Device>();
        ID=(String)json.get("id");
        JSONArray jsonArray=(JSONArray) json.get("components");
        int size=jsonArray.size();
        for (int i=0;i<size;i++){
            JSONObject component=(JSONObject) jsonArray.get(i);
            if(component.get("type").equals("resistor")){
                devices.put((String) component.get("id"),new Resistant(component,nodes));
            }else if(component.get("type").equals("nmos")){
                devices.put((String) component.get("id"),new NMOS(component,nodes));
            }else{
                throw new Exception("Invalid Device type");
            }
        }
    }

    /**
     * getID(): is a method which return the id of an object.
     * @return ID of topology object.
     */
    public String getID(){
        return ID;
    }

    /**
     * getDevicesID(): iterates over the topology object,
     * and returns an  <code>ArrayList</code> containing IDs of devices in object topology.
     * @return list of devices ids.
     */
   ArrayList<String> getDevicesID(){
       ArrayList<String>devicesList=new ArrayList<String>();
       for (Map.Entry<String,Device> entry : devices.entrySet())  {
           devicesList.add(entry.getValue().getID());
       }
       return  devicesList;
   }

    /**
     * Finds which devices in topology object contain a specific node.
     * @param NodeID the node we are looking for.
     * @return <code>ArraList</code> of devices ids which contain the node.
     */
    ArrayList<String> getDevicesWithNode(String NodeID){
        ArrayList<String>devicesList=new ArrayList<String>();
        for (Map.Entry<String,Device> entry : devices.entrySet())  {
            if (entry.getValue().isNodeExist(NodeID)){
            devicesList.add(entry.getValue().getID());
            }
        }
        return  devicesList;
    }

    /**
     * Convert topology object to a jsonObject, which togology data members are stored in.
     * @return returns the jsonObject.
     */
    JSONObject ConvertToJson(){
        JSONObject json=new JSONObject();

        json.put("id",ID);
        JSONArray jsonArray=new JSONArray();
        for (Map.Entry<String,Device> entry : devices.entrySet()){
            jsonArray.add(entry.getValue().ConvertToJson());
        }
        json.put("components",jsonArray);

        return json;
    }
}
