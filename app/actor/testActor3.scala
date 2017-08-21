package actor

import java.util.concurrent.TimeUnit

import akka.actor.{Actor, ActorRef}
import model.message.testClass
import org.slf4j.LoggerFactory

import scala.collection.mutable
import scala.concurrent.duration.Duration

/**
  * Created by Administrator on 2016/7/28.
  */
class testActor3 extends Actor{
  val log = LoggerFactory.getLogger(this.getClass)
  private val testMap = mutable.HashMap[Long,String]()

//  var test = context.system.scheduler.schedule(Duration(500, TimeUnit.MILLISECONDS),Duration(15000, TimeUnit.MILLISECONDS),self,Some("hello"))

  @throws[Exception](classOf[Exception])
  override def preStart(): Unit = {
    log.info(s"${self.path.name}  actor starting .........")

  }

  @throws[Exception](classOf[Exception])
  override def postStop(): Unit = {
    log.info(s"${self.path.name}  actor stopping .........")
  }
  override def receive:Receive = {
    case testClass(id,message)=>
      testMap += (id->message)
//      test = context.system.scheduler.schedule(Duration(500, TimeUnit.MILLISECONDS),Duration(5000, TimeUnit.MILLISECONDS),self,message)
//      println("message is: "+message)
//      testMap.map{
//        each=>
//          println("fffffff  "+each._1+"    "+each._2)
//      }
    case Some(message)=>
      testMap.map{
        each=>
          println("fffffff   "+each._1+"    "+each._2)
      }
//      println("other massage")
  }

}
