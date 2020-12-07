package java.bgu.spl.mics;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * The {@link MessageBusImpl class is the implementation of the MessageBus interface.
 * Write your implementation here!
 * Only private fields and methods can be added to this class.
 */
public class MessageBusImpl implements MessageBus {

	private static MessageBusImpl instance;
	private ArrayList<MicroService> registeredMicroservice;
	// use of blocking queue - can you explain this example please(from forum)
	private ArrayList<PriorityBlockingQueue<Message>> microserviceMessageQueue;
	private ConcurrentHashMap<Class<? extends Message>, ArrayList<MicroService>> registrationHashMap;

	private MessageBusImpl()
	{
		instance = getInstance();
		registeredMicroservice = new ArrayList<>();
		microserviceMessageQueue = new ArrayList<>();
		registrationHashMap = new ConcurrentHashMap<>();
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
			registrationHashMap.put(type, new ArrayList<MicroService>());
		}
		registrationHashMap.get(type).add(m);
	}

	@Override
	public void subscribeBroadcast(Class<? extends Broadcast> type, MicroService m) {
		if(!registrationHashMap.containsKey(type)){
			registrationHashMap.put(type, new ArrayList<MicroService>());
		}
		registrationHashMap.get(type).add(m);
    }

	@Override @SuppressWarnings("unchecked")
	public <T> void complete(Event<T> e, T result) {
		//?
	}

	@Override
	public void sendBroadcast(Broadcast b) {
		// retrieving list of microservices which are subscribed to broadcast b
		ArrayList<MicroService> a = registrationHashMap.get(b);
		// adding the broadcast message to the queue of each microservice on the subscribed list
		for (Object o : a) {
			int index = registeredMicroservice.indexOf(o);
			microserviceMessageQueue.get(index).add(b);
		}
	}

	
	@Override
	public <T> Future<T> sendEvent(Event<T> e) {
		// microservice uses method to add the event to a different subscribed microservice
		// round robin

		// if event not subscribed to return null
		if(!registrationHashMap.containsKey(e)) {
			return null;
		} else {
			// retrieving list of microservices subscribed to this type of event
			ArrayList<MicroService> a = registrationHashMap.get(e);
			// first microservice in "queue will receive the message
			MicroService m = a.get(0);
			int index = registeredMicroservice.indexOf(m);
			microserviceMessageQueue.get(index).add(e);
			//round robin implementation - after microservice receives a message it is removed and added
			// in order to keep a linear order in which subscribed microservices receive event
			registrationHashMap.get(e).remove(m);
			registrationHashMap.get(e).add(m);
			//Future<Event> result = new Future();
			return null; //WHAT TO RETURNNNN
		}
	}

	@Override
	public void register(MicroService m) {
		registeredMicroservice.add(m);
		//add queue
		microserviceMessageQueue.add(new PriorityBlockingQueue<Message>());

	}

	@Override
	public void unregister(MicroService m) {
		// finding and removing microservice from list of subscribed microservices
		int i = registeredMicroservice.indexOf(m);
		registeredMicroservice.remove(i);

		//remove queue
		microserviceMessageQueue.remove(i);
		Set keys = registrationHashMap.keySet();
		Iterator iter = keys.iterator();
		while(iter.hasNext()){
			// TRY to remove casting
			Class<? extends Message> key = (Class<? extends Message>)iter.next();
			//Object key = iterator.next(); ***OPTIONAL***
			if(registrationHashMap.get(key).contains(m)){
				registrationHashMap.get(key).remove(m);
				if(registrationHashMap.get(key).isEmpty()){
					// if no microservices are subscribed to message remove key reference from hashmap
					registrationHashMap.remove(key);
				}
			}
		}
	}

	@Override
	public Message awaitMessage(MicroService m)  throws InterruptedException {
		int index = registeredMicroservice.indexOf(m);
		// throw exception???
		while(microserviceMessageQueue.get(index).isEmpty()){
			m.wait();
		}
		Message mes = microserviceMessageQueue.get(index).peek();
		microserviceMessageQueue.get(index).remove(mes);
		return mes;
	}
}
