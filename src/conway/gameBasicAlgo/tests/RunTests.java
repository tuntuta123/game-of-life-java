package conway.gameBasicAlgo.tests;

public class RunTests{
    public static void main(String[] args){
		//GridTest.runTests();
		ConwayTest.runTests();
		NodeTest node = new NodeTest();
		node.runTests();
		ShapeTest.runTests();
		DemoTest.runTests();
		MenuTest.runTests();
		SubMenuTest.runTests();
		RulesMenuTest.runTests();
		System.out.println("All tests passed");
	}
}
