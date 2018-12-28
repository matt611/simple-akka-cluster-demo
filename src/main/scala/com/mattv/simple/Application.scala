package com.mattv.simple

import akka.actor.ActorSystem
import akka.actor.Props
import com.typesafe.config.ConfigFactory

/**
  *  Very simple application to demonstrate spinning up an akka cluster
  *  and the events the cluster emits.
  */

object Application extends App {
  val port:String = args(0)
  println(s"Starting application listening on port $port")

  // Set the ports in the config.
  val config = ConfigFactory.parseString(s"akka.remote.artery.canonical.port=$port")
    .withFallback(ConfigFactory.load())

  // All actor systems who wish to join this cluster must have the same name.
  val system = ActorSystem("SimpleClusterDemo", config)

  // The application has a single actor whose sole job is to print cluster events
  // as it receives them.
  system.actorOf(Props[SimpleClusterActor], name = "clusterListener")
}
