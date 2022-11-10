package java_basic.thread_advanced;

import java.util.LinkedList;
import java.util.List;

/**
 * Description: 每个顾客可以指定需要选择哪些座位
 * Creator: levin
 * Date: 10/18/2022
 * Time: 3:14 PM
 * Email: levinforward@163.com
 */
public class HappyCinemaAdvanced {

    public static void main(String[] args) {
        List<Integer> available = new LinkedList<>();
        available.add(3);
        available.add(5);
        available.add(12);
        available.add(2);
        available.add(1);

        List<Integer> seat1 = new LinkedList<>();
        seat1.add(3);
        seat1.add(12);

        List<Integer> seats2 = new LinkedList<>();
        seats2.add(2);
        seats2.add(9);

        HappyCinemaWithSeatsList cinema = new HappyCinemaWithSeatsList(available, "jiu guan");
        new Thread(new HappyCustomer(cinema, seat1), "滑雪瀛").start();
        new Thread(new HappyCustomer(cinema, seats2), "五台封").start();
    }
}

class HappyCustomer implements Runnable{
    private HappyCinemaWithSeatsList cinema;
    private List<Integer> seats;

    public HappyCustomer(HappyCinemaWithSeatsList cinema, List<Integer> seats){
        this.cinema = cinema;
        this.seats = seats;
    }

    @Override
    public void run() {
        boolean flag = cinema.bookSeats(seats);
        if(flag){
            System.out.println(Thread.currentThread().getName() + "预订成功! 剩下的座位是：" + cinema.available);
        }else {
            System.out.println(Thread.currentThread().getName() + "预订失败！剩余座位是:" + cinema.available);
        }
    }
}

class HappyCinemaWithSeatsList{
    public List<Integer> available;
    public String name;

    public HappyCinemaWithSeatsList(List<Integer> available, String name){
        this.available = available;
        this.name = name;
    }

    public boolean bookSeats(List<Integer> seats){
        synchronized (available){
            System.out.println("已有的座位是:" + available) ;
            System.out.println("需要预订的座位是：" + seats);

            List<Integer> copy = new LinkedList<>();

            copy.addAll(available);

            copy.removeAll(seats);

            if(copy.size() == available.size() - seats.size()){
                available = copy;
                return true;
            }
            return false;
        }
    }
}