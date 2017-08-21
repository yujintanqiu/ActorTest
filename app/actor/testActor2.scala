package actor

import java.util.concurrent.TimeUnit

import akka.actor.{Actor, ActorRef, Props}
import com.google.inject.Inject
import com.google.inject.name.Named
import model.message.testClass
import org.slf4j.LoggerFactory

import scala.concurrent.duration.Duration

/**
  * Created by Administrator on 2016/7/28.
  */
object testActor2{
  def props(testActor3:ActorRef) = Props(classOf[testActor2],testActor3)
}
class testActor2 @Inject()(
                            @Named("test3") testActor3:ActorRef
                          )extends Actor{
  val log = LoggerFactory.getLogger(this.getClass)

//  var test = context.system.scheduler.schedule(Duration(500, TimeUnit.MILLISECONDS),Duration(15000, TimeUnit.MILLISECONDS),self,Some("hello"))

  @throws[Exception](classOf[Exception])
  override def preStart(): Unit = {
    log.debug(s"${self.path.name}  actor starting .........")
    println(s"...........${self.path.name}")

  }

  @throws[Exception](classOf[Exception])
  override def postStop(): Unit = {
//    test.cancel()
    log.info(s"${self.path.name}  actor stopping .........")
  }
  override def receive:Receive = {
    case testClass(id,message)=>
//      test = context.system.scheduler.schedule(Duration(500, TimeUnit.MILLISECONDS),Duration(5000, TimeUnit.MILLISECONDS),self,message)
      testActor3 ! testClass(id,message)
      println("message is: "+message)
    case Some(message)=>
      testActor3 ! Some(message)
      println(message)
  }

}
