package ActorWordCount

import java.io.File

import scala.actors.{Actor, Future}
import scala.collection.mutable
import scala.io.{BufferedSource, Source}

class ActorDemo3 extends Actor{
  override def act(): Unit = {
    receive{
      case x:File=>{
        val source: BufferedSource = Source.fromFile(x)
        val iterator = source.getLines()
        val m1: Map[String, Int] = iterator.toList.flatMap((x) => {
          x.split("\\s+")
        })
          .map((x) => {
            (x, 1)
          }).groupBy((x) => {
          x._1
        })
          .map((x) => {
            (x._1, x._2.size)
          })
        sender ! m1
      }
    }
  }
}

object Test12{
  def main(args: Array[String]): Unit = {
    val list = List("D://a.txt", "D://b.txt", "D://c.txt")
    val map = new mutable.HashMap[String,Map[String,Int]]()

    //循环读路径
    for (path <- list) {
      //获取文件
      val file = new File(path.toString)
      val actor = new ActorDemo3
      actor.start()
      val value: Future[Any] = actor !! file
      map.put(path, value.apply().asInstanceOf[Map[String, Int]])
    }

    for((k,v) <- map){
      for((k1,v1) <- v){
        println("路径："+k+"---内容"+"map:("+k1+","+v1+")")
      }
    }
  }
}