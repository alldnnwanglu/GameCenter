public class Sample
{
    public static void main(String[] args)
    {
        System.loadLibrary("SampleC");
        NativeMethodTest nmt = new NativeMethodTest();

        int square = nmt.intMethod(5);
        boolean bool = nmt.booleanMethod(true);
        String text = nmt.stringMethod("java");
        int sum = nmt.intArrayMethod(new int[]{1,2,3,4,5,6,7,8,13});

        System.out.println("intMethod: " + square);
        System.out.println("booleanMethod:" + bool);
        System.out.println("stringMethod:" +text);
        System.out.println("intArrayMethod:" + sum);
    }
}

class NativeMethodTest
{
    public native int intMethod(int n);
    public native boolean booleanMethod(boolean bool);
    public native String stringMethod(String text);
    public native int intArrayMethod(int[] intArray);
}