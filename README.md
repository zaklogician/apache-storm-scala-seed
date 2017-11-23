# Apache Storm seed project for Scala

This is an SBT seed project to get you started with Apache Storm on Scala.
It contains a standard Word Count example.

Use `sbt run` to execute.

## Word Count Topology

The `RandomWordSpout`s emit random words from a given word list. The words are
forwarded to `WordCountBolt` nodes grouped by values (i.e. the same word always
goes to the same cluster). The `WordCountBolt`s keep track of the counts, and
forward them to the (unique) `AggregatorBolt`, which is responsible for
printing the final word count once the cluster is terminated.

## Logging

To enable ZooKeeper logging for the local cluster, just remove `log4j2.xml`.
