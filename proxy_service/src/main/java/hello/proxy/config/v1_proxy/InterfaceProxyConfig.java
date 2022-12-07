package hello.proxy.config.v1_proxy;

import hello.proxy.app.v1.*;
import hello.proxy.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterfaceProxyConfig {
    /**
     * 빈으로 OrderControllerInterfaceProxy 가 등록됨
     * @param logTrace
     * @return
     */
    @Bean
    public OrderControllerV1 orderController(LogTrace logTrace) { // 스프링 빈이 넘어온다.
        /*
         orderControllerImpl : 실제 객체인데, 이 안의 주입된 orderService 가 프록시 객체다.
         orderControllerInterfaceProxy 에서 target.request(itemId) 은 결국 위 impl 객체를 호출한 것이다.
         orderControllerImpl 안의 orderService 가 프록시 객체고, 여기서 serviceProxy를 호출하고,
         orderServiceInterfaceProxy 에서 target.orderItem(itemId); 가 실제 객체의 orderItem 을 호출한 것이다.

         결국 Proxy 에서 호출하는 실제 객체에 주입된 객체가 프록시.
         controller, service, repository 실제 객체를 찾아가기전, 프록시를 먼저 호출한다.
         */
        OrderControllerV1Impl orderControllerImpl = new OrderControllerV1Impl(orderService(logTrace));
        return new OrderControllerInterfaceProxy(orderControllerImpl, logTrace);
    }

    /**
     * 빈으로 OrderServiceInterfaceProxy 가 등록됨
     * @param logTrace
     * @return
     */
    @Bean
    public OrderServiceV1 orderService(LogTrace logTrace) {
        OrderServiceV1Impl serviceImpl = new OrderServiceV1Impl(orderRepository(logTrace));
        // 프록시에서 실제 객체를 호출한다.
        // serviceImpl : 실제 객체
        // 실제 객체가 orderRepository 를 호출하는데, 얘가 프록시다.
        // serviceImpl 안의 주입된 OrderRepository 가 프록시 (orderRepository(logTrace) 가 프록시를 리턴)
        // OrderServiceInterfaceProxy 에서 target.orderItem()을 호출한다. (target 이 프록시)
        // orderRepositoryProxy 를 타고, 그 안에서 실제객체가 호출됨
        return new OrderServiceInterfaceProxy(serviceImpl, logTrace);
    }

    /**
     * 빈으로 OrderRepositoryInterfaceProxy 가 등록됨
     * @param logTrace
     * @return
     */
    @Bean
    public OrderRepositoryV1 orderRepository(LogTrace logTrace) {
        OrderRepositoryV1Impl repositoryImpl = new OrderRepositoryV1Impl();
        return new OrderRepositoryInterfaceProxy(repositoryImpl, logTrace);
    }
}
