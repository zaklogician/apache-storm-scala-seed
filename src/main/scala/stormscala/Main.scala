package stormscala 

import org.apache.storm.Config
import org.apache.storm.LocalCluster
import org.apache.storm.topology.TopologyBuilder
import org.apache.storm.tuple.Fields

object Main {

  def main(args: Array[String]): Unit = {
    println()
    
    print("Building topology...\t")
    val builder: TopologyBuilder = new TopologyBuilder
    builder.setSpout("spout", new RandomWordSpout, 5)
    builder.setBolt("count", new WordCountBolt, 12).fieldsGrouping("spout", new Fields("word"))
    builder.setBolt("total", new AggregatorBolt, 1).globalGrouping("count")
    println("OK")

    print("Creating config...\t")
    val config = new Config
    config.setMaxTaskParallelism(3)
    config.setDebug(false)
    println("OK")

    print("Creating cluster...\t")
    val cluster: LocalCluster = new LocalCluster
    println("OK")

    print("Executing topology...\t")
    cluster.submitTopology("wc", config, builder.createTopology)
    Thread.sleep(5000)
    println("OK")

    cluster.killTopology("wc")
    cluster.shutdown

    println("\nALL OK")
  }

}
