package com.hisign.publicsafety.common;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

/**
 * 
 * @author longyun
 * 2014年12月12日 上午9:43:20
 * com.hisign.guava.cache.GuavaCache.java
 * 回收的参数：
 * 1. 大小的设置：CacheBuilder.maximumSize(long)  CacheBuilder.weigher(Weigher)  CacheBuilder.maxumumWeigher(long)
 * 2. 时间：expireAfterAccess(long, TimeUnit) expireAfterWrite(long, TimeUnit)
 * 3. 引用：CacheBuilder.weakKeys() CacheBuilder.weakValues()  CacheBuilder.softValues()
 * 4. 明确的删除：invalidate(key)  invalidateAll(keys)  invalidateAll()
 * 5. 删除监听器：CacheBuilder.removalListener(RemovalListener)
 * 
 * refresh机制：
 *　1. LoadingCache.refresh(K)  在生成新的value的时候，旧的value依然会被使用。
 *　2. CacheLoader.reload(K, V) 生成新的value过程中允许使用旧的value
 *　3. CacheBuilder.refreshAfterWrite(long, TimeUnit) 自动刷新cache
 *
 *
 * guava Cache数据移除：

　　guava做cache时候数据的移除方式，在guava中数据的移除分为被动移除和主动移除两种。
　　被动移除数据的方式，guava默认提供了三种方式：
　　1.基于大小的移除:看字面意思就知道就是按照缓存的大小来移除，如果即将到达指定的大小，那就会把不常用的键值对从cache中移除。
　　定义的方式一般为 CacheBuilder.maximumSize(long)，还有一种一种可以算权重的方法，个人认为实际使用中不太用到。就这个常用的来看有几个注意点，
　　　　其一，这个size指的是cache中的条目数，不是内存大小或是其他；
　　　　其二，并不是完全到了指定的size系统才开始移除不常用的数据的，而是接近这个size的时候系统就会开始做移除的动作；
　　　　其三，如果一个键值对已经从缓存中被移除了，你再次请求访问的时候，如果cachebuild是使用cacheloader方式的，那依然还是会从cacheloader中再取一次值，如果这样还没有，就会抛出异常
　　2.基于时间的移除：guava提供了两个基于时间移除的方法
　　　　expireAfterAccess(long, TimeUnit)  这个方法是根据某个键值对最后一次访问之后多少时间后移除
　　　　expireAfterWrite(long, TimeUnit)  这个方法是根据某个键值对被创建或值被替换后多少时间移除
　　3.基于引用的移除：
　　这种移除方式主要是基于java的垃圾回收机制，根据键或者值的引用关系决定移除
　　主动移除数据方式，主动移除有三种方法：
　　1.单独移除用 Cache.invalidate(key)
　　2.批量移除用 Cache.invalidateAll(keys)
　　3.移除所有用 Cache.invalidateAll()
　　如果需要在移除数据的时候有所动作还可以定义Removal Listener，但是有点需要注意的是默认Removal Listener中的行为是和移除动作同步执行的，
       如果需要改成异步形式，可以考虑使用RemovalListeners.asynchronous(RemovalListener, Executor)
 */
public class GuavaCache {
	
	/**
	 * 日志实例对象
	 */
	private static Logger logger = LoggerFactory.getLogger(GuavaCache.class);
	
	/**
	 * 不需要延迟处理（泛型方式安装）
	 * @param cacheLoader
	 * @return
	 */
	public <K, V> LoadingCache<K, V> cached(CacheLoader<K, V> cacheLoader){
		LoadingCache<K, V> cache = CacheBuilder.newBuilder()
				.maximumSize(200)
				.weakKeys()
				.softValues()
				.expireAfterWrite(24, TimeUnit.HOURS)
				.removalListener(new RemovalListener<K, V>() {
					@Override
					public void onRemoval(RemovalNotification<K, V> kv) {
						logger.info(kv.getKey() + " was removed from loading cache...");
					}
				}).build(cacheLoader);
		
		return cache;
	}
	
	/**
     * 通过key获取value
     * 调用方式 commonCache.get(key) ; return String
     * @param key
     * @return
     * @throws Exception
     */
    public LoadingCache<String, Object> commonCache(final String key) throws Exception{
       
    	LoadingCache<String, Object> commonCache = cached(new CacheLoader<String, Object>(){
	        
    		@Override
	        public String load(String key) throws Exception {
    			logger.info("default value is the same as the key...");
	            return key;    
	        }
        });
        
        return commonCache;
    }
    
    /**
     * 对需要延迟处理的可以采用这个机制；(泛型的方式封装)
     * @param <K>
     * @param <V>
     * @param key
     * @param callable
     * @return V
     * @throws Exception
     */
    public static <K,V> Cache<K , V> callableCached(int maxNum, int expire){
          Cache<K, V> cache = CacheBuilder.newBuilder()
	          .maximumSize(maxNum)
	          .expireAfterWrite(expire, TimeUnit.HOURS)
	          .removalListener(new RemovalListener<K, V>() {
					@Override
					public void onRemoval(RemovalNotification<K, V> kv) {
						logger.info(kv.getKey() + " was removed from loading cache...");
					}
	          	})
	          .build();
          
          return cache;
    }

    /**
     * 
     * @param key
     * @return
     */
    private Object getCallableCache(Cache cache, final String key, Callable callable) {
           try {
             //Callable只有在缓存值不存在时，才会调用
             return cache.get(key, callable);
           } catch (ExecutionException e) {
             logger.error("acquire key [{}] from cache error:{}", new Object[]{key, e});
             return null;
           } 
    }
    
}
