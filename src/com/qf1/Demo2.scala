package com.qf1

import scala.actors.Actor


class Msg
case class ADMsg(msgType:String,msgContent:String) extends Msg
case class MyMsg(msgType:String,msgContent:String) extends Msg
class Demo2 extends Actor{
  override def act(): Unit = {
    receive{
      case ADMsg(msgType,msgContent) =>{
        println(s"消息类型:${msgType},内容：${msgContent}")
      }
      case MyMsg(msgType,msgContent) =>{
        println(s"消息类型:${msgType},内容：${msgContent}")
      }
    }
  }
}

object Test11{
  def main(args: Array[String]): Unit = {
    //
    val demo2: Demo2 = new Demo2
    demo2.start()
    val aDMsg = new ADMsg("广告","你中了500万，请拨打12345咨询")
    new MyMsg("正常","有钱吗")
    demo2! aDMsg
  }
}