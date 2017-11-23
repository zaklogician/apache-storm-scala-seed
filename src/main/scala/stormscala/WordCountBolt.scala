package stormscala

import org.apache.storm.topology.BasicOutputCollector
import org.apache.storm.topology.OutputFieldsDeclarer
import org.apache.storm.topology.base.BaseBasicBolt
import org.apache.storm.tuple.Fields
import org.apache.storm.tuple.Tuple
import org.apache.storm.tuple.Values

class WordCountBolt extends BaseBasicBolt {
  val totals = scala.collection.mutable.Map[String,Long]()

  override def execute(input: Tuple, collector: BasicOutputCollector): Unit = {
    val word = input.getStringByField("word")
    val count = totals.getOrElseUpdate(word, 0) + 1
    totals.update(word, count)
    collector.emit( new Values(word, new java.lang.Long(count) ) )
  }

  override def declareOutputFields(declarer: OutputFieldsDeclarer): Unit = {
    declarer.declare( new Fields("word", "count") )
  }

}
