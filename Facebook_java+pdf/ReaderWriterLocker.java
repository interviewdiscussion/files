第二题，reader writer problem，
给定：
  Mutex:. 
    void lock();
    bool unlock();

  SharedMutex:
    void readerLock();.
    void readerUnlock();
    void writerLock();
    void writerUnlock();


实现下面4个api，自己定义mutex和variable，不管starvation。
    
public class ReadWriteLock{
    
  private int readers       = 0;
  private int writers       = 0;
  private int writeRequests = 0;

  public void lockRead() throws InterruptedException{
    lock();
    while(writers > 0 || writeRequests > 0){
        await();
    readers++;
    unlock();
    
  }

  public void unlockRead(){
    lock();
    readers--;
    signal();
    unlock();
  }

  public void lockWrite() throws InterruptedException{
      lock();
    writeRequests++;

    while(readers > 0 || writers > 0){
      await();
    }
    writeRequests--;
    writers++;
      unlock();
  }

  public void unlockWrite() throws InterruptedException{
      lock();
    writers--;
    notifyAll();
      unlock();
  }
}