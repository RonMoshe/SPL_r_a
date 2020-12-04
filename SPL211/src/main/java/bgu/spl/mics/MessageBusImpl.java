package java.bgu.spl.mics;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The {@link MessageBusImpl class is the implementation of the MessageBus interface.
 * Write your implementation here!
 * Only private fields and methods can be added to this class.
 */
public class MessageBusImpl implements MessageBus {

	private static MessageBusImpl instance;
	private ArrayList<MicroService> registeredMicroservice;
	private ArrayList<PriorityQueue<Message>> microserviceMessageQueue;
	private ConcurrentHashMap<Class<? extends Message>, ArrayList<MicroService>> registrationHashMap;


	private MessageBusImpl()
	{
		instance = getInstance();
		registeredMicroservice = new ArrayList<MicroService>();
		microserviceMessageQueue = new ArrayList<PriorityQueue<Message>>();
	}

	//synchronized method to control simultaneous access
	synchronized public static MessageBusImpl getInstance()
	{
		if (instance == null)
		{
			// if instance is null, initialize
			instance = new MessageBusImpl();
		}
		return instance;
	}

	@Override
	public <T> void subscribeEvent(Class<? extends Event<T>> type, MicroService m) {
		if(!registrationHashMap.containsKey(type)){

			registrationHashMap.putIfAbsent(type, new ArrayList<MicroService>());
		}
		registrationHashMap.get(type).add(m);
	}

	@Override
	public void subscribeBroadcast(Class<? extends Broadcast> type, MicroService m) {
		if(!registrationHashMap.containsKey(type)){
			registrationHashMap.putIfAbsent(type, new ArrayList<MicroService>());
		}
		registrationHashMap.get(type).add(m);
    }

	@Override @SuppressWarnings("unchecked")
	public <T> void complete(Event<T> e, T result) {
		//?

	}

	@Override
	public void sendBroadcast(Broadcast b) {
		ArrayList a = registrationHashMap.get(b);
		for(int i = 0; i < a.size(); i++){
			int index = registeredMicroservice.indexOf(a.get(i));
			microserviceMessageQueue.get(index).add(b);
		}
	}

	
	@Override
	public <T> Future<T> sendEvent(Event<T> e) {
		// microservice uses method to add the event to a different subscribed microservice
		// round robin
        return null;
	}

	@Override
	public void register(MicroService m) {
		registeredMicroservice.add(m);
		//add queue
		microserviceMessageQueue.add(new PriorityQueue<Message>());
	}

	@Override
	public void unregister(MicroService m) {
		int i = registeredMicroservice.indexOf(m);
		registeredMicroservice.remove(i);
		//remove queue
		microserviceMessageQueue.remove(i);
		//registrationHashMap.forEac;
		Set keys = registrationHashMap.keySet();
		Iterator iter = keys.iterator();
		while(iter.hasNext()){
			// TRY to remove casting
			Class<? extends Message> key = (Class<? extends Message>)iter.next();
			if(registrationHashMap.get(key).contains(m)){
				registrationHashMap.get(key).remove(m);
			}
		}
	}

	@Override
	public Message awaitMessage(MicroService m)  throws InterruptedException {
		int index = registeredMicroservice.indexOf(m);
		// throw exception???
		while(microserviceMessageQueue.get(index).isEmpty()){
			m.wait(1);
		}
		Message mes = microserviceMessageQueue.get(index).peek();
		microserviceMessageQueue.get(index).remove(mes);
		return mes;
	}
}
