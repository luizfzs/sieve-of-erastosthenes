public class Sieve {
    public long sieve(final int limit) {
        final int SQRT_MAX = (int) Math.sqrt(limit);
        boolean[] testNumbers = new boolean[limit];

        long start = System.currentTimeMillis();
        for (int i = 2; i <= SQRT_MAX ; i++) {
            if (!testNumbers[i]) {
                int iSquared = i*i;
                int j = iSquared;
                for (int k = 0; j < limit && j > 0; j = iSquared + (i*k)) {
                    testNumbers[j] = true;
                    k++;
                }
            }
        }
        long stop = System.currentTimeMillis();

        int count = 0;
        for (int i = 2; i < limit; i++) {
            if (!testNumbers[i]) {
                count++;
            }
        }

        System.out.println("Found " + count + " in " + (stop - start) + " ms");
        return (stop - start);
    }

    public void foundMaxSieveLimit(final int start, final int max) {
        int left = start;
        int right = max;

        int current;
        do {
            // we do this to avoid integer overflow. we're trying to get to the upper limit of the integer range.
            current = (right / 2) + (left / 2);
            System.out.println("New current is " + current);

            try {
                sieve(current);
                left = current + 1;
            } catch (OutOfMemoryError e) {
                System.out.println("OOM error");
                right = current;
            }
        } while (left < right);

        System.out.println("Largest that worked was " + current);
    }

    public long[] benchmark(final int limit, final int testIterations) {
        final int ITERATIONS = 20;
        long[] runtime = new long[ITERATIONS];
        for (int i = 0; i < ITERATIONS; i++){
            runtime[i] = sieve(limit);
            System.gc();
        }

        return runtime;
    }


}
