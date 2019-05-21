package com.test;

public class Student {

    public void doSomething(){
        System.out.println("111111111");
    }

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 100);
        Date finalDate = calendar.getTime();
// 打印日期
        System.out.println("*******"+sdf.format(finalDate )+"********");
    }

}
