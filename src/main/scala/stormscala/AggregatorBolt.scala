package stormscala

import org.apache.storm.topology.BasicOutputCollector
import org.apache.storm.topology.OutputFieldsDeclarer
import org.apache.storm.topology.base.BaseBasicBolt
import org.apache.storm.tuple.Fields
import org.apache.storm.tuple.Tuple
import org.apache.storm.tuple.Values

class AggregatorBolt extends BaseBasicBolt {
  val totals = scala.collection.mutable.Map[String,Long]()

  override def execute(input: Tuple, collector: BasicOutputCollector): Unit = {
    val word = input.getStringByField("word")
    val count = input.getLongByField("count")
    totals.update(word, count)
  }

  override def declareOutputFields(declarer: OutputFieldsDeclarer): Unit = {
    // emits nothing
  }

  /** Prints results once topology is shut down */
  override def cleanup: Unit = {
    println("Word Counts: ")
    totals.foreach( x => println(x) )
  }
}
