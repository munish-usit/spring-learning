&copy; Copyright (c) 2019, Munish. All rights reserved.

# Problem: Last value price service

##### Problem context: 
Task is to design and implement a service for keeping track of the last price for financial instruments.
Producers will use the service to publish prices and consumers will use it to obtain them.

The price data consists of records with the following fields:
* id: a string field to indicate which instrument this price refers to.
* asOf: a date time field to indicate when the price was determined. 
* payload: the price data itself, which is a flexible data structure.

Producers should be able to provide prices in batch runs.
The sequence of uploading a batch run is as follows:
1. The producer indicates that a batch run is started
2. The producer uploads the records in the batch run in multiple chunks of 1000 records.
3. The producer completes or cancels the batch run.

Consumers can request the last price record for a given id. 
The last value is determined by the asOf time, as set by the producer.
On completion, all prices in a batch run should be made available at the same time.
Batch runs which are cancelled can be discarded.

The service should be resilient against producers which call the service methods in an incorrect order, 
or clients which call the service while a batch is being processed.

	
##### Solution steps:
	1.	Parse input data and create instrument object for each valid line.
	2. 	Store the latest price value for each instrument in the localInstrumentPriceMap (specific for each batch task).
	3. 	After all batch task has been finished, upate global memory instrumentPriceMap.
	4.	Global instrumentPriceMap is updated based on the isin and its latest record as per the date.
	5.  Global instrumentPriceMap is updated synchronously by each thread.
	6.	Return the latest price for particular isin from global instrumentPriceMap.

##### Environment
1.	Java Version: 1.8
2.  Maven Version 3.6.1

##### Dependencies (pom.xml)
1.	Apache log4j
2.	Jackson 
3.	Junit

##### Installation
1.	Run mvn clean install


##### Unit Test Cases
1.	ConfigLoadTest -- to test if config.json is correctly loaded.
2.	DiskManager	Test -- to configure baseDir path
3.	BatchTaskProducerTest -- to start/stop and upload input data in batch run 
4.	ConsumerTaskTest -- to test latest price value for an instrument.

> Assumption: 

 *	Input data format "INE619B01017,10-01-2018,234". 
 *  Only latest price for the instrument is stored in the memory.

