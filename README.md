# Simple AkkaCluster Demo

This is a very simple demon of AkkaCluster. It contains an application that starts an actor 
system in cluster mode with a single actor.  The actor's only function is to log the various
cluster events it receives.

# Running the Demo
 
To see demo in action open 2 consoles.  In one console type:
```sbt "run 2551"```

In the other console type:
```sbt "run 2552"```

