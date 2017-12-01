package com.noshow.test;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.noshow.service.OwnerMemberService;
import com.noshow.vo.Restaurant;

public class OwnerMemberTest {
   public static void main(String[] args) {
      ApplicationContext ctx = new ClassPathXmlApplicationContext("com/noshow/config/spring/model-context.xml");
      
      OwnerMemberService service = (OwnerMemberService)ctx.getBean("ownerMemberServiceImpl");
      
     /*Restaurant rt = new Restaurant("id-1", 1111, "꼬미네", "031111222", "치킨집", "WED", 
    		  new Date(20170511), new Date(20170311), 3, "ggomi.jpg", "ggomi2.jpg", "경기도 수원시", 100, 50000);
      System.out.println(rt);*/

      
     /* int i = service.insertRestaurant(rt, "role");
      System.out.println("식당정보 등록" +i);*/
      
      
      /*int u = service.updateRestaurant(new Restaurant("id-1", 2222, "박꼬미네", "031-1222-111", "치킨집", "THU", new Date(20170511), new Date(20170311), 
    		  "ggomi.jpg", "ggomi2.jpg", "경기도 성남시", 100, 50000));
      System.out.println("식당정보 업데이트" +u);*/
      
      /*int d = service.deleteRestaurant("id-1");
      System.out.println("식당삭제" +d);*/
      
      /*Restaurant sel = service.selectRestaurantByBusinessId("id-7");
      System.out.println(sel);*/
   }

}