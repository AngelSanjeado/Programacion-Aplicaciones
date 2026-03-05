public class Test
{
    /**
     * Precondition:  N > 0
     * Postcondition: avgFirstN() = (1+2+...+N)/N
     */
    public double avgFirstN(int N) {
        int sum = 0;
        for (int k = 1; k <= N; k++)
            sum += k;
        return sum/N;         // What if N is 0?
    } // avgFirstN()

    public static void main (String argv[])
    {
        Test t = new Test();
        System.out.println("Average first 10: " + t.avgFirstN(10));
        System.out.println("What if the argument is 0? " + t.avgFirstN(0));
    }
} // Test
