package actor

import akka.actor.{Actor, ActorRef}
import com.google.inject.Inject
import com.google.inject.name.Named
import model.message.testClass
import org.slf4j.LoggerFactory

/**
  * Created by Administrator on 2016/7/17.
  */
class testActor1 @Inject()(
                            @Named("test3") testActor3:ActorRef
                          )extends Actor{
  val log = LoggerFactory.getLogger(this.getClass)

  @throws[Exception](classOf[Exception])
  override def preStart(): Unit = {
    log.info(s"${self.path.name}  actor starting .........")

  }

//  self ! Some("lijiabo")

  @throws[Exception](classOf[Exception])
  override def postStop(): Unit = {
    log.info(s"${self.path.name}  actor stopping .........")
  }
  override def receive:Receive = {
    case testClass(id,message) =>
      if(id==1){
        val child = if(context.child("child").nonEmpty) context.child("child").get
        else context.actorOf(testActor.props(testActor3),"child")
        child ! testClass(id,message)
        println("Actor is starting...........")
      }
      else if(id==2){
        val child = if(context.child("child2").nonEmpty) context.child("child2").get
        else context.actorOf(testActor2.props(testActor3),"child2")
        child ! testClass(id,message)
        println("Actor2 is starting...........")
      }
    case Some(message)=>
      val child = if(context.child("child2").nonEmpty) {
        context.child("child2").get
      }
      else {
        println("empty")
        context.actorOf(testActor2.props(testActor3),"child2")
      }
      child ! Some(message)
      println("actor2 other message:"+message)
  }
}
