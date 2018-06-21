package com.mattv.simple

import akka.actor.ActorSystem
import akka.actor.Props
import com.typesafe.config.ConfigFactory


object Application {
  def main(args: Array[String]): Unit = {
    val port:String = args(0)
    println(s"Starting clustered listening on port $port")

    // set the ports in the config
    val config = ConfigFactory.parseString(s"""
        akka.remote.netty.tcp.port=$port
        akka.remote.artery.canonical.port=$port
        """).withFallback(ConfigFactory.load())

    // all actor systems who wish to join this cluster must have the same name
    val system = ActorSystem("SimpleClusterDemo", config)
    system.actorOf(Props[SimpleClusterActor], name = "clusterListener")
  }
}
