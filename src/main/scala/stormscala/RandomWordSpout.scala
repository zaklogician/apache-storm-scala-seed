package stormscala

import org.apache.storm.spout.SpoutOutputCollector
import org.apache.storm.task.TopologyContext
import org.apache.storm.topology.OutputFieldsDeclarer
import org.apache.storm.topology.base.BaseRichSpout
import org.apache.storm.tuple.Fields
import org.apache.storm.tuple.Values

import scala.util.Random

class RandomWordSpout extends BaseRichSpout {

  var _collector: SpoutOutputCollector = _

  var _rand: Random = _ // new Random(0xDEADBEEF)
  val words: List[String] = List(
    "character", "characteristic", "characterize", "charge", "charity",
    "chart", "chase", "cheap", "check", "cheek", "cheese", "chef", "chemical",
    "chest", "chicken", "chief", "child", "childhood", "Chinese", "chip",
    "chocolate", "choice"
  )
  
  override def nextTuple: Unit = {
    Thread.sleep(100)
    val word = words( _rand.nextInt(words.length) )
    _collector.emit( new Values(word) )
  }

  override def open(conf: java.util.Map[_, _], context: TopologyContext, collector: SpoutOutputCollector): Unit = {
    _collector = collector
    _rand = Random
  }

  override def declareOutputFields(declarer: OutputFieldsDeclarer): Unit = {
    declarer.declare(new Fields("word"))
  }

}
