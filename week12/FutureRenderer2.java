package Week12;

import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureRenderer2 {
	private final ExecutorService executor = new ScheduledThreadPoolExecutor (100);
	
	void renderPage (CharSequence source) throws Exception {
		final List<ImageInfo> imageInfos = scanForImageInfo(source);
		
		Callable<List<ImageData>> task = new Callable<List<ImageData>>() {
			public List<ImageData> call () {
				List<ImageData> result = new ArrayList<ImageData>();
				for (ImageInfo imageInfo : imageInfos) {
					result.add(imageInfo.downloadImage());
				}
				
				return result; 
			}
		};
		
		Future<List<ImageData>> future = executor.submit(task);
		renderText(source);
		
		try {
			List<ImageData> imageData = future.get(3, TimeUnit.SECONDS);
			for (ImageData data: imageData) {
				renderImage(data);
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			future.cancel(true);
		} catch (TimeoutException e) {
			renderCross();
		}
	}

	private void renderCross() {
		// TODO Auto-generated method stub
		
	}

	private void renderImage(ImageData data) {
		// TODO Auto-generated method stub
		
	}

	private void renderText(CharSequence source) {
		// TODO Auto-generated method stub
		
	}

	private List<ImageInfo> scanForImageInfo(CharSequence source) {
		// TODO Auto-generated method stub
		return null;
	}
}