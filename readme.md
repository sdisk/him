### HIM
### 1.[Google ProtoBuf Guide](:https://developers.google.com/protocol-buffers/)

#### 1.1 Protocal Buffer Basic - Java
- 定义一个消息格式化文件（后缀为.proto）
- 使用协议buffer进行编译
- 使用java的API进行读写消息

#### 1.2 定义一个协议规范
```$xslt
syntax = "proto2";
package tutorial;

option java_package = "com.example.tutorial";
option java_outer_classname = "AddressBookProtos";

message Person{
    required string name = 1;
    required int32 id = 2;
    optional string email = 3;
    
    enum PhoneType {
        MOBILE = 0;
        HOME = 1;
        WORK = 2;
    }
    message PhoneNumber {
        required string number =1;
        optional PhoneType type = 2 [default = HOME];   
    }
    
    repeated AddreddBook {
        repeated Person person = 1;  
    }
}
```
通过java_package和java_outer_classname定义包名和类名，而package也是需要的（避免文件冲突）

filed的三种修饰：
- required ： 必须提供（尽量少使用，不利于扩展）
- optional ：可以有也可以没有
- repeated ： 可重复的，类似于一个list，可为空

filed的类型：
- bool
- int32
- float
- double
- string

filed 后面 “=1”， “=2”等就是定义一个唯一的tag（从1到15）

#### 1.3 使用协议buffer进行编译
根据 .proto文件进行编译，首先安装编译器，然后运行命令就可以看到生成的java文件了
```$xslt
protoc -I=$SRC_DIR --java_out=$DST_DIR $SRC_DIR/addressbook.proto
```
生成的文件为com/example/tutorial/AddressBookProtos.java
#### 1.4 使用java的API进行读写消息
builders vs Messages

 Messages文件是不可变的类，不需要修改，需要构建消息使用build()方法来进行构建
 ```$xslt
Person john =
  Person.newBuilder()
    .setId(1234)
    .setName("John Doe")
    .setEmail("jdoe@example.com")
    .addPhones(
      Person.PhoneNumber.newBuilder()
        .setNumber("555-4321")
        .setType(Person.PhoneType.HOME))
    .build();
```
标准的消息方法，方便检查
- isInitialized()
- toString()
- mergeFrom(Message other) : (builder only) 合并消息
- clear(): (builder only) 清空消息
 
 解析和序列化
 - byte[] toByteArray();
 - static Person parseFrom(byte[] data);
 - void writeTo(OutputStream output);
 - static Person parseFrom(InputStream input);
 
 ### 2.Protocol Buffers and O-O Design
 
Protocol buffer classes are basically dumb data holders (like structs in C); 
they don't make good first class citizens in an object model. 
If you want to add richer behaviour to a generated class, 
the best way to do this is to wrap the generated protocol buffer class in an application-specific class.
Wrapping protocol buffers is also a good idea if you don't have control over the design of the .proto file (if, say, you're reusing one from another project).
In that case, you can use the wrapper class to craft an interface better suited to the unique environment of your application: hiding some data and methods, exposing convenience functions, etc.
You should never add behaviour to the generated classes by inheriting from them. This will break internal mechanisms and is not good object-oriented practice anyway.


