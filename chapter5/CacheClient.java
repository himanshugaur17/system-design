package chapter5;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CacheClient {
    public static void main(String[] args){
        DistributedCacheManager distributedCacheManager=new DistributedCacheManager();

        List<Employee> employees=IntStream.range(0, 101)
        .mapToObj(i->new Employee(i, "Himanshu-"+i))
        .collect(Collectors.toList());


    }
}
