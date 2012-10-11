package com.orange.pobox;

import java.util.ArrayList;

public class MinimumLinkedList<E>
{
    public MinimumLinkedList(int maxElementCount)
    {
     this.maxElementCount = maxElementCount;
     this.list = new ArrayList<E>();
     this.valueList = new ArrayList<Double>();
    }
    
    public void add(E e, double value)
    {
     int position = findPosition(value);
     
         if (position >= maxElementCount)
             return;
         
     int elementCount = list.size();
     list.add(position, e);
     valueList.add(position, value);
     
         if (elementCount == maxElementCount + 1) // So if we've hit the limit pop off the largest valued one
         {
          list.remove(maxElementCount);
          valueList.remove(maxElementCount);
         }
    }
    
    private int findPosition(double value)
    {
     int count = 0;
     
         for (Double val : valueList)
         {
             if (value < val)
                 return count;
             
          count++;
         }
     
     return count;
    }

    public ArrayList<E> getArrayList()
    {
     return list;
    }
    
    public int size()
    {
     return list.size();
    }
    
 private ArrayList<E> list;
 private ArrayList<Double> valueList;
 private int maxElementCount;
}
