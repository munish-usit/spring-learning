package com.learning.test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class CompletableFutureTest {

	private static ExecutorService executorService = Executors.newFixedThreadPool(4);
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		System.out.println("main 1 "+Thread.currentThread().getName());
		CompletableFuture<String> cf = CompletableFuture.supplyAsync(
				()-> getDataById(10), 
				executorService
			)
			.thenApply(data -> sendData(data));
		
		System.out.println("main 2 "+Thread.currentThread().getName());
		cf.get();
		System.out.println("main 3 "+Thread.currentThread().getName());
		executorService.shutdown();
	}
	private static String getDataById(int id) {
		System.out.println("getDataById: "+ Thread.currentThread().getName());
		try {
			Thread.sleep(1000);
		  } catch (InterruptedException e) {
			e.printStackTrace();
		  }
		return "Data:"+ id;
	}
	
	private static void task2(String data) {
		CompletableFuture<String> cf = CompletableFuture.supplyAsync(
				()-> sendData(data),executorService);
	}
	private static String sendData(String data) {
		System.out.println("sendData: "+ Thread.currentThread().getName());
		System.out.println(data +" "+ Thread.currentThread().getName());
		return data;
	}

}
