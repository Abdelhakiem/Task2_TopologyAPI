package com.TopologyApi;
import org.json.simple.JSONObject;
import java.util.HashMap;

/**
 * NMOS class represent NMOS transistor.
 * Inherits Device abstract class.
 * @see  Device
 */
public class NMOS extends Device {
    private Node drain,gate,source;

    /**
     * Default Constructor of NMOS class.
     */
    public NMOS(){}
    /**
     * Constructor which intializes NMOS data members from a json object.
     * @param jsonObject the json object from which data members will be intialized.
     * @param nodes <code>HashMap</code>  object contains all nodes in the current topology.
     */

    public NMOS(JSONObject jsonObject, HashMap<String,Node> nodes){
        ID= (String) jsonObject.get("id");
        type= (String) jsonObject.get("type");

        JSONObject nmos = (JSONObject)jsonObject.get("m(l)");
        try{
        Default=(double)nmos.get("default");
        }catch(Exception e){
            Default=Double.valueOf((long)nmos.get("default")).longValue();
        }
        try{
            min=(double)nmos.get("min");
        }catch(Exception e){
            min=Double.valueOf((long)nmos.get("min")).longValue();
        }try{
            max=(double)nmos.get("max");
        }catch(Exception e){
            max=Double.valueOf((long)nmos.get("max")).longValue();
        }
        JSONObject json= (JSONObject)jsonObject.get("netlist");
        if(nodes.get("drain")==null){
            nodes.put((String) json.get("drain"),new Node((String) json.get("drain")));
        }if(nodes.get("gate")==null){
            nodes.put((String) json.get("gate"),new Node((String) json.get("gate")));
        }if(nodes.get("source")==null){
            nodes.put((String) json.get("source"),new Node((String) json.get("source")));
        }
        drain=nodes.get((String) json.get("drain")); gate=nodes.get((String) json.get("gate")); source=nodes.get((String) json.get("source"));
    }

    /**
     * overided abstract method in Device class.
     * @return JsonObject stores values of data members of the NMOS object.
     */
    public  JSONObject ConvertToJson(){
        JSONObject json=new JSONObject();
        json.put("id",ID); json.put("type",type);
        JSONObject nmos=new JSONObject();
        nmos.put("default",Default);nmos.put("min",min);nmos.put("max",max);
        json.put("m(l)",nmos);
        JSONObject netlist=new JSONObject();
        netlist.put("drain",drain.getID());netlist.put("gate",gate.getID());netlist.put("source",source.getID());
        json.put("netlist",netlist);
        return  json;
    }

    /**
     * Overides abstract method from Device class.
     * @param NodeID String object represents the node to be search in the Device object.
     * @return returns true of node exists, otherwise returns false.
     */
    public  boolean isNodeExist(String NodeID){
        return (( drain.getID().equals( NodeID) ) || (gate.getID().equals( NodeID) )  ||  (source.getID().equals( NodeID)) );
    }
}
