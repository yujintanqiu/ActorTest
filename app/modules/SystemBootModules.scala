package modules

import actor.{testActor1, testActor2, testActor3}
import com.google.inject.AbstractModule
import play.api.libs.concurrent.AkkaGuiceSupport

/**
  * Created by Administrator on 2016/7/17.
  */
class SystemBootModules extends AbstractModule with AkkaGuiceSupport{
  override def configure():Unit={
    bindActor[testActor1]("test1")
    bindActor[testActor2]("test2")
    bindActor[testActor3]("test3")
  }
}
