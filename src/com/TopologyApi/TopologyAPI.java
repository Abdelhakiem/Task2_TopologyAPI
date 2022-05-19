package com.TopologyApi;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * TopologyAPI is a class supports functionality needed for accessing, managing and storing device topologies objects.
 * @see Topology
 */
public class TopologyAPI {
    /**
     * <code>HashMap</code> object for mapping between ids of nodes in this topology ond its corresponding node object.
     */
    private HashMap<String,Topology>topologies=new HashMap<String,Topology>();;



    /**
     * Takes a string as parameter which contains the name of json file to be read.
     * Tries to read that file and converting it into a jsonObject, then tries to initialize a topology object by passing
     * that jsonObject to contrusctor of Topology class which takes JsonObject as a parameter, adding that
     * topology object to the <code>HashMap</code> object which called (topologies).
     * Errors in Catch:
     * If readJson method failed to the read that file, an Input Output Exception shows.
     * If readJson method failed to parse that file into a jsonObject, Parse Exception shows.
     * OtherWise the default exceptoin shows up.
     * @param FileName name of JsonFile which to be converted into a topology object.
     */
    public void readJSON(String FileName){
        JSONParser parser= new JSONParser();
        try {
            Object obj=parser.parse(new FileReader(FileName));
            JSONObject jsonObject=(JSONObject)obj;
            topologies.put((String) jsonObject.get("id"),new Topology(jsonObject));
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (ParseException e){
            e.printStackTrace();
        }
        catch (Exception  e){
            e.printStackTrace();
        }
    }

    /**
     * Writes a json file from a Topology object. It takes id of the topology as a string parameter.
     * The jsonFile's name to be writing has the same name of topology Id and ".josn" extension.
     * First writeJSON creates FileWriter object. by using topologies HashMap tries to map between topology id which
     * we are looking for and its Topology object. Then convert topology object of that id into a JsonObject
     * and writes it into that file.
     * If topologies hashMap doesn't contain a topology with this id an NullPointerException will show up.
     * If writeJson method failed to write that Json File for any other reason an Input Out Exception will appear.
     * @param TopologyID String parameter of the topology id , which is needed to be writen in a json file.
     */
    public void writeJSON(String TopologyID){
        try(FileWriter file=new FileWriter(TopologyID+".json")){
            JSONObject obj=topologies.get(TopologyID).ConvertToJson();
           file.write(obj.toJSONString());
           file.flush();
        }catch (IOException e){
            e.printStackTrace();
        }catch(NullPointerException e) {
            System.out.println("NullPointerException is found.");
        }
    }

    /**
     * Iterates over topologies in the TopologyApi object and returns their ids in an arrayList called topologyList.
     * In each Iteration we call the getID() method in Topology object, which returns the id of topology as a string.
     * @see Topology
     * @return returns an <code>ArrayList</code>  of topologies ids.
     */
   public ArrayList<String> queryTopologies(){
       ArrayList<String> topologyList=new ArrayList<String>();
       for (Map.Entry<String,Topology> entry : topologies.entrySet()){
           topologyList.add(entry.getValue().getID());
       }
       return topologyList;
   }

    /**
     * Returns an ArrayLIst of strings which represent the IDs of Devices in a specific Topology.
     * Toplogies HashMap object gets the object of the Topology.
     * Then We call getDevicesID() method which returns a list of IDs of Devices in that topology.
     * If there is no Topology with that ID a NullPointerException will be displayed.
     * @param TopologyID ID of Topology we query about.
     * @return ArrayList of IDs of Devices in this Topology.
     */
    public ArrayList<String> queryDevices(String TopologyID) {
         ArrayList<String>topologyList=new ArrayList<String>();
        try{
            topologyList  = topologies.get(TopologyID).getDevicesID();
        }catch(NullPointerException e) {
            System.out.println("NullPointerException is found.");
        }
        return topologyList;

    }
    /**
     * Searching for a node by its id in Devices in a given topology, and returns
     * an ArrayList of ids of the devices which contains this node.
     * By mapping between topology ID and its objects using HashMap called topologies,Then we call getDevicesWithNode()
     * which return the list of devices ids.
     * If there is no Topology with that ID a NullPointerException will be displayed.
     * @param TopologyID id of Topology to be searched in.
     * @param NetlistNodeID id of the node which we're looking for.
     * @return ArrayList of ids of the devices which contains this node in this specific topology.
     */
    public ArrayList<String> queryDevicesWithNetlistNode(String TopologyID,String NetlistNodeID){
        ArrayList<String>topologyList=new ArrayList<String>();
        try{
            topologyList=topologies.get(TopologyID).getDevicesWithNode(NetlistNodeID);
        }catch(NullPointerException e) {
            System.out.println("NullPointerException is found.");
        }
        return topologyList;
    }

    /**
     * Deletes Topology by its ID.
     * topologies HashMap objects removes the object of topology corresponding to its ID.
     * If there is no Topology with that ID a NullPointerException will be displayed.
     * @param TopologyID id of topology to be deleted.
     */
    void deleteTopology(String TopologyID){
        try{
            topologies.remove(TopologyID);
        }catch(NullPointerException e) {
            System.out.println("NullPointerException is found.");
        }
    }


}
