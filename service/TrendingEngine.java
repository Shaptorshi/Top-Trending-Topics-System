package src1.service;

import src1.model.Event;
import java.util.*;
import java.util.Map.Entry;

public class TrendingEngine{
    //stores event objects
    Queue<Event> windowQueue;
    Map<String,Integer> freqMap;
    int windowSize;

    public TrendingEngine(int windowSize){
        this.windowQueue = new LinkedList<>();
        this.freqMap = new HashMap<>();
        this.windowSize = windowSize;
    }

    public void addEvent(String topic,int timeStamp){
        Event event = new Event(topic,timeStamp);
        //add new event to queue
        windowQueue.offer(event);
        //update frequency map
        freqMap.put(topic, freqMap.getOrDefault(topic,0)+1);
        //remove expired events
        removeExpiredEvents(timeStamp);
    }

    private void removeExpiredEvents(int currTime){
        while(!windowQueue.isEmpty() && currTime - windowQueue.peek().getTimeStamp()>windowSize){
            Event expired = windowQueue.poll();
            String topic = expired.getTopic();

            freqMap.put(topic, freqMap.get(topic)-1);

            if(freqMap.get(topic)==0){
                freqMap.remove(topic);
            }
        }
    }
    public List<String> getTopK(int k){
        PriorityQueue<Map.Entry<String,Integer>> minHeap = new PriorityQueue<>((a,b)->Integer.compare(a.getValue(),b.getValue()));

        for(Entry<String,Integer> entry: freqMap.entrySet()){
            minHeap.offer(entry);
        
            if(minHeap.size()>k){
                minHeap.poll();
            }
        }
        List<String> result = new ArrayList<>();

        while(!minHeap.isEmpty()){
            result.add(minHeap.poll().getKey());
        }
        //for having the highest frequency item at the front
        Collections.reverse(result);

        return result;
    }
}

/* 
Time Complexity:-

offer -> O(log K)
poll -> O(log K)

total: O(N log K)

Space Complexity:-

O(K)
*/
