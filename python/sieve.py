import datetime
import time
import math
import argparse


def sieve(limit):
    sqrt_limit = int(math.sqrt(limit))
    test_numbers = (limit + 1) * [True]

    start = time.time_ns()
    for i in range(2, sqrt_limit + 1):
        if test_numbers[i]:
            for j in range(i * i, limit, i):
                test_numbers[j] = False

    stop = time.time_ns()

    computed_primes = [i for i in range(2, len(test_numbers)) if test_numbers[i]]
    run_time = (stop - start) / 1_000_000
    print(f"{len(computed_primes)} in {run_time}")
    return run_time


def find_max_limit_sieve(start, end):
    while start <= end:
        current = (start + end) // 2
        print("------")
        print(f"Search interval: {start} : {end} -> {end - start}. Attempts remaining: {math.log2(end - start) if end - start > 1 else 0}")
        print(f"Attempting limit {current}")
        print(f"now(): {datetime.datetime.now()}")
        try:
            sieve(current)
            start = current + 1
            print(f"{current} worked. Pushing lower bound to {start}")
        except MemoryError:
            end = current - 1
            print(f"{current} didn't work. Reducing upper bound to {end}")


def benchmark(limit, iterations):
    run_times = []
    for i in range(iterations):
        print(f"Running iteration {i} of {iterations}")
        run_times.append(sieve(limit))

    return run_times


if __name__ == '__main__':
    parser = argparse.ArgumentParser(description="Sieve of Erastosthenes")

    parser.add_argument("--sieve", type=int,
                        help="Sieve of Erastosthenes up to the given integer")

    parser.add_argument("--find-max", nargs=2, type=int, metavar=('start', 'end'),
                        help="Tests the maximum value the machine can handle without a MemoryError")

    parser.add_argument("--benchmark", nargs=2, type=int, metavar=('limit', 'iterations'),
                        help="Run sieve(limit) <iteration> times and prints each run time per line in the end")

    args = parser.parse_args()

    if args.sieve:
        primes = sieve(args.sieve)

    elif args.find_max:
        start, end = args.find_max
        find_max_limit_sieve(start, end)

    elif args.benchmark:
        limit, iterations = args.benchmark
        run_times = benchmark(limit, iterations)
        for i in range(len(run_times)):
            print(run_times[i])

    else:
        parser.print_help()
