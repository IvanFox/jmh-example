package samples;

import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;

public class BenchmarkModes {

    /*
     * JMH generates lots of synthetic code for the benchmarks for
     * you during the compilation. JMH can measure the methods in lots
     * of modes, and it will generate all the needed code at once.
     * Users may select the default benchmark mode with the special
     * annotation, or select/override the mode via the command line.
     *
     * With this scenario, we start to measure something useful. Note
     * that we can conveniently have the exception at the benchmark method,
     * in order to reduce some of the clutter.
     *
     * P.S. It is helping at times to look into the generated code trying
     * to diagnose  the performance issue. You might see you don't measuring
     * it right! The generated code for this particular sample is somewhere at
     *  target/generated-sources/annotations/.../JMHSample_02_BenchmarkModes.java
     */

    /*
     * This benchmark type measures the raw throughput.
     * We are using the special annotation to select the units to measure in,
     * although you can use the default.
     */

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void measureThroughput() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
    }

    /*
     * This benchmark type measures the average execution time.
     * Some might say it is the reciprocal throughput, and it really is.
     * There are workloads where measuring times is more convenient though.
     */

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void measureAvgTime() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
    }

    /*
     * This benchmark type samples the execution time.
     * With this benchmark, we are gathering the execution timings on their own,
     * which allows us to infer the distributions, percentiles, etc.
     *
     * At this point, JMH only calculates percentile estimates.
     *
     * JMH also tries to auto-adjust sampling frequency: if the method
     * is long enough, you will end up capturing all the samples.
     */
    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void measureSamples() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
    }

    /*
     * This benchmark type measures the single method invocation time.
     * This mode is useful to do cold startup tests, when you specifically
     * do not want to call the benchmark method continuously.
     */

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void measureSingleShot() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
    }

    /*
     * We can also ask for multiple benchmark modes at once. All the tests
     * above can be replaced with just a single test like this:
     */

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime, Mode.SampleTime, Mode.SingleShotTime})
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void measureMultiple() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
    }


    @Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void measureAll() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
    }

//    public static void main(String[] args) throws RunnerException {
//        Options opt = new OptionsBuilder()
//                .include(".*" + samples.BenchmarkModes.class.getSimpleName() + ".*")
//                .warmupIterations(5)
//                .measurementIterations(5)
//                .forks(1)
//                .build();
//
//        new Runner(opt).run();
//    }

}