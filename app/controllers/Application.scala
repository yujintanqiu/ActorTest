package controllers

import akka.actor.{ActorRef, ActorSystem, Props}
import com.google.inject.name.Named
import com.google.inject.Inject
import model.message.testClass
import play.api.mvc._

class Application  @Inject()(
                            @Named("test1") testActor1:ActorRef
                            )extends Controller {

  def index = Action {implicit request=>
    //val system = ActorSystem("mySystem")
//    val myActor = system.actorOf(Props(testActor))
    testActor1 ! testClass(1,"hello World")
    testActor1 ! testClass(2,"Hello Li jiabo")
    testActor1 ! Some("你好！")
    Ok(views.html.index("Your new application is ready."))
  }

}