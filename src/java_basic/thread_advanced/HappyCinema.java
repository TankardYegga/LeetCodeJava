package java_basic.thread_advanced;

/**
 * Description: 不同顾客抢电影院的票,只指定抢的数目但不具体是哪些具体座位的
 * Creator: levin
 * Date: 10/18/2022
 * Time: 2:24 PM
 * Email: levinforward@163.com
 */
public class HappyCinema {

    public static void main(String[] args) throws InterruptedException{
        Cinema c = new Cinema(1, "Happiness");
        new Thread(new Customer(c, "Ada", 1), "Ada").start();
        new Thread(new Customer(c, "Josey", 1), "Josey").start();
        Thread.sleep(1000);
        System.out.println(c.getTicketsNum());
    }
}

class Customer implements Runnable{

    private Cinema cinema;
    private String name;
    private int tickets;

    public Customer(Cinema cinema, String name, int tickets){
        this.cinema = cinema;
        this.name = name;
        this.tickets = tickets;
    }

    @Override
    public void run() {
        synchronized (cinema){
            boolean flag = cinema.buyTickets(tickets);
            if(flag){
                System.out.println(Thread.currentThread().getName() + " 购票成功！");
            }else {
                System.out.println(Thread.currentThread().getName() + " 购票失败！");
            }
        }
    }
}

class Cinema{

    public int ticketsNum;
    public String name;

    public int getTicketsNum(){
        return ticketsNum;
    }

    public Cinema(int ticketsNum, String name){
        this.ticketsNum = ticketsNum;
        this.name = name;
    }

    public boolean buyTickets(int tickets){
        if(ticketsNum >= tickets){
            ticketsNum -= tickets;
            return true;
        }
        return false;
    }
}
