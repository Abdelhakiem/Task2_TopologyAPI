package com.TopologyApi;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class TopologyAPITest {
     public  TopologyAPI api=new TopologyAPI();

    /**
     * Testing readJson method
     */
    @Test
  public void readJsonTest(){
       ArrayList<String>fileList=new ArrayList<String>();
       fileList.add("Test1.json");fileList.add("Test2.json");
       fileList.add("Test3.json");fileList.add("Test4.json");
       for (String fileName:fileList ) {
         try{

           api.readJSON(fileName);
         }catch (Exception e){
             fail("Should have not thrown any exception");
         }
       }
   }
    /**
     * Testing write method
     */
   @Test
    public void writeJsonTest(){

       readJsonTest();
       ArrayList<String>topologiesList=new ArrayList<String>();
       topologiesList.add("top1");topologiesList.add("top2");
       topologiesList.add("top3");topologiesList.add("top4");
       for (String topologyID:topologiesList ) {
           try{
               api.writeJSON(topologyID);
           }catch (Exception e){
               fail("Should have not thrown any exception");
           }
       }

   }

    /**
     * Testing queryTopologies method
     */
   @Test
   public void queryTopologiesTest(){
       readJsonTest();
       ArrayList<String>ExpectedOuput=new ArrayList<>();
       ExpectedOuput.add("top1");ExpectedOuput.add("top2");
       ExpectedOuput.add("top3");ExpectedOuput.add("top4");
       ArrayList<String>Output=api.queryTopologies();
       assertTrue(Output.size()==ExpectedOuput.size());
       for (String topology:ExpectedOuput ) {
           assertTrue(Output.contains(topology));
       }
   }

    /**
     * Testing queryDevicesWithNetlistNode method .
     */
    @Test
   public void queryDevicesWithNetlistNodeTest_FirstTopology(){
        readJsonTest();
        ArrayList<String>ExpectedOuput=new ArrayList<>();
        ExpectedOuput.add("res1");ExpectedOuput.add("m1");
        ArrayList<String>Output=api.queryDevicesWithNetlistNode("top1","n1");
        assertTrue(Output.size()==ExpectedOuput.size());
        for (String topology:ExpectedOuput ) {
            assertTrue(Output.contains(topology));
        }
   }
    @Test
    public void queryDevicesWithNetlistNodeTest_InvalidNodeID(){
        readJsonTest();
        assertTrue(api.queryDevicesWithNetlistNode("top1","n4").size()==0);
    }
    @Test
    public void queryDevicesWithNetlistNodeTest_SecondTopology(){
        readJsonTest();
        ArrayList<String>ExpectedOuput=new ArrayList<>();
          ExpectedOuput.add("res1");ExpectedOuput.add("m1");ExpectedOuput.add("m2");
        ArrayList<String>Output=api.queryDevicesWithNetlistNode("top2","n1");
        assertTrue(Output.size()==ExpectedOuput.size());
        for (String topology:ExpectedOuput ) {
            assertTrue(Output.contains(topology));
        }

    }

    /**
     * Testing queryDevices method by four different topologies.
     */
    @Test
   public void queryDevicesTest_FirstTopology(){
       readJsonTest();
       ArrayList<String>ExpectedOuput=new ArrayList<>();
       ExpectedOuput.add("res1");ExpectedOuput.add("m1");
       ArrayList<String>Output=api.queryDevices("top1");
       assertTrue(Output.size()==ExpectedOuput.size());
       for (String topology:ExpectedOuput ) {
           assertTrue(Output.contains(topology));
       }
   }
    @Test
    public void queryDevicesTest_SecondTopology(){
        readJsonTest();
        ArrayList<String>ExpectedOuput=new ArrayList<>();
        ExpectedOuput.add("res1");ExpectedOuput.add("m1");
        ExpectedOuput.add("res2");ExpectedOuput.add("m2");
        ExpectedOuput.add("res3");ExpectedOuput.add("m3");
        ArrayList<String>Output=api.queryDevices("top2");
        assertTrue(Output.size()==ExpectedOuput.size());
        for (String topology:ExpectedOuput ) {
            assertTrue(Output.contains(topology));
        }
    }
    @Test
    public void queryDevicesTest_ThirdTopology(){
        readJsonTest();
        ArrayList<String>ExpectedOuput=new ArrayList<>();
        ExpectedOuput.add("m2"); ExpectedOuput.add("m9"); ExpectedOuput.add("res3");
        ArrayList<String>Output=api.queryDevices("top3");
        assertTrue(Output.size()==ExpectedOuput.size());
        for (String topology:ExpectedOuput ) {
            assertTrue(Output.contains(topology));
        }
    }
    @Test
    public void queryDevicesTest_FourthTopology(){
        readJsonTest();
        ArrayList<String>ExpectedOuput=new ArrayList<>();
        //res1,res8,m1,m2,m4
        ExpectedOuput.add("res1"); ExpectedOuput.add("res8"); ExpectedOuput.add("m1");
        ExpectedOuput.add("m2"); ExpectedOuput.add("m4");
        ArrayList<String>Output=api.queryDevices("top4");
        assertTrue(Output.size()==ExpectedOuput.size());
        for (String topology:ExpectedOuput ) {
            assertTrue(Output.contains(topology));
        }
    }


    /**
     * Testing deleteTopology method by two topolgy. One is a valid, and the other is invalid.
     */
    @Test
   public void deleteTopologyTest_InvalidTopology(){

       // NullPointerException is found, should be displayed in terminal.
       api.queryDevices("InvalidTopologyId");
   }
   @Test
    public void deleteTopologyTest_deletingFirstTopology(){
       readJsonTest();
       try {
       api.deleteTopology("top1");
       }catch (Exception e){
           fail("No Error Should have returned");
       }
   }



}