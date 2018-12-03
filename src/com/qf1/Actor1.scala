package com.qf1

package com.qf1

import scala.actors.{Actor, Future}

class Actor1 extends Actor{
  //接受消息
  override def act(): Unit = {
    loop{
      //    print("->ok") //接受到打印ok
      //匹配成功，执行代码
      receive{
        case "start" =>{println("收到start消息")}
          sender !"receive"
        case "stop"=>{println("收到stop消息")}
        case _=>{print("挂了")}
      }
    }
  }

}

object Test1{
  def main(args: Array[String]): Unit = {
    val demo = new ActorDemo();
    //启动
    demo.start()
    for (i <- 0 to 10){
      demo !"stop"     //使用异步发送，同步阻塞不能收到10条
    }
    //    val future: Future[Any] = demo !! "start"
    //    val st: Any = future.apply() //从future中取出消息
    //    println("--->"+st)
  }
}

