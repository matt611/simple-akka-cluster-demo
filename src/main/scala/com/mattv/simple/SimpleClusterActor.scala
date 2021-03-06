package com.mattv.simple

import akka.actor.Actor
import akka.cluster.Cluster
import akka.cluster.ClusterEvent._

/**
  * A simple actor that subscribes to the cluster and prints cluster events
  * as they are received.
  */
class SimpleClusterActor extends Actor {
  val cluster = Cluster(context.system)

  override def preStart(): Unit = {
    println("ACTOR STARTING")
    cluster.subscribe(self, initialStateMode = initialStateAsEvents, classOf[MemberEvent], classOf[UnreachableMember])
  }

  override def postStop(): Unit = {
    println("ACTOR STOPPING")
    cluster.unsubscribe(self)
  }

  def receive = {
    case evt: MemberEvent => {
      println("CLUSTER EVENT: " + evt)
    }
  }
}
