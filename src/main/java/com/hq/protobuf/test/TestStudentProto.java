package com.hq.protobuf.test;

import com.google.protobuf.InvalidProtocolBufferException;
import com.hq.protobuf.StudentInfo;

/**
 * @program: him
 * @description:
 * @create: 2019-08-16 17:35
 **/
public class TestStudentProto {
    /**
     * 使用build进行构建对象
     */
    public static void main(String[] args) throws InvalidProtocolBufferException {
        StudentInfo.Student student = StudentInfo.Student.newBuilder()
                .setName("张三").setAge(28).setAddress("巴中").build();
        byte [] sb = student.toByteArray();
        StudentInfo.Student student1 = StudentInfo.Student.parseFrom(sb);
        System.out.println(student1.getName()+" "+ student1.getAge() +" " +student1.getAddress());
    }
}
