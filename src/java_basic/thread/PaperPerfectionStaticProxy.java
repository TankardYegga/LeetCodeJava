package java_basic.thread;

/**
 * Description: JVM
 * Creator: levin
 * Date: 10/13/2022
 * Time: 10:29 AM
 * Email: levinforward@163.com
 */
public class PaperPerfectionStaticProxy {

    public static void main(String[] args) {
        new PaperPerfectionInstitute(new PaperAuthor()).correctPaper();
    }
}


interface PaperService{
    void correctPaper();
}

class PaperAuthor implements PaperService{

    @Override
    public void correctPaper() {
        System.out.println("I help revise the paper");
    }
}

class PaperPerfectionInstitute implements PaperService{

    public PaperService target;

    public PaperPerfectionInstitute(PaperService target) {
        this.target = target;
    }

    @Override
    public void correctPaper() {
        beforeDraft();
        target.correctPaper();
        afterDraft();
    }

    private void afterDraft() {
        System.out.println("Charge the fees");
    }

    private void beforeDraft(){
        System.out.println("Tell the requirements:");
        System.out.println("Sign the informed consent");
    }
}