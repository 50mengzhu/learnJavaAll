#### 理解一下Quartz的执行逻辑
在Quartz框架中存在三个主要组件，他们分别是

| 组件名 | 作用 |
| :----: | :----: |
| Scheduler | 用于调度的抽象接口 |
| Job | 具体运行的任务 |
| Trigger | 定时任务的调度信息 |

以上三个组件的关系是这样的
<p/>

![](https://img-blog.csdn.net/20180710135431806?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L25vYW1hbl93Z3M=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)
其中Job很好理解，就是我们需要执行的任务；而Trigger则是我们定期执行时的周期信息等。

<p>
那么怎样将这两个实例联系起来呢，实际上就是通过我们上述的Scheduler来实现这个功能。
了解之后我们先简单的了解一下代码，相关代码的描述请参考同级目录下的测试代码。

#### 在使用Quartz时需要注意的一些问题
##### Q1：当任务执行的时间超过定时任务的间隔，会产生什么现象？怎么处理
这个问题其实比较常见，当我们的任务由于没有正确的进行时间预测，出现问题中描述的情况。那么我们应该怎么处理这种场景呢？

而且实际上，对于Quartz框架来说也不是单线程去运行任务的，而是默认采用的是10个线程。
> 这里Quartz使用的线程默认是10个的依据是在quartz.properties中存在这样的代码描述
```properties
org.quartz.threadPool.threadCount: 10
```
所以当出现以上问题的时候，如果不加控制，有可能同一个任务并发执行。而任务的并发执行就有可能让我们的业务陷入混乱之中。
<p>
目前说来，主要有以下几种处理思路——

+ 当任务到了调度时间点的时候，发现已有同样的任务实例在运行。那么将后来的任务直接丢弃，并记录未执行日志
+ 后来的任务进行排队，等到上一个任务实例完成之后，立即执行后入场的任务调度
+ 后来的任务进行排队，等待上一个任务实例完成之后，等待我们设定的间隔时间之后再进行任务调度
+ ...

当然大家所面临的业务逻辑也不一致，需要根据自己特定的业务逻辑进行相关的选择处理逻辑。

###### DisallowConcurrentExceution注解
其中Quartz框架为我们提供好了一些对上述问题的处理方式，比如我们上述提及到的第二种处理方式，在Quartz中可以使用`@DisallowConcurrentExceution`注解不允许并发执行。

此注解是使用在Job上防止在进行Job调度的时候，同一个任务会并行执行。而等上次任务执行完成之后，后续任务会立即跟上执行。


##### Q2: Quartz整合Spring注意事项
如果Quartz没有被Spring托管，那么需要将被spring托管的对象放入JobMapData中，因为Quartz不受Spring管理的话，而在定时任务中需要使用到Spring管理的对象的时候，
如果不从JobMapData中获取而是直接调用则是使用的是new出来的对象。那么此时必然会抛出空指针异常。示例代码如下——
```java
class Test {
    void func() {
        // ...
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("job1").build();
        jobDetail.getJobDataMap().put("task", jobDetail);
        // ...
    }
}
```

如果Quartz被Spring进行托管，那么就不需要在意上述的问题了，由于Quartz是被Spring托管，那么Quartz使用的变量实际上就是Spring托管的变量，因此不会出现以上的NPE。
p.s. 当Quartz被Spring管理之后，此时不允许并发执行调度任务的方式可以采用以下的方式，这和上述的注解`@DisallowConcurrentExceution`作用一致。
```xml
<bean name="thirdCron" class="org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean">
    <property name="targetObject">
        <bean class="com.hafiz.www.cron.ThirdCron"/>
    </property>
    <property name="targetMethod" value="executeJob"/>
    <!--作业不并发调度-->
    <property name="concurrent" value="false"/>
</bean>
```




