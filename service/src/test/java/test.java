import com.aopdemo.LiuTest;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class test {
    @Test
    @LiuTest
public void retryTest(){
    String a="1112123213132";
    int length=a.length();
    if(length>4){
      a=a.substring(0, 4);
      System.out.println(a);
    }
  else{
        System.out.println(a);
    }
    }

}
