/*
binary search can become complex when element duplication occurs
 there are 5 variants such as below:

1) Contains (True or False)
2) Index of first occurrence of a key
3) Index of last occurrence of a key
4) First Index of least element greater than key
5) Last Index of greatest element less than key
*/
class BinarySearch{
    
/*Variant 1: Contains key (True or False)

Input : 2 3 3 5 5 5 6 6
Function : Contains(4)
Returns : False

Function : Contains(5)
Returns : True
*/
public boolean containsKey(int arr[],int target){
    boolean result = false;
    int low = 0;
    int high = arr.length-1;
    while(low<=high){
        int mid = low + (high-low)/2;
        if(arr[mid] > target){
            high = mid - 1;
        }
        else if(arr[mid] < target){
            low = mid + 1;
        }
        else{
            result = true;
            break;
        }
    }
    return result;
}
    
/*Variant 2: First occurrence of key (index of array).
Input : 2 3 3 5 5 5 6 6
Function : first(3)
Returns : 1

Function : first(5)
Returns : 3

Function : first(4)
Returns : -1
*/
    
public int firstOccurrence(int arr[],int target){
    int result = -1; //to be returned if the element is not found
    int low = 0;
    int high = arr.length-1;
    while(low<=high){
        int mid = low + (high-low+1)/2;
        if(arr[mid] < target){
            low = mid + 1;
        }
        else if(arr[mid] > target){
            high = mid - 1;
        }
        else{
            result = mid; //storing the latest index when arr[mid]==target
            high = mid - 1; //we push the algorithm to the left in hopes of finding another occurrence of target, because we are supposed to return the first occurrence. 
            //Even if the current mid points to the first occurrence of the target it is safe to push the algorithm to left because we are storing the latest valid result.
        }
    }
    return result;
}
    
/*Variant 3: Last occurrence of key (index of array)

Input : 2 3 3 5 5 5 6 6
Function : last(3)
Returns : 2

Function : last(5)
Returns : 5

Function : last(4)
Returns : -1
*/
    
public int lastOccurrence(int arr[],int target){
    int result = -1;
    int low = 0;
    int high = arr.length-1;
    while(low<=high){
        int mid = low + (high-low+1)/2;
        if(arr[mid] < target){
            low = mid + 1;
        }
        else if(arr[mid] > target){
            high = mid - 1;
        }
        else{
            result = mid;
            low = mid + 1; //pushing the algorithm to explore the right side in hopes to find last occurrence of element
        }
    }
    return result;
}
    
/*Variant 4: index(first occurrence) of least integer greater than key.
Input : 2 3 3 5 5 5 6 6
Function : leastGreater(2)
Returns : 1

Function : leastGreater(5)
Returns : 6
*/
public int firstOccurrenceGreaterThanKey(int arr[],int target){
    int result = -1;
    int low = 0;
    int high = arr.length-1;
    while(low<=high){
        int mid = low + (high-low+1)/2;
        if(arr[mid] < target){
            low = mid + 1;
        }
        else if(arr[mid] > target){
            result = mid;
            high = mid - 1; //to find the first index ==> push left
        }
        else{
            low = mid + 1;
        }
    }
    return result;
}

/*Variant 5: index(last occurrence) of greatest integer lesser than key

Input : 2 3 3 5 5 5 6 6
Function : greatestLesser(2)
Returns : -1

Function : greatestLesser(5)
Returns : 2
*/
    
public int lastOccurrenceLesserThanKey(int arr[],int target){
    int result = -1;
    int low = 0;
    int high = arr.length-1;
    while(low<=high){
        int mid = low + (high-low+1)/2;
        if(arr[mid] < target){
            result = mid;
            low = mid + 1; //to find last index push right
        }
        else if(arr[mid] > target){
            high = mid - 1;
        }
        else{
            high = mid - 1;
        }
    }
    return result;
}
    
}
