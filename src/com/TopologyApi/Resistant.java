package com.TopologyApi;
import org.json.simple.JSONObject;
import java.util.HashMap;

/**
 * Resistant class represent resistants in the topology.
 * Inherits Device abstract class.
 * @see  Device
 */
public class Resistant extends Device{
private Node t1,t2;

    /**
     * Default constructor of Resistant class.
     */
  public Resistant(){

  }
    /**
     * Constructor which intializes Resistant data members from a json object.
     * @param jsonObject the json object from which data members will be intialized.
     * @param nodes <code>HashMap</code>  object contains all nodes in the current topology.
     */
    public Resistant(JSONObject jsonObject,HashMap<String,Node> nodes){
        ID= (String) jsonObject.get("id");
        type= (String) jsonObject.get("type");
        JSONObject res = (JSONObject)jsonObject.get("resistance");

        try{
            Default=(double)res.get("default");
        }catch(Exception e){
            Default=Double.valueOf((long)res.get("default")).longValue();
        }
        try{
            min=(double)res.get("min");
        }catch(Exception e){
            min=Double.valueOf((long)res.get("min")).longValue();
        }try{
            max=(double)res.get("max");
        }catch(Exception e){
            max=Double.valueOf((long)res.get("max")).longValue();
        }
        JSONObject json= (JSONObject)jsonObject.get("netlist");
        if(nodes.get("t1")==null){

            nodes.put((String) json.get("t1"),new Node((String) json.get("t1")));
        }
        if(nodes.get("t2")==null){

            nodes.put((String) json.get("t2"),new Node((String) json.get("t2")));
        }
        t1=nodes.get((String) json.get("t1")); t2=nodes.get((String) json.get("t2"));
    }
    /**
     * overides abstract method in Device class.
     * @return JsonObject stores values of data members of the NMOS object.
     */
    public  JSONObject ConvertToJson(){
        JSONObject json=new JSONObject();
        json.put("id",ID); json.put("type",type);
        JSONObject res=new JSONObject();
        res.put("default",Default);res.put("min",min);res.put("max",max);
        json.put("resistance",res);
        JSONObject netlist=new JSONObject();
       netlist.put("t1",t1.getID());netlist.put("t2",t2.getID());
       json.put("netlist",netlist);
        return  json;
    }
    /**
     * Overides abstract method from Device class.
     * @param NodeID String object represents the node to be search in the Device object.
     * @return returns true of node exists, otherwise returns false.
     */
    public  boolean isNodeExist(String NodeID){
        return ((t1.getID().equals( NodeID)) || (t2.getID().equals( NodeID) ));
    }
}
