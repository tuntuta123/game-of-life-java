package conway.tests;

public class RunTests{
    public static void main(String[] args){
        //GridTest.runTests();
        ConwayTest.runTests();
		NodeTest node = new NodeTest();
		node.runTests();
		ShapeTest.runTests();
		DemoTest.runTests();
        System.out.println("ALl tests passed");
    }
}
