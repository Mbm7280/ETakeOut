package com.echo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
@EnableCaching //开启Spring Cache注解方式是缓存功能
public class AppRun {
    public static void main(String[] args) {
        SpringApplication.run(AppRun.class,args);
        System.out.println("       !       \n" +
                "       !       \n" +
                "       ^       \n" +
                "      / \\      \n" +
                "     /___\\     \n" +
                "    |=   =|    \n" +
                "    |     |    \n" +
                "    |     |    \n" +
                "    |     |    \n" +
                "    |     |    \n" +
                "    |     |    \n" +
                "    |     |    \n" +
                "    |     |    \n" +
                "    |     |    \n" +
                "    |     |    \n" +
                "   /|##!##|\\   \n" +
                "  / |##!##| \\  \n" +
                " /  |##!##|  \\ \n" +
                "|  / ^ | ^ \\  |\n" +
                "| /  ( | )  \\ |\n" +
                "|/   ( | )   \\|\n" +
                "    ((   ))    \n" +
                "   ((  :  ))   \n" +
                "   ((  :  ))   \n" +
                "    ((   ))    \n" +
                "     (( ))     \n" +
                "      ( )      \n" +
                "       .       \n" +
                "       .       \n" +
                "       .      \n" +
                "    启动成功");
    }
}
