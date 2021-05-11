package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.example.mapper")

public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        System.out.println("/**********/**********/**********//**********//**********//**********//**********/\n" +
                "   .--,       .--,\n" +
                "  ( (  \\.---./  ) )\n" +
                "   '.__/o   o\\__.'\n" +
                "      {=  ^  =}\n" +
                "       >  -  <\n" +
                "      /       \\\n" +
                "     //       \\\\\n" +
                "    //|   .   |\\\\\n" +
                "    \"'\\       /'\"_.-~^`'-.\n" +
                "       \\  _  /--'         `\n" +
                "     ___)( )(___\n" +
                "    (((__) (__)))    高山仰止,景行行止.虽不能至,心向往之。\n" +
                " /**********/**********/**********//**********//**********//**********//**********/ ");

    }

}
