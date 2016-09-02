# examprep1producerconsumer

Explain about Thread Programming including:

? When and why we will use Threads in our programs?

	Threads can be implemented for many purposes, but it doesn't always make sense to use them.
	First of all there is a hardware limitation that we need to consider. A single core on the computer
	can run a single thread at a time. If we are developing applications to a device with only one core,
	we get no benefit from using threads. Today modern computers (laptops, desktops) have multiple cores, enabling
	the benefits of using threads. 
	Next threads are usually used for asynchronous tasks or background tasks. For instance, some applications need
	to do more than one event at a time. This is not feasible with a single thread (the main thread). This is because
	event handling needs to exist alongside the rest of the instructions that we need to compute. An example of this is 
	GUI especially Swing. So one thread takes care of listening for events such as keystrokes, mouse clicks etc while
	others handle instructions that need to compute either in the "foreground" or in the background.
	
? Explain about the Race Condition Problem and ways to solve it in Java:

	The race condition is one of the 3 issues we can encounter when we work with threads. A race condition is defined
	as the problem that occurs when two or more threads attempts to access the same memory/resource/hardware. The name
	implies that there is a race going on. The metaphor is that the resources are manipulated by whichever thread reaches
	it first. This is unpredictable and we have no control over which threads go off in which order. As a result
	simple operations such as counting, summation, increments and so on can execute in ways we did not intent.

? Explain the producer/consumer problem and how to solve it in modern java programs:

	We have two actors involved: the producer and the consumer.
	The producer is the one sending data or inputting data to, for instance in this example, a buffer. The consumer is the one taking data out
	Say the producer sends more data in and faster than the consumer can take out. Our buffer can only hold so much, 
	The opposite is also true. We cannot take data out of an empty buffer.
	The solution is using sleep or discarding data. When a buffer is empty, the consumer rests until notified when it's not. When the buffer is full
	the producer discards the data until notified that it's not full. 

?	Explain what 'busy waiting' is and why it's a bad thing in modern software systems:

	Busy waiting is when a thread is waiting for a criteria to be met without going to sleep and waiting to be notified. It keeps evaluating if the criteria
	is met, usually using something like a while-loop. The reason it is bad is because it consumes system resources and slows down performance. So unlike
	waiting to be notified and resting, we reserve some of our CPU while waiting for a criteria. Hence we are busy waiting. 
	
?	Describe java's BlockingQueue interface, examples of relevant implementations and methods relevant as a solution to the P/C problem. 

	It is an interface that a class can implement that supplies the following functionality: 
	add(object), offer(object), put(object), offer(object, with timeout),
	remove(object), poll(); take(), poll(with timeout),
	element(), peek()
	The methods are implemented so that you can run the type of operation you need in a concurrent application to avoid the P/C problem. 
	The following buffer(queues) implements BlockingQueue: ArrayBlockingQueue, DelayQueue, LinkedBlockingQueue, PriorityBlockingQueue and SynchronousQueue.
	The methods put() can be used to attempt inserting data which will only happen if our buffer/queue) is not full. It blocks the method call until not full.
	Similar to take() which operates the same when taking data out. 
	
?	Calculate the time it takes for each execution and explain (as well as you can) the result:
	The result was random for my solution. It ranges from 2000 - 2450ms. It was possible to finish late with 4 threads
	as well as with 1. I have the two following potential explanations for this:
	
		Explanation 1:
		The first culprit I suspect is my solution itself for my alternative method in ExamPrep1ProducerConsumer2.
		If my implementation of the threads is slowly / ineffective or even down right wrong. We get none of the 
		benefits of utilizing multiple cores and as such at run time we see the same results as a 
		single core (thread).

		Explanation 2:
		I suspect the exponential nature of calculating the fibonacci sequence recursivel 'O(2^n)'.
	
