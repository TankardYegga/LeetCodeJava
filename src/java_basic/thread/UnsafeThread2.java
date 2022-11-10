package java_basic.thread;

/**
 * Description: JVM
 * Creator: levin
 * Date: 10/15/2022
 * Time: 8:47 PM
 * Email: levinforward@163.com
 */
public class UnsafeThread2 {

    public static void main(String[] args) {
        Account ac = new Account("China Bank", 900);
        Drawing uncle = new Drawing("赵叔", "幸福", ac, 300);
        Drawing wife = new Drawing("赵叔的wife", "苏木", ac, 800);
        wife.start();
        uncle.start();
    }
}

class Account{
    public String name;
    public int money;

    public Account(String name, int money){
        this.money = money;
        this.name = name;
    }
}

class Drawing extends Thread{
    public Account account;
    public int drawMoney;
    public int packetMoney;
    public String drawPeople;

    public Drawing(String drawPeople, String name, Account account, int drawMoney){
        super(name);
        this.drawPeople = drawPeople;
        this.account = account;
        this.drawMoney = drawMoney;
    }

    @Override
    public void run(){
//        test();
        testSync();
    }

//    public synchronized void test(){
//        if(account.money - drawMoney < 0){
//            return;
//        }
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        account.money -= drawMoney;
//        packetMoney += drawMoney;
//        System.out.println(this.getName());
//        System.out.println(drawPeople + " draw " + drawMoney + " this time");
//        System.out.println(drawPeople + " has " + packetMoney + " in pocket now");
//        System.out.println(drawPeople + " has " + account.money + " left in the card");
//    }


    public void testSync(){
        if(account.money <= 0){
            return;
        }
        synchronized (account){
            if(account.money - drawMoney < 0){
                return;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            account.money -= drawMoney;
            packetMoney += drawMoney;
            System.out.println(this.getName());
            System.out.println(drawPeople + " draw " + drawMoney + " this time");
            System.out.println(drawPeople + " has " + packetMoney + " in pocket now");
            System.out.println(drawPeople + " has " + account.money + " left in the card");
        }
    }

}
