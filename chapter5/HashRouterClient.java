package chapter5;

public class HashRouterClient {
    public static void main(String[] args){
        ConsistentHashRouter consistentHashRouter=new ConsistentHashRouter(4, 6, 100);
        consistentHashRouter.analyzeDistribution();
    }
}
