package conway.tests;

public class RunTests{
    public static void main(String[] args){
        GridTest.runTests();
        ConwayTest.runTests();
		NodeTest.runTests();
		ShapeTest.runTests();
        System.out.println("ALl tests passed");
    }
}
