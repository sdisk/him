package com.hq.protobuf.test;

/**
 * RMI: remote method invocation(远程方法调用)，（只支持java）
 * client
 * server
 * RPC: remote producer call(远程过程调用)，跨语言的
 * 1.定义个接口说明文件：描述了对象（结构体），对象成员，接口方法等信息
 * 2.通过rpc框架提供的编译器，将接口说明文件翻译成具体的语言文件
 * 3.在客户端和服务端分别列入RPC编译器所生产的文件，即可像调用本地方法一样调用远程方法
 *
 *   Java NIO 提供了 ByteBuffer 作为它 的字节容器
 *   在netty中使用ByteBuf来替换
 * @program: him
 * @description:
 * @create: 2019-08-15 14:17
 **/
public class ProtoBufTest {
}
