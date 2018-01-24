package benchmark

import java.io.InputStream
import java.util.concurrent.TimeUnit

import example.Utility
import org.openjdk.jmh.annotations._

@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Array(Mode.Throughput))
class TestCompression {

  //@State(Scope.Benchmark)
  //val stream: InputStream = getClass.getResourceAsStream("/data")
  //@State(Scope.Benchmark)
  //val data: String = scala.io.Source.fromInputStream(stream).getLines().mkString
  //val data = "khkbfjvbfkjnbvjbfvj"

  @Benchmark
  def gzipCompress = Utility.gzipCompress(MyState.data)

  @Benchmark
  def gzipDecomress = Utility.gzipDecompress(gzipCompress)


  @Benchmark
  def snappyCompress = Utility.snappyCompress(MyState.data)

  @Benchmark
  def snappyDecomress = Utility.snappyDecompress(snappyCompress)


}

@State(Scope.Benchmark)
object MyState {
  @State(Scope.Benchmark)
  val stream: InputStream = getClass.getResourceAsStream("/data")
  @State(Scope.Benchmark)
  val data: String = scala.io.Source.fromInputStream(stream).getLines().mkString
}
