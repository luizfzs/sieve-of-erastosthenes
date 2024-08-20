public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            printCommands();
            return;
        }

        Sieve s = new Sieve();

        if ("sieve".equals(args[0])) {
            if (args.length < 2) {
                showInvalidArgumentsAndListCommands();
                return;
            }
            s.sieve(Integer.parseInt(args[1]));
        }

        if ("find-max".equals(args[0])) {
            if (args.length < 3) {
                showInvalidArgumentsAndListCommands();
                return;
            }
            s.foundMaxSieveLimit(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
        }

        if ("benchmark".equals(args[0])) {
            if (args.length < 3) {
                showInvalidArgumentsAndListCommands();
                return;
            }
            long[] result = s.benchmark(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
            for (long l : result) {
                System.out.println(l);
            }
        }

    }

    private static void showInvalidArgumentsAndListCommands() {
        System.out.println("Invalid arguments");
        printCommands();
    }

    private static void printCommands() {
        System.out.println("Valid options are:");
        System.out.println("    sieve <number>");
        System.out.println("    find-max <start> <limit>");
        System.out.println("    benchmark <limit> <iterations>");
    }
}